package pkg09.ordenamientodatos;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Documento {

    private String apellido1;
    private String apellido2;
    private String nombre;
    private String documento;

    public Documento(String apellido1, String apellido2, String nombre, String documento) {
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre = nombre;
        this.documento = documento;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombreCompleto() {
        return apellido1 + " " + apellido2 + " " + nombre;
    }

    //************ATRIBUTOS Y METODOS ESTATICOS****************
    public static List<Documento> documentos = new ArrayList();
    public static String[] encabezados;

    public static void obtenerDatosDesdeArchivo(String nombreArchivo) {
        documentos.clear();
        BufferedReader br = Archivo.abrirArchivo(nombreArchivo);
        if (br != null) {
            try {
                String linea = br.readLine();
                encabezados = linea.split(";");
                linea = br.readLine();
                while (linea != null) {
                    String[] textos = linea.split(";");
                    if (textos.length >= 4) {
                        Documento d = new Documento(textos[0], textos[1], textos[2], textos[3]);
                        documentos.add(d);
                    }
                    linea = br.readLine();
                }

            } catch (Exception ex) {

            }
        }
    }

    public static void mostrarDatos(JTable tbl) {
        String[][] datos = null;
        if (documentos.size() > 0) {
            datos = new String[documentos.size()][encabezados.length];
            for (int i = 0; i < documentos.size(); i++) {
                datos[i][0] = documentos.get(i).apellido1;
                datos[i][1] = documentos.get(i).apellido2;
                datos[i][2] = documentos.get(i).nombre;
                datos[i][3] = documentos.get(i).documento;
            }
        }

        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tbl.setModel(dtm);
    }

    //metodo para intercambiar elementos
    private static void intercambiar(int origen, int destino) {
        Documento temporal = documentos.get(origen);
        documentos.set(origen, documentos.get(destino));
        documentos.set(destino, temporal);
    }

//metodo para verificar si un documento es mayor que otro
    private static boolean esMayor(Documento d1, Documento d2, int criterio) {
        if (criterio == 0) {
            //ordenar primero por Nombre completo y luego tipo de documento
            return (d1.getNombreCompleto().compareTo(d2.getNombreCompleto()) > 0)
                    || (d1.getNombreCompleto().equals(d2.getNombreCompleto())
                    && d1.getDocumento().compareTo(d2.getDocumento()) > 0);
        } else {
            //ordenar primero por el tipo de documento y luego por el nombre completo
            return (d1.getDocumento().compareTo(d2.getDocumento()) > 0)
                    || (d1.getDocumento().equals(d2.getDocumento())
                    && d1.getNombreCompleto().compareTo(d2.getNombreCompleto()) > 0);
        }
    }

    public static void ordenarBurbujaRecursivo(int n, int criterio) {
        if (n == documentos.size() - 1) {
            return;
        } else {
            for (int i = n + 1; i < documentos.size(); i++) {
                if (esMayor(documentos.get(n), documentos.get(i), criterio)) {
                    intercambiar(n, i);
                }
            }
            ordenarBurbujaRecursivo(n + 1, criterio);
        }
    }

    //Método que ordena los datos según el algoritmo de la BURBUJA
    public static void ordenarBurbuja(int criterio) {
        for (int i = 0; i < documentos.size() - 1; i++) {
            for (int j = i + 1; j < documentos.size(); j++) {
                if (esMayor(documentos.get(i), documentos.get(j), criterio)) {
                    intercambiar(i, j);
                }
            }
        }
    }

    private static int localizarPivote(int inicio, int fin, int criterio) {
        int pivote = inicio;
        Documento dP = documentos.get(pivote);

        for (int i = inicio + 1; i <= fin; i++) {
            if (esMayor(dP, documentos.get(i), criterio)) {
                pivote++;
                if (i != pivote) {
                    intercambiar(i, pivote);
                }
            }
        }
        if (inicio != pivote) {
            intercambiar(inicio, pivote);
        }
        return pivote;
    }

    //Método que ordena los datos según el algoritmo RAPIDO
    public static void ordenarRapido(int inicio, int fin, int criterio) {
        //punto de finalización
        if (inicio >= fin) {
            return;
        }
        //casos recursivos
        int pivote = localizarPivote(inicio, fin, criterio);
        ordenarRapido(inicio, pivote - 1, criterio);
        ordenarRapido(pivote + 1, fin, criterio);
    }

    private static boolean esMenor(Documento d1, Documento d2, int criterio) {
        if (criterio == 0) {
            //ordenar primero por Nombre completo y luego tipo de documento
            return (d1.getNombreCompleto().compareTo(d2.getNombreCompleto()) < 0)
                    || (d1.getNombreCompleto().equals(d2.getNombreCompleto())
                    && d1.getDocumento().compareTo(d2.getDocumento()) < 0);
        } else {
            //ordenar primero por el tipo de documento y luego por el nombre completo
            return (d1.getDocumento().compareTo(d2.getDocumento()) < 0)
                    || (d1.getDocumento().equals(d2.getDocumento())
                    && d1.getNombreCompleto().compareTo(d2.getNombreCompleto()) < 0);
        }
    }

    public static void ordenarInserccionRecursivo(int criterio) {
        // Lo divido en pequeños bloques para ordenarse y evitar el error de StackOverflowError
        int tamañoBloque = 1000; // 

        ordenarInserccionRecursivoAux(0, tamañoBloque - 1, criterio);

        // Aplico el algoritmo de inserción recursivo a los bloques siguientes
        for (int i = tamañoBloque; i < documentos.size(); i += tamañoBloque) {
            int inicioBloque = i;
            int finBloque = Math.min(i + tamañoBloque - 1, documentos.size() - 1);
            ordenarInserccionRecursivoAux(inicioBloque, finBloque, criterio);
        }
    }

    private static void ordenarInserccionRecursivoAux(int inicio, int fin, int criterio) {
        // Verificamos que los índices estén dentro de los límites de la lista
        if (inicio < 0 || fin >= documentos.size() || inicio >= fin) {
            return;
        }
        //Divido la lista en subconjuntos más pequeños
        int medio = inicio + (fin - inicio) / 2;
        
        ordenarInserccionRecursivoAux(inicio, medio, criterio);
        ordenarInserccionRecursivoAux(medio + 1, fin, criterio);

        fusionar(inicio, medio, fin, criterio);
    }

//unir todas las sublistas     
    private static void fusionar(int inicio, int medio, int fin, int criterio) {
        List<Documento> temporal = new ArrayList<>();
        int i = inicio;
        int j = medio + 1;

        while (i <= medio && j <= fin) {
            if (esMenor(documentos.get(i), documentos.get(j), criterio)) {
                temporal.add(documentos.get(i));
                i++;
            } else {
                temporal.add(documentos.get(j));
                j++;
            }
        }
        while (i <= medio) {
            temporal.add(documentos.get(i));
            i++;
        }
        while (j <= fin) {
            temporal.add(documentos.get(j));
            j++;
        }
        for (int k = 0; k < temporal.size(); k++) {
            documentos.set(inicio + k, temporal.get(k));
        }
    }
}
