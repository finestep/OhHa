package base.ents;

import base.Game;
import util.Vec2D;

import java.awt.*;
import java.util.Iterator;

/**
 * A simple gun used by the enemies
 */
public class Gun_EnemyGun extends Ent_Gun {
    private static int  maxAmmo=1;
    private int ammo=maxAmmo;
    private static double reload=1200,rate=10;
    private double refire=0,idle=0;
    private static Vec2D vertboost = new Vec2D(0,-18);

    public Gun_EnemyGun(Ent_Char p) {
        super(p);
        size=new Vec2D(10,5);
        offset=new Vec2D(5,2);
        mass=0;
        vel=parent.vel;
        pos=parent.pos.add(offset);
    }

    @Override
    public boolean fire() {
        if(ammo<=0||refire>0) return false;
        refire=rate;
        idle=0;
        ammo--;
        Vec2D dirvec = new Vec2D((parent.side?-1:1),0);
        Ent r = RocketFactory.create_herocket(pos.add(dirvec.mul(6)), dirvec.mul(80).add(vertboost), parent);
        Iterator<Ent> iter = Game.STATEMAN.getEntIter();
        while(iter.hasNext()) {
            Ent e = iter.next();
            if(e.getCollclass()==Ent.COLL_NONE) continue;
            if(e instanceof Ent_Char) {
                if(((Ent_Char) e).type==parent.type) parent.ignore(e.id);
            }
        }
        r.vel._add(parent.vel.mul(.5));
        Game.STATEMAN.add_ent(r);
        parent.vel._add(dirvec.mul(-15));
        return true;
    }

    @Override
    public boolean fire2() {
        return false;
    }

    @Override
    public boolean update(double dt) {
        if(refire>0) {
            idle=0;
            refire-=dt*1000;
        } else {
            refire=0;
            idle+=dt*1000;
            if(idle>=reload) idle = reload;
        }
        if(ammo<maxAmmo&&idle>=reload) {
            ammo++;
            idle=0;
        }
        return super.update(dt);
    }

    @Override
    public int getAmmo() {
        if(refire>0) return 0;
        return ammo;
    }

    public void draw(Graphics2D g, Vec2D cam, Dimension res) {
        g.setColor(Color.GRAY);
        int x = (int)Math.round(pos.x+cam.x-size.x);
        int y = (int)Math.round(pos.y+cam.y-size.y);
        int w = (int)(size.x)*2;
        int h = (int)(size.y)*2;
        g.fillRect(x,y,w,h);
    }
}
