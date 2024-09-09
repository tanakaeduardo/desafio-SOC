package org.apache.struts.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.crud.entity.Exame;
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
		String sql = "SELECT * FROM exame_realizado AS er "
				+ "JOIN exame AS e "
				+ "ON e.cd_exame = er.cd_exame "
				+ "JOIN funcionario AS f "
				+ "ON f.cd_funcionario = er.cd_funcionario where 1 = 1 ";
		if(exameRealizado.getCd_funcionario() != null && exameRealizado.getCd_funcionario() != 0)sql += (" AND er.cd_funcionario = ? ");
		if(exameRealizado.getCd_exame() != null && exameRealizado.getCd_exame() != 0)sql += (" AND er.cd_exame = ? ");
	
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
				int i=1;
				if(exameRealizado.getCd_funcionario() != null && exameRealizado.getCd_funcionario() != 0) {
					ps.setInt(i, exameRealizado.getCd_funcionario() );
					i++;
				}
				if(exameRealizado.getCd_exame() != null && exameRealizado.getCd_exame() != 0) ps.setInt(i, exameRealizado.getCd_exame());
	
			    try (ResultSet rs = ps.executeQuery()){
				    while (rs.next()) {
				    	ExameRealizado exm = new ExameRealizado(rs.getInt("cd_exame"), rs.getInt("cd_funcionario"), rs.getDate("dt_realizacao"));
				    	exm.setExame(new Exame(rs.getInt("cd_exame"), rs.getString("nm_exame"), rs.getBoolean("ic_ativo"), rs.getString("ds_detalhe_exame"), rs.getString("ds_detalhe_exame1")));
				    	exm.setFuncionario(new Funcionario(rs.getInt("cd_funcionario"), rs.getString("nm_funcionario")));
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
		String sql = "SELECT * FROM exame_realizado er "
				+ "JOIN exame e "
				+ "ON e.cd_exame = er.cd_exame "
				+ "JOIN funcionario f "
				+ "ON f.cd_funcionario = er.cd_funcionario  WHERE er.cd_exame = ? AND er.cd_funcionario = ?";
	
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
			        exameRealizado.setExame(new Exame(rs.getInt("cd_exame"), rs.getString("nm_exame"), rs.getBoolean("ic_ativo"), rs.getString("ds_detalhe_exame"), rs.getString("ds_detalhe_exame1")));
			        exameRealizado.setFuncionario(new Funcionario(rs.getInt("cd_funcionario"), rs.getString("nm_funcionario")));

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
            boolean status = ps.executeUpdate()==1;
		    return status;
		} catch (SQLException e) {
            throw new ConexaoFalhouException(e);
		}
    }
    
	public boolean update(ExameRealizado exameRealizado, Integer cd_exame_novo) throws ConexaoFalhouException {
		String sql = "UPDATE exame_realizado SET cd_exame = ? WHERE cd_exame = ? AND cd_funcionario = ? ";
		
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
            ps.setInt(1, cd_exame_novo);
            ps.setInt(2, exameRealizado.getCd_exame());
            ps.setInt(3, exameRealizado.getCd_funcionario());
            boolean status = (ps.executeUpdate() == 1);
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
	            boolean status = ps.executeUpdate() == 1;
			    return status;    
		} catch (SQLException e) {
            throw new ConexaoFalhouException(e);
		}
    }
	
	public List<Funcionario> funcionarioList() throws ConexaoFalhouException{	
		List<Funcionario> list = new ArrayList<Funcionario>();
		list.add(new Funcionario(0,""));
		String sql = "SELECT DISTINCT f.* FROM exame_realizado er "
				+ "JOIN funcionario f "
				+ "ON f.cd_funcionario = er.cd_funcionario; "; 
	
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()
	        ) {
			    while (rs.next()) {
			    	Funcionario funcionario = new Funcionario(rs.getInt("cd_funcionario"), rs.getString("nm_funcionario"));
			    	list.add(funcionario);
			    }			    		    		    
		} catch (SQLException e) {
            throw new ConexaoFalhouException(e);
		}
		return list;
	}
	
	public List<Exame> exameList() throws ConexaoFalhouException{	
		List<Exame> list = new ArrayList<Exame>();
		list.add(new Exame(0,"",Boolean.TRUE,"",""));
		String sql = "SELECT DISTINCT e.* FROM exame_realizado er "
				+ "JOIN exame e "
				+ "ON e.cd_exame = er.cd_exame; "; 
	
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()
	        ) {
			    while (rs.next()) {
			    	Exame exame = new Exame(rs.getInt("cd_exame"), rs.getString("nm_exame")
			    			, rs.getBoolean("ic_ativo"), rs.getString("ds_detalhe_exame"), 
			                rs.getString("ds_detalhe_exame1"));
			    	list.add(exame);
			    }			    		    		    
		} catch (SQLException e) {
            throw new ConexaoFalhouException(e);
		}
		return list;
	}
}