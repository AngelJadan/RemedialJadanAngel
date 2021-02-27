package ec.ups.edu.gestion;

import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.dao.ClienteDAO;
import ec.ups.edu.modelo.Cliente;

@Named
public class GestionClienteON {
	@Inject
	private ClienteDAO cliDAO;
	
	public boolean guardat(Cliente cliente) {
		boolean estado =  false;
		try {
			estado = cliDAO.insert(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return estado;
		}
	}
	public Cliente buscarCliente(String cedula, int cta) {
		Cliente cliente = new Cliente();
		try {
			cliente = cliDAO.buscar(cedula, cta);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return cliente;
		}
	}
	public boolean debitar(Cliente cliente, float recarga) {
		boolean estado = false;
		try {
			if (cliente.getSaldo()>=recarga) {
				float nsaldo = cliente.getSaldo()-recarga;
				cliente.setSaldo(nsaldo);
				estado = cliDAO.update(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return estado;
		}
	}
}
