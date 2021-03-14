package pl.edu.utp.pz1.servlets;

import pl.edu.utp.pz1.model.Project;
import pl.edu.utp.pz1.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProjectDelete", value = "/project-delete")
public class ProjectDelete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParameter = request.getParameter("id");
        if (idParameter != null) {
            int projectId = Integer.parseInt(request.getParameter("id"));
            EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
            Project project = null;
            try {
                project = entityManager.find(Project.class, projectId);
                entityManager.getTransaction().begin();
                entityManager.remove(project);
                entityManager.getTransaction().commit();
            } finally {
                entityManager.close();
            }
            if (project != null) {
                System.out.println("Project with ID: " + projectId + " has been removed!");
            } else {
                System.out.println("Project with ID: " + projectId + " was not found!");
            }
        }
    }

}
