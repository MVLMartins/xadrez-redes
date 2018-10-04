import java.util.LinkedList;

public abstract class Peca {

	private Casa casa;
	private String cor;
	public boolean foiMovida;

	Peca(String cor) {
		this.cor = cor;
	}

	void setCasa(Casa casa) {
		this.casa = casa;
	}

	Casa getCasa() {
		return casa;
	}

	String getCor() {
		return cor;
	}

	int getLinha() {
		return casa.getLinha();
	}

	int getColuna() {
		return casa.getColuna();
	}

	Tabuleiro getTabuleiro() {
		return casa.tab;
	}

	boolean podePular(int linha, int coluna) {
		return dentroDoTabuleiro(linha, coluna) && casaVazia(linha, coluna);
	}

	boolean podeAtacar(int linha, int coluna) {
		return dentroDoTabuleiro(linha, coluna) && casaInimiga(linha, coluna);
	}

	boolean dentroDoTabuleiro(int linha, int coluna) {
		return (linha >= 0 && linha <= 7 && coluna >= 0 && coluna <= 7);
	}

	boolean casaVazia(int linha, int coluna) {
		return getTabuleiro().obtemCasa(linha, coluna).getPeca() == null;
	}

	boolean casaInimiga(int linha, int coluna) {
		if (casaVazia(linha, coluna))
			return false;

		return !cor.equals(getTabuleiro().obtemCasa(linha, coluna).getPeca().getCor());
	}

	// metodo herdado pela torre e rainha
	LinkedList<Casa> movimentosHorizontais() {
		LinkedList<Casa> movimentos = new LinkedList<Casa>();

		for (int i = 1; i < 8; i++) {
			if (podePular(getLinha() + i, getColuna()))
				movimentos.add(getTabuleiro().obtemCasa(getLinha() + i, getColuna()));
			else if (podeAtacar(getLinha() + i, getColuna())) {
				movimentos.add(getTabuleiro().obtemCasa(getLinha() + i, getColuna()));
				break;
			} else
				break;
		}

		for (int i = 1; i < 8; i++) {
			if (podePular(getLinha() - i, getColuna()))
				movimentos.add(getTabuleiro().obtemCasa(getLinha() - i, getColuna()));
			else if (podeAtacar(getLinha() - i, getColuna())) {
				movimentos.add(getTabuleiro().obtemCasa(getLinha() - i, getColuna()));
				break;
			} else
				break;
		}

		for (int i = 1; i < 8; i++) {
			if (podePular(getLinha(), getColuna() + i))
				movimentos.add(getTabuleiro().obtemCasa(getLinha(), getColuna() + i));
			else if (podeAtacar(getLinha(), getColuna() + i)) {
				movimentos.add(getTabuleiro().obtemCasa(getLinha(), getColuna() + i));
				break;
			} else
				break;
		}

		for (int i = 1; i < 8; i++) {
			if (podePular(getLinha(), getColuna() - i))
				movimentos.add(getTabuleiro().obtemCasa(getLinha(), getColuna() - i));
			else if (podeAtacar(getLinha(), getColuna() - i)) {
				movimentos.add(getTabuleiro().obtemCasa(getLinha(), getColuna() - i));
				break;
			} else
				break;
		}

		return movimentos;
	}

	// metodo herdado pelo bispo e rainha
	LinkedList<Casa> movimentosDiagonais() {
		LinkedList<Casa> movimentos = new LinkedList<Casa>();

		for (int i = 1; i < 8; i++) {
			if (podePular(getLinha() + i, getColuna() + i))
				movimentos.add(getTabuleiro().obtemCasa(getLinha() + i, getColuna() + i));
			else if (podeAtacar(getLinha() + i, getColuna() + i)) {
				movimentos.add(getTabuleiro().obtemCasa(getLinha() + i, getColuna() + i));
				break;
			} else
				break;
		}

		for (int i = 1; i < 8; i++) {
			if (podePular(getLinha() + i, getColuna() - i))
				movimentos.add(getTabuleiro().obtemCasa(getLinha() + i, getColuna() - i));
			else if (podeAtacar(getLinha() + i, getColuna() - i)) {
				movimentos.add(getTabuleiro().obtemCasa(getLinha() + i, getColuna() - i));
				break;
			} else
				break;
		}

		for (int i = 1; i < 8; i++) {
			if (podePular(getLinha() - i, getColuna() - i))
				movimentos.add(getTabuleiro().obtemCasa(getLinha() - i, getColuna() - i));
			else if (podeAtacar(getLinha() - i, getColuna() - i)) {
				movimentos.add(getTabuleiro().obtemCasa(getLinha() - i, getColuna() - i));
				break;
			} else
				break;
		}

		for (int i = 1; i < 8; i++) {
			if (podePular(getLinha() - i, getColuna() + i))
				movimentos.add(getTabuleiro().obtemCasa(getLinha() - i, getColuna() + i));
			else if (podeAtacar(getLinha() - i, getColuna() + i)) {
				movimentos.add(getTabuleiro().obtemCasa(getLinha() - i, getColuna() + i));
				break;
			} else
				break;
		}

		return movimentos;
	}
}
