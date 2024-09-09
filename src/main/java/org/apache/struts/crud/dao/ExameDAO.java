package org.apache.struts.crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.crud.entity.Exame;
import org.apache.struts.crud.entity.Funcionario;
import org.apache.struts.crud.util.ConexaoFalhouException;
import org.apache.struts.crud.util.ConnectionFactory;


/**
 * Data access methods that a ExameDao class
 * must define to provide information about
 * a Person or collection of Exame objects.
 * 
 * @author eduardo tanaka
 */
public class ExameDAO {    
    
    public List<Exame> list(Exame exame) throws ConexaoFalhouException{
    	List<Exame> exames = new ArrayList<Exame>();
		String sql = "SELECT * FROM exame  where 1 = 1 ";
		if(exame.getCd_exame() != 0) sql += ("AND cd_exame = ? ");
		if(exame.getNm_exame() != null && !exame.getNm_exame().equals("")) sql += (" AND nm_exame like ('%?%') ");
		if(exame.getIc_ativo() != null) sql += (" AND ic_ativo = ? ");

		sql += ("  limit 10 ");
	
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
			int i=1;
			if(exame.getCd_exame() != 0) {
				ps.setInt(i, exame.getCd_exame());
				i++;
			}
			if(exame.getNm_exame() != null && !exame.getNm_exame().equals("")) {
				ps.setString(i, exame.getNm_exame());
				i++;
			}
			if(exame.getIc_ativo() != null) {
				ps.setBoolean(i, exame.getIc_ativo());
				i++;
			}

		    try (ResultSet rs = ps.executeQuery()){

			    while(rs.next()) {
			        int cd_exame = rs.getInt("cd_exame");
			        String nm_exame = rs.getString("nm_exame");
			        Boolean ic_ativo = rs.getBoolean("ic_ativo");
			        String ds_detalhe_exame = rs.getString("ds_detalhe_exame");
			        String ds_detalhe_exame1 = rs.getString("ds_detalhe_exame1");
			        exames.add(new Exame(cd_exame, nm_exame, ic_ativo, ds_detalhe_exame, 
			                ds_detalhe_exame1));
		    }
		}
	} catch (SQLException e) {
           throw new ConexaoFalhouException(e);
	}
		return exames;
    }
    
    public Exame read(int cd_exame) throws ConexaoFalhouException{
    	Exame exame = null;
			String sql = "SELECT * FROM exame  WHERE cd_exame = ?";
		
			try  (
		            Connection conn = ConnectionFactory.recuperarConexao();
					PreparedStatement ps = conn.prepareStatement(sql);
		        ) {
					ps.setInt(1, cd_exame);
					try (ResultSet rs = ps.executeQuery()){
				    
					    while(rs.next()) {
		 			        String nm_exame = rs.getString("nm_exame");
					        Boolean ic_ativo = rs.getBoolean("ic_ativo");
					        String ds_detalhe_exame = rs.getString("ds_detalhe_exame");
					        String ds_detalhe_exame1 = rs.getString("ds_detalhe_exame1");
					        exame = new Exame(cd_exame, nm_exame, ic_ativo, ds_detalhe_exame, 
					                ds_detalhe_exame1);
					    }
			    }
			} catch (SQLException e) {
		          throw new ConexaoFalhouException(e);
			}
		return exame;
    }
    
    public boolean save(Exame exame) throws ConexaoFalhouException {
    	int status;
    	String sql = "INSERT INTO exame (nm_exame, ic_ativo, ds_detalhe_exame, ds_detalhe_exame1) VALUES (?,?,?,?)";
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
            ps.setString(1, exame.getNm_exame());
            ps.setBoolean(2, exame.getIc_ativo());
            ps.setString(3, exame.getDs_detalhe_exame());
            ps.setString(4, exame.getDs_detalhe_exame1());
            status = ps.executeUpdate();

		} catch (SQLException e) {
	           throw new ConexaoFalhouException(e);
		}
		return status ==1 ;
    }
    
    public boolean update(Exame exame) throws ConexaoFalhouException {
    	int status;
		String sql = "UPDATE exame SET nm_exame = ? , ic_ativo = ? , ds_detalhe_exame = ? , ds_detalhe_exame1 = ? WHERE cd_exame = ? ";
		
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
            ps.setString(1, exame.getNm_exame());
            ps.setBoolean(2, exame.getIc_ativo());
            ps.setString(3, exame.getDs_detalhe_exame());
            ps.setString(4, exame.getDs_detalhe_exame1());
            ps.setInt(5, exame.getCd_exame());
            status = ps.executeUpdate();
	
		} catch (SQLException e) {
	           throw new ConexaoFalhouException(e);
		}
		return status ==1 ;
    }
    
    public boolean delete(Integer cd_exame) throws ConexaoFalhouException {
    	int status;
    	String sql = "DELETE FROM exame WHERE cd_exame = ? ";
		
		try  (
	            Connection conn = ConnectionFactory.recuperarConexao();
				PreparedStatement ps = conn.prepareStatement(sql);
	        ) {
            ps.setInt(1, cd_exame);
            status = ps.executeUpdate();
	
		} catch (SQLException e) {
	           throw new ConexaoFalhouException(e);
		}
		return status ==1 ;
    }
}
