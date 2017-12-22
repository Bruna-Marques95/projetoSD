package hey.action;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import hey.model.HeyBean;

public class CreateElectionAction extends ActionSupport implements SessionAware {
	private String titleofelection=null;
	private String descriptionofelection=null;
	private String typeofelection=null;
	private String startday=null;
	private String startmonth=null;
	private String startyear=null;
	private String starthour=null;
	private String startminute=null;
	private String endday=null;
	private String endmonth=null;
	private String endyear=null;
	private String endhour=null;
	private String endminute=null;
	private String organicunit=null;
	private String associationname=null;
	
	private Map<String,Object> session;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	
	@Override
	public String execute() throws FileNotFoundException, IOException, SQLException {
		String realtypeofelection; 
		if(this.titleofelection!=null && !titleofelection.equals("") && this.descriptionofelection!=null && !descriptionofelection.equals("")){
			if (this.typeofelection.equals("0")) {
				realtypeofelection="Conselho Geral";
				if(this.getHeyBean().getCriarEleicao(realtypeofelection, this.titleofelection, this.descriptionofelection, this.startday, this.startmonth, this.startyear, this.starthour, this.startminute, this.endday, this.endmonth, this.endyear, this.endhour, this.endminute, this.organicunit, this.associationname)){
					return SUCCESS;
				}
				else{
					return ERROR;
				}
			}
			else if (this.typeofelection.equals("1")) {
				realtypeofelection="Nucleo";
				if(this.getHeyBean().getCriarEleicao(realtypeofelection, this.titleofelection, this.descriptionofelection, this.startday, this.startmonth, this.startyear, this.starthour, this.startminute, this.endday, this.endmonth, this.endyear, this.endhour, this.endminute, this.organicunit, this.associationname)){
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
	
	public String getTitleofelection() {
		return titleofelection;
	}


	public void setTitleofelection(String titleofelection) {
		this.titleofelection = titleofelection;
	}


	public String getDescriptionofelection() {
		return descriptionofelection;
	}


	public void setDescriptionofelection(String descriptionofelection) {
		this.descriptionofelection = descriptionofelection;
	}


	public String getTypeofelection() {
		return typeofelection;
	}


	public void setTypeofelection(String typeofelection) {
		this.typeofelection = typeofelection;
	}


	/**
	 * @return the startday
	 */
	public String getStartday() {
		return startday;
	}


	/**
	 * @param startday the startday to set
	 */
	public void setStartday(String startday) {
		this.startday = startday;
	}


	/**
	 * @return the startmonth
	 */
	public String getStartmonth() {
		return startmonth;
	}


	/**
	 * @param startmonth the startmonth to set
	 */
	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}


	/**
	 * @return the startyear
	 */
	public String getStartyear() {
		return startyear;
	}


	/**
	 * @param startyear the startyear to set
	 */
	public void setStartyear(String startyear) {
		this.startyear = startyear;
	}


	/**
	 * @return the starthour
	 */
	public String getStarthour() {
		return starthour;
	}


	/**
	 * @param starthour the starthour to set
	 */
	public void setStarthour(String starthour) {
		this.starthour = starthour;
	}


	/**
	 * @return the startminute
	 */
	public String getStartminute() {
		return startminute;
	}


	/**
	 * @param startminute the startminute to set
	 */
	public void setStartminute(String startminute) {
		this.startminute = startminute;
	}


	/**
	 * @return the endday
	 */
	public String getEndday() {
		return endday;
	}


	/**
	 * @param endday the endday to set
	 */
	public void setEndday(String endday) {
		this.endday = endday;
	}


	/**
	 * @return the endmonth
	 */
	public String getEndmonth() {
		return endmonth;
	}


	/**
	 * @param endmonth the endmonth to set
	 */
	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}


	/**
	 * @return the endyear
	 */
	public String getEndyear() {
		return endyear;
	}


	/**
	 * @param endyear the endyear to set
	 */
	public void setEndyear(String endyear) {
		this.endyear = endyear;
	}


	/**
	 * @return the endhour
	 */
	public String getEndhour() {
		return endhour;
	}


	/**
	 * @param endhour the endhour to set
	 */
	public void setEndhour(String endhour) {
		this.endhour = endhour;
	}


	/**
	 * @return the endminute
	 */
	public String getEndminute() {
		return endminute;
	}


	/**
	 * @param endminute the endminute to set
	 */
	public void setEndminute(String endminute) {
		this.endminute = endminute;
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
	 * @return the associationname
	 */
	public String getAssociationname() {
		return associationname;
	}


	/**
	 * @param associationname the associationname to set
	 */
	public void setAssociationname(String associationname) {
		this.associationname = associationname;
	}
	
	

}
