/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia_comDAO;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author lucas
 */
public class ProdutoDAO {
    
    //Método que resulta na criação de uma tupla no database
    public void cadastrar(Produto prod) throws ClassNotFoundException, SQLException {   
        Connection con = ConnectionFactory.getConexao();    //Inicia a conexão
        PreparedStatement command = con.prepareStatement("Insert into produto (descricao, preco) values (?,?)");    //Comando SQL
        command.setString(1, prod.getDescricao());  
        command.setDouble(2, prod.getPreco());
        command.execute();  //Executa o comando
        con.close();    //Fecha a conexão
    }
    
    //Método update da aplicação, atualiza a tupla com os dados fornecidos pelo usuário
    public void atualizar(Produto prod) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement command = con.prepareStatement("Update produto set descricao = ?, preco= ? where id = ?");    //Comando SQL
        command.setString(1, prod.getDescricao());
        command.setDouble(2, prod.getPreco());
        command.setInt(3, prod.getId());
        command.execute();
        con.close();
    }
    //Método delete da aplicação, excluí uma tupla através do id
    public void deletar(Produto prod) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement command = con.prepareStatement("Delete from produto where id = ?");
        command.setInt(1, prod.getId());
        command.execute();
        con.close();
    }
    //Consulta um item pelo id
    public Produto consultarById(Produto prod) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement command = con.prepareStatement("Select from produto where id = ?");
        command.setInt(1, prod.getId());
        ResultSet rs = command.executeQuery();
        
        //Cria um objeto produto
        Produto p = new Produto();
        if(rs.next()) {     //Verificador de dados utilizando ResultSet, pois algumas tuplas podem retornar vazias
            p.setId(rs.getInt("id"));
            p.setDescricao(rs.getString("descricao"));
            p.setPreco(rs.getDouble("preco"));
        }
        return p;
        
    }
    //Consultar todos os produtos utilizando a coleção List
    public List<Produto> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement command = con.prepareStatement("select * from produtos");
        ResultSet rs = command.executeQuery();
        
        List<Produto> lprod = new ArrayList(); //Cria o objeto lprod
        
        if (rs.next()) {        //Verificador de dados e looping
            Produto p = new Produto();
            p.setId(rs.getInt("id"));
            p.setDescricao(rs.getString("descricao"));
            p.setPreco(rs.getDouble("preco"));
            lprod.add(prod);
        }
        return lprod;
    }
}
