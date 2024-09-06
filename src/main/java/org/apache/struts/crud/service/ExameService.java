package org.apache.struts.crud.service;

import java.util.List;

import org.apache.struts.crud.dao.ExameDAO;
import org.apache.struts.crud.entity.Exame;
import org.apache.struts.crud.util.ConexaoFalhouException;
import org.apache.struts.crud.util.RegraDeNegocioException;

/**
 * Models a Exam who registers.
 * 
 *
 * @author eduardo tanaka
 */

public class ExameService {
		
	public ExameService() {
	}
	
	public List<Exame> listarExame (Exame exame) throws ConexaoFalhouException{
		return new ExameDAO().list(exame);
	}
	
	public boolean salvarExame(Exame exame) throws ConexaoFalhouException {
		 return new ExameDAO().save(exame);
	}
	
	public Exame buscarExame(Integer cd_exame) throws ConexaoFalhouException {
		Exame exame = new ExameDAO().read(cd_exame);
		if(exame != null) {
			return exame;
		} else {
			throw new RegraDeNegocioException("Exame não encontrado");
		}
	}
	
	public boolean alterarExame(Exame exame) throws ConexaoFalhouException {
		return new ExameDAO().update(exame);
	}
	
	public boolean apagarExame(Integer cd_exame) throws Exception {
		return new ExameDAO().delete(cd_exame);
	}
	
}
