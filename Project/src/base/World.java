package base;

import base.ents.Ent;
import util.Vec2D;

import java.awt.*;

/**
 * Placeholder world; solid below the x axis
 */
public class World implements IWorldTopology {
	static final Color col = new Color(0,0,0);
	static final  Vec2D globalAcc = new Vec2D(0,80);

	@Override
	public Vec2D[] getClosestAABB(Vec2D pos) {
		Vec2D[] ret={null,null};
		ret[0]=new Vec2D(pos.x,32);
		ret[1]=new Vec2D(320,32);
		return ret;
	}

	@Override
	public void environmentHook(Ent e,double dt) {
		e.vel._add(globalAcc.mul(dt));
	}

	@Override
	public void draw(Graphics2D g,Vec2D campos,Dimension d) {
		g.setColor(col);
		int y = (int)Math.round(campos.y);
		g.drawLine(0,y,d.width,y);
	}
}
