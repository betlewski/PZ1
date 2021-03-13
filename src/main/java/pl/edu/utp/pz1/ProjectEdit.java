package pl.edu.utp.pz1;

import pl.edu.utp.pz1.model.Project;
import pl.edu.utp.pz1.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "ProjectEdit")
public class ProjectEdit extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("btn_save") != null) {
            EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
            Project project = new Project("Projekt testowy", "Opis testowy");
            project.setCreateDateTime(LocalDateTime.of(2021, 03, 6, 10, 0));
            project.setSubmitDateTime(LocalDateTime.of(2021, 03, 7, 10, 0));
            entityManager.getTransaction().begin();
            entityManager.persist(project);
            entityManager.getTransaction().commit();
            entityManager.close();

            request.setAttribute("project", project);
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/project_edit.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
