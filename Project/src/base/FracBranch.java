package base;

import util.Vec2D;

import java.awt.*;
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

    public FracBranch getBranch(int id) {
        Iterator<FracBranch> iter = getBranchIterator();
        while(iter.hasNext()) {
            FracBranch branch = iter.next();
            if(branch.id==id) return branch;
        }
        return null;
    }

	public void init() {
		if(initialized) System.out.println("Attempted to initialize "+toString()+"twice");

        Vec2D[] edge = {null,null};
        edge[0]=new Vec2D(origin-20,depth*100);
        edge[1]=new Vec2D(20,100);
        solid.add(edge);

		double w = 40+80*Game.RAND.nextDouble();  //width of the first edge
		Vec2D[] firstbox = {new Vec2D(origin+w/2,depth*100+30),new Vec2D(w/2,30)}; //todo make these actual constants
		solid.add(firstbox);
		double x = w; //current position of the edge

		double endwidth = 40+80*Game.RAND.nextDouble();
		int i=0;

		while(x<width-endwidth-40) {
			double p = 40+60*Game.RAND.nextDouble();
			Vec2D[] box = {null,null};
			if(width-x-p>endwidth) {
				FracBranch branch = new FracBranch(i,p*30,x,depth+1);
				branches.add(branch);
				i++;

				x+=p;
				w = 80+90*Game.RAND.nextDouble();
				box[0] = new Vec2D(origin+x+w/2,depth*100+60);
                box[1]=new Vec2D(w/2,60);
                x+=w;
			}
			else {
				box[0]=new Vec2D(origin+x+endwidth,depth*100+60);
                box[1]=new Vec2D(endwidth/2,60);
			}

            solid.add(box);
		}
        Vec2D[] endEdge = {null,null};
        endEdge[0]=new Vec2D(x+endwidth+20,depth*100);
        endEdge[1]=new Vec2D(20,100);
        solid.add( endEdge);
	initialized=true;
	}

    public Vec2D[] getBox(Vec2D pos ) {
        double dist = Double.POSITIVE_INFINITY;
        Iterator<Vec2D[]> iter = solid.iterator();
        Vec2D[] foundBox=null;
        while(iter.hasNext()) {
            Vec2D[] box = iter.next();
            double d = (box[0].sub(pos) ).length();
            if(d<dist) {
                foundBox=box;
                dist=d;
            }
        }
        return foundBox;
    }

    public void draw(Graphics2D g,Vec2D cam, Dimension res) {
        Iterator<Vec2D[]> iter = solid.iterator();
        int lastX=-1;
        while(iter.hasNext()) {
            Vec2D[] box = iter.next();
            g.setColor(Color.black);
            int x=(int)(box[0].x+cam.x-box[1].x);
            int y=(int)(box[0].y+cam.y-box[1].y);
            int w=(int)(box[1].x*2);
            int h=(int)(box[1].y*2);
            g.drawLine(lastX,y+h,x,y+h);
            g.drawLine(x,y+h,x,y);
            g.drawLine(x,y,x+w,y);
            g.drawLine(x+w,y,x+w,y+h);
            lastX=x+w;
            if(x+w>res.getWidth()) return;
        }
    }
}
