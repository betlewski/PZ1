package pl.edu.utp.pz1.servlets;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "InitServlet", value = "/init")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Project project = new Project("Nowy projekt", "Opis nowego projektu");
        project.setCreateDateTime(LocalDateTime.now());
        project.setSubmitDate(LocalDate.now().plusDays(7));

        Task task1 = new Task("Zadanie 1", "Opis zadania 1", 1);
        task1.setCreateDateTime(LocalDateTime.now());
        task1.setProject(project);

        Task task2 = new Task("Zadanie 2", "Opis zadania 2", 2);
        task2.setCreateDateTime(LocalDateTime.now());
        task2.setProject(project);

        EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(project);
            entityManager.persist(task1);
            entityManager.persist(task2);
            entityManager.getTransaction().commit();

            entityManager.refresh(project);
            List<Task> tasks = project.getTasks();
            System.out.printf("Project with ID: %d, name: %s%n", project.getProjectId(), project.getName());
            for (Task task : tasks) {
                System.out.printf("Task with ID: %d, name: %s%n", task.getTaskId(), task.getName());
            }
        } finally {
            entityManager.close();
        }
    }

}
