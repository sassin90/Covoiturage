package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

public class LoginUserFilter implements Filter {

    public void destroy() {
    }

    public void doFilter( ServletRequest request, ServletResponse response,
            FilterChain chain ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
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

        User user = (User) session.getAttribute( "userLogin" );

        if ( user == null ) {
            doLogin( request, response, req );
            return;
        }
        
      
        chain.doFilter( request, response );
    }

    @Override
    public void init( FilterConfig arg0 ) throws ServletException {
        // TODO Auto-generated method stub

    }
    
    protected void doLogin( ServletRequest request, ServletResponse response,
            HttpServletRequest req ) throws ServletException, IOException {
        HttpServletResponse resp = (HttpServletResponse) response;

        if ( !req.getRequestURI().equals( req.getContextPath() + "/index.xhtml" )
                && req.getSession().getAttribute( "user" ) == null ) {
            resp.sendRedirect( req.getContextPath() + "/index.xhtml" );
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher( "/index.xhtml" );
        rd.forward( request, response );

    }

    protected void accessDenied( ServletRequest request,
            ServletResponse response, HttpServletRequest req )
            throws ServletException, IOException {
        RequestDispatcher rd = req
                .getRequestDispatcher( "/admincp/accessDenied.xhtml" );
        rd.forward( request, response );
    }
    
}