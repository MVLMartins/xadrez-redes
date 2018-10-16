import java.io.Serializable;

public class Movimento implements Serializable {

    private int linha;
    private int coluna;
    private int linhaAntiga;
    private int colunaAntiga;

    public Movimento(int linha, int coluna, int linhaAntiga, int colunaAntiga){
        this.linha = linha;
        this.coluna = coluna;
        this.linhaAntiga = linhaAntiga;
        this.colunaAntiga = colunaAntiga;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getLinhaAntiga() {
        return linhaAntiga;
    }

    public int getColunaAntiga() {
        return colunaAntiga;
    }
}
