package pkg09.ordenamientodatos;

import java.text.DecimalFormat;

public class Util {

    //metodos para mostrar el tiempo
    private static long inicio;

    public static void iniciarCronometro() {
        inicio = System.currentTimeMillis();
    }

    //esto devuelve en milesimas
    public static long getTiempoCronometro() {
        return System.currentTimeMillis() - inicio;
    }

    //queremos ver en horas,minutos,seg
    public static String getTextoTiempoCronometro() {
        long tiempo = getTiempoCronometro();
        long ms = tiempo % 1000;
        tiempo = (tiempo - ms) / 1000;
        long s = tiempo % 60;
        tiempo = (tiempo - s) / 60;
        long m = tiempo % 60;
        tiempo = (tiempo - m) / 60;
        long h = tiempo % 60;
        DecimalFormat df = new DecimalFormat("00");
        DecimalFormat dfMS = new DecimalFormat("000");

        return df.format(h) + ":" + df.format(m) + ":" + df.format(s) + "." + dfMS.format(ms);
    }

}
