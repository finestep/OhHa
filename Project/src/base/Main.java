package base;

import base.managers.DrawMan;

/**
 * Date: 14.5.2013
 * Time: 17:57
 * Entrypoint class
 */
public class Main {
	public static void main(String[] arg) throws Exception  {
		System.out.println("Initializing");
		Game GAME = new Game(true);
		GAME.run();
		System.out.println("The end");
	}
}
