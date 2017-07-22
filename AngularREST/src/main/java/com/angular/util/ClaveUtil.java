package com.angular.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * La clase ClaveUtil es utilizada para la administraci\u00f3n de los
 * m\u00e9todos utilitarios para las claves.
 *
 * @author Diego Tonguino
 * @version $Revision: $
 */
public class ClaveUtil {

    /**
     * Genera una clave aleatoria de N d\u00edgitos
     *
     * @param longitud la longitud de la generarClaveAleatoria
     * @return la clave generada
     */
    public static String generarClaveAleatoria(int longitud) {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }

    /**
     * Encripta la clave con el m\u00e9todo MD5 con un tama\u00f1o m\u00e1ximo
     * de 50 caracteres.
     *
     * @param clave la clave a ser encriptada
     * @param longitud la longitud de la generarClaveAleatoria
     * @return la clave encriptada
     */
    public static String encriptarClave(String clave, int longitud) {
        String md5 = md5(clave);
        return md5.substring(0, md5.length() >= longitud ? longitud : md5.length());
    }

    /**
     * Encripta la clave con el m\u00e9todo MD5.
     *
     * @param clave la clave a ser encriptada
     * @return la clave encriptada
     */
    private static String md5(String clave) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(clave.getBytes());

            int size = b.length;
            StringBuilder h = new StringBuilder(size);
            for (int i = 0; i < size; i++) {
                int u = b[i] & 255;
                if (u < 16) {
                    h.append("0").append(Integer.toHexString(u));
                } else {
                    h.append(Integer.toHexString(u));
                }
            }
            return h.toString();
        } catch (NoSuchAlgorithmException ex) {
            return clave;
        }
    }
}
