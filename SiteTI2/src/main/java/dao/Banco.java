package dao;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;


public class Banco {
    
    protected Connection connection;
    
    public Banco() {
        connection = null;
    }
    
    @SuppressWarnings("unused")
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        return DriverManager.getConnection(dbUrl, username, password);
    }
    
    /**
     * Realiza a conexao com o postgresql
     * @throws URISyntaxException 
     * 
     */
    public boolean connect() throws URISyntaxException {
//        Configuração para conexão local
    
        String driverName = "org.postgresql.Driver";                    
        String serverName = "localhost";
        String mydatabase = "YAGI";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
        String username = "yagi";
        String password = "yagi123";
        boolean status = false;

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
            status = (connection == null);
            System.out.println("Conexão efetuada com o postgres!");
        } catch (ClassNotFoundException e) { 
            System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return status;
    }
    
    /**
     * Fecha conexao com banco de dados
     * 
     */
    public boolean close() {
        boolean status = false;
        
        try {
            connection.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return status;
    }
}