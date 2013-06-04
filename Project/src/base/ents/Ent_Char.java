package base.ents;

import base.CollEvent;
import base.Game;
import util.Vec2D;

import java.awt.*;
import java.util.Iterator;

/**
 * Basic actor entity
 */
public class Ent_Char extends Ent implements IHurtable {
	public Color col;
	private ICharBrain brain;
	private double maxVel=400,runPower,jumpPower;
	boolean grounded=false;
	public final CharTypeEnum type;
	double fric = Math.pow(0.2,1/Game.TICKS_PER_SECOND);

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	private double health;
	protected Ent_Gun weapon=null;
	boolean side; //true if the character is facing left

	public Ent_Char(CharTypeEnum t,ICharBrain b,
					Vec2D s,double m,int ct,int cc,
					double r,double j,double h) {
		super(s,m,cc,ct);
		type = t;
		brain=b;
		runPower=r;
		jumpPower=j;
		health=h;
		vel=new Vec2D();
		side=false;
	}

	@Override
	public boolean update(double dt) {
		super.update(dt);
		if(grounded) vel.x*= fric; //todo move to EnvironmentHook

		if(vel.y<0) grounded=false;

		vel.x+=Math.min(1,Math.max(-1,brain.movement()))*runPower*dt*(grounded?1:0);
		vel.y-=Math.abs(Math.min(1,Math.max(0,brain.jump()))*jumpPower)*(grounded?1:0); //how much should the character jump? only jump upwards, when on the ground

		if(weapon!=null) {
			if(brain.fireWeapon(weapon)) weapon.fire();
			if(brain.fire2Weapon(weapon)) weapon.fire2();
			weapon.update(dt);
		}

		side = vel.x<0; //todo ask the brain where to face

		try {
		if(vel.length()>maxVel) vel=vel.unit().mul(maxVel);
		} catch (Exception e) {} //cannot happen when maxVel > 0 todo assert all max*'s are positive

		if(health<0) {
			if(weapon!=null) weapon.remove();
			return true;
		}
		return false;
	}

	@Override
	public void handleCollisions() {
		Iterator<CollEvent> iter = collisions.iterator();
		while(iter.hasNext()) {
			CollEvent ev = iter.next();
			pos._add(ev.d);
			if(ev.e==null) {
				if(Math.abs(ev.d.x)<0.001) {
					vel.y=0;
					if(!grounded) {
						vel.x*=.5;
						if(Math.abs(vel.y)<0.1) vel.y=0;
						grounded=true;
						//System.out.println("Grounded");
					}
				} else {
					vel.x*=-.1;
					vel.y*=.5;
				}
			} else {

					vel._add( ev.d.unit().mul( vel.dot( ev.d.unit() )/mass*-1.5 ));
				if(grounded) vel.x=0;
				//todo assert that all entities' masses are positive
			}
			iter.remove();
		}
	}

	@Override
	public void draw(Graphics2D g, Vec2D cam,Dimension res) {
		int x=(int)Math.round(pos.x+cam.x-size.x);
		int y=(int)Math.round(pos.y+cam.y-size.y);
		int w=(int)size.x*2;
		int h=(int)size.y*2;

		g.setColor(col);

		g.fillRect(x,y,w,h);
		if(grounded) {
			g.setColor(Color.RED);
			g.drawLine(x,y+h,x+w,y+h);
		}
		if(weapon!=null) weapon.draw(g,cam,res);
	}

}
