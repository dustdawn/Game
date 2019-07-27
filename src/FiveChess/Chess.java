package FiveChess;

import java.awt.Color;

//Æå×Ó

public class Chess{
	private int x;
	private int y;
	private Color color;
	public static final int R=30;
	public Chess(int x,int y,Color color){
		this.x=x;
		this.y=y;
		this.color=color;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Color getColor(){
		return color;
	}
}