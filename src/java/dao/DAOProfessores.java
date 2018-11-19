/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fw.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import to.TOProfessores;
import to.TOUsuario;

/**
 *
 * @author 71500286
 */
public class DAOProfessores {
    public static boolean save(Connection c, TOProfessores t) throws Exception{
        StringBuilder sql = new StringBuilder();
        if(t.getId() < 1){
            sql.append("insert into professores (nome, telefone, sexo, idade)"
                + "VALUES ("
                    + "\""+t.getNome()+"\", "
                    + "\""+t.getTelefone()+"\", "
                    + "\""+t.getSexo()+"\", "
                        + "\"" + t.getIdade()+ "\")");
        }
        else{
            sql.append("update usuario "
                + "SET name = \""+t.getNome()+"\","
                        + " telefone =\"" + t.getTelefone()+ "\","
                        + " sexo =\"" + t.getSexo()+ "\","
                        + " idade =\"" + t.getIdade()+ "\","
                                + "WHERE id = " + t.getId());
        }
        
        return Data.executeUpdate(c, sql.toString()) > 0;
    }
    
    public static boolean delete(Connection c, int id) throws Exception{
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("delete from professores WHERE id = " + id);
        
        return Data.executeUpdate(c, sql.toString()) > 0;
    }
    
    public static TOProfessores getOne (Connection c, int id) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from professores where id = " + id);
                
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOProfessores t = new TOProfessores();
                t.setId(res.getInt("id"));
                t.setNome(res.getString("name"));
                t.setTelefone(res.getString("email"));
                t.setSexo(res.getInt("token"));
                t.setIdade(res.getInt("expiracao"));
                
                return t;
            }
        }
        catch(Exception e){
            return null;
        }
        
        return null;
    }
    
    public static List<TOProfessores> getAll (Connection c) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from professores");
        
        List<TOProfessores> l = new ArrayList<>();
        
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOProfessores t = new TOProfessores();
                t.setId(res.getInt("id"));
                t.setNome(res.getString("name"));
                t.setTelefone(res.getString("email"));
                t.setSexo(res.getInt("token"));
                t.setIdade(res.getInt("expiracao"));
                
                l.add(t);
            }
        }
        catch(Exception e){
            return null;
        }
        
        return l;
    }
}
