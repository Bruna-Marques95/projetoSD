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
	
	//Comentar este método e substituir por outro que aceda a base de dados
	/**
	 * returns true if and only if user matches password
	 */
	/*public boolean userMatchesPassword(String user, String password) throws RemoteException {
		System.out.println("Looking up " + user + "...");
		return users.containsKey(user) && users.get(user).equals(password);
	}*/
	
	/**
	 * returns true if and only if user matches password
	 */
	public boolean userMatchesPassword(String user, String password) throws RemoteException{
        String databaseAddress;
        try {
            databaseAddress = leFicheiroComEnderecoIP(nomeFicheiroComEnderecoIP);
            String firstPartOfDatabaseAddress ="jdbc:oracle:thin:@";
            String lastPartOfDatabaseAddress =":1521:xe";
            Connection connection = null;
            
            try {
                connection = DriverManager.getConnection(firstPartOfDatabaseAddress+databaseAddress+lastPartOfDatabaseAddress,
                        "utilizadorBD",
                        "utilizadorBD");
            } catch (SQLException e) {
                System.out.println("A ligação falhou");
                e.printStackTrace();
                return false;
            }
           
            if (connection != null) {
                System.out.println("Ligação feita com sucessso");
            } else {
                System.out.println("Nao conseguimos estabelecer a ligacao");
            }

                //(NOME, NOMEUTILIZADOR,PASSWORD,NUMTELEFONE,MORADA, VALIDADECC,NUMCC,UNIDADEORGANICANOME,TIPO)
                String query = "Select count(*) from PESSOA where NomeUtilizador = ? and Password = ?";


                try (PreparedStatement ps= connection.prepareStatement(query)){
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
            
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }
		
	}
	
	/**
	 * returns all the user names
	 */
	public ArrayList<String> getAllUsers() {
		System.out.println("Looking up all users...");
		return new ArrayList<String>(users.keySet());	
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		RMIServerInterface s = new RMIServer();
		LocateRegistry.createRegistry(1099).rebind("server", s);
		System.out.println("Server ready...");
		String databaseAddress = leFicheiroComEnderecoIP(nomeFicheiroComEnderecoIP);
        String firstPartOfDatabaseAddress ="jdbc:oracle:thin:@";
        String lastPartOfDatabaseAddress =":1521:xe";
		
		
		try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

        }catch(ClassNotFoundException e){
            System.out.println("O driver não foi encontrado, verificar se este existe.");
            e.printStackTrace();

        }
        System.out.println("O JDBC Driver funciona ... a tentar efectuar uma ligacao");
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(firstPartOfDatabaseAddress+databaseAddress+lastPartOfDatabaseAddress,
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("A ligação falhou");
            e.printStackTrace();
            return;
        }

        if (conexao!=null){
            System.out.println("A ligação à base de dados foi efectuada com sucesso");
        }
        else{
            System.out.println("Não foi possível estabelecer ligação com a base de dados");
        }

        try{
            Statement stmt;
            if (conexao.createStatement() == null){
                conexao = DriverManager.getConnection(firstPartOfDatabaseAddress+databaseAddress+lastPartOfDatabaseAddress,
                        "utilizadorBD",
                        "utilizadorBD");
            }
            if ((stmt = conexao.createStatement()) == null){
                System.out.println("Não foi possível criar um statement");
                System.exit(-1);
            }

            String query = "SELECT * FROM PESSOA";
            System.out.println("Processando a query: " + query);
            ResultSet resultado = stmt.executeQuery(query);

            ResultSetMetaData rsmd = resultado.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            for(int i = 1 ; i <= columnsNumber ; i++){
                System.out.print(rsmd.getColumnName(i) +", ");
            }
            System.out.println("");//Quando chegamos ao fim da linha com os cabeçalhos da tabela, saltamos uma linha

            while (resultado.next()) {
                // Listar o resultado da query
                // List the result from the query
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultado.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println("");//Permite mostrar as linhas da tabela corretamente - Separa as linhas da tabela
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
		
	}
	public static String leFicheiroComEnderecoIP(String nomeFicheiro) throws FileNotFoundException,IOException {
		BufferedReader fich = new BufferedReader(new FileReader(nomeFicheiro));

        //Lê o ficheiro que contem o endereco IP na rede(Necessario alterar antes de correr o programa)
        String EnderecoIpNaRede= fich.readLine();
        
        return EnderecoIpNaRede;
		
		
		
	}
}
