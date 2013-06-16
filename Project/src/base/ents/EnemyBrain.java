package base.ents;

import base.Game;
import util.Collision;
import util.Vec2D;

/**
 * Controls the enemy behaviour
 */
public class EnemyBrain implements ICharBrain {
    private Ent parent;
    public void init(Ent parent) {
        this.parent=parent;
    }

    @Override
    public double movement() {
        return -1;
    }

    @Override
    public double jump() {
        return emptyAhead()?0:1;
    }

    @Override
    public boolean fireWeapon(Ent_Gun wep) {
        return true;
    }

    @Override
    public boolean fire2Weapon(Ent_Gun wep) {
        return false;
    }

    private boolean emptyAhead() {
        Vec2D pos=parent.pos.add(new Vec2D(-parent.size.x*2,parent.size.y*2));
        Vec2D[] worldBox=Game.WORLD().getClosestAABB(pos);
        Vec2D d = Collision.CollAABB(pos,worldBox[0],parent.getSize(),worldBox[1]);
        return d.length()>0;
    }
}
