package ec.ups.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.modelo.Autor;
import ec.ups.edu.modelo.Categoria;
import ec.ups.edu.modelo.Libro;


@Stateless
public class AutorDAO {
	
	@Inject
	private EntityManager em;

	public List<Autor> consultar()throws SQLException {
		List<Autor> categorias = new ArrayList<Autor>();
		try {
			String sql = "SELECT c FROM Autor c";			
			categorias = em.createQuery(sql, Autor.class).getResultList();
		} catch (Exception e) {
			categorias=null;
		}
		return categorias;	
	}
}
