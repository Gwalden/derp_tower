package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import java.util.List;

@Path("/userdb")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserDBResource {
	private static UserDao dao = BDDFactory.getDbi().open(UserDao.class);
    final static Logger logger = LoggerFactory.getLogger(UserDBResource.class);


    public UserDBResource() {
		try {
			dao.createUserTable();
			dao.insert(new User("Mathuidi","123456","Mathuidi@Charo"));
		} catch (Exception e) {
			System.out.println("Table déjà là ! " + e.getMessage());
		}
	}
	
    
    @GET
    @Path("/login")
    public User logIn(@Context SecurityContext context)
    {
    	User user = (User) context.getUserPrincipal();
    	if (user.getId() == -1)
    		return null;
		return user;
    }
	@POST
	public User createUser(User user) {
        int id = dao.insert(user);
		return user;
	}

	@GET
	@Path("/{name}")
	public User getUser(@PathParam("name") String name) {
		User user = dao.findByName(name);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		return user;
	}

	@GET
	public List<User> getAllUsers() {		
		return dao.all();
	}
}

