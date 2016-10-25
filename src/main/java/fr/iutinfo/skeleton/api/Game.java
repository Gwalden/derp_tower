package fr.iutinfo.skeleton.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {

	private int mana1 = 0;
	private int mana2 = 0;
	private int score1 = 0;
	private int score2 = 0;
	private int id = 0;
	private User player1;
	private User player2;
	private User turn;
	private User winner;
	private List<Entity> list_joueur1 = new ArrayList<>();
	private List<Entity> list_joueur2 = new ArrayList<>();
	final static Logger logger = LoggerFactory.getLogger(Game.class);
	
	
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

	
	public void putEntityJoueur1(int type, int r) {
		int l =2;
		int c = 13;
		if (r == 2){
			l=5;
			c=14;
		}
		else if (r == 3){
			l=6;
			c = 14;
		}
		else if (r == 4)
			l=9;	
		Entity e = null;
		if (type == 5)
			 e = new Naksian(l,c,id);
		else if (type == 2)
			 e = new Battler(l,c,id);
		else if (type == 3)
			 e = new Derp(l,c,id);
		else if (type == 4)
			e = new Gorrok(l,c,id);
		else if (type == 1)
			 e = new Hydrea(l,c,id);
		if (this.getMana2()-e.getCout()>=0) {
			this.setMana1(this.getMana2()-e.getCout());
			list_joueur1.add(e);
		}
		id++;
	}
	
	public void putEntityJoueur2(int type, int r) {
		int l = 2;
		int c = 13;
		if (r == 2){
			l=5;
			c=14;
		}
		else if (r == 3){
			l=6;
			c = 14;
		}
		else if (r == 4)
			l=9;	
		Entity e = null;
		if (type == 5)
			e = new Naksian(l,c,id);
		else if (type == 2)
			 e = new Battler(l,c,id);
		else if (type == 3)
			e = new Derp(l,c,id);
		else if (type == 4)
			e = new Gorrok(l,c,id);
		else if (type == 1)
			 e = new Hydrea(l,c,id);
		if (this.getMana2()-e.getCout()>=0) {
			this.setMana2(this.getMana2()-e.getCout());
			list_joueur2.add(e);
		}
		id++;
	}

	public User getTurn() {
		return turn;
	}

	public void setTurn(User turn) {
		this.turn = turn;
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
	
	public void ajouterScore1() {
		for (int i=0;i<list_joueur1.size();i++) {
			if ((list_joueur1.get(i).getX()==2) && (list_joueur1.get(i).getY() == 13)) {
				score1 += list_joueur1.get(i).getPoint();
			} else if ((list_joueur1.get(i).getX()==5) && (list_joueur1.get(i).getY() == 14)) {
				score1 += list_joueur1.get(i).getPoint();
			} else if ((list_joueur1.get(i).getX()==6) && (list_joueur1.get(i).getY() == 14)) {
				score1 += list_joueur1.get(i).getPoint();
			} else if ((list_joueur1.get(i).getX()==9) && (list_joueur1.get(i).getY() == 13)) {
				score1 += list_joueur1.get(i).getPoint();
			}
		}
	}
	
	public void ajouterScore2() {
		for (int i=0;i<list_joueur2.size();i++) {
			if ((list_joueur2.get(i).getX()==2) && (list_joueur2.get(i).getY() == 3)) {
				score2 += list_joueur2.get(i).getPoint();
			} else if ((list_joueur2.get(i).getX()==5) && (list_joueur2.get(i).getY() == 2)) {
				score2 += list_joueur2.get(i).getPoint();
			} else if ((list_joueur2.get(i).getX()==6) && (list_joueur2.get(i).getY() == 2)) {
				score2 += list_joueur2.get(i).getPoint();
			} else if ((list_joueur2.get(i).getX()==9) && (list_joueur2.get(i).getY() == 3)) {
				score2 += list_joueur2.get(i).getPoint();
			}
		}
	}
	
	public void nextTurn(User user, Action action) {
		this.nettoyerTroupes();
		this.commencerCombat();
		this.nettoyerTroupes();

		if (user.getName().equals(this.player1.getName())) {
			this.putEntityJoueur1(action.getChamp(), action.getLane());
			this.setMana2(this.getMana2()+1);
			this.setTurn(this.player2);
			this.deplacerEntityJoueur2();
			this.ajouterScore2();
			logger.debug("Score 2 = "+score2);
		}
		else if (user.getName().equals(this.player2.getName())) {
			this.putEntityJoueur2(action.getChamp(), action.getLane());
			this.setMana1(this.getMana1()+1);
			this.setTurn(this.player1);
			this.deplacerEntityJoueur1();
			this.ajouterScore1();
			logger.debug("Score 1 = "+score1);
		}
		this.nettoyerTroupes();
		this.finDeGame();
		this.nettoyerTroupes();
	}
	
	public User getWinner() {
		return winner;
	}

	public void setWinner(User winner) {
		this.winner = winner;
	}

	public void finDeGame() {
		if (this.getScore1()==2) {
			this.winner = player1;
		} else if (this.getScore2()==2) {
			this.winner = player2;
		}
	}
	
	public int getMana1() {
		return mana1;
	}

	public void setMana1(int mana1) {
		this.mana1 = mana1;
	}

	public int getMana2() {
		return mana2;
	}

	public void setMana2(int mana2) {
		this.mana2 = mana2;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void commencerCombat() {
		for (int i=0;i<list_joueur1.size();i++) {
			for (int j=0;j<list_joueur2.size();j++) {
				list_joueur1.get(i).donneAttaque(list_joueur2.get(j));
			}
		}

		for (int i=0;i<list_joueur2.size();i++) {
			for (int j=0;j<list_joueur1.size();j++) {
				list_joueur2.get(i).donneAttaque(list_joueur1.get(j));
			}
		}
	}

	public void nettoyerTroupes() {
		for (int i=0;i<list_joueur1.size();i++) {
			if (list_joueur1.get(i).estMort() == true) {
				list_joueur1.remove(i);
			}
		}
		for (int j=0;j<list_joueur2.size();j++) {
			if (list_joueur2.get(j).estMort() == true) {
				list_joueur2.remove(j);
			}
		}
	}

}
