package fr.iutinfo.skeleton.api;

import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Games {

	private static List<Game>glist = new ArrayList<>();
	private static List<User>ulist = new ArrayList<>();
    final static Logger logger = LoggerFactory.getLogger(User.class);

	
	
	@GET
	public Game getGame(@Context SecurityContext context){
		User u = (User) context.getUserPrincipal();
		logger.debug(u.toString());

		for (Game lgame : glist) {
			if (lgame.getPlayer1().getName().equals( u.getName()) || lgame.getPlayer2().getName().equals( u.getName()))
				return lgame;
		}
		throw new WebApplicationException(404);
	}
	
	
	@POST
	public void creatGame(@Context SecurityContext context){
		User u = (User) context.getUserPrincipal();
		logger.debug(u.toString());
		ulist.add(u);
		logger.debug("LISTE AFAFA :     "+ulist.size());
		if (ulist.size() >= 2){
			Game gamec = new Game();
			gamec.setPlayer1(ulist.get(0));
			gamec.setPlayer2(ulist.get(1));
			gamec.setTurn(ulist.get(0));
			ulist.remove(1);
			ulist.remove(0);
			glist.add(gamec);
		}
		logger.debug("GLIST :     "+ glist.toString());
	}
	
	@PUT
	public Game playGame(@Context SecurityContext context){
		User u = (User) context.getUserPrincipal();
		for (Game lgame : glist) {
			if (lgame.getPlayer1().getName().equals(u.getName()) || lgame.getPlayer2().getName().equals(u.getName())) {
				if (lgame.getTurn().getName().equals(u.getName()))
					lgame.nextTurn(u);
				return lgame;
			}
		}
		throw new WebApplicationException(404);
	}
}
