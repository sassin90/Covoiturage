package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserAdmin;

public class LoginCheckFilter extends AbstractFilter implements Filter {

    public void destroy() {
    }

    public void doFilter( ServletRequest request, ServletResponse response,
            FilterChain chain ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String chemin = req.getRequestURI().substring(
                req.getContextPath().length() );
       
        if ( chemin.startsWith( "/resources" )
                || req.getServletPath().contains( "/javax.faces.resource" ) ) {
          
            chain.doFilter( request, response );
            return;
        }

        if ( session.isNew() ) {
            doLogin( request, response, req );
            return;
        }

        UserAdmin user = (UserAdmin) session.getAttribute( "user" );

        if ( user == null ) {
            doLogin( request, response, req );
            return;
        }
        
        if ( user.getRoles().get(0).getRolename().equals("administrateur") ) {
            if ( req.getServletPath().contains( "admincp/login.xhtml" ) ) {
                resp.sendRedirect( "/TestJSF/admincp/administrateur/dashboard.xhtml" );
                return;
            }

        }
        else if ( user.getRoles().get(0).getRolename().equals("moderateur") ) {
            if ( req.getServletPath().contains( "admincp/login.xhtml" ) ) {
                resp.sendRedirect( "/TestJSF/admincp/moderateur/dashboard.xhtml" );
               return;
            }
        }
       
        if( user.getRoles().get(0).getRolename().equals("moderateur") && !req.getRequestURI().contains( "moderateur" ))
        {
        	 resp.sendRedirect( "/TestJSF/admincp/moderateur/dashboard.xhtml" );
        }
        chain.doFilter( request, response );
    }

    @Override
    public void init( FilterConfig arg0 ) throws ServletException {
        // TODO Auto-generated method stub

    }
}