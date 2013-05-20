package base;

import util.Vec2D;

import java.awt.*;

/**
 * Placeholder world; solid below the x axis
 */
public class World implements IWorldTopology {
	static final Color col = new Color(0,0,0);
	@Override
	public Vec2D[] getClosestAABB(Vec2D pos) {
		Vec2D[] ret={};
		ret[0]=new Vec2D(pos.x,64);
		ret[1]=new Vec2D(320,32);
		return ret;
	}
	@Override
	public void draw(Graphics2D g,Vec2D campos,Dimension d) {
		g.setColor(col);
		int y = -(int)Math.round(campos.y);
		g.drawLine(0,y,d.width,y);
	}
}
