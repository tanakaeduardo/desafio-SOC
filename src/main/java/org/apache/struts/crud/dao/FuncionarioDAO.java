package org.apache.struts.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.crud.entity.Funcionario;
import org.apache.struts.crud.util.ConexaoFalhouException;
import org.apache.struts.crud.util.ConnectionFactory;

/**
 * Define methods a PersonService must implement
 * to provide services related to a Funcionario class.
 * 
 * @author eduardo tanaka
 */
public class FuncionarioDAO {
	
	public FuncionarioDAO() {
	}
	
	public List<Funcionario> list(Funcionario funcionario) throws ConexaoFalhouException{
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM funcionario where 1 = 1  ";
		if(funcionario.getCd_funcionario() != 0)sql += ("AND cd_funcionario = ? ");
		if(funcionario.getNm_funcionario() != null)sql += (" AND nm_funcionario like ('%?%') ");
	
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
				int i=1;
				if(funcionario.getCd_funcionario() != 1) {
					ps.setInt(i, funcionario.getCd_funcionario());
					i++;
				}
				if(funcionario.getNm_funcionario() != null) ps.setString(i, funcionario.getNm_funcionario());
	
			    try (ResultSet rs = ps.executeQuery()){
				    while (rs.next()) {
				    	Funcionario func = new Funcionario(rs.getInt(1), rs.getString(2));
				    	funcionarios.add(func);
				    }
			    }			    		    		    
		} catch (SQLException e) {
            throw new ConexaoFalhouException(e);
		}
		return funcionarios;
	}
	
	
	public Funcionario read(Integer cd_funcionario) throws ConexaoFalhouException{
		Funcionario funcionario = new Funcionario();
		String sql = "SELECT * FROM funcionario where cd_funcionario = ? ";
				
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
			ps.setInt(1, funcionario.getCd_funcionario());
		    try (ResultSet rs = ps.executeQuery()){ 
			    while (rs.next()) {
			    	funcionario = new Funcionario(rs.getInt(1), rs.getString(2));
			    }
		    }
		} catch (SQLException e) {
        	throw new ConexaoFalhouException(e);
		}
		return funcionario;
	}
	
	public boolean save(Funcionario funcionario) throws ConexaoFalhouException {
        String sql = "INSERT INTO funcionario (cd_funcionario, nm_funcionario)" +
                "VALUES (?, ?)";

		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {

            ps.setInt(1, funcionario.getCd_funcionario());
            ps.setString(2, funcionario.getNm_funcionario());
            boolean status = ps.execute();
		    return status;
        } catch (SQLException e) {
            throw new ConexaoFalhouException(e);
        }
	}
	
	public boolean update(Integer cd_funcionario, String nm_funcionario) throws ConexaoFalhouException {
        String sql = "UPDATE funcionario SET nm_funcionario = ? where cd_funcionario = ?";

		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
            ps.setString(1, nm_funcionario);
            ps.setInt(2, cd_funcionario);
            boolean status = ps.execute();

		    return status;
        } catch (SQLException e) {
            throw new ConexaoFalhouException(e);
        }
	}
	
	public boolean delete(Integer cd_funcionario) throws ConexaoFalhouException {
        String sql = "DELETE FROM funcionario WHERE cd_funcionario = ? ";
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
            ps.setInt(1, cd_funcionario);
            boolean status = ps.execute();
            
		    return status;
        } catch (SQLException e) {
            throw new ConexaoFalhouException(e);
        }
	}

}