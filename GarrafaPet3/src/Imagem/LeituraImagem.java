package Imagem;

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

public class LeituraImagem {

	public void jButton1ActionPerformed(String nomeArq,String nomefoto) {
		BufferedImage image;
		BufferedImage image2;

		String str = "C:\\Users\\alan_\\Pictures\\Trabalho_PET\\";
		String garrafaBoa = str.concat(nomeArq);
		String garrafaTeste = str.concat(nomefoto);
		try {
			image = ImageIO.read(new File(garrafaBoa));
			image2 = ImageIO.read(new File(garrafaTeste));
			ImageIcon icon = new ImageIcon(image);
			JLabel imageLabel = new JLabel(icon);
			JFrame frame = new JFrame();
			Container contentPane = frame.getContentPane();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
			frame.setSize(600, 400);
			frame.setVisible(true);

			int width = image.getWidth();
			int height = image.getHeight();
			frame.setTitle("Imagem Sem Filtro: " + height + " x " + width);
			int nbands = image.getSampleModel().getNumBands();
			Raster inputRaster = image.getData();
			int[] pixels = new int[nbands * width * height];
			inputRaster.getPixels(0, 0, width, height, pixels);

			int[][] r = new int[width][height];
			int[][] g = new int[width][height];
			int[][] b = new int[width][height];
			
			int[][] r2 = new int[width][height];
			int[][] g2 = new int[width][height];
			int[][] b2 = new int[width][height];

			Color rgb = null;
			Color rgb2  = null;
			for (int w = 0; w < width; w++) {
				for (int h = 0; h < height; h++) {

					rgb = new Color(image.getRGB(w, h));
					
					r[w][h] = rgb.getRed();
					g[w][h] = rgb.getGreen();
					b[w][h] = rgb.getBlue();

					rgb2 = new Color(image2.getRGB(w, h));
					
					r2[w][h] = rgb2.getRed();
					g2[w][h] = rgb2.getGreen();
					b2[w][h] = rgb2.getBlue();
				}
			}
			BufferedImage imagemSoma = new BufferedImage(image.getWidth(), image.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			BufferedImage imagemSoma2 = new BufferedImage(image.getWidth(), image.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			int[][] imagemSemFiltro = new int[width][height];
			int[][] imagemSemFiltro2 = new int[width][height];
			for (int i = 0; i <= image.getWidth() - 1; i++) {
				for (int j = 0; j <= image.getHeight() - 1; j++) {
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

			ImageIO.write(imagemSoma, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\ImagemPbIdeal.jpg"));
			ImageIO.write(imagemSoma2, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\ImagemPbTeste.jpg"));
			
			int[][] mat = { { 0, -1, 0 }, { -1, 4, -1 }, { 0, -1, 0 } };
			int[][] imagemComFiltro = funcConvolucao(mat, 3, image.getWidth(), image.getHeight(), imagemSemFiltro);
			int[][] imagemFiltro= new int[image.getWidth()][ image.getHeight()];
			int[][] imagemComFiltro2 = funcConvolucao(mat, 3, image.getWidth(), image.getHeight(), imagemSemFiltro2);
			int[][] imagemFiltro2 = new int[image.getWidth()][ image.getHeight()];
			
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
					
					cor = imagemComFiltro2[i][j];
					soma = (int) Math.pow(cor, 2);
					if(soma > 255)
						soma = 255;
					if(soma < 0)
						soma = 0;
					imagemFiltro2[i][j] = soma;
					imagemFiltroRgb2.setRGB(i, j, new Color(cor, cor, cor).getRGB());
					imagemTeste2.setRGB(i, j,new Color(soma,soma,soma).getRGB());
				}
			}
			mat = new int[][] { { 1 , 1, 1, 1,1 }, {1,1, 1, 1, 1 }, { 1,1,1, 1, 1 }, { 1,1,1, 1, 1 }, { 1,1,1, 1, 1 } };
			imagemFiltro = media(mat, 5, image.getWidth(), image.getHeight(), imagemFiltro);
			imagemFiltro2 = media(mat, 5, image.getWidth(), image.getHeight(), imagemFiltro2);
			
			
			boolean resp = validaGarrafa(imagemFiltro,imagemFiltro2,image.getWidth(), image.getHeight());
			
			
				ImageIO.write(imagemFiltroRgb, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\realceDeBordasIdeal.jpg"));		
				ImageIO.write(imagemTeste, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PET\\mediaimagemIdeal.jpg"));
			
				ImageIO.write(imagemFiltroRgb2, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PETrealceDeBordasTeste.jpg"));		
				ImageIO.write(imagemTeste2, "jpg", new File("C:\\Users\\alan_\\Pictures\\Trabalho_PETmediaimagemTeste.jpg"));

		} catch (IOException ex) {
		}

	}

	private boolean validaGarrafa(int[][] imagemFiltro, int[][] imagemFiltro2, int largura, int altura) {
		
		
		
		
		return false;
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

}
