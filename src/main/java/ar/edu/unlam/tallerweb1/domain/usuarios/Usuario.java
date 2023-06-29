package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;

import javax.persistence.*;
import java.util.List;

// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en el
@Entity
public class Usuario {

	// La anotacion id indica que este atributo es el utilizado como clave primaria de la entity, se indica que el valor es autogenerado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// para el resto de los atributo no se usan anotaciones entonces se usa el default de hibernate: la columna se llama igual que
	// el atributo, la misma admite nulos, y el tipo de dato se deduce del tipo de dato de java.

	private String email;
	private String password;
	private String rol;
	private Boolean activo = false;

	@OneToMany
	private List<Estado> estado;
	@OneToMany
	private  List<Dieta> dieta;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public List<Estado> getEstado() {return estado;}
	public void setEstado(List<Estado> estado) {this.estado = estado;}
	public boolean activo() {
		return activo;
    }
    public void activar() {
		activo = true;
    }
	public List<Dieta> getDieta() {return dieta;}
	public void setDieta(List<Dieta> dieta) {this.dieta = dieta;}
}
