package test.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import util.Vec2D;

/**
 * Tests for Vec2D
 */
public class Vec2DTest {
	private Vec2D a,b,c;
	@Before
	public void setUp() throws Exception {
		a=new Vec2D();
		b=new Vec2D(1,9.25);
		c=new Vec2D(-5,0);
	}

	@Test
	public void testClone() throws Exception {
		 Vec2D v = a.clone();
		assertEquals(0,v.x,0.000001);
		assertEquals(0,v.y,0.000001);
		v = c.clone();
		assertEquals(-5,v.x,0.000001);
		assertEquals(0,v.y,0.000001);
	}

	@Test
	public void testAdd() throws Exception {
	  	Vec2D v = a.add(b);
		assertEquals(v.x,b.x,0.000001);
		assertEquals(v.y,b.y,0.000001);
		v = v.add(c);
		assertEquals(-4,v.x,0.000001);
		assertEquals(9.25,v.y,0.000001);
		v = b.add(c);
		Vec2D u = c.add(b);
		assertEquals(v.x,u.x,0.000001);
		assertEquals(v.y,u.y,0.000001);
	}

	@Test
	public void testMul() throws Exception {
		Vec2D v = a.mul(9e200);
		assertEquals(v.x,0,0.000001);
		assertEquals(v.y,0,0.000001);
		v = c.mul(0);
		assertEquals(v.x,0,0.000001);
		assertEquals(v.y,0,0.000001);
		v = b.mul(-5e100);
		assertEquals(-5e100,v.x,0.000001);
		assertEquals(9.25*-5e100,v.y,0.000001);
	}

	@Test
	public void testNeg() throws Exception {
		Vec2D v = a.neg();
		assertEquals(0,v.x,0.000001);
		assertEquals(0,v.y,0.000001);
		v = b.neg();
		assertEquals(v.x,-b.x,0.000001);
		assertEquals(v.y,-b.y,0.000001);
		v = c.add(c.neg());
		assertEquals(0,v.x,0.000001);
		assertEquals(0,v.y,0.000001);
	}

	@Test
	public void testDot() throws Exception {
		double x = a.dot(b);
		assertEquals(0,x,0.000001);
		x = a.dot(c);
		assertEquals(0,x,0.000001);
		x = b.dot(c);
		assertEquals(-5,x,0.000001);
		x = c.dot(new Vec2D(2e200,3));
		double y = (new Vec2D(2e200,3)).dot(c);
		assertEquals(x,y,0.000001);
	}

	@Test
	public void testLength() throws Exception {
		double x = a.length();
		assertEquals(0,x,0.000001);
		x = c.length();
		assertEquals(5,x,0.000001);
		x = b.length();
		assertEquals(9.303897,x,0.0001);
	}

	@Test
	public void testUnit() throws Exception {
		double x = c.unit().x;
		assertEquals(-1,x,0.000001);
		Vec2D v = b.mul(5e150).unit();
		assertEquals(1,v.length(),0.000001);

	}
}
