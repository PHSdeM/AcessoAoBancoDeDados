package br.edu.ifms.lp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexao {
protected Connection getConnection() {

    Connection conexao = null;
    String userName = "root";
    String password = "";
    String url = "jdbc:mysql://localhost:3306/db_projeto";
    String driver = "com.mysql.cj.jdbc.Driver";
         


try {
		
 
 conexao = DriverManager.getConnection(url, userName, password);
 System.out.println("Conexão Estabelecida!");
 return conexao;
} catch (SQLException e) {

e.printStackTrace();
}
try {
conexao.close();
System.out.println("conexão encerrada!");
} catch (SQLException e) {

e.printStackTrace();
}
return conexao;
}

}