package hey.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import hey.model.HeyBean;

public class ManageVotingTableAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private String operationoftable = null;
	private String electionidoftable = null;
	private String tableid = null;
	private Map<String,Object> session;
	
	@Override
	public String execute() throws FileNotFoundException, IOException {
		if ((!this.operationoftable.equals("0") && !this.operationoftable.equals("1")) || this.electionidoftable.equals("") || this.tableid.equals("") || this.tableid==null || this.electionidoftable==null) {
			return ERROR;
			
		}
		else{
			if (this.getHeyBean().getGerirMesas(this.operationoftable, this.electionidoftable , this.tableid)) {
				return SUCCESS;
				
			}
			else{
				return ERROR;
			}
			
		}
		
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
	
	/**
	 * @return the operationoftable
	 */
	public String getOperationoftable() {
		return operationoftable;
	}
	/**
	 * @param operationoftable the operationoftable to set
	 */
	public void setOperationoftable(String operationoftable) {
		this.operationoftable = operationoftable;
	}
	/**
	 * @return the electionidoftable
	 */
	public String getElectionidoftable() {
		return electionidoftable;
	}
	/**
	 * @param electionidoftable the electionidoftable to set
	 */
	public void setElectionidoftable(String electionidoftable) {
		this.electionidoftable = electionidoftable;
	}
	/**
	 * @return the tableid
	 */
	public String getTableid() {
		return tableid;
	}
	/**
	 * @param tableid the tableid to set
	 */
	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
