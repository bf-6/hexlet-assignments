package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // BEGIN
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        String name = req.getParameter("name");

        if (name.isEmpty()) {
            PrintWriter writer = resp.getWriter();
            writer.println("Hello, Guest!");
        } else {
            PrintWriter writer = resp.getWriter();
            writer.println("Hello, " + name + "!");
        }
    }
    // END
}
