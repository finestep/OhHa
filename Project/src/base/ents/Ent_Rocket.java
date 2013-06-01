package base.ents;

import base.CollEvent;
import base.Game;
import util.Vec2D;

import java.awt.*;
import java.util.Iterator;
import java.util.HashSet;

/**
 * It's a rocket.
 */
public class Ent_Rocket extends Ent {
	public boolean dir; //direction facing, true for left todo change to angle in radians
	public final RocketTypeEnum type;
	private double vdrag=1/Math.pow(0.666, Game.TICKS_PER_SECOND);
	private double accel,push,damage,radius;
	private boolean exploded=false;
	HashSet<Integer> ignore=new HashSet();

	public Ent_Rocket(RocketTypeEnum t) {
		type=t;
	}

	@Override
	public boolean update(double dt) {
		vel.y*=vdrag;
		vel.x+=(dir?-1:1)*accel*dt;
		super.update(dt);
		return exploded;
	}

	@Override
	public void handleCollisions() {
		Iterator<CollEvent> iter = collisions.iterator();
		while(iter.hasNext()) {
			CollEvent ev = iter.next();
			if(!ignore.contains(ev.id)) explode();
			break; //whatever we're done here
		}
	}

	private void explode() {
		exploded=true;
		Iterator<Ent> iter = Game.STATEMAN.getEntIter();
		while(iter.hasNext()) {
			Ent e = iter.next();
			if(e.id==id) continue;
			Vec2D d = e.pos.add(pos.neg());
			double dist = d.length();
			if(dist>radius) continue;
			if(e.mass()>0) {
				e.vel._add(d.mul(Math.pow(dist,-3)*push/e.mass));
				e.vel.y-=push*.06;
			}
			if(e instanceof IHurtable) {
				((IHurtable) e).setHealth(((IHurtable) e).getHealth() - dist / radius * damage);
			}
		}

	}

	@Override
	public void draw(Graphics2D g, Vec2D cam, Dimension res) {
		g.setColor(Color.YELLOW);
		int x=(int)(pos.x-size.x+cam.x);
		int y=(int)(pos.y-size.y+cam.y);
		int w=(int)size.x*2;
		int h=(int)size.y*2;
		g.fillRect(x,y,w,h);

	}

}
