package base.managers;

import base.Game;
import base.WindowMon;
import base.ents.Ent;
import util.Vec2D;

import java.awt.*;
import java.util.Iterator;

/**
 * Drawing manager
 */
public class DrawMan {
	Graphics2D gfx;
	Vec2D CAM=new Vec2D(320,240);
	private Frame f;
	private Font fnt;

	private Color alphawhite=new Color(255,255,255,180);

	public static final Dimension RES = new Dimension(640,480);
	public void init() {
		f=new Frame(Game.TITLE);
		f.setResizable(false);
		f.setSize(RES);
		f.addWindowListener(new WindowMon());
		f.addKeyListener(Game.INPUTMAN);
		Canvas c = new Canvas();
		c.setSize(RES);
		c.setVisible(true);
		c.addKeyListener(Game.INPUTMAN);
		f.add(c);
		f.setVisible(true);

		gfx=(Graphics2D)c.getGraphics();

		fnt = new Font("Arial",0,12);
	}
	//todo implement double buffering
	public void draw_game() {
		Color col;
		if(Game.CONFIGMAN.BLUR_ENABLE) col = alphawhite;
		else col = Color.white;
		gfx.setColor(col);
		gfx.fillRect(0,0,RES.width,RES.height);
		Game.WORLD().draw(gfx,CAM,RES);
		Iterator<Ent> iter = Game.STATEMAN.getEntIter();
		while(iter.hasNext()) iter.next().draw(gfx,CAM,RES);
	}

	/**
	 * Draws the requested lines of text in upper left corner of the screen
	 * Should be called after draw_game()
	 * @param txt Array containing the lines
	 */
	public void text_hook(String[] txt) {
		gfx.setColor(Color.BLACK);
		gfx.setFont(fnt);
		for(int i=0;i<txt.length;i++) {
			gfx.drawString(txt[i],5,20+i*14);
		}

	}

	/**
	 * Requests focus back to frame
	 */
	public void focus() {
		f.requestFocus();
	}

	public void exit() {
		f.setVisible(false);
		f.dispose();
	}
}
