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
import ec.ups.edu.modelo.Libro;

@Path("libros")
public class LibroREST {
	@Inject
	private GestionLibroON glibroon;
	private Libro libro = new Libro();
	
	
	@GET
	@Produces("application/json")
	@Path("/libros")
	public List<Libro> listarLibros() {
		System.out.println("consumiendo");
		List<Libro> libros = new ArrayList<Libro>();;
		libros = glibroon.libros();
		
		return libros;
	}
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/reservar")
	public Message reservar(@QueryParam("Titulo") String titulo, @QueryParam("Stock") int stock) {
		Message sms = new Message();
		try {
			if (glibroon.reservar(titulo,stock)) {
				sms.setCode("500");
				sms.setMessage("Reservado");
			}
		} catch (Exception e) {
			sms.setCode("501");
			sms.setMessage(e.getLocalizedMessage());
		}finally {
			return sms;
		}
	}	
}
