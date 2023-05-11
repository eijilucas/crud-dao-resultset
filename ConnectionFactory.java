/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia_comDAO;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author lucas
 */
public class ConnectionFactory {
    //Define os atributos user, url e a senha
    protected static final String User = "postgres";
    protected static final String Password = "1234";
    protected static final String Url = "jdbc:postgresql://localhost:5432/Produto";
    
    //Cria a classe de conexão com o banco de dados através do driver JDBC, fazendo um link com o banco de dados e a aplicação.
    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver"); //Chama o driver
        Connection con  = DriverManager.getConnection(Url, User, Password); //Define os parâmetros de conexão
        return con; //Cria a conexão
    }
}
