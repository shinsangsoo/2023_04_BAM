package bam.controller;

import bam.dto.Member;

public abstract class Controller {
	
	public static Member loginedMember;
	
	public abstract void doAction(String cmd, String methodName);
	public abstract void makeTestData();
}