package pl.edu.utp.pz1.servlets.project;

import pl.edu.utp.pz1.model.Project;
import pl.edu.utp.pz1.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ProjectEdit", value = "/project-edit")
public class ProjectEdit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("btn_save") != null) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            LocalDate submitDate = LocalDate.parse(request.getParameter("submitDate"));
            EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
            Project projectToUpdate = findProjectByJPQL(entityManager, Integer.parseInt(id));
            projectToUpdate.setName(name);
            projectToUpdate.setDescription(description);
            projectToUpdate.setSubmitDate(submitDate);

            entityManager.getTransaction().begin();
            entityManager.persist(projectToUpdate);
            entityManager.getTransaction().commit();
            entityManager.close();

            request.setAttribute("successfulEdit", true);
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/project_edit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectId = request.getParameter("project_id");
        if (projectId != null) {
            EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
            Project projectByJPQL = findProjectByJPQL(entityManager, Integer.parseInt(projectId));
            request.setAttribute("project", projectByJPQL);
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/project_edit.jsp");
        dispatcher.forward(request, response);
    }

    private Project findProjectByJPQL(EntityManager entityManager, int projectId) throws NoResultException {
        TypedQuery<Project> query = entityManager.createQuery(
                "SELECT p FROM Project p WHERE p.projectId = :id", Project.class);
        query.setParameter("id", projectId);
        Project project = query.getSingleResult();
        if (project != null) {
            return project;
        } else {
            throw new IllegalArgumentException("Project with ID: " + projectId + " was not found!");
        }
    }
}
