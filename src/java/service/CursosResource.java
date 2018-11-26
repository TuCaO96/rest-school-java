/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bo.BOCursos;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import to.TOCursos;

/**
 *
 * @author 71500430
 */
@Path("cursos")
public class CursosResource {
    
    @Context
    private UriInfo context;
    
    
    public CursosResource() {
    }
    
    @GET
    @Produces("application/json; charset=utf-8")
    public List<TOCursos> getCursos() throws Exception {
        List<TOCursos> cursos = BOCursos.lista();
        
        return cursos;        
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json; charset=utf-8")
    public TOCursos getCurso(@PathParam("id") int id) throws Exception {
        TOCursos cursos = BOCursos.obter(id);
        
        if(cursos == null){
            throw new ClassNotFoundException("Curso não encontrato");
        }
        return cursos;
    }
    
    @POST
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOCursos postCurso(TOCursos curso) throws Exception {
        boolean success = BOCursos.inserir(curso);        
        if(!success){
            return null;
        }        
        return curso;
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; chatset=utf-8")
    public TOCursos deleteCursos(@PathParam("id") int id) throws Exception {
        boolean success = BOCursos.excluir(id);        
        if(!success){
            throw new ClassNotFoundException("Erro ao remover curso");
        }        
        return null;
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public boolean putCurso(@PathParam("id") int id, TOCursos curso) throws Exception {
        if(BOCursos.obter(id) == null){
            throw new ClassNotFoundException("Curso não encontrado");
        }        
        curso.setId(id);        
        boolean success = BOCursos.alterar(curso);        
        return success;
    }
    
}
