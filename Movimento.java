import java.awt.*;
import java.io.Serializable;

public class Movimento implements Serializable {

    public int linha;
    public int coluna;
    public int linhaAntiga;
    public int colunaAntiga;
    public String cor;
    public Color corCasa;

    public Movimento(int linha, int coluna, int linhaAntiga, int colunaAntiga){
        this.linha = linha;
        this.coluna = coluna;
        this.linhaAntiga = linhaAntiga;
        this.colunaAntiga = colunaAntiga;
    }


}
