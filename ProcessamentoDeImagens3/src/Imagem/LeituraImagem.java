package Imagem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;



public class LeituraImagem {

	public void jButton1ActionPerformed(String nomeArq, String nomefoto) {
		BufferedImage image1;
		BufferedImage image2;
		
//Transforma imagem em cinza
		String str = "C:\\Users\\alan_\\Pictures\\Trabalho_PET\\";
		String garrafaBoa = str.concat(nomeArq);
		String garrafaTeste = str.concat(nomefoto);
		try {
			image1 = ImageIO.read(new File(garrafaBoa));
			image2 = ImageIO.read(new File(garrafaTeste));
			ImageIcon icon = new ImageIcon(image1);
			JLabel imageLabel = new JLabel(icon);
			JFrame frame = new JFrame();
			Container contentPane = frame.getContentPane();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
			frame.setSize(630, 400);
			//frame.setVisible(true); //aparece a imagem que estou lendo

			int width = image1.getWidth();//pega largura da imagem da garrafa original.
			int height = image1.getHeight();//pega altura da imagem da garrafa original.
			frame.setTitle("Altura: " + height + "  x  Largura: " + width); //Dando nome a janela de apresentação da imagem.
			int nbands = image1.getSampleModel().getNumBands();
			Raster inputRaster = image1.getData();
			int[] pixels = new int[nbands * width * height];
			inputRaster.getPixels(0, 0, width, height, pixels);
			
			//Declarando Red Green e Blue para depois verificar a frequência individualmente.
			int[][] r = new int[width][height];
			int[][] g = new int[width][height];
			int[][] b = new int[width][height];

			int[][] r2 = new int[width][height];
			int[][] g2 = new int[width][height];
			int[][] b2 = new int[width][height];

			Color rgb = null;
			Color rgb2 = null;
			
			for (int w = 0; w < width; w++) {  	//Começa a varrer a matriz linha
				for (int h = 0; h < height; h++) {  //apos terminar uma linha começa por coluna

					rgb = new Color(image1.getRGB(w, h));  //w e h são as posições da matriz x e y

					r[w][h] = rgb.getRed();  //capturando frequência de vermelho na posição w h
					g[w][h] = rgb.getGreen();// capturando frequência de Verde na posição w h
					b[w][h] = rgb.getBlue(); //capturando ferquência de Azul na posição w h

					rgb2 = new Color(image2.getRGB(w, h));
					r2[w][h] = rgb2.getRed();
					g2[w][h] = rgb2.getGreen();
					b2[w][h] = rgb2.getBlue();
				}
			}
			
			// aplicando filtros
			BufferedImage imagemSoma = new BufferedImage(image1.getWidth(), image1.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			BufferedImage imagemSoma2 = new BufferedImage(image1.getWidth(), image1.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			
			int[][] imagemSemFiltro = new int[width][height];
			int[][] imagemSemFiltro2 = new int[width][height];
			
			for (int i = 0; i <= image1.getWidth() - 1; i++) {
				for (int j = 0; j <= image1.getHeight() - 1; j++) {
					int vermelho = r[i][j];
					int verde = g[i][j];
					int azul = b[i][j];
					int cor = (vermelho + verde + azul) / 3;
					imagemSoma.setRGB(i, j, new Color(cor, cor, cor).getRGB());
					imagemSemFiltro[i][j] = cor;
					vermelho = r2[i][j];
					verde = g2[i][j];
					azul = b2[i][j];
					cor = (vermelho + verde + azul) / 3;
					imagemSoma2.setRGB(i, j, new Color(cor, cor, cor).getRGB());
					imagemSemFiltro2[i][j] = cor;
				}
			}
			
			//salvando imagem pb ideal e pb teste
			ImageIO.write(imagemSoma, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\ImagemPbIdeal.jpg"));
			ImageIO.write(imagemSoma2, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\ImagemPbTeste.jpg"));

			//pegando a imagem PB para verificar
			int[][] mat = { { 0, -1, 0 }, { -1, 4, -1 }, { 0, -1, 0 } };
			int[][] imagemComFiltro = funcConvolucao(mat, 3, image1.getWidth(), image1.getHeight(), imagemSemFiltro);
			int[][] imagemFiltro = new int[image1.getWidth()][image1.getHeight()];
			int[][] imagemComFiltro2 = funcConvolucao(mat, 3, image1.getWidth(), image1.getHeight(), imagemSemFiltro2);
			int[][] imagemFiltro2 = new int[image1.getWidth()][image1.getHeight()];

			BufferedImage imagemFiltroRgb = new BufferedImage(image1.getWidth(), image1.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			BufferedImage imagemTeste = new BufferedImage(image1.getWidth(), image1.getHeight(),
					BufferedImage.TYPE_INT_RGB);

			BufferedImage imagemFiltroRgb2 = new BufferedImage(image1.getWidth(), image1.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			BufferedImage imagemTeste2 = new BufferedImage(image1.getWidth(), image1.getHeight(),
					BufferedImage.TYPE_INT_RGB);

			for (int i = 0; i <= image1.getWidth() - 1; i++) {
				for (int j = 0; j <= image1.getHeight() - 1; j++) {
					int cor = imagemComFiltro[i][j];
					int soma = (int) Math.pow(cor, 2);
					if (soma > 255)
						soma = 255;
					if (soma < 0)
						soma = 0;
					imagemFiltro[i][j] = soma;
					imagemFiltroRgb.setRGB(i, j, new Color(cor, cor, cor).getRGB());
					imagemTeste.setRGB(i, j, new Color(soma, soma, soma).getRGB());

					cor = imagemComFiltro2[i][j];
					soma = (int) Math.pow(cor, 2);
					if (soma > 255)
						soma = 255;
					if (soma < 0)
						soma = 0;
					imagemFiltro2[i][j] = soma;
					imagemFiltroRgb2.setRGB(i, j, new Color(cor, cor, cor).getRGB());
					imagemTeste2.setRGB(i, j, new Color(soma, soma, soma).getRGB());
				}
			}
			
			int linhaVerticalControle = validaGarrafa(imagemFiltro, image1.getHeight());
			int linhaVerticalTeste = validaGarrafa(imagemFiltro2, image1.getHeight());
			BufferedImage linhaControle = new BufferedImage(image1.getWidth(), image1.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			BufferedImage linhaTeste = new BufferedImage(image1.getWidth(), image1.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i <= image1.getWidth() - 1; i++) {
				for (int j = 0; j <= image1.getHeight() - 1; j++) {
					int cor = imagemFiltro[i][j];
					linhaControle.setRGB(i, j, new Color(cor, cor, cor).getRGB());

					cor = imagemFiltro2[i][j];
					linhaTeste.setRGB(i, j, new Color(cor, cor, cor).getRGB());
				}
			}
			int diferenca = linhaVerticalControle - linhaVerticalTeste;
			diferenca = Math.abs(diferenca);
			if (diferenca > 20) {
				System.out.println("garrafa invalida");
				System.out.println("Diferenca em pixels =" + diferenca);
			} else {
				System.out.println("garrafa valida");
				System.out.println("Diferenca em pixels =" + diferenca);
			}
			ImageIO.write(linhaTeste, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\linhaTeste.jpg"));
			ImageIO.write(linhaControle, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\linhaControle.jpg"));
			ImageIO.write(imagemFiltroRgb, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\realceDeBordasIdeal.jpg"));
			ImageIO.write(imagemTeste, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\mediaimagemIdeal.jpg"));
			ImageIO.write(imagemFiltroRgb2, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\realceDeBordasTeste.jpg"));
			ImageIO.write(imagemTeste2, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\mediaimagemTeste.jpg"));
		
		} catch (IOException ex) {
		}

	}

	
	
	
	
	
	
	
	
	public void imprime1() {
		BufferedImage image;

		String str = "C:\\Users\\alan_\\Pictures\\Trabalho_PET\\mediaimagemIdeal.jpg";
		try {
			image = ImageIO.read(new File(str));
			ImageIcon icon = new ImageIcon(image);
			JLabel imageLabel = new JLabel(icon);
			JFrame frame = new JFrame();
			Container contentPane = frame.getContentPane();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
			frame.setSize(630, 400);
			frame.setVisible(true); //aparece a imagem que estou lendo

			int width = image.getWidth(); //pega largura da imagem da garrafa original.
			int height = image.getHeight(); //pega altura da imagem da garrafa original.
			frame.setTitle("Altura: " + height + "  x  Largura: " + width); //Dando nome a janela de apresentação da imagem.
			int nbands = image.getSampleModel().getNumBands();
			Raster inputRaster = image.getData();
			int[] pixels = new int[nbands * width * height];
			inputRaster.getPixels(0, 0, width, height, pixels);


		}catch (IOException ex) {
				}

			}
	
	
	public void imprime2() {
		BufferedImage image;

		String str = "C:\\Users\\alan_\\Pictures\\Trabalho_PET\\mediaimagemTeste.jpg";
		try {
			image = ImageIO.read(new File(str));
			ImageIcon icon = new ImageIcon(image);
			JLabel imageLabel = new JLabel(icon);
			JFrame frame = new JFrame();
			Container contentPane = frame.getContentPane();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
			frame.setSize(630, 400);
			frame.setVisible(true); //aparece a imagem que estou lendo

			int width = image.getWidth(); //pega largura da imagem da garrafa original.
			int height = image.getHeight(); //pega altura da imagem da garrafa original.
			frame.setTitle("Altura: " + height + "  x  Largura: " + width); //Dando nome a janela de apresentação da imagem.
			int nbands = image.getSampleModel().getNumBands();
			Raster inputRaster = image.getData();
			int[] pixels = new int[nbands * width * height];
			inputRaster.getPixels(0, 0, width, height, pixels);


		}catch (IOException ex) {
				}

			}
	
	
	
	
	
	
	public void imprime3() {
		BufferedImage image;

		String str = "C:\\Users\\alan_\\Pictures\\Trabalho_PET\\linhaControle.jpg";
		try {
			image = ImageIO.read(new File(str));
			ImageIcon icon = new ImageIcon(image);
			JLabel imageLabel = new JLabel(icon);
			JFrame frame = new JFrame();
			Container contentPane = frame.getContentPane();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
			frame.setSize(630, 400);
			frame.setVisible(true); //aparece a imagem que estou lendo

			int width = image.getWidth(); //pega largura da imagem da garrafa original.
			int height = image.getHeight(); //pega altura da imagem da garrafa original.
			frame.setTitle("Altura: " + height + "  x  Largura: " + width); //Dando nome a janela de apresentação da imagem.
			int nbands = image.getSampleModel().getNumBands();
			Raster inputRaster = image.getData();
			int[] pixels = new int[nbands * width * height];
			inputRaster.getPixels(0, 0, width, height, pixels);


		}catch (IOException ex) {
				}

			}
	
	
	public void imprime4() {
		BufferedImage image;

		String str = "C:\\Users\\alan_\\Pictures\\Trabalho_PET\\linhaTeste.jpg";
		try {
			image = ImageIO.read(new File(str));
			ImageIcon icon = new ImageIcon(image);
			JLabel imageLabel = new JLabel(icon);
			JFrame frame = new JFrame();
			Container contentPane = frame.getContentPane();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
			frame.setSize(630, 400);
			frame.setVisible(true); //aparece a imagem que estou lendo

			int width = image.getWidth(); //pega largura da imagem da garrafa original.
			int height = image.getHeight(); //pega altura da imagem da garrafa original.
			frame.setTitle("Altura: " + height + "  x  Largura: " + width); //Dando nome a janela de apresentação da imagem.
			int nbands = image.getSampleModel().getNumBands();
			Raster inputRaster = image.getData();
			int[] pixels = new int[nbands * width * height];
			inputRaster.getPixels(0, 0, width, height, pixels);


		}catch (IOException ex) {
				}

			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private int validaGarrafa(int[][] imagemFiltro, int altura) {

		int i = validaLinha(imagemFiltro, altura);
		for (int o = 0; o <= altura - 1; o++) {
			imagemFiltro[i][o] = 255;
		}
		return i;

	}

	private int validaLinha(int[][] imagemFiltro, int altura) {
		double soma = 0;
		int maior = 0;
		int pixel = 0;
		for (int i = 100; i <= 300; i++) {  //pula 100 pixel para começar a procurar 
			if (imagemFiltro[i][altura / 2] >= 20) {
				for (int o = 0; o <= altura - 1; o++) {
					soma = soma + imagemFiltro[i][o];
				}
				soma = soma / altura;
				if (soma > maior) {
					maior = (int) soma;
					pixel = i;
				}
				System.out.println(soma);
			}

		}
		return pixel;
	}
//convolução.
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
				if (total > 255)
					total = 255;
				if (total < 0)
					total = 0;
				imagemComFiltro[i][j] = Math.abs(total);

			}
		}

		return imagemComFiltro;  //retorna a imagem depois da convolução
	}

}
