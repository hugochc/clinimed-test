package dao;

import java.util.List;

import model.Paciente;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class PacienteDAOImpl implements PacienteDAO {

	@Override
	public Paciente salvar(Paciente paciente) {
		Session sessao = null;
		Transaction transacao = null;
		
		try {
			sessao = HibernateUtil.getSession();
			transacao = sessao.beginTransaction();
			
			if(paciente.getCodPac() != null) {
				sessao.update(paciente);
			} else {
				sessao.save(paciente);
			}
			transacao.commit();
			sessao.close();
			return paciente;
			
		} catch (Exception e) {
			transacao.rollback();
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public boolean excluir(Paciente paciente) {
		Session sessao = null;
		Transaction transacao = null;
		
		try {
			sessao = HibernateUtil.getSession();
			transacao = sessao.beginTransaction();
			
			sessao.delete(paciente);
			
			transacao.commit();
			sessao.close();
			return true;
			
		} catch (Exception e) {
			transacao.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Paciente pesquisaPorCodigo(long codigo) {
		Session sessao = null;
		Transaction transacao = null;
		Paciente paciente = null;
		
		try {
			sessao = HibernateUtil.getSession();
			transacao = sessao.beginTransaction();
			
			Criteria criteria = sessao.createCriteria(Paciente.class);
			criteria.add(Restrictions.eq("codPac", codigo));
			paciente = (Paciente) criteria.uniqueResult();
			transacao.commit();
			sessao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return paciente;
	}

	@Override
	public Paciente pesquisaPorCpf(String cpf) {
		Session sessao = null;
		Transaction transacao = null;
		Paciente paciente = null;
		
		try {
			sessao = HibernateUtil.getSession();
			transacao = sessao.beginTransaction();
			
			Criteria criteria = sessao.createCriteria(Paciente.class);
			criteria.add(Restrictions.eq("cpfPac", cpf));
			paciente = (Paciente) criteria.uniqueResult();
			transacao.commit();
			sessao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return paciente;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> pesquisaPorNome(String nome) {
		Session sessao = null;
		Transaction transacao = null;
		List<Paciente> pacientesList = null;
		
		try {
			sessao = HibernateUtil.getSession();
			transacao = sessao.beginTransaction();
			
			Criteria criteria = sessao.createCriteria(Paciente.class);
			criteria.add(Restrictions.like("nomePac", "%"+nome+"%"));
			criteria.addOrder(Order.asc("nomePac"));
			pacientesList = criteria.list();
			transacao.commit();
			sessao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pacientesList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> getLista() {
		Session sessao = null;
		Transaction transacao = null;
		List<Paciente> pacientesList = null;
		
		try {
			sessao = HibernateUtil.getSession();
			transacao = sessao.beginTransaction();
			
			Criteria criteria = sessao.createCriteria(Paciente.class);
			criteria.addOrder(Order.asc("codPac"));
			pacientesList = criteria.list();
			transacao.commit();
			sessao.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pacientesList;
	}

}
