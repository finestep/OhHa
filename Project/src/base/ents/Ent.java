package base.ents;

import base.CollEvent;
import base.IRenderable;
import util.Vec2D;
import java.util.ArrayDeque;
import java.util.HashSet;

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
	public int colltype() { return colltype; } //what entities does the entity hit
	protected int collclass;
	public int collclass() { return collclass; } //what entities does the entity get hit by
	protected Vec2D size;
	protected double mass;
	public double mass() {return mass; }
	protected ArrayDeque<CollEvent> collisions = new ArrayDeque();
	HashSet<Integer> ignore=new HashSet();
	public Vec2D size() { return size; }
	public Vec2D pos,vel;

	/**
	 * Entities are assigned an unique (ordinal) id upon creation
	 */
	protected Ent() {
		this.id=_COUNT;
		_COUNT++;
	}

	protected Ent(Vec2D s,double m,int cc,int ct) {
		this();
		size=s;
		mass=m;
		collclass=cc;
		colltype=ct;
	}

	/**
	 * Updates, in the very least, the entity's position and handles the collisions it has accumulated
	 * @param dt deltatime of the sim
	 * @return should the entity be removed
	 */
	public boolean update(double dt) {
		handleCollisions(); //subclasses should empty collisions queue here
		pos._add(vel.mul(dt));
		return false; //return true if entity should die

	}

	/**
	 * Entities should clear the queue and respond to the collisions as they best see fit
	 */
	public void handleCollisions() {};

	public void collided(CollEvent ev) {
		if(!ignore.contains(ev.id)) collisions.add(ev);
	}


	public void ignore(int n) {
		ignore.add(n);
	}
}
