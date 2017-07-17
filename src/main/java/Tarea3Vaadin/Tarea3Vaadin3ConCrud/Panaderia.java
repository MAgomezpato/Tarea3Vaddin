package Tarea3Vaadin;

import java.io.Serializable;
import java.time.LocalDate;


public class Panaderia implements Serializable, Cloneable {

    private Long id;

    private String nombre = "";

    private String apellido = "";

    private LocalDate birthDate;

    private LocalDate nacimiento;



    private Equipo cursos;

    private String email = "";

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


    public Equipo getCursos() {
        return cursos;
    }

    public void setCursos(Equipo cursos) {
        this.cursos = cursos;
    }









    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }




    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }





    public String getapellido() {
        return apellido;
    }

    public void setapellido(String apellido) {
        this.apellido = apellido;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isPersisted() {
        return id != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Panaderia && obj.getClass().equals(getClass())) {
            return this.id.equals(((Panaderia) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }

    @Override
    public Panaderia clone() throws CloneNotSupportedException {
        return (Panaderia) super.clone();
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}