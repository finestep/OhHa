package base.ents;

/**
 * Entities with health should implement this; the functions are self-explanatory
 */
public interface IHurtable {
	public double getHealth();
	public void setHealth(double health);
}
