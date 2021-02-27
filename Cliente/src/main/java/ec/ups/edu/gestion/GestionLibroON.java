package ec.ups.edu.gestion;

import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.dao.LibroDAO;
import ec.ups.edu.modelo.Categoria;
import ec.ups.edu.modelo.Libro;

@Named
public class GestionLibroON {
	@Inject
	private LibroDAO libDAO;
	
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
}
