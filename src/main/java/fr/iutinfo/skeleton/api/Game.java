package fr.iutinfo.skeleton.api;


import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Game {
	
	private List<Entity> list = new ArrayList<>();
	
	@GET
	public List<Entity> getlist(){
		return this.list;
	}
	
	@PUT
	public List<Entity> putentity(){
		this.list.add(new Entity(10, 10));
		return this.list;
	}
}
