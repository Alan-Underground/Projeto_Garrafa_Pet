package Pacote;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class verificaGarrafa {

	public void verificaImg() {
		BufferedImage image;

		String str = "C:\\Users\\alan_\\Pictures\\Trabalho_PET\\IMG1.jpg";
		try {
			image = ImageIO.read(new File(str));
			ImageIcon icon = new ImageIcon(image);
			JLabel imageLabel = new JLabel(icon);
			JFrame frame = new JFrame();
			Container contentPane = frame.getContentPane();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
			frame.setSize(600, 400);
			frame.setVisible(true); //aparece a imagem que estou lendo

			int width = image.getWidth(); //pega largura da imagem da garrafa original.
			int height = image.getHeight(); //pega altura da imagem da garrafa original.
			frame.setTitle("Altura: " + height + "  x  Largura: " + width); //Dando nome a janela de apresentação da imagem.
			int nbands = image.getSampleModel().getNumBands();
			Raster inputRaster = image.getData();
			int[] pixels = new int[nbands * width * height];
			inputRaster.getPixels(0, 0, width, height, pixels);
			
			//Declarando Red Green e Blue para depois verificar a frequência individualmente.
			int[][] r = new int[width][height]; 
			int[][] g = new int[width][height];
			int[][] b = new int[width][height];
			
			Color rgb = null;

			for (int w = 0; w < width; w++) {           //Começa a varrer a matriz linha 
				for (int h = 0; h < height; h++) {		//por coluna

					rgb = new Color(image.getRGB(w, h)); //w e h são as posições da matriz

					r[w][h] = rgb.getRed();  //capturando frequência de vermelho na posição w h
					g[w][h] = rgb.getGreen();// capturando frequência de Verde na posição w h
					b[w][h] = rgb.getBlue(); //capturando ferquência de Azul na posição w h
					
				}
			}
			BufferedImage imagemCinza = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB); //criando nova imagem
			int[][] corLista = new int[width][height];
			int[][] imagemComFiltro = new int[width][height];
			int[][] imagemSemFiltro = new int[width][height];
			for (int i = 0; i <= image.getWidth()  -1; i++) {
				for (int j = 0; j <= image.getHeight() - 1; j++) {
					int vermelho = r[i][j];
					int verde = g[i][j];
					int azul = b[i][j];
					int cor = (vermelho + verde + azul) / 3;
					imagemCinza.setRGB(i, j, new Color(cor, cor, cor).getRGB());
					corLista[i][j] = cor;
				}
			}

			ImageIO.write(imagemCinza, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\IMG2.jpg")); //Salvando no diretorio
			
		} catch (IOException ex) {
		}

	}


	
	
	
	public void convolucao() {
		BufferedImage image;

		String str = "C:\\Users\\alan_\\Pictures\\Trabalho_PET\\IMG2.jpg";
		try {
			image = ImageIO.read(new File(str));
			ImageIcon icon = new ImageIcon(image);
			JLabel imageLabel = new JLabel(icon);
			JFrame frame = new JFrame();
			Container contentPane = frame.getContentPane();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
			frame.setSize(600, 400);
			frame.setVisible(true); //aparece a imagem que estou lendo

			int width = image.getWidth(); //pega largura da imagem da garrafa original.
			int height = image.getHeight(); //pega altura da imagem da garrafa original.
			frame.setTitle("Altura: " + height + "  x  Largura: " + width); //Dando nome a janela de apresentação da imagem.
			int nbands = image.getSampleModel().getNumBands();
			Raster inputRaster = image.getData();
			int[] pixels = new int[nbands * width * height];
			inputRaster.getPixels(0, 0, width, height, pixels);

			//Declarando Red Green e Blue para depois verificar a frequência individualmente.
			int[][] r = new int[width][height]; 
			int[][] g = new int[width][height];
			int[][] b = new int[width][height];
			


			Color rgb = null;

			for (int w = 0; w < width; w++) {           //Começa a varrer a matriz linha 
				for (int h = 0; h < height; h++) {		//por coluna

					rgb = new Color(image.getRGB(w, h)); //w e h são as posições da matriz

					r[w][h] = rgb.getRed();  //capturando frequência de vermelho na posição w h
					g[w][h] = rgb.getGreen();// capturando frequência de Verde na posição w h
					b[w][h] = rgb.getBlue(); //capturando ferquência de Azul na posição w h
					
				}
			}
			BufferedImage imagemCinza = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB); //criando nova imagem
			int[][] corLista = new int[width][height];

			for (int i = 0; i <= image.getWidth()  -1; i++) {
				for (int j = 0; j <= image.getHeight() - 1; j++) {
					int vermelho = r[i][j];
					int verde = g[i][j];
					int azul = b[i][j];
					int cor = (vermelho + verde + azul) / 3;
					imagemCinza.setRGB(i, j, new Color(cor, cor, cor).getRGB());
					corLista[i][j] = cor;
				}
			}
			
			
			int[][] imagemSemFiltro;
			int[][] imagemSemFiltro2;
			int[][] mat = { { 0, -1, 0 }, { -1, 4, -1 }, { 0, -1, 0 } };
			int[][] imagemComFiltro = funcConvolucao(mat, 3, image.getWidth(), image.getHeight(), corLista);
			int[][] imagemFiltro= new int[image.getWidth()][ image.getHeight()];
			BufferedImage imagemFiltroRgb = new BufferedImage(image.getWidth(),image.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			BufferedImage imagemTeste = new BufferedImage(image.getWidth(),image.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			
			BufferedImage imagemFiltroRgb2 = new BufferedImage(image.getWidth(),image.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			BufferedImage imagemTeste2 = new BufferedImage(image.getWidth(),image.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			
			for (int i = 0; i <=  image.getWidth()-1; i++) {
				for (int j = 0; j <= image.getHeight()-1; j++) {
					int cor = imagemComFiltro[i][j];
					int soma = (int) Math.pow(cor, 2);
					if(soma > 255)
						soma = 255;
					if(soma < 0)
						soma = 0;
					imagemFiltro[i][j] = soma;
					imagemFiltroRgb.setRGB(i, j, new Color(cor, cor, cor).getRGB());
					imagemTeste.setRGB(i, j,new Color(soma,soma,soma).getRGB());
					
					
			mat = new int[][] { { 1 , 1, 1, 1,1 }, {1,1, 1, 1, 1 }, { 1,1,1, 1, 1 }, { 1,1,1, 1, 1 }, { 1,1,1, 1, 1 } };
			imagemFiltro = media(mat, 5, image.getWidth(), image.getHeight(), imagemFiltro);
	
			
			
		//	boolean resp = validaGarrafa(imagemFiltro,imagemFiltro2,image.getWidth(), image.getHeight());
			
			
				ImageIO.write(imagemFiltroRgb, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\realceDeBordasIdeal.jpg"));		
				ImageIO.write(imagemTeste, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\mediaimagemIdeal.jpg"));
			
				ImageIO.write(imagemFiltroRgb2, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\ralceDeBordasTeste.jpg"));		
				ImageIO.write(imagemTeste2, "jpg", new File("CC:\\Users\\alan_\\Pictures\\Trabalho_PET\\mediaimagemTeste.jpg"));

		
			
				
				ImageIO.write(imagemCinza, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\IMG3.jpg")); //Salvando no diretorio
				} 
			}
		}catch (IOException ex) {
				}

			}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int[][] media(int[][] mat, int tamanhoMat, int Width, int Height, int[][] imagemSemFiltro) {
		int[][] imagemComFiltro = new int[Width][Height];

		int meio = (tamanhoMat - 1) / 2;
		int largura = Width - (1 + meio);
		int altura = Height - (1 + meio);
		
		
		
		for (int i = meio; i <= largura; i++) {
			for (int j = meio; j <= altura; j++) {
				int[] dados = new int[tamanhoMat * tamanhoMat];
				int k = 0;
				for (int l = 0; l < tamanhoMat; l++) {
					for (int m = 0; m < tamanhoMat; m++) {
						if (mat[l][m] != 0) {
							dados[k] = mat[l][m] * imagemSemFiltro[i + (l - meio)][j + (m - meio)];
							k++;
						}
					}

				}
				int total = 0;
				for (int o = 0; o < tamanhoMat * tamanhoMat; o++) {
					total += dados[o];
					total = total/25;
				}
				if(total > 255)
					total = 255;
				if(total < 0)
					total = 0;
				imagemComFiltro[i][j] = Math.abs(total);

			}
		}
		
		return imagemComFiltro;
	}
	
	
	
	
	
	
	
	
	
	
	public int[][] funcConvolucao(int[][] mat, int tamanhoMat, int Width, int Height, int[][] imagemSemFiltro) {
		int[][] imagemComFiltro = new int[Width][Height];

		int meio = (tamanhoMat - 1) / 2;
		int largura = Width - (1 + meio);
		int altura = Height - (1 + meio);
		
		
		
		for (int i = meio; i <= largura; i++) {
			for (int j = meio; j <= altura; j++) {
				int[] dados = new int[tamanhoMat * tamanhoMat];
				int k = 0;
				for (int l = 0; l < tamanhoMat; l++) {
					for (int m = 0; m < tamanhoMat; m++) {
						if (mat[l][m] != 0) {
							dados[k] = mat[l][m] * imagemSemFiltro[i + (l - meio)][j + (m - meio)];
							k++;
						}
					}

				}
				int total = 0;
				for (int o = 0; o < tamanhoMat * tamanhoMat; o++) {
					total += dados[o];
				}
				if(total > 255)
					total = 255;
				if(total < 0)
					total = 0;
				imagemComFiltro[i][j] = Math.abs(total);

			}
		}
		
		return imagemComFiltro;
	}











}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*

	public int[][] funcConvolucao(int[][] mat,int tamanhoMat, int Width, int Height, int[][] imagemSemFiltro) {
		
		BufferedImage image;

		String str = "C:\\Users\\alan_\\Pictures\\Trabalho_PET\\IMG2.jpg";
		
		int[][] imagemComFiltro = new int[Width][Height];
			//int [][] parteDeCima = imagemSemFiltro[i][j];
	//	int width = image.getWidth(); //pega largura da imagem da Imagem Cinza.
	//	int height = image.getHeight(); //pega altura da imagem da Imagem Cinza.
			
	//		for (int w = 0; w < width; w++) {           //Começa a varrer a matriz linha 
	//			for (int h = 0; h < height; h++) {		//por coluna

					//rgb = new Color(image.getColor(w)); //w e h são as posições da matriz

					//r[w][h] = rgb.getRed();  //capturando frequência de vermelho na posição w h
					//g[w][h] = rgb.getGreen();// capturando frequência de Verde na posição w h
					//b[w][h] = rgb.getBlue(); //capturando ferquência de Azul na posição w h
					

					/*image.getRaster(); //obtendo pixel  - raster é para gravação de pixel   não precisa disso. */
		//		}
		//	}
		
			
			
			
			
		/*for (int i = 0; i <= Width - 1; i++) {
			for (int j = 0; j <= Height - 1; j++) {
				int[] dados = new int[9];
				int k = 0;
				for (int l = 0; l < tamanhoMat; l++) {
					for (int m = 0; m < tamanhoMat; m++) {
						if (mat[l][m] != 0) {
							dados[k] = mat[l][m] * imagemSemFiltro[i+(l-meio)][j+(l-meio)];
							k++;
							// arrumar a borda e deposi comecar a implementar.
						}
					}

				}

			}
		}*/
