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

@WebServlet(name = "TaskEdit", value = "/task-edit")
public class TaskEdit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPut(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPut(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String sequenceString = request.getParameter("sequence");
        String taskIdString = request.getParameter("task_id");

        if (name != null && taskIdString != null) {
            EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
            try {
                int taskId = Integer.parseInt(taskIdString);
                Task task = entityManager.find(Task.class, taskId);
                if (task != null) {
                    task.setName(name);
                    if (description != null) {
                        task.setDescription(description);
                    }
                    if (sequenceString != null) {
                        task.setSequence(Integer.parseInt(sequenceString));
                    }
                    entityManager.getTransaction().begin();
                    entityManager.persist(task);
                    entityManager.getTransaction().commit();
                    System.out.println("Task with ID: " + task.getTaskId()
                            + ", name: " + task.getName() + " has been edited!");
                }
            } finally {
                entityManager.close();
            }
        }
        // TODO:
        // powrót na stronę z zadaniami dla konkretnego projektu
    }

}
