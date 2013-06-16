package base.ents;

import base.Game;
import static base.ControlEnum.*;

/**
 * Reads input from INPUTMAN and moves the player
 */
public class InputSampler implements ICharBrain {
	@Override
	public double movement() {
		double x=0;
		if(Game.INPUTMAN.get(CTRL_LEFT)) x-=1;
		if(Game.INPUTMAN.get(CTRL_RIGHT)) x+=1;
		return x;
	}

	@Override
	public double jump() {
		if(Game.INPUTMAN.get(CTRL_UP)) return 1;
		return 0;
	}

	@Override
	public boolean fireWeapon(Ent_Gun w) {
		if(w.getAmmo()<1) return false;
		if(Game.INPUTMAN.get(CTRL_A)) return true;
		return false;
	}
	@Override
	public boolean fire2Weapon(Ent_Gun w) {
		if(w.getAmmo()<1) return false;
		if(Game.INPUTMAN.get(CTRL_B)) return true;
		return false;
	}

}
