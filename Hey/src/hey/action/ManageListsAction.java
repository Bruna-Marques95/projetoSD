package hey.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import hey.model.HeyBean;

public class ManageListsAction extends ActionSupport implements SessionAware{
	
	private Map<String,Object> session;
	private Map<Integer,String> elections;
	private String option=null;
	private String electionid=null;
	private String listname=null;
	private String typeoflist=null;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	
	public String execute() throws FileNotFoundException, IOException{
		if (!this.option.equals("0") && !this.option.equals("1")) {
			return ERROR;
		}
		else if(this.option.equals("0")){
			if (this.listname.equals("") || this.typeoflist.equals("") || this.electionid.equals("") || this.typeoflist==null||this.electionid==null || this.listname==null) {
				return ERROR;
				
			}
			else{
				if (this.getHeyBean().getGerirListas(this.option, this.electionid, this.listname, this.typeoflist)) {
					return SUCCESS;
				}
				else{
					return ERROR;
				}
				
			}
			
		}
		else if (this.option.equals("1")) {
			if (this.getHeyBean().getGerirListas(this.option, this.electionid, this.listname, this.typeoflist)) {
				return SUCCESS;
			}
			else{
				return ERROR;
			}
			
			
		}
		else{
			return ERROR;
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
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	/**
	 * @return the elections
	 */
	public Map<Integer,String> getElections() {
		return elections;
	}


	/**
	 * @param elections the elections to set
	 */
	public void setElections(Map<Integer,String> elections) {
		this.elections = elections;
	}
	
	public void setElectionid(String electionid) {
		this.electionid = electionid;
	}
	
	public void setListname(String listname) {
		this.listname = listname;
	}
	
	public void setOption(String option) {
		this.option = option;
	}
	
	public void setTypeoflist(String typeoflist) {
		this.typeoflist = typeoflist;
	}
	
	

}
