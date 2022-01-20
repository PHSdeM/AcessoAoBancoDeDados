package br.edu.ifms.lp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Main {
	
	 public static void main(String[] args) throws SQLException {
	        
			Connection connection = new Conexao().getConnection();
   System.out.println("Conexao aberta!!!");
   try {
		connection.close();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
  
   
   AcessoBancoDados acessoBD = new AcessoBancoDados();
   
   acessoBD.pesquisar("Arroz");
   
  
 }

}

