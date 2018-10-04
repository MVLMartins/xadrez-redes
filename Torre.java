import java.util.LinkedList;

public class Torre extends Peca implements PecaDeXadrez {

	Torre(String cor) {
		super(cor);
	}

	public void movimentosValidos() {
		for (Casa casa : casasNavegaveis()) {
			casa.getBotao().setBackground(getTabuleiro().movimentoNormal);
		}
	}

	public LinkedList<Casa> casasNavegaveis() {
		return movimentosHorizontais();
	}

	public void movimentosEspeciais() {
	}
}
