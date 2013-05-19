package base.ents;
import util.Vec2D;
/**
 * Abstract entity class
 */
public abstract class Ent {
	//Collision bits
	static int COLL_ALL=0xFFFFFFF;
	static int COLL_WRLD=1;
	static int COLL_PLR=2;
	static int COLL_ENM=4;
	static int COLL_PRJ=8;

	protected int colltype;
	public int colltype() { return colltype; }
	protected Vec2D size;
	public Vec2D size() { return size; }
	public Vec2D pos,vel;

	public boolean update(double dt) {
		pos._add(vel.mul(dt));
		return false; //return true if entity should die
	}
	public abstract void draw();
}
