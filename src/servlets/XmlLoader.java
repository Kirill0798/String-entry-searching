package servlets;

import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/result.xml")
public class XmlLoader extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("xml");
        HttpSession session = request.getSession();
        Task task = (Task) session.getAttribute("task");

        if (task == null){
            response.setStatus(HttpServletResponse.SC_FOUND);
            response.setHeader("Location","/index.jsp");
        }

        try(OutputStream outputStream = response.getOutputStream()){
            JAXBContext jaxbContext = JAXBContext.newInstance(Task.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(task, outputStream);

        } catch(JAXBException | IOException exc){
            exc.printStackTrace();
        }
    }

}
