package com.sapestore.filters;

import java.io.IOException;
import java.sql.Array;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet Filter implementation class RequestLoggingFilter
 */
@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {
	private ServletContext context;
    /**
     * Default constructor. 
     */
    public RequestLoggingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("unused")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		//System.out.println("Previous Page:"+req.getHeader("Referrer"));
		HttpSession session=req.getSession(false);
		//System.out.println("from session:"+session);
		//System.out.println("from session attribute:"+session.getAttribute("userId"));
		
		
		  //String url = req.getRequestURL().toString();
		  /*System.out.println(url);
		  String[] i= url.split("/SapeStore");
		  System.out.println(i[1]);
		  session.setAttribute("url", i[1]);*/
		  
		  
		try{
			if(session.getAttribute("username")!=null){
				chain.doFilter(req, res);
			}
			else{
				// pass the request along the filter chain
				res.sendRedirect("../login.jsp");
			}
		}
		catch(NullPointerException e){
			res.sendRedirect("../login.jsp");
		}
		
	} 
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("RequestLoggingFilter initialized");
	}

}
