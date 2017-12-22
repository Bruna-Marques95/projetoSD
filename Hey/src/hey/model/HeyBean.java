/**
 * Raul Barbosa 2014-11-07
 */
package hey.model;


import java.util.ArrayList;
import java.util.Map;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import rmiserver.RMIServerInterface;

public class HeyBean {
	private RMIServerInterface server;
	private String username=null; // username and password supplied by the user
	private String password=null;
	private String ccnumber=null;
	private String permission=null; 
	private Map <Integer, String> elections;

	public HeyBean() {
		try {
			server = (RMIServerInterface) Naming.lookup("server");
		}
		catch(NotBoundException|MalformedURLException|RemoteException e) {
			e.printStackTrace(); // what happens *after* we reach this line?
		}
	}

	public ArrayList<String> getAllUsers() throws RemoteException {
		return server.getAllUsers(); // are you going to throw all exceptions?
	}

	public boolean getUserMatchesPassword() throws RemoteException {
		boolean sucesso;
		sucesso = server.userMatchesPassword(this.username, this.password);
		return sucesso;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getRegistarPessoa(String name, String username, String password, String phonenumber, String address, String expiracyday, String expiracymonth, String expiracyyear, String ccnumber, String organicunit, String occupation, String permisssion) throws RemoteException{
		String expiracydate=expiracyday+"-"+expiracymonth+"-"+expiracyyear;
		boolean sucesso;
		sucesso=server.registerPerson(name, username, password, phonenumber, address, expiracydate, ccnumber, organicunit, occupation, permisssion);
		return sucesso;
			
	}
	
	public boolean getGerirDepFac(String operation, String typeofbuilding, String nameofbuilding, String newnameofbuilding) throws FileNotFoundException, IOException{
		boolean sucesso;
		System.out.println("CHEGOU AO BEAN");
		sucesso=server.manageDepFac(operation, typeofbuilding ,nameofbuilding, newnameofbuilding);
		return sucesso;
			
	}
	
	public boolean getCriarEleicao(String typeofelection, String titleofelection, String descriptionofelection, String startday, String startmonth, String startyear, String starthour, String startminute, String endday, String endmonth, String endyear, String endhour,String endminute, String organicunit, String associationname) throws FileNotFoundException, IOException, SQLException{
		boolean sucesso;
		String startdate=startday+"-"+startmonth+"-"+startyear+"-"+starthour+"-"+startminute;
		String enddate=endday+"-"+endmonth+"-"+endyear+"-"+endhour+"-"+endminute;
		System.out.println(startdate);
		System.out.println(enddate);
		sucesso=server.createElection(typeofelection, titleofelection, descriptionofelection, startdate, enddate, organicunit, associationname);
		System.out.println(sucesso);
		return sucesso;
		
	}
	
	public boolean getGerirListas(String option, String electionid, String listname, String typeoflist) throws FileNotFoundException, IOException{
		boolean sucesso;
		sucesso=server.manageLists(option, electionid, listname, typeoflist);
		return sucesso;
		
		
	}
	
	public boolean getGerirMesas(String operationoftable, String electionid, String tableid) throws FileNotFoundException, IOException{
		boolean sucesso;
		sucesso=server.manageTables(operationoftable, electionid, tableid);
		return sucesso;
		
	}
	
	public boolean getEditarEleicao(String opedit, String newarg1, String newarg2, String idelec) throws FileNotFoundException, SQLException, IOException{
		boolean sucesso;
		sucesso=server.editElection(opedit, newarg1, newarg2, idelec);
		return sucesso;
		
	}
	
	
	
	public int getCcnumbers(String username, String password) throws FileNotFoundException, IOException{
		int ccnumber;
		ccnumber=server.getCcnumber(username, password);
		return ccnumber;
	}
	
	public int getPermissions(String username, String password) throws FileNotFoundException, IOException{
		int permissions;
		permissions=server.getPermission(username, password);
		return permissions;
		
		
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

	/**
	 * @return the elections
	 */
	public Map <Integer, String> getElections() {
		return elections;
	}

	/**
	 * @param elections the elections to set
	 */
	public void setElections(Map <Integer, String> elections) {
		this.elections = elections;
	}
		

	
	
	
}
