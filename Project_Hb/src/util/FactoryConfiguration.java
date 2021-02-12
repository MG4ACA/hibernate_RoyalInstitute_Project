package util;

import entity.Course;
import entity.Registration;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private  FactoryConfiguration() {
        Configuration configure = new Configuration().configure().addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Registration.class);
        sessionFactory = configure.buildSessionFactory();

    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration==null? factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
