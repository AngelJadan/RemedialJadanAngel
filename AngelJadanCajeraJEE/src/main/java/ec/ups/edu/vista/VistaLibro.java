package ec.ups.edu.vista;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.gestion.GestionLibroON;
import ec.ups.edu.modelo.Autor;
import ec.ups.edu.modelo.Categoria;
import ec.ups.edu.modelo.Libro;

@RequestScoped
@Named
public class VistaLibro {
	@Inject
	private GestionLibroON glibroon;
	private List<Autor> autores;
	private List<Categoria> categorias;
	
	private Autor autor;
	private Categoria categoria;
	private Libro libro;
	
	@PostConstruct
	public void init() {
		autor = new Autor();
		categoria = new Categoria();
		libro = new Libro();
		
		autores = new ArrayList<Autor>();
		categorias = new ArrayList<Categoria>();
		listautores();
		listcategorias();
	}
	
	public String saveLibro() {
		try {
			System.out.println("autor"+autor.toString());
			System.out.println("categoria "+categoria.toString());
			System.out.println("libro "+libro.toString());
			libro.setAutor(autor);
			libro.setCategoria(categoria);
			if(glibroon.saveLibro(libro)) {
				FacesContext.getCurrentInstance().addMessage("form",
						new FacesMessage("Libro guardado exitosamente"));
				libro = new Libro();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage("Error "+e.getLocalizedMessage()));
		}
		return "guardado";
	}
	
	public void listcategorias() {
		categorias = glibroon.categorias();
	}
	public void listautores() {
		autores = glibroon.autores();
	}
	public GestionLibroON getGlibroon() {
		return glibroon;
	}
	public void setGlibroon(GestionLibroON glibroon) {
		this.glibroon = glibroon;
	}
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	
}
