package base.ents;

import base.Game;
import util.Vec2D;

import java.awt.*;

import static base.ents.CharTypeEnum.*;
import static base.ents.Ent.*;

/**
 * Facilitates the creation of Ent_Chars
 */
public class CharFactory {
    /**
     * Creates the player character
     * @param p Position of the character to be created
     * @return The created character
     * @throws IllegalStateException Thrown if a player character already exists
     */
	static public Ent make_player(Vec2D p) throws IllegalStateException {
		if(Game.ENTMAN.PLAYER()!=null) throw new IllegalStateException("Attempt to create another player!");
		Ent_Char e = new Ent_Char(CHAR_PLAYER,new InputSampler(),new Vec2D(10,20),1,COLL_WRLD|COLL_PLR,COLL_PRJ,260,150,100);
		e.pos=p;
		e.col= Color.GREEN;
		Ent_Gun gun = new Gun_Rocketlaser(e);
		e.weapon=gun;
		return e;
	}

    /**
     * Creates a dummy character
     * @param p position of the character
     * @return character created
     */
	static public Ent make_dummy(Vec2D p) {
		Ent_Char e = new Ent_Char(CHAR_DUMMY,new NullBrain(),new Vec2D(11,23),1.5,COLL_WRLD|COLL_ENM,COLL_PRJ,0,0,200);
		e.pos=p;
		e.col=Color.blue;
		return e;
	}

    /**
     * Creates an enemy character
     * @param p position of the character
     * @return character created
     */
    static public Ent make_enemy(Vec2D p) {
        EnemyBrain brain = new EnemyBrain();
        Ent_Char e = new Ent_Char(CHAR_ENEMY,brain,new Vec2D(11,23),1.5,COLL_WRLD|COLL_ENM,COLL_PRJ,180,80,150);
        brain.init(e);
        e.pos=p;
        e.col=Color.blue;
        Ent_Gun gun = new Gun_EnemyGun(e);
        e.weapon=gun;
        return e;
    }
}
