package ar.edu.unlam.tallerweb1.domain.persona;

import ar.edu.unlam.tallerweb1.domain.conditionScore.ConditionScore;
import ar.edu.unlam.tallerweb1.domain.dieta.Dieta;
import ar.edu.unlam.tallerweb1.domain.estados.Estado;

import javax.persistence.*;
import java.util.List;

@Entity
public class Persona {
	public Persona() {
		this.conditionScore = new ConditionScore();
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nombre;
	//Estado => Eenfermedades: Celiaco o Sano
	@OneToOne
	private Estado estado;
	@Column
	private String apellido;
	@Column
	private String email;

	@Column
	private ConditionScore conditionScore;
	@Column
	private int edad;
	@Column
	private double altura;
	@Column
	private int peso;

	@OneToMany
	private  List<Dieta> dieta;
	@Column
	private char genero;

	@Column
	private int objetivo; //0 => Gestion; 1=> Perdida de Peso; 2=> Ganancia de peso;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Estado getEstado(){return this.estado;}
	public void setEstado(Estado estado){this.estado = estado;}

    public ConditionScore getConditionScore() {
		return this.conditionScore;
    }

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setConditionScore(ConditionScore conditionScore) {
		this.conditionScore = conditionScore;
	}

	public int getEdad() {
		return edad;
	}

	public double getAltura() {
		return altura;
	}

	public int getPeso() {
		return peso;
	}

	public List<Dieta> getDieta() {
		return dieta;
	}

	public void setDieta(List<Dieta> dieta) {
		this.dieta = dieta;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public List<Dieta> getDietas() {
		return this.dieta;
	}

	public char getGenero() {
		return this.genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public int getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(int objetivo) {
		this.objetivo = objetivo;
	}
}
