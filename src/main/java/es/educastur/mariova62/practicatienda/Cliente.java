/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.educastur.mariova62.practicatienda;

/**
 *
 * @author 1dawd07
 */
import java.io.Serializable;

public class Cliente implements Serializable {

    private String idCliente;
    private String nombre;
    private String telefono;
    private String email;

    public Cliente(String idCliente, String nombre, String telefono, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("idCliente=").append(idCliente);
        sb.append(", nombre=").append(nombre);
        sb.append(", telefono=").append(telefono);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }*/
    @Override
    public String toString() {
        return String.format("""
        
        \tCLIENTE
        ───────────────────────
        ID:       %s
        Nombre:   %s
        Teléfono: %s
        Email:    %s
        """,
                idCliente, nombre, telefono, email
        );
    }

}
