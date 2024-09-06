package org.apache.struts.crud.service;

import java.util.List;

import org.apache.struts.crud.dao.FuncionarioDAO;
import org.apache.struts.crud.entity.Funcionario;
import org.apache.struts.crud.util.ConexaoFalhouException;
import org.apache.struts.crud.util.RegraDeNegocioException;

/**
 * Models a Exam who registers.
 * 
 *
 * @author eduardo tanaka
 */

public class FuncionarioService {
		
	public FuncionarioService() {
	}
	
	public List<Funcionario> listarFuncionario (Funcionario funcionario) throws ConexaoFalhouException{
		return new FuncionarioDAO().list(funcionario);
	}
	
	public boolean salvarFuncionario(Funcionario funcionario) throws ConexaoFalhouException {
		 return new FuncionarioDAO().save(funcionario);
	}
	
	public Funcionario buscarFuncionario(Integer cd_funcionario) throws ConexaoFalhouException {
		Funcionario funcionario = new FuncionarioDAO().read(cd_funcionario);
		if(funcionario != null) {
			return funcionario;
		} else {
			throw new RegraDeNegocioException("Funcionario não encontrado");
		}
	}
	
	public boolean alterarFuncionario(Integer cd_funcionario, String nm_funcionario) throws ConexaoFalhouException {
		return new FuncionarioDAO().update(cd_funcionario, nm_funcionario);
	}
	
	public boolean apagarFuncionario(Integer cd_funcionario) throws Exception {
		return new FuncionarioDAO().delete(cd_funcionario);
	}
	
}
