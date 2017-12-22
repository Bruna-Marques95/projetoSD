/**
 * Raul Barbosa 2014-11-07
 */
package rmiserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Calendar;

public class RMIServer extends UnicastRemoteObject implements RMIServerInterface {
	private static final long serialVersionUID = 20141107L;
	private HashMap<String, String> users;
	private static String nomeFicheiroComEnderecoIP = "EnderecoIPnaRede.txt";
	
	public RMIServer() throws RemoteException {
		super();
		users = new HashMap<String, String>();
		users.put("bender", "rodriguez"); // static users and passwords, to simplify the example
		users.put("fry",    "philip");
		users.put("leela",  "turanga");
		users.put("homer",  "simpson");
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		RMIServerInterface s = new RMIServer();
		LocateRegistry.createRegistry(1099).rebind("server", s);
		System.out.println("Server ready...");
		
		/*Calendar calSistema = Calendar.getInstance();
		int diaSistema = calSistema.get(Calendar.DAY_OF_MONTH);
        int mesSistema = calSistema.get(Calendar.MONTH);
        int anoSistema = calSistema.get(Calendar.YEAR);
        calSistema.set(anoSistema, mesSistema, diaSistema);
		Calendar cal = stringToData("25-12-2017");
		if(!validaDatas(calSistema,cal)){
			System.out.println(cal.get(Calendar.DAY_OF_MONTH) + ":" + cal.get(Calendar.MONTH) + " : " + cal.get(Calendar.YEAR));
		}
		System.out.println(cal.get(Calendar.DAY_OF_MONTH) + ":" + cal.get(Calendar.MONTH) + " : " + cal.get(Calendar.YEAR));*/
		
		
		
		
	}
	public static String leFicheiroComEnderecoIP(String nomeFicheiro) throws FileNotFoundException, IOException {
		try{
			BufferedReader fich = new BufferedReader(new FileReader(nomeFicheiro));

	        //Lê o ficheiro que contem o endereco IP na rede(Necessario alterar antes de correr o programa)
	        String EnderecoIpNaRede = fich.readLine();
	        fich.close();
	        
	        return EnderecoIpNaRede;
		}
		catch(IOException e){
			e.printStackTrace();
			return "";
			
		}
		
	}
	
	//Code adapted from: http://theopentutorials.com/tutorials/java/jdbc/jdbc-mysql-connection-tutorial/
	public Connection connectToDB() throws FileNotFoundException, IOException{
		String databaseAddress=leFicheiroComEnderecoIP(nomeFicheiroComEnderecoIP);
        String firstPartOfDatabaseAddress ="jdbc:oracle:thin:@";
        String lastPartOfDatabaseAddress =":1521:xe";
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("There is no oracle driver");
        }
        try {
            connection = DriverManager.getConnection(firstPartOfDatabaseAddress+databaseAddress+lastPartOfDatabaseAddress, "utilizadorBD", "utilizadorBD");
           
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return connection;
    }
	
	
	
	
	//Auxiliary method to help remove all information about lists from database
	public int verificaSeExisteChavePessoaLista(String valorParam) throws FileNotFoundException, IOException{      
		Connection connectionToDB = null;
		connectionToDB = connectToDB();
	    String query = "Select count(*) from PESSOA_LISTA WHERE LISTANOMELISTA = ? ";
	    try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
	    	ps.setString(1, valorParam);
	        ResultSet rs= ps.executeQuery();
	        rs.next();
	        return rs.getInt(1);
	    }
	    catch (SQLException e){
	    	System.out.println(e);
	        return 0;
	    }

	 }
	
	public String devolveUnidadeOrganicaDeMesa(int idMesa) throws FileNotFoundException, IOException{
		Connection connectionToDB = null;
		connectionToDB = connectToDB();
	    String query = "Select UONOME from MESA_VOTO WHERE ID = ? ";
	    try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
	    	ps.setInt(1, idMesa);
	        ResultSet rs= ps.executeQuery();
	        rs.next();
	        return rs.getString(1);
	    }
	    catch (SQLException e){
	    	System.out.println(e);
	        return "";
	    }
		
	}
	
	public String devolveUnidadeOrganicaDeEleicao(int idEleicao) throws FileNotFoundException, IOException{
		Connection connectionToDB = null;
		connectionToDB = connectToDB();
	    String query = "Select UNIDADEORGANICANOME from ELEICAO WHERE ID = ? ";
	    try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
	    	ps.setInt(1, idEleicao);
	        ResultSet rs= ps.executeQuery();
	        rs.next();
	        return rs.getString(1);
	    }
	    catch (SQLException e){
	    	System.out.println(e);
	        return "";
	    }
		
	}
	
	
	
	
	
	/**
	 * returns true if and only if user matches password
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public boolean userMatchesPassword(String username, String password) throws FileNotFoundException, IOException {
		Connection connectionToDB = null;
		connectionToDB = connectToDB();
		String query = "Select count(*) from PESSOA where NOMEUTILIZADOR = ? and PASSWORD = ?";
		try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs= ps.executeQuery();
            rs.next();

            return (rs.getInt(1)>=1);
        }
        catch (SQLException e){
            System.out.println(e);
            return false;
        }
	}
	
	
	public void apagaAssociacaoListaAPedido(String valorParam) throws FileNotFoundException, IOException{
        
		Connection connectionToDB = null;
		connectionToDB = connectToDB();
        String query = "DELETE from PESSOA_LISTA WHERE LISTANOMELISTA = ? ";
        try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
            ps.setString(1, valorParam);
            ps.executeUpdate();
            connectionToDB.commit();
        }
        catch (SQLException e){
            System.out.println(e);
        }

    }
	
	public int getCcnumber(String username, String password) throws FileNotFoundException, IOException{
		Connection connectionToDB = null;
		connectionToDB = connectToDB();
		String query = "Select NUMCC from PESSOA where NOMEUTILIZADOR = ? and PASSWORD = ?";
		try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs= ps.executeQuery();
            rs.next();

            return (rs.getInt(1));
        }
        catch (SQLException e){
            System.out.println(e);
            return 0;
        }
		
	}
	
	public int getPermission(String username, String password) throws FileNotFoundException, IOException{
		Connection connectionToDB = null;
		connectionToDB = connectToDB();
		String query = "Select PERMISSAO from PESSOA where NOMEUTILIZADOR = ? and PASSWORD = ?";
		try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs= ps.executeQuery();
            rs.next();

            return (rs.getInt(1));
        }
        catch (SQLException e){
            System.out.println(e);
            return 0;
        }
		
	}
	
	
	/**
	 * returns all the user names
	 */
	public ArrayList<String> getAllUsers() {
		System.out.println("Looking up all users...");
		return new ArrayList<String>(users.keySet());	
	}
	
	//Função utilizada para verificar a validade do código de um cartão de cidadão : Função reutilizada da meta 1 do projeto de SD
    public static boolean verificaIDCC(String id){
        try{
            Integer.parseInt(id);
            if(Integer.parseInt(id) >= 10000000 && Integer.parseInt(id) <= 99999999){
                return true;
            }
            else{
                return false;
            }

        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public static boolean verificaTelefone(String numeroTel){
        try{
            Integer.parseInt(numeroTel);
            if(Integer.parseInt(numeroTel) >= 100000000 && Integer.parseInt(numeroTel) <= 999999999){
                return true;
            }
            else{
                return false;
            }

        }catch(NumberFormatException e){
            return false;
        }

    }



    public static boolean validaDatas(Calendar dI, Calendar dF) {
        System.out.println("DATA dI-> "+dI.get(Calendar.DAY_OF_MONTH) + ":" + (dI.get(Calendar.MONTH)) + ":" + dI.get(Calendar.YEAR));
        System.out.println("DATA dF-> "+dF.get(Calendar.DAY_OF_MONTH) + ":" + (dF.get(Calendar.MONTH)) + ":" + dF.get(Calendar.YEAR));
        if(dI.get(Calendar.YEAR) >= dF.get(Calendar.YEAR)){
            if(dI.get(Calendar.YEAR) == dF.get(Calendar.YEAR)){
                if(dI.get(Calendar.MONTH) >= dF.get(Calendar.MONTH)){
                    if(dI.get(Calendar.MONTH) == dF.get(Calendar.MONTH)){
                        if(dI.get(Calendar.DAY_OF_MONTH) >= dF.get(Calendar.DAY_OF_MONTH)){
                            System.out.println(dI.get(Calendar.DAY_OF_MONTH));
                            if(dI.get(Calendar.DAY_OF_MONTH) == dF.get(Calendar.DAY_OF_MONTH)){
                                return false;
                            }
                            else return false;
                        }
                        else return true;
                    }
                    else return false;
                }
                else return true;
            }
            else return false;
        }
        else return true;
    }
    
    public static boolean comparaDatas2(Calendar dI, Calendar dF) {
        System.out.println("DATA dI-> "+dI.get(Calendar.DAY_OF_MONTH) + ":" + (dI.get(Calendar.MONTH)) + ":" + dI.get(Calendar.YEAR) + ":" + dI.get(Calendar.HOUR_OF_DAY) + ":" + dI.get(Calendar.MINUTE));
        System.out.println("DATA dF-> "+dF.get(Calendar.DAY_OF_MONTH) + ":" + (dF.get(Calendar.MONTH)) + ":" + dF.get(Calendar.YEAR) + ":" + dF.get(Calendar.HOUR_OF_DAY) + ":" + dF.get(Calendar.MINUTE));
        if(dI.get(Calendar.YEAR) >= dF.get(Calendar.YEAR)){
            if(dI.get(Calendar.YEAR) == dF.get(Calendar.YEAR)){
                if(dI.get(Calendar.MONTH) >= dF.get(Calendar.MONTH)){
                    if(dI.get(Calendar.MONTH) == dF.get(Calendar.MONTH)){
                        if(dI.get(Calendar.DAY_OF_MONTH) >= dF.get(Calendar.DAY_OF_MONTH)){
                            if(dI.get(Calendar.DAY_OF_MONTH) == dF.get(Calendar.DAY_OF_MONTH)){
                                if (dI.get(Calendar.HOUR_OF_DAY) >= dF.get(Calendar.HOUR_OF_DAY)){
                                    if (dI.get(Calendar.HOUR_OF_DAY) == dF.get(Calendar.HOUR_OF_DAY)){
                                        return (dI.get(Calendar.MINUTE) < dF.get(Calendar.MINUTE));
                                    }
                                    else return false;
                                }
                                else return true;
                            }
                            else return false;
                        }
                        else return true;
                    }
                    else return false;
                }
                else return true;
            }
            else return false;
        }
        else return true;

    }
    
    
    
    
    
    public static boolean verificaFuncao(String funcao){
        if (funcao.equalsIgnoreCase("aluno") || funcao.equalsIgnoreCase("professor") || funcao.equalsIgnoreCase("funcionario")){
            return true;
        }
        else{
            return false;
        }
    }
    
    
    public static boolean verificaPermissao(String permissao){
        if (permissao.equalsIgnoreCase("0") || permissao.equalsIgnoreCase("1")){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String devolveTipoPorIDEleicao(String iD) throws FileNotFoundException, IOException{
    	Connection connectionToDB = null;
		connectionToDB = connectToDB();
       
        String query = "Select TIPO from ELEICAO WHERE ID = ? ";
        try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
            ps.setInt(1, Integer.parseInt(iD));
            ResultSet rs= ps.executeQuery();
            rs.next();

            return rs.getString(1);
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return "";

    }
    
    public String devolveUOporChave(int idMesa) throws FileNotFoundException, IOException{
        Connection connection=null;
        connection=connectToDB();

        String query = "Select UONOME from MESA_VOTO WHERE MESA_VOTOID = ? ";
        try (PreparedStatement ps= connection.prepareStatement(query)){
            ps.setInt(1, idMesa);
            ResultSet rs= ps.executeQuery();
            rs.next();

            return rs.getString(1);
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return "";


    }
    
    
    
    
    
    
    
    
	
	public boolean registerPerson(String nomePessoa, String nomeUtilizador, String password, String numeroTelefone, String morada, String dataValidadeDoCC, String numeroCC, String unidadeOrganica,String funcaoPessoa, String permissao) throws FileNotFoundException, IOException{
		System.out.println(nomePessoa + " : " + nomeUtilizador + " : " + password + " : " + numeroTelefone + " : " + morada + " : " + dataValidadeDoCC + " : " + numeroCC + " : " + unidadeOrganica + " : " + funcaoPessoa + " : " + permissao);
		
		if (!verificaFuncao(funcaoPessoa)) {
			return false;
		}
		else if (!verificaIDCC(numeroCC)) {
			return false;
		}
		else if (!verificaTelefone(numeroTelefone)) {
			return false;
		}
		
		int dia=0;
		int mes=0;
		int ano=0;
		
		String [] arrayDateComponents = dataValidadeDoCC.split("-");
		System.out.println(arrayDateComponents);
		dia= Integer.parseInt(arrayDateComponents[0]);
		mes= Integer.parseInt(arrayDateComponents[1]);
		ano= Integer.parseInt(arrayDateComponents[2]);
		Calendar cal = Calendar.getInstance();
		cal.set(ano, mes-1, dia);
		
		Calendar calSistema = Calendar.getInstance();
		int diaSistema = calSistema.get(Calendar.DAY_OF_MONTH);
        int mesSistema = calSistema.get(Calendar.MONTH);
        int anoSistema = calSistema.get(Calendar.YEAR);
        calSistema.set(anoSistema, mesSistema, diaSistema);
		
		if(!validaDatas(calSistema,cal)){
			return false;
		}
		
		Connection connectionToDB = null;
		connectionToDB = connectToDB();
		 String query = "INSERT into PESSOA"
                 + " VALUES(?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,(select nome from UNIDADEORGANICA where nome = ?),INITCAP(?),?)";

		 try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
             ps.setString(1, nomePessoa);
             ps.setString(2, nomeUtilizador);
             ps.setString(3, password);
             ps.setInt(4, Integer.parseInt(numeroTelefone));
             ps.setString(5, morada);
             ps.setString(6, dataValidadeDoCC);
             ps.setInt(7,Integer.parseInt(numeroCC));
             ps.setString(8, unidadeOrganica);
             ps.setString(9,funcaoPessoa);
             ps.setInt(10, Integer.parseInt(permissao));


             ps.executeUpdate();
             connectionToDB.commit();
             return true;
        }
        catch (SQLException e){
            System.out.println(e);
            return false;
        }

    }
	
	 public boolean manageDepFac(String argumentosOperacaoGeral,String argumentoNome, String argumentoNovoNome, String argumentoNovoNovoNome) throws FileNotFoundException, IOException{
		 System.out.println(argumentosOperacaoGeral + " : " + argumentoNome + " : " + argumentoNovoNome + " : " + argumentoNovoNovoNome);
		 boolean successoAlterarDados1 = false;
		 if(argumentosOperacaoGeral.equalsIgnoreCase("0")){
			 if (argumentoNome.equalsIgnoreCase("0")) {
				Connection connectionToDB = null;
				connectionToDB = connectToDB();
				String query = "INSERT into FACULDADE"
			         + " VALUES(NLS_UPPER(?),(select nome from UNIDADEORGANICA where nome = NLS_UPPER(?)))";

				try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
					ps.setString(1, argumentoNovoNome);
			        ps.setString(2, argumentoNovoNome);
			        ps.executeUpdate();
			        connectionToDB.commit();
			        return true;
			    }
			       catch (SQLException e){
			           System.out.println(e);
			           return false;
			    }
			}
			 else if (argumentoNome.equalsIgnoreCase("1")) {
				 Connection connectionToDB = null;
					connectionToDB = connectToDB();
					String query = "INSERT into DEPARTAMENTO"
				         + " VALUES(NLS_UPPER(?),(select nome from FACULDADE where nome = NLS_UPPER(?)),(select nome from UNIDADEORGANICA where nome = NLS_UPPER(?)))";

					try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
						ps.setString(1, argumentoNovoNovoNome);
				        ps.setString(2, argumentoNovoNome);
				        ps.setString(3, argumentoNovoNovoNome);
				        ps.executeUpdate();
				        connectionToDB.commit();
				        return true;
				    }
				       catch (SQLException e){
				           System.out.println(e);
				           return false;
				    }
			}
			 
			 else{
				 return false;
			 }
		 
		 
		 }
		 else if (argumentosOperacaoGeral.equalsIgnoreCase("1")) {
			if (argumentoNome.equalsIgnoreCase("0")) {
				Connection connectionToDB = null;
				connectionToDB = connectToDB();
				String query = "DELETE FROM FACULDADE WHERE NOME = NLS_UPPER(?)";
	            successoAlterarDados1=false;
	            try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
	                ps.setString(1, argumentoNovoNome);
	                String query2 = "DELETE FROM DEPARTAMENTO WHERE FACULDADENOME = NLS_UPPER(?)";
	                try(PreparedStatement ps2= connectionToDB.prepareStatement(query2)){
	                    ps2.setString(1,argumentoNovoNome);
	                    ps2.executeUpdate();
	                    connectionToDB.commit();
	                    successoAlterarDados1=true;
	                }
	                catch (SQLException e){
	                    System.out.println(e);
	                    return false;
	                }
	                if (successoAlterarDados1==true){/*Garante que se 1ª operacao tem de ser feita com sucesso para a segunda ocorrer*/
	                    ps.executeUpdate();
	                    connectionToDB.commit();
	                    return true;
	                }
	                else{
	                    return false;
	                }
	            }
	            catch (SQLException e){
	                System.out.println(e);
	                return false;
	            }	 
					
			}
			else if (argumentoNome.equalsIgnoreCase("1")) {
				Connection connectionToDB = null;
				connectionToDB = connectToDB();
				String query = "DELETE FROM DEPARTAMENTO WHERE NOME = NLS_UPPER(?)";
	            try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
	                ps.setString(1, argumentoNovoNome);
	                ps.executeUpdate();
	                connectionToDB.commit();
	                return true;

	            }
	            catch (SQLException e){
	                System.out.println(e);
	                return false;
	            }
					
			}
			else{
				return false;
			}
			 
		
			 
		}
		 else if (argumentosOperacaoGeral.equalsIgnoreCase("2")) {
			if (argumentoNome.equalsIgnoreCase("0")) {
				successoAlterarDados1=false;
				Connection connectionToDB = null;
				connectionToDB = connectToDB();
				String query = "UPDATE FACULDADE SET NOME = NLS_UPPER(?), UONOME = NLS_UPPER(?) WHERE NOME = NLS_UPPER(?)";
	            try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
	                ps.setString(1, argumentoNovoNovoNome);
	                ps.setString(2,argumentoNovoNovoNome);
	                ps.setString(3, argumentoNovoNome);
	                String query2 = "UPDATE DEPARTAMENTO SET FACULDADENOME = NLS_UPPER(?) WHERE FACULDADENOME = NLS_UPPER(?)";
	                try(PreparedStatement ps2= connectionToDB.prepareStatement(query2)){
	                    ps2.setString(1,argumentoNovoNovoNome);
	                    ps2.setString(2,argumentoNovoNome);
	                    ps2.executeUpdate();
	                    connectionToDB.commit();
	                    successoAlterarDados1=true;
	                }
	                catch (SQLException e){
	                    System.out.println(e);
	                    return false;
	                }
	                if (successoAlterarDados1==true){/*Garante que se 1ª operacao tem de ser feita com sucesso para a segunda ocorrer*/
	                    ps.executeUpdate();
	                    connectionToDB.commit();
	                    return true;
	                }
	                else{
	                    return false;
	                }

	            }
	            catch (SQLException e){
	                System.out.println(e);
	                try {
	                    connectionToDB.rollback();/*SE FALHAR A 2ª MODIFICACAO (A DAS FACULDADES- CANCELA A ALTERACAO DOS DEPARTAMENTOS)*/
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	                return false;
	            }
				
				
					 
					
			}
			else if (argumentoNome.equalsIgnoreCase("1")) {
				Connection connectionToDB = null;
				connectionToDB = connectToDB();
				String query = "UPDATE DEPARTAMENTO SET NOME = NLS_UPPER(?), UNIDADEORGANICANOME = (select nome from UNIDADEORGANICA where nome = NLS_UPPER(?)) " +
	                    "WHERE NOME = NLS_UPPER(?)";
	            System.out.println( argumentoNome +" " + argumentoNovoNome + " " + argumentoNovoNovoNome);
	            try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
	                ps.setString(1, argumentoNovoNovoNome);
	                ps.setString(2, argumentoNovoNovoNome);
	                ps.setString(3, argumentoNovoNome);
	                ps.executeUpdate();
	                connectionToDB.commit();
	            }
	            catch (SQLException e){
	                System.out.println(e);
	                return false;
	            }
				
					 
					
			}
			else{
				return false;
			}
			 
			 
			 
		}
		 return true;
	 }
	 
	 
	 public boolean createElection(String tipoEleicao, String tituloEleicao, String descricaoEleicao, String dataInicio, String dataFim, String unidadeorganica, String associacaoEstudantes) throws FileNotFoundException, IOException{
		 System.out.println(tipoEleicao +" : "+ " : "+tituloEleicao+" : "+descricaoEleicao+" : "+dataInicio+" : "+dataFim+" : "+unidadeorganica);
		 String [] arrayDateComponents = dataInicio.split("-");
		 System.out.println(arrayDateComponents);
		 
		 
		 Calendar calSistema = Calendar.getInstance();
		 int diaSistema = calSistema.get(Calendar.DAY_OF_MONTH);
	     int mesSistema = calSistema.get(Calendar.MONTH);
	     int anoSistema = calSistema.get(Calendar.YEAR);
	     int horaSistema = calSistema.get(Calendar.HOUR_OF_DAY);
	     int minutoSistema = calSistema.get(Calendar.MINUTE);
	     calSistema.set(anoSistema, mesSistema, diaSistema, horaSistema, minutoSistema);
		 
		 
		 
		 int diaInicio= Integer.parseInt(arrayDateComponents[0]);
		 int mesInicio= Integer.parseInt(arrayDateComponents[1]);
		 int anoInicio= Integer.parseInt(arrayDateComponents[2]);
		 int horaInicio=Integer.parseInt(arrayDateComponents[3]);
		 int minutoInicio=Integer.parseInt(arrayDateComponents[4]);
		 
		 
		 Calendar calInicio = Calendar.getInstance();
		 calInicio.set(anoInicio, mesInicio-1, diaInicio, horaInicio, minutoInicio);
			
	     
	     String [] arrayDateComponents2 = dataFim.split("-");
		 System.out.println(arrayDateComponents2);
		 int diaFim= Integer.parseInt(arrayDateComponents2[0]);
		 int mesFim= Integer.parseInt(arrayDateComponents2[1]);
		 int anoFim= Integer.parseInt(arrayDateComponents2[2]);
		 int horaFim=Integer.parseInt(arrayDateComponents2[3]);
		 int minutoFim=Integer.parseInt(arrayDateComponents2[4]);
		 
		 Calendar calFim = Calendar.getInstance();
		 calFim.set(anoFim, mesFim-1, diaFim, horaFim, minutoFim);
		 
		 
	     
	     
		 String dataInicioFormatoQuery=arrayDateComponents[2]+"-"+arrayDateComponents[1]+"-"+arrayDateComponents[0]+" "+arrayDateComponents[3]+":"+arrayDateComponents[4]+":"+"00";
		 String dataFimFormatoQuery=arrayDateComponents2[2]+"-"+arrayDateComponents2[1]+"-"+arrayDateComponents2[0]+" "+arrayDateComponents2[3]+":"+arrayDateComponents2[4]+":"+"00";
		 
		 System.out.println(dataInicioFormatoQuery);
		 System.out.println(dataFimFormatoQuery);
		 
		 
		 if (!comparaDatas2(calSistema, calInicio) || !comparaDatas2(calInicio, calFim)) {
			 return false;
			 
		}
		 else{
			 if (tipoEleicao.equalsIgnoreCase("Conselho Geral")) {
				 Connection connectionToDB = null;
				 connectionToDB = connectToDB();
				 String query ="INSERT into ELEICAO"
				 +" Values(?,?,TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS'),0, 0, 0, ?, seq_Eleicao.nextval, (select nome from UNIDADEORGANICA where nome = 'UC'))";
				 try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
		                ps.setString(1, tituloEleicao);
		                ps.setString(2, descricaoEleicao);
		                ps.setString(3, dataInicioFormatoQuery);
		                ps.setString(4, dataFimFormatoQuery);
		                ps.setString(5, tipoEleicao);
		                ps.executeUpdate();
		                connectionToDB.commit();
		                System.out.println("Fez commit");
		                //connectionToDB.close();
		                
		                Connection connectionToDB2 = null;
						connectionToDB2 = connectToDB();
						String query2="INSERT into BROWSERVOTO VALUES(seq_BrowserVoto.nextval)";
						try (PreparedStatement ps2= connectionToDB2.prepareStatement(query2)){
							ps2.executeQuery();
							connectionToDB.commit();
							
							Connection connectionToDB3 = null;
							connectionToDB3 = connectToDB();
							String query3="SELECT MAX(Greatest(ID)) from BROWSERVOTO";
							try (PreparedStatement ps3= connectionToDB3.prepareStatement(query3)){
								ResultSet rs= ps3.executeQuery();
								rs.next();
								int idBrowser=rs.getInt(1);
								
								Connection connectionToDB4 = null;
								connectionToDB4 = connectToDB();
								String query4 ="SELECT MAX(Greatest(ID)) from ELEICAO";
								try (PreparedStatement ps4= connectionToDB4.prepareStatement(query4)){
									ResultSet rs2= ps4.executeQuery();
									rs2.next();
									int idEleicao=rs2.getInt(1);
									
									
									Connection connectionToDB5 = null;
									connectionToDB5 = connectToDB();
									String query5="INSERT into BROWSERVOTO_ELEICAO VALUES(?,?)";
									try (PreparedStatement ps5= connectionToDB5.prepareStatement(query5)){
										System.out.println(idBrowser + " : "+ idEleicao);
										ps5.setInt(1, idBrowser);
										ps5.setInt(2, idEleicao);
										ps5.executeUpdate();
						                connectionToDB5.commit();
						                System.out.println("Fez commit 2");
										return true;
									}
									catch (SQLException e) {
										System.out.println(e);
										return false;
									}
								}
								catch (SQLException e) {
									System.out.println(e);
									return false;
								}
								
							}
							catch (SQLException e) {
								System.out.println(e);
								return false;
							}
							
						}
						catch (SQLException e) {
							System.out.println(e);
							return false;
						}
		                
		                

		            }
		            catch (SQLException e){
		                System.out.println(e);
		                return false;
		            }
			 }
			 else if (tipoEleicao.equalsIgnoreCase("Nucleo")) {
				 boolean encontrado=false;
				 Connection connectionToDB = null;
				 connectionToDB = connectToDB();
				 String searchQuery="SELECT COUNT(*) as CONTAGEM from NUCLEOESTUDANTES where NOMENUCLEO = ?"; 
				 try(PreparedStatement psQueryCount = connectionToDB.prepareStatement(searchQuery)){
					 psQueryCount.setString(1, associacaoEstudantes);
					 ResultSet rs = psQueryCount.executeQuery();
					 rs.next();
					 if (rs.getInt("CONTAGEM")==1) {
						 encontrado=true;
					}
					 else{
						 return false;
					 }
					 
					 
				 }
				 catch(SQLException e){
					 return false; 
				 }
				 
				 if (encontrado==true) {
					 //connectionToDB = connectToDB();//Not necessary
					 String query = "INSERT into ELEICAO"
		                        + " Values(?,?,TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS'),0, 0, 0, ?, seq_Eleicao.nextval, (select nome from UNIDADEORGANICA where nome=NLS_UPPER(?)))";
		                try (PreparedStatement ps = connectionToDB.prepareStatement(query)) {
		                    ps.setString(1, tituloEleicao);
		                    ps.setString(2, descricaoEleicao);
		                    ps.setString(3, dataInicioFormatoQuery);
		                    ps.setString(4, dataFimFormatoQuery);
		                    ps.setString(5, tipoEleicao);
		                    ps.setString(6, unidadeorganica);
		                    ps.executeUpdate();
		                    connectionToDB.commit();
		                    
		                    Connection connectionToDB2 = null;
							connectionToDB2 = connectToDB();
							String query2="INSERT into BROWSERVOTO VALUES(seq_BrowserVoto.nextval)";
							try (PreparedStatement ps2= connectionToDB2.prepareStatement(query2)){
								ps2.executeQuery();
								connectionToDB.commit();
								
								Connection connectionToDB3 = null;
								connectionToDB3 = connectToDB();
								String query3="SELECT MAX(Greatest(ID)) from BROWSERVOTO";
								try (PreparedStatement ps3= connectionToDB3.prepareStatement(query3)){
									ResultSet rs= ps3.executeQuery();
									rs.next();
									int idBrowser=rs.getInt(1);
									
									Connection connectionToDB4 = null;
									connectionToDB4 = connectToDB();
									String query4 ="SELECT MAX(Greatest(ID)) from ELEICAO";
									try (PreparedStatement ps4= connectionToDB4.prepareStatement(query4)){
										ResultSet rs2= ps4.executeQuery();
										rs2.next();
										int idEleicao=rs2.getInt(1);
										
										
										Connection connectionToDB5 = null;
										connectionToDB5 = connectToDB();
										String query5="INSERT into BROWSERVOTO_ELEICAO VALUES(?,?)";
										try (PreparedStatement ps5= connectionToDB5.prepareStatement(query5)){
											System.out.println(idBrowser + " : "+ idEleicao);
											ps5.setInt(1, idBrowser);
											ps5.setInt(2, idEleicao);
											ps5.executeUpdate();
							                connectionToDB5.commit();
							                System.out.println("Fez commit 2");
											return true;
										}
										catch (SQLException e) {
											System.out.println(e);
											return false;
										}
									}
									catch (SQLException e) {
										System.out.println(e);
										return false;
									}
									
								}
								catch (SQLException e) {
									System.out.println(e);
									return false;
								}
								
							}
							catch (SQLException e) {
								System.out.println(e);
								return false;
							}

		                } catch (SQLException e) {
		                    System.out.println(e);
		                    return false;
		                }
				 }else{
					 return false;
					 
				}
				
			 }
			 else{
				return false;
			 }
		 }	 
		 
	}
	 public boolean manageLists(String opcao, String eleicaoID, String nomeLista, String tipoLista) throws FileNotFoundException, IOException{
		 if (opcao.equals("0")) {
			 if (devolveTipoPorIDEleicao(eleicaoID).equalsIgnoreCase("Conselho Geral")) {
				 if (verificaFuncao(tipoLista)) {
					 Connection connectionToDB = null;
					 connectionToDB = connectToDB();
					 String query ="INSERT into LISTA"
						+" Values(0,INITCAP(?),?,INITCAP(?))";
						try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
				            ps.setString(1, nomeLista);
			                ps.setInt(2, Integer.parseInt(eleicaoID));
					        ps.setString(3, tipoLista);
					        ps.executeUpdate();
					        connectionToDB.commit();
					        System.out.println("Fez commit");
					        return true;

					     }
					     catch (SQLException e){
					    	 System.out.println(e);
					         return false;
					     }
					
				}
				 else{
					 return false;
				 }
				
			}
			 else if (devolveTipoPorIDEleicao(eleicaoID).equalsIgnoreCase("Nucleo")) {
				 if (tipoLista.equalsIgnoreCase("Aluno")) {
					 Connection connectionToDB = null;
					 connectionToDB = connectToDB();
					 String query ="INSERT into LISTA"
						+" Values(0,INITCAP(?),?,INITCAP(?))";
						try (PreparedStatement ps= connectionToDB.prepareStatement(query)){
				            ps.setString(1, nomeLista);
			                ps.setInt(2, Integer.parseInt(eleicaoID));
					        ps.setString(3, tipoLista);
					        ps.executeUpdate();
					        connectionToDB.commit();
					        System.out.println("Fez commit");
					        return true;

					     }
					     catch (SQLException e){
					    	 System.out.println(e);
					         return false;
					     }
					
				}
				 else{
					 return false;
				 }
				
			}
			 else{
				 return false;
			 }
			 
			
		 	}
		 //Deletes the references from the deleted list from the database
		 	else if (opcao.equals("1")) {
		 		Connection connectionToDB = null;
				connectionToDB = connectToDB();
				 
		         String query = "DELETE FROM LISTA WHERE NOMELISTA=?";
		         try (PreparedStatement ps= connectToDB().prepareStatement(query)){
		        	 ps.setString(1, nomeLista);
		                if (verificaSeExisteChavePessoaLista(nomeLista)>=1){
		                    apagaAssociacaoListaAPedido(nomeLista);

		                }
		                ps.executeUpdate();
		                connectionToDB.commit();
		                return true;
		        	 

				 }
				 catch (SQLException e){
				    System.out.println(e);
				    return false;
				 }
		
		}
		else{
			return false;	
		}
		 
	 }
	 
	 public boolean manageTables(String operacao, String idEleicao, String idMesa) throws FileNotFoundException, IOException{
		 if (operacao.equals("0")) {
			 if (devolveTipoPorIDEleicao(idEleicao).equalsIgnoreCase("Conselho Geral")){
				 try{
					 int intIdMesa = Integer.parseInt(idMesa);
					 int intIdEleicao = Integer.parseInt(idEleicao);
					 Connection connectionToDB = null;
					 connectionToDB = connectToDB();
						
					 String UOmesa=	devolveUnidadeOrganicaDeMesa(intIdMesa);																																			//String unidadeorganica da mesa//Id da mesa //id da mesa//id da eleicao
				     String query = "INSERT into MESA_VOTO_ELEICAO" +"VALUES((select UONome from MESA_VOTO where UONome = (select nome from UNIDADEORGANICA where nome = ?) and id = ?), (select id from MESA_VOTO where id = ?), (select id from ELEICAO where id = ?))";
				     try (PreparedStatement ps= connectToDB().prepareStatement(query)){
				    	 ps.setString(1, UOmesa);
				    	 ps.setInt(2, intIdMesa);
				         ps.setInt(3, intIdMesa);
				         ps.setInt(4, intIdEleicao);
				         ps.executeUpdate();
				         connectionToDB.commit();
				         return true;
				        	 

					}
				     catch (SQLException e){
				    	 System.out.println(e);
						 return false;
				     }
					 
				 }
				 catch(NumberFormatException e){
					 e.printStackTrace();
					 return false;
					 
				 } 
				
			}
			 else if (devolveTipoPorIDEleicao(idEleicao).equalsIgnoreCase("Nucleo")) {
				 try{
					 int intIdMesa = Integer.parseInt(idMesa);
					 int intIdEleicao = Integer.parseInt(idEleicao);
					 if (devolveUnidadeOrganicaDeEleicao(intIdEleicao).equalsIgnoreCase(devolveUnidadeOrganicaDeMesa(intIdMesa))) {
						 Connection connectionToDB = null;
						 connectionToDB = connectToDB();
							
						 String UOmesa=	devolveUnidadeOrganicaDeMesa(intIdMesa);	
					     String query = "INSERT into MESA_VOTO_ELEICAO" +"VALUES((select UONome from MESA_VOTO where UONome = (select nome from UNIDADEORGANICA where nome = ?) and id = ?), (select id from MESA_VOTO where id = ?), (select id from ELEICAO where id = ?))";
					     try (PreparedStatement ps= connectToDB().prepareStatement(query)){
					    	 ps.setString(1, UOmesa);
					    	 ps.setInt(2, intIdMesa);
					         ps.setInt(3, intIdMesa);
					         ps.setInt(4, intIdEleicao);
					         ps.executeUpdate();
					         connectionToDB.commit();
					         return true;
					        	 

						}
					     catch (SQLException e){
					    	 System.out.println(e);
							 return false;
					     }
						 
						
					}
					 else{
						 System.out.println("Can't associate table. Not the right department");
						 return false;
					 }
					 
				 }
				 catch(NumberFormatException e){
					 e.printStackTrace();
					 return false;
					 
				 }
				 
			}
			else{
				System.out.println("Should be impossible[there are only 2 types of elections");
				return false;
			}
			 
			
		}
		 else if(operacao.equals("1")){
			 try{
				 int intIdMesa = Integer.parseInt(idMesa);
				 int intIdEleicao = Integer.parseInt(idEleicao);
				 Connection connectionToDB = null;
				 connectionToDB = connectToDB();
					 
			     String query = "DELETE FROM MESA_VOTO_ELEICAO WHERE MESA_VOTOID = ? AND ELEICAOID = ?";
			     try (PreparedStatement ps= connectToDB().prepareStatement(query)){
			    	 ps.setInt(1, intIdMesa);
			         ps.setInt(2, intIdEleicao);
			         ps.executeUpdate();
			         connectionToDB.commit();
			         return true;
			        	 

				}
			     catch (SQLException e){
			    	 System.out.println(e);
					 return false;
			     }
				 
			 }
			 catch(NumberFormatException e){
				 e.printStackTrace();
				 return false;
				 
			 }
			 
		 }
		 else{
			 return false;
		 }
		 
	 }
	 
	 public String devolveDataInicio(int idEleicao) throws SQLException, FileNotFoundException, IOException{
		 Connection connectionToDB = null;
		 connectionToDB = connectToDB();
		 String query="Select DATAINICIO from ELEICAO where ID = ?";
		 try(PreparedStatement ps= connectionToDB.prepareStatement(query)){
			 ps.setInt(1, idEleicao);
			 ResultSet rs =ps.executeQuery();
			 rs.next();
			 String dataI=rs.getString(1);
			 return dataI;
			 
		 }
		 catch (SQLException e) {
			 return "";
			// TODO: handle exception
		}
		 
		 
	 }
	 
	 public String devolveDataFim(int idEleicao) throws SQLException, FileNotFoundException, IOException{
		 Connection connectionToDB = null;
		 connectionToDB = connectToDB();
		 String query="Select DATAIFIM from ELEICAO where ID = ?";
		 try(PreparedStatement ps= connectionToDB.prepareStatement(query)){
			 ps.setInt(1, idEleicao);
			 ResultSet rs =ps.executeQuery();
			 rs.next();
			 String dataI=rs.getString(1);
			 return dataI;
			 
		 }
		 catch (SQLException e) {
			 return "";
			// TODO: handle exception
		}
		 
		 
	 }
	 
	 public boolean editElection(String op, String arg1, String arg2, String id) throws FileNotFoundException, SQLException, IOException{
		 Calendar calSistema = Calendar.getInstance();
		 int diaSistema = calSistema.get(Calendar.DAY_OF_MONTH);
	     int mesSistema = calSistema.get(Calendar.MONTH);
	     int anoSistema = calSistema.get(Calendar.YEAR);
	     int horaSistema = calSistema.get(Calendar.HOUR_OF_DAY);
	     int minutoSistema = calSistema.get(Calendar.MINUTE);
	     calSistema.set(anoSistema, mesSistema, diaSistema, horaSistema, minutoSistema);
	     int elecId=0;
	     try{
	    	 elecId = Integer.parseInt(id);
	    	 String dataI= devolveDataInicio(elecId);
	    	 //'YYYY-MM-DD HH24:MI:SS'
	    	 String [] arrayData= dataI.split(" ");
	    	 String [] arrayDia = arrayData[0].split("-");
	    	 String [] arrayHora = arrayData[1].split(":");
	    	 int dia=Integer.parseInt(arrayDia[2]);
	    	 int mes=Integer.parseInt(arrayDia[1]);
	    	 int ano=Integer.parseInt(arrayDia[0]);
	    	 int hora=Integer.parseInt(arrayHora[0]);
	    	 int min=Integer.parseInt(arrayHora[1]);
	    	 Calendar eleicao=Calendar.getInstance();
	    	 eleicao.set(ano, mes, dia, hora, min);
	    	 if (!comparaDatas2(calSistema, eleicao)) {
	    		 return false;
			}
	    	 else{
	    		 if (op.equals("0")) {
	    			 Connection connectDB = null;
	    			 connectDB = connectToDB();
	    			 String query="UPDATE ELEICAO SET TITULO = ?, DESCRICAO = ? WHERE ID = ?";
	    			 try (PreparedStatement ps= connectToDB().prepareStatement(query)){
	                     ps.setString(1, arg1);
	                     ps.setString(2, arg2);
	                     ps.setInt(3,elecId);
	                     ps.executeUpdate();
	                     connectToDB().commit();
	                     return true;
	                 }
	                 catch (SQLException e){
	                     System.out.println(e);
	                     return false;
	                 }
	    			 
					 
					 
	 				
	 			}
	 			 else if(op.equals("1")){
	 				String [] dataIN = arg1.split("-");
					String [] dataFN = arg2.split("-");
	 				 try{
	 					 int ndi=Integer.parseInt(dataIN[0]);
	 					 int nmi=Integer.parseInt(dataIN[1]);
	 					 int nai=Integer.parseInt(dataIN[2]);
	 					 int nhi=Integer.parseInt(dataIN[3]);
	 					 int nmmi=Integer.parseInt(dataIN[4]);
	 					 
	 					int ndf=Integer.parseInt(dataFN[0]);
	 					 int nmf=Integer.parseInt(dataFN[1]);
	 					 int naf=Integer.parseInt(dataFN[2]);
	 					 int nhf=Integer.parseInt(dataFN[3]);
	 					 int nmmf=Integer.parseInt(dataFN[4]);
	 					 
	 					 Calendar novadataI=Calendar.getInstance();
	 					 novadataI.set(nai, nmi, ndi, nhi, nmmi);
	 					 
	 					 Calendar novadataF= Calendar.getInstance();
	 					 novadataF.set(naf, nmf, ndf, nhf, nmf);
	 					 
	 					 if (!comparaDatas2(novadataI, novadataF)) {
	 						 return false;
							
						}
	 					 else{
	 						 String novaDIT=dataIN[3]+"-"+dataIN[2]+"-"+dataIN[0]+" "+dataIN[3]+":"+dataIN[4]+":"+"00";
	 						String novaDIF=dataFN[3]+"-"+dataFN[2]+"-"+dataFN[0]+" "+dataFN[3]+":"+dataFN[4]+":"+"00";
	 						Connection connectDB = null;
	 		    			 connectDB = connectToDB();
	 		    			 String query="UPDATE ELEICAO SET DATAINICIO = TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS') , DATAFIM = TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS') WHERE ID = ?";
	 		    			 try (PreparedStatement ps= connectToDB().prepareStatement(query)){
	 		                     ps.setString(1, novaDIT);
	 		                     ps.setString(2, novaDIF);
	 		                     ps.setInt(3,elecId);
	 		                     ps.executeUpdate();
	 		                     connectToDB().commit();
	 		                     return true;
	 		                 }
	 		                 catch (SQLException e){
	 		                     System.out.println(e);
	 		                     return false;
	 		                 }
	 						 
	 					 }
	 					 
	 				 }
	 				 catch (NumberFormatException e) {
	 					 return false;
					}
	 				 
	 			 }
	 			 else{
	 				 return false;
	 			 }
	    		 
	    	 }
	     
			
	     }
	     catch (NumberFormatException e) {
	    	 return false;
			// TODO: handle exception
		}
	 }
		 
}
	
