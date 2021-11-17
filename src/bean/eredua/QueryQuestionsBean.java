package bean.eredua;

import java.util.List;

import businessLogic.BLFacade;
import domain.Event;

public class QueryQuestionsBean {
	
	BLFacade facadeBL;
	List<Event> gertaerak;
	
	public QueryQuestionsBean() {
		facadeBL = FacadeBean.getBusinessLogic();
		gertaerak = facadeBL.getEvents(eguna);
	}
}
