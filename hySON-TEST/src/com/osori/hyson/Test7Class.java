package com.osori.hyson;

import java.util.ArrayList;

public class Test7Class
{
	public int a;
	public String b;
	@Member(Integer.class)
	public ArrayList<Integer> c;
	public int[] d;
	@Member(Test1Class.class)
	public ArrayList<Test1Class> temps;
}