import java.util.LinkedList;

public class Bispo extends Peca implements PecaDeXadrez {

	Bispo(String cor) {
		super(cor);
	}

	public void movimentosValidos() {
		for (Casa casa : casasNavegaveis()) {
			casa.getBotao().setBackground(getTabuleiro().movimentoNormal);
		}
	}

	public LinkedList<Casa> casasNavegaveis() {
		return movimentosDiagonais();
	}

	public void movimentosEspeciais() {
	}
}
