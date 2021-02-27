package ec.ups.edu.services;

import javax.inject.Inject;
import javax.ws.rs.Path;

import ec.ups.edu.gestion.GestionLibroON;
import ec.ups.edu.modelo.Categoria;

@Path("librorest")
public class LibroREST {
	@Inject
	private GestionLibroON glibroon;
	
	public Categoria listarCategoria() {
		Categoria categoria = new Categoria();
		return categoria;
	} 
}
