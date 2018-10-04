import java.util.LinkedList;

public class Rei extends Peca implements PecaDeXadrez {

	Rei(String cor) {
		super(cor);
	}

	public void movimentosValidos() {
		for (Casa casa : casasNavegaveis()) {
			casa.getBotao().setBackground(getTabuleiro().movimentoNormal);
		}
		movimentosEspeciais();
	}

	public LinkedList<Casa> casasNavegaveis() {
		LinkedList<Casa> movimentos = new LinkedList<>();

		if ((podePular(getLinha() + 1, getColuna())) || podeAtacar(getLinha() + 1, getColuna()))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() + 1, getColuna()));
		if ((podePular(getLinha() - 1, getColuna())) || podeAtacar(getLinha() - 1, getColuna()))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() - 1, getColuna()));
		if ((podePular(getLinha(), getColuna() + 1)) || podeAtacar(getLinha(), getColuna() + 1))
			movimentos.add(getTabuleiro().obtemCasa(getLinha(), getColuna() + 1));
		if ((podePular(getLinha(), getColuna() - 1)) || podeAtacar(getLinha(), getColuna() - 1))
			movimentos.add(getTabuleiro().obtemCasa(getLinha(), getColuna() - 1));

		if ((podePular(getLinha() + 1, getColuna() + 1)) || podeAtacar(getLinha() + 1, getColuna() + 1))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() + 1, getColuna() + 1));
		if ((podePular(getLinha() - 1, getColuna() - 1)) || podeAtacar(getLinha() - 1, getColuna() - 1))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() - 1, getColuna() - 1));
		if ((podePular(getLinha() + 1, getColuna() - 1)) || podeAtacar(getLinha() + 1, getColuna() - 1))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() + 1, getColuna() - 1));
		if ((podePular(getLinha() - 1, getColuna() + 1)) || podeAtacar(getLinha() - 1, getColuna() + 1))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() - 1, getColuna() + 1));

		return movimentos;
	}

	public void movimentosEspeciais() {
		if (!foiMovida && getCor().equals("branca")) {
			if ((getTabuleiro().obtemCasa(getLinha(), getColuna() + 3).getPeca() instanceof Torre)
					&& !getTabuleiro().obtemCasa(getLinha(), getColuna() + 3).getPeca().foiMovida) {
				boolean podeEfetuarRoque = true;

				for (int i = 1; i < 3; i++) {
					if (!casaVazia(getLinha(), getColuna() + i)) {
						podeEfetuarRoque = false;
						break;
					}
				}
				for (int i = 0; i < 3; i++) {
					for (Casa casa : getTabuleiro().casasAtacaveis(getTabuleiro().getPecasPretas())) {
						if (!podeEfetuarRoque)
							break;

						if (getLinha() == casa.getLinha() && (getColuna() + i) == casa.getColuna())
							podeEfetuarRoque = false;
					}
				}
				if (podeEfetuarRoque)
					getTabuleiro().obtemBotaoCasa(getLinha(), getColuna() + 2).setBackground(getTabuleiro().reiRoque);
			}
			if ((getTabuleiro().obtemCasa(getLinha(), getColuna() - 4).getPeca() instanceof Torre)
					&& !getTabuleiro().obtemCasa(getLinha(), getColuna() - 4).getPeca().foiMovida) {
				boolean podeEfetuarRoque = true;

				for (int i = 1; i < 4; i++) {
					if (!casaVazia(getLinha(), getColuna() - i)) {
						podeEfetuarRoque = false;
						break;
					}
				}
				for (int i = 0; i < 4; i++) {
					for (Casa casa : getTabuleiro().casasAtacaveis(getTabuleiro().getPecasPretas())) {
						if (!podeEfetuarRoque)
							break;

						if (getLinha() == casa.getLinha() && (getColuna() - i) == casa.getColuna())
							podeEfetuarRoque = false;
					}
				}
				if (podeEfetuarRoque)
					getTabuleiro().obtemBotaoCasa(getLinha(), getColuna() - 2).setBackground(getTabuleiro().reiRoque);
			}
		} else if (!foiMovida && getCor().equals("preta")) {
			if ((getTabuleiro().obtemCasa(getLinha(), getColuna() + 3).getPeca() instanceof Torre)
					&& !getTabuleiro().obtemCasa(getLinha(), getColuna() + 3).getPeca().foiMovida) {
				boolean podeEfetuarRoque = true;

				for (int i = 1; i < 3; i++) {
					if (!casaVazia(getLinha(), getColuna() + i)) {
						podeEfetuarRoque = false;
						break;
					}
				}
				for (int i = 0; i < 3; i++) {
					for (Casa casa : getTabuleiro().casasAtacaveis(getTabuleiro().getPecasBrancas())) {
						if (!podeEfetuarRoque)
							break;

						if (getLinha() == casa.getLinha() && (getColuna() + i) == casa.getColuna())
							podeEfetuarRoque = false;
					}
				}
				if (podeEfetuarRoque)
					getTabuleiro().obtemBotaoCasa(getLinha(), getColuna() + 2).setBackground(getTabuleiro().reiRoque);
			}
			if ((getTabuleiro().obtemCasa(getLinha(), getColuna() - 4).getPeca() instanceof Torre)
					&& !getTabuleiro().obtemCasa(getLinha(), getColuna() - 4).getPeca().foiMovida) {
				boolean podeEfetuarRoque = true;

				for (int i = 1; i < 4; i++) {
					if (!casaVazia(getLinha(), getColuna() - i)) {
						podeEfetuarRoque = false;
						break;
					}
				}
				for (int i = 0; i < 4; i++) {
					for (Casa casa : getTabuleiro().casasAtacaveis(getTabuleiro().getPecasBrancas())) {
						if (!podeEfetuarRoque)
							break;

						if (getLinha() == casa.getLinha() && (getColuna() - i) == casa.getColuna())
							podeEfetuarRoque = false;
					}
				}
				if (podeEfetuarRoque)
					getTabuleiro().obtemBotaoCasa(getLinha(), getColuna() - 2).setBackground(getTabuleiro().reiRoque);
			}
		}
	}
}