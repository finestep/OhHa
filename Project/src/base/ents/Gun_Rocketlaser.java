package base.ents;

import base.Game;
import util.Vec2D;

import java.awt.*;

/**
 * A run-of-the-mill rocket launcher / laser cannon hybrid
 */
public class Gun_Rocketlaser extends Ent_Gun {
	private static int  maxAmmo=3;
	private int ammo=maxAmmo;
	private static double reload=900,rate=350;
	private double refire=0,idle=0;
	private static Vec2D vertboost = new Vec2D(0,-15);
    private boolean lasered=false;

	public Gun_Rocketlaser(Ent_Char p) {
		super(p);
		size=new Vec2D(10,5);
		offset=new Vec2D(5,2);
		mass=0;
		vel=parent.vel;
		pos=parent.pos.add(offset);
	}

	@Override
	public boolean fire() {
		if(ammo<=0||refire>0||lasered) return false;
		refire=rate;
		idle=0;
		ammo--;
		Vec2D dirvec = new Vec2D((parent.side?-1:1),0);
		Ent r = RocketFactory.create_fragrocket(pos.add(dirvec.mul(6)),dirvec.mul(100).add(vertboost),parent);
		r.vel._add(parent.vel.mul(.5));
		Game.STATEMAN.add_ent(r);
		parent.vel._add(dirvec.mul(-10));
		return true;
	}

	@Override
	public boolean fire2() {
        if(ammo<2||refire>0||lasered) return false;
        refire=rate;
        idle=0;
        ammo-=2;
        lasered=true;
		Ent laser = new Ent_Laser(pos.clone(),parent.side);
        Game.STATEMAN.add_ent(laser);
		return true;
	}

	@Override
	public boolean update(double dt) {
		if(refire>0) {
			idle=0;
			refire-=dt*1000;
		} else {
			refire=0;
			idle+=dt*1000;
			if(idle>=reload) idle = reload;
		}
		if(ammo<maxAmmo&&idle>=reload) {
			ammo++;
			idle=0;
		}
        if(ammo==maxAmmo) lasered=false;
		return super.update(dt);
	}

	@Override
	public int getAmmo() {
		if(refire>0||lasered) return 0;
		return ammo;
	}

	public void draw(Graphics2D g, Vec2D cam, Dimension res) {
		g.setColor(Color.GRAY);
		int x = (int)Math.round(pos.x+cam.x-size.x);
		int y = (int)Math.round(pos.y+cam.y-size.y);
		int w = (int)(size.x)*2;
		int h = (int)(size.y)*2;
		g.fillRect(x,y,w,h);

		//hud drawing todo move hud drawing elsewhere?
		g.setColor(Color.BLACK);
		y -= 4;
		x+=(!parent.side)?size.x:0;
		w = (int)(size.x*(refire/rate));
		g.drawLine(x,y,x+w,y);
		y-=2;
		g.setColor(Color.BLUE);
        if(lasered) g.setColor(Color.RED);
		w = (int)(size.x*((double)ammo/maxAmmo));
		g.drawLine(x,y,x+w,y);
		g.setColor(Color.ORANGE);
		y-=3;
		w = (int)(size.x*(idle/reload));
		g.drawLine(x,y,x+w,y);
	}
}
