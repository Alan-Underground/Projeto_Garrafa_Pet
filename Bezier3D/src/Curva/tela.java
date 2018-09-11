package Curva;

import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class tela extends JFrame {

	public class vetPosUltimo {
		public vetPosUltimo(int[] xM, int[] yM, int[] zM) {
			this.xM = xM;
			this.yM = yM;
			this.zM = zM;
		}

		int[] xM = new int[100];
		int[] yM = new int[100];
		int[] zM = new int[100];

	}

	private JPanel contentPane;
	private JTextField textoX1; //Recebe texto do teclado x1
	private JTextField textoY1;	//Recebe texto do teclado y1
	private JTextField textoZ1;	
	private JTextField textoZ2; 
	private JTextField textoY2; 
	private JTextField textoX2;
	private JTextField textoX3;
	private JTextField textoY3;
	private JTextField textoZ3;
	private int[] xT = new int[100]; //dados calculados no calcula Bezier
	private int[] yT = new int[100];
	private int[] zT = new int[100];
	private int X0, Y0, Z0 = 0;

	int[][] listX= new int[3][100]; // gerar mais curvas
	int[][] listY= new int[3][100];
	int[][] listZ= new int[3][100];
	int[][] listXrotacionados = new int[3][100];
	int[][] listYrotacionados = new int[3][100];
	int contador = 0;
	int andarX = 0;
	int andarY = 0;
	static private int grausdex = 0; //começa em 0 para rotacinar soma os valores  para rotacionar
	static private int grausdey = 0;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tela frame = new tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	static public int getGrausdex() {
		return grausdex;
	}

	static public int getGrausdey() {
		return grausdey;
	}

	public void calculaBezie(int x1, int y1, int z1, int x2, int y2, int z2, int x3, int y3, int z3, int x4, int y4,
			int z4) {
		float t = 0;

	
		this.xT = new int[100];
		this.yT = new int[100];
		this.zT = new int[100];

		for (int i = 0; i < 100; i++) { //calculo de bezier

			
			int Tx = (int) (somaBezier((1 - t), 3) * x1 + (3 * t) * somaBezier((1 - t), 2) * x2
					+ somaBezier((3 * t), 2) * (1 - t) * x3 + somaBezier(t, 3) * x4);
			int Ty = (int) (somaBezier((1 - t), 3) * y1 + (3 * t) * somaBezier((1 - t), 2) * y2
					+ somaBezier((3 * t), 2) * (1 - t) * y3 + somaBezier(t, 3) * y4);
			int Tz = (int) (somaBezier((1 - t), 3) * z1 + (3 * t) * somaBezier((1 - t), 2) * z2
					+ somaBezier((3 * t), 2) * (1 - t) * z3 + somaBezier(t, 3) * z4);
			t += 0.01;
			this.xT[i] = Tx;
			this.yT[i] = Ty;
			this.zT[i] = Tz;
		}
		this.listX[this.contador]=  xT;// coloca na curva valor X
		this.listY[this.contador]=  yT;
		this.listZ[this.contador]=  zT;
		contador++;
		X0 = xT[99];
		Y0 = yT[99];
		Z0 = zT[99];
	}
	public float somaBezier(float valor, int expoente) {
		float soma = valor;
		for (int i = 0; i < expoente; i++) {
			soma = soma * valor;
		}
		return soma;
	}
	
	public void rotX(int valor){
		grausdex += valor;
	}
	public void rotY(int valor){
		grausdey += valor;
	}

	private void updateJanela(){
		
		rotacionarr(); 
		this.repaint();
	}

	
	public void criaBezier() {
		calculaBezie(X0, Y0, Z0, Integer.parseInt(textoX1.getText()), Integer.parseInt(textoY1.getText()),
				Integer.parseInt(textoZ1.getText()), Integer.parseInt(textoX2.getText()), Integer.parseInt(textoY2.getText()),
				Integer.parseInt(textoZ2.getText()), Integer.parseInt(textoX3.getText()), Integer.parseInt(textoY3.getText()),
				Integer.parseInt(textoZ3.getText()));
		}
	public tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 706);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textoX1 = new JTextField();
		textoX1.setColumns(10);
		textoX1.setBounds(28, 56, 41, 20);
		contentPane.add(textoX1);
		
		
		JTextPane textPane = new JTextPane();
		textPane.setText("X1");
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setBounds(42, 35, 18, 20);
		contentPane.add(textPane);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Y1");
		textPane_1.setEnabled(false);
		textPane_1.setEditable(false);
		textPane_1.setBounds(227, 35, 18, 20);
		contentPane.add(textPane_1);

		textoY1 = new JTextField();
		textoY1.setColumns(10);
		textoY1.setBounds(215, 56, 41, 20);
		contentPane.add(textoY1);

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("Z1");
		textPane_2.setEnabled(false);
		textPane_2.setEditable(false);
		textPane_2.setBounds(408, 35, 18, 20);
		contentPane.add(textPane_2);

		textoZ1 = new JTextField();
		textoZ1.setColumns(10);
		textoZ1.setBounds(398, 56, 41, 20);
		contentPane.add(textoZ1);

		textoZ2 = new JTextField();
		textoZ2.setColumns(10);
		textoZ2.setBounds(449, 56, 41, 20);
		contentPane.add(textoZ2);
		
		JPanel panel = new JPanel(){
			public void paint(Graphics g) {
				for (int i = 0; i < contador; i++) {
					g.drawPolyline(listXrotacionados[i], listYrotacionados[i], 100);
				}
	
			
		}

	};
	panel.setBounds(10, 154, 561, 502);
	contentPane.add(panel);
	
	
	
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("Z2");
		textPane_3.setEnabled(false);
		textPane_3.setEditable(false);
		textPane_3.setBounds(463, 35, 18, 20);
		contentPane.add(textPane_3);

		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("Y2");
		textPane_4.setEnabled(false);
		textPane_4.setEditable(false);
		textPane_4.setBounds(278, 35, 18, 20);
		contentPane.add(textPane_4);

		textoY2 = new JTextField();
		textoY2.setColumns(10);
		textoY2.setBounds(266, 56, 41, 20);
		contentPane.add(textoY2);

		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText("X2");
		textPane_5.setEnabled(false);
		textPane_5.setEditable(false);
		textPane_5.setBounds(91, 35, 18, 20);
		contentPane.add(textPane_5);

		textoX2 = new JTextField();
		textoX2.setColumns(10);
		textoX2.setBounds(81, 56, 41, 20);
		contentPane.add(textoX2);

		textoX3 = new JTextField();
		textoX3.setColumns(10);
		textoX3.setBounds(132, 56, 41, 20);
		contentPane.add(textoX3);

		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText("X3");
		textPane_6.setEnabled(false);
		textPane_6.setEditable(false);
		textPane_6.setBounds(144, 35, 18, 20);
		contentPane.add(textPane_6);

		textoY3 = new JTextField();
		textoY3.setColumns(10);
		textoY3.setBounds(317, 56, 41, 20);
		contentPane.add(textoY3);

		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText("Y3");
		textPane_7.setEnabled(false);
		textPane_7.setEditable(false);
		textPane_7.setBounds(331, 35, 18, 20);
		contentPane.add(textPane_7);

		textoZ3 = new JTextField();
		textoZ3.setColumns(10);
		textoZ3.setBounds(500, 56, 41, 20);
		contentPane.add(textoZ3);

		JTextPane textPane_8 = new JTextPane();
		textPane_8.setText("Z3");
		textPane_8.setEnabled(false);
		textPane_8.setEditable(false);
		textPane_8.setBounds(514, 35, 18, 20);
		contentPane.add(textPane_8);

		JButton cima = new JButton("^\r\n");
		cima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					rotX(10); 
					 
					updateJanela();
			}
		});
		cima.setBounds(204, 105, 41, 33);
		contentPane.add(cima);

		JButton esquerda = new JButton("<\r\n");
		esquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotY(-20);
				
				updateJanela();
			}
		});
		esquerda.setBounds(102, 105, 41, 33);
		contentPane.add(esquerda);

		JButton Baixo = new JButton("v\r\n");
		Baixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotX(-20);
				updateJanela();
		
			}
		});
		Baixo.setBounds(255, 105, 41, 33);
		contentPane.add(Baixo);

		JButton Direita = new JButton(">\r\n");
		Direita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rotY(10);
				updateJanela();
				
			}
		});
		Direita.setBounds(153, 105, 41, 33);
		contentPane.add(Direita);
		
		
		

		JButton criaBezier = new JButton("Cria Bezier");
		criaBezier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criaBezier();
			
				updateJanela();
			}

		});
	
		criaBezier.setBounds(345, 107, 124, 28);
		contentPane.add(criaBezier);	
	}
	
	
	private void rotacionarr() {
		int x = tela.getGrausdex();
		int y = tela.getGrausdey();

		float ys = (float) Math.sin(Math.PI / 180 * y);

		float yc = (float) Math.cos(Math.PI / 180 * y);

		float xs = (float) Math.sin(Math.PI / 180 * x);

		float xc = (float) Math.cos(Math.PI / 180 * x);
	
		for (int i = 0; i < contador; i++) {
			int[] resultadosx = new int[100];
			int[] resultadosy = new int[100];
			for (int j = 0; j < 100; j++) {
				resultadosx[j] = (int) ((yc * listX[i][j] + ys * listZ[i][j]));
				resultadosy[j] = (int) (((ys * xs) * listX[i][j])
						+ (xc * listY[i][j]) + ((-yc * xs) * listZ[i][j]));

			}
			listXrotacionados[i] = resultadosx;
			listYrotacionados[i] = resultadosy;
		
		}
		}

	
}
