package base;

import java.awt.event.*;
import java.awt.Window;
/**
 * Window monitor class
 */
public class WindowMon extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		Window w = e.getWindow();
		w.setVisible(false);
		w.dispose();
		//quit other mans / enter quit routine
		Game.quit();
	}
}
