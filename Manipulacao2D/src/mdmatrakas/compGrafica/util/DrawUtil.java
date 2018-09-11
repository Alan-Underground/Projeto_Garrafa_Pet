package mdmatrakas.compGrafica.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import mdmatrakas.compGrafica.model.Janela;
import mdmatrakas.compGrafica.model.Ponto2D;
import mdmatrakas.compGrafica.model.Universo2D;

public class DrawUtil {
	private Graphics graphics;

	int maxX;
	int maxY;

	int left;
	int right;
	int bottom;
	int top;

	Ponto2D centro;
	int centerX;
	int centerY;

	float pixelSize;
	float rWidth;
	float rHeight;

	public DrawUtil(Graphics g, Dimension d, Universo2D universo) {
		this.graphics = g;

		if (d != null) {
			maxX = d.width - 1;
			maxY = d.height - 1;
			centerX = maxX / 2;
			centerY = maxY / 2;
		} else {
			maxX = 0;
			maxY = 0;
			centerX = maxX / 2;
			centerY = maxY / 2;
		}

		if (universo != null) {
			rWidth = universo.getLargura();
			rHeight = universo.getAltura();
			pixelSize = Math.max(rWidth / maxX, rHeight / maxY);

			centro = universo.getJanela().getCentro();

			left = iX(universo.getJanela().getMinX());
			right = iX(universo.getJanela().getMaxX());
			bottom = iY(universo.getJanela().getMinY());
			top = iY(universo.getJanela().getMaxY());
		} else {
			rWidth = 0;
			rHeight = 0;
			pixelSize = Math.max(rWidth / maxX, rHeight / maxY);

			centro = new Ponto2D();

			left = 0;
			right = 0;
			bottom = 0;
			top = 0;
		}
	}

	public void setGraphics(Graphics g) {
		graphics = g;
	}

	public void setDimension(Dimension d, Universo2D universo) {
		if (d == null)
			throw new IllegalArgumentException("Uma dimensão deve ser especificada.");

		maxX = d.width - 1;
		maxY = d.height - 1;
		centerX = maxX / 2;
		centerY = maxY / 2;

		if (universo != null) {
			rWidth = universo.getLargura();
			rHeight = universo.getAltura();
			pixelSize = Math.max(rWidth / maxX, rHeight / maxY);

			centro = universo.getJanela().getCentro();
			left = iX(universo.getJanela().getMinX());
			right = iX(universo.getJanela().getMaxX());
			bottom = iY(universo.getJanela().getMinY());
			top = iY(universo.getJanela().getMaxY());
		} else {
			rWidth = 0;
			rHeight = 0;
			pixelSize = Math.max(rWidth / maxX, rHeight / maxY);

			centro = new Ponto2D();
			left = 0;
			right = 0;
			bottom = 0;
			top = 0;
		}
	}

	public void setJanela(Janela j, Universo2D universo) {
		if (j == null)
			throw new IllegalArgumentException("Uma janela deve ser especificada.");

		if (universo != null) {
			rWidth = j.getLargura();
			rHeight = j.getAltura();
			pixelSize = Math.max(rWidth / maxX, rHeight / maxY);

			centro = universo.getJanela().getCentro();

			left = iX(universo.getJanela().getMinX());
			right = iX(universo.getJanela().getMaxX());
			bottom = iY(universo.getJanela().getMinY());
			top = iY(universo.getJanela().getMaxY());
		} else {
			rWidth = 0;
			rHeight = 0;
			pixelSize = Math.max(rWidth / maxX, rHeight / maxY);

			centro = new Ponto2D();
			left = 0;
			right = 0;
			bottom = 0;
			top = 0;
		}
	}

	public int iX(float x) {
		return Math.round(centerX + (x - centro.getX()) / pixelSize);
	}

	public int iY(float y) {
		return Math.round(centerY - (y - centro.getY()) / pixelSize);
	}

	public float fx(int x) {
		return ((x - centerX) * pixelSize) + centro.getX();
	}

	public float fy(int y) {
		return ((centerY - y) * pixelSize) + centro.getY();
	}

	public float getPixelSize(){
		return pixelSize;
	}
	
	public void drawCircle(int xC, int yC, int r) {
		int x = 0;
		int y = r;
		int u = 1;
		int v = 2 * r - 1;
		int e = 0;
		while (x < y) {
			putPixel(xC + x, yC + y);
			putPixel(xC + y, yC - x);
			putPixel(xC - x, yC - y);
			putPixel(xC - y, yC + x);

			x++;
			e += u;
			u += 2;
			if (v < 2 * e) {
				y--;
				e -= v;
				v -= 2;
			}
			if (x > y)
				break;

			putPixel(xC + y, yC + x);
			putPixel(xC + x, yC - y);
			putPixel(xC - y, yC - x);
			putPixel(xC - x, yC + y);
		}
	}

	public void drawLine(int xP, int yP, int xQ, int yQ) {
		int x = xP;
		int y = yP;
		int d = 0;
		int dx = xQ - xP;
		int dy = yQ - yP;
		int xInc = 1;
		int yInc = 1;
		int c;
		int m;

		if (dx < 0) {
			xInc = -1;
			dx = -dx;
		}
		if (dy < 0) {
			yInc = -1;
			dy = -dy;
		}
		if (dy <= dx) {
			c = 2 * dx;
			m = 2 * dy;
			if (xInc < 0)
				dx++;
			for (;;) {
				putPixel(x, y);
				if (x == xQ)
					break;
				x += xInc;
				d += m;
				if (d >= dx) {
					y += yInc;
					d -= c;
				}
			}
		} else {
			c = 2 * dy;
			m = 2 * dx;
			if (yInc < 0)
				dy++;
			for (;;) {
				putPixel(x, y);
				if (y == yQ)
					break;
				y += yInc;
				d += m;
				if (d >= dy) {
					x += xInc;
					d -= c;
				}
			}
		}
	}

	public void drawLine2(int xP, int yP, int xQ, int yQ) {
		int x = xP;
		int y = yP;
		float d = 0F;
		float m = (float) (yQ - yP) / (float) (xQ - xP);

		for (;;) {
			putPixel(x, y);
			if (x == xQ)
				break;
			x++;
			d += m;
			if (d >= 0.5) {
				y++;
				d--;
			}
		}
	}

	public void drawLine3(int xP, int yP, int xQ, int yQ) {
		int x = xP;
		int y = yP;
		int d = 0;
		int dx = xQ - xP;
		int c = 2 * dx;
		int m = 2 * (yQ - yP);

		for (;;) {
			putPixel(x, y);
			if (x == xQ)
				break;
			x++;
			d += m;
			if (d >= dx) {
				y++;
				d -= c;
			}
		}
	}

	public void doubleStep1(int xP, int yP, int xQ, int yQ) {
		int dx, dy, x, y, yInc;

		if (xP >= xQ) {
			if (xP == xQ) // não permitido porque dividimos por dx (= xQ - xP)
				return;
			// xP > xQ, então permute os pontos P e Q
			int t;
			t = xP;
			xP = xQ;
			xQ = t;
			t = yP;
			yP = yQ;
			yQ = t;
		}
		// Agora xP < xQ
		if (yQ >= yP) // Caso normal, yP < yQ
		{
			yInc = 1;
			dy = yQ - yP;
		} else {
			yInc = -1;
			dy = yP - yQ;
		}
		dx = xQ - xP; // dx > 0, dy > 0

		float d = 0F; // erro d = yExato - y
		float m = (float) dy / (float) dx; // m <= 1, m = | inclinação |

		putPixel(xP, yP);
		y = yP;

		for (x = xP; x < xQ - 1;) {
			if (d + 2 * m < 0.5) // padrão 1
			{
				putPixel(++x, y);
				putPixel(++x, y);
				d += 2 * m; // O erro aumenta em 2m, ja que y permanece
							// innalterado e yExato aumenta em 2m
			} else {
				if (d + 2 * m < 1.5) // padrão 2 ou 3
				{
					if (d + m < 0.5) // padrão 2
					{
						putPixel(++x, y);
						putPixel(++x, y += yInc);
						d += 2 * m - 1; // Devido a ++y, o erro é agora 1 a
										// menos que com o padrão 1
					} else // padrão 3
					{
						putPixel(++x, y += yInc);
						putPixel(++x, y);
						d += 2 * m - 1; // Mesmo do padrão 2
					}
				} else // padrão 4
				{
					putPixel(++x, y += yInc);
					putPixel(++x, y += yInc);
					d += 2 * m - 2; // Devido a y+=2, o erro é agora 2 a menos
									// que com o padrão 1
				}
			}
		}
		if (x < xQ) // x = xQ - 1
		{
			putPixel(xQ, yQ);
		}
	}

	public void doubleStep2(int xP, int yP, int xQ, int yQ) {
		int dx, dy, x, y, yInc;

		if (xP >= xQ) {
			if (xP == xQ) // não permitido porque dividimos por dx (= xQ - xP)
				return;
			// xP > xQ, então permute os pontos P e Q
			int t;
			t = xP;
			xP = xQ;
			xQ = t;
			t = yP;
			yP = yQ;
			yQ = t;
		}
		// Agora xP < xQ
		if (yQ >= yP) // Caso normal, yP < yQ
		{
			yInc = 1;
			dy = yQ - yP;
		} else {
			yInc = -1;
			dy = yP - yQ;
		}
		dx = xQ - xP; // dx > 0, dy > 0

		int dy4 = dy * 4;
		int v = dy4 - dx;
		int dx2 = 2 * dx;
		int dy2 = 2 * dy;
		int dy4MenosDx2 = dy4 - dx2;
		int dy4MenosDx4 = dy4MenosDx2 - dx2;

		putPixel(xP, yP);
		y = yP;

		for (x = xP; x < xQ - 1;) {
			if (v < 0) // padrão 1
			{
				putPixel(++x, y);
				putPixel(++x, y);
				v += dy4;
			} else {
				if (v < dx2) // padrão 2 ou 3
				{
					if (v < dy2) // padrão 2
					{
						putPixel(++x, y);
						putPixel(++x, y += yInc);
						v += dy4MenosDx2;
					} else // padrão 3
					{
						putPixel(++x, y += yInc);
						putPixel(++x, y);
						v += dy4MenosDx2;
					}
				} else // padrão 4
				{
					putPixel(++x, y += yInc);
					putPixel(++x, y += yInc);
					v += dy4MenosDx4;
				}
			}
		}
		if (x < xQ) {
			putPixel(xQ, yQ);
		}
	}

	public void drawLine(Ponto2D P, Ponto2D Q) {
		drawLine(iX(P.getX()), iY(P.getY()), iX(Q.getX()), iY(Q.getY()));
	}

	public void drawLine(float px, float py, float qx, float qy) {
		drawLine(iX(px), iY(py), iX(qx), iY(qy));
	}

	public void showDrawArea() {
		if (graphics == null)
			return;
		Color c = graphics.getColor();
		graphics.setColor(Color.lightGray);

		graphics.fillRect(0, 0, left, bottom);
		graphics.fillRect(0, 0, right, top);
		graphics.fillRect(0, bottom, maxX, maxY);
		graphics.fillRect(right, top, maxX, maxY);

		graphics.setColor(c);
	}

	public void putPixel(int x, int y) {
		if (graphics == null)
			return;
		graphics.drawLine(x, y, x, y);
	}

	public void putPixel(float x, float y) {
		if (graphics == null)
			return;
		graphics.drawLine(iX(x), iY(y), iX(x), iY(y));
	}

	public void putPixel(Ponto2D P) {
		if (graphics == null)
			return;
		graphics.drawLine(iX(P.getX()), iY(P.getY()), iX(P.getX()), iY(P.getY()));
	}

}
