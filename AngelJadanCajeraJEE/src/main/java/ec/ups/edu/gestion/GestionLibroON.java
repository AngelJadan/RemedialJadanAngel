package ec.ups.edu.gestion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.dao.AutorDAO;
import ec.ups.dao.CategoriaDAO;
import ec.ups.dao.LibroDAO;
import ec.ups.edu.modelo.Autor;
import ec.ups.edu.modelo.Categoria;
import ec.ups.edu.modelo.Libro;

@Named
public class GestionLibroON {
	@Inject
	private LibroDAO libDAO;
	@Inject
	private CategoriaDAO catDAO;
	@Inject
	private AutorDAO autDAO;
	
	public boolean saveLibro(Libro libro) {
		boolean estado =  false;
		try {
			estado = libDAO.insert(libro);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return estado;
		}
	}
	public Categoria reservar(Libro libro) {
		Categoria categoria = new Categoria();
		try {
			libDAO.update(libro);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return categoria;
		}
	}
	public List<Categoria> categorias(){
		List<Categoria> lista = new ArrayList<Categoria>();
		try {
			lista = catDAO.consultar();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return lista;
		}
	}
	public List<Autor> autores(){
		List<Autor> lista = new ArrayList<Autor>();
		try {
			lista = autDAO.consultar();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return lista;
		}
	}
}
