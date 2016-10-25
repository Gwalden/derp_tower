package fr.iutinfo.skeleton.api;

public class Hydrea extends Entity {
	
	public Hydrea(){
	}

	public Hydrea(int x, int y, int id){
		this.type = 1;
		this.x = x;
		this.y = y;
		this.id = id;
		this.dep = 1;
		this.att = 2;
		this.vie = 5;
		this.cout = 2;
		this.rAtt = 2;
		this.point = 3;
		this.peutAttaquer = false;
	}
}
