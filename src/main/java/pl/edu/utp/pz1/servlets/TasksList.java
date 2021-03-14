package pl.edu.utp.pz1.servlets;

import pl.edu.utp.pz1.model.Task;
import pl.edu.utp.pz1.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TasksList", value = "/tasks")
public class TasksList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParameter = request.getParameter("project_id");
        if (idParameter != null) {
            int projectId = Integer.parseInt(idParameter);
            String pageString = request.getParameter("page");
            String sizeString = request.getParameter("sizePage");

            int page = 0, size = 5;
            List<Task> tasks;

            if (pageString != null) {
                page = Integer.parseInt(pageString);
            }
            if (sizeString != null) {
                size = Integer.parseInt(sizeString);
            }

            EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
            try {
                TypedQuery<Task> query = entityManager.createQuery(
                        "SELECT t FROM Task t WHERE t.project.projectId = :id ORDER BY t.createDateTime", Task.class);
                query.setParameter("id", projectId);
                query.setFirstResult(page * size);
                query.setMaxResults(size);
                tasks = query.getResultList();
            } finally {
                entityManager.close();
            }
            tasks.forEach(System.out::println);
            request.setAttribute("page", page);
            request.setAttribute("sizePage", size);
            request.setAttribute("tasks", tasks);
        }
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/tasks.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
