package ec.edu.ups.rest;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONObject;

public class SolicitudAutorizacion {
	private String WS_GET_SOLICITUD ="http://localhost:8080/AngelJadanOperadoraJEE/ws/Solicitud/solicitud?cedula";
	private String WS_GET_SOLICITUD_SRI ="http://localhost:8080/AngelJadanSRI/ws/Autorizacion/autorizacion?cedula&nombre&apellido&valor";
	
	public String solicitudOperadora(String cedula) {
		String estado = "";
		try {
			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target(WS_GET_SOLICITUD);
			Response response = target.queryParam("cedula", cedula).request().get();
			String value = response.readEntity(String.class);
			
			System.out.println("val"+value.length());
			
			response.close();
			if (value.equals("{}")) {
				return estado;
			}else {
				System.out.println("operadora");
				JSONObject jsonobject = new JSONObject(value);
				System.out.println(jsonobject.get("code"));
				System.out.println(jsonobject.get("message"));
				estado=jsonobject.getString("message");
			}
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
			new Exception("Error");
		}
		return estado;
	}
	public Message autorizacionSRI(String cedula,String nombre, String apellido, double valor) {
		Message sms = new Message();
		
		try {
			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target(WS_GET_SOLICITUD_SRI);
			Response response = target.queryParam("cedula", cedula).queryParam("nombre", nombre).queryParam("apellido", apellido).queryParam("valor", valor).request().get();
			String value = response.readEntity(String.class);
			
			System.out.println("val"+value.length());
			
			response.close();
			if (value.equals("{}")) {
				return sms;
			}else {
				System.out.println("sri");
				JSONObject jsonobject = new JSONObject(value);			
				System.out.println(jsonobject.get("code"));
				sms.setCode(jsonobject.getString("code"));
				System.out.println(jsonobject.get("message"));
				sms.setMessage(jsonobject.getString("message"));
				System.out.println(jsonobject.get("autorizacion"));
				sms.setAutorizacion(jsonobject.getString("autorizacion"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			new Exception("Error");
		}
		return sms;
	}
	
}
