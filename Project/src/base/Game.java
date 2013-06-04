package base;

import base.ents.CharFactory;
import base.ents.Ent;
import base.managers.DrawMan;
import base.managers.EntMan;
import base.managers.StateMan;
import base.managers.InputMan;
import base.managers.ConfigMan;
import util.Vec2D;

/**
 * High-level game logic routine, container for global manager references
 */
public class Game {

	public static final EntMan ENTMAN=new EntMan();
	public static final StateMan STATEMAN=new StateMan();
	public static final InputMan INPUTMAN=new InputMan();
	public static final DrawMan DRAWMAN=new DrawMan();

	public static final ConfigMan CONFIGMAN=new ConfigMan();
	private static IWorldTopology WORLD;
	public static IWorldTopology WORLD() { return WORLD; }
	public static final double TICKS_PER_SECOND=120;
	public static final double FRAMES_PER_SECOND=60;
	public static final String TITLE = "TBA";
	private static boolean exit=false;

	private long start;
	private String[] txt = {null,null,null};

	public Game(boolean plr) {
		CONFIGMAN.init();
		WORLD = new World();
		INPUTMAN.init();
		DRAWMAN.init();
		if(!plr) return;
		Ent player = CharFactory.make_player(new Vec2D(0,-50));
		STATEMAN.add_ent(player);
		ENTMAN.setPlayer(player);

		STATEMAN.add_ent(CharFactory.make_dummy(new Vec2D(150,-50)));

	}
	public void run() throws Exception {
		double accum=0;
		double dt = 1/TICKS_PER_SECOND;
		double ft = 1/FRAMES_PER_SECOND;
		start=System.nanoTime()/1000000;
		long startframe;
		long endframe=(long) (start-ft*1000);
		long sleeptime;
		int n=0;
		double prevn=0;
		while ( !exit ) { //perhaps instead have run() simulate a single tick?
			startframe=System.nanoTime()/1000000;
			sleeptime=startframe-endframe;
			accum+=sleeptime;
			n=0;
			while(accum>dt*1000) { //renderer gave us some time, eat it
				ENTMAN.update(dt);
				STATEMAN.updateList();
				//todo add rules class that determines if game should end
				accum-=dt*1000; //can modify timescale here
				n++;
			}
			//todo add interpolation?

			DRAWMAN.draw_game();
			txt[0]="Framerate: "+(1000./sleeptime);
			txt[1]="Frametime: "+(sleeptime);
			txt[2]="Tickrate: "+(float)(n*1000./sleeptime*.2+prevn*.8);
			DRAWMAN.text_hook(txt);
			DRAWMAN.sync();
			prevn=n*1000./sleeptime*.2+prevn*.8;
			endframe=System.nanoTime()/1000000;
			long sleep = (long)Math.floor(ft * 1000)-(startframe-endframe); //constant fps
			int sleep2 = (int) ((ft-Math.floor(ft*1000)/1000)*1000000);
			Thread.sleep(sleep,sleep2);
		}

	}
	public static void quit() { //todo find out a proper way to do this
		System.out.println("Exitting");
		STATEMAN.delAll();
		DRAWMAN.exit();
		exit=true;
	}

}
