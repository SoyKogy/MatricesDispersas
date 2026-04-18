import javax.swing.JOptionPane;

public class Forma2 {
    // "Punta" es el nodo cabeza de la forma 2
    private NodoForma2 Punta;

    public Forma2() {
        this.Punta = null;    
    }    

    public NodoForma2 getPunta() {
        return Punta;
    }

    public void setPunta(NodoForma2 Punta) {
        this.Punta = Punta;
    }


    public void LlenarDesdeDispersa(int Mat[][]) {
        // esta matriz auxiliar guarda referencia a cada nodo creado para luego armar la liga por columnas
        NodoForma2 nodos[][] = new NodoForma2[Mat.length][Mat[0].length];

        // se llena por pasos para separar la creacion de "Punta" y las 2 ligas principales
        Paso1(Mat.length, Mat[0].length);
        Paso2(Mat, nodos);
        Paso3(nodos);
    }

    private void Paso1(int n, int m) {
        // en forma 2, "Punta" guarda las dimensiones y sirve de inicio para ambas listas
        Punta = new NodoForma2(n, m, 0);

        // si la matriz esta vacia, ambas ligas se quedan apuntando al nodo cabeza
        Punta.setLigaFila(Punta);
        Punta.setLigaColm(Punta);
    }

    private void Paso2(int[][] Mat, NodoForma2[][] nodos) {
        NodoForma2 q = Punta, x;

        // aca se crean todos los nodos y se encadenan en orden por filas
        // la idea es que "q" siempre quede parado en el ultimo nodo enlazado
        for (int i = 0; i < Mat.length; i++) {
            for (int j = 0; j < Mat[0].length; j++) {
                if (Mat[i][j] != 0) {
                    x = new NodoForma2(i, j, Mat[i][j]);
                    nodos[i][j] = x;
                    q.setLigaFila(x);
                    q = x;
                }
            }
        }

        // cuando ya no hay mas nodos por filas, la lista vuelve a "Punta"
        q.setLigaFila(Punta);
    }

    private void Paso3(NodoForma2[][] nodos) {
        NodoForma2 q = Punta;

        // usando los mismos nodos ya creados, se arma el recorrido por columnas
        // esto evita volver a crear nodos y asegura que un mismo dato pertenezca a las 2 listas
        for (int j = 0; j < nodos[0].length; j++) {
            for (int i = 0; i < nodos.length; i++) {
                if (nodos[i][j] != null) {
                    q.setLigaColm(nodos[i][j]);
                    q = nodos[i][j];
                }
            }
        }

        // al terminar el recorrido por columnas, tambien se vuelve a "Punta"
        q.setLigaColm(Punta);
    }

    public String mostrarForma2Dentro() {

        if (Punta == null) {
            return "La forma 2 aun no ha sido creada.";
        }

        String formaMostrada = "Punta = [ "+Punta.getFila()+", "+Punta.getCol()+", "+Punta.getDato()+" ]\n";

        formaMostrada += "\nRecorrido por filas:\n";
        formaMostrada += "[ Fila, Columna, Dato ]\n";

        NodoForma2 p = Punta.getLigaFila();

        // si "Punta" se apunta a si misma, significa que no hay datos guardados
        if (p == Punta) {
            formaMostrada += "[  vacia  ]\n";
        }

        while (p != Punta) {

            formaMostrada += "[      "+p.getFila()+",           "+p.getCol()+",         "+p.getDato()+"  ]\n";
            p = p.getLigaFila();

            // queda por fila algo como:
            // [6,   1, 0]
            // [5, -10, 2]

            /*================IMPORTANTE===================
            hice el mostrado como una tripleta ya que:
            1. como hay 2 formas de mostrar la forma2 (por fila y por columna), me parece más compacto así
                ya que JOptionPane no veo que permita otras formas de impresión
            2. no sabía cómo más hacerlo
            3. me daba pereza comerme el coco reinventando la rueda solo para una impresión
            */ 
            
        }

        formaMostrada += "\nRecorrido por columnas:\n";
        formaMostrada += "[ Fila, Columna, Dato ]\n";

        p = Punta.getLigaColm();

        if (p == Punta) {
            formaMostrada += "[  vacia  ]\n";
        }

        // se vuelve a recorrer la misma forma, pero ahora siguiendo la liga de columnas
        while (p != Punta) {

            formaMostrada += "[      "+p.getFila()+",           "+p.getCol()+",         "+p.getDato()+"  ]\n";
            p = p.getLigaColm();
        }

        return formaMostrada;
    }

    public void Mostrar() {
        JOptionPane.showMessageDialog(null, mostrarForma2Dentro());
    }

    public void SumarFilas() {
        if (Punta == null) {
            JOptionPane.showMessageDialog(null, "La forma 2 aun no ha sido creada.");
            return;
        }

                                    // |v| se accede a la cantidad de filas de la forma 2
        int[] sumas = new int[Punta.getFila()];
        NodoForma2 p = Punta.getLigaFila();

        // se recorre la forma usando la liga por filas, y cada dato se acumula en su fila correspondiente
        while (p != Punta) {
            sumas[p.getFila()] += p.getDato();
            p = p.getLigaFila();
        }

        JOptionPane.showMessageDialog(null, mostrarSumas(sumas, "Suma de filas:"));
    }

    public void SumarColumnas() {
        if (Punta == null) {
            JOptionPane.showMessageDialog(null, "La forma 2 aun no ha sido creada.");
            return;
        }

        int[] sumas = new int[Punta.getCol()];
        NodoForma2 p = Punta.getLigaColm();

        // se recorre la forma usando la liga por columnas, y cada dato se acumula en su columna correspondiente
        while (p != Punta) {
            sumas[p.getCol()] += p.getDato();
            p = p.getLigaColm();
        }

        JOptionPane.showMessageDialog(null, mostrarSumas(sumas, "Suma de columnas:"));
    }

    public void Insertar() {
        if (Punta == null) {
            JOptionPane.showMessageDialog(null, "La forma 2 aun no ha sido creada.");
            return;
        }

        int posicion[] = new int[2];     // en [0] se guarda fila y en [1] columna
        int encontrado;
        int datoInt = 0;
        NodoForma2 x = null;

        // se valdia la entrada y luego se busca la posicion para insertar o sumar el dato
        do {
            encontrado = 0;
            do {
                String fila = JOptionPane.showInputDialog("Ingrese la fila del dato a insertar.");
                
                encontrado = 0;
                /* VALORES PARA "encontrado":
                    0 = dato aún no encontrado en forma 2
                    1 = dato encontrado en forma 2
                    2 = input vacío o incorrecto
                    3 = input con numeros no enteros
                */
                if (fila == null || fila == "" || fila.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Se debe ingresar una fila a insertar.");
                    encontrado = 2;
                } else if (!fila.matches("\\d+")) {
                                        /* ^ regex es una forma de escribir patrones para comprobar si un texto cumple cierta forma.
                                            acá se está preguntando:
                                            "\\d" = hay un digito del 0 al 9?
                                            "+" = lo hay 1 o mas veces? 
                                        */   
                    JOptionPane.showMessageDialog(null, "Ingrese solo números enteros positivos.");
                    encontrado = 3;
                } else {
                    posicion[0] = Integer.parseInt(fila);
                }
            } while (encontrado == 2 || encontrado == 3);

            do {
                String colm = JOptionPane.showInputDialog("Ingrese la columna del dato a insertar.");
                
                encontrado = 0;
                /* VALORES PARA "encontrado":
                    0 = dato aún no encontrado en forma 2
                    1 = dato encontrado en forma 2
                    2 = input vacío o incorrecto
                    3 = input con numeros no enteros
                */
                if (colm == null || colm == "" || colm.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Se debe ingresar una columna a insertar.");
                    encontrado = 2;
                } else if (!colm.matches("\\d+")) {
                                        /* ^ regex es una forma de escribir patrones para comprobar si un texto cumple cierta forma.
                                            acá se está preguntando:
                                            "\\d" = hay un digito del 0 al 9?
                                            "+" = lo hay 1 o mas veces? 
                                        */   
                    JOptionPane.showMessageDialog(null, "Ingrese solo números enteros positivos.");
                    encontrado = 3;
                } else {
                    posicion[1] = Integer.parseInt(colm);
                }
            } while (encontrado == 2 || encontrado == 3);

            do {
                String dato = JOptionPane.showInputDialog("Ingrese el dato entero a insertar.");
                
                encontrado = 0;
                /* VALORES PARA "encontrado":
                    0 = dato aún no encontrado en forma 2
                    1 = dato encontrado en forma 2
                    2 = input vacío o incorrecto
                    3 = input con numeros no enteros
                */
                if (dato == null || dato == "" || dato.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Se debe ingresar un dato a insertar.");
                    encontrado = 2;
                } else if (!dato.matches("-?\\d+")) {
                                        /* ^ regex es una forma de escribir patrones para comprobar si un texto cumple cierta forma.
                                            acá se está preguntando:
                                            "-?" = hay ngeativo 0 o 1 vez?
                                            "\\d" = hay un digito del 0 al 9?
                                            "+" = lo hay 1 o mas veces? 
                                         */   
                    JOptionPane.showMessageDialog(null, "Ingrese solo números enteros.");
                    encontrado = 3;
                } else {
                    datoInt = Integer.parseInt(dato);

                    if (datoInt == 0) {
                        return; // como el 0 es un espacio vacío, funciona para regresar al menú
                    }
                }
            } while (encontrado == 2 || encontrado == 3);

            /* 
            =============== BUSQUEDA ===================
            */ 
            /*  se buscará la posicion en la forma 2. si existe, se sumará el nuevo
                dato al que ya estaba guardado en esa posicion.
            */
            encontrado = 0; // se resetea la variable bandera
            x = BuscarNodo(posicion[0], posicion[1]);

            if (x != null) {
                encontrado = 1;
            }

            /* 
            =============== INSERTADO ===================
            */ 
            if (encontrado == 1) {
                x.setDato(x.getDato() + datoInt);

                // si al sumar queda 0, entonces esa posicion se elimina de la forma 2
                if (x.getDato() == 0) {
                    EliminarNodo(x);
                }
            } else {   // sino, si la posicion no existía, se crea el nodo y se enlaza en la forma 2
                x = new NodoForma2(posicion[0], posicion[1], datoInt);
                InsertarNodo(x);

                if (Punta.getFila() < posicion[0] + 1) { // si la nueva fila se sale del rango actual, "Punta" debe actualizar su cantidad de filas
                    Punta.setFila(posicion[0] + 1);
                }

                if (Punta.getCol() < posicion[1] + 1) { // si la nueva columna se sale del rango actual, "Punta" debe actualizar su cantidad de columnas
                    Punta.setCol(posicion[1] + 1);
                }
            }

            JOptionPane.showMessageDialog(null, mostrarForma2Dentro());
        } while (encontrado == 2 || encontrado == 3);
    }

    public void EliminarDato() {
        if (Punta == null) {
            JOptionPane.showMessageDialog(null, "La forma 2 aun no ha sido creada.");
            return;
        }

        int encontrado;
        NodoForma2 x = null;
        
        do {
            String dato = JOptionPane.showInputDialog("Ingrese un dato entero a eliminar.\n"
                                                        + "Si hay 2 o más datos iguales, se eliminará el primero que se encuentre.\n\n"
                                                        + "Ingrese 0 para volver.");
            encontrado = 0;
            /* VALORES PARA "encontrado":
                0 = dato aún no encontrado en forma 2
                1 = dato encontrado en forma 2
                2 = input vacío o incorrecto
                3 = input con numeros no enteros
            */
            if (dato == null || dato == "" || dato.isBlank()) {
                JOptionPane.showMessageDialog(null, "Se debe ingresar un dato a eliminar.");
                encontrado = 2;
            } else if (!dato.matches("-?\\d+")) {
                                    /* ^ regex es una forma de escribir patrones para comprobar si un texto cumple cierta forma.
                                        acá se está preguntando:
                                        "-?" = hay ngeativo 0 o 1 vez?
                                        "\\d" = hay un digito del 0 al 9?
                                        "+" = lo hay 1 o mas veces? 
                                     */   
                JOptionPane.showMessageDialog(null, "Ingrese solo números enteros.");
                encontrado = 3;
            }

            int datoInt = 0;
            if (encontrado == 0) {
                datoInt = Integer.parseInt(dato);

                if (datoInt == 0) {
                    return; // como el 0 es un espacio vacío, funciona para regresar al menú
                }
            }
             
            /* 
            =============== BUSQUEDA ===================
             */ 
            /*  se buscará el dato en la forma 2. si no existe, se anunca que no se puede
                eliminar un dato que no existe, y se repite el dowhile.
            */

            NodoForma2 p = Punta.getLigaFila();
            x = null;

            while (p != Punta && encontrado == 0) {
                if (p.getDato() == datoInt) {
                    encontrado = 1;
                    x = p;
                }
                p = p.getLigaFila();
            }

            if (encontrado == 0) {
                JOptionPane.showMessageDialog(null, "No se encontró " + datoInt + " en la forma 2.");
                encontrado = 2;
            }
            
            /* 
            =============== ELIMINADO ===================
            */ 

            // se desenlaza el nodo encontrado de la lista por filas y de la lista por columnas
            if (encontrado == 1) {
                EliminarNodo(x);
            }
            
            if (encontrado == 1) {
                
                JOptionPane.showMessageDialog(null, mostrarForma2Dentro());
            }
            
        } while (encontrado == 2 || encontrado == 3);
        
    }

    public void EliminarPosicion() {
        if (Punta == null) {
            JOptionPane.showMessageDialog(null, "La forma 2 aun no ha sido creada.");
            return;
        }

        int posicion[] = new int[2];     // en [0] se guarda fila y en [1] columna
        int encontrado;
        NodoForma2 x = null;

        do {
            encontrado = 0;
            do {
                String fila = JOptionPane.showInputDialog("Ingrese la fila del dato a eliminar.");
                
                encontrado = 0;
                /* VALORES PARA "encontrado":
                    0 = dato aún no encontrado en forma 2
                    1 = dato encontrado en forma 2
                    2 = input vacío o incorrecto
                    3 = input con numeros no enteros
                */
                if (fila == null || fila == "" || fila.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Se debe ingresar una fila a eliminar.");
                    encontrado = 2;
                } else if (!fila.matches("\\d+")) {
                                        /* ^ regex es una forma de escribir patrones para comprobar si un texto cumple cierta forma.
                                            acá se está preguntando:
                                            "\\d" = hay un digito del 0 al 9?
                                            "+" = lo hay 1 o mas veces? 
                                        */   
                    JOptionPane.showMessageDialog(null, "Ingrese solo números enteros positivos.");
                    encontrado = 3;
                } else {
                    posicion[0] = Integer.parseInt(fila);
                }
            } while (encontrado == 2 || encontrado == 3);
            
            do {
                String colm = JOptionPane.showInputDialog("Ingrese la columna del dato a eliminar.");
                
                encontrado = 0;
                /* VALORES PARA "encontrado":
                    0 = dato aún no encontrado en forma 2
                    1 = dato encontrado en forma 2
                    2 = input vacío o incorrecto
                    3 = input con numeros no enteros
                */
                if (colm == null || colm == "" || colm.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Se debe ingresar una columna a eliminar.");
                    encontrado = 2;
                } else if (!colm.matches("\\d+")) {
                                        /* ^ regex es una forma de escribir patrones para comprobar si un texto cumple cierta forma.
                                            acá se está preguntando:
                                            "\\d" = hay un digito del 0 al 9?
                                            "+" = lo hay 1 o mas veces? 
                                        */   
                    JOptionPane.showMessageDialog(null, "Ingrese solo números enteros positivos.");
                    encontrado = 3;
                } else {
                    posicion[1] = Integer.parseInt(colm);
                }
            } while (encontrado == 2 || encontrado == 3);

            
            /* 
            =============== BUSQUEDA ===================
                */ 
            /*  se buscará la posicion en la forma 2. si no existe, se anuncia que no se puede
                eliminar una posicion que no existe, y se repite el dowhile.
            */
            encontrado = 0; // se resetea la variable bandera
            x = null;

            NodoForma2 p = Punta.getLigaFila();
            while (p != Punta && encontrado == 0) {

                if (p.getFila() == posicion[0] && p.getCol() == posicion[1]) {
                        encontrado = 1;
                        x = p;
                }
                p = p.getLigaFila();
            }

            if (encontrado == 0) {
                JOptionPane.showMessageDialog(null, "No se encontró la posicion ( "+posicion[0]+" , "+posicion[1]+" ) en la forma 2.");
                encontrado = 2;
            }
            
            /* 
            =============== ELIMINADO ===================
            */ 

            // se desenlaza el nodo encontrado de la lista por filas y de la lista por columnas
            if (encontrado == 1) {
                EliminarNodo(x);
                JOptionPane.showMessageDialog(null, mostrarForma2Dentro());
            }
        } while (encontrado == 2 || encontrado == 3);

    }

    public String mostrarSumas(int[] sumas, String titulo) {

        // se arma el mensaje final
        String sumatoria = titulo + "\n[  ";
        for (int i = 0; i < sumas.length; i++) {
            
            if (i == sumas.length - 1) {
                sumatoria += sumas[i] + "  ]";
            } else {
                sumatoria += sumas[i] + ",  ";
            }
        }

        return sumatoria;
    }

    private NodoForma2 BuscarNodo(int fila, int col) {
        NodoForma2 p = Punta.getLigaFila();

        // se busca la posicion exacta usando el recorrido por filas
        while (p != Punta) {
            if (p.getFila() == fila && p.getCol() == col) {
                return p;
            }
            p = p.getLigaFila();
        }

        return null;
    }

    private void InsertarNodo(NodoForma2 x) {
        NodoForma2 anteriorFila = Punta;
        NodoForma2 anteriorCol = Punta;
        NodoForma2 p = Punta.getLigaFila();

        // se busca donde debe quedar el nodo nuevo dentro del recorrido por filas
        while (p != Punta && (p.getFila() < x.getFila() || (p.getFila() == x.getFila() && p.getCol() < x.getCol()))) {
            anteriorFila = p;
            p = p.getLigaFila();
        }
        x.setLigaFila(p);
        anteriorFila.setLigaFila(x);

        p = Punta.getLigaColm();

        // se busca donde debe quedar el nodo nuevo dentro del recorrido por columnas
        while (p != Punta && (p.getCol() < x.getCol() || (p.getCol() == x.getCol() && p.getFila() < x.getFila()))) {
            anteriorCol = p;
            p = p.getLigaColm();
        }
        x.setLigaColm(p);
        anteriorCol.setLigaColm(x);
    }

    private void EliminarNodo(NodoForma2 x) {
        NodoForma2 anteriorFila = Punta;
        NodoForma2 anteriorCol = Punta;
        NodoForma2 p = Punta.getLigaFila();

        // se busca el nodo anterior a "x" dentro del recorrido por filas
        while (p != Punta && p != x) {
            anteriorFila = p;
            p = p.getLigaFila();
        }

        p = Punta.getLigaColm();

        // se busca el nodo anterior a "x" dentro del recorrido por columnas
        while (p != Punta && p != x) {
            anteriorCol = p;
            p = p.getLigaColm();
        }

        // luego se salta "x" en ambas ligas para que deje de pertenecer a la forma 2
        anteriorFila.setLigaFila(x.getLigaFila());
        anteriorCol.setLigaColm(x.getLigaColm());

        // despues de eliminar un nodo, se recalculan las dimensiones que ahora le corresponden a "Punta"
        RecalcularDimensiones();
    }

    private void RecalcularDimensiones() {
        NodoForma2 p = Punta.getLigaFila();
        int mayorFila = -1, mayorCol = -1;

        // se recorre la forma buscando la mayor fila y la mayor columna que aun existen
        while (p != Punta) {
            if (mayorFila < p.getFila()) {
                mayorFila = p.getFila();
            }

            if (mayorCol < p.getCol()) {
                mayorCol = p.getCol();
            }
            p = p.getLigaFila();
        }

        // si la forma quedó vacia, sus dimensiones pasan a ser 0 x 0
        if (mayorFila == -1 && mayorCol == -1) {
            Punta.setFila(0);
            Punta.setCol(0);
        } else {
            Punta.setFila(mayorFila + 1);
            Punta.setCol(mayorCol + 1);
        }
    }
}
