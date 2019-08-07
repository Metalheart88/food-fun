package view;

import domain.Food;
import exception.EntityNotFoundException;
import service.FoodService;
import view.util.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class FoodServletController extends BaseController {
    private FoodService foodService;

    public void init() {
        foodService = new FoodService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            String action = extractAction(request);

            switch (action) {
//                case "/new":
//                    showNewForm(request, response);
//                    break;
//                case "/insert":
//                    insertFood(request, response);
//                    break;
//                case "/delete":
//                    deleteFood(request, response);
//                    break;
//                case "/edit":
//                    showEditForm(request, response);
//                    break;
//                case "/update":
//                    updateFood(request, response);
//                    break;
                default:
                    listFood(request, response);
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
            return "list";
        } else {
            return pathInfo;
        }
    }

    private void listFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Food> listFood = foodService.getAll();
        request.setAttribute("listFood", listFood);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/food/FoodList.jsp");
        dispatcher.forward(request, response);
    }

//    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//
//    }

//    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    private void insertFood(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        Food food = null;
//
//
//        try {
//            String id = UUID.randomUUID().toString();//
//        } catch (Exception e) {
//            request.setAttribute("message", processException(e));
//            request.setAttribute("isNew", true);
//            dispatcher.forward(request, response);
//        }
//    }
//
//    private void updateFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    private void deleteFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            String id = request.getParameter("id");
//            service.remove(id);
//            request.setAttribute("message", Message.buildSuccessMessage("Car deleted successfully"));
//            listFood(request, response);
//        } catch (Exception e) {
//            request.setAttribute("message", processException(e));
//            listFood(request, response);
//        }
//
//    }
}
