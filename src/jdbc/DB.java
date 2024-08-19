package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	private static Connection conexao;
	
	public static Properties getPropriedades() throws IOException {
		Properties propriedades = new Properties();
		FileInputStream file = new FileInputStream("./db.properties");
		propriedades.load(file);
		return propriedades;
	}
	
	public static Connection getConexao() throws IOException, SQLException {
		Properties p = DB.getPropriedades();
		
		conexao = DriverManager.getConnection(
				p.getProperty("host"),
				p.getProperty("user"),
				p.getProperty("password")
				);
		
		return conexao;
	}
	
	public static void endConexao() throws SQLException {
		if (conexao != null) {
			conexao.close();
		}
		
	}
}
