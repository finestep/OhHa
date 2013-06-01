package base.ents;

/**
 * Entities with health should implement this
 */
public interface IHurtable {
	public double getHealth();
	public void setHealth(double health);
}
