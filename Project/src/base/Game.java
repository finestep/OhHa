package base;

import base.managers.DrawMan;
import base.managers.EntMan;
import base.managers.StateMan;

//Game logic container
public class Game {

	public static final EntMan ENTMAN=new EntMan();
	public static final StateMan STATEMAN=new StateMan();
	public static final DrawMan DRAWMAN=new DrawMan();
	private static IWorldTopology WORLD;
	public static IWorldTopology WORLD() { return WORLD; }
	public static final double TICKS_PER_SECOND=100;
	public static final double FRAMES_PER_SECOND=60;
	public static final String TITLE = "TBA";
	private static boolean exit=false;
	private long start;

	public Game() {

		WORLD = new World();
		DRAWMAN.init();

	}
	public void run() throws Exception {
		double accum=0;
		double dt = 1/TICKS_PER_SECOND;
		double ft = 1/FRAMES_PER_SECOND;
		start=System.currentTimeMillis();
		long startframe= (long) (start+ft);
		long endframe=startframe;
		long sleeptime;
		while ( !exit ) { //perhaps instead have run() simulate a single tick?
			startframe=System.currentTimeMillis();
			sleeptime=startframe-endframe;
			accum+=sleeptime;
			//process input here
			while(accum>dt) { //renderer gave us some time, eat it
				ENTMAN.update(dt);
				//add rules class that determines if game should end
				accum-=dt;
			}
			DRAWMAN.draw_game();
			endframe=System.currentTimeMillis();
			Thread.sleep(Math.max((long)ft*1000-endframe,5)); //constant fps
		}

	}
	public static void quit() {
		System.out.println("Exitting");
		STATEMAN.delAll();
		exit=true;
	}

}
