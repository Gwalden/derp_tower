package fr.iutinfo.skeleton.api;

public class Battler extends Entity {
	
	public Battler(){
	}

	public Battler(int x, int y, int id){
		this.type = 2;
		this.x = x;
		this.y = y;
		this.id = id;
		this.dep = 1;
		this.att = 1;
		this.vie = 2;
		this.cout = 1;
		this.rAtt = 1;
		this.point = 2;
		this.peutAttaquer = false;
	}
}
