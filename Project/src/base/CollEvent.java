package base;

import base.ents.Ent;
import util.Vec2D;

/**
 * Simple container for collision information
 */
public class CollEvent {
	public int id; //id hit
	public Ent e; //entity hit, or null
	public Vec2D pos1; //position of the entity when collided
	public Vec2D pos2; //position of the entity hit, or null
	public Vec2D vel1; //vel of the entity itself
	public Vec2D vel2; //vel of the entity hit, or null
	public Vec2D d; //minimum translation vector from CollAABB()
	public CollEvent(int id,Ent e,Vec2D pos1,Vec2D pos2,Vec2D vel1,Vec2D vel2,Vec2D d) {
		this.id=id;
		this.e=e;
		this.pos1=pos1;
		this.pos2=pos2;
		this.vel1=vel1;
		this.vel2=vel2;
		this.d=d;
	}
}
