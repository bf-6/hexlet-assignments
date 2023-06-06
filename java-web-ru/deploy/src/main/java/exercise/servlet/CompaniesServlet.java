package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();

        List<String> companies = getCompanies();

        if (!(request.getQueryString() == null)
                && request.getQueryString().contains("search")) {

            String paramValue = request.getParameter("search");

            String listCompanies = companies.stream()
                    .filter(string -> string.contains(paramValue))
                    .collect(Collectors.joining("\n"));
            String res = listCompanies.length() > 0 ? listCompanies : "Companies not found";
            out.println(res);

        } else {
            out.println(String.join("\n", companies));
        }
        // END
    }
}
