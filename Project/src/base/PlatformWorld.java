package base;

import base.ents.CharFactory;
import base.ents.Ent;
import base.ents.RocketFactory;
import base.managers.DrawMan;
import util.Vec2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Simple world consisting of floating platforms
 */
public class PlatformWorld implements IWorldTopology {
    private Vec2D globalAcc = new Vec2D(0,100);
    private ArrayList<Vec2D[]> solid = new ArrayList(); //geometry of the world
    private double generated;
    private double prevHeight;

    public PlatformWorld() {
        Vec2D[] box = {null,null};
        box[0] = new Vec2D(10,30);
        box[1] = new Vec2D(60,20);
        solid.add(box);
        generated = 130;
        prevHeight = 30;
    }

    private void generate(double pos) {
        while(pos+Game.DRAWMAN.RES.getWidth()>generated) {
            double pit = 40+40*Game.RAND.nextDouble();
            double width = 60+120*Game.RAND.nextDouble();
            double height = prevHeight+100*(Game.RAND.nextDouble()-.7);
            if(height>250) height-=(250-height);

            Vec2D[] box = {null,null};
            box[0]=new Vec2D(generated+pit+width,prevHeight+height);
            box[1]=new Vec2D(width,30);
            solid.add(box);
            prevHeight = height;
            generated+=pit+width*2;
             if(Game.ents&&Game.RAND.nextDouble()<0.3) {
                Ent enemy = CharFactory.make_enemy(new Vec2D(generated-width*.2,height-50) );
                Game.STATEMAN.add_ent(enemy);
            }
        }
    }


    @Override
    public boolean environmentHook(Ent e,double dt) {
        e.vel._add(globalAcc.mul(dt));
        if(e.pos.x+Game.DRAWMAN.RES.getWidth()>generated ) generate(e.pos.x);
        return e.pos.y > 450;
    }


    @Override
    public Vec2D[] getClosestAABB(Vec2D pos ) {
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

    @Override
    public void draw(Graphics2D g,Vec2D cam, Dimension res) {
        Iterator<Vec2D[]> iter = solid.iterator();
        while(iter.hasNext()) {
            Vec2D[] box = iter.next();
            while(box[0].x+box[1].x<cam.x-res.getWidth()) continue;
            g.setColor(Color.black);
            int x=(int)(box[0].x+cam.x-box[1].x);
            int y=(int)(box[0].y+cam.y-box[1].y);
            int w=(int)(box[1].x*2);
            int h=(int)(box[1].y*2);
            g.drawRect(x,y,w,h);
            if(x+w>res.getWidth()) return;
        }
    }

}
