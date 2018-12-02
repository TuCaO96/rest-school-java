/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bo.BODisciplinasCursos;
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
import to.TODisciplinasCurso;

/**
 *
 * @author 71500430
 */
@Path("disciplina")
public class DisciplinasResource {
    
    @Context
    private UriInfo context;
    
    @Context
    private HttpServletResponse response;

    public DisciplinasResource() {
    }
    
    @GET
    @Produces("application/json; charset=utf-8")
    public List<TODisciplinasCurso> getDisciplinas() throws Exception {        
        Object o = Cache.getCache("disciplina", "lista");
        
        if (o == null) {
            List<TODisciplinasCurso> disciplinas = BODisciplinasCursos.lista();        
            Cache.setCache("disciplina", "lista", disciplinas, 2);
            
            return disciplinas;
        }else {
            return (List<TODisciplinasCurso>) o;
        }
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json; charset=utf-8")
    public TODisciplinasCurso getDisciplina(@PathParam("id") int id) throws Exception {
        TODisciplinasCurso disciplina = BODisciplinasCursos.obter(id);        
        if(disciplina == null){
            throw new ClassNotFoundException("Disciplina não encontrado");
        }        
        return disciplina;
    }
    
    @POST
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TODisciplinasCurso postDisciplina(TODisciplinasCurso disciplina, @HeaderParam("chave") String chave, @HeaderParam("token") String token) throws Exception {
        if(BOUsuario.autenticado(chave, token)){
            BODisciplinasCursos.inserir(disciplina);        
            return disciplina;
        }else{
            response.sendError(401);
            return null;
        }
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TODisciplinasCurso deleteDisciplina(@PathParam("id") int id, @HeaderParam("chave") String chave, @HeaderParam("token") String token) throws Exception {
        if(BOUsuario.autenticado(chave, token)){
            boolean success = BODisciplinasCursos.excluir(id);        
            if(!success){
                throw new ClassNotFoundException("Erro ao remover workshop");
            }
        }   
        return null;
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public boolean putWorkshop(@PathParam("id") int id, TODisciplinasCurso workshop, @HeaderParam("chave") String chave, @HeaderParam("token") String token) throws Exception {
        if(BOUsuario.autenticado(chave, token)){
            if(BODisciplinasCursos.obter(id) == null){
                throw new ClassNotFoundException("Workshop não encontrado");
            }        
            BODisciplinasCursos.alterar(workshop);  
            return true;
        }else{
            return false;
        }
    }    
}
