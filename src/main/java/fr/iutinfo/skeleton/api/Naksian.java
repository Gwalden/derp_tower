package fr.iutinfo.skeleton.api;

public class Naksian extends Entity{
	final int type = 5;
	
	public Naksian(){
	}

	public Naksian(int x, int y, int id){
		this.x = x;
		this.y = y;
		this.id = id;
		this.dep = 2;
		this.att = 4;
		this.vie = 4;
		this.cout = 5;
		this.rAtt = 2;
		this.point = 2;
		this.peutAttaquer = false;
	}
}
