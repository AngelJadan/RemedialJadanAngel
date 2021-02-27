package ec.ups.dao;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.modelo.Cliente;


@Stateless
public class ClienteDAO {
	
	@Inject
	private EntityManager em;
	
	public boolean insert(Cliente cliente) throws SQLException{
		em.persist(cliente);
		return true;
	}
	public Cliente buscar(String cedula, int cta)throws SQLException {
		Cliente cli = new Cliente();
		try {
			String sql = "SELECT c FROM Cliente c"
					+ " WHERE cedula=:ced OR cuenta=:cta";
			System.out.println(sql);
			cli = (Cliente)em.createQuery(sql, Cliente.class)
					.setParameter("ced", cedula)
					.setParameter("cta", cta).getSingleResult();
		} catch (Exception e) {
			cli=null;
		}
		return cli;	
	}
	public boolean update(Cliente cliente)throws SQLException{
		em.merge(cliente);
		return true;
	}
}
