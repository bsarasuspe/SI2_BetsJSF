package bean.eredua;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

public class FacadeBean {
	private static FacadeBean singleton = new FacadeBean();
	private static BLFacade facadeInterface;
	private Date data;

	private FacadeBean() {
		try {
			facadeInterface = new BLFacadeImplementation(new DataAccess());
		} catch (Exception e) {
			System.out.println("FacadeBean: negozioaren logika sortzean errorea: " + e.getMessage());
		}
	}

	public static BLFacade getBusinessLogic() {
		return facadeInterface;
	}
	

}
