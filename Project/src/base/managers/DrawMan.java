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
	Vec2D CAM=new Vec2D(320,-240);
	Frame f;
	public static final Dimension RES = new Dimension(640,480);
	public void init() {
		f=new Frame(Game.TITLE);
		f.setSize(RES);
		f.addWindowListener(new WindowMon());
		Canvas c = new Canvas();
		c.setSize(RES);
		c.setVisible(true);
		f.add(c);
		f.setVisible(true);

		gfx=(Graphics2D)c.getGraphics();
	}

	public void draw_game() {
		Game.WORLD().draw(gfx,CAM,RES);
		Iterator<Ent> iter = Game.STATEMAN.getEntIter();
		while(iter.hasNext()) iter.next().draw(gfx,CAM,RES);
	}
}
