package pl.edu.utp.pz1.servlets.task;

import pl.edu.utp.pz1.model.Task;
import pl.edu.utp.pz1.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TaskDelete", value = "/task-delete")
public class TaskDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doDelete(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doDelete(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParameter = request.getParameter("task_id");
        if (idParameter != null) {
            int taskId = Integer.parseInt(idParameter);
            EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
            Task task;
            try {
                task = entityManager.find(Task.class, taskId);
                entityManager.getTransaction().begin();
                entityManager.remove(task);
                entityManager.getTransaction().commit();
            } finally {
                entityManager.close();
            }
            if (task != null) {
                System.out.println("Task with ID: " + taskId + " has been removed!");
            } else {
                System.out.println("Task with ID: " + taskId + " was not found!");
            }
        }
        response.sendRedirect(request.getHeader("referer"));
    }

}
