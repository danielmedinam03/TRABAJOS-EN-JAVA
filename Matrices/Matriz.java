/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

//import java.util.InputMismatchException;
import javax.swing.JOptionPane;

public class Matriz {

    public static void main(String[] args) {

        //Nombre de las golosinas
        String[][] nombresGolosinas = {
            {"KitKat", "Chicles de fresa", "Lacasitos", "Palotes"},
            {"Kinder Bueno", "Bolsa variada Haribo", "Chetoos", "Twix"},
            {"Kinder Bueno", "M&M'S", "Papa Delta", "Chicles de menta"},
            {"Lacasitos", "Crunch", "Milkybar", "KitKat"}
        };

        //Precio de las golosinas
        double[][] precio = {
            {1000, 200, 500, 950},
            {1800, 100, 120, 100},
            {1750, 130, 120, 800},
            {1500, 110, 720, 350}
        };

        int cantidad[][] = new int[4][4];

        //Relleno la matriz con 5
        reLlenarMatriz(cantidad, 5);

        //Indica si salimos o no
        int opcion, i, j, cantidadNueva;
        String pos, contra;
        double ventaTotales = 0;

        //Bucle para pedir las opciones hasta que elijamos salir
        do {

            //opciones
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Menú Principal. \n"
                    + "1. Pedir Golosinas. \n"
                    + "2. Mostrar Golosinas. \n"
                    + "3. Llenar Golosinas. \n"
                    + "4. Estadisticas de la Maquina. \n"
                    + "5. Salir. \n"
                    + "Entre su opcion: ?"
            ));

            //Realiza una de las opciones
            switch (opcion) {
                case 1:

                    //Pido la posicion
                    pos = JOptionPane.showInputDialog("Introduce la posicion que quieras");

                    //Valido la posicion
                    if (validarPos(nombresGolosinas, pos)) {

                        //Extraigo la fila y columna
                        i = extraerNumero(pos, 0);
                        j = extraerNumero(pos, 1);

                        //Indico si hay valores en la matriz
                        if (hayValorPosicion(cantidad, i, j)) {
                            //Muestro la golosina
                            JOptionPane.showMessageDialog(null, "Golosiona: " + nombresGolosinas[i][j] + "");

                            //Reduzco la cantidad en 1
                            reducirPos(cantidad, i, j, 1);

                            //aumento la cantidad
                            ventaTotales += precio[i][j];

                        } else {
                            JOptionPane.showMessageDialog(null, "No hay mas golosinas de este tipo, espere al técnico para que la rellene");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "La posicion introducida no es valida");
                    }

                    break;
                case 2:

                    //muestro las golosinas
                    mostrarGolosionas(nombresGolosinas, precio, cantidad);

                    break;
                case 3:
                    int cont = 0;
                    contra = JOptionPane.showInputDialog("Digite contraseña: ");

                    do {
                        if (cadenaIguales(contra, "Maquina2021")) {

                            pos = JOptionPane.showInputDialog("Posición a elegir");
                            i = extraerNumero(pos, 0);
                            j = extraerNumero(pos, 1);

                            if (validarPos(nombresGolosinas, pos)) {
                                cantidadNueva = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de Golosinas: "));
                                aumentarPos(cantidad, i, j, cantidadNueva);
                                JOptionPane.showMessageDialog(null, "Se aumentaron la cantidad de golosinas.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Contraseña Invalida.");

                        }

                    } while (cont != 0);
                    break;
                case 4:

                    //Muestro las ventas
                    JOptionPane.showMessageDialog(null, "Las ventas han sido de " + ventaTotales);

                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Gracias por su compra!!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Las opciones son entre 1 y 5");
            }

            //controla la excepcionn en caso de que se introduzca un valor no correcto
        } while (opcion != 5);

    }

    public static void reLlenarMatriz(int[][] matriz, int num) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = num;
            }
        }

    }

    public static boolean validarPos(String[][] matriz, String pos) {

        if (pos.length() != 2) {
            return false;
        }

        if (!(Numero(pos.substring(0, 1)) && Numero(pos.substring(1, 2)))) {
            return false;
        }

        int i = extraerNumero(pos, 0);
        int j = extraerNumero(pos, 1);

        if (!((i >= 0 && i < matriz.length) && (j >= 0 && j < matriz[0].length))) {
            return false;
        }

        return true;

    }

    public static boolean Numero(String num) {

        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static int extraerNumero(String numero, int pos) {

        int num = -1;
        if (Numero(numero)) {
            num = Integer.parseInt(numero.substring(pos, pos + 1));
        }

        return num;

    }

    public static void reducirPos(int[][] matriz, int i, int j, int cantidad) {
        matriz[i][j] -= cantidad;
    }

    public static boolean hayValorPosicion(int[][] matriz, int i, int j) {
        if (matriz[i][j] > 0) {
            return true;
        }

        return false;
    }

    public static void mostrarGolosionas(String[][] nombres, double[][] precios, int cantidad[][]) {
        String posicion = "";

        for (int i = 0; i < nombres.length; i++) {
            for (int j = 0; j < nombres[i].length; j++) {

                posicion += "" + i + "" + j + ": Nombre:  " + nombres[i][j] + " - Precio: " + precios[i][j] + " - Cantidad: " + cantidad[i][j] + "\n";

            }
        }
        JOptionPane.showMessageDialog(null, posicion);
    }

    public static boolean cadenaIguales(String cadena1, String cadena2) {

        if (cadena1.equals(cadena2)) {
            return true;
        } else {
            return false;
        }

    }

    public static void aumentarPos(int[][] matriz, int i, int j, int cantidad) {
        matriz[i][j] += cantidad;
    }

}
