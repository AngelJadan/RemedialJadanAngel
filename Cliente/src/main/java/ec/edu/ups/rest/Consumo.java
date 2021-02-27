package ec.edu.ups.rest;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONObject;

public class Consumo {
	private String WS_GET_LISTA ="http://localhost:8080/AngelJadanRemedial/ws/librorest/libros";
	private String WS_GET_RESERVA ="http://localhost:8080/AngelJadanRemedial/ws/librorest/reserva?titulo&stock";
	
	public String listaLibro(String titulo) {
		String estado = "";
		try {
			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target(WS_GET_LISTA);
			Response response = target.queryParam("titulo", titulo).request().get();
			String value = response.readEntity(String.class);
			
			System.out.println("val"+value.length());
			
			response.close();
			if (value.equals("{}")) {
				return estado;
			}else {
				System.out.println("libro");
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
	public Message reservar(String titulo) {
		Message sms = new Message();
		
		try {
			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target(WS_GET_LISTA);
			Response response = target.queryParam("cedula", titulo).request().get();
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
