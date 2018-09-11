package pacote;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import mdmatrakas.compGrafica.model.Janela;
import mdmatrakas.compGrafica.model.Modelo;
import mdmatrakas.compGrafica.model.ModeloFactory;
import mdmatrakas.compGrafica.model.Ponto2D;
import mdmatrakas.compGrafica.model.Universo2D;
import mdmatrakas.compGrafica.transformacao.Transformacao;
import mdmatrakas.compGrafica.util.XmlService;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FrameComputacaoGrafica extends JFrame {
	private static final long serialVersionUID = 1L;

	private DrawingPanel drawPanel;
	private JPanel status;
	private JLabel statusText;
	private JToolBar toolBar;
	private JButton btnPonto;
	private JButton btnLinha;
	private JButton btnRetangulo;
	private JButton btnTriangulo;

	private Documento doc;
	private JMenuBar menuBar;
	private JMenu mnArquivo;
	private JMenuItem mntmAbrir;
	private JMenuItem mntmSalvar;
	private JMenuItem mntmSair;
	private JMenuItem mntmNovo;
	private JButton btnBezier;
	private JButton btnQuadrilatero;
	private JMenu mnModelo;
	private JMenuItem mntmEscala;
	private JMenuItem mntmTranslao;
	private JMenuItem mntmReflexo;
	private JMenuItem mntmRotao;
	private JMenuItem mntmCisalhamento;
	private JMenuItem mntmIdentidade;

	public FrameComputacaoGrafica(Documento doc) {
		this.doc = doc;

		drawPanel = new DrawingPanel();
		drawPanel.setDocumento(doc);

		status = new JPanel();
		statusText = new JLabel("...");

		status.setLayout(new FlowLayout(FlowLayout.LEADING));
		status.add(statusText);

		getContentPane().add(drawPanel, BorderLayout.CENTER);
		getContentPane().add(status, BorderLayout.SOUTH);

		toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);

		btnPonto = new JButton("Ponto");
		btnPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Modelo m = ModeloFactory.novoModelo(ModeloFactory.PONTO);
				getDoc().novoModelo(m);
			}
		});
		toolBar.add(btnPonto);

		btnLinha = new JButton("Linha");
		btnLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modelo m = ModeloFactory.novoModelo(ModeloFactory.LINHA);
				getDoc().novoModelo(m);
			}
		});
		toolBar.add(btnLinha);

		btnRetangulo = new JButton("Retangulo");
		btnRetangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modelo m = ModeloFactory.novoModelo(ModeloFactory.RETANGULO);
				getDoc().novoModelo(m);
			}
		});
		toolBar.add(btnRetangulo);

		btnTriangulo = new JButton("Triangulo");
		btnTriangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modelo m = ModeloFactory.novoModelo(ModeloFactory.TRIANGULO);
				getDoc().novoModelo(m);
			}
		});
		toolBar.add(btnTriangulo);

		btnQuadrilatero = new JButton("Quadrilatero");
		btnQuadrilatero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modelo m = ModeloFactory.novoModelo(ModeloFactory.QUADRILATERO);
				getDoc().novoModelo(m);
			}
		});
		toolBar.add(btnQuadrilatero);

		btnBezier = new JButton("Bezier");
		btnBezier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modelo m = ModeloFactory.novoModelo(ModeloFactory.BEZIER);
				getDoc().novoModelo(m);
			}
		});
		toolBar.add(btnBezier);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File(System.getProperty("user.home")));

//				FileNameExtensionFilter cgFilter = new FileNameExtensionFilter("Arquivo Computação Gráfica", "cg");
//				fc.addChoosableFileFilter(cgFilter);
//				FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Arquivo XML", "xml");
//				fc.addChoosableFileFilter(xmlFilter);
//				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Arquivo de texto", "txt");
//				fc.setFileFilter(txtFilter);

				int result = fc.showOpenDialog(FrameComputacaoGrafica.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					getDoc().setUniverso((Universo2D) XmlService.unmarshal(Universo2D.class, f));
				}
			}
		});

		mntmNovo = new JMenuItem("Novo");
		mntmNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Universo2D universo = new Universo2D();
				universo.setInicio(new Ponto2D(-5, -5));
				universo.setFim(new Ponto2D(5, 5));
				universo.setUnidade("Milimetro");
				universo.setJanela(new Janela(new Ponto2D(-4, -4), new Ponto2D(3, 3)));

				getDoc().setUniverso(universo);
			}
		});
		mnArquivo.add(mntmNovo);
		mnArquivo.add(mntmAbrir);

		mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File(System.getProperty("user.home")));

//				FileNameExtensionFilter cgFilter = new FileNameExtensionFilter("Arquivo Computação Gráfica", "cg");
//				fc.addChoosableFileFilter(cgFilter);
//				FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Arquivo XML", "xml");
//				fc.addChoosableFileFilter(xmlFilter);
//				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Arquivo de texto", "txt");
//				fc.setFileFilter(txtFilter);

				int result = fc.showSaveDialog(FrameComputacaoGrafica.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					// System.out.println(getDoc().getUniverso());
					XmlService.marshal(getDoc().getUniverso(), f);
				}
			}
		});
		mnArquivo.add(mntmSalvar);

		mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArquivo.add(mntmSair);

		mnModelo = new JMenu("Modelo");
		menuBar.add(mnModelo);

		mntmTranslao = new JMenuItem("Translação");
		mntmTranslao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogSelecionarModelo dlg = new DialogSelecionarModelo();
				dlg.setVisible(true);
				if (dlg.getRespCode() == DialogSelecionarModelo.RESP_OK) {
					Modelo m = dlg.getSelectedModel();
					if (m != null) {
						DialogLerValores ler = new DialogLerValores();
						ler.setName("Translação");
						ler.setLabelXName("Translação em X:");
						ler.setLabelYName("Translação em Y:");
						ler.setVisible(true);
						System.out.println(ler.getRespCode());
						if (ler.getRespCode() == DialogLerValores.RESP_OK) {
							Transformacao t = Transformacao.getTranslacao(ler.getFieldX(), ler.getFieldY());
							if (ler.getDefinir())
								m.setTransformacao(t);
							else
								m.comporTransformacao(t);
						}
					}
				}
			}
		});

		mntmIdentidade = new JMenuItem("Identidade");
		mntmIdentidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogSelecionarModelo dlg = new DialogSelecionarModelo();
				dlg.setVisible(true);
				if (dlg.getRespCode() == DialogSelecionarModelo.RESP_OK) {
					Modelo m = dlg.getSelectedModel();
					if (m != null) {
						Transformacao t = Transformacao.getIdentidade();
						m.setTransformacao(t);
					}
				}
			}
		});
		mnModelo.add(mntmIdentidade);
		mnModelo.add(mntmTranslao);

		mntmEscala = new JMenuItem("Escala");
		mntmEscala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogSelecionarModelo dlg = new DialogSelecionarModelo();
				dlg.setVisible(true);
				if (dlg.getRespCode() == DialogSelecionarModelo.RESP_OK) {
					Modelo m = dlg.getSelectedModel();
					if (m != null) {
						DialogLerValores ler = new DialogLerValores();
						ler.setName("Escala");
						ler.setLabelXName("Escala em X:");
						ler.setLabelYName("Escala em Y:");
						ler.setVisible(true);
						if (ler.getRespCode() == DialogLerValores.RESP_OK) {
							Transformacao t = Transformacao.getEscala(ler.getFieldX(), ler.getFieldY());
							if (ler.getDefinir())
								m.setTransformacao(t);
							else
								m.comporTransformacao(t);
						}
					}
				}
			}
		});
		mnModelo.add(mntmEscala);

		mntmReflexo = new JMenuItem("Reflexão");
		mntmReflexo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogSelecionarModelo dlg = new DialogSelecionarModelo();
				dlg.setVisible(true);
				if (dlg.getRespCode() == DialogSelecionarModelo.RESP_OK) {
					Modelo m = dlg.getSelectedModel();
					if (m != null) {
						DialogLerValores ler = new DialogLerValores();
						ler.setName("Reflexão");
						ler.setLabelXName("Refletir em X [0/1] :");
						ler.setLabelYName("Refletir em Y [0/1] :");
						ler.setVisible(true);
						if (ler.getRespCode() == DialogLerValores.RESP_OK) {
							Transformacao t = Transformacao.getReflexao(ler.getFieldX() == 0 ? false : true,
									ler.getFieldY() == 0 ? false : true);
							if (ler.getDefinir())
								m.setTransformacao(t);
							else
								m.comporTransformacao(t);
						}
					}
				}
			}
		});
		mnModelo.add(mntmReflexo);

		mntmRotao = new JMenuItem("Rotação");
		mntmRotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogSelecionarModelo dlg = new DialogSelecionarModelo();
				dlg.setVisible(true);
				if (dlg.getRespCode() == DialogSelecionarModelo.RESP_OK) {
					Modelo m = dlg.getSelectedModel();
					if (m != null) {
						DialogLerValores ler = new DialogLerValores();
						ler.setName("Rotação");
						ler.setLabelXName("Valor do ângulo (graus):");
						ler.setLabelYName("Ou -> Valor do ângulo (radianos):");
						ler.setVisible(true);
						if (ler.getRespCode() == DialogLerValores.RESP_OK) {
							Transformacao t;
							if (ler.getFieldX() == 0 && ler.getFieldY() != 0)
								t = Transformacao.getRotacao(ler.getFieldY());
							else
								t = Transformacao.getRotacaoGraus(ler.getFieldX());
							if (ler.getDefinir())
								m.setTransformacao(t);
							else
								m.comporTransformacao(t);
						}
					}
				}
			}
		});
		mnModelo.add(mntmRotao);

		mntmCisalhamento = new JMenuItem("Cisalhamento");
		mntmCisalhamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogSelecionarModelo dlg = new DialogSelecionarModelo();
				dlg.setVisible(true);
				if (dlg.getRespCode() == DialogSelecionarModelo.RESP_OK) {
					Modelo m = dlg.getSelectedModel();
					if (m != null) {
						DialogLerValores ler = new DialogLerValores();
						ler.setName("Cisalhamento");
						ler.setLabelXName("Cisalhamento em X:");
						ler.setLabelYName("Cisalhamento em Y:");
						ler.setVisible(true);
						if (ler.getRespCode() == DialogLerValores.RESP_OK) {
							Transformacao t = Transformacao.getCisalhamento(ler.getFieldX(), ler.getFieldY());
							if (ler.getDefinir())
								m.setTransformacao(t);
							else
								m.comporTransformacao(t);
						}
					}
				}
			}
		});
		mnModelo.add(mntmCisalhamento);
	}

	private Documento getDoc() {
		return doc;
	}

	public void setStatusText(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				statusText.setText(text);
			}
		});
	}

}
