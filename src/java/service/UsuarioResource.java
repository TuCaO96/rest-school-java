/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bo.BOProfessores;
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
import fw.Cache;
import fw.Criptografia;
import to.TOProfessores;

/**
 * REST Web Service
 *
 * @author 71500286
 */
@Path("usuarios")
public class UsuarioResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioResource
     */
    public UsuarioResource() {
    }

    /**
     * Retrieves representation of an instance of service.UsuariosResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json; charset=utf-8")
    public List<TOUsuario> getUsuarios() throws Exception {
        List<TOUsuario> usuarios = BOUsuario.getAll();
        
        Object o = Cache.getCache("usuario", "lista");

        if (o == null) {
            List<TOProfessores> professores = BOProfessores.lista();
            
            Cache.setCache("usuario", "lista", professores, 2);
            
            return usuarios;
        }else {
            return (List<TOUsuario>) o;
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
    public TOUsuario getUsuario(@PathParam("id") int id) throws Exception {
        TOUsuario usuario = BOUsuario.getOne(id);
        
        if(usuario == null){
            throw new ClassNotFoundException("Usuário não encontrado");
        }
        
        return usuario;
    }

    /**
     * POST method for updating or creating an instance of UsuariosResource
     * @param usuario representation for the resource
     */
    @POST
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    @Path("autenticar")
    public TOUsuario postLogin(TOUsuario usuario) throws ClassNotFoundException, Exception {
        TOUsuario usuario1 = BOUsuario.getOneByEmail(usuario.getEmail());
        if(usuario1 == null || !usuario1.getSenha().equals(Criptografia.sha1(usuario.getSenha()))){
            throw new ClassNotFoundException("Usuário e/ou senha inválidos");
        }
        
        return usuario1;
    }
    
    /**
     * POST method for updating or creating an instance of UsuariosResource
     * @param usuario representation for the resource
     */
    @POST
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOUsuario postUsuario(TOUsuario usuario) throws Exception {
        boolean success = BOUsuario.save(usuario);
        
        if(!success){
            return null;
        }
        
        return usuario;
    }
    
    /**
     * DELETE method for updating or creating an instance of UsuariosResource
     * @param id
     */
    @DELETE
    @Path("{id}")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public TOUsuario deleteUsuario(@PathParam("id") int id) throws Exception {
        boolean success = BOUsuario.delete(id);
        
        if(!success){
            throw new ClassNotFoundException("Erro ao remover usuário");
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
    public boolean putUsuario(@PathParam("id") int id, TOUsuario usuario) throws Exception {
        if(BOUsuario.getOne(id) == null){
            throw new ClassNotFoundException("Usuário não encontrado");
        }
        
        usuario.setId(id);
        
        boolean success = BOUsuario.save(usuario);
        
        return success;
    }
}
