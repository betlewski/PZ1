package pl.edu.utp.pz1.servlets;

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
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            LocalDate submitDate = LocalDate.parse(request.getParameter("submitDate"));
            EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
            Project project = new Project(name, description, submitDate);

            entityManager.getTransaction().begin();
            entityManager.persist(project);
            entityManager.getTransaction().commit();
            entityManager.close();

            request.setAttribute("newProjectId", project.getProjectId());
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/project_edit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
