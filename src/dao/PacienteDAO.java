package dao;

import java.util.List;

import model.Paciente;

public interface PacienteDAO {

	Paciente salvar(Paciente paciente);
	boolean excluir(Paciente paciente);
	Paciente pesquisaPorCodigo(long codigo);
	Paciente pesquisaPorCpf(String cpf);
	List<Paciente> pesquisaPorNome(String nome);
	List<Paciente> getLista();
	
}
