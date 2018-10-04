import java.awt.Dimension;

import javax.swing.JButton;

public class Casa {

	public Tabuleiro tab = Tabuleiro.getTabuleiro();
	private JButton botao;
	private Peca peca;
	private int linha, coluna;

	Casa(int lin, int col) {
		linha = lin;
		coluna = col;
		botao = new JButton();
		botao.setPreferredSize(new Dimension(85, 85));
	}

	void setPeca(Peca peca) {
		this.peca = peca;

		if (peca != null)
			peca.setCasa(this);
	}

	JButton getBotao() {
		return botao;
	}

	Peca getPeca() {
		return peca;
	}

	int getLinha() {
		return linha;
	}
	
	int getColuna() {
		return coluna;
	}
}
