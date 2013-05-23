package base;

import base.ents.Ent;
import util.Vec2D;

/**
 * Describes world solidity querying
 */
public interface IWorldTopology extends IRenderable {
	public Vec2D[] getClosestAABB(Vec2D pos); //return a sufficiently large AABB closest to pos in array {pos,size}
	//implement IRenderable so that it is easy to tell what is solid
	public void environmentHook(Ent e, double dt); //called every tick for every entity. Implement eg. gravity here.
}
