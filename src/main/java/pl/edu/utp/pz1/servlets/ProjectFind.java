package pl.edu.utp.pz1.servlets;

import pl.edu.utp.pz1.model.Project;
import pl.edu.utp.pz1.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProjectFind", value = "/project-find")
public class ProjectFind extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParameter = request.getParameter("id");
        if (idParameter != null) {
            int projectId = Integer.parseInt(request.getParameter("id"));
            EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
            try {
                findByEntity(entityManager, projectId);
                findByJPQL(entityManager, projectId);
            } finally {
                entityManager.close();
            }
        }
    }

    private void findByEntity(EntityManager entityManager, int projectId) throws NoResultException {
        Project project = entityManager.find(Project.class, projectId);
        if (project != null) {
            System.out.println("Project with ID: " + projectId + " - name: " + project.getName());
        } else {
            System.out.println("Project with ID: " + projectId + " was not found!");
        }
    }

    private void findByJPQL(EntityManager entityManager, int projectId) throws NoResultException {
        TypedQuery<Project> query = entityManager.createQuery(
                "SELECT p FROM Project p WHERE p.projectId = :id", Project.class);
        query.setParameter("id", projectId);
        Project project = query.getSingleResult();
        if (project != null) {
            System.out.println("Project with ID: " + projectId + " - name: " + project.getName());
        } else {
            System.out.println("Project with ID: " + projectId + " was not found!");
        }
    }

}
