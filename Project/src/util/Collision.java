package util;

/**
AABB Collision routines
 */
public class Collision {
	/**
	 * Returns the smallest penetration vector between two Axis Aligned Bounding Boxes, or null vector if no overlap occurs
	 * @param p1 Origin of the first aabb
	 * @param p2 Origin of the second aabb
	 * @param s1 Half-width of the first aabb
	 * @param s2 Half-width of the second aabb
	 * @return Minimum translation vector to resolve collision (potentially null vector)
	 */
	public static Vec2D CollAABB(Vec2D p1,Vec2D p2, Vec2D s1, Vec2D s2) {
		double dx = Math.abs(p1.x-p2.x)-s1.x-s2.x;
		if(dx>0) return new Vec2D(); //aabb centers are horizontally further away than their widths' sum
		double dy = Math.abs(p1.y-p2.y)-s1.y-s2.y;
		if(dy>0) return new Vec2D(); //aabb centers are vertically further away than their heights' sum
		if(Math.abs(dy)<Math.abs(dx)) return new Vec2D(0,-dy);
		else return new Vec2D(-dx,0);

	}
	//public static Vec2D CollAABBCircle(Vec2D p1,Vec2D p2,Vec2D s1,float r)
}
