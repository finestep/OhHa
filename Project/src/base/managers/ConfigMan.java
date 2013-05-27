package base.managers;

import base.EControl;
import java.awt.event.KeyEvent;

import java.util.HashMap;

/**
 * Loads, holds and saves user configuration (eventually)
 **/
public class ConfigMan {
	private HashMap<Integer,EControl> keys = new HashMap();

	//default keys
	public void init() {
		keys.put(KeyEvent.VK_UP, EControl.CTRL_UP);
		keys.put(KeyEvent.VK_LEFT,EControl.CTRL_LEFT);
		keys.put(KeyEvent.VK_RIGHT,EControl.CTRL_RIGHT);
		keys.put(KeyEvent.VK_A,EControl.CTRL_A);
		keys.put(KeyEvent.VK_S,EControl.CTRL_B);
	}
	//todo loadsave ini

	public EControl keyToCtrl(int key) {
   		if(!keys.containsKey(key)) return EControl.CTRL_NONE;
		return keys.get(key);
	}
}
