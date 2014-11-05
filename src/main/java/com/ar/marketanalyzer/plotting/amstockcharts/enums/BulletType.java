package com.ar.marketanalyzer.plotting.amstockcharts.enums;

public enum BulletType {
	round("round"),
	square("square"),
	diamond("diamond"),
	triangleUp("triangleUp"),
	triangleDown("triangleDown"),
	traingleLeft("triangleLeft"),
	triangleRight("triangleRight"),
	bubble("bubble");
	
	private final String bulletType;
	
	BulletType(String bulletType) {
		this.bulletType = bulletType;
	}
	
	@Override
	public String toString() {
		return bulletType;
	}
}
