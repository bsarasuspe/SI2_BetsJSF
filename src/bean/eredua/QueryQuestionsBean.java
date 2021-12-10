package bean.eredua;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class QueryQuestionsBean {

	private BLFacade facadeBL; 
	private List<Event> gertaerak;
	private Event gertaera;
	private List<Question> galderak;
	private Question galdera;
	private Date data;
	private float minBet;
	private String questionValue;

	public QueryQuestionsBean() {
		facadeBL = FacadeBean.getBusinessLogic();
	}

	public List<Event> getGertaerak() {
		return gertaerak;
	}

	public void setGertaerak(List<Event> gertaerak) {
		this.gertaerak = gertaerak;
	}

	public List<Question> getGalderak() {
		return galderak;
	}

	public void setGalderak(List<Question> galderak) {
		this.galderak = galderak;
	}

	public Question getGaldera() {
		return galdera;
	}

	public void setGaldera(Question galdera) {
		this.galdera = galdera;
	}

	public String getItzuli() {
		return "return";
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
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Selected date: " + event.getObject()));
		this.gertaerak = facadeBL.getEvents((Date) event.getObject());
	}

	public void onEventSelect(SelectEvent event) {
		this.galderak = this.gertaera.getQuestions();
		this.gertaera = (Event) event.getObject();
		FacesContext.getCurrentInstance().addMessage("nireForm:mezuak",
				new FacesMessage("Selected event: " + gertaera.getDescription()));

	}

	public void onQuestionSelect(SelectEvent event) {
		this.galdera = (Question) event.getObject();
		// Egia esan, selection="#{login.mota}" atributuarekin ere lortzen da
		FacesContext.getCurrentInstance().addMessage("nireForm:mezuak",
				new FacesMessage("Selected question: " + galdera.getQuestion()));
	}

	public void listener(AjaxBehaviorEvent event) {
		// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Selected
		// event: "));
		this.gertaera = (Event) event.getSource();
	}

	public float getMinBet() {
		return minBet;
	}

	public void setMinBet(float minBetValue) {
		this.minBet = minBetValue;
	}

	public String getQuestionValue() {
		return questionValue;
	}

	public void setQuestionValue(String questionValue) {
		this.questionValue = questionValue;
	}

	public void createQuestion() throws EventFinished, QuestionAlreadyExist {
		facadeBL.createQuestion(this.gertaera, this.questionValue, this.minBet);
		FacesContext.getCurrentInstance().addMessage("nireForm:mezuak",
				new FacesMessage("Question created: " + this.questionValue));
	}
}
