/* Vinicius Shoiti Koike Graciliano
      n USP 9862972 - SI - Noite    */

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

class Tabuleiro {

	private static Tabuleiro instance;
	public String jogador;
	public boolean ehSuaVez;

	private FrameTabuleiro out ;


	private static Casa[][] tabuleiro = new Casa[8][8];
	private static LinkedList<PecaDeXadrez> pecasBrancas = new LinkedList<>();
	private static LinkedList<PecaDeXadrez> pecasPretas = new LinkedList<>();
	
	private PecaDeXadrez pecaClicada;
	private int linhaClicada;
	private int colunaClicada;
	private String corClicada;
	private int numDeJogadas;

	private final Color casaBranca = new Color(255, 255, 255);
	private final Color casaPreta = new Color(34, 116, 165);

	// cores utilizadas para identificar os movimentos
	public final Color movimentoNormal = new Color(255, 191, 0);
	public final Color peaoMovDuasCasas = new Color(255, 191, 0);
	public final Color peaoEnPassant =  new Color(232, 63, 111);
	public final Color peaoPromocao = new Color(232, 63, 111);
	public final Color reiRoque = new Color(232, 63, 111);

	private Tabuleiro(FrameTabuleiro out) {
		this.out = out;
	}

	public static Tabuleiro criaTabuleiro(String jogador,FrameTabuleiro out){
		if(instance==null){
			instance = new Tabuleiro(out);
			instance.jogador = jogador;
			if(jogador.equals("branca")){
				instance.ehSuaVez = true;
			}else{
				instance.ehSuaVez = false;
			}
		}
		return  instance;
	}

	public static Tabuleiro getTabuleiro(){
		return instance;
	}


	void criaEPintaAsCasas() {
		criaAsCasas();
		criaAsPecas();
		pintaAsCasas();
	}

	void criaAsCasas() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				tabuleiro[i][j] = new Casa(i, j);
			}
		}
	}

	void criaAsPecas() {
		obtemCasa(0, 0).setPeca(new Torre("preta"));
		pecasPretas.add((PecaDeXadrez) obtemCasa(0, 0).getPeca());
		obtemCasa(0, 1).setPeca(new Cavalo("preta"));
		pecasPretas.add((PecaDeXadrez) obtemCasa(0, 1).getPeca());
		obtemCasa(0, 2).setPeca(new Bispo("preta"));
		pecasPretas.add((PecaDeXadrez) obtemCasa(0, 2).getPeca());
		obtemCasa(0, 3).setPeca(new Rainha("preta"));
		pecasPretas.add((PecaDeXadrez) obtemCasa(0, 3).getPeca());
		obtemCasa(0, 4).setPeca(new Rei("preta"));
		pecasPretas.add((PecaDeXadrez) obtemCasa(0, 4).getPeca());
		obtemCasa(0, 5).setPeca(new Bispo("preta"));
		pecasPretas.add((PecaDeXadrez) obtemCasa(0, 5).getPeca());
		obtemCasa(0, 6).setPeca(new Cavalo("preta"));
		pecasPretas.add((PecaDeXadrez) obtemCasa(0, 6).getPeca());
		obtemCasa(0, 7).setPeca(new Torre("preta"));
		pecasPretas.add((PecaDeXadrez) obtemCasa(0, 7).getPeca());

		for (int i = 0; i < 8; i++) {
			obtemCasa(1, i).setPeca(new Peao("preta"));
			pecasPretas.add((PecaDeXadrez) obtemCasa(1, i).getPeca());
		}

		for (int i = 0; i < 8; i++) {
			obtemCasa(6, i).setPeca(new Peao("branca"));
			pecasBrancas.add((PecaDeXadrez) obtemCasa(6, i).getPeca());
		}

		obtemCasa(7, 0).setPeca(new Torre("branca"));
		pecasBrancas.add((PecaDeXadrez) obtemCasa(7, 0).getPeca());
		obtemCasa(7, 1).setPeca(new Cavalo("branca"));
		pecasBrancas.add((PecaDeXadrez) obtemCasa(7, 1).getPeca());
		obtemCasa(7, 2).setPeca(new Bispo("branca"));
		pecasBrancas.add((PecaDeXadrez) obtemCasa(7, 2).getPeca());
		obtemCasa(7, 3).setPeca(new Rainha("branca"));
		pecasBrancas.add((PecaDeXadrez) obtemCasa(7, 3).getPeca());
		obtemCasa(7, 4).setPeca(new Rei("branca"));
		pecasBrancas.add((PecaDeXadrez) obtemCasa(7, 4).getPeca());
		obtemCasa(7, 5).setPeca(new Bispo("branca"));
		pecasBrancas.add((PecaDeXadrez) obtemCasa(7, 5).getPeca());
		obtemCasa(7, 6).setPeca(new Cavalo("branca"));
		pecasBrancas.add((PecaDeXadrez) obtemCasa(7, 6).getPeca());
		obtemCasa(7, 7).setPeca(new Torre("branca"));
		pecasBrancas.add((PecaDeXadrez) obtemCasa(7, 7).getPeca());
	}

	void pintaAsCasas() {
		boolean white = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (white) {
					obtemBotaoCasa(i, j).setBackground(casaBranca);
					white = false;
				} else {
					obtemBotaoCasa(i, j).setBackground(casaPreta);
					white = true;
				}
			}
			if (white)
				white = false;
			else
				white = true;
		}
	}

	JButton obtemBotaoCasa(int linha, int coluna) {
		return tabuleiro[linha][coluna].getBotao();
	}

	Casa obtemCasa(int linha, int coluna) {
		return tabuleiro[linha][coluna];
	}

	public void trocaVez(){
		ehSuaVez = !ehSuaVez;
	}

	public boolean ehSuaVez (){
		return ehSuaVez;
	}

	boolean ehSuaPeca(String cor) {
		return jogador.equals(cor);
	}

	LinkedList<PecaDeXadrez> getPecasBrancas() {
		return pecasBrancas;
	}
	
	LinkedList<PecaDeXadrez> getPecasPretas() {
		return pecasPretas;
	}

	LinkedList<Casa> casasAtacaveis(LinkedList<PecaDeXadrez> pecas) {
		LinkedList<Casa> casas = new LinkedList<>();
		for (PecaDeXadrez peca : pecas) {
			casas.addAll(peca.casasNavegaveis());
		}
		return casas;
	}

	boolean estaEmCheque(String cor) {
		LinkedList<PecaDeXadrez> pecas;
		if (cor.equals("preta"))
			pecas = pecasBrancas;
		else pecas = pecasPretas;
		
		for (Casa casaAtacavel : casasAtacaveis(pecas)) {
			if (casaAtacavel.getPeca() != null) {
			}
			if (casaAtacavel.getPeca() instanceof Rei) {
				return true;
			}
		}

		return false;
	}

	boolean estaEmMate(String cor) {
		// TODO: por falta de planejamento, o jogo n√£o sabe determinar um Mate, apenas impede todas as jogadas, pois todas resultam em Cheque
		return false;
	}
	
	void movePeca(PecaDeXadrez pecaClicada, int linha, int coluna) {
		Peca peca = (Peca) pecaClicada;
		int linhaTemp = peca.getCasa().getLinha();
		int colunaTemp = peca.getCasa().getColuna();

		colocaPeca(peca, linha, coluna);
		removePeca(linhaTemp, colunaTemp);
	}

	void peaoMoveDuasCasas(PecaDeXadrez pecaClicada, int linha, int coluna) {
		Peca peca = (Peca) pecaClicada;
		int linhaTemp = peca.getCasa().getLinha();
		int colunaTemp = peca.getCasa().getColuna();

		colocaPeca(peca, linha, coluna);
		removePeca(linhaTemp, colunaTemp);

		Peao peaoMovido = (Peao) obtemCasa(linha, coluna).getPeca();
		peaoMovido.moveuDuasCasas = true;
	}

	void peaoEnPassant(PecaDeXadrez pecaClicada, int linha, int coluna) {
		Peca peca = (Peca) pecaClicada;
		int linhaTemp = peca.getCasa().getLinha();
		int colunaTemp = peca.getCasa().getColuna();

		colocaPeca(peca, linha, coluna);
		removePeca(linhaTemp, colunaTemp);

		if (linha == 2)
			removePeca(linha + 1, coluna);
		else
			removePeca(linha - 1, coluna);
	}

	void peaoPromocao(PecaDeXadrez peao, int linha, int coluna, Peca pecaPromovida, Image icone) {
		Peca peca = (Peca) pecaClicada;
		int linhaTemp = peca.getCasa().getLinha();
		int colunaTemp = peca.getCasa().getColuna();

		obtemCasa(linha, coluna).setPeca(pecaPromovida);
		obtemBotaoCasa(linha, coluna).setIcon(new ImageIcon(icone));

		removePeca(linhaTemp, colunaTemp);
	}

	void reiRoque(PecaDeXadrez rei, int linha, int coluna) {
		if (coluna == 2) {
			movePeca(rei, linha, coluna);
			movePeca(((PecaDeXadrez) obtemCasa(linha, 0).getPeca()), linha, 3);
		}
		else {
			movePeca(rei, linha, coluna);
			movePeca(((PecaDeXadrez) obtemCasa(linha, 7).getPeca()), linha, 5);
		}
		
	}
	
	void colocaPeca(Peca peca, int linha, int coluna) {
		// se ha uma peca no alvo, apaga ela
		if ((obtemCasa(linha, coluna).getPeca() != null) 
				&& obtemCasa(linha, coluna).getPeca().getCor().equals("preta"))
			pecasPretas.remove((PecaDeXadrez) (obtemCasa(linha, coluna).getPeca()));
		else if ((obtemCasa(linha, coluna).getPeca() != null)
				&& obtemCasa(linha, coluna).getPeca().getCor().equals("branca"))
			pecasBrancas.remove((PecaDeXadrez) (obtemCasa(linha, coluna).getPeca()));

		obtemBotaoCasa(linha, coluna).setIcon(peca.getCasa().getBotao().getIcon());
		obtemCasa(linha, coluna).setPeca(peca);
		obtemCasa(linha, coluna).getPeca().foiMovida = true;
	}

	void removePeca(int linha, int coluna) {
		obtemCasa(linha, coluna).setPeca(null);
		obtemBotaoCasa(linha, coluna).setIcon(null);
	}


	

	void trataCliqueSobreUmaCasa(int linha, int coluna) throws IOException {
		if(!ehSuaVez()) {
			JOptionPane.showMessageDialog(null, "N„o È a sua vez.");
		}else{
			if (obtemCasa(linha, coluna).getBotao().getBackground() == casaBranca
					|| obtemCasa(linha, coluna).getBotao().getBackground() == casaPreta) {
				if (obtemCasa(linha, coluna).getPeca() != null) {
					if (ehSuaPeca(obtemCasa(linha, coluna).getPeca().getCor())) {
						pintaAsCasas();
						pecaClicada = (PecaDeXadrez) obtemCasa(linha, coluna).getPeca();
						linhaClicada = linha;
						colunaClicada = coluna;
						corClicada = obtemCasa(linha, coluna).getPeca().getCor();
						pecaClicada.movimentosValidos();
					} else {
						JOptionPane.showMessageDialog(null, "N„o È a sua peÁa.");
					}
				}
			} else {

				if (estaEmCheque(corClicada)) {
					JOptionPane.showMessageDialog(null, "Essa jogada deixar√° o Rei em Cheque.");
				}else{
					Peca x = (Peca) pecaClicada;
					try{
						out.cliente.enviaMovimento(linha,coluna,x.getCasa().getLinha(),x.getCasa().getColuna(),
                                obtemCasa(linha, coluna).getBotao().getBackground());
						
						//
						
						
					}catch (IOException e){
						System.out.print("xx");
						e.printStackTrace();
					}

				}


			}
		}


	}

	public void  movePecas(int linha, int coluna,int linhaAntiga, int colunaAntiga,Color cor ){

		System.out.println(jogador + "a");
		PecaDeXadrez pecaClicada = (PecaDeXadrez) obtemCasa(linhaAntiga,colunaAntiga).getPeca();
		System.out.println(pecaClicada instanceof Peao);

        System.out.println(cor);
		if(movimentoNormal.equals(cor)) {
			movePeca(pecaClicada, linha, coluna);
		}else if(peaoMovDuasCasas.equals(cor)){
		    System.out.println("x");
		    peaoMoveDuasCasas(pecaClicada, linha, coluna);
        }else if (peaoEnPassant.equals(cor)) {
			peaoEnPassant(pecaClicada, linha, coluna);
		}

		else if (peaoPromocao.equals(cor)) {
			String[] pecas = { "Rainha", "Cavalo", "Torre", "Bispo" };
			String pecaEscolhida = (String) JOptionPane.showInputDialog(null, "Escolha a pe√ßa desejada:",
					"Promo√ß√£o", JOptionPane.PLAIN_MESSAGE, null, pecas, pecas[0]);
			if (((Peca) pecaClicada).getCor().equals("preta")) {
				if (pecaEscolhida.equals("Rainha"))
					peaoPromocao(pecaClicada, linha, coluna, new Rainha("preta"),
							new ImageIcon("src/imagens/pecas/pretas/rainha.png", "").getImage().getScaledInstance(35,
									50, java.awt.Image.SCALE_SMOOTH));
				else if (pecaEscolhida.equals("Cavalo"))
					peaoPromocao(pecaClicada, linha, coluna, new Cavalo("preta"),
							new ImageIcon("src/imagens/pecas/pretas/cavalo.png", "").getImage().getScaledInstance(40,
									55, java.awt.Image.SCALE_SMOOTH));
				else if (pecaEscolhida.equals("Torre"))
					peaoPromocao(pecaClicada, linha, coluna, new Torre("preta"),
							new ImageIcon("src/imagens/pecas/pretas/torre.png", "").getImage().getScaledInstance(45, 50,
									java.awt.Image.SCALE_SMOOTH));
				else
					peaoPromocao(pecaClicada, linha, coluna, new Bispo("preta"),
							new ImageIcon("src/imagens/pecas/pretas/bispo.png", "").getImage().getScaledInstance(40, 58,
									java.awt.Image.SCALE_SMOOTH));
				pecasPretas.add((PecaDeXadrez) obtemCasa(linha, coluna).getPeca());
			} else {
				if (pecaEscolhida.equals("Rainha"))
					peaoPromocao(pecaClicada, linha, coluna, new Rainha("branca"),
							new ImageIcon("src/imagens/pecas/brancas/rainha.png", "").getImage().getScaledInstance(35,
									50, java.awt.Image.SCALE_SMOOTH));
				else if (pecaEscolhida.equals("Cavalo"))
					peaoPromocao(pecaClicada, linha, coluna, new Cavalo("branca"),
							new ImageIcon("src/imagens/pecas/brancas/cavalo.png", "").getImage().getScaledInstance(40,
									55, java.awt.Image.SCALE_SMOOTH));
				else if (pecaEscolhida.equals("Torre"))
					peaoPromocao(pecaClicada, linha, coluna, new Torre("branca"),
							new ImageIcon("src/imagens/pecas/brancas/torre.png", "").getImage().getScaledInstance(45,
									50, java.awt.Image.SCALE_SMOOTH));
				else
					peaoPromocao(pecaClicada, linha, coluna, new Bispo("branca"),
							new ImageIcon("src/imagens/pecas/brancas/bispo.png", "").getImage().getScaledInstance(40,
									58, java.awt.Image.SCALE_SMOOTH));
				pecasBrancas.add((PecaDeXadrez) obtemCasa(linha, coluna).getPeca());
			}
		}
		else if (reiRoque.equals(cor)) {
				reiRoque(pecaClicada, linha, coluna);
		}

		ehSuaVez= !ehSuaVez;
		pintaAsCasas();

	}

}
