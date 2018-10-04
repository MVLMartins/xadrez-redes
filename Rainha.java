import java.util.LinkedList;

public class Rainha extends Peca implements PecaDeXadrez {

	Rainha(String cor) {
		super(cor);
	}

	public void movimentosValidos() {
		for (Casa casa : casasNavegaveis()) {
			casa.getBotao().setBackground(getTabuleiro().movimentoNormal);
		}
	}

	public LinkedList<Casa> casasNavegaveis() {
		LinkedList<Casa> movimentos = new LinkedList<>();

		movimentos.addAll(movimentosHorizontais());
		movimentos.addAll(movimentosDiagonais());
		
		return movimentos;
	}

	public void movimentosEspeciais() {
	}
}
