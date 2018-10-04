import java.util.LinkedList;

public class Peao extends Peca implements PecaDeXadrez {

	// determina se o peao pode ser alvo de um en passant
	public boolean moveuDuasCasas;

	Peao(String cor) {
		super(cor);
	}

	public void movimentosValidos() {
		if (getCor().equals("preta")) {
			if (podePular(getLinha() + 1, getColuna()))
				getTabuleiro().obtemBotaoCasa(getLinha() + 1, getColuna()).setBackground(getTabuleiro().movimentoNormal);
		} else {
			if (podePular(getLinha() - 1, getColuna()))
				getTabuleiro().obtemBotaoCasa(getLinha() - 1, getColuna()).setBackground(getTabuleiro().movimentoNormal);
		}
		
		for (Casa casa : casasNavegaveis()) {
			casa.getBotao().setBackground(getTabuleiro().movimentoNormal);
		}
		
		movimentosEspeciais();
	}

	public LinkedList<Casa> casasNavegaveis() {
		LinkedList<Casa> movimentos = new LinkedList<>();

		if (getCor().equals("preta")) {
			if (podeAtacar(getLinha() + 1, getColuna() - 1))
				movimentos.add(getTabuleiro().obtemCasa(getLinha() + 1, getColuna() - 1));
			if (podeAtacar(getLinha() + 1, getColuna() + 1))
				movimentos.add(getTabuleiro().obtemCasa(getLinha() + 1, getColuna() + 1));
		}  else {
			if (podeAtacar(getLinha() - 1, getColuna() - 1))
				movimentos.add(getTabuleiro().obtemCasa(getLinha() - 1, getColuna() - 1));
			if (podeAtacar(getLinha() - 1, getColuna() + 1))
				movimentos.add(getTabuleiro().obtemCasa(getLinha() - 1, getColuna() + 1));
		}
		
		return movimentos;
	}

	public void movimentosEspeciais() {
		// move duas casas no primeiro movimento
		if (getCor().equals("preta") && getLinha() == 1 && podePular(getLinha() + 2, getColuna()))
			getTabuleiro().obtemBotaoCasa(getLinha() + 2, getColuna()).setBackground(getTabuleiro().peaoMovDuasCasas);
		if (getCor().equals("branca") && getLinha() == 6 && podePular(getLinha() - 2, getColuna()))
			getTabuleiro().obtemBotaoCasa(getLinha() - 2, getColuna()).setBackground(getTabuleiro().peaoMovDuasCasas);

		// en passant
		if (getCor().equals("branca") && getLinha() == 3) {
			Peca alvo;
			if ((alvo = getTabuleiro().obtemCasa(getLinha(), getColuna() + 1).getPeca()) instanceof Peao) {
				Peao peaoAlvo = (Peao) alvo;
				if (peaoAlvo.moveuDuasCasas)
					getTabuleiro().obtemBotaoCasa(getLinha() - 1, getColuna() + 1).setBackground(getTabuleiro().peaoEnPassant);
			}
			if ((alvo = getTabuleiro().obtemCasa(getLinha(), getColuna() - 1).getPeca()) instanceof Peao) {
				Peao peaoAlvo = (Peao) alvo;
				if (peaoAlvo.moveuDuasCasas)
					getTabuleiro().obtemBotaoCasa(getLinha() - 1, getColuna() - 1).setBackground(getTabuleiro().peaoEnPassant);
			}
		} else if (getCor().equals("preta") && getLinha() == 4) {
			Peca alvo;
			if ((alvo = getTabuleiro().obtemCasa(getLinha(), getColuna() + 1).getPeca()) instanceof Peao) {
				Peao peaoAlvo = (Peao) alvo;
				if (peaoAlvo.moveuDuasCasas)
					getTabuleiro().obtemBotaoCasa(getLinha() + 1, getColuna() + 1).setBackground(getTabuleiro().peaoEnPassant);
			}
			if ((alvo = getTabuleiro().obtemCasa(getLinha(), getColuna() - 1).getPeca()) instanceof Peao) {
				Peao peaoAlvo = (Peao) alvo;
				if (peaoAlvo.moveuDuasCasas)
					getTabuleiro().obtemBotaoCasa(getLinha() + 1, getColuna() - 1).setBackground(getTabuleiro().peaoEnPassant);
			}
		}

		// promocao
		if (getCor().equals("branca") && getLinha() == 1) {
			if (podePular(getLinha() - 1, getColuna()))
				getTabuleiro().obtemBotaoCasa(getLinha() - 1, getColuna()).setBackground(getTabuleiro().peaoPromocao);

			if (podeAtacar(getLinha() - 1, getColuna() - 1))
				getTabuleiro().obtemBotaoCasa(getLinha() - 1, getColuna() - 1).setBackground(getTabuleiro().peaoPromocao);
			if (podeAtacar(getLinha() - 1, getColuna() + 1))
				getTabuleiro().obtemBotaoCasa(getLinha() - 1, getColuna() + 1).setBackground(getTabuleiro().peaoPromocao);
		} else if (getCor().equals("preta") && getLinha() == 6) {
			if (podePular(getLinha() + 1, getColuna()))
				getTabuleiro().obtemBotaoCasa(getLinha() + 1, getColuna()).setBackground(getTabuleiro().peaoPromocao);

			if (podeAtacar(getLinha() + 1, getColuna() - 1))
				getTabuleiro().obtemBotaoCasa(getLinha() + 1, getColuna() - 1).setBackground(getTabuleiro().peaoPromocao);
			if (podeAtacar(getLinha() + 1, getColuna() + 1))
				getTabuleiro().obtemBotaoCasa(getLinha() + 1, getColuna() + 1).setBackground(getTabuleiro().peaoPromocao);
		}
	}
}
