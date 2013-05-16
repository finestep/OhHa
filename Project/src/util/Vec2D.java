package util;

/**
Basic 2 dimensional vector class
 Default constructor returns null vector
 */
public class Vec2D {
	public double x=0,y=0;
	public Vec2D(){}
	public Vec2D(double x,double y) {this.x=x;this.y=y;}
	@Override
	public Vec2D clone() {
		return new Vec2D(x,y);
	}

	/**
	 * Vector sum
	 * @param b The other vector of the sum operation
	 * @return Reference to new vector (this + b)
	 */
	public Vec2D add(Vec2D b) {
		return new Vec2D(x+b.x,y+b.y);
	}

	/**
	 * Vector scalar multiplication
	 * @param a Float to multiply the vector with
	 * @return Reference to new vector a*this
	 */
	public Vec2D mul(double a) {
		return new Vec2D(x*a,y*a);
	}

	/**
	 * Shorthand for -1*this
	 * @return Reference to new vector
	 */
	public Vec2D neg() {
		return new Vec2D(-x,-y);
	}

	/**
	 * Dot product operation of two vectors
	 * @param b Vector to calculate the product with
	 * @return The result of the dot product
	 */
	public double dot(Vec2D b) {
		return x*b.x+y*b.y;
	}

	/**
	 *
	 * @return Euclidean length of the vector
	 */
	public double length() {
		return Math.sqrt(x*x+y*y);
	}

	/**
	 * Calculates the unit vector with the same direction as this
	 * @return A reference to the new vector that has a length of 1
	 */
	public Vec2D unit() throws Exception {
		if (length()<0.000000001) {
			throw new ArithmeticException();
		}
		return clone().mul(1/length());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Vec2D vec2D = (Vec2D) o;

		if (Double.compare(vec2D.x, x) != 0) return false;
		if (Double.compare(vec2D.y, y) != 0) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
