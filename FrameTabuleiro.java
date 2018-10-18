import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

class FrameTabuleiro extends JFrame implements ActionListener {

	static final long serialVersionUID = 1L;

	// imagens das pecas pretas
	private Image peaoPreto = new ImageIcon("imagens/pecas/pretas/peao.png", "")
			.getImage().getScaledInstance(30, 45, java.awt.Image.SCALE_SMOOTH);
	private Image torrePreta = new ImageIcon("imagens/pecas/pretas/torre.png", "")
			.getImage().getScaledInstance(45, 50, java.awt.Image.SCALE_SMOOTH);
	private Image cavaloPreto = new ImageIcon("imagens/pecas/pretas/cavalo.png", "")
			.getImage().getScaledInstance(40, 55, java.awt.Image.SCALE_SMOOTH);
	private Image bispoPreto = new ImageIcon("imagens/pecas/pretas/bispo.png", "")
			.getImage().getScaledInstance(40, 58, java.awt.Image.SCALE_SMOOTH);
	private Image rainhaPreta = new ImageIcon("imagens/pecas/pretas/rainha.png", "")
			.getImage().getScaledInstance(35, 50, java.awt.Image.SCALE_SMOOTH);
	private Image reiPreto = new ImageIcon("imagens/pecas/pretas/rei.png", "")
			.getImage().getScaledInstance(40, 60, java.awt.Image.SCALE_SMOOTH);

	// imagens das pecas brancas
	private Image peaoBranco = new ImageIcon("imagens/pecas/brancas/peao.png", "")
			.getImage().getScaledInstance(30, 45, java.awt.Image.SCALE_SMOOTH);
	private Image torreBranca = new ImageIcon("imagens/pecas/brancas/torre.png", "")
			.getImage().getScaledInstance(45, 50, java.awt.Image.SCALE_SMOOTH);
	private Image cavaloBranco = new ImageIcon("imagens/pecas/brancas/cavalo.png", "")
			.getImage().getScaledInstance(40, 55, java.awt.Image.SCALE_SMOOTH);
	private Image bispoBranco = new ImageIcon("imagens/pecas/brancas/bispo.png", "")
			.getImage().getScaledInstance(40, 58, java.awt.Image.SCALE_SMOOTH);
	private Image rainhaBranca = new ImageIcon("imagens/pecas/brancas/rainha.png", "")
			.getImage().getScaledInstance(35, 50, java.awt.Image.SCALE_SMOOTH);
	private Image reiBranco = new ImageIcon("imagens/pecas/brancas/rei.png", "")
			.getImage().getScaledInstance(40, 60, java.awt.Image.SCALE_SMOOTH);

	private GridLayout gerenciadorDeLayout = new GridLayout(8, 8);

	private static final String TEXTO_BARRA_SUPERIOR = "Xadrez - EP redes";
	public Tabuleiro tabuleiro ;
	public Client cliente;
	
	FrameTabuleiro(String jogador, Client client) {
		this.cliente = client;
		super.setTitle(TEXTO_BARRA_SUPERIOR);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.montaTabuleiro(jogador);
		
		Cronometro c1 = new Cronometro(5);
		Cronometro c2 = new Cronometro(6);
		
		c1.init("Jogador 1", 1);
		c2.init("Jogador 2", 2);
		c1.iniciarCronometro();
	
		
		super.pack();
		super.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] coordenada = e.getActionCommand().split(",");
		int linha = Integer.parseInt(coordenada[0]);
		int coluna = Integer.parseInt(coordenada[1]);
		try {
			tabuleiro.trataCliqueSobreUmaCasa(linha,coluna);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	void montaTabuleiro(String jogador) {
		tabuleiro =  Tabuleiro.criaTabuleiro(jogador,this);
		tabuleiro.criaEPintaAsCasas();
		JPanel painelTabuleiro = new JPanel();
		painelTabuleiro.setLayout(gerenciadorDeLayout);

		// adiciona as casas
		for (int linha = 0; linha < 8; linha++) {
			for (int coluna = 0; coluna < 8; coluna++) {
				painelTabuleiro.add(tabuleiro.obtemBotaoCasa(linha, coluna));
				tabuleiro.obtemBotaoCasa(linha, coluna).addActionListener(this);
				tabuleiro.obtemBotaoCasa(linha, coluna).setActionCommand(linha + "," + coluna);
			}
		}

		// adiciona as pecas pretas
		tabuleiro.obtemBotaoCasa(0,0).setIcon(new ImageIcon(torrePreta));
		tabuleiro.obtemBotaoCasa(0,1).setIcon(new ImageIcon(cavaloPreto));
		tabuleiro.obtemBotaoCasa(0,2).setIcon(new ImageIcon(bispoPreto));
		tabuleiro.obtemBotaoCasa(0,3).setIcon(new ImageIcon(rainhaPreta));
		tabuleiro.obtemBotaoCasa(0,4).setIcon(new ImageIcon(reiPreto));
		tabuleiro.obtemBotaoCasa(0,5).setIcon(new ImageIcon(bispoPreto));
		tabuleiro.obtemBotaoCasa(0,6).setIcon(new ImageIcon(cavaloPreto));
		tabuleiro.obtemBotaoCasa(0,7).setIcon(new ImageIcon(torrePreta));

		for (int i = 0; i < 8; i++) {
			tabuleiro.obtemBotaoCasa(1,i).setIcon(new ImageIcon(peaoPreto));
		}

		// adiciona as pecas brancas
		tabuleiro.obtemBotaoCasa(7,0).setIcon(new ImageIcon(torreBranca));
		tabuleiro.obtemBotaoCasa(7,1).setIcon(new ImageIcon(cavaloBranco));
		tabuleiro.obtemBotaoCasa(7,2).setIcon(new ImageIcon(bispoBranco));
		tabuleiro.obtemBotaoCasa(7,3).setIcon(new ImageIcon(rainhaBranca));
		tabuleiro.obtemBotaoCasa(7,4).setIcon(new ImageIcon(reiBranco));
		tabuleiro.obtemBotaoCasa(7,5).setIcon(new ImageIcon(bispoBranco));
		tabuleiro.obtemBotaoCasa(7,6).setIcon(new ImageIcon(cavaloBranco));
		tabuleiro.obtemBotaoCasa(7,7).setIcon(new ImageIcon(torreBranca));

		for (int i = 0; i < 8; i++) {
			tabuleiro.obtemBotaoCasa(6,i).setIcon(new ImageIcon(peaoBranco));
		}

		super.getContentPane().add(painelTabuleiro);
	}
	/*
	public static void main(String[] args) throws Exception {
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		FrameTabuleiro frame = new FrameTabuleiro("verde");
		
		// para mover a peca: 
		//     - copia a peca para a casa final:  jButtonCasaFinal.setIcon(jButtonCasaInicial.getIcon()); 
		//     - apaga a peca na casa inicial:    jButtonCasaInicial.setIcon(null);
		// para pinta uma casa de amarelo:        jButton.setBackground(Color.YELLOW);
		// para mostrar mensagem:                 JOptionPane.showMessageDialog(null, "mensagem de erro");
	}*/
}
