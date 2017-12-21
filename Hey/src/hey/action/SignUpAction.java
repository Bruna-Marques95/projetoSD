package hey.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import hey.model.HeyBean;

public class SignUpAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private String nomePessoa=null;
	private String nomeUtilizador=null;
	private String password=null;
	private String numeroTelefone=null;
	private String morada=null;
	private String dataValidadeDoCC=null;
	private String numeroCC=null;
	private String unidadeOrganica=null;
	private String funcaoPessoa=null;
	private String permissao=null;
	
	private static final long serialVersionUID = 4L;
	private Map<String,Object> session;
	
	public String execute(){
		if (getHeyBean().signup(this.nomePessoa, this.nomeUtilizador, this.password, this.numeroTelefone, this.morada, this.dataValidadeDoCC, numeroCC, this.unidadeOrganica, this.funcaoPessoa, this.permissao)) {
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
	

}
