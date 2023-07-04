package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.conditionScore.ConditionScore;
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

    /**Data for Login**/
    @Column private String email;
    @Column private String password;
    @Column private String rol = "User";
    @Column private Boolean activo = false;


    /**Personal Data**/
    @Column private String nombre;
    @Column private String apellido;
    @Column private int edad;
    @Column private double altura;
    @Column private int peso;
    @OneToOne private Estado estado;
    @Column private String genero;

    /**Data for page**/
    @OneToOne private ConditionScore conditionScore;
    @Column private int objetivo; //0 => Gestion; 1=> Perdida de Peso; 2=> Ganancia de peso;
    @OneToMany private List<Dieta> dieta;

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean activo() {
        return activo;
    }

    public void activar() {
        activo = true;
    }

    public List<Dieta> getDieta() {
        return dieta;
    }

    public void setDieta(List<Dieta> dieta) {
        this.dieta = dieta;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public ConditionScore getConditionScore() {
        return conditionScore;
    }

    public void setConditionScore(ConditionScore conditionScore) {
        this.conditionScore = conditionScore;
    }

    public int getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(int objetivo) {
        this.objetivo = objetivo;
    }

    public void addNewWeekCS(int newCS) {
        this.conditionScore.addNewPoint(newCS);
    }
}
