package base.ents;

/**
 * Dummy brain that does nothing
 */
public class NullBrain implements ICharBrain {
	@Override
	public double movement() {return 0;}
	@Override
	public double jump() {return 0;}
	@Override
	public boolean fireWeapon(Ent_Gun wep) {return false;}
	@Override
	public boolean fire2Weapon(Ent_Gun wep) {return false;}
}
