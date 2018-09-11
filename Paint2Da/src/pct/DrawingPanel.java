package mdmatrakas.compGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import mdmatrakas.compGrafica.util.DrawUtil;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	private Color backColor;
	private Color drawColor;

	private DrawUtil drawUtil;
	private Documento doc;

	float xP;
	float yP;

	public DrawingPanel() {
		this.doc = null;

		drawUtil = null;

		backColor = new Color(255, 255, 255);
		drawColor = new Color(0, 0, 0);

		setBackground(backColor);
		setForeground(drawColor);

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		if (doc == null || drawUtil == null)
			return;

		doc.mouseClick(drawUtil.fx(e.getX()), drawUtil.fy(e.getY()));
		repaint();
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if (doc == null || drawUtil == null)
			return;

		xP = drawUtil.fx(e.getX());
		yP = drawUtil.fy(e.getY());
		doc.mousePress(xP, yP);
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		if (doc == null || drawUtil == null)
			return;

		doc.mouseRelease(drawUtil.fx(e.getX()), drawUtil.fy(e.getY()));
		repaint();
	}

	public void mouseDragged(MouseEvent e) {
		if (doc == null || drawUtil == null)
			return;

		float x = drawUtil.fx(e.getX());
		float y = drawUtil.fy(e.getY());
		String str = String.format("Disp: {(%d;%d)(%d;%d)} - Univ: {[%.3f;%.3f][%.3f;%.3f]}", drawUtil.iX(xP),
				drawUtil.iY(yP), e.getX(), e.getY(), xP, yP, x, y);
		AppComputacaoGrafica.getApp().setStatusText(str);

		doc.mouseDrag(x, y);
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		if (doc == null || drawUtil == null)
			return;

		float x = drawUtil.fx(e.getX());
		float y = drawUtil.fy(e.getY());
		String str = String.format("Disp: (%d;%d) - Univ: [%f;%f]", e.getX(), e.getY(), x, y);
		AppComputacaoGrafica.getApp().setStatusText(str);

		doc.mouseMove(x, y);
		repaint();
	}

	public void setDocumento(Documento doc) {
		this.doc = doc;

		if (drawUtil == null)
			drawUtil = new DrawUtil(null, this.getSize(), doc.getUniverso());
		else
			drawUtil.setDimension(this.getSize(), doc.getUniverso());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (doc == null || drawUtil == null)
			return;

		drawUtil.setGraphics(g);
		drawUtil.setDimension(getSize(), doc.getUniverso());
		drawUtil.showDrawArea();

		doc.desenharModelos(drawUtil);

		drawUtil.setGraphics(null);

	}

	@Override
	public void setSize(Dimension d) {
		super.setSize(d);
		if (doc == null || drawUtil == null)
			return;

		drawUtil.setDimension(d, doc.getUniverso());
	}

}
