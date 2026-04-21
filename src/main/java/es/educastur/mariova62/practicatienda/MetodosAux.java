/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.educastur.mariova62.practicatienda;

/**
 *
 * @author 1dawd07
 */
public class MetodosAux {

    /**
     * Comprueba si un numero "s" es Int
     *
     * @param s numero a teclear
     * @return true si es Int, false si no lo es
     */
    public static boolean esInt(String s) {
        @SuppressWarnings("unused")
        int n;
        try {
            n = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Comprueba si un numero "s" es Double
     *
     * @param s numero a teclear
     * @return true si es Double, false si no lo es
     */
    public static boolean esDouble(String s) {
        @SuppressWarnings("unused")
        double n;
        try {
            n = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Valida un número de DNI español.
     *
     * Comprueba que el formato del DNI sea correcto (8 dígitos seguidos de una
     * letra) y que la letra se corresponda con el número según el algoritmo
     * oficial. La expresión regular excluye las letras I, Ñ, O, U.
     *
     * @param dni La cadena de texto con el DNI a validar.
     * @return "true" si el DNI es válido, "false" en caso contrario.
     */
    public static boolean validarDNI(String dni) {
        if (dni.isBlank() || !dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
            return false;
        }
        String numeroStr = dni.substring(0, 8);
        char letra = dni.charAt(8);
        char letraCalculada = calcularLetraDNI(Integer.parseInt(numeroStr));
        return letra == letraCalculada;
    }

    /**
     * Calcula la letra correspondiente a un número de DNI.
     *
     * @param numero Número del DNI.
     * @return La letra calculada.
     */
    private static char calcularLetraDNI(int numero) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        return letras.charAt(numero % 23);
    }

}
