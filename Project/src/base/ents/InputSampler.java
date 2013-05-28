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
		if(Game.INPUTMAN.get(CTRL_LEFT)) x-=.66;
		if(Game.INPUTMAN.get(CTRL_RIGHT)) x+=.66;
		return x;
	}

	@Override
	public double jump() {
		if(Game.INPUTMAN.get(CTRL_UP)) return 1;
		return 0;
	}

}
