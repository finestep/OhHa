package base;

import util.Vec2D;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * Can be drawn by DrawMan
 */
public interface IRenderable {
	public void draw(Graphics2D g, Vec2D campos,Dimension res);  //render the world
}
