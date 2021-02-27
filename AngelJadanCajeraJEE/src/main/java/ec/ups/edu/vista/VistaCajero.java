package ec.ups.edu.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.ups.rest.Message;
import ec.edu.ups.rest.SolicitudAutorizacion;
import ec.ups.edu.gestion.GestionClienteON;
import ec.ups.edu.modelo.Cliente;

@Named
@RequestScoped
public class VistaCajero {

	@Inject
	private GestionClienteON gcllienteon;
	private Cliente cliente;
	private String cedula;
	private int cta;
	private float valorRecarga;
	private SolicitudAutorizacion solicitar;
	
	@PostConstruct
	private void init() {
		cliente = new Cliente();
		solicitar=new SolicitudAutorizacion();
	}
	public void buscarCliente() {
		try {
			cliente = gcllienteon.buscarCliente(cedula, cta);
			if (cliente.equals(false)) {
				FacesContext.getCurrentInstance().addMessage("formul",
						new FacesMessage("Cliente no encontrado"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formul",
					new FacesMessage("Cliente no encontrado"));
		}
	}
	public void guardar() {
		try {
			boolean est = false;
			est = gcllienteon.guardat(cliente);
			if (est==true) {
				FacesContext.getCurrentInstance().addMessage("fomrmul",
						new FacesMessage("Cliente guardado correctamente"));
				cliente = new Cliente();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("fomrmul",
					new FacesMessage("Cliente no registrado"));
		}
	}
	public void recargar() {
		Message sms = new Message();
		String estado = "";
		System.out.println("valor "+valorRecarga);
		cliente = gcllienteon.buscarCliente(cedula, cta);
		estado = solicitar.solicitudOperadora(cedula);	
		System.out.println(estado);
		if (estado.equals("true")) {		
			System.out.println("sri");
			sms = solicitar.autorizacionSRI(cliente.getCedula(),cliente.getNombre(),cliente.getApellido(),valorRecarga);
			if(sms.getAutorizacion().equals("Autorizado")) {
				boolean est=false;
				est = gcllienteon.debitar(cliente, valorRecarga);
				if (est==true) {
					cliente = gcllienteon.buscarCliente(cedula, cta);
					FacesContext.getCurrentInstance().addMessage("formul",
							new FacesMessage("Transaccion satisfactoria; nuevo saldo "+cliente.getSaldo()
							+"\n Autorizacion n: "+sms.getMessage()));
				}else {
					FacesContext.getCurrentInstance().addMessage("formul",
							new FacesMessage("Debito no realziado"));
				}
				FacesContext.getCurrentInstance().addMessage("formul",
						new FacesMessage("Autorizacio #: "+sms.getMessage()));
			}else {
				FacesContext.getCurrentInstance().addMessage("formul",
						new FacesMessage("No autorizado"));
			}
		}else {
			FacesContext.getCurrentInstance().addMessage("formul",
					new FacesMessage("Rechazado por la operadora"));
		}
	}
	public GestionClienteON getGcllienteon() {
		return gcllienteon;
	}

	public void setGcllienteon(GestionClienteON gcllienteon) {
		this.gcllienteon = gcllienteon;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public int getCta() {
		return cta;
	}
	public void setCta(int cta) {
		this.cta = cta;
	}
	public float getValorRecarga() {
		return valorRecarga;
	}
	public void setValorRecarga(float valorRecarga) {
		this.valorRecarga = valorRecarga;
	}
	public SolicitudAutorizacion getSolicitar() {
		return solicitar;
	}
	public void setSolicitar(SolicitudAutorizacion solicitar) {
		this.solicitar = solicitar;
	}
	
}
