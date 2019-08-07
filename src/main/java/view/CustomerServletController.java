package view;

import domain.Customer;
import domain.CustomerCountry;
import exception.EntityNotFoundException;
import org.apache.log4j.Logger;
import service.CustomerService;
import view.util.Message;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CustomerServletController extends BaseController {

    private CustomerService customerService;

    public void init() {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String action = extractAction(request);

            switch (action) {
                case "/signup":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteCustomer(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
//                case "/changePassword":

                default:
                    listUser(request, response);
                    break;
            }
        } catch (ServletException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    }

    private String extractAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "/";
        } else {
            return pathInfo;
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        Customer listUser = customerService.getById(id);

        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/pages/user/UserProfile.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/pages/user/UserForm.jsp");
        request.setAttribute("countries", CustomerCountry.values());
        request.setAttribute("isNew", true);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            Customer existingCustomer = customerService.getById(id);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/user/UserForm.jsp");
            request.setAttribute("customer", existingCustomer);
            request.setAttribute("countries", CustomerCountry.values());
            request.setAttribute("isEdit", true);
            dispatcher.forward(request, response);
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            listUser(request, response);
        }

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Customer customer = null;

        try {
            String id = UUID.randomUUID().toString();
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String country = request.getParameter("country");
            String province = request.getParameter("province");
            String streetName = request.getParameter("street_name");
            int streetNumber = Integer.parseInt(request.getParameter("street_number"));
            String postalCode = request.getParameter("postal_code");

            customer = new Customer(id, password, email, phone, name, country, province, streetNumber, streetName, postalCode);

            customerService.add(customer);
            request.setAttribute("message", Message.buildSuccessMessage("User added successfully"));
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/common/loginForm.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("customer", customer);
            request.setAttribute("message", processException(e));
            request.setAttribute("isNew", true);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/user/UserForm.jsp");
            dispatcher.forward(request, response);
        }
    }
//
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer customer = null;
        try {
            String id = request.getParameter("id");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String name = request.getParameter("name");
            String country = request.getParameter("country");
            String province = request.getParameter("province");
            String streetName = request.getParameter("street_name");
            int streetNumber = Integer.parseInt(request.getParameter("street_number"));
            String postalCode = request.getParameter("postal_code");

            customer = new Customer(id, email, phone, name, country, province, streetNumber, streetName, postalCode);

            customerService.update(customer);
            request.setAttribute("message", Message.buildSuccessMessage("Profile updated successfully"));
            request.setAttribute("loggedUser", customer);
            listUser(request, response);
        } catch (Exception e) {
            request.setAttribute("customer", customer);
            request.setAttribute("message", processException(e));
            request.setAttribute("isEdit", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/UserForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            customerService.delete(id);
            request.setAttribute("message", Message.buildSuccessMessage("Profile was deleted successfully"));
            request.getSession().invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", processException(e));
        }
    }
}
