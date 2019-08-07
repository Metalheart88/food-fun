package view;

import domain.Customer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    public FilterConfig filterConfig;

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        Customer customer = (Customer) request.getSession().getAttribute("loggedUser");
        if (customer == null) {
            if (request.getRequestURL().toString().contains("orders"))  {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        filter.doFilter(servletRequest, servletResponse);
    }

    public void init(final FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {

    }
}
