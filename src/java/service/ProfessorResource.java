/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bo.BOProfessores;
import bo.BOWorkshop;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import to.TOProfessores;
import to.TOUsuario;
import to.TOWorkshops;

/**
 * REST Web Service
 *
 * @author 71500286
 */
@Path("generic")
public class ProfessorResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProfessorResource
     */
    public ProfessorResource() {
    }

    /**
     * Retrieves representation of an instance of service.UsuariosResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json; charset=utf-8")
    public List<TOProfessores> getProfessores() throws Exception {
        List<TOProfessores> professores = BOProfessores.lista();
        
        return professores;
    }
    
    /**
     * Retrieves representation of an instance of service.UsuariosResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces("application/json; charset=utf-8")
    public TOProfessores getProfessor(@PathParam("id") int id) throws Exception {
        TOProfessores professor = BOProfessores.obter(id);
        
        if(professor == null){
            throw new ClassNotFoundException("Professor não encontrado");
        }
        
        return professor;
    }

    
    /**
     * POST method for updating or creating an instance of UsuariosResource
     * @param professor representation for the resource
     */
    @POST
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOProfessores postProfessor(TOProfessores professor) throws Exception {
        BOProfessores.inserir(professor);
        
        return professor;
    }
    
    /**
     * DELETE method for updating or creating an instance of UsuariosResource
     * @param id
     */
    @DELETE
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOProfessores deleteProfessor(@PathParam("id") int id) throws Exception {
        BOProfessores.excluir(id);
        
        return null;
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
    public boolean putProfessor(@PathParam("id") int id, TOProfessores professor) throws Exception {
        if(BOProfessores.obter(id) == null){
            throw new ClassNotFoundException("Professor não encontrado");
        }
        
        BOProfessores.alterar(professor);
        
        return true;
    }
}
