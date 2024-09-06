package org.apache.struts.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.crud.entity.ExameRealizado;
import org.apache.struts.crud.entity.Funcionario;
import org.apache.struts.crud.util.ConexaoFalhouException;
import org.apache.struts.crud.util.ConnectionFactory;

/**
 * Define methods a ExameRealizadoDao must implement
 * to provide services related to a ExameRealizado class.
 * 
 * @author eduardo tanaka
 */
public class ExameRealizadoDAO {
    
	public List<ExameRealizado> list(ExameRealizado exameRealizado) throws ConexaoFalhouException{	
		List<ExameRealizado> list = new ArrayList<ExameRealizado>();
		String sql = "SELECT * FROM funcionario where 1 = 1 ";
		if(exameRealizado.getCd_funcionario() != null)sql += (" AND cd_funcionario = ? ");
		if(exameRealizado.getCd_exame() != null)sql += (" AND nm_funcionario = ? ");
	
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
				int i=1;
				if(exameRealizado.getCd_funcionario() != 1) {
					ps.setInt(i, exameRealizado.getCd_funcionario());
					i++;
				}
				if(exameRealizado.getCd_exame() != null) ps.setInt(i, exameRealizado.getCd_exame());
	
			    try (ResultSet rs = ps.executeQuery()){
				    while (rs.next()) {
				    	ExameRealizado exm = new ExameRealizado(rs.getInt(1), rs.getInt(2), rs.getDate(3));
				    	list.add(exm);
				    }
			    }			    		    		    
		} catch (SQLException e) {
            throw new ConexaoFalhouException(e);
		}
		return list;
	}
    
	public ExameRealizado read(Integer cd_exame, Integer cd_funcionario) throws ConexaoFalhouException{
		ExameRealizado exameRealizado = null;
		String sql = "SELECT * FROM exame_realizado  WHERE cd_exame = ? AND cd_funcionario = ?";
	
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
			ps.setInt(1, cd_exame);
			ps.setInt(2, cd_funcionario);

		    try (ResultSet rs = ps.executeQuery()){ 
			    while(rs.next()) {
			        Date dt_realizacao = rs.getDate("dt_realizacao");
			        exameRealizado = new ExameRealizado(cd_exame, cd_funcionario, dt_realizacao);
		    }
		}
		} catch (SQLException e) {
		throw new ConexaoFalhouException(e);
		}
		return exameRealizado;
    }
    
	public boolean save(ExameRealizado exameRealizado) throws ConexaoFalhouException {
		String sql = "INSERT INTO exame_realizado (cd_exame, cd_funcionario, dt_realizacao) VALUES (?, ?, ?)";
		
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
            ps.setInt(1, exameRealizado.getCd_exame());
            ps.setInt(2, exameRealizado.getCd_funcionario());
            ps.setDate(3, exameRealizado.getDt_realizacao());  
            boolean status = ps.execute();
		    return status;
		} catch (SQLException e) {
            throw new ConexaoFalhouException(e);
		}
    }
    
	public boolean update(ExameRealizado exameRealizado, int cd_exame_novo) throws ConexaoFalhouException {
		String sql = "UPDATE exame_realizado SET cd_exame = ? , dt_realizacao = ?, cd_funcionario = ? WHERE cd_exame = ? ";
		
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
            ps.setInt(1, exameRealizado.getCd_exame());
            ps.setDate(2, exameRealizado.getDt_realizacao());
            ps.setInt(3, exameRealizado.getCd_funcionario());
            ps.setInt(4, cd_exame_novo);
            boolean status = ps.execute();
		    return status;
		} catch (SQLException e) {
            throw new ConexaoFalhouException(e);
		}
    }
    
	public boolean delete(int cd_exame, int cd_funcionario) throws ConexaoFalhouException {
		String sql = "DELETE FROM exame_realizado WHERE cd_funcionario = ? AND cd_exame = ?";
		
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
	           ps.setInt(1, cd_funcionario);
	            ps.setInt(2, cd_exame);
	            boolean status = ps.execute();
			    return status;    
		} catch (SQLException e) {
            throw new ConexaoFalhouException(e);
		}
    }
}