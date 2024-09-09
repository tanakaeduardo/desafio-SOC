package org.apache.struts.crud.service;

import java.util.List;

import org.apache.struts.crud.dao.ExameRealizadoDAO;
import org.apache.struts.crud.entity.Exame;
import org.apache.struts.crud.entity.ExameRealizado;
import org.apache.struts.crud.entity.Funcionario;
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
	
	public ExameRealizado buscarExameRealizado(Integer cd_exame, Integer cd_funcionario ) throws ConexaoFalhouException {
		ExameRealizado exameRealizado = new ExameRealizadoDAO().read(cd_exame, cd_funcionario);
		if(exameRealizado != null) {
			return exameRealizado;
		} else {
			throw new RegraDeNegocioException("Exame realizado não encontrado");
		}
	}
	
	public boolean alterarExameRealizado(ExameRealizado exameRealizado, Integer cd_exame) throws ConexaoFalhouException {
		return new ExameRealizadoDAO().update(exameRealizado, cd_exame);
	}
	
	public boolean apagarExameRealizado(Integer cd_exame, Integer cd_funcionario) throws Exception {
		return new ExameRealizadoDAO().delete(cd_exame, cd_funcionario);
	}
	
	public List<Funcionario> listarFuncionarios () throws ConexaoFalhouException{
		return new ExameRealizadoDAO().funcionarioList();
	}
	
	public List<Exame> listarExames () throws ConexaoFalhouException{
		return new ExameRealizadoDAO().exameList();
	}
}
