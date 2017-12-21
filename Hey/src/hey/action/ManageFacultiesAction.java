package hey.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import hey.model.HeyBean;

public class ManageFacultiesAction extends ActionSupport implements SessionAware{
	private String operation=null;
	private String typeofbuilding=null;
	private String nameofbuilding=null;
	private String newnameofbuilding=null;
	private Map<String, Object> session;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	
	@Override
	public String execute() throws FileNotFoundException, IOException {
		if(this.operation!=null && !this.operation.equals("") && this.nameofbuilding!=null && !this.nameofbuilding.equals("") &&!this.typeofbuilding.equals("") && this.typeofbuilding!=null){
			System.out.println("CHEGOU AQUI");
			this.getHeyBean().getGerirDepFac(this.operation, this.typeofbuilding,this.nameofbuilding, this.newnameofbuilding);
			return SUCCESS;
				
		}
		else{
			return ERROR;
		}	
	}
	

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

	/**
	 * @return the newnameofbuilding
	 */
	public String getNewnameofbuilding() {
		return newnameofbuilding;
	}

	/**
	 * @param newnameofbuilding the newnameofbuilding to set
	 */
	public void setNewnameofbuilding(String newnameofbuilding) {
		this.newnameofbuilding = newnameofbuilding;
	}

	/**
	 * @return the nameofbuilding
	 */
	public String getNameofbuilding() {
		return nameofbuilding;
	}

	/**
	 * @param nameofbuilding the nameofbuilding to set
	 */
	public void setNameofbuilding(String nameofbuilding) {
		this.nameofbuilding = nameofbuilding;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public HeyBean getHeyBean() {
		if(!session.containsKey("heyBean"))
			this.setHeyBean(new HeyBean());
		return (HeyBean) session.get("heyBean");
	}
	
	public void setHeyBean(HeyBean heyBean) {
		this.session.put("heyBean", heyBean);
	}


	/**
	 * @return the typeofbuilding
	 */
	public String getTypeofbuilding() {
		return typeofbuilding;
	}


	/**
	 * @param typeofbuilding the typeofbuilding to set
	 */
	public void setTypeofbuilding(String typeofbuilding) {
		this.typeofbuilding = typeofbuilding;
	}
	

}
