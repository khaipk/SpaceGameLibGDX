package com.mygdx.game.tools;


public class CheckCollistion {
		
	private int x;
	private int y;
	private int width;
	private int height;
	
	public CheckCollistion(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean checkCollistion(CheckCollistion rect) {
		return x < rect.x+rect.width && x + width > rect.x && y < rect.y +rect.height && y + height > rect.y;
	}
}
