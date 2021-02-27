package ec.ups.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.modelo.Categoria;
import ec.ups.edu.modelo.Libro;


@Stateless
public class AutorDAO {
	
	@Inject
	private EntityManager em;

	public List<Categoria> consultar()throws SQLException {
		List<Categoria> categorias = new ArrayList<Categoria>();
		try {
			String sql = "SELECT c FROM Categoria c";			
			categorias = em.createQuery(sql, Categoria.class).getResultList();
		} catch (Exception e) {
			categorias=null;
		}
		return categorias;	
	}
}
