/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bo.BOEnemCortes;
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
import to.TOEnemCortes;

/**
 *
 * @author 71500430
 */
@Path("enemcortes")
public class EnemCortesResource {
    
    
    @Context
    private UriInfo context;

    public EnemCortesResource() {
    }
    
    @GET
    @Produces("application/json; charset=utf-8")
    public List<TOEnemCortes> getEnemCortes() throws Exception {        
        Object o = Cache.getCache("enem", "lista");
        if (o == null) {
            List<TOEnemCortes> cortes = BOEnemCortes.lista();
            
            Cache.setCache("enem", "lista", cortes, 2);
            
            return cortes;
        }else {
            return (List<TOEnemCortes>) o;
        }        
    }
    
    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GET
    @Path("{id}")
    @Produces("application/json; charset=utf-8")
    public TOEnemCortes getEnemCorte(@PathParam("id") int id) throws Exception {
        TOEnemCortes cortes = BOEnemCortes.obter(id);        
        if(cortes == null){
            throw new ClassNotFoundException("Notas de Corte não encontrado");
        }        
        return cortes;
    }
    
    @POST
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOEnemCortes postEnemCortes(TOEnemCortes cortes) throws Exception {        
        BOEnemCortes.inserir(cortes);        
        return cortes;
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOEnemCortes deleteEnemCortes(@PathParam("id") int id) throws Exception {
        boolean success = BOEnemCortes.excluir(id);
        
        if(!success){
            throw new ClassNotFoundException("Erro ao remover nota de corte");
        }
        
        return null;
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public boolean putWorkshop(@PathParam("id") int id, TOEnemCortes workshop) throws Exception {
        if(BOEnemCortes.obter(id) == null){
            throw new ClassNotFoundException("Workshop não encontrado");
        }        
        BOEnemCortes.alterar(workshop);
        
        return true;
    }
}
