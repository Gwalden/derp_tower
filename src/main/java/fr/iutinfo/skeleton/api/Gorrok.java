package fr.iutinfo.skeleton.api;

public class Gorrok extends Entity{
	
	public Gorrok(){
	}

	public Gorrok(int x, int y, int id){
		this.type = 4;
		this.x = x;
		this.y = y;
		this.id = id;
		this.dep = 2;
		this.att = 1;
		this.vie = 2;
		this.cout = 2;
		this.rAtt = 1;
		this.point = 1;
		this.peutAttaquer = false;
	}
}
