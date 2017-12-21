package hey.action;

import java.rmi.RemoteException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import hey.model.HeyBean;

public class SignUpAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private String name=null;
	private String username=null;
	private String password=null;
	private String phonenumber=null;
	private String address=null;
	private String expiracy=null;
	private String expiracyday=null;
	private String expiracymonth=null;
	private String expiracyyear=null;
	private String ccnumber=null;
	private String organicunit=null;
	private String occupation=null;
	private String permission=null;
	
	private static final long serialVersionUID = 4L;
	private Map<String,Object> session;
	
	@Override
	public String execute() throws RemoteException{
		
		
		if (this.getHeyBean().getRegistarPessoa(this.name, this.username, this.password, this.phonenumber, this.address, this.expiracyday, this.expiracymonth, this.expiracyyear, this.ccnumber, this.organicunit, this.occupation, this.permission)) {
			return SUCCESS;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password; // what about this input? 
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * @param phonenumber the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the expiracy
	 */
	public String getExpiracy() {
		return expiracy;
	}

	/**
	 * @param expiracy the expiracy to set
	 */
	public void setExpiracy(String expiracy) {
		this.expiracy = expiracy;
	}

	/**
	 * @return the expiracyday
	 */
	public String getExpiracyday() {
		return expiracyday;
	}

	/**
	 * @param expiracyday the expiracyday to set
	 */
	public void setExpiracyday(String expiracyday) {
		this.expiracyday = expiracyday;
	}

	/**
	 * @return the expiracymonth
	 */
	public String getExpiracymonth() {
		return expiracymonth;
	}

	/**
	 * @param expiracymonth the expiracymonth to set
	 */
	public void setExpiracymonth(String expiracymonth) {
		this.expiracymonth = expiracymonth;
	}

	/**
	 * @return the expiracyyear
	 */
	public String getExpiracyyear() {
		return expiracyyear;
	}

	/**
	 * @param expiracyyear the expiracyyear to set
	 */
	public void setExpiracyyear(String expiracyyear) {
		this.expiracyyear = expiracyyear;
	}

	/**
	 * @return the ccnumber
	 */
	public String getCcnumber() {
		return ccnumber;
	}

	/**
	 * @param ccnumber the ccnumber to set
	 */
	public void setCcnumber(String ccnumber) {
		this.ccnumber = ccnumber;
	}

	/**
	 * @return the organicunit
	 */
	public String getOrganicunit() {
		return organicunit;
	}

	/**
	 * @param organicunit the organicunit to set
	 */
	public void setOrganicunit(String organicunit) {
		this.organicunit = organicunit;
	}

	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	
	

}
