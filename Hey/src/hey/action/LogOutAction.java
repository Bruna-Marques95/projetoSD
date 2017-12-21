package hey.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogOutAction extends ActionSupport{
	private static final long serialVersionUID = 4L;

	@Override
	public String execute() {
		ActionContext.getContext().getSession().put("loggedin", false);
		return SUCCESS;
	}

}
