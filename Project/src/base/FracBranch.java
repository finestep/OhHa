package base;

import util.Vec2D;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Describes a single branch of the fractal world
 */
public class FracBranch {
	private ArrayList<FracBranch> branches = new ArrayList(); //further branches below this
	private ArrayList<Vec2D[]> solid = new ArrayList(); //actual geometry
	public final double width; //actual width in pixels
	public final double origin; // horizontal start position relative to 0 depth origin
	public final int depth;

	public final int id; //local branch identifier

	public FracBranch(int id,double width, double origin, int depth) {
		this.id=id;
		this.width = width;
		this.origin = origin;
		this.depth = depth;
	}

	private boolean initialized=false;
	public boolean isInitialized() {
		return initialized;
	}

	public Iterator getBranchIterator() {
		return branches.iterator();
	}

	public void init() {
		if(initialized) System.out.println("Attempted to initialize "+toString()+"twice");


		double w = 40+40*Game.RAND.nextDouble();  //width of the first edge
		Vec2D[] firstbox = {new Vec2D(origin+w/2,depth*100+30),new Vec2D(w/2,30)}; //todo make these actual constants
		solid.add(firstbox);
		double x = w; //current position of the edge

		double endwidth = 40+40*Game.RAND.nextDouble();
		int i=0;

		while(x<width-endwidth-40) {
			double p = 40+60*Game.RAND.nextDouble();
			Vec2D[] box = {null,null};
			if(width-x-p>endwidth) {
				FracBranch branch = new FracBranch(i,p*50,x,depth+1);
				branches.add(branch);
				i++;

				x+=p;
				w = 30+50*Game.RAND.nextDouble();
				box[0] = new Vec2D(origin+x+w/2,depth*100+30);
				box[1] = new Vec2D(w/2,30);
				solid.add(box);
			}
			else {
				box[0]=new Vec2D(origin+x+endwidth,depth*100+30);
				box[1]=new Vec2D(w/2,30);
				solid.add(box);
			}
		}
	initialized=true;
	}
}
