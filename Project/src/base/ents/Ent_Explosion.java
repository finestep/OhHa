package base.ents;

import util.Vec2D;

import java.awt.*;

/**
 * An explosion effect
 */
public class Ent_Explosion extends Ent {
    private double life,lifetime;
    public Ent_Explosion(Vec2D pos,double rad,double life) {
        size=new Vec2D(rad,rad);
        this.pos = pos;
        mass=0;
        colltype=COLL_NONE;
        collclass=COLL_NONE;
        vel=new Vec2D();
        this.lifetime=life;
        this.life=life;
    }
    @Override
    public boolean update(double dt) {
        life-=dt;
        return life<0;
    }

    @Override
    public void draw(Graphics2D g,Vec2D cam, Dimension res) {
        g.setColor(Color.ORANGE);
        double alpha = (1-life/lifetime);
        int x = (int)(pos.x-alpha*size.x+cam.x);
        int y= (int) (pos.y-alpha*size.y+cam.y);
        int w= (int) (alpha*size.x*2);
        int h= (int) (alpha*size.y*2);
        g.fillOval(x,y,w,h);
    }
}
