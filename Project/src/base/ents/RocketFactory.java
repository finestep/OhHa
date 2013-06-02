package base.ents;

import base.Game;
import util.Vec2D;
import static base.ents.RocketTypeEnum.*;

/**
 * Creates different rocket types
 */
public class RocketFactory {
	static private void init_rocket(Ent_Rocket r,Vec2D p,Vec2D v,int id) {
		r.pos=p;
		r.vel=v;
		if(v.x<0) r.dir=true; else r.dir=false;
		r.ignore(id);
	}

	static public Ent create_fragrocket(Vec2D p,Vec2D v,Ent_Char launcher) {
		Ent_Rocket r = new Ent_Rocket(ROCKET_FRAG,new Vec2D(4,2),150,100,30,100,0.3);
		launcher.ignore(r.id);
		init_rocket(r,p,v,launcher.id);
		return r;
	}
	static public Ent create_herocket(Vec2D p,Vec2D v,Ent_Char launcher) {
		Ent_Rocket r = new Ent_Rocket(ROCKET_HE,new  Vec2D(5,3),210,80,250,70,0.5);
		launcher.ignore(r.id);
		init_rocket(r,p,v,launcher.id);
		return r;
	}
	static public Ent create_impulserocket(Vec2D p,Vec2D v,Ent_Char launcher) {
		Ent_Rocket r = new Ent_Rocket(ROCKET_IMPULSE,new  Vec2D(3,2),40,350,20,50,0.1);
		launcher.ignore(r.id);
		init_rocket(r,p,v,launcher.id);
		return r;
	}
}
