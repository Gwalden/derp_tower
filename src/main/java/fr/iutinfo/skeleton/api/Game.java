package fr.iutinfo.skeleton.api;


import java.util.ArrayList;
import java.util.List;

public class Game {

	
	private int id = 0;
	private User player1;
	private User player2;
	private List<Entity> list_joueur1 = new ArrayList<>();
	private List<Entity> list_joueur2 = new ArrayList<>();
	
	
	@Override
	public String toString() {
		return "Game [id=" + id + ", player1=" + player1 + ", player2=" + player2 + ", list_joueur1=" + list_joueur1
				+ ", list_joueur2=" + list_joueur2 + "]";
	}

	public User getPlayer1() {
		return player1;
	}

	public void setPlayer1(User player1) {
		this.player1 = player1;
	}

	public User getPlayer2() {
		return player2;
	}

	public void setPlayer2(User player2) {
		this.player2 = player2;
	}

    public Game() {
    }
    
	public Game getGame() {
		return this;
	}
	
	public List<Entity> getList_joueur1() {
		return list_joueur1;
	}

	public void setList_joueur1(List<Entity> list) {
		list_joueur1 = list;
	}

	public List<Entity> getList_joueur2() {
		return list_joueur2;
	}

	public void setList_joueur2(List<Entity> list) {
		list_joueur2 = list;
	}

	public Game putGame() {
		this.putEntityJoueur1();
		this.putEntityJoueur2();
		this.deplacerEntityJoueur1();
		this.deplacerEntityJoueur2();
		return this;
	}
	
	public void putEntityJoueur1() {
		int r = (int)(Math.random()*4);
		if (r==1) {
			list_joueur1.add(new Entity(2,3,id));
		} else if (r==2) {
			list_joueur1.add(new Entity(5,2,id));
		} else if (r==3) {
			list_joueur1.add(new Entity(6,2,id));
		} else if (r==4) {
			list_joueur1.add(new Entity(9,3,id));
		}
		id++;
	}

	public void putEntityJoueur2() {
		int r = (int)(Math.random()*4);
		if (r==1) {
			list_joueur2.add(new Entity(2,13,id));
		} else if (r==2) {
			list_joueur2.add(new Entity(5,14,id));
		} else if (r==3) {
			list_joueur2.add(new Entity(6,14,id));
		} else if (r==4) {
			list_joueur2.add(new Entity(9,13,id));
		}
		id++;
	}

	public void deplacerEntityJoueur1() {
		for (int i=0;i<list_joueur1.size();i++) {
			
			list_joueur1.get(i).setY(list_joueur1.get(i).getY()+list_joueur1.get(i).getDep());
			
			if ((list_joueur1.get(i).getX()==2) || (list_joueur1.get(i).getX()==9)) {
				if (list_joueur1.get(i).getY()>13) {
					list_joueur1.remove(i);
				}
			} else if ((list_joueur1.get(i).getX()==5) || (list_joueur1.get(i).getX()==6)) {
				if (list_joueur1.get(i).getY()>14) {
					list_joueur1.remove(i);
				}
			}
		}
	}
	
	public void deplacerEntityJoueur2() {
		for (int i=0;i<list_joueur2.size();i++) {
			
			list_joueur2.get(i).setY(list_joueur2.get(i).getY()-list_joueur2.get(i).getDep());
			
			if ((list_joueur2.get(i).getX()==2) || (list_joueur2.get(i).getX()==9)) {
				if (list_joueur2.get(i).getY()<3) {
					list_joueur2.remove(i);
				}
			} else if ((list_joueur2.get(i).getX()==5) || (list_joueur2.get(i).getX()==6)) {
				if (list_joueur2.get(i).getY()<2) {
					list_joueur2.remove(i);
				}
			}
		}
	}
}
