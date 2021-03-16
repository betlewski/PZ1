package pl.edu.utp.pz1.servlets.task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TaskServlet")
public class TaskServlet extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        try {
            outHeader(out, "Lab");
            out.println("<h2>Hello World!</h2>");
            outFooter(out);
        } finally {
            out.close();
        }
    }

    public static void outHeader(PrintWriter out, String title) {
        String str = "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"" + CONTENT_TYPE + "\">" +
                "<title>" + title + "</title>" +
                "</head><body>";
        out.print(str);
    }

    public static void outFooter(PrintWriter out) {
        out.print("</body></html>");
    }

}
