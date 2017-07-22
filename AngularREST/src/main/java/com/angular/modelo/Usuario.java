package com.angular.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SEG_USUARIO")
public class Usuario implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 3695579153210351946L;

    /**
     * Propiedad que representa a la clave primaria de la entidad Usuario, se
     * relaciona con la columna CODIGO.
     */
    @Id
    @Column(name = "CODIGO", nullable = false, length = 50)
    private String codigo;

    /**
     * ${COL_COMMENT}. Representa a la columna NOMBRE.
     */
    @Column(name = "NOMBRE", nullable = false, length = 500)
    private String nombre;

    /**
     * ${COL_COMMENT}. Representa a la columna TOKEN.
     */
    @Column(name = "TOKEN", nullable = false, length = 50)
    private String token;

    /**
     * Crea una nueva instancia de la entidad Usuario.
     */
    public Usuario() {
    }

    /**
     * Crea una nueva instancia de la entidad Usuario de acuerdo a su clave
     * primaria.
     *
     * @param codigoigo valor para la clave primaria de la entidad Usuario.
     */
    public Usuario(String codigoigo) {
        this.codigo = codigoigo;
    }

    /**
     * Obtiene el valor de la propiedad codigo relacionado con la columna
     * CODIGO.
     *
     * @return el valor asignado a la propiedad codigo.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Asigna un valor a la propiedad codigo, relacionado con la columna CODIGO.
     *
     * @param codigo el valor a ser asignado a la propiedad codigo.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el valor de la propiedad nombre relacionado con la columna
     * NOMBRE.
     *
     * @return el valor asignado a la propiedad nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un valor a la propiedad nombre, relacionado con la columna NOMBRE.
     *
     * @param nombre el valor a ser asignado a la propiedad nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el valor de la propiedad token relacionado con la columna TOKEN.
     *
     * @return el valor asignado a la propiedad token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Asigna un valor a la propiedad token, relacionado con la columna TOKEN.
     *
     * @param token el valor a ser asignado a la propiedad token.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash = (codigo != null) ? codigo.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Usuario)) {
            igual = false;
        }
        Usuario other = (Usuario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            igual = false;
        }
        return igual;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Usuario[codigoigo=" + codigo + "]";
    }

}
