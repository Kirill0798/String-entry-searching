package servlets;

import database.DatabaseConnector;
import model.Task;

import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/html/result.html")
public class Executor extends HttpServlet {
    private final String DB_DRIVER = "org.sqlite.JDBC";
    private final String DB_PATH = "jdbc:sqlite:/Users/kirill/Desktop/labs_db/tnp_3.db";
    private Connection connection = null;

    public void init(ServletConfig servletConfig) throws ServletException{
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_PATH);
            System.out.println("Соединение с SQLite было установлено");

            final String sql = "CREATE TABLE IF NOT EXISTS searching(\n"+
                    "first text,\n"+
                    "second text, \n"+
                    "result text"+
                    ");";
            final Statement statement = connection.createStatement();
            statement.execute(sql);
        }catch (ClassNotFoundException | SQLException exp){
            exp.printStackTrace();
        }
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Task task = (Task) session.getAttribute("task");

        String first = task.getFirst();
        String second = task.getSecond();
        String result = null;

        try {
            String sql;
            PreparedStatement preparedStatement;

            sql = "SELECT * FROM searching WHERE first = ? AND second = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, first);
            preparedStatement.setString(2, second);

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                result = resultSet.getString("result");
                System.out.println("Результат выгружен из бд");
            }else {
                result = search(first, second);
                sql = "INSERT INTO searching(first, second, result) VALUES(?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, first);
                preparedStatement.setString(2, second);
                preparedStatement.setString(3, result);
                preparedStatement.executeUpdate();
                System.out.println("Результат загружен");
            }
        }catch(SQLException exp){
            exp.printStackTrace();
            result = search(first, second);
        }

        task.setResult(result);
        session.setAttribute("task", task);

//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("result.jsp");
        request.getRequestDispatcher("/result.jsp").forward(request, response);
//        if (dispatcher != null){
//            dispatcher.forward(request, response);
//        }
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
