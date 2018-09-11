package mdmatrakas.compGrafica.model.util;

public interface Manipulador {
	public void click(float x, float y);

	public void press(float x, float y);

	public void release(float x, float y);

	public void drag(float x, float y);

	public void move(float x, float y);
	
	public boolean isFinalizado();
	
	public boolean isCancelado();
}
