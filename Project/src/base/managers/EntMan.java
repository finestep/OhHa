package base.managers;

import base.CollEvent;
import base.Game;
import base.ents.Ent;
import util.Collision;
import util.Vec2D;

import java.util.Iterator;

/**
 * Updates entities
 */
public class EntMan {
	private static Ent PLAYER=null;
	public Ent PLAYER() { return PLAYER; }

	/**
	 * Set the player pointer
	 * @param e entity to set as the player
	 */
	public void setPlayer(Ent e) {
		PLAYER=e;
		System.out.println("New player "+e);
	}

	public EntMan() {
	}

	/**
	 * Call the entities' update() and remove them if necessary
	 * @param dt deltatime to use for the update
	 */
	public void update(double dt) {
		Iterator<Ent> iter = Game.STATEMAN.getEntIter();

		while(iter.hasNext()) {
			Ent e = iter.next();
			Game.WORLD().environmentHook(e,dt); //gravity et al happens here
			int coll = e.colltype();
			if(coll!=Ent.COLL_NONE) {   //collides with anything in the first place?
				if((coll&Ent.COLL_WRLD)!=0) {  //check collision with world?
					collideWorld(e);
				}
				entColls(e);  //check for collisions with the rest of the ents
			}

			if(e.update(dt)) {
				iter.remove();
				System.out.println("Removed "+e);
			}
		}
	}

	private void collideWorld(Ent e) { //check the entity's collision against the world geometry
		Vec2D[] worldBox=Game.WORLD().getClosestAABB(e.pos);
		Vec2D d = Collision.CollAABB(e.pos,worldBox[0],e.size(),worldBox[1]);
		if(d.length()>0) {
			CollEvent ev=new CollEvent(Ent.ENT_WORLD,null,e.pos,worldBox[0],e.vel,new Vec2D(),d);
			e.collided(ev);
		}

	}
	private void entColls(Ent e) { //test ents against each other
		Iterator<Ent> iter = Game.STATEMAN.getEntIter();

		while(iter.hasNext()) {
			Ent b = iter.next();
			int mask=~1;
			if(b.id==e.id || ( b.colltype()&mask&(e.collclass()&mask) )==0  ) continue;
			//	||e.vel.unit().dot(b.pos.sub(e.pos).unit())<-.1) continue;
			Vec2D d = Collision.CollAABB(e.pos,b.pos,e.size(),b.size());
			if(d.length()>0) {
				CollEvent ev1=new CollEvent(b.id,b,e.pos,b.pos,e.vel,b.vel,d.mul(-.5));
				CollEvent ev2=new CollEvent(e.id,e,b.pos,e.pos,b.vel,e.vel,d.mul(.5));
				e.collided(ev1);
				b.collided(ev2);
			}
		}
	}
}
