package ec.ups.dao;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.modelo.Categoria;
import ec.ups.edu.modelo.Libro;


@Stateless
public class LibroDAO {
	
	@Inject
	private EntityManager em;
	
	public boolean insert(Libro libro) throws SQLException{
		em.persist(libro);
		return true;
	}
	public boolean update(Libro libro) throws SQLException{
		em.merge(libro);
		return true;
	}
	public Libro consultar(String titulo)throws SQLException {
		Libro cli = new Libro();
		try {
			String sql = "SELECT l FROM Libro l"
					+ " WHERE titulo=:tit";			
			cli = (Libro)em.createQuery(sql, Libro.class)
					.setParameter("tit",titulo)
					.getSingleResult();
		} catch (Exception e) {
			cli=null;
		}
		return cli;	
	}
}
