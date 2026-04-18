public class NodoForma2 {

    // Atributos
    private int Fila, Col, Dato;
    private NodoForma2 LigaFil, LigaCol;

    // Constructor 

    public NodoForma2 (int Fila, int Col, int Dato) {
        this.Fila = Fila;
        this.Col = Col;
        this.Dato = Dato;

        this.LigaFil = null;
        this.LigaCol = null;
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

    public NodoForma2 getLigaFila() {
        return LigaFil;
    }

    public void setLigaFila(NodoForma2 LigaFil) {
        this.LigaFil = LigaFil;
    }

    public NodoForma2 getLigaColm() {
        return LigaCol;
    }

    public void setLigaColm(NodoForma2 LigaCol) {
        this.LigaCol = LigaCol;
    }
}
