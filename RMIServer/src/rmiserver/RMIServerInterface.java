/**
 * Raul Barbosa 2014-11-07
 */
package rmiserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

public interface RMIServerInterface extends Remote {
	public boolean userMatchesPassword(String user, String password) throws RemoteException, FileNotFoundException, IOException;
	public ArrayList<String> getAllUsers() throws RemoteException;
	public boolean registerPerson(String nomePessoa, String nomeUtilizador, String password, String numeroTelefone, String morada, String dataValidadeDoCC, String numeroCC, String unidadeOrganica,String funcaoPessoa, String permissao) throws RemoteException, FileNotFoundException, IOException;
	public int getCcnumber(String username, String password) throws FileNotFoundException, IOException;
	public Connection connectToDB() throws FileNotFoundException, IOException;
	public int getPermission(String username, String password) throws FileNotFoundException, IOException;
	public boolean manageDepFac(String argumentosOperacaoGeral,String argumentoNome, String argumentoNovoNome, String argumentoNovoNovoNome) throws FileNotFoundException, IOException;

}
