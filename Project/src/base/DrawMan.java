package base;

import java.awt.*;

/**
 * Drawing manager
 */
public class DrawMan {
	Graphics2D gfx;
	Frame f;
	public void init() {
		f=new Frame();
		f.setSize(640,480);
		f.addWindowListener(new WindowMon());
		Canvas c = new Canvas();
		c.setSize(640,480);
		c.setVisible(true);
		f.add(c);
		f.setVisible(true);

		gfx=(Graphics2D)c.getGraphics();
	}
}
