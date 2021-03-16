package pl.edu.utp.pz1.servlets.task;

import pl.edu.utp.pz1.model.Project;
import pl.edu.utp.pz1.model.Task;
import pl.edu.utp.pz1.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TaskCreate", value = "/task-create")
public class TaskCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String sequenceString = request.getParameter("sequence");
        String projectIdString = request.getParameter("project_id");

        if (name != null && projectIdString != null) {
            Task task = new Task();
            task.setName(name);
            if (description != null) {
                task.setDescription(description);
            }
            if (sequenceString != null) {
                task.setSequence(Integer.parseInt(sequenceString));
            }
            EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
            try {
                int projectId = Integer.parseInt(projectIdString);
                Project project = entityManager.find(Project.class, projectId);
                if (project != null) {
                    task.setProject(project);
                    entityManager.getTransaction().begin();
                    entityManager.persist(task);
                    entityManager.getTransaction().commit();
                    System.out.println("Task with ID: " + task.getTaskId()
                            + ", name: " + task.getName() + " has been created!");
                }
            } finally {
                entityManager.close();
            }
        }
        // TODO:
        // powrót na stronę z zadaniami dla konkretnego projektu
    }

}
