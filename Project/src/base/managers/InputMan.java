package base.managers;

import base.EControl;
import base.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Holds information about controls
 */
public class InputMan extends KeyAdapter {
	private HashMap<EControl,Boolean> ctrls = new HashMap();

	public void init() {
		for(EControl i : EControl.values() ) {
			ctrls.put(i,false);
		}
	}

	@Override
	public void keyPressed(KeyEvent ev) {
		EControl c= Game.CONFIGMAN.keyToCtrl(ev.getKeyCode()); //what is it mapped to
		if(c!=EControl.CTRL_NONE) ctrls.put(c,true); //if it's an actual control we care about, update it
	}

	@Override
	public void keyReleased(KeyEvent ev) {
		EControl c=Game.CONFIGMAN.keyToCtrl(ev.getKeyCode());
		if(c!=EControl.CTRL_NONE) ctrls.put(c,false); //should always happen but eh
	}

	/**
	 * Returns if a control is pressed
	 * @param c control to check
	 * @return boolean true if pressed
	 */
	public boolean get(EControl c) {
		return ctrls.get(c);
	}

}
