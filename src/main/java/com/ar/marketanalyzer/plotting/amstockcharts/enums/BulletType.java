package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum BulletType {
	ROUND("round"),
	SQUARE("square"),
	DIAMOND("diamond"),
	TRIANGLE_UP("triangleUp"),
	TRIANGLE_DOWN("triangleDown"),
	TRIANGLE_LEFT("triangleLeft"),
	TRIANGLE_RIGHT("triangleRight"),
	BUBBLE("bubble");
	
	private final String bulletType;
	
	BulletType(String bulletType) {
		this.bulletType = bulletType;
	}
	
	@Override
	public String toString() {
		return bulletType;
	}
}
