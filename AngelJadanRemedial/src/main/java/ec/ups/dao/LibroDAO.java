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
public class LibroDAO {

	@Inject
	private EntityManager em;

	public boolean insert(Libro libro) throws SQLException {
		em.persist(libro);
		return true;
	}

	public boolean update(Libro libro) throws SQLException {
		em.merge(libro);
		return true;
	}

	public List<Libro> consultar() throws SQLException {
		List<Libro> lista = new ArrayList<Libro>();

		String sql = "SELECT l FROM Libro l";
		lista = em.createQuery(sql, Libro.class).getResultList();
		return lista;
	}
}
