package ec.ups.edu.services;

import javax.inject.Inject;
import javax.ws.rs.Path;

import ec.ups.edu.gestion.GestionLibroON;
import ec.ups.edu.modelo.Categoria;

@Path("clienterest")
public class ClienteREST {
	@Inject
	private GestionLibroON gclienteon;
	
	public Categoria buscarCliente(String cedula, int cta) {
		Categoria categoria = new Categoria();
		categoria = gclienteon.buscarCliente(cedula, cta);
		return categoria;
	}
}
