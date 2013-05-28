package base.ents;

/**
 * Instructs Ent_Chars how to move
 */
public interface ICharBrain {
	/**
	 * Decide how much the character should move vertically
	 * @return desired horizontal velocity increase as percentage (-1.0 to 1.0) of runPower (subject to physics limitations)
	 */
	public double movement();

	/**
	 * Decide how fast the character should move upwards
	 * @return desired upwards horizontal velocity increase as percentage (0.0 to 1.0) of jumpPower subject to normalization and physics)
	 */
	public double jump();
}
