import java.util.LinkedList;

public interface PecaDeXadrez {
	// recebe a linha e coluna da peca, mostra todos os movimentos validos
	void movimentosValidos();
	
	// recebe a linha e coluna da peca, retorna os movimentos normais validos
	LinkedList<Casa> casasNavegaveis();
	
	// recebe a linha e coluna da peca, mostra os movimentos especiais validos
	void movimentosEspeciais();
}
