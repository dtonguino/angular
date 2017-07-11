/*
 * Sistema: Angular
 * Creado: 06-jul-2017 - 11:22:56
 * 
 * Copyright (C) 2017 Diego Tonguino
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.angular.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Almacena la informaci\u00f3n de los pa\u00edses
 *
 * @author Diego Tonguino
 * @version $Revision: $
 */
@Entity
@Table(name = "GEN_PAIS")
@XmlRootElement
public class Pais implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -4259511727297789466L;

    /**
     * Propiedad que representa a la clave primaria de la entidad Pais, se
     * relaciona con la columna COD_PAIS.
     */
    @Id
    @Column(name = "COD_PAIS", nullable = false, length = 15)
    private String codigoPais;

    /**
     * El nombre del pa\u00eds. Representa a la columna NOMBRE.
     */
    @Column(name = "NOMBRE", nullable = false, length = 250)
    private String nombre;

    /**
     * Crea una nueva instancia de la entidad Pais.
     */
    public Pais() {
    }

    /**
     * Crea una nueva instancia de la entidad Pais de acuerdo a su clave
     * primaria.
     *
     * @param codigoPais valor para la clave primaria de la entidad Pais.
     */
    public Pais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    /**
     * Obtiene el valor de la propiedad codigoPais relacionado con la columna
     * COD_PAIS.
     *
     * @return el valor asignado a la propiedad codigoPais.
     */
    public String getCodigoPais() {
        return codigoPais;
    }

    /**
     * Asigna un valor a la propiedad codigoPais, relacionado con la columna
     * COD_PAIS.
     *
     * @param codigoPais el valor a ser asignado a la propiedad codigoPais.
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
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
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash = (codigoPais != null) ? codigoPais.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Pais)) {
            igual = false;
        }
        Pais other = (Pais) object;
        if ((this.codigoPais == null && other.codigoPais != null) || (this.codigoPais != null && !this.codigoPais.equals(other.codigoPais))) {
            igual = false;
        }
        return igual;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Pais[codigoPais=" + codigoPais + "]";
    }

}
