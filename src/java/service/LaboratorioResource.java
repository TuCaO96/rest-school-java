/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bo.BOCursos;
import bo.BOLaboratorio;
import bo.BOUsuario;
import fw.Cache;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import to.TOLaboratorios;

/**
 *
 * @author 71500430
 */
@Path("laboratorios")
public class LaboratorioResource {
    
    @Context
    private UriInfo context;
    
    @Context
    private HttpServletResponse response;
    
    public LaboratorioResource() {
    }
    
    @GET
    @Produces("application/json; charset=utf-8")
    public List<TOLaboratorios> getLaboratorios() throws Exception {
        Object o = Cache.getCache("curso", "lista");
            if (o == null) {
                List<TOLaboratorios> cursos = BOLaboratorio.lista();

                Cache.setCache("curso", "lista", cursos, 2);

                return cursos;
            }else {
                return (List<TOLaboratorios>) o;
            }       
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json; charset=utf-8")
    public TOLaboratorios getLaboratorio(@PathParam("id") int id) throws Exception {
        TOLaboratorios cursos = BOLaboratorio.obter(id);

            if(cursos == null){
                throw new ClassNotFoundException("Curso não encontrato");
            }
            return cursos;
    }
    
    @POST
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOLaboratorios postLaboratorio(TOLaboratorios curso, @HeaderParam("chave") String chave, @HeaderParam("token") String token) throws Exception {
        if(BOUsuario.autenticado(chave, token)){
            boolean success = BOLaboratorio.inserir(curso);        
            if(!success){
                return null;
            }        
            return curso;
        }else{
            response.sendError(401);
            return null;            
        }
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; chatset=utf-8")
    public TOLaboratorios deleteLaboratorios(@PathParam("id") int id, @HeaderParam("chave") String chave, @HeaderParam("token") String token) throws Exception {
        
        if(BOUsuario.autenticado(chave, token)){
            boolean success = BOLaboratorio.excluir(id);        
            if(!success){
                throw new ClassNotFoundException("Erro ao remover curso");
            }        
            return null;
        }else{
            response.sendError(401);
            return null;            
        }
        
        
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public boolean putLaboratorio(@PathParam("id") int id, TOLaboratorios laboratorio, @HeaderParam("chave") String chave, @HeaderParam("token") String token) throws Exception {
        
        if(BOUsuario.autenticado(chave, token)){
            BOLaboratorio.alterar(laboratorio);
            return true;
        }
        else{
            response.sendError(401);
            return false;            
        }
    }
    
}
