package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import model.Paciente;

import org.junit.Test;

import util.GenericValidator;
import dao.PacienteDAO;
import dao.PacienteDAOImpl;

public class PersistenceTest {

	@Test
	public void testPersistence() throws Exception {
		PacienteDAO dao = new PacienteDAOImpl();
		Paciente result;
		String[] listCPF = {"12707668648", "15418066897", "62978731605", "50475833767", "72743056975"};
		
		for(int i=0; i<5; i++) {
			Paciente p = new Paciente("p"+i, listCPF[i]);
			
			//Salvar
			
			//Teste para verificar o tipo do objeto que sera persistido
			assertTrue(p instanceof Paciente);
					
			//Teste para verificar se os campos obrigatorios estao preenchidos
			assertTrue(p.getNomePac() != null && p.getCpfPac() != null);
						
			//Teste para validar o CPF do Paciente a ser persistido
			assertTrue(p.getCpfPac().length() == 11);
			assertTrue(GenericValidator.validaCpfCnpj(p.getCpfPac()));
						
			//Teste para verficar se um CPF j� existe no banco antes de persistir o paciente
			assertTrue(new PacienteDAOImpl().pesquisaPorCpf(p.getCpfPac()) == null);			
			
			result = dao.salvar(p);			
			System.out.println("Paciente Id: "+result.getCodPac());						
		}
		
		/*==================================================================================*/
		//Pesquisar
		
		//Obter a lista de paciente cadastrados
		List<Paciente> lstPac = dao.getLista();
		assertEquals(lstPac.size(), 5);
		
		//Testar a pesquisa por Nome do Paciente
		List<Paciente> listaNomes = dao.pesquisaPorNome("p3");
		assertEquals(listaNomes.get(0).getNomePac(), "p3");
				
		//Testar se o paciente existe
		assertTrue(new PacienteDAOImpl().pesquisaPorCodigo(1L) != null);
		Paciente pac = dao.pesquisaPorCodigo(1L);
		
		
		/*==================================================================================*/
		//Alterar
		
		//Alterando os dados do paciente recuperado no teste anterior
		pac.setNomePac("Maria");
		pac.setCelPac("1234567890");
		
		//Teste para validar novamente o CPF na alteração
		assertTrue(pac.getCpfPac().length() == 11);
		assertTrue(GenericValidator.validaCpfCnpj(pac.getCpfPac()));
		
		result = dao.salvar(pac);
		System.out.println("Nome alterado: "+result.getNomePac());
		
		
		/*==================================================================================*/
		//Excluir
		
	}
	
}
