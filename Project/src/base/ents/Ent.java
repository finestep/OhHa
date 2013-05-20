package base.ents;

import base.CollEvent;
import base.IRenderable;
import util.Vec2D;
import java.util.ArrayDeque;

/**
 * Abstract entity class
 */
public abstract class Ent implements IRenderable {
	//Special ents
	public final static int ENT_WORLD=-1;
	public final static int ENT_NONE=-2;
	//Collision bits
	public final static int COLL_NONE=0;
	public final static int COLL_ALL=0xFFFFFFF;
	public final static int COLL_WRLD=1;
	public final static int COLL_PLR=2;
	public final static int COLL_ENM=4;
	public final static int COLL_PRJ=8;

	private static int _COUNT=0;
	public final int id;
	protected int colltype;
	public int colltype() { return colltype; }
	protected Vec2D size;
	protected double mass;
	public double mass() {return mass; }
	protected ArrayDeque<CollEvent> collisions = new ArrayDeque();
	public Vec2D size() { return size; }
	public Vec2D pos,vel;

	Ent(int id) {
		this.id=_COUNT;
		_COUNT++;
	}

	public boolean update(double dt) {
		pos._add(vel.mul(dt));
		return false; //return true if entity should die
	}

	public void collided(CollEvent ev) {
		collisions.add(ev);
	}
}
