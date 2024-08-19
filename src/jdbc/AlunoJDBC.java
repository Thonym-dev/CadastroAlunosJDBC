
package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {

	Connection con;
	String sql;
	Statement st;
	PreparedStatement pst;
	ResultSet rst;
	
	public void salvar(Aluno a) throws SQLException {
		
		try {
			con = DB.getConexao();
			sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?,?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			Date dataSql = Date.valueOf( a.getDt_nasc() );
			pst.setDate(3, dataSql);
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			pst.close();
			DB.endConexao();
		}
	}
	
	public List<Aluno> listar() throws SQLException{
		List<Aluno> lista = new ArrayList<Aluno>();
		
		try {
			con = DB.getConexao();
			sql = "SELECT * FROM aluno";
			st = con.createStatement();
			rst = st.executeQuery(sql);
			while (rst.next()) {
				Aluno a = new Aluno();
				a.setId(rst.getInt("id"));
				a.setNome(rst.getString("nome"));
				a.setSexo(rst.getString("sexo"));
				a.setDt_nasc(rst.getDate("dt_nasc").toLocalDate());
				lista.add(a);	
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			st.close();
			rst.close();
			DB.endConexao();
		}
		
		return lista;	
	}
	
	public void apagar(int id) throws SQLException {
		try {
			con = DB.getConexao();
			sql = "DELETE FROM aluno WHERE id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			int linhas = pst.executeUpdate();
			System.out.println("### ALUNO EXCLUIDO COM SUCESSO! ###");
			System.out.println("*** FORAM APAGADAS " + linhas + " LINHA(S) ***");
			
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			pst.close();
			DB.endConexao();
		}
		
	}
	
	public void alterar(Aluno a) throws SQLException {
		try {
			con = DB.getConexao();
			sql = "UPDATE aluno SET nome=?, sexo=?, dt_nasc =? WHERE id = ?";
			pst = con.prepareStatement(sql);
			
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			Date date = Date.valueOf(a.getDt_nasc());
			pst.setDate(3, date);
			pst.setInt(4, a.getId());
			
			System.out.println(a.getNome()+" "+a.getId()+" "+date);
			int linhas = pst.executeUpdate();
			System.out.println("*** FORAM ALTERADAS " + linhas + " LINHA(S) ***");
			System.out.println("### ALUNO ALTERADO COM SUCESSO! ###");
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			pst.close();
			DB.endConexao();
		}
		
		
	}
}
