package filter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AbstractFilter {

    public AbstractFilter() {
        super();
    }

    protected void doLogin( ServletRequest request, ServletResponse response,
            HttpServletRequest req ) throws ServletException, IOException {
        HttpServletResponse resp = (HttpServletResponse) response;

        if ( !req.getRequestURI().equals( req.getContextPath() + "/admincp/login.xhtml" )
                && req.getSession().getAttribute( "user" ) == null ) {
            resp.sendRedirect( req.getContextPath() + "/admincp/login.xhtml" );
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher( "/admincp/login.xhtml" );
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