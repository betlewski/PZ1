package pl.edu.utp.pz1.servlets.task;

import pl.edu.utp.pz1.model.Task;
import pl.edu.utp.pz1.util.HibernateUtil;
import pl.edu.utp.pz1.util.StringUtils;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TaskEdit", value = "/task-edit")
public class TaskEdit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String taskIdString = request.getParameter("task_id");
        if (taskIdString != null) {
            EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
            try {
                int taskId = Integer.parseInt(taskIdString);
                Task task = entityManager.find(Task.class, taskId);
                if (task != null) {
                    request.setAttribute("task", task);
                    request.setAttribute("project_id", task.getProject().getProjectId());
                }
            } finally {
                entityManager.close();
            }
        }
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/task_edit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("btn_save") != null) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String sequenceString = request.getParameter("sequence");
            String taskIdString = request.getParameter("task_id");

            if (!StringUtils.isNullOrEmpty(taskIdString)) {
                EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
                try {
                    int taskId = Integer.parseInt(taskIdString);
                    Task task = entityManager.find(Task.class, taskId);
                    if (task != null) {
                        if (!StringUtils.isNullOrEmpty(name)) {
                            task.setName(name);
                            task.setDescription(description);
                            Integer sequence = null;
                            if (!StringUtils.isNullOrEmpty(sequenceString)) {
                                sequence = Integer.parseInt(sequenceString);
                            }
                            task.setSequence(sequence);
                            entityManager.getTransaction().begin();
                            entityManager.persist(task);
                            entityManager.getTransaction().commit();

                            request.setAttribute("successfulEdit", true);
                            System.out.println("Task with ID: " + task.getTaskId()
                                    + ", name: " + task.getName() + " has been edited!");
                        } else {
                            request.setAttribute("noRequiredData", true);
                        }
                        request.setAttribute("task", task);
                        request.setAttribute("project_id", task.getProject().getProjectId());
                    }
                } finally {
                    entityManager.close();
                }
            }
        }
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/task_edit.jsp");
        dispatcher.forward(request, response);
    }

}
