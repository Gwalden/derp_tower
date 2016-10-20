package fr.iutinfo.skeleton.api;


import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Game {
	
	static private List<Entity> list = new ArrayList<>();
	
	@GET
	public List<Entity> getlist(){
		return list;
	}
	
	@PUT
	public List<Entity> putentity(){
		for (int i = 0; i < list.size(); i++)
		{
			list.get(i).dep();
			if (list.get(i).getY()>400)
				list.remove(i);
		}
		list.add(new Entity((int)(Math.random()*400)-40,10));
		return list;
	}
}
