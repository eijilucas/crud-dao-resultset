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
    
    public void cadastrar(Produto prod) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement command = con.prepareStatement("Insert into produto (descricao, preco) values (?,?)");
        command.setString(1, prod.getDescricao());
        command.setDouble(2, prod.getPreco());
        command.execute();
        con.close();
    }
    
    public void atualizar(Produto prod) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement command = con.prepareStatement("Update produto set descricao = ?, preco= ? where id = ?");
        command.setString(1, prod.getDescricao());
        command.setDouble(2, prod.getPreco());
        command.setInt(3, prod.getId());
        command.execute();
        con.close();
    }
    
    public void deletar(Produto prod) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement command = con.prepareStatement("Delete from produto where id = ?");
        command.setInt(1, prod.getId());
        command.execute();
        con.close();
    }
    
    public Produto consultarById(Produto prod) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement command = con.prepareStatement("Select from produto where id = ?");
        command.setInt(1, prod.getId());
        ResultSet rs = command.executeQuery();
        
        Produto p = new Produto();
        if(rs.next()) {
            p.setId(rs.getInt("id"));
            p.setDescricao(rs.getString("descricao"));
            p.setPreco(rs.getDouble("preco"));
        }
        return p;
        
    }
    
    public List<Produto> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement command = con.prepareStatement("select * from produtos");
        ResultSet rs = command.executeQuery();
        
        List<Produto> lprod = new ArrayList<Produto>();
        
        if (rs.next()) {
            Produto p = new Produto();
            p.setId(rs.getInt("id"));
            p.setDescricao(rs.getString("descricao"));
            p.setPreco(rs.getDouble("preco"));
            lprod = (List<Produto>) p;
        }
        return lprod;
    }
}
