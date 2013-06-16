package base.ents;

import base.CollEvent;
import util.Vec2D;

import java.awt.*;
import java.util.Iterator;

/**
 * A laser beam
 */
public class Ent_Laser extends Ent {
    private boolean dir;
    private static double basedmg=30,dmgmax=50,lifetime=1,width=500,vertTime=0.7,height=4,horTime=1.5;
    private static Color col = Color.RED;
    private double dmg=0;
    private double life;
       public Ent_Laser(Vec2D pos,boolean dir) {
           super();
           this.pos=pos;
           this.dir=dir;
           vel=new Vec2D();
           life=lifetime;
           mass=0;
           colltype=COLL_NONE;
           collclass=COLL_PRJ|COLL_ENM;
           size=new Vec2D(1,1);
       }

    @Override
    public boolean update(double dt) {
        if(size.x<width) {
            size.x+=(width/vertTime)*dt;
            if(dir) {
                pos.x-=width/vertTime*dt;
            } else {
                pos.x+=width/vertTime*dt;
            }
        }
        if(size.y<height) size.y+=(height/horTime)*dt;

        handleCollisions();
        life-=dt;
        dmg=(basedmg+(lifetime-life)/lifetime*(dmgmax-basedmg))*dt;
        return life<0;
    }

    @Override
    protected void handleCollisions() {
        Iterator<CollEvent> iter = collisions.iterator();
        while(iter.hasNext()) {
            Ent e = iter.next().e;
            if(e instanceof IHurtable) ((IHurtable) e).setHealth(((IHurtable) e).getHealth()-dmg);
            iter.remove();
        }
    }

    @Override
    public void draw(Graphics2D g,Vec2D cam, Dimension res) {
        int x=(int)Math.round(pos.x+cam.x-size.x);
        int y=(int)Math.round(pos.y+cam.y-size.y);
        int w=(int)size.x*2;
        int h=(int)size.y*2;

        g.setColor(col);

        g.fillRect(x,y,w,h);
    }
}
