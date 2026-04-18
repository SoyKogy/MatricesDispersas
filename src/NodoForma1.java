public class NodoForma1 {

    // Atributos
    private int Fila, Col, Dato;
    private NodoForma1 Liga, LigaFila, LigaColm;

    // Constructor 

    public NodoForma1 (int Fila, int Col, int Dato) {
        this.Fila = Fila;
        this.Col = Col;
        this.Dato = Dato;

        this.Liga = null;
        this.LigaFila = null;
        this.LigaColm = null;
    }

    public int getFila() {
        return Fila;
    }

    public void setFila(int Fila) {
        this.Fila = Fila;
    }

    public int getCol() {
        return Col;
    }

    public void setCol(int Col) {
        this.Col = Col;
    }

    public int getDato() {
        return Dato;
    }

    public void setDato(int Dato) {
        this.Dato = Dato;
    }

    public NodoForma1 getLiga() {
        return Liga;
    }

    public void setLiga(NodoForma1 Liga) {
        this.Liga = Liga;
    }

    public NodoForma1 getLigaFila() {
        return LigaFila;
    }

    public void setLigaFila(NodoForma1 LigaFila) {
        this.LigaFila = LigaFila;
    }

    public NodoForma1 getLigaColm() {
        return LigaColm;
    }

    public void setLigaColm(NodoForma1 LigaColm) {
        this.LigaColm = LigaColm;
    }
}
