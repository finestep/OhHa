package base.ents;

import base.ents.Ent;
import util.Vec2D;

/**
 * Gun for Ent_Chars to hold
 */
abstract public class Ent_Gun extends Ent { //todo don't inherit Ent
	protected Ent_Char parent;
	private boolean delete=false;
	protected Vec2D offset;

	protected Ent_Gun(Ent_Char p) {
		super();
		collclass=COLL_NONE;
		colltype=COLL_NONE;
		parent=p;

	}

	@Override
	public boolean update(double dt) {
		vel=parent.vel;
		pos.x=parent.pos.x+offset.x*(parent.side?-1:1);
		pos.y=parent.pos.y+offset.y;
		return delete;
	}


	/**
	 * How much ammo does the gun have
	 * @return How many times the gun can be fired at full rate until it is empty, 0 if the next fire() will fail
	 */
	abstract public int getAmmo();

	/**
	 * Fires the primary function of the gun
	 * @return was the desired effect reached
	 */
	abstract public boolean fire();

	/**
	 * Fires the secondary function of the gun
	 * @return  was the desired effect reached
	 */
	abstract public boolean fire2();

	public void remove() {
		delete=true;
	}

}

