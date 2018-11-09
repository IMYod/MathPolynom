package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myMath.Monom;

class MonomTest {

	@Test
	void init1Test() {
		Monom m1 = new Monom(-2.3,5);
		Monom m2 = new Monom(m1);
		assertEquals(-2.3, m2.get_coefficient());
		assertEquals(5, m2.get_power());
	}

	@Test
	void init2Test() {
		Monom m1 = new Monom("-2.3x^5");
		assertEquals(-2.3, m1.get_coefficient());
		assertEquals(5, m1.get_power());
	}
	
	@Test
	void comapreToTest() {
		Monom m1 = new Monom(28,5);
		Monom m2 = new Monom(-8,5);
		assertEquals(0, m1.compareTo(m2));
		Monom m3 =new Monom(0, 0);
		assertTrue(m1.compareTo(m3) > 0);
		assertTrue(m3.compareTo(m2) < 0);
	}
	
	@Test
	void multiplyTest() {
		Monom m1 = new Monom(2, 5);
		Monom m2 = new Monom(-3.3, 4);
		m1.multiply(m2);
		Monom m3 = new Monom(-6.6, 9);
		assertEquals(m3, m1);
	}
	
	@Test
	void addTest() {
		Monom m1 = new Monom(-28,5);
		Monom m2 = new Monom(8,5);
		m1.add(m2);
		Monom m3 = new Monom(-20, 5);
		assertEquals(m3, m1);
		
		Monom m4 = new Monom(-20,55);
		m1.add(m4);
		assertEquals(m3, m1);
	}
	
	@Test
	void derivativeTest() {
		Monom m1 = new Monom(5, 2);
		m1 = m1.derivative();
		Monom m2 = new Monom(10, 1);
		assertEquals(m2, m1);
		
		m1 = m1.derivative();
		m2 = new Monom(10, 0);
		assertEquals(m2, m1);
		
		m1 = m1.derivative();
		m2 = new Monom(0, 0);
		assertEquals(m2, m1);		
	}
	
	@Test
	void fTest() {
		Monom m1 = new Monom(5, 2);
		double actual = m1.f(-4);
		double expected = 80;
		assertEquals(expected, actual);
	}
	
	@Test
	void isZeroTest() {
		Monom m0 = new Monom(0, 0);
		assertTrue(m0.isZero());
		m0 = new Monom(0, 4);
		assertTrue(m0.isZero());
		m0 = new Monom(4, 0);
		assertFalse(m0.isZero());
	}
	
	@Test
	void toStringTest() {
		Monom m1 = new Monom("-15*x^24");
		Monom m2 = new Monom(m1.toString());
		m1 = new Monom(-15, 24);
		assertEquals(m2, m1);
	}
}
