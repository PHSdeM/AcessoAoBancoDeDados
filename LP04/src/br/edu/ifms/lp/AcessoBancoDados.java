package br.edu.ifms.lp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AcessoBancoDados {
    private Connection connection;
    private Conexao conexao = new Conexao();
    
    public void incluir(Produto pr) throws SQLException {
	   	   
	   String sql =   "INSERT INTO Produto (nome, valor)"+
		     "VALUES(?, ?)";
	   
	   System.out.println("Produto inserido com sucesso!");
			   
	   
	try {
		PreparedStatement s;
		s = conexao.getConnection().prepareStatement(sql);
		s.setString(1,  pr.getNome());
		s.setDouble(2,  pr.getValor());
		s.execute();
		int count = s.executeUpdate();
		System.out.println(count +
			     " Linhas foram inseridas");
		s.close();
	} catch (SQLException e1) {
		
		
		e1.printStackTrace();
	}
	 
	   
	 
	      
}
	
	
	public void excluir(int id) {
	Connection conn = null;
	 try {
	   conn = conexao.getConnection();	     
	   PreparedStatement s;
	   
	   s = conn.prepareStatement(
	     "DELETE FROM Produto WHERE id = ?");
	   s.setInt(1, id);

	   int count = s.executeUpdate();
	   s.close();

	   System.out.println(
	     count + " linhas foram excluidas");
	  
	 } catch (Exception e) {
	   throw new RuntimeException(
	     "Falha ao excluir linha.");

	 } finally {
	   if (conn != null) {
	     try {
	       conn.close();
	       System.out.println("Conexão encerrada.");
	     } catch (Exception e) {}
	   }
	 }
	}

	
	public void alterar(Produto pr) {
	 Connection conn = null;
	 try {
	   conn =conexao.getConnection();

	   PreparedStatement s;
	   s = conn.prepareStatement(
	     "UPDATE Produto SET nome = ?, valor = ? "+
	     "WHERE idProduto = ?");

	   s.setString(1, pr.getNome());
	   s.setDouble(2, pr.getValor());
	   s.setInt(3, pr.getId());

	   int count = s.executeUpdate();
	   s.close();
	   System.out.println(
	     count + " linhas atualizadas");

	 } catch (Exception e) {
	   throw new RuntimeException(
	     "Falha ao alterar");

	 } finally {
	   if (conn != null) {
	     try {
	       conn.close();
	       System.out.println(
	         "Conexão com o banco foi finalizada.");
	     } catch (Exception e) { }
	   }
	 }
	}

	
	public List pesquisar(String nome) {

	 ArrayList lista = new ArrayList();

	 Connection conn = null;
	 try {
	   conn = conexao.getConnection();

	   System.out.println("Conexao estabelecida!");
	   PreparedStatement s = conn.prepareStatement(
	     "SELECT * FROM Produto WHERE nome LIKE ?");
	   s.setString(1, "%"+ nome +"%");
	   s.executeQuery();

	  
	   ResultSet rs = s.getResultSet();

	  
	   int count = 0;
	   while (rs.next()) {
	     
		 Produto pr = new Produto();
	     pr.setId( rs.getInt("id") );
	     pr.setNome( rs.getString("nome"));
	     pr.setValor( rs.getDouble("valor"));

	     
	     lista.add(pr);

	     ++count;
	   }
	   rs.close();
	   s.close();

	   System.out.println(
	     count + " linhas");

	 } catch (Exception e) {
	   System.err.println("Falha ao conectar.");
	   e.printStackTrace();

	 } finally {
	   if (conn != null) {
	     try {
	       conn.close();
	       System.out.println("Conexão finalizada");
	     } catch (Exception e) {}
	   }
	 }

	 return lista;
	}
	


}