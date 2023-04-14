package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class RedirectionSiAucuneRessourceFiltre
 */
@WebFilter(
	urlPatterns="/", 
	dispatcherTypes= { 
		DispatcherType.REQUEST, 
		DispatcherType.INCLUDE, 
		DispatcherType.FORWARD, 
		DispatcherType.ERROR 
	} 
)
public class RedirectionSiAucuneRessourceFiltre implements Filter {

    /**
     * Default constructor. 
     */
    public RedirectionSiAucuneRessourceFiltre() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		httpResponse.sendRedirect("encheres");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
