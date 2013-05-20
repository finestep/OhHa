package base;

import util.Vec2D;

/**
 * Describes world solidity querying
 */
public interface IWorldTopology extends IRenderable {
	public Vec2D[] getClosestAABB(Vec2D pos); //return a sufficiently large AABB closest to pos in array {pos,size}
}
