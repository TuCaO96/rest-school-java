/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Criptografia;
import fw.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import to.TOUsuario;

/**
 *
 * @author 71500286
 */
public class DAOUsuario {
    public static boolean login(Connection c, String email, String senha) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from usuario where email = \"" + email + 
                "\" AND senha = \"" + senha + "\"");
        try (ResultSet rs = Data.executeQuery(c, sql.toString())) {
            rs.next();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public static boolean save(Connection c, TOUsuario usuario) throws Exception{
        StringBuilder sql = new StringBuilder();
        
        if(usuario.getId() < 1){
            sql.append("insert into usuario (nome, email, senha, chave, expiracao, token)"
                + "VALUES (\""+usuario.getNome()+"\", \""+usuario.getEmail()+"\","
                        + "\""+Criptografia.sha1(usuario.getSenha())+"\", \"" + usuario.getChave() + "\""
                        + "\"" + usuario.getExpiracao() + "\""
                        + "\"" + usuario.getToken() + "\")");
        }
        else{
            sql.append("update usuario "
                + "SET name = \""+usuario.getNome()+"\", email = \""+usuario.getEmail()+"\","
                        + " senha =\""+Criptografia.sha1(usuario.getSenha())+"\""
                        + " chave =\"" + usuario.getChave() + "\""
                        + " expiracao =\"" + usuario.getExpiracao()+ "\""
                        + " token =\"" + usuario.getToken()+ "\""
                                + "WHERE id = " + usuario.getId());
        }
        
        return Data.executeUpdate(c, sql.toString()) > 0;
    }
    
    public static boolean delete(Connection c, int id) throws Exception{
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("delete from usuario WHERE id = " + id);
        
        return Data.executeUpdate(c, sql.toString()) > 0;
    }
    
    public static TOUsuario getOne (Connection c, int id) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from usuario where id = " + id);
                
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOUsuario t = new TOUsuario();
                t.setId(res.getInt("id"));
                t.setNome(res.getString("name"));
                t.setEmail(res.getString("email"));
                t.setToken(res.getString("token"));
                t.setExpiracao(res.getTimestamp("expiracao"));
                
                return t;
            }
        }
        catch(Exception e){
            return null;
        }
        
        return null;
    }
    
    public static TOUsuario getByEmail (Connection c, String email) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from usuario where email = \"" + email + "\" ");
                
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOUsuario t = new TOUsuario();
                t.setId(res.getInt("id"));
                t.setNome(res.getString("name"));
                t.setEmail(res.getString("email"));
                t.setSenha(res.getString("senha"));
                t.setChave(res.getString("chave"));
                t.setToken(res.getString("token"));
                t.setExpiracao(res.getTimestamp("expiracao"));
                
                return t;
            }
        }
        catch(Exception e){
            return null;
        }
        
        return null;
    }
    
    public static List<TOUsuario> getAll (Connection c) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from usuario");
        
        List<TOUsuario> l = new ArrayList<>();
        
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOUsuario t = new TOUsuario();
                t.setId(res.getInt("id"));
                t.setNome(res.getString("name"));
                t.setEmail(res.getString("email"));
                t.setToken(res.getString("token"));
                t.setExpiracao(res.getTimestamp("expiracao"));
                
                l.add(t);
            }
        }
        catch(Exception e){
            return null;
        }
        
        return l;
    }
    
    public static boolean atualizaToken(Connection c, TOUsuario t) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" update usuario set token = ?, expiracao = ? where email = ? ");

        return Data.executeUpdate(c, sql.toString(), t.getToken(), t.getExpiracao(), t.getEmail()) > 0;

    }
}
