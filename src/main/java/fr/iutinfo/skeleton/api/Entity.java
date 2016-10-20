package fr.iutinfo.skeleton.api;

public class Entity {

	private int x;
	private int y;
	private int dep = 40;
	
	public int getDep() {
		return dep;
	}

	public void setDep(int dep) {
		this.dep = dep;
	}

	public Entity(){
	}
	
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void dep(){
		this.y = this.y+this.dep;
	}
	
}
