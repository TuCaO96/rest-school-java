/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bo.BOUsuario;
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
import bo.BOWorkshop;
import fw.Cache;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HeaderParam;
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
    
    @Context
    private HttpServletResponse response;

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
        
        Object o = Cache.getCache("workshop", "lista");

        if (o == null) {
            List<TOWorkshops> workshops = BOWorkshop.lista();
            
            Cache.setCache("workshop", "lista", workshops, 2);
            
            return workshops;
        }else {
            return (List<TOWorkshops>) o;
        }
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
    public TOWorkshops postWorkshop(TOWorkshops workshop, @HeaderParam("chave") String chave, @HeaderParam("token") String token) throws Exception {
        if(BOUsuario.autenticado(chave, token)){
            BOWorkshop.inserir(workshop);
        
            return workshop;
        }
         else{
            response.sendError(401);
            return null;
        }
        
    }
    
    /**
     * DELETE method for updating or creating an instance of UsuariosResource
     * @param id
     */
    @DELETE
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOWorkshops deleteWorkshop(@PathParam("id") int id, @HeaderParam("chave") String chave, @HeaderParam("token") String token) throws Exception {
        if(BOUsuario.autenticado(chave, token)){
           BOWorkshop.excluir(id);
        
            return null;
        }
         else{
            response.sendError(401);
            return null;
        }
        
        
    }
    
    /**
     * PUT method for updating or creating an instance of UsuariosResource
     * @param id
     * @param workshop
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public boolean putWorkshop(@PathParam("id") int id, TOWorkshops workshop, @HeaderParam("chave") String chave, @HeaderParam("token") String token) throws Exception {
        if(BOUsuario.autenticado(chave, token)){
            if(BOWorkshop.obter(id) == null){
                throw new ClassNotFoundException("Workshop não encontrado");
            }

            BOWorkshop.alterar(workshop);

            return true;
        }
         else{
            response.sendError(401);
            return false;
        }
    }
}
