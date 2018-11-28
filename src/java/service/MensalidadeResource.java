/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bo.BOMensalidadeCurso;
import fw.Cache;
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
import to.TOMensalidadeCurso;

/**
 *
 * @author 71500430
 */
public class MensalidadeResource {
    
    @Context
    private UriInfo context;

    public MensalidadeResource() {
    }
    
    @GET
    @Produces("application/json; charset=utf-8")
    public List<TOMensalidadeCurso> getMensalidades() throws Exception {
        Object o = Cache.getCache("mensalidade", "lista");

        if (o == null) {
            List<TOMensalidadeCurso> mensalidades = BOMensalidadeCurso.lista();
            
            Cache.setCache("mensalidade", "lista", mensalidades, 2);
            
            return mensalidades;
        }else {
            return (List<TOMensalidadeCurso>) o;
        }
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json; charset=utf-8")
    public TOMensalidadeCurso getMensalidade(@PathParam("id") int id) throws Exception {
        
        TOMensalidadeCurso mensalidade = BOMensalidadeCurso.obter(id);
        
        if(mensalidade == null){
            throw new ClassNotFoundException("Workshop não encontrado");
        }
        
        return mensalidade;
    }
    
    @POST
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOMensalidadeCurso postMensalidades(TOMensalidadeCurso mensalidade) throws Exception {
        BOMensalidadeCurso.inserir(mensalidade);        
        return mensalidade;
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOMensalidadeCurso deleteMensalidade(@PathParam("id") int id) throws Exception {
        boolean success = BOMensalidadeCurso.excluir(id);        
        if(!success){
            throw new ClassNotFoundException("Erro ao remover workshop");
        }        
        return null;
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public boolean putMensalidade(@PathParam("id") int id, TOMensalidadeCurso workshop) throws Exception {
        if(BOMensalidadeCurso.obter(id) == null){
            throw new ClassNotFoundException("Workshop não encontrado");
        }        
        BOMensalidadeCurso.alterar(workshop);
        return true;
    }
    
}
