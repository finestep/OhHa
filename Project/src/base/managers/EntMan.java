package base.managers;

import base.CollEvent;
import base.Game;
import base.ents.Ent;
import util.Collision;
import util.Vec2D;

import java.util.Iterator;

/**
 * Updates gamestate
 */
public class EntMan {
	//private static Ent PLAYER;
	//public Ent player() { return PLAYER; }
	public void update(double dt) {
		Iterator<Ent> iter = Game.STATEMAN.getEntIter();

		while(iter.hasNext()) {
			Ent e = iter.next();

			int coll = e.colltype();
			if(coll!=Ent.COLL_NONE) {   //collides with anything in the first place?
				if((coll&Ent.COLL_WRLD)!=0) {  //check collision with world?
					collideWorld(e);
				}

				entColls(e);  //check for collisions with the rest of the ents
			}
			if(e.update(dt)) iter.remove();
		}
	}

	public void collideWorld(Ent e) {
		Vec2D[] worldBox=Game.WORLD().getClosestAABB(e.pos);
		Vec2D d = Collision.CollAABB(e.pos,e.size(),worldBox[0],worldBox[1]);
		if(Double.compare(d.length(),0)!=0) {
			CollEvent ev=new CollEvent(Ent.ENT_WORLD,null,e.pos,null,e.vel,null,d);
			e.collided(ev);
		}

	}
	public void entColls(Ent e) {
		Iterator<Ent> iter = Game.STATEMAN.getEntIter();

		while(iter.hasNext()) {
			Ent b = iter.next();
			if(b==e || ( b.colltype()&e.colltype() )==0 ) continue;
			Vec2D d = Collision.CollAABB(e.pos,e.size(),b.pos,b.size());
			if(Double.compare(d.length(),0)!=0) {
				CollEvent ev1=new CollEvent(b.id,b,e.pos,b.pos,e.vel,b.vel,d);
				CollEvent ev2=new CollEvent(e.id,e,b.pos,e.pos,b.vel,e.vel,d);
				e.collided(ev1);
				b.collided(ev2);
			}
		}
	}
}
