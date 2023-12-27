package petProject4.hTask.JPA;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Jpa {

    public static void startSession() {
        final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        addingBooks(sessionFactory);
        showingBooks(sessionFactory);


        sessionFactory.close();
    }

    private static void showingBooks(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
        }
    }

    private static void addingBooks(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Books book1 = new Books();
            book1.setId(1);
            book1.setTitle("Patient");
            book1.setAuthor("Alex Michaelides");
            book1.setYear(2019);
            session.persist(book1);

            Books book2 = new Books();
            book2.setTitle("The Green Mile");
            book2.setAuthor("Stephen King");
            book2.setYear(1999);

            Books book3 = new Books();
            book3.setTitle("Misery");
            book3.setAuthor("Stephen King");
            book3.setYear(1987);

            Books book4 = new Books();
            book4.setTitle("Harry Potter and the Philosophers Stone");
            book4.setAuthor("Joanne Rowling");
            book4.setYear(1997);

            Books book5 = new Books();
            book5.setTitle("Harry Potter and the Chamber of Secrets");
            book5.setAuthor("Joanne Rowling");
            book5.setYear(1998);

            Books book6 = new Books();
            book6.setTitle("Harry Potter and the Prisoner of Azkaban");
            book6.setAuthor("Joanne Rowling");
            book6.setYear(1999);

            Books book7 = new Books();
            book7.setTitle("Harry Potter and the Goblet of Fire");
            book7.setAuthor("Joanne Rowling");
            book7.setYear(2000);

            Books book8 = new Books();
            book8.setTitle("Harry Potter and the Order of the Phoenix");
            book8.setAuthor("Joanne Rowling");
            book8.setYear(2003);

            Books book9 = new Books();
            book9.setTitle("Harry Potter and the Half-Blood Prince");
            book9.setAuthor("Joanne Rowling");
            book9.setYear(2005);

            Books book10 = new Books();
            book10.setTitle("Harry Potter and the Deathly Hallows");
            book10.setAuthor("Joanne Rowling");
            book10.setYear(2007);

            session.getTransaction().commit();
        }
    }

}
