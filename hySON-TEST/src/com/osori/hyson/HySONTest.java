package com.osori.hyson;

import static org.junit.Assert.*;

import org.junit.Test;

public class HySONTest {
	
	@Test
	public void Test1()
	{
		String jsonString = "{ \"value\" : 10 }";
		Test1Class t = HySON.parse(jsonString, Test1Class.class);
		
		assertEquals(10, t.value);
	}
	
	@Test
	public void Test2()
	{
		/* 필요한가?
		HySON hyson = new HySON("{ \"object\" : { "value" : 10 } } ");
		Temp t = hyson.parse("object", Temp.class);
		*/
	}
	
	@Test
	public void Test3()
	{
		String jsonString = "{ \"temp\" : { \"value\" : 10 } }";
		Test3Class t = HySON.parse(jsonString, Test3Class.class);
		
		assertEquals(10, t.temp.value);
	}
	
	@Test
	public void Test4()
	{
		String jsonString = "{ \"temps\" : [ { \"value\" : 10 } ]}";
		Test4Class t = HySON.parse(jsonString, Test4Class.class);
		
		assertNotNull(t);
		assertNotNull(t.temps);
		assertEquals(1, t.temps.length);
		assertNotNull(t.temps[0]);
		assertEquals(10, t.temps[0].value);
	}
	
	@Test
	public void Test5()
	{
		String jsonString = "{ \"temps\" : [ { \"value\" : 10 }, { \"value\" : 100 } ] }";
		Test5Class t = HySON.parse(jsonString, Test5Class.class);
		
		assertNotNull(t);
		assertNotNull(t.temps);
		assertEquals(2, t.temps.size());
		
		assertNotNull(t.temps.get(0));
		assertEquals(10, t.temps.get(0).value);
		
		assertNotNull(t.temps.get(1));
		assertEquals(100, t.temps.get(1).value);
	}
	
	@Test
	public void Test6()
	{
		String jsonString = "{ \"member1\" : [ { \"temp\" : { \"value\" : 0 } }, { \"temp\" : { \"value\" : 100 } } ] }";
		Test6Class t = HySON.parse(jsonString, Test6Class.class);
		
		assertNotNull(t);
		assertNotNull(t.member1);
		assertEquals(2, t.member1.size());
		
		assertNotNull(t.member1.get(0));
		assertNotNull(t.member1.get(0).temp);
		assertEquals(0, t.member1.get(0).temp.value);


		assertNotNull(t.member1.get(1));
		assertNotNull(t.member1.get(1).temp);
		assertEquals(100, t.member1.get(1).temp.value);
	}
	
	@Test
	public void Test7()
	{
		String jsonString = "{\"a\": 10, \"b\" : \"횟횟\", \"c\" : [2, 3, 4], \"d\": [5, 6], \"temps\":[{\"value\": 20}]}";
		Test7Class t = HySON.parse(jsonString, Test7Class.class);
		
		assertNotNull(t);
		assertEquals(10, t.a);
		assertEquals("횟횟", t.b);
		
		assertNotNull(t.c);
		assertEquals(3, t.c.size());
		assertEquals(2, t.c.get(0).intValue());
		assertEquals(3, t.c.get(1).intValue());
		assertEquals(4, t.c.get(2).intValue());
		
		assertNotNull(t.d);
		assertEquals(2, t.d.length);
		assertEquals(5, t.d[0]);
		assertEquals(6, t.d[1]);
		
		assertNotNull(t.temps);
		assertEquals(1, t.temps.size());
		assertNotNull(t.temps.get(0));
		assertEquals(20, t.temps.get(0).value);
	}
}
