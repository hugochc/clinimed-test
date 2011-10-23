package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import mock.ConsultaMock;
import model.Paciente;

import org.junit.Test;

import util.GenericValidator;
import dao.PacienteDAO;
import dao.PacienteDAOImpl;

public class PacienteTest {

	@Test
	public void testPaciente() throws Exception {
		PacienteDAO dao = new PacienteDAOImpl();
		Paciente result;
		String[] listCPF = {"12707668648", "88278873429", "62978731605", "50475833767", "72743056975"};

		for(int i=0; i<5; i++) {
			Paciente p = new Paciente("p"+i, listCPF[i]);

			//Salvar			
			//Teste para verificar o tipo do objeto que sera persistido
			assertTrue(p instanceof Paciente);
			
			//Teste para verificar se os campos obrigatorios estao preenchidos
			assertTrue( p != null && p.getNomePac() != null && p.getCpfPac() != null);

			//Teste para validar o CPF do Paciente a ser persistido
			assertTrue(p.getCpfPac().length() == 11);
			assertTrue(GenericValidator.validaCpf(p.getCpfPac()));

			//Teste para verficar se um CPF ja existe no banco antes de persistir o paciente
			assertTrue(new PacienteDAOImpl().pesquisaPorCpf(p.getCpfPac()) == null);			

			result = dao.salvar(p);					
		}		
		/*==================================================================================*/
		//Pesquisar		
		//Obter a lista de paciente cadastrados
		List<Paciente> lstPac = dao.getLista();
		assertEquals(lstPac.size(), 5);

		//Testar a pesquisa por Nome do Paciente
		List<Paciente> listaNomes = dao.pesquisaPorNome("p3");
		assertEquals(listaNomes.get(0).getNomePac(), "p3");

		//Pesquisar Paciente usando um codigo invalido
		assertTrue(dao.pesquisaPorCodigo(50L) == null);

		//Testar se o paciente existe
		assertTrue(dao.pesquisaPorCodigo(1L) != null);
		Paciente pac = dao.pesquisaPorCodigo(1L);		
		/*==================================================================================*/
		//Alterar		
		//Alterando os dados do paciente recuperado no teste anterior
		pac.setNomePac("Maria");
		pac.setCelPac("1234567890");
		assertTrue(pac.getCelPac().equals("1234567890"));

		//Teste para validar novamente o CPF na alteração
		assertTrue(pac.getCpfPac().length() == 11);
		assertTrue(GenericValidator.validaCpf(pac.getCpfPac()));

		result = dao.salvar(pac);

		//Teste de alteracao com um CPF invalido
		result.setCpfPac("1234567890");
		assertTrue(GenericValidator.validaCpf(pac.getCpfPac()) == false);		
		result = dao.salvar(result);		
		/*==================================================================================*/
		//Excluir
		//Verificar se existe alguma consulta cadastrada para o paciente antes e excluí-lo
		assertEquals(0, new ConsultaMock().getConsultasPorCpf(result.getCpfPac()));
		assertTrue(new PacienteDAOImpl().excluir(result));		
	}
}
