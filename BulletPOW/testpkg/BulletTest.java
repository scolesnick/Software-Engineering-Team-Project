package testpkg;

import static org.junit.Assert.*;

import org.junit.Test;

import gameMechanics.Bullet;

public class BulletTest
{

	@Test
	public void testBullet()
	{
		Bullet b = new Bullet(40,40);
		assertNotNull(b);
	}
	
	@Test
	public void testSetVelocity()
	{
		Bullet b = new Bullet(40,40);
		b.setVelocity(50, 50);
		// Angle calculated should be pi/4 radians
		assertEquals(Math.PI / 4, b.getAngle(), 0.1);
	}
	
	@Test
	public void testStep()
	{
		Bullet b = new Bullet(40,40);
		b.setVelocity(50, 50);
		int startX = b.getX();
		double incX = b.getXVel();
		int futureX = startX + (int) incX;
		b.step();
		// X and Y coordinates should be updated
		assertEquals(futureX, b.getX(), 0.1);	
	}

}
