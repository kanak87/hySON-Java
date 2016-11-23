package com.osori.hyson.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.osori.hyson.HySON;
import com.osori.hyson.test.data.array.BooleanArray;
import com.osori.hyson.test.data.array.BooleanObjectArray;
import com.osori.hyson.test.data.array.DoubleArray;
import com.osori.hyson.test.data.array.DoubleObjectArray;
import com.osori.hyson.test.data.array.IntArray;
import com.osori.hyson.test.data.array.IntegerArray;
import com.osori.hyson.test.data.array.StringArray;

public class ArrayParseTest {
	
	@Test
	public void testParseString() {
		String jsonString = "{ \"values\" : [\"asdf\", \"1234\"] }";
		StringArray result = HySON.parse(jsonString, StringArray.class);
		
		assertNotNull(result);
		assertEquals(2, result.values.length);
		assertEquals("asdf", result.values[0]);
		assertEquals("1234", result.values[1]);
	}
	
	@Test
	public void testParseBooleanArray() {
		String jsonString = "{ \"values\" : [true, true, false] }";
		BooleanArray result = HySON.parse(jsonString, BooleanArray.class);
		
		assertNotNull(result);
		assertEquals(3, result.values.length);
		assertTrue(result.values[0]);
		assertTrue(result.values[1]);
		assertFalse(result.values[2]);
	}
	
	@Test
	public void testParseBooleanObjectArray() {
		String jsonString = "{ \"values\" : [false, true, false] }";
		BooleanObjectArray result = HySON.parse(jsonString, BooleanObjectArray.class);
		
		assertNotNull(result);
		assertEquals(3, result.values.length);
		assertFalse(result.values[0].booleanValue());
		assertTrue(result.values[1].booleanValue());
		assertFalse(result.values[2].booleanValue());
	}
	
	@Test
	public void testParseIntArray() {
		String jsonString = "{ \"values\" : [1, 100, 200] }";
		IntArray result = HySON.parse(jsonString, IntArray.class);
		
		assertNotNull(result);
		assertEquals(3, result.values.length);
		assertEquals(1, result.values[0]);
		assertEquals(100, result.values[1]);
		assertEquals(200, result.values[2]);
	}
	
	@Test
	public void testParseIntegerArray() {
		String jsonString = "{ \"values\" : [1, 100, 200] }";
		IntegerArray result = HySON.parse(jsonString, IntegerArray.class);
		
		assertNotNull(result);
		assertEquals(3, result.values.length);
		assertEquals(1, result.values[0].intValue());
		assertEquals(100, result.values[1].intValue());
		assertEquals(200, result.values[2].intValue());
	}
	
	@Test
	public void testParseDoubleArray() {
		String jsonString = "{ \"values\" : [0.5, 0.1, 0.9, 0.4] }";
		DoubleArray result = HySON.parse(jsonString, DoubleArray.class);
		
		assertNotNull(result);
		assertEquals(4, result.values.length);
		assertEquals(0.5, result.values[0], 0.0001);
		assertEquals(0.1, result.values[1], 0.0001);
		assertEquals(0.9, result.values[2], 0.0001);
		assertEquals(0.4, result.values[3], 0.0001);
	}
	
	@Test
	public void testParseDoubleObjectArray() {
		String jsonString = "{ \"values\" : [0.3, 0.5] }";
		DoubleObjectArray result = HySON.parse(jsonString, DoubleObjectArray.class);
		
		assertNotNull(result);
		assertEquals(2, result.values.length);
		assertEquals(0.3, result.values[0].doubleValue(), 0.0001);
		assertEquals(0.5, result.values[1].doubleValue(), 0.0001);
	}
}
