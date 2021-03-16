package pl.edu.utp.pz1.servlets.task;

import pl.edu.utp.pz1.model.Project;
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
        if (request.getParameter("btn_save") != null) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String sequenceString = request.getParameter("sequence");
            String projectIdString = request.getParameter("project_id");

            if (!StringUtils.isNullOrEmpty(name) && !StringUtils.isNullOrEmpty(projectIdString)) {
                Task task = new Task();
                task.setName(name);
                if (!StringUtils.isNullOrEmpty(description)) {
                    task.setDescription(description);
                }
                if (!StringUtils.isNullOrEmpty(sequenceString)) {
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
                        request.setAttribute("newTaskId", task.getTaskId());
                    }
                } finally {
                    entityManager.close();
                }
            } else {
                request.setAttribute("noRequiredData", true);
            }
        }
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/task_create.jsp");
        dispatcher.forward(request, response);
    }

}
