package base.managers;

import base.ControlEnum;
import base.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Holds information about controls
 */
public class InputMan extends KeyAdapter {
	private HashMap<ControlEnum,Boolean> ctrls = new HashMap();

	/**
	 * initializes the manager
	 */
	public void init() {
		for(ControlEnum i : ControlEnum.values() ) {
			ctrls.put(i,false);
		}
	}

	@Override
	public void keyPressed(KeyEvent ev) {
		if(Game.CONFIGMAN.DEBUG_INPUT) System.out.println("pressed "+ev.getKeyCode());
		if(ev.getKeyCode()==KeyEvent.VK_B) Game.CONFIGMAN.BLUR_ENABLE = !Game.CONFIGMAN.BLUR_ENABLE;
		if(ev.getKeyCode()==KeyEvent.VK_ESCAPE) Game.quit();
		ControlEnum c= Game.CONFIGMAN.keyToCtrl(ev.getKeyCode()); //what is it mapped to
		if(c!= ControlEnum.CTRL_NONE) ctrls.put(c,true); //if it's an actual control we care about, update it
	}

	@Override
	public void keyReleased(KeyEvent ev) {
		ControlEnum c=Game.CONFIGMAN.keyToCtrl(ev.getKeyCode());
		if(c!= ControlEnum.CTRL_NONE) ctrls.put(c,false); //should always happen but eh
	}

	/**
	 * Returns true if a control is active
	 * @param c control to check
	 * @return boolean true if pressed
	 */
	public boolean get(ControlEnum c) {
		return ctrls.get(c);
	}

}
