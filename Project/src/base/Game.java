package base;

import base.managers.DrawMan;
import base.managers.EntMan;
import base.managers.StateMan;
import base.managers.InputMan;
import base.managers.ConfigMan;

/**
 * High-level game logic routine, container for global manager references
 */
public class Game {

	public static final EntMan ENTMAN=new EntMan();
	public static final StateMan STATEMAN=new StateMan();
	public static final DrawMan DRAWMAN=new DrawMan();
	public static final InputMan INPUTMAN=new InputMan();
	public static final ConfigMan CONFIGMAN=new ConfigMan();
	private static IWorldTopology WORLD;
	public static IWorldTopology WORLD() { return WORLD; }
	public static final double TICKS_PER_SECOND=100;
	public static final double FRAMES_PER_SECOND=60;
	public static final String TITLE = "TBA";
	private static boolean exit=false;
	private long start;

	public Game() {
		CONFIGMAN.init();
		WORLD = new World();
		INPUTMAN.init();
		DRAWMAN.init();

	}
	public void run() throws Exception {
		double accum=0;
		double dt = 1/TICKS_PER_SECOND;
		double ft = 1/FRAMES_PER_SECOND;
		start=System.currentTimeMillis();
		long startframe= (long) (start+ft);
		long endframe=start;
		long sleeptime;
		while ( !exit ) { //perhaps instead have run() simulate a single tick?
			startframe=System.currentTimeMillis();
			sleeptime=startframe-endframe;
			accum+=sleeptime;
			while(accum>dt*1000) { //renderer gave us some time, eat it
				ENTMAN.update(dt);
				//todo add rules class that determines if game should end
				accum-=dt*1000;
			}
			DRAWMAN.draw_game();
			endframe=System.currentTimeMillis();
			Thread.sleep(Math.max((long)ft*1000-endframe,5)); //constant fps
		}

	}
	public static void quit() { //todo find out a proper way to do this
		System.out.println("Exitting");
		STATEMAN.delAll();
		exit=true;
	}

}
