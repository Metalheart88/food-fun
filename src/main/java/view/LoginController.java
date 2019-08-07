package view;

import domain.Customer;
import service.AuthenticationService;
import service.CustomerService;
import view.util.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends BaseController {

    private CustomerService customerService;

    private AuthenticationService authenticationService;

    public void init(){
        customerService = new CustomerService();
        authenticationService = new AuthenticationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/common/loginForm.jsp");
            dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            Customer customer = customerService.getByEmail(email);

            if (authenticationService.verifyUserPassword(password, customer.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("loggedUser", customer);
                session.setAttribute("nameUser", customer.getName().toUpperCase());
                if (email.equalsIgnoreCase("admin@test.com")) session.setAttribute("isAdmin", true);
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                req.setAttribute("message", Message.buildWarningMessage("The email or password are not correct!"));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/common/loginForm.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (Exception e){
            req.setAttribute("message", processException(e));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/common/loginForm.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
