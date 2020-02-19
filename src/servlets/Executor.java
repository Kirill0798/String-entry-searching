package servlets;

import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/html/result.html")
public class Executor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Task task = (Task) session.getAttribute("task");

        String first = task.getFirst();
        String second = task.getSecond();
        String result = search(first, second);

        task.setResult(result);
        session.setAttribute("task", task);

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
    }

    static String search(String first, String second){
        Integer index = first.lastIndexOf(second);
        if(index != -1) {
           String result = index.toString();
            return result;
        }else{
            return null;
        }
    }
}
