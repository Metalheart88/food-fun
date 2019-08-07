package view;

import domain.*;
import exception.EntityNotFoundException;
import repository.CustomerRepository;
import repository.FoodRepository;
import service.CustomerService;
import service.FoodService;
import service.OrderService;
import view.util.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderServletController extends BaseController {

    private OrderService orderService;
    private FoodService foodService;
    private CustomerService customerService;

    public void init() {
        orderService = new OrderService();
        foodService = new FoodService();
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
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertOrder(request, response);
                    break;
//                case "/changePassword":

                default:
                    listOrders(request, response);
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

    private void listOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerId = request.getParameter("id");

        List<Order> listOrders = orderService.getAllForCustomer(customerId);
        request.setAttribute("listOrders", listOrders);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/pages/order/OrderList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/pages/order/OrderForm.jsp");

        try {
            String id = request.getParameter("id");
            Food food = foodService.getById(id);

            request.setAttribute("food", food);
            dispatcher.forward(request, response);

        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
        }

    }

//    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        try {
//            String id = request.getParameter("id");
//            Customer existingCustomer = customerService.getById(id);
//            RequestDispatcher dispatcher = request
//                    .getRequestDispatcher("/pages/user/UserForm.jsp");
//            request.setAttribute("customer", existingCustomer);
//            request.setAttribute("isEdit", true);
//            dispatcher.forward(request, response);
//        } catch (EntityNotFoundException e) {
//            request.setAttribute("message", e.getMessage());
//            listUser(request, response);
//        }
//
//    }

    private void insertOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Order order = null;

        try {
            Date date = new Date();

            String orderId = UUID.randomUUID().toString();
            String orderDate = new Date().toString();
            Delivery delivery = new Delivery(UUID.randomUUID().toString(), new Date(date.getTime() + 16*3600*1000).toString(), new Date(date.getTime() + 16*3600*1000).toString());
            Customer customer = customerService.getById(request.getParameter("customerId"));
            Food food = foodService.getById(request.getParameter("foodId"));
            int orderQty = Integer.parseInt(request.getParameter("quantity"));
            float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));

            order = new Order(orderId, orderDate, delivery, customer, orderQty, totalPrice, food);

            orderService.add(order);
            request.setAttribute("message", Message.buildSuccessMessage("Order paid successfully"));
//            listUser(request, response);
            request.setAttribute("orderId", orderId);
            request.setAttribute("deliveryDate", delivery.getDeliveryTime());
            request.setAttribute("customer", customer);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/order/OrderComplete.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e) {
            request.setAttribute("order", order);
            request.setAttribute("message", processException(e));
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/food/FoodList.jsp");
            dispatcher.forward(request, response);
        }
    }
//
//    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Customer customer = null;
//        try {
//            String id = request.getParameter("id");
//            String email = request.getParameter("email");
//            String phone = request.getParameter("phone");
//            String name = request.getParameter("name");
//            String country = request.getParameter("country");
//            String province = request.getParameter("province");
//            String streetName = request.getParameter("street_name");
//            int streetNumber = Integer.parseInt(request.getParameter("street_number"));
//            String postalCode = request.getParameter("postal_code");
//
//            customer = new Customer(id, email, phone, name, country, province, streetNumber, streetName, postalCode);
//
//            customerService.update(customer);
//            request.setAttribute("message", Message.buildSuccessMessage("Profile updated successfully"));
//            listUser(request, response);
//        } catch (Exception e) {
//            request.setAttribute("customer", customer);
//            request.setAttribute("message", processException(e));
//            request.setAttribute("isEdit", true);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/UserForm.jsp");
//            dispatcher.forward(request, response);
//        }
//    }
//
//    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            String id = request.getParameter("id");
//            service.remove(id);
//            request.setAttribute("message", Message.buildSuccessMessage("User deleted successfully"));
//        } catch (Exception e) {
//            request.setAttribute("message", processException(e));
//        }
//        listUser(request, response);
//
//    }


}
