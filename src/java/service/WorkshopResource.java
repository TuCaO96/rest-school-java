/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import to.TOUsuario;
import bo.BOUsuario;
import bo.BOWorkshop;
import to.TOWorkshops;

/**
 * REST Web Service
 *
 * @author 71500286
 */
@Path("workshops")
public class WorkshopResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioResource
     */
    public WorkshopResource() {
    }

    /**
     * Retrieves representation of an instance of service.UsuariosResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json; charset=utf-8")
    public List<TOWorkshops> getWorkshops() throws Exception {
        List<TOWorkshops> workshops = BOWorkshop.lista();
        
        return workshops;
    }
    
    /**
     * Retrieves representation of an instance of service.UsuariosResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces("application/json; charset=utf-8")
    public TOWorkshops getWorkshop(@PathParam("id") int id) throws Exception {
        TOWorkshops workshop = BOWorkshop.obter(id);
        
        if(workshop == null){
            throw new ClassNotFoundException("Workshop não encontrado");
        }
        
        return workshop;
    }

    
    /**
     * POST method for updating or creating an instance of UsuariosResource
     * @param workshop representation for the resource
     */
    @POST
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOWorkshops postWorkshop(TOWorkshops workshop) throws Exception {
        BOWorkshop.inserir(workshop);
        
        return workshop;
    }
    
    /**
     * DELETE method for updating or creating an instance of UsuariosResource
     * @param id
     */
    @DELETE
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOUsuario deleteWorkshop(@PathParam("id") int id) throws Exception {
        boolean success = BOUsuario.delete(id);
        
        if(!success){
            throw new ClassNotFoundException("Erro ao remover workshop");
        }
        
        return null;
    }
    
    /**
     * PUT method for updating or creating an instance of UsuariosResource
     * @param id
     * @param usuario
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public boolean putWorkshop(@PathParam("id") int id, TOWorkshops workshop) throws Exception {
        if(BOUsuario.getOne(id) == null){
            throw new ClassNotFoundException("Workshop não encontrado");
        }
        
        BOWorkshop.alterar(workshop);
        
        return true;
    }
}
