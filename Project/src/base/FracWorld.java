package base;

import base.ents.Ent;
import util.Vec2D;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;
import java.util.ArrayList;

/**
 * A self-similar fractal world
 */
public class FracWorld implements IWorldTopology {

	private ArrayList<Integer> playerPath = new ArrayList();
	private Vec2D globalacc = new Vec2D(0,-100);
	private FracBranch base = new FracBranch(0,4096,0,0);


	private FracBranch currentBranch = base;

	public FracWorld() {
		base.init();
	}


	private int branchID(double posx, int depth) {
		Iterator<FracBranch> iter = currentBranch.getBranchIterator();
		double dist = Double.POSITIVE_INFINITY;
		int id = -1;
		while (iter.hasNext()) {
			FracBranch b = iter.next();
			double d = Math.abs(b.origin+b.width/2-posx);
			if(d<dist) {
				dist =  d;
				id = b.id;
			}

		}
		return id;
	}

	private int depth(double ypos) {
		if(ypos<0) return 0;
		else return (int)Math.floor(ypos/100);
	}

	@Override
	public void environmentHook(Ent e,double dt) {
		e.vel._add(globalacc.mul(dt));
		if(e.id==Game.ENTMAN.PLAYER().id) {
			if(depth(e.pos.y)>playerPath.size()) { //player jumped down
				playerPath.add(branchID(e.pos.x,playerPath.size()));
			}
		}

	}

	@Override
	public Vec2D[] getClosestAABB(Vec2D pos) {
		return null; //todo traverse world tree
	}

	@Override
	public void draw(Graphics2D g,Vec2D campos, Dimension res) {
		//todo fracworld drawing
	}

}
