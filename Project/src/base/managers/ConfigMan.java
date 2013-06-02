package base.managers;

import base.ControlEnum;

import java.awt.event.KeyEvent;

import java.util.HashMap;

/**
 * Loads, holds and saves user configuration (eventually)
 **/
public class ConfigMan {
	public static boolean DEBUG_INPUT = false;
	public static boolean BLUR_ENABLE = false;
	private HashMap<Integer, ControlEnum> keys = new HashMap();

	//default keys
	public void init() {
		keys.put(KeyEvent.VK_UP, ControlEnum.CTRL_UP);
		keys.put(KeyEvent.VK_LEFT, ControlEnum.CTRL_LEFT);
		keys.put(KeyEvent.VK_RIGHT, ControlEnum.CTRL_RIGHT);
		keys.put(KeyEvent.VK_Z, ControlEnum.CTRL_A);
		keys.put(KeyEvent.VK_X, ControlEnum.CTRL_B);
	}
	//todo loadsave ini

	public ControlEnum keyToCtrl(int key) {
   		if(!keys.containsKey(key)) return ControlEnum.CTRL_NONE;
		return keys.get(key);
	}
}
