package ec.ups.edu.services;

import javax.inject.Inject;
import javax.ws.rs.Path;

import ec.ups.edu.gestion.GestionClienteON;
import ec.ups.edu.modelo.Cliente;

@Path("clienterest")
public class ClienteREST {
	@Inject
	private GestionClienteON gclienteon;
	
	public Cliente buscarCliente(String cedula, int cta) {
		Cliente cliente = new Cliente();
		cliente = gclienteon.buscarCliente(cedula, cta);
		return cliente;
	}
}
