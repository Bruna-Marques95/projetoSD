package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Map;
import hey.model.HeyBean;

public class EditElection extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private String opedit=null;
	private String newarg1=null;
	private String newarg2=null;
	private String idelec=null;
	private Map<String, Object> session;
	
	@Override
	public String execute() throws FileNotFoundException, SQLException, IOException{
		if (!this.opedit.equals("0") && !this.opedit.equals("1")) {
			return ERROR;
		}
		else{
			if (this.getHeyBean().getEditarEleicao(this.opedit,this.newarg1, this.newarg2, this.idelec)) {
				return SUCCESS;
				
			}
			else{
				return ERROR;
			}
			
		}
			
	}
	
	
		
	public String getIdelec() {
		return idelec;
	}



	public void setIdelec(String idelec) {
		this.idelec = idelec;
	}



	public HeyBean getHeyBean(){
		if (!session.containsKey("heyBean")) {
			setHeyBean(new HeyBean());
		}
		return (HeyBean) session.get("heyBean");
	}
	
	public void setHeyBean(HeyBean heyBean) {
		this.session.put("heyBean", heyBean);
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	/**
	 * @return the newarg2
	 */
	public String getNewarg2() {
		return newarg2;
	}
	/**
	 * @param newarg2 the newarg2 to set
	 */
	public void setNewarg2(String newarg2) {
		this.newarg2 = newarg2;
	}
	/**
	 * @return the newarg1
	 */
	public String getNewarg1() {
		return newarg1;
	}
	/**
	 * @param newarg1 the newarg1 to set
	 */
	public void setNewarg1(String newarg1) {
		this.newarg1 = newarg1;
	}
	/**
	 * @return the opedit
	 */
	public String getOpedit() {
		return opedit;
	}
	/**
	 * @param opedit the opedit to set
	 */
	public void setOpedit(String opedit) {
		this.opedit = opedit;
	}

}
