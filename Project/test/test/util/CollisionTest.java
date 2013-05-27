package test.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import util.Vec2D;
import util.Collision;
/**
 * Tests some AABB collision scenarios
 */
public class CollisionTest {
	private Vec2D p1,p2,s1,s2;
	@Before
	public void setUp() {
		p1=new Vec2D();
		p2=new Vec2D(1,1);
		s1=p2.clone();
		s2=p2.clone();
	}
	@Test
	public void testIsect() throws Exception {
		Vec2D d = Collision.CollAABB(p1,p2,s1,s2);
		assertTrue(d.length()>0.0001);
	}
	@Test
	public void testNoColl() throws Exception {
		p1=new Vec2D(-3,0);   //no collision
		Vec2D d = Collision.CollAABB(p1,p2,s1,s2);
		assertEquals(0,d.length(),0.000001);
	}
	@Test
	public void onPlatform() {

		p1=new Vec2D(0,-1);  //box on a platform one unit deep
		p2=new Vec2D(0,0);
		s1=new Vec2D(3,1);
		Vec2D d = Collision.CollAABB(p2,p1,s2,s1);
		assertEquals(0,d.x,0.000001);
		assertEquals(-1,d.y,0.000001);
	}
	@Test
	public void inWall() {
		p1=new Vec2D(-2,0);	//box in a wall one unit deep
		p2=new Vec2D(0,0);
		s1=new Vec2D(2,5);
		Vec2D d = Collision.CollAABB(p2,p1,s2,s1);
		assertEquals(-1,d.x,0.000001);
		assertEquals(0,d.y,0.000001);
	}
}
