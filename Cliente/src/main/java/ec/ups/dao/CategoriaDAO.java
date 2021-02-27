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
public class CategoriaDAO {
	
	@Inject
	private EntityManager em;

	public List<Autor> consultar()throws SQLException {
		List<Autor> autores = new ArrayList<Autor>();
		try {
			String sql = "SELECT c FROM Categoria c";			
			autores = em.createQuery(sql, Autor.class).getResultList();
		} catch (Exception e) {
			autores=null;
		}
		return autores;	
	}
}
