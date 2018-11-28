/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bo.BODisciplinasCursos;
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
import to.TODisciplinasCurso;

/**
 *
 * @author 71500430
 */
@Path("disciplina")
public class DisciplinasResource {
    
    @Context
    private UriInfo context;

    public DisciplinasResource() {
    }
    
    @GET
    @Produces("application/json; charset=utf-8")
    public List<TODisciplinasCurso> getDisciplinas() throws Exception {
        List<TODisciplinasCurso> disciplinas = BODisciplinasCursos.lista();        
        return disciplinas;
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
    public TODisciplinasCurso postDisciplina(TODisciplinasCurso disciplina) throws Exception {
        BODisciplinasCursos.inserir(disciplina);        
        return disciplina;
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TODisciplinasCurso deleteDisciplina(@PathParam("id") int id) throws Exception {
        boolean success = BODisciplinasCursos.excluir(id);        
        if(!success){
            throw new ClassNotFoundException("Erro ao remover workshop");
        }        
        return null;
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public boolean putWorkshop(@PathParam("id") int id, TODisciplinasCurso workshop) throws Exception {
        if(BODisciplinasCursos.obter(id) == null){
            throw new ClassNotFoundException("Workshop não encontrado");
        }        
        BODisciplinasCursos.alterar(workshop);        
        return true;
    }
    
    
    
    
    
}
