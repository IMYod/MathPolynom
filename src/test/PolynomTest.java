package test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import myMath.Polynom;

class PolynomTest {

	@Test
	void initTest() {
		Polynom p1 = new Polynom("-20x^10+3x^4-5x^2+2x-995");		
		Polynom p2 = new Polynom(p1.toString());		
		assertEquals(p1, p2);
		
		p2 = new Polynom(p1);
		assertEquals(p1, p2);
		
		Polynom p3 = (Polynom) p2.copy();
		assertEquals(p1, p3);
		
		p3.add(new Polynom("1"));
		assertNotEquals(p1, p3);
	}
	
	@Test
	void initZeroTest() {
		Polynom p4 = new Polynom();
		Polynom p5 = new Polynom("0");
		assertEquals(p4, p5);
	}
	
	@Test
	void addZeroTest() {
		Polynom p1 = new Polynom("3x^2+2x-5");
		Polynom p2 = new Polynom("0");
		p1.add(p2);
		assertEquals(p1, new Polynom("3x^2+2x-5"));
		
		p2.add(p2);
		assertEquals(p2, new Polynom("0"));
	}
	@Test
	void addTest() {
		Polynom p1 = new Polynom("3x^2+2x-5");
		p1.add(p1);
		assertEquals(p1, new Polynom("6x^2+4x-10"));
		
		Polynom p3 = new Polynom("2x^8+3x^4-5x^2+7x-995");
		Polynom p4 = new Polynom("3x^2+2x-5");
		p3.add(p4);
		assertEquals(p3, new Polynom("2x^8+3x^4-2x^2+9x-1000"));
	}

	@Test
	void substractZeroTest() {
		Polynom p1 = new Polynom("3x^2+2x-5");
		Polynom p2 = new Polynom("0");
		p1.substract(p2);
		assertEquals(p1, new Polynom("3x^2+2x-5"));
		
		p2.substract(p2);
		assertEquals(p2, new Polynom("0"));
		
		p1.substract(p1);
		assertEquals(p1, p2);
	}
	
	@Test
	void substractTest() {
		Polynom p3 = new Polynom("2x^8+3x^4-5x^2+7x-995");
		Polynom p4 = new Polynom("22x^7+3x^4+5x-5");
		p3.substract(p4);
		assertEquals(p3, new Polynom("2x^8-22x^7-5x^2+2x-990"));
		
		p4.substract(new Polynom("22x^7+3x^4+5x-5"));
		assertEquals(p4, new Polynom("0"));
	}
	
	@Test
	void multiplyZeroTest() {
		Polynom p1 = new Polynom("3x^2+2x-5");
		Polynom p2 = new Polynom("0");
		p2.multiply(p2);
		assertEquals(p2, new Polynom("0"));
		
		p2.multiply(p1);
		assertEquals(p2, new Polynom("0"));
		
		p1.multiply(p2);
		assertEquals(p1, new Polynom("0"));
	}
	
	@Test
	void multiplyTest() {
		Polynom p3 = new Polynom("3x^2+2x-5");
		Polynom p4 = new Polynom(p3);
		p3.multiply(p4);
		Polynom expected = new Polynom("9x^4+12x^3-26x^2-20x+25");
		assertEquals(expected, p3);
	}
	
	@Test
	void fTest() {
		Polynom p1 = new Polynom("3x^2+2x-5");
		assertEquals(-5, p1.f(0));
		assertEquals(3, p1.f(-2));
		
		Polynom p2 = new Polynom("5");
		assertEquals(5, p2.f(-2));
		
		Polynom p3 = new Polynom();
		assertEquals(0, p3.f(-2));
	}
	
	@Test
	void derivativeTest() {
		Polynom p1 = new Polynom("3x^2+2x-5");
		Polynom expected = new Polynom("6x+2");
		p1 = (Polynom)p1.derivative();
		assertEquals(expected, p1);
		
		p1 = (Polynom)p1.derivative();
		expected = new Polynom("6");
		assertEquals(expected, p1);
		
		p1 = (Polynom)p1.derivative();
		expected = new Polynom("0");
		assertEquals(expected, p1);
		
		p1 = (Polynom)p1.derivative();
		assertEquals(expected, p1);
	}
	
	@Test
	void areaTest() {
		Polynom p1 = new Polynom("10");
		double actual = p1.area(0, 10, 2);
		assertEquals(100, actual);
		
		actual = p1.area(0, 9, 2);
		assertEquals(90, actual);
		
		p1 = new Polynom();
		actual = p1.area(-5, 9, 0.1);
		assertEquals(0, actual);
		
		Polynom p2 = new Polynom("3x^2+2x-5");
		actual = 0;
		for (int i=0; i<=9; i++)
			actual += Math.abs(p2.f(i));
		assertEquals(p2.area(0, 10, 1), actual);
	}
	
	@Test
	void rootTest() {
		Polynom p1 = new Polynom("8x");
		double actual = p1.root(-8, 5, 0.01);
		if (actual > 0.01 || actual < -0.01)
			fail();
		
		p1 = new Polynom("x^2-6x+5");
		actual = p1.root(-8, 3.5, 0.01);
		if (actual > 1.01 || actual < -1.01)
			fail();
		
		actual = p1.root(1.015, 12, 0.01);
		if (actual > 5.01 || actual < -5.01)
			fail();
	}

	@Test
	void isZeroTest() {
		Polynom p0 = new Polynom();
		assertTrue(p0.isZero());
		
		p0 = new Polynom("0");
		assertTrue(p0.isZero());
		
		p0 = new Polynom("0x^3-0*x+0");
		assertTrue(p0.isZero());
	}
	
	@Test
	void extremaPointsTest() {
		Polynom p1 = new Polynom("3*x^2");
		LinkedList<Double> ll1 =  p1.extremaPoints(-5, 5, 0.25);
		assertFalse(ll1.isEmpty());
		Iterator<Double> it1 = ll1.iterator();
		assertTrue(0 == it1.next());
		assertFalse(it1.hasNext());
		
		Polynom p2 = new Polynom("5x");
		LinkedList<Double> ll2 =  p2.extremaPoints(-5, 5, 0.25);
		assertTrue(ll2.isEmpty());
		
		Polynom p3 = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
		LinkedList<Double> ll3 =  p3.extremaPoints(-2, 6, 0.1);
		int actual = ll3.size();
		assertEquals(3, actual);
	}
	
	@Test
	void areaAboveTest() {
		Polynom p1 = new Polynom("-10");
		double actual = p1.areaAbove(0, 10, 2);
		assertEquals(100, actual);
		
		actual = p1.areaAbove(0, 9, 2);
		assertEquals(90, actual);
		
		p1 = new Polynom();
		actual = p1.areaAbove(-5, 9, 0.1);
		assertTrue(actual==0);
	}
	
	@Test
	void GUITest() {
		Polynom p = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
		p.GUI(-2, 6, 0.01);
		assertTrue(true); //If is didn't throw exception
	}

}
