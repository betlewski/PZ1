package pl.edu.utp.pz1.listeners;

import pl.edu.utp.pz1.util.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.getInstance().closeEntityManagerFactory();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateUtil.getInstance().createEntityManagerFactory();
    }

}

