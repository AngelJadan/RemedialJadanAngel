package ec.ups.edu.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.ups.edu.gestion.GestionLibroON;
import ec.ups.edu.modelo.Categoria;
import ec.ups.edu.modelo.Libro;

@Path("librorest")
public class LibroREST {
	@Inject
	private GestionLibroON glibroon;
	private List<Libro> libros;
	private Libro libro;
	
	public LibroREST() {
		libros = new ArrayList<Libro>();
		libro = new Libro();
	}
	
	@GET
	@Path("/libros")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Libro> listarLibros() {
		try {
			libros = glibroon.libros();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return libros;
		}
	}
	@POST
	@Path("/reservar")
	@Produces("application/json")
	@Consumes("application/json")
	public String reservar(@QueryParam("Titulo") String titulo, @QueryParam("Stock") int stock) {
		String estado = "";
		try {
			if (glibroon.reservar(titulo,stock)) {
				estado = "Reservado";
			}
		} catch (Exception e) {
			estado = e.getLocalizedMessage();
		}finally {
			return estado;
		}
	}

	public GestionLibroON getGlibroon() {
		return glibroon;
	}

	public void setGlibroon(GestionLibroON glibroon) {
		this.glibroon = glibroon;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	
	
}
