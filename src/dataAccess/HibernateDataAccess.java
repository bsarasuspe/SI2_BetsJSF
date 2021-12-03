package dataAccess;

import java.util.Date;
import java.util.Vector;

import domain.Event;
import domain.Question;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Event> getEvents(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Date> getEventsMonth(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existQuestion(Event event, String question) {
		// TODO Auto-generated method stub
		return false;
	}

}
