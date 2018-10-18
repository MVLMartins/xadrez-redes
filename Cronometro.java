import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cronometro {
	
	private JLabel contagemTempo;
	private Timer tm;
	public int contador;

	private boolean ativo = false;

	public boolean estaAtivo(){
		return ativo;
	}
	
	
	public Cronometro(int contador) {
		super();
		this.contador = contador*60;
	}


	public void init(String nomeJanela, int tipo) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame janela = new JFrame(nomeJanela);
		janela.setSize(300, 200);
		janela.setAlwaysOnTop(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLayout(new BorderLayout());
		
		contagemTempo = new JLabel("05:00:00"); 
		contagemTempo.setFont(new Font(contagemTempo.getName(), Font.PLAIN, 80));
		janela.add(contagemTempo, BorderLayout.CENTER);
		
		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(2,1));
		
		janela.add(painel, BorderLayout.WEST);
		janela.pack();
		
		if (tipo == 1) {
			java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	        int componentWidth = janela.getWidth();
	        int componentHeight = janela.getHeight();
	        janela.setBounds(((screenSize.width-componentWidth)/(16/10)), ((screenSize.height-componentHeight)/(31/10)), componentWidth, componentHeight);
		}else {
			java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	        int componentWidth = janela.getWidth();
	        int componentHeight = janela.getHeight();
	        janela.setBounds(((screenSize.width-componentWidth)/(16/10)), (screenSize.height-componentHeight)/2, componentWidth, componentHeight);
		}
		
		
		janela.setVisible(true);
	}
	
	
	 public static void centerContainer(Container container) {
	        
	    }
	
	 
	public void iniciarCronometro() {
		ativo = true;
		tm = new Timer();
		tm.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				contador--;
				int seg = contador % 60;
				int min = contador / 60;
				int hora = min / 60;
				min %= 60;
				contagemTempo.setText(String.format("%02d:%02d:%02d", hora, min, seg));
			}
		}, 1000,1000);
	}
	
	public void pararCronometro() {
		tm.cancel();
		ativo = false;
	}
	
}
