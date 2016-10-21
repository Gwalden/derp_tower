package fr.iutinfo.skeleton.auth;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.api.BDDFactory;
import fr.iutinfo.skeleton.api.User;
import fr.iutinfo.skeleton.api.UserDao;

@Provider
@PreMatching
public class AuthFilter implements ContainerRequestFilter {
    private final static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void filter(ContainerRequestContext containerRequest) throws WebApplicationException {
        String authorizationHeader = containerRequest.getHeaderString(HttpHeaders.AUTHORIZATION);
        String scheme = containerRequest.getUriInfo().getRequestUri().getScheme();
        logger.debug("authorizationHeader : " + authorizationHeader);

        if (authorizationHeader != null) {
            String[] loginPassword = BasicAuth.decode(authorizationHeader);
            checkLoginPassword(loginPassword);
            String login = loginPassword[0];
            String password = loginPassword[1];
            logger.debug("login : " + login + ", password : " + password);
            User user = loadUserFromLogin(login);
        }
    }

    private User loadUserFromLogin(String login) {
        UserDao dao = BDDFactory.getDbi().open(UserDao.class);
        User user = dao.findByName(login);
        return user;
    }

    private void checkLoginPassword(String[] loginPassword) {
        if (loginPassword == null || loginPassword.length != 2) {
            throw new WebApplicationException(Status.NOT_ACCEPTABLE);
        }
    }
}
