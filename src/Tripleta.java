import java.util.Random;

import javax.swing.JOptionPane;


public class Tripleta {
    //Atributos
    private int MatTripleta[][];
    
    //Metodos

    public Tripleta(int numeroTerminos) {
        MatTripleta = new int[numeroTerminos + 1][3];
    }

    public int[][] getMtri() {
        return MatTripleta;
    }

    public void setMtri(int[][] MatTripleta) {
        this.MatTripleta = MatTripleta;
    }
    
    public int getMtri(int k, int i) {
        return MatTripleta[k][i];
    }

    public void setMtri(int k, int i, int d) {
        MatTripleta[k][i] = d;
    }

    public void LlenarTripletaDesdeDispersa(int Dispersa[][], int numeroTerminos) {

        // una tripleta es:
        // |fila|columna|valor|
        // y su primera fila es:
        // |cantidad de filas|cantidad de columnas|cantidad de terminos|

        // por ende un dato estaria en:
        // 
        // fila: MatTripleta[posEnTripleta][0] = i
        // colm: MatTripleta[posEnTripleta][1] = j
        // dato: MatTripleta[posEnTripleta][2] = Dispersa[i][j]
        //
        
        MatTripleta[0][0] = Dispersa.length;
        MatTripleta[0][1] = Dispersa[0].length;
        MatTripleta[0][2] = numeroTerminos;

        // donde posEnTripleta hace ++ en cada iteracion, itera sobre la tripleta
        //      i itera sobre filas de la dispersa
        //      j itera socre colms de la dispersa

        for (int i = 0, posEnTripleta = 1; i < Dispersa.length && posEnTripleta < numeroTerminos; i++) {
            for (int j = 0; j < Dispersa[0].length; j++) {

                // si en la dispersa hay un valor "0", se considera como vacío.
                if (Dispersa[i][j] != 0) {
                    MatTripleta[posEnTripleta][0] = i; // guarda la fila del dato
                    MatTripleta[posEnTripleta][1] = j; // columna
                    MatTripleta[posEnTripleta][2] = Dispersa[i][j];
                    
                    posEnTripleta++;
                }
            }
        }

        
    }
    
    public String mostrarTripletaDentro() {

        String tripletaMostrada = "[ Fila, Columna, Dato ]\n";

        for (int i = 0; i < MatTripleta.length; i++) {

            tripletaMostrada += "[      "+MatTripleta[i][0]+",           "+MatTripleta[i][1]+",         "+MatTripleta[i][2]+"  ]\n";

            // queda por fila algo como:
            // [6,   1, 0]
            // [5, -10, 2]
        }

        return tripletaMostrada;
    }

    public void Mostrar() {
        JOptionPane.showMessageDialog(null, mostrarTripletaDentro());
    }

    public void SumarFilas() {
                                    // |v| se accede a la cantidad de filas de la tripleta
        int[] sumas = new int[MatTripleta[0][0]];

        // i itera sobre "sumas". cada posicion de "sumas" es la sumatoria de esa fila,
        //         por ende 'i' tambien lleva registro de en que fila se esta actualmente
        // j itera sobre cada fila REAL de la tripleta (no la fila ingresada)
        for (int i = 0, j = 1; i < sumas.length; i++) {

            for (; j < MatTripleta.length && MatTripleta[j][0] == i; j++) {
                sumas[i] += MatTripleta[j][2];
            }

        }

        // mostrado de la sumatoria
        /*3 2 0  
          1 1 4  
          1 1 0
         */
        String sumatoria = "[  ";
        for (int i = 0; i < sumas.length; i++) {
            
            if (i == sumas.length - 1) {
                sumatoria += sumas[i] + "  ]";
            } else {
                sumatoria += sumas[i] + ",  ";
            }
        }

        JOptionPane.showMessageDialog(null, sumatoria);
    }

    public void SumarColumnas() {
        
        // aca ya no es tan sencillo acceder a la mayor columna pues el ultimo numero
        // puede no ser la mayor cantidad de columnas.

        int[] sumas = new int[MatTripleta[0][1]];

        // i itera sobre "sumas". cada posicion de "sumas" es la sumatoria de esa columna,
        //         por ende 'i' tambien lleva registro de en que columna se esta actualmente
        // j itera sobre cada fila REAL de la tripleta (no la columna ni la fila ingresada)
        for (int i = 0, j = 1; i < sumas.length; i++) {


            for (; j < MatTripleta.length; j++) {
                if (MatTripleta[j][1] == i) {   // si el numero de columna actual concuerda con la columna que se está sumando
                    sumas[i] += MatTripleta[j][2];
                }
            }
                     // si ya se finalizó la búsqueda y suma de esa columna          
            j = 0;   // entonces se resetea el contador de la fila real
        }

        // mostrado de la sumatoria
        /*3 2 3  
          1 1 4  
          1 1 3
         */
        String sumatoria = "[  ";
        for (int i = 0; i < sumas.length; i++) {
            
            if (i == sumas.length - 1) {
                sumatoria += sumas[i] + "  ]";
            } else {
                sumatoria += sumas[i] + ",  ";
            }
        }

        JOptionPane.showMessageDialog(null, sumatoria);    }

    public void Insertar() {
        int posicion[] = new int[2];     // en [0] se guarda fila y en [1] columna
        int encontrado[] = new int[2];
        int datoInt = 0;

        // se valdia la entrada y luego se busca la posicion para insertar o sumar el dato
        do {
            encontrado[0] = 0;
            encontrado[1] = 0;
            do {
                String fila = JOptionPane.showInputDialog("Ingrese la fila del dato a insertar.");
                
                encontrado[0] = 0;
                /* VALORES PARA "encontrado[]":
                CASILLA 0
                    0 = dato aún no encontrado en tripleta
                    1 = dato encontrado en tripleta
                    2 = input vacío o incorrecto
                    3 = input con numeros no enteros
                CASILLA 1
                    posición del dato encontrado en tripleta 
                */
                if (fila == null || fila == "" || fila.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Se debe ingresar una fila a insertar.");
                    encontrado[0] = 2;
                } else if (!fila.matches("\\d+")) {
                                        /* ^ regex es una forma de escribir patrones para comprobar si un texto cumple cierta forma.
                                            acá se está preguntando:
                                            "\\d" = hay un digito del 0 al 9?
                                            "+" = lo hay 1 o mas veces? 
                                        */   
                    JOptionPane.showMessageDialog(null, "Ingrese solo números enteros positivos.");
                    encontrado[0] = 3;
                } else {
                    posicion[0] = Integer.parseInt(fila);
                }
            } while (encontrado[0] == 2 || encontrado[0] == 3);

            do {
                String colm = JOptionPane.showInputDialog("Ingrese la columna del dato a insertar.");
                
                encontrado[0] = 0;
                /* VALORES PARA "encontrado[]":
                CASILLA 0
                    0 = dato aún no encontrado en tripleta
                    1 = dato encontrado en tripleta
                    2 = input vacío o incorrecto
                    3 = input con numeros no enteros
                CASILLA 1
                    posición del dato encontrado en tripleta 
                */
                if (colm == null || colm == "" || colm.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Se debe ingresar una columna a insertar.");
                    encontrado[0] = 2;
                } else if (!colm.matches("\\d+")) {
                                        /* ^ regex es una forma de escribir patrones para comprobar si un texto cumple cierta forma.
                                            acá se está preguntando:
                                            "\\d" = hay un digito del 0 al 9?
                                            "+" = lo hay 1 o mas veces? 
                                        */   
                    JOptionPane.showMessageDialog(null, "Ingrese solo números enteros positivos.");
                    encontrado[0] = 3;
                } else {
                    posicion[1] = Integer.parseInt(colm);
                }
            } while (encontrado[0] == 2 || encontrado[0] == 3);

            do {
                String dato = JOptionPane.showInputDialog("Ingrese el dato entero a insertar.");
                
                encontrado[0] = 0;
                /* VALORES PARA "encontrado[]":
                CASILLA 0
                    0 = dato aún no encontrado en tripleta
                    1 = dato encontrado en tripleta
                    2 = input vacío o incorrecto
                    3 = input con numeros no enteros
                CASILLA 1
                    posición del dato encontrado en tripleta 
                */
                if (dato == null || dato == "" || dato.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Se debe ingresar un dato a insertar.");
                    encontrado[0] = 2;
                } else if (!dato.matches("-?\\d+")) {
                                        /* ^ regex es una forma de escribir patrones para comprobar si un texto cumple cierta forma.
                                            acá se está preguntando:
                                            "-?" = hay ngeativo 0 o 1 vez?
                                            "\\d" = hay un digito del 0 al 9?
                                            "+" = lo hay 1 o mas veces? 
                                         */   
                    JOptionPane.showMessageDialog(null, "Ingrese solo números enteros.");
                    encontrado[0] = 3;
                } else {
                    datoInt = Integer.parseInt(dato);

                    if (datoInt == 0) {
                        return; // como el 0 es un espacio vacío, funciona para regresar al menú
                    }
                }
            } while (encontrado[0] == 2 || encontrado[0] == 3);

            /* 
            =============== BUSQUEDA ===================
            */ 
            /*  se buscará la posicion en la tripleta. si existe, se sumará el nuevo
                dato al que ya estaba guardado en esa posicion.
            */
            encontrado[0] = 0; // se resetea la variable bandera

            for (int i = 1; i < MatTripleta.length && encontrado[0] == 0; i++) {
                if (MatTripleta[i][0] == posicion[0] && MatTripleta[i][1] == posicion[1]) {
                    encontrado[0] = 1;
                    encontrado[1] = i;
                }
            }

            /* 
            =============== INSERTADO ===================
            */ 
            if (encontrado[0] == 1) {
                MatTripleta[encontrado[1]][2] += datoInt;

                // si al sumar queda 0, entonces esa posicion se elimina de la tripleta
                if (MatTripleta[encontrado[1]][2] == 0) {
                    Tripleta nuevaTripleta = new Tripleta(MatTripleta.length - 2);
                                                        //   ^  -2 ya que en L13 se hace "MatTripleta = new int[numeroTerminos + 1][3];"
                                                        // por ende hay que restar:
                                                        // -1 para la linea que se quitó
                                                        // -1 para pasar de 0-based a 1-based
                    nuevaTripleta.MatTripleta[0][2] = MatTripleta[0][2] - 1; // se resta 1 dato para la nueva cantidad de datos
                    // i itera sobre MatTripleta
                    // j itera sobre nuevaTripleta 
                    for (int i = 1, j = 1; j < nuevaTripleta.MatTripleta.length; i++, j++) {
                                            // ^ se usa la clase tripleta recursivamente, entonces se llama
                                            // a la longitud de la MatTripleta (variable) que hay dentro de 
                                            // nuevaTripleta (objeto), ya que no se puede hacer ".length"
                                            // en objetos. 
                        if (encontrado[1] == i) {
                            j--;
                        } else {
                            nuevaTripleta.MatTripleta[j][0] = MatTripleta[i][0];
                            nuevaTripleta.MatTripleta[j][1] = MatTripleta[i][1];
                            nuevaTripleta.MatTripleta[j][2] = MatTripleta[i][2];
                        }

                        if (j == (nuevaTripleta.MatTripleta.length - 1)) {
                            setMtri(nuevaTripleta.MatTripleta); 
                            MatTripleta[0][1] = contarColmsTripleta(MatTripleta);
                            MatTripleta[0][0] = contarFilasTripleta(MatTripleta);
                        }
                    }
                } else {
                    if (MatTripleta[0][0] < posicion[0] + 1) {
                        MatTripleta[0][0] = posicion[0] + 1;
                    }

                    if (MatTripleta[0][1] < posicion[1] + 1) {
                        MatTripleta[0][1] = posicion[1] + 1;
                    }
                }
            } else {
                // variable bandera para saber si el nuevo dato ya fue guardado en la nueva tripleta
                int insertado = 0;

                // estas guardan las dimensiones actuales de la tripleta, ya que pueden aumentar si la nueva posicion se sale del rango
                // las creé para no perderme xd
                int filasActuales = MatTripleta[0][0];
                int colmsActuales = MatTripleta[0][1];

                Tripleta nuevaTripleta = new Tripleta(MatTripleta[0][2] + 1);
                nuevaTripleta.MatTripleta[0][2] = MatTripleta[0][2] + 1; // se suma 1 dato para la nueva cantidad de datos

                // i itera sobre MatTripleta
                // j itera sobre nuevaTripleta
                for (int i = 1, j = 1; i < MatTripleta.length; i++, j++) {

                    // si el nuevo dato debe ir antes del dato actual, se guarda primero
                    if (insertado == 0 && (posicion[0] < MatTripleta[i][0] || (posicion[0] == MatTripleta[i][0] && posicion[1] < MatTripleta[i][1]))) {
                        nuevaTripleta.MatTripleta[j][0] = posicion[0];
                        nuevaTripleta.MatTripleta[j][1] = posicion[1];
                        nuevaTripleta.MatTripleta[j][2] = datoInt;

                        insertado = 1;
                        j++;
                    }

                    nuevaTripleta.MatTripleta[j][0] = MatTripleta[i][0];
                    nuevaTripleta.MatTripleta[j][1] = MatTripleta[i][1];
                    nuevaTripleta.MatTripleta[j][2] = MatTripleta[i][2];
                }

                // si el dato aun no se ha insertado, entonces va al final de la tripleta
                if (insertado == 0) {
                    nuevaTripleta.MatTripleta[nuevaTripleta.MatTripleta.length - 1][0] = posicion[0];
                    nuevaTripleta.MatTripleta[nuevaTripleta.MatTripleta.length - 1][1] = posicion[1];
                    nuevaTripleta.MatTripleta[nuevaTripleta.MatTripleta.length - 1][2] = datoInt;
                }

                setMtri(nuevaTripleta.MatTripleta);

                if (filasActuales < posicion[0] + 1) {
                    MatTripleta[0][0] = posicion[0] + 1;
                } else {
                    MatTripleta[0][0] = filasActuales;
                }

                if (colmsActuales < posicion[1] + 1) {
                    MatTripleta[0][1] = posicion[1] + 1;
                } else {
                    MatTripleta[0][1] = colmsActuales;
                }
            }

            JOptionPane.showMessageDialog(null, mostrarTripletaDentro());
        } while (encontrado[0] == 2 || encontrado[0] == 3);
    }

    public void EliminarDato() {
        int encontrado[] = new int[2];
        
        do {
            String dato = JOptionPane.showInputDialog("Ingrese un dato entero a eliminar.\n"
                                                        + "Si hay 2 o más datos iguales, se eliminará el primero que se encuentre.\n\n"
                                                        + "Ingrese 0 para volver.");
            encontrado[0] = 0;
            /* VALORES PARA "encontrado[]":
            CASILLA 0
                0 = dato aún no encontrado en tripleta
                1 = dato encontrado en tripleta
                2 = input vacío o incorrecto
                3 = input con numeros no enteros
            CASILLA 1
                posición del dato encontrado en tripleta 
            */
            if (dato == null || dato == "" || dato.isBlank()) {
                JOptionPane.showMessageDialog(null, "Se debe ingresar un dato a eliminar.");
                encontrado[0] = 2;
            } else if (!dato.matches("-?\\d+")) {
                                    /* ^ regex es una forma de escribir patrones para comprobar si un texto cumple cierta forma.
                                        acá se está preguntando:
                                        "-?" = hay ngeativo 0 o 1 vez?
                                        "\\d" = hay un digito del 0 al 9?
                                        "+" = lo hay 1 o mas veces? 
                                     */   
                JOptionPane.showMessageDialog(null, "Ingrese solo números enteros.");
                encontrado[0] = 3;
            }

            int datoInt = 0;
            if (encontrado[0] == 0) {
                datoInt = Integer.parseInt(dato);

                if (datoInt == 0) {
                    return; // como el 0 es un espacio vacío, funciona para regresar al menú
                }
            }
            
            /* 
            =============== BUSQUEDA ===================
             */ 
            /*  se buscará el dato en la tripleta. si no existe, se anunca que no se puede
                eliminar un dato que no existe, y se repite el dowhile.
            */
            
            for (int i = 1; i < MatTripleta.length && encontrado[0] == 0; i++) {
                if (MatTripleta[i][2] == datoInt) {
                    encontrado[0] = 1;
                    encontrado[1] = i; // se guarda la posicion en la cual se encontro el dato en la tripleta
                }
            }

            if (encontrado[0] == 0) {
                JOptionPane.showMessageDialog(null, "No se encontró " + datoInt + " en la tripleta.");
                encontrado[0] = 2;
            }
            
            /* 
            =============== ELIMINADO ===================
            */ 

            // se crea una nueva tripleta con 1 fila menos
            Tripleta nuevaTripleta = new Tripleta(MatTripleta.length - 2);
                                                //   ^  -2 ya que en L13 se hace "MatTripleta = new int[numeroTerminos + 1][3];"
                                                // por ende hay que restar:
                                                // -1 para la linea que se quitó
                                                // -1 para pasar de 0-based a 1-based
            if (encontrado[0] == 1)                 /* hola :) */                {
                nuevaTripleta.MatTripleta[0][2] = MatTripleta[0][2] - 1; // se resta 1 dato para la nueva cantidad de datos
            }
            // i itera sobre MatTripleta
            // j itera sobre nuevaTripleta 
            for (int i = 1, j = 1; j < nuevaTripleta.MatTripleta.length && encontrado[0] == 1; i++, j++) {
                                    // ^ se usa la clase tripleta recursivamente, entonces se llama
                                    // a la longitud de la MatTripleta (variable) que hay dentro de 
                                  // nuevaTripleta (objeto), ya que no se puede hacer ".length"
                                    // en objetos. 
                if (encontrado[1] == i) {
                    j--;
                } else {
                    nuevaTripleta.MatTripleta[j][0] = MatTripleta[i][0];
                    nuevaTripleta.MatTripleta[j][1] = MatTripleta[i][1];
                    nuevaTripleta.MatTripleta[j][2] = MatTripleta[i][2];
                }

                if (j == (nuevaTripleta.MatTripleta.length - 1)) {
                    setMtri(nuevaTripleta.MatTripleta); 
                    MatTripleta[0][1] = contarColmsTripleta(MatTripleta);
                    MatTripleta[0][0] = contarFilasTripleta(MatTripleta);
                }
            }
            
            if (encontrado[0] == 1) {
                
                JOptionPane.showMessageDialog(null, mostrarTripletaDentro());
            }
            
        } while (encontrado[0] == 2 || encontrado[0] == 3);
        
    }

    public void EliminarPosicion() {
        int posicion[] = new int[2];     // en [0] se guarda fila y en [1] columna
        int encontrado[] = new int[2];

        do {
            encontrado[0] = 0;
            encontrado[1] = 0;
            do {
                String fila = JOptionPane.showInputDialog("Ingrese la fila del dato a eliminar.");
                
                encontrado[0] = 0;
                /* VALORES PARA "encontrado[]":
                CASILLA 0
                    O = dato aún no encontrado en tripleta
                    1 = dato encontrado en tripleta
                    2 = input vacío o incorrecto
                    3 = input con numeros no enteros
                CASILLA 1
                    posición del dato encontrado en tripleta 
                */
                if (fila == null || fila == "" || fila.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Se debe ingresar una fila a eliminar.");
                    encontrado[0] = 2;
                } else if (!fila.matches("\\d+")) {
                                        /* ^ regex es una forma de escribir patrones para comprobar si un texto cumple cierta forma.
                                            acá se está preguntando:
                                            "\\d" = hay un digito del 0 al 9?
                                            "+" = lo hay 1 o mas veces? 
                                        */   
                    JOptionPane.showMessageDialog(null, "Ingrese solo números enteros positivos.");
                    encontrado[0] = 3;
                } else {
                    posicion[0] = Integer.parseInt(fila);
                }
            } while (encontrado[0] == 2 || encontrado[0] == 3);
            
            do {
                String colm = JOptionPane.showInputDialog("Ingrese la columna del dato a eliminar.");
                
                encontrado[0] = 0;
                /* VALORES PARA "encontrado[]":
                CASILLA 0
                    O = dato aún no encontrado en tripleta
                    1 = dato encontrado en tripleta
                    2 = input vacío o incorrecto
                    3 = input con numeros no enteros
                CASILLA 1
                    posición del dato encontrado en tripleta 
                */
                if (colm == null || colm == "" || colm.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Se debe ingresar una columna a eliminar.");
                    encontrado[0] = 2;
                } else if (!colm.matches("\\d+")) {
                                        /* ^ regex es una forma de escribir patrones para comprobar si un texto cumple cierta forma.
                                            acá se está preguntando:
                                            "\\d" = hay un digito del 0 al 9?
                                            "+" = lo hay 1 o mas veces? 
                                        */   
                    JOptionPane.showMessageDialog(null, "Ingrese solo números enteros positivos.");
                    encontrado[0] = 3;
                } else {
                    posicion[1] = Integer.parseInt(colm);
                }
            } while (encontrado[0] == 2 || encontrado[0] == 3);

            
            /* 
            =============== BUSQUEDA ===================
                */ 
            /*  se buscará la posicion en la tripleta. si no existe, se anuncia que no se puede
                eliminar una posicion que no existe, y se repite el dowhile.
            */
            encontrado[0] = 0; // se resetea la variable bandera

            for (int i = 1; i < MatTripleta.length && encontrado[0] == 0; i++) {

                // se busca el numero de la mayor columna para la fila actual

                if (MatTripleta[i][0] == posicion[0] && MatTripleta[i][1] == posicion[1]) {
                        encontrado[0] = 1;
                        encontrado[1] = i;
                }
            }

            if (encontrado[0] == 0) {
                JOptionPane.showMessageDialog(null, "No se encontró la posicion ( "+posicion[0]+" , "+posicion[1]+" ) en la tripleta.");
                encontrado[0] = 2;
            }
            
            /* 
            =============== ELIMINADO ===================
            */ 

            // se crea una nueva tripleta con 1 fila menos
            if (encontrado[0] == 1) {
                Tripleta nuevaTripleta = new Tripleta(MatTripleta.length - 2);
                                                                //   ^  -2 ya que en L13 se hace "MatTripleta = new int[numeroTerminos + 1][3];"
                                                            // por ende hay que restar:
                                                            // -1 para la linea que se quitó
                                                            // -1 para pasar de 0-based a 1-based

                nuevaTripleta.MatTripleta[0][2] = MatTripleta[0][2] - 1; // se resta 1 dato para la nueva cantidad de datos
                // i itera sobre MatTripleta
                // j itera sobre nuevaTripleta 
                for (int i = 1, j = 1; j < nuevaTripleta.MatTripleta.length; i++, j++) {
                                        // ^ se usa la clase tripleta recursivamente, entonces se llama
                                        // a la longitud de la MatTripleta (variable) que hay dentro de 
                                        // nuevaTripleta (objeto), ya que no se puede hacer ".length"
                                        // en objetos. 
                    if (encontrado[1] == i) {
                        j--;
                    } else {
                        nuevaTripleta.MatTripleta[j][0] = MatTripleta[i][0];
                        nuevaTripleta.MatTripleta[j][1] = MatTripleta[i][1];
                        nuevaTripleta.MatTripleta[j][2] = MatTripleta[i][2];
                    }

                    if (j == (nuevaTripleta.MatTripleta.length - 1)) {
                    setMtri(nuevaTripleta.MatTripleta); 
                    MatTripleta[0][1] = contarColmsTripleta(MatTripleta);
                    MatTripleta[0][0] = contarFilasTripleta(MatTripleta);
                    }
                }

                if (encontrado[0] == 1) {
                    JOptionPane.showMessageDialog(null, mostrarTripletaDentro());
                }
            }
        } while (encontrado[0] == 2 || encontrado[0] == 3);
    }

    public void SumarConMatrizRandom() {

        int filas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de filas de la nueva matriz."));
        int colms = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de columnas de la nueva matriz."));
        // hay que contar terminos de la nueva matriz para poder crear un objeto tipo tripleta

        int Dispersa[][], numeroTerminos;
        Dispersa = nuevaMatrizDispersaAuto(filas, colms); // crear matriz random
        numeroTerminos = Matrices.ContarDatosDispersa(Dispersa);

        SumarMatriz(Dispersa, numeroTerminos);
    }

    public void SumarConMatrizManual() {

        int filas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de filas de la nueva matriz."));
        int colms = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de columnas de la nueva matriz."));
        // hay que contar terminos de la nueva matriz para poder crear un objeto tipo tripleta

        int Dispersa[][], numeroTerminos;
        Dispersa = nuevaMatrizDispersaManual(filas, colms); // crear matriz manual (que locha)
        numeroTerminos = Matrices.ContarDatosDispersa(Dispersa);
                        
        SumarMatriz(Dispersa, numeroTerminos);
    }

    public void SumarMatriz(int Dispersa[][], int numeroTerminos) {
        
        Tripleta nuevaTripleta = new Tripleta(numeroTerminos);
        nuevaTripleta.LlenarTripletaDesdeDispersa(Dispersa, numeroTerminos); 

        // este seria el maximo caso de numeros posibles para la tripleta final, en caso de que
        // ninguna posicion entre tripletas coincida
        int maxNumeroTerminos = nuevaTripleta.MatTripleta[0][2] + MatTripleta[0][2];
        Tripleta tripletaFinal = new Tripleta(maxNumeroTerminos);

        // i itera sobre MatTripleta
        // j itera sobre nuevaTripleta
        // posFinal itera sobre tripletaFinal
        for (int i = 1, j = 1, posFinal = 1; i < MatTripleta.length || j < nuevaTripleta.MatTripleta.length; posFinal++) {

            // si aún se está dentro de los limites de ambas tripletas
            if (i < MatTripleta.length && j < nuevaTripleta.MatTripleta.length) {


                // y la fila del dato actual es igual
                if (MatTripleta[i][0] == nuevaTripleta.MatTripleta[j][0]) {
                    // y la columna del dato actual es igual
                    if (MatTripleta[i][1] == nuevaTripleta.MatTripleta[j][1]) {

                        // se guarda la misma fila
                        tripletaFinal.MatTripleta[posFinal][0] = MatTripleta[i][0]; 
                        // misma columna
                        tripletaFinal.MatTripleta[posFinal][1] = MatTripleta[i][1];
                        // y suman los datos de la vieja y la nueva
                        tripletaFinal.MatTripleta[posFinal][2] = MatTripleta[i][2] + nuevaTripleta.MatTripleta[j][2];

                        // 
                        if (MatTripleta[i][2] + nuevaTripleta.MatTripleta[j][2] == 0) {
                            posFinal--;
                        }

                        // se incrementan los iteradores
                        i++;
                        j++;

                    // y la columna de MatTripleta es < a la de la nueva
                    } else if (MatTripleta[i][1] < nuevaTripleta.MatTripleta[j][1]) {
                        
                        tripletaFinal.MatTripleta[posFinal][0] = MatTripleta[i][0];
                        tripletaFinal.MatTripleta[posFinal][1] = MatTripleta[i][1];
                        tripletaFinal.MatTripleta[posFinal][2] = MatTripleta[i][2];
                        i++;
                    } else {
                        tripletaFinal.MatTripleta[posFinal][0] = nuevaTripleta.MatTripleta[j][0];
                        tripletaFinal.MatTripleta[posFinal][1] = nuevaTripleta.MatTripleta[j][1];
                        tripletaFinal.MatTripleta[posFinal][2] = nuevaTripleta.MatTripleta[j][2];
                        j++;
                    }
                    
                // sino, si la fila es distinta 
                } else if (MatTripleta[i][0] < nuevaTripleta.MatTripleta[j][0]) {
                    tripletaFinal.MatTripleta[posFinal][0] = MatTripleta[i][0];
                    tripletaFinal.MatTripleta[posFinal][1] = MatTripleta[i][1];
                    tripletaFinal.MatTripleta[posFinal][2] = MatTripleta[i][2];
                    i++;
                } else {
                    tripletaFinal.MatTripleta[posFinal][0] = nuevaTripleta.MatTripleta[j][0];
                    tripletaFinal.MatTripleta[posFinal][1] = nuevaTripleta.MatTripleta[j][1];
                    tripletaFinal.MatTripleta[posFinal][2] = nuevaTripleta.MatTripleta[j][2];
                    j++;
                }


            // sino, si se esta dentro del limite de la nueva pero no de mattripleta
            } else if (i >= MatTripleta.length && j < nuevaTripleta.MatTripleta.length) {
                // si a MatTripleta ya no le quedan datos, se siguen guardando los de la nueva
                tripletaFinal.MatTripleta[posFinal][0] = nuevaTripleta.MatTripleta[j][0];
                tripletaFinal.MatTripleta[posFinal][1] = nuevaTripleta.MatTripleta[j][1];
                tripletaFinal.MatTripleta[posFinal][2] = nuevaTripleta.MatTripleta[j][2];

                j++;
            // sino, si se esta dentro del limite de mattripleta pero no de la nueva
            } else if (i < MatTripleta.length && j >= nuevaTripleta.MatTripleta.length) {
                // si a la nueva ya no le quedan datos, se siguen guardando los de la MatTripleta
                tripletaFinal.MatTripleta[posFinal][0] = MatTripleta[i][0];
                tripletaFinal.MatTripleta[posFinal][1] = MatTripleta[i][1];
                tripletaFinal.MatTripleta[posFinal][2] = MatTripleta[i][2];

                i++;
            }
            
        }

        // cantidad de filas
        tripletaFinal.MatTripleta[0][0] = contarFilasTripleta(tripletaFinal.MatTripleta);
        // cantidad de columnas
        tripletaFinal.MatTripleta[0][1] = contarColmsTripleta(tripletaFinal.MatTripleta);
        // cantidad de datos
        tripletaFinal.MatTripleta[0][2] = contarDatosTripleta(tripletaFinal.MatTripleta);

        // esta se hace por si la tripleta final quedo con espacios en cero
        Tripleta tripletaAjustada = new Tripleta(tripletaFinal.MatTripleta[0][2]);

        for (int i = 0; i <= tripletaFinal.MatTripleta[0][2]; i++) {
            // se guardan los datos de la tripleta final en la tripleta ajustada
            tripletaAjustada.setMtri(i, 0, tripletaFinal.getMtri(i, 0));
            tripletaAjustada.setMtri(i, 1, tripletaFinal.getMtri(i, 1));
            tripletaAjustada.setMtri(i, 2, tripletaFinal.getMtri(i, 2));
        }
        
        JOptionPane.showMessageDialog(null, "Tripleta inicial:\n" + mostrarTripletaDentro() +
                                                            "Tripleta nueva:\n" + nuevaTripleta.mostrarTripletaDentro() +
                                                            "Tripleta resultante:\n" + tripletaAjustada.mostrarTripletaDentro());
        
    }

    public void MultiplicarConMatrizRandom() {

        int filas = MatTripleta[0][1];
        int colms = Integer.parseInt(JOptionPane.showInputDialog("Para poder multiplicar 2 matrices, se asumen "+MatTripleta[0][1]+" filas para la nueva matriz.\nIngrese el número de columnas de la nueva matriz."));

        int Dispersa[][], numeroTerminos;
        Dispersa = nuevaMatrizDispersaAuto(filas, colms);
        numeroTerminos = Matrices.ContarDatosDispersa(Dispersa);

        MultiplicarMatriz(Dispersa, numeroTerminos);
    }

    public void MultiplicarConMatrizManual() {

        int filas = MatTripleta[0][1];
        int colms = Integer.parseInt(JOptionPane.showInputDialog("Para poder multiplicar 2 matrices, se asumen "+MatTripleta[0][1]+" filas para la nueva matriz.\nIngrese el número de columnas de la nueva matriz."));

        int Dispersa[][], numeroTerminos;
        Dispersa = nuevaMatrizDispersaManual(filas, colms);
        numeroTerminos = Matrices.ContarDatosDispersa(Dispersa);

        MultiplicarMatriz(Dispersa, numeroTerminos);
    }

    public void MultiplicarMatriz(int Dispersa[][], int numeroTerminos) {

        Tripleta nuevaTripleta = new Tripleta(numeroTerminos);
        nuevaTripleta.LlenarTripletaDesdeDispersa(Dispersa, numeroTerminos); 

        if (MatTripleta[0][1] != nuevaTripleta.MatTripleta[0][0]) {
            JOptionPane.showMessageDialog(null, "No se pueden multiplicar las matrices.\n" +
                                                "La cantidad de columnas de la 1ra debe ser igual a la cantidad de filas de la 2da.");
            return;
        }

        Tripleta tripletaFinal = new Tripleta(MatTripleta[0][0] * nuevaTripleta.MatTripleta[0][1]); // la primera fila de la tripleta final ya la crea el constructor

        /*para que una matriz se multiplique, sus dimensiones deben ser:
          primera matriz: m x n
          segunda matriz: n x p
              resultante: m x p
              
          y la primera y segunda deben cumplir con n = n (colms 1ra = filas 2da)*/   
        tripletaFinal.MatTripleta[0][0] = MatTripleta[0][0];                                    // filas de 1ra
        tripletaFinal.MatTripleta[0][1] = nuevaTripleta.MatTripleta[0][1];                      // colms de 2da
        tripletaFinal.MatTripleta[0][2] = MatTripleta[0][0] * nuevaTripleta.MatTripleta[0][1];  // cantidad POSIBLE de datos (no cantidad de datos)
        // ^ hay que tener en cuenta que esto ========NO======= es para la tripleta ajustada (que es la verdadera tripleta final),
        // se hace solo con fines de iterar en la tripletafinal en funcion de sus dimensiones.
        //          * después se guardará las dimensiones y cnatidad de datos que tenga la tripleta ajustada.

        int filas = tripletaFinal.MatTripleta[0][0]; 
        int colms = tripletaFinal.MatTripleta[0][1];
        // ^ tuve que crear estas variables para mayor claridad sobre los numeros, ya que me estaba empezando a perder
        //   ademas estas son las que van a limitar el recorrido REAL de la matriz resultante

        // i itera sobre MatTripleta
        // j itera sobre nuevaTripleta

        // k itera sobre n (columna de MatTripleta y fila de nuevaTripleta)
        // x itera sobre filas posibles de tripletafinal
        // y itera sobre colms posibles de tripletafinal

        // posFinal itera sobre cada fila REAL de tripletaFinal
        int posFinal = 1;
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < colms; y++) {
                

                int producto = 0, acumulado = 0;

                           // o k < nuevaTripleta.MatTripleta[0][0]
                for (int k = 0; k < MatTripleta[0][1]; k++) {

                    // busqueda de la fila dentro de MatTripleta
                    for (int i = 1; i < MatTripleta.length; i++) {

                        // "(x,k) existe?"
                        if (MatTripleta[i][0] == x && MatTripleta[i][1] == k) {
                            
                            for (int j = 1; j < nuevaTripleta.MatTripleta.length; j++) {

                                //"y (k,y) existe?"
                                if (nuevaTripleta.MatTripleta[j][0] == k && nuevaTripleta.MatTripleta[j][1] == y) {

                                    // entonces se puede hacer (x,k) * (k,y)
                                    producto = MatTripleta[i][2] * nuevaTripleta.MatTripleta[j][2];
                                    acumulado += producto;

                                    j = nuevaTripleta.MatTripleta.length;
                                } else {
                                    // si cualquier dato que haya se multiplica por cero, da 0, no se añade nada al acumulado
                                }
                            }

                            i = MatTripleta.length;
                        } else {
                            // si cualquier dato que haya se multiplica por cero, da 0, no se añade nada al acumulado
                        }
                    }
                }

                // generar la posicion SOLO si el dato es != 0
                if (acumulado != 0) {
        
                    tripletaFinal.MatTripleta[posFinal][0] = x;
                    tripletaFinal.MatTripleta[posFinal][1] = y;
                    tripletaFinal.MatTripleta[posFinal][2] = acumulado;

                    posFinal++;
                }
            }
        }

        
        // cantidad de filas
        tripletaFinal.MatTripleta[0][0] = contarFilasTripleta(tripletaFinal.MatTripleta);
        // cantidad de columnas
        tripletaFinal.MatTripleta[0][1] = contarColmsTripleta(tripletaFinal.MatTripleta);
        // cantidad de datos
        tripletaFinal.MatTripleta[0][2] = contarDatosTripleta(tripletaFinal.MatTripleta);

        // esta se hace por si la tripleta final quedo con espacios en cero
        Tripleta tripletaAjustada = new Tripleta(tripletaFinal.MatTripleta[0][2]);

        for (int i = 0; i <= tripletaFinal.MatTripleta[0][2]; i++) {
            // se guardan los datos de la tripleta final en la tripleta ajustada
            tripletaAjustada.setMtri(i, 0, tripletaFinal.getMtri(i, 0));
            tripletaAjustada.setMtri(i, 1, tripletaFinal.getMtri(i, 1));
            tripletaAjustada.setMtri(i, 2, tripletaFinal.getMtri(i, 2));
        }
        
        JOptionPane.showMessageDialog(null, "Tripleta inicial:\n" + mostrarTripletaDentro() +
                                                            "\nTripleta nueva:\n" + nuevaTripleta.mostrarTripletaDentro() +
                                                            "\nTripleta resultante:\n" + tripletaAjustada.mostrarTripletaDentro());
    }
    
    public int[][] nuevaMatrizDispersaManual(int filas, int colms) {

        int matriz[][] = new int[filas][colms];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < colms; j++) {

                int ban, dato = 0;
                String entrada; // ingresamos un dato en modo de string
                do {
                    ban = 0;
                    entrada = JOptionPane.showInputDialog
                            ("Matriz actual:\n" + Matrices.mostrarMatrizDispersa(matriz) 
                                
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
                            k = entrada.length();   // se rompe el ciclo
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
        return matriz;
    }

    public int[][] nuevaMatrizDispersaAuto(int filas, int colms) {
        
        int matriz[][] = new int[filas][colms];

        Random random = new Random(); // se crea un objeto de la clase Random

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < colms; j++) {
                matriz[i][j] = random.nextInt(11);
            }
        }

        // si la matriz se generó aleatoriamente, se muestra
        
        JOptionPane.showMessageDialog(null, "Matriz generada:\n" + Matrices.mostrarMatrizDispersa(matriz));

        return matriz;
    }

    public int contarFilasTripleta(int[][] tripleta) {
        int cantFilas = 0;

        for (int i = 1; i < tripleta.length; i++) {
            if (cantFilas < tripleta[i][0]) {
                cantFilas = tripleta[i][0];
            }
        }

        return cantFilas + 1;
    }

    public int contarColmsTripleta(int[][] tripleta) {
        int cantColms = 0;

        for (int i = 1; i < tripleta.length; i++) {
            if (cantColms < tripleta[i][1]) {
                cantColms = tripleta[i][1];
            }
        }

        return cantColms + 1;
    }

    public int contarDatosTripleta(int[][] tripleta) {
        int cantDatos = 0;

        for (int i = 1; i < tripleta.length; i++) {
            if (tripleta[i][2] != 0) {
                cantDatos++;
            }
        }

        return cantDatos;
    }
        
}
