package pl.edu.utp.pz1.servlets;

import pl.edu.utp.pz1.model.Project;
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

@WebServlet(name = "ProjectsList", value = "/projects")
public class ProjectsList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
        List<Project> projects;

        try {
            TypedQuery<Project> query = entityManager.createQuery("SELECT p FROM Project p ORDER BY p.createDateTime", Project.class);
            projects = query.getResultList();
        } finally {
            entityManager.close();
        }

        projects.forEach(p -> System.out.println(p));
        request.setAttribute("projects", projects);
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/projects.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
