package base.ents;

import base.CollEvent;
import base.Game;
import util.Vec2D;

import java.awt.*;
import java.util.Iterator;

/**
 * It's a rocket.
 */
public class Ent_Rocket extends Ent implements IHurtable {
	public boolean dir; //direction facing, true for left todo change to angle in radians
	public final RocketTypeEnum type;
	private double lift=4;
	private double accel,push,damage,radius;
	private boolean exploded=false;
	private double lifetime;

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	private double health;
	static private Color col = new Color(40,40,40);


	public Ent_Rocket(RocketTypeEnum t,Vec2D s,double acc,double push, double dam, double r,double m) {
		type=t;
		size=s;
		accel=acc;
		this.push=push;
		damage=dam;
		radius=r;
		mass=m;
		colltype=COLL_WRLD|COLL_PRJ;
		collclass=COLL_PLR|COLL_ENM;
		lifetime=3;
		health=7;
	}

	@Override
	public boolean update(double dt) {
		vel.y-=lift*Math.sqrt(Math.abs(vel.x))*dt;   //todo make perpendicular to vel direction
		vel.x+=(dir?-1:1)*accel*dt;
		lifetime-=dt;
          if(Game.RAND.nextDouble()<0.07) {
            Ent e = new Ent_Explosion(pos.clone(),4,0.5+Game.RAND.nextDouble());
            Game.STATEMAN.add_ent(e);
        }
		super.update(dt);
		if(lifetime<0||health<0) explode();
		return exploded;
	}

	@Override
	public void handleCollisions() {
		Iterator<CollEvent> iter = collisions.iterator();
		while(iter.hasNext()) {
			CollEvent ev = iter.next();
			if(!ignore.contains(ev.id)) {
				explode();
				break; //whatever we're done here
			}
		}
	}

	private void explode() {
		exploded=true;
		Iterator<Ent> iter = Game.STATEMAN.getEntIter();
        Ent expl = new Ent_Explosion(pos.clone(),radius*.8,0.2);
        Game.STATEMAN.add_ent(expl);
		while(iter.hasNext()) {
			Ent e = iter.next();
			if(e.id==id) continue;
			Vec2D d = e.pos.add(pos.neg());
			double dist = d.length();
			if(dist>radius) continue;
			if(e.getMass()>0) {
				e.vel._add(d.mul(Math.pow(dist,-2)*push*60/e.mass));
				e.vel.y-=push*.2;
			}
			if(e instanceof IHurtable) {
				double dmg = Math.max(5,(radius-dist) / radius * damage );
				((IHurtable) e).setHealth(((IHurtable) e).getHealth() - dmg);
			}
		}

	}

	@Override
	public void draw(Graphics2D g, Vec2D cam, Dimension res) {
		g.setColor(col);
		int x=(int)(pos.x-size.x+cam.x);
		int y=(int)(pos.y-size.y+cam.y);
		int w=(int)size.x*2;
		int h=(int)size.y*2;
		g.fillRect(x,y,w,h);

	}


}
