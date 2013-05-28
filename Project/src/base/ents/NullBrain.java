package base.ents;

/**
 * Dummy brain that does nothing
 */
public class NullBrain implements ICharBrain {
	@Override
	public double movement() {return 0;}
	@Override
	public double jump() {return 0;}
}
