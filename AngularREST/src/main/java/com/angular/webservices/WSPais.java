/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angular.webservices;

import com.angular.services.GenericCrud;
import com.angular.modelo.Pais;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Diego Tonguino
 */
@Stateless
@Path("WSPais")
public class WSPais extends GenericCrud {

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Pais entity) {
        super.insert(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Pais entity) {
        super.update(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        try {
            super.delete(super.findByPK(id, Pais.class));
        } catch (Exception ex) {
            Logger.getLogger(WSPais.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Pais find(@PathParam("id") String id) {
        return super.findByPK(id, Pais.class);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Pais> findAll() {
        return super.find(new Pais());
    }

}
