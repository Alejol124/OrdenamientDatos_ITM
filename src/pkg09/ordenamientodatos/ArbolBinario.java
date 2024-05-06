package pkg09.ordenamientodatos;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ArbolBinario {

    private Nodo raiz;
    private int criterio;

    public ArbolBinario(Nodo raiz) {
        this.raiz = raiz;
    }

    public ArbolBinario() {
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public int getCriterio() {
        return criterio;
    }

    public void setCriterio(int criterio) {
        this.criterio = criterio;
    }

    private boolean insertar(Nodo nRecorrido, Nodo n) {
        //no insertar si el documento es igual
        if (n.getDocumento().equals(nRecorrido.getDocumento())) {
            return false;
        } else if (!Documento.esMayor(n.getDocumento(), nRecorrido.getDocumento(), criterio)) {
            //insertar a la izquierda del nodo
            //esta disponible el nodo izquierdo?
            if (nRecorrido.izquierdo == null) {
                nRecorrido.izquierdo = n;
                return true;
            } else {
                //continuar recorriendo el subarbol izquierdo
                return insertar(nRecorrido.izquierdo, n);
            }
        } else {
            //insertar a la derecha del nodo
            //esta disponible el nodo izquierdo?
            if (nRecorrido.derecho == null) {
                nRecorrido.derecho = n;
                return true;
            } else {
                //continuar recorriendo el subarbol izquierdo
                return insertar(nRecorrido.derecho, n);
            }
        }
    }

    public boolean insertarNodo(Nodo n) {
        if (raiz == null) {
            raiz = n;
            return true;
        }

        Nodo actual = raiz;
        Nodo padre;

        while (true) {
            padre = actual;

            // Si el documento es igual, no se permite la inserción
            if (n.getDocumento().equals(actual.getDocumento())) {
                return false;

            } else if (!Documento.esMayor(n.getDocumento(), actual.getDocumento(), criterio)) {
                // Si el documento es menor, avanza hacia el subárbol izquierdo
                actual = actual.izquierdo;
                if (actual == null) {
                    padre.izquierdo = n;
                    return true;
                }
            } // Si el documento es mayor, avanza hacia el subárbol derecho
            else {
                actual = actual.derecho;
                if (actual == null) {
                    padre.derecho = n;
                    return true;
                }
            }
        }
    }

    public void recorrerInorden(Nodo n) {
        if (n != null) {
            recorrerInorden(n.izquierdo);
            recorrerInorden(n.derecho);
        }
    }

    public void mostrar(JTable tbl) {
        String[][] datos = null;
        if (raiz != null) {
            datos = new String[Documento.documentos.size()][Documento.encabezados.length];

            Nodo n = raiz;
            Nodo predecesor;
            int fila = -1;

            while (n != null) {
                if (n.izquierdo == null) {
                    fila++;
                    datos[fila][0] = n.getDocumento().getApellido1();
                    datos[fila][1] = n.getDocumento().getApellido2();
                    datos[fila][2] = n.getDocumento().getNombre();
                    datos[fila][3] = n.getDocumento().getDocumento();

                    n = n.derecho;
                } else {
                    // Encuentra el nodo predecesor
                    predecesor = n.izquierdo;
                    while (predecesor.derecho != null && predecesor.derecho != n) {
                        predecesor = predecesor.derecho;
                    }

                    // Si el predecesor aún no ha sido enlazado al nodo actual, enlázalo y avanza a la izquierda
                    if (predecesor.derecho == null) {
                        predecesor.derecho = n;
                        n = n.izquierdo;
                    } else {
                        // Si el predecesor ya está enlazado al nodo actual, rompe el enlace, procesa el nodo y avanza a la derecha
                        predecesor.derecho = null;
                        fila++;
                        datos[fila][0] = n.getDocumento().getApellido1();
                        datos[fila][1] = n.getDocumento().getApellido2();
                        datos[fila][2] = n.getDocumento().getNombre();
                        datos[fila][3] = n.getDocumento().getDocumento();
                        n = n.derecho;
                    }
                }
            }

        }
        DefaultTableModel dtm = new DefaultTableModel(datos, Documento.encabezados);
        tbl.setModel(dtm);
    }//mostrar

    public void buscarDatos(String dato, Nodo n, JTable tbl) {
        boolean encontrado = false;

        if (n != null) { //CASO BASE
            if (n.getDocumento().getNombreCompleto().equals(dato)) {
                encontrado = true;

            } else {
                buscarDatos(dato, n.izquierdo, tbl);
                buscarDatos(dato, n.derecho, tbl);
            }
        }
        if (encontrado) {
            String nombre = n.getDocumento().getNombre();
            String apellido1 = n.getDocumento().getApellido1();
            String apellido2 = n.getDocumento().getApellido2();

            for (int i = 0; i < tbl.getRowCount(); i++) {
                String apellido1Tabla = (String) tbl.getValueAt(i, 0);
                String apellido2Tabla = (String) tbl.getValueAt(i, 1);
                String nombreTabla = (String) tbl.getValueAt(i, 2);

                if (apellido1.equals(apellido1Tabla) && apellido2.equals(apellido2Tabla) && nombre.equals(nombreTabla)) {
                    tbl.setRowSelectionInterval(i, i);
                    tbl.scrollRectToVisible(tbl.getCellRect(i, 0, true));
                    break;
                }
            }

        }
    }

}
