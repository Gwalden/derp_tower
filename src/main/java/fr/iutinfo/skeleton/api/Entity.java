package fr.iutinfo.skeleton.api;

public abstract class Entity {

	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}

	protected int type = 0; 
	protected int id;
	protected int x;
	protected int y;
	protected int dep = 1;
	protected int att = 1;
	protected int vie = 1;
	protected int cout = 1;
	protected int rAtt = 1;
	protected int point = 1;
	protected boolean peutAttaquer = false;

	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}

	public int getAtt() {
		return att;
	}


	public void setAtt(int att) {
		this.att = att;
	}

	public int getrAtt() {
		return rAtt;
	}

	public void setrAtt(int rAtt) {
		this.rAtt = rAtt;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}



	public Entity(){
	}

	public Entity(int x, int y, int id){
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public int getDep() {
		return dep;
	}

	public void setDep(int dep) {
		this.dep = dep;
	}

	public void perdVie(int attaque) {
		if (this.vie-attaque < 0) {
			this.vie = 0;
		} else {
			this.vie = this.vie-attaque;
		}
	}

	public int getVie() {
		return this.vie;
	}


	public boolean getPeutAttaquer() {
		return peutAttaquer;
	}

	public void setPeutAttaquer(boolean b) {
		this.peutAttaquer = b;
	}

	public boolean donneAttaque(Entity e) {
		if ((this.getX()==e.getX() &&  (this.getY()+rAtt==e.getY() || this.getY()-rAtt==e.getY() || this.getY()==e.getY())) || (this.getY()==e.getY() &&  (this.getX()+rAtt==e.getX() || this.getX()-rAtt==e.getX() || this.getX()==e.getX()))) {
			e.perdVie(this.att);
			this.peutAttaquer = true;
		}
		return this.peutAttaquer;
	}


	public boolean estMort() {
		if (vie <= 0) {
			return true;
		} else {
			return false;
		}
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

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
