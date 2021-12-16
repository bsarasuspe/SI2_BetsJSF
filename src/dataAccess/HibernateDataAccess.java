package dataAccess;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;

import com.mysql.cj.xdevapi.Result;

import configuration.UtilDate;
import domain.Event;
import domain.Question;
import eredua.HibernateUtil;
import exceptions.QuestionAlreadyExist;

public class HibernateDataAccess implements HibernateDataAccessInterface {

	@Override
	public void open() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void emptyDatabase() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeDB() {
		Calendar today = Calendar.getInstance();

		int month = today.get(Calendar.MONTH);
		month += 1;
		int year = today.get(Calendar.YEAR);
		if (month == 12) {
			month = 0;
			year += 1;
		}

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
		Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
		Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
		Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
		Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
		Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
		Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
		Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
		Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
		Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

		session.save(ev1);
		session.save(ev2);
		session.save(ev3);
		session.save(ev4);
		session.save(ev5);
		session.save(ev6);
		session.save(ev7);
		session.save(ev8);
		session.save(ev9);
		session.save(ev10);

		Question q1;
		Question q2;
		Question q3;
		Question q4;
		Question q5;
		Question q6;

		q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
		q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
		q3 = ev2.addQuestion("¿Quién ganará el partido?", 1);
		q4 = ev2.addQuestion("¿Cuántos goles se marcarán?", 2);
		q5 = ev3.addQuestion("¿Quién ganará el partido?", 1);
		q6 = ev3.addQuestion("¿Habrá goles en la primera parte?", 2);

		session.save(q1);
		session.save(q2);
		session.save(q3);
		session.save(q4);
		session.save(q5);
		session.save(q6);

		session.getTransaction().commit();

	}

	@Override
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Question q = new Question();
		q.setQuestion(question);
		q.setBetMinimum(betMinimum);
		try {
			q.setEvent((Event) session.get(Event.class, event.getEventNumber()));
			session.persist(q);
			session.getTransaction().commit();
		} catch (org.hibernate.PropertyValueException ex) {
			System.out.println("Errorea: data falta da ");
			q = null;
			session.getTransaction().rollback();
			q = null;
		} catch (Exception ex) {
			System.out.println("Errorea: gertaera ez da existitzen: " + ex.toString());
			q = null;
		}
		return null;
	}
	
	public boolean register(String email, String username, String password) {
		return false;
	}

	@Override
	public List<Event> getEvents(Date date) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Event e where e.eventDate = :date");
		q.setParameter("date", date);
		List result = q.list();
		session.getTransaction().commit();
		return result;
	}

	@Override
	public List<Date> getEventsMonth(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existQuestion(Event event, String question) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		Calendar today = Calendar.getInstance();

		int month = today.get(Calendar.MONTH);
		month += 1;
		int year = today.get(Calendar.YEAR);
		if (month == 12) {
			month = 0;
			year += 1;
		}

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		System.out.println(" ");
		System.out.println("Sortu galdera bat:");

		String question = "Galdera proba";
		float betMinimum = (float) 1.2;
		Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));

		Question q2 = new Question();
		q2.setQuestion(question);
		q2.setBetMinimum(betMinimum);
		try {
			q2.setEvent((Event) session.get(Event.class, ev1.getEventNumber()));
			session.persist(q2);
			session.getTransaction().commit();
		} catch (org.hibernate.PropertyValueException ex) {
			System.out.println("Errorea: data falta da ");
			q2 = null;
			session.getTransaction().rollback();
			q2 = null;
		} catch (Exception ex) {
			System.out.println("Errorea: gertaera ez da existitzen: " + ex.toString());
			q2 = null;
		}

	}

}
