package base;

import base.ents.Ent;
import util.Vec2D;

/**
 * Describes world solidity querying
 */
public interface IWorldTopology extends IRenderable {
	/**
	 * Return a sufficiently large AABB closest to pos in array {pos,size}
	 * @param pos Position to find the AABB next to (usually center of an AABB about to be tested against world)
	 * @return {pos,size} of world AABB for this point
	 */
	public Vec2D[] getClosestAABB(Vec2D pos);
	//implement IRenderable so that it is easy to tell what is solid
	public void environmentHook(Ent e, double dt); //called every tick for every entity. Implement eg. gravity here.
}
