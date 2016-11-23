package com.osori.hyson.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.osori.hyson.HySON;
import com.osori.hyson.test.data.BooleanObjectValue;
import com.osori.hyson.test.data.BooleanValue;
import com.osori.hyson.test.data.DoubleObjectValue;
import com.osori.hyson.test.data.DoubleValue;
import com.osori.hyson.test.data.FloatObjectValue;
import com.osori.hyson.test.data.FloatValue;
import com.osori.hyson.test.data.IntValue;
import com.osori.hyson.test.data.IntegerValue;
import com.osori.hyson.test.data.StringValue;

public class ValueParseTest {
	@Test
	public void testString() {
		String jsonString = "{ \"value\" : \"hySON\" }";
		StringValue result = HySON.parse(jsonString, StringValue.class);
		
		assertNotNull(result);
		assertEquals("hySON", result.value);
	}
	
	@Test
	public void testInt() {
		String jsonString = "{ \"value\" : 1234 }";
		IntValue result = HySON.parse(jsonString, IntValue.class);
		
		assertNotNull(result);
		assertEquals(1234, result.value);
	}
	
	@Test
	public void testInteger() {
		String jsonString = "{ \"value\" : 4567 }";
		IntegerValue result = HySON.parse(jsonString, IntegerValue.class);
		
		assertNotNull(result);
		assertEquals(4567, result.value.intValue());
	}
	
	@Test
	public void testDouble() {
		String jsonString = "{ \"value\" : 4.0 }";
		DoubleValue result = HySON.parse(jsonString, DoubleValue.class);
		
		assertNotNull(result);
		assertEquals(4.0, result.value, 0);
	}
	
	@Test
	public void testDoubleObject() {
		String jsonString = "{ \"value\" : 4.0 }";
		DoubleObjectValue result = HySON.parse(jsonString, DoubleObjectValue.class);
		
		assertNotNull(result);
		assertEquals(4.0, result.value.doubleValue(), 0);
	}
	
	@Test
	public void testFloat() {
		String jsonString = "{ \"value\" : 4.0 }";
		FloatValue result = HySON.parse(jsonString, FloatValue.class);
		
		assertNotNull(result);
		assertEquals(4.0, result.value, 0.0001f);
	}
	
	@Test
	public void testFloatObject() {
		String jsonString = "{ \"value\" : 4.0 }";
		FloatObjectValue result = HySON.parse(jsonString, FloatObjectValue.class);
		
		assertNotNull(result);
		assertEquals(4.0, result.value.floatValue(), 0.0001f);
	}

	
	@Test
	public void testBoolean() {
		String jsonString = "{ \"value\" : true }";
		BooleanValue result = HySON.parse(jsonString, BooleanValue.class);
		
		assertNotNull(result);
		assertEquals(true, result.value);

		String jsonString2 = "{ \"value\" : false }";
		BooleanValue result2 = HySON.parse(jsonString2, BooleanValue.class);
		
		assertNotNull(result);
		assertEquals(false, result2.value);
	}
	
	@Test
	public void testBooleanObject() {
		String jsonString = "{ \"value\" : true }";
		BooleanObjectValue result = HySON.parse(jsonString, BooleanObjectValue.class);
		
		assertNotNull(result);
		assertEquals(true, result.value.booleanValue());
		

		String jsonString2 = "{ \"value\" : false }";
		BooleanObjectValue result2 = HySON.parse(jsonString2, BooleanObjectValue.class);
		
		assertNotNull(result);
		assertEquals(false, result2.value.booleanValue());
	}
}
