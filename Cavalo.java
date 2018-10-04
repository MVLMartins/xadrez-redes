import java.util.LinkedList;

public class Cavalo extends Peca implements PecaDeXadrez {

	Cavalo(String cor) {
		super(cor);
	}

	public void movimentosValidos() {
		for (Casa casa : casasNavegaveis()) {
			casa.getBotao().setBackground(getTabuleiro().movimentoNormal);
		}
	}

	public LinkedList<Casa> casasNavegaveis() {
		LinkedList<Casa> movimentos = new LinkedList<>();
		
		if (podePular(getLinha() + 2, getColuna() + 1) || podeAtacar(getLinha() + 2, getColuna() + 1))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() + 2, getColuna() + 1));
		if (podePular(getLinha() + 1, getColuna() + 2) || podeAtacar(getLinha() + 1, getColuna() + 2))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() + 1, getColuna() + 2));
		if (podePular(getLinha() + 2, getColuna() - 1) || podeAtacar(getLinha() + 2, getColuna() - 1))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() + 2, getColuna() - 1));
		if (podePular(getLinha() + 1, getColuna() - 2) || podeAtacar(getLinha() + 1, getColuna() - 2))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() + 1, getColuna() - 2));

		if (podePular(getLinha() - 2, getColuna() + 1) || podeAtacar(getLinha() - 2, getColuna() + 1))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() - 2, getColuna() + 1));
		if (podePular(getLinha() - 1, getColuna() + 2) || podeAtacar(getLinha() - 1, getColuna() + 2))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() - 1, getColuna() + 2));
		if (podePular(getLinha() - 2, getColuna() - 1) || podeAtacar(getLinha() - 2, getColuna() - 1))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() - 2, getColuna() - 1));
		if (podePular(getLinha() - 1, getColuna() - 2) || podeAtacar(getLinha() - 1, getColuna() - 2))
			movimentos.add(getTabuleiro().obtemCasa(getLinha() - 1, getColuna() - 2));
		
		return movimentos;
	}

	public void movimentosEspeciais() {
	}
}
