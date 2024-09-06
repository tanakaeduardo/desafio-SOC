package org.apache.struts.crud.service;

import java.util.List;

import org.apache.struts.crud.dao.ExameRealizadoDAO;
import org.apache.struts.crud.entity.ExameRealizado;
import org.apache.struts.crud.util.ConexaoFalhouException;
import org.apache.struts.crud.util.RegraDeNegocioException;

/**
 * Models a Exam who registers.
 * 
 *
 * @author eduardo tanaka
 */

public class ExameRealizadoService {
		
	public ExameRealizadoService() {
	}
	
	public List<ExameRealizado> listarExameRealizado (ExameRealizado exameRealizado) throws ConexaoFalhouException{
		return new ExameRealizadoDAO().list(exameRealizado);
	}
	
	public boolean salvarExameRealizado(ExameRealizado exameRealizado) throws ConexaoFalhouException {
		 return new ExameRealizadoDAO().save(exameRealizado);
	}
	
	public ExameRealizado buscarExameRealizado(Integer cd_exameRealizado, String nm_exameRealizado ) throws ConexaoFalhouException {
		ExameRealizado exameRealizado = new ExameRealizadoDAO().read(cd_exameRealizado, nm_exameRealizado);
		if(exameRealizado != null) {
			return exameRealizado;
		} else {
			throw new RegraDeNegocioException("ExameRealizado não encontrado");
		}
	}
	
	public boolean alterarExameRealizado(Integer cd_exameRealizado, String nm_exameRealizado) throws ConexaoFalhouException {
		return new ExameRealizadoDAO().update(cd_exameRealizado, nm_exameRealizado);
	}
	
	public boolean apagarExameRealizado(Integer cd_exameRealizado) throws Exception {
		return new ExameRealizadoDAO().delete(cd_exameRealizado);
	}
	
}
