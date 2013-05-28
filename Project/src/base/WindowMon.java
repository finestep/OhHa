package base;

import java.awt.event.*;
import java.awt.Window;
/**
 * Window monitor class
 */
public class WindowMon extends WindowAdapter {
	public void windowClosing(WindowEvent e) {

		//quit other mans / enter quit routine
		Game.quit();
	}
}
