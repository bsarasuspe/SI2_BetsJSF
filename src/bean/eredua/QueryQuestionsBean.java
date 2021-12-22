package bean.eredua;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class QueryQuestionsBean {

	private BLFacade facadeBL;
	private List<Event> gertaerak;
	private Event gertaera;
	private List<Question> galderak;
	private Event event;
	private List<Event> allEvents;
	private Question galdera;
	private Date data;
	private float minBet;
	private String questionValue;
	private String rUsername;
	private String rEmail;
	private String rPassword1;
	private String rPassword2;
	private String lUsername;
	private String lPassword;
	private User user;

	public QueryQuestionsBean() {
		facadeBL = FacadeBean.getBusinessLogic();
		showAllEvents();
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

	public String getrUsername() {
		return rUsername;
	}

	public void setrUsername(String rUsername) {
		this.rUsername = rUsername;
	}

	public String getrEmail() {
		return rEmail;
	}

	public void setrEmail(String rEmail) {
		this.rEmail = rEmail;
	}

	public String getlUsername() {
		return lUsername;
	}

	public void setlUsername(String lUsername) {
		this.lUsername = lUsername;
	}

	public String getrPassword1() {
		return rPassword1;
	}

	public void setrPassword1(String rPassword1) {
		this.rPassword1 = rPassword1;
	}

	public String getrPassword2() {
		return rPassword2;
	}

	public void setrPassword2(String rPassword2) {
		this.rPassword2 = rPassword2;
	}

	public String getlPassword() {
		return lPassword;
	}

	public void setlPassword(String lPassword) {
		this.lPassword = lPassword;
	}

	public String login() {
		if(lUsername.length() > 0) {
			if(lPassword.length() > 0) {
				user = facadeBL.login(lUsername, lPassword);
				if (user == null) {
					FacesContext.getCurrentInstance().addMessage("mezuak",
							new FacesMessage("Erabiltzailea eta pasahitza ez dira zuzenak."));
					return "error";
				} else {
					return "ok";
				}
			}else {
				FacesContext.getCurrentInstance().addMessage("mezuak",
						new FacesMessage("Pasahitzak ezin du hutsa egon."));
				return "error";
			}
		}else {
			FacesContext.getCurrentInstance().addMessage("mezuak",
					new FacesMessage("Erabiltzaile izenak ezin du hutsa egon."));
			return "error";
		}

	}
	
	public void showAllEvents() {
		this.allEvents = facadeBL.showAllEvents();
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Event> getAllEvents() {
		return allEvents;
	}

	public void setAllEvents(List<Event> allEvents) {
		this.allEvents = allEvents;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String register() {
		if (rEmail.length() > 0) {
			if(Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", rEmail)) {
				if (rUsername.length() > 0) {
					if (rPassword1.length() > 0) {
						if (rPassword2.length() > 0) {
							if (rPassword1.equals(rPassword2)) {
								facadeBL.register(rEmail, rUsername, rPassword1);
								FacesContext.getCurrentInstance().addMessage("mezuak",
										new FacesMessage("Ongi erregistratu zara."));
								return "ok";
							} else {
								FacesContext.getCurrentInstance().addMessage("mezuak",
										new FacesMessage("Pasahitzak ez dira berdinak."));
								return "error";
							}
						} else {
							FacesContext.getCurrentInstance().addMessage("mezuak",
									new FacesMessage("Pasahitza errepikatu behar duzu."));
							return "error";
						}
					} else {
						FacesContext.getCurrentInstance().addMessage("mezuak",
								new FacesMessage("Pasahitzak ezindu hutsa egon."));
						return "error";
					}
				} else {
					FacesContext.getCurrentInstance().addMessage("mezuak",
							new FacesMessage("Erabiltzaileak ezin du hutsa egon."));
					return "error";
				}
			}else {
				FacesContext.getCurrentInstance().addMessage("mezuak",
						new FacesMessage("Eposta ez da zuzena."));
				return "error";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("mezuak", 
					new FacesMessage("Epostak ezin du hutsa egon."));
			return "error";
		}

	}

}
