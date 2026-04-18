public class Forma1 {
    // "Punta" es el nodo principal desde el cual se accede a toda la forma 1
    private NodoForma1 Punta;

    public Forma1() {
        this.Punta = null;    
    }    

    public NodoForma1 getPunta() {
        return Punta;
    }

    public void setPunta(NodoForma1 Punta) {
        this.Punta = Punta;
    }


    public void LlenarDesdeDispersa(int Mat[][]) {
        // se llena la forma por pasos para no perderse entre cabeceras y recorridos
        Paso1(Mat.length, Mat[0].length);
        Paso2(Mat);
        Paso3();
    }

    private void Paso1(int n, int m) {
        NodoForma1 x;
        NodoForma1 p;
        int i = 0;
        int may = Math.max(n, m);

        // "Punta" guarda las dimensiones de la matriz y sirve de inicio para las cabeceras
        Punta = new NodoForma1(n, m, 0);
        p = Punta;

        // se crea una cabecera por cada fila/columna necesaria segun la dimension mayor
        while (i < may) {
            x = new NodoForma1(i, i, 0);
            p.setLiga(x);
            p = x;
            i++;
        }

        // al final se cierra la lista de cabeceras apuntando de nuevo a "Punta"
        p.setLiga(Punta);
    }

    private void Paso2(int[][] Mat) {
        NodoForma1 p = Punta.getLiga();
        NodoForma1 q = p, x;

        // aca se recorren las filas de la matriz dispersa para encadenar solo los datos != 0
        for (int i = 0; i < Mat.length; i++) {
            for (int j = 0; j < Mat[0].length; j++) {
                if (Mat[i][j] != 0) {
                    x = new NodoForma1(i, j, Mat[i][j]);
                    q.setLigaFila(x);
                    q = x;
                }
            }

            // cuando se termina la fila, esta vuelve a su cabecera correspondiente
            q.setLigaFila(p);
            p = p.getLiga();
            q = p;
        }

    }

    private void Paso3() {
        // "RC" va llevando la cabecera de la columna que se esta armando
        NodoForma1 RC = Punta.getLiga();
        NodoForma1 a = RC;
        NodoForma1 p = Punta.getLiga();
        NodoForma1 q = p.getLigaFila();

        // este paso reutiliza lo que ya quedo enlazado por filas para enlazar ahora por columnas
        while (RC != Punta) {
            while (p != Punta) {
                while (q != p) {
                    if (q.getCol() == RC.getCol()) {
                        a.setLigaColm(q);
                        a = q;
                    }
                    q = q.getLigaFila();
                }
                p = p.getLiga();
                q = p.getLigaFila();
            }

            // al terminar cada columna, esta tambien se cierra en su cabecera
            a.setLigaColm(RC);
            RC = RC.getLiga();
            a = RC;
            p = Punta.getLiga();
            q = p.getLigaFila();
        }

    }
}
