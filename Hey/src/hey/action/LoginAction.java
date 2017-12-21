/**
 * Raul Barbosa 2014-11-07
 */
package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;
import hey.model.HeyBean;

public class LoginAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 4L;
	private Map<String, Object> session;
	private String username = null;
	private String password = null;
	private String permission = null;

	/*Já se liga ao servidor, agora é necessário confirmar o login*/
	/*Devemos usar a base de dados? Dá menos trabalho? Talvez*/
		/*-Colocar os métodos no servidor RMI e chamá-los diretamente, melhor do que andar andar a criar classes*/
	@Override
	public String execute() throws FileNotFoundException, IOException {
		// any username is accepted without confirmation (should check using RMI)
		try{
			if(this.username != null && !username.equals("") && !password.equals("") && this.password!=null){
				this.getHeyBean().setUsername(username);
				this.getHeyBean().setPassword(password);
				if(this.getHeyBean().getUserMatchesPassword()){
					session.put("username", username);
					session.put("loggedin", true); // this marks the user as logged in
					int ccIdentifier=this.getHeyBean().getCcnumbers(username, password);
					if (ccIdentifier!=0) {
						this.getHeyBean().setCcnumber(Integer.toString(this.getHeyBean().getCcnumbers(username,password)));
					}
					else{
						return ERROR;
					}
					permission=Integer.toString(this.getHeyBean().getPermissions(username,password));
					if (permission.equals("0")) {
						return SUCCESS;
						
					}
					else if (permission.equals("1")) {
						return "admin";
						
					}
				}
			
			}
			else{
				return ERROR;
			}
			
		}catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ERROR;
	}
	
	public void setUsername(String username) {
		this.username = username; // will you sanitize this input? maybe use a prepared statement?
	}

	public void setPassword(String password) {
		this.password = password; // what about this input? 
	}
	
	
	public HeyBean getHeyBean() {
		if(!session.containsKey("heyBean"))
			this.setHeyBean(new HeyBean());
		return (HeyBean) session.get("heyBean");
	}

	public void setHeyBean(HeyBean heyBean) {
		this.session.put("heyBean", heyBean);
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
