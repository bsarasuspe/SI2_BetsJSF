package bean.eredua;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;

public class QueryQuestionsBean {
	
	private BLFacade facadeBL;
	private Vector<Event> gertaerak = new Vector<Event>();
	private Event gertaera;
	private Vector<Question> galderak = new Vector<Question>();
	private Question galdera;
	private Date data;
	
	public QueryQuestionsBean() {
		super();
	}
	
	public Vector<Event> getGertaerak() {
		return gertaerak;
	}

	public void setGertaerak(Vector<Event> gertaerak) {
		this.gertaerak = gertaerak;
	}

	public Vector<Question> getGalderak() {
		return galderak;
	}

	public void setGalderak(Vector<Question> galderak) {
		this.galderak = galderak;
	}

	public Question getGaldera() {
		return galdera;
	}

	public void setGaldera(Question galdera) {
		this.galdera = galdera;
	}
	
	public String getItzuli() {
		return "itzuli";
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Event getGertaera() {
		return gertaera;
	}

	public void setGertaera(Event gertaera) {
		this.gertaera = gertaera;
	}

	public void onDateSelect(SelectEvent event) {
		facadeBL = FacadeBean.getBusinessLogic();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data aukeratua: " + event.getObject()));
		this.gertaerak = facadeBL.getEvents((Date) event.getObject());
	}

	public void onEventSelect(SelectEvent event) {
		this.gertaera = (Event) event.getObject();
		// Egia esan, selection="#{login.mota}" atributuarekin ere lortzen da
		FacesContext.getCurrentInstance().addMessage("nireForm:mezuak",
				new FacesMessage("Aukeratutako gertaera:" + gertaera.getDescription()));
	}
	
	public void onQuestionSelect(SelectEvent event) {
		this.galdera = (Question) event.getObject();
		// Egia esan, selection="#{login.mota}" atributuarekin ere lortzen da
		FacesContext.getCurrentInstance().addMessage("nireForm:mezuak",
				new FacesMessage("Aukeratutako galdera:" + galdera.getQuestion()));
	}
}
