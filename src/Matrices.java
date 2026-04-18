import java.util.Random;

import javax.swing.JOptionPane;

/*
    Proyecto Matrices Dispersas

    @author: Thomas Madrigal Morales
 */

public class Matrices {
    public static void main(String[] args) throws Exception {
        
        // "A" es la tripleta

        int filas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de filas:"));
        int columnas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de columnas:"));
        
        int Dispersa[][] = LlenarDispersa(filas, columnas);
        int numeroTerminos = ContarDatosDispersa(Dispersa);
        // ingreso de los datos en la matriz dispersa
        

        // creacion de la tripleta
        Tripleta T = new Tripleta(numeroTerminos);
        T.LlenarTripletaDesdeDispersa(Dispersa, numeroTerminos);

        // creacion de la forma 2
        Forma2 F2 = new Forma2();
        F2.LlenarDesdeDispersa(Dispersa);


        int opcForma = 0; // variable para el menu principal
        int opc = 0; // variable para el menu de cada forma
        int opcSumar = 0; // variable para la opcion de sumar 2 matrices
        do {
            opcForma = MenuPrincipal();
            switch (opcForma) {

                case 2:
                    do {
                        opc = MenuForma2();
                        switch (opc) {
                            case 2:
                                F2.Mostrar();
                                break;
                            case 3:
                                F2.SumarFilas();
                                break;
                            case 4:
                                F2.SumarColumnas();
                                break;
                            case 5:
                                F2.Insertar();
                                break;
                            case 6:
                                F2.EliminarDato();
                                break;
                            case 7:
                                F2.EliminarPosicion();
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Opcion incorrecta");
                        }
                    } while (opc != 0);
                    break;
                case 3:
                    do {
                        opc = Menu();
                        switch (opc) {
                            case 2:
                                T.Mostrar();
                                break;
                            case 3:
                                T.SumarFilas();
                                break;
                            case 4:
                                T.SumarColumnas();
                                break;
                            case 5:
                                T.Insertar();
                                break;
                            case 6:
                                T.EliminarDato();
                                break;
                            case 7:
                                T.EliminarPosicion();
                                break;
                            case 8:
                                do {
                                    opcSumar = Integer.parseInt(JOptionPane.showInputDialog("Que desea hacer?\n" +
                                                            "1. Sumar con una matriz autogenerada\n" +
                                                            "2. Sumar con una matriz ingresada manualmente\n\n" +
                                                            "0. Regresar al menú anterior\n" +
                                                            "Ingrese una opción: "));
                                    switch (opcSumar) {
                                    case 1:
                                        T.SumarConMatrizRandom();
                                        break;
                                    case 2:
                                        T.SumarConMatrizManual();
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        System.out.println("Opcion incorrecta");
                                    }
                                } while (opcSumar != 0);
                                break;
                            case 9:
                                do {
                                    opcSumar = Integer.parseInt(JOptionPane.showInputDialog("Que desea hacer?\n" +
                                                            "1. Multiplicar con una matriz autogenerada\n" +
                                                            "2. Multiplicar con una matriz ingresada manualmente\n\n" +
                                                            "0. Regresar al menú anterior\n" +
                                                            "Ingrese una opción: "));
                                    switch (opcSumar) {
                                    case 1:
                                        T.MultiplicarConMatrizRandom();
                                        break;
                                    case 2:
                                        T.MultiplicarConMatrizManual();
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        System.out.println("Opcion incorrecta");
                                    }
                                } while (opcSumar != 0);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Opcion incorrecta");
                        }
                    } while (opc != 0);
                    break;
                case 0:
                    System.out.println("Programa finalizado...");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        } while (opcForma != 0);
    }

    public static int[][] LlenarDispersa(int filas, int columnas) {

        // creamos una nueva matriz con la cual llenar la dispersa que esta en el main
        int matriz[][] = new int[filas][columnas];

        int aleatorio = JOptionPane.showConfirmDialog(null, "¿Desea llenar automáticamente la matriz con datos aleatorios del 0 al 10?\n\n" +
                                                        "Si no, tendrá que hacerlo de forma manual.", 
                                                        "Atención", JOptionPane.YES_NO_OPTION);
        
        for (int i = 0; i < filas && aleatorio == JOptionPane.NO_OPTION; i++) {
            for (int j = 0; j < columnas; j++) {
                

                int ban, dato = 0;
                String entrada; // ingresamos un dato en modo de string
                do {
                    ban = 0;
                    entrada = JOptionPane.showInputDialog
                            ("Matriz actual:\n" + mostrarMatrizDispersa(matriz) 
                                
                            + "\n\nIngrese un dato para la posición ( "+i+" , "+j+" ):");
                    
                    // ciclo de verificacion para ver que entrada de verdad sea un entero
                    int contieneLetras = 0;
                    for (int k = 0; k < entrada.length(); k++) {
                    
                        if (entrada.charAt(0) == '-') {
                            k++;
                        }
                        if (!Character.isDigit(entrada.charAt(k))) {
                            contieneLetras = 1;
                            ban = 1;
                            k = entrada.length();
                        }
                    }
                    
                    // verificación de la entrada del usuario
                    if (entrada == null || entrada == "" || entrada.isBlank()) { // si el usuario no ingresó datos
                        ban = 0;
                    } else if (contieneLetras == 1) {   // si la entrada no es del todo un número
                        JOptionPane.showMessageDialog(null, "Debe ingresar un numero entero para la posición ( "+i+" , "+j+" ).");
                    } else if (Integer.parseInt(entrada) == 0) {    // si solo se ingresó un 0, se hace claridad en que no se cuenta en la tripleta
                        int opcion = JOptionPane.showConfirmDialog(null, "Si ingresa 0 como un dato, este no será tomado en la tripleta." +
                                                                            "\n\nPresione 'sí' para dejar vacío ese dato, o 'no' para corregirlo.",
                                                                             "Atención", 
                                                                             JOptionPane.YES_NO_OPTION);
                        if (opcion == JOptionPane.NO_OPTION) {
                            ban = 1;    // si el usuario ingresó un 0, y presionó que lo va a 
                        }            // corregir, entonces se activa este cosito que repite el ciclo
                    } else {
                            dato = Integer.parseInt(entrada);
                    }
                               
                } while (ban == 1);

                // el dato se guarda en la matriz dispersa
                matriz[i][j] = dato;
            }
        }

        // si se va a llenar de forma aleatoria
        
        Random random = new Random(); // se crea un objeto de la clase Random

        for (int i = 0; i < filas && aleatorio == JOptionPane.YES_OPTION; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = random.nextInt(11);
            }
        }

        // si la matriz se generó aleatoriamente, se muestra
        if (aleatorio == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Matriz generada:\n" + mostrarMatrizDispersa(matriz));
        }
        return matriz;
    }
    
    public static int MenuPrincipal() {

        return Integer.parseInt(JOptionPane.showInputDialog(
                "\n ===== Menu Principal :) ====="
                + "\n2. Forma 2"
                + "\n3. Tripleta"
                + "\n0. Salir"
                + "\nSeleccione una opcion"));
    }

    public static int MenuForma2() {
        int opc = Integer.parseInt(JOptionPane.showInputDialog("*** Menú Forma 2 =) ***\n" +
                                    "2. Mostrar forma\n" +
                                    "3. Suma de filas\n" +
                                    "4. Suma de columnas\n" +
                                    "5. Insertar dato\n" +
                                    "6. Eliminar dato específico\n" +
                                    "7. Eliminar por posición\n\n" +
                                    "0. Regresar al menú anterior\n" +
                                    "Ingrese una opción: "));
        return opc;
    }

    public static int Menu() {
        int opc = Integer.parseInt(JOptionPane.showInputDialog("*** Menú =) ***\n" +
                                    "2. Mostrar forma\n" +
                                    "3. Suma de filas\n" +
                                    "4. Suma de columnas\n" +
                                    "5. Insertar dato\n" +
                                    "6. Eliminar dato específico\n" +
                                    "7. Eliminar por posición\n\n" +
                                    "8. Sumar con otra matriz\n" +
                                    "9. Multiplicar con otra matriz\n" +
                                    "0. Regresar al menú anterior\n" +
                                    "Ingrese una opción: "));
        return opc;
    }
    public static int ContarDatosDispersa(int Mat[][]) {
        int N = 0;
        
        // se cuenta la cantidad de datos que hay dentro de la matriz dispersa
        for (int i = 0; i < Mat.length; i++) {
            for (int j = 0; j < Mat[0].length; j++) {

                /*como todas las columnas tienen el mismo tamaño, y una matriz
                 es un arreglo de arreglos, Mat[0].length obtiene el tamaño 
                 (osea la cantidad de datos) del "arreglo" en la posición 0.
                 
                 con obtener el tamaño del primer "arreglo" es suficiente para saber
                 la cantidad de columnas de la matriz entera, ya que los demás 
                 arreglos del arreglo tienen el mismo tamaño que la primera
                */

                // si en una casilla hay 0, significa que no hay un dato
                if (Mat[i][j] != 0) {
                    N++;
                }
            }
        }
        
        return N;
    }
    
    public static String mostrarMatrizDispersa(int[][] Dispersa) {
                
        String matrizMostrada = "";

        for (int i = 0; i < Dispersa.length; i++) {

            matrizMostrada += "[";
            for (int j = 0; j < Dispersa[0].length; j++) {
                
                if (j == Dispersa[0].length - 1) {
                    matrizMostrada += Dispersa[i][j];
                } else {
                    matrizMostrada += Dispersa[i][j] + ",  ";
                }
            }
            matrizMostrada += "]\n";

            // queda por fila algo como:
            // [6,   1, 0, 3]
            // [5, -10, 2, 4]
        }

        // mostramos en consola para debug
        System.out.println("mostrarMatrizDispersa: \n" + matrizMostrada);
        return matrizMostrada;
    }
}
