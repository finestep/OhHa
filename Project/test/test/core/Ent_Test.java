package test.core;

import base.CollEvent;
import base.ents.Ent;
import util.Vec2D;

import java.awt.*;
import java.util.Iterator;

/**
 * Barebones test entity
 */
public class Ent_Test extends Ent {
	private static final Color col = new Color(0,255,0);

	public Ent_Test(Vec2D p,Vec2D v) {
		super();

		size=new Vec2D(10,10);
		mass=1;

		collclass=COLL_NONE;
		colltype=COLL_WRLD;

		pos=p;
		vel=v;

	}

	public void handleCollisions() {
		Iterator<CollEvent> iter = collisions.iterator();
		while(iter.hasNext()) {
			CollEvent ev = iter.next();
			pos._add(ev.d);
			vel._mul(0);
			iter.remove();
		}
	}


	@Override
	public void draw(Graphics2D g, Vec2D cam,Dimension res) {
		int x=(int)Math.round(pos.x+cam.x-size.x);
		int y=(int)Math.round(pos.y+cam.y-size.y);
		int w=(int)size.x*2;
		int h=(int)size.y*2;

		g.setColor(col);
		g.fillRect(x,y,w,h);
	}




}
