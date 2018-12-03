/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bo.BOProfessores;
import bo.BOUsuario;
import fw.Cache;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import to.TOProfessores;

/**
 * REST Web Service
 *
 * @author 71500286
 */
@Path("professores")
public class ProfessorResource {

    @Context
    private UriInfo context;

    @Context
    private HttpServletResponse response;
    
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
        Object o = Cache.getCache("professor", "lista");

        if (o == null) {
            List<TOProfessores> professores = BOProfessores.lista();
            
            Cache.setCache("professor", "lista", professores, 2);
            
            return professores;
        }else {
            return (List<TOProfessores>) o;
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
    public TOProfessores postProfessor(TOProfessores professor, @HeaderParam("chave") String chave, @HeaderParam("token") String token) throws Exception {
        if(BOUsuario.autenticado(chave, token)){
            BOProfessores.inserir(professor);
        
        return professor;
        } else{
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
    public TOProfessores deleteProfessor(@PathParam("id") int id, @HeaderParam("chave") String chave, @HeaderParam("token") String token) throws Exception {
        if(BOUsuario.autenticado(chave, token)){
            BOProfessores.excluir(id);
        
            return null;
        } else{
            response.sendError(401);
            return null;
        }
        
        
    }
    
    /**
     * PUT method for updating or creating an instance of UsuariosResource
     * @param id
     * @param professor
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public boolean putProfessor(@PathParam("id") int id, TOProfessores professor, @HeaderParam("chave") String chave, @HeaderParam("token") String token) throws Exception {
        if(BOUsuario.autenticado(chave, token)){
            if(BOProfessores.obter(id) == null){
                throw new ClassNotFoundException("Professor não encontrado");
            }

            BOProfessores.alterar(professor);
        
            return true;
        } else{
            response.sendError(401);
            return false;
        }
    }
}
