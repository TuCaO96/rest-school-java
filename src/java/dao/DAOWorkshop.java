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
import to.TOEnemCortes;
import to.TOWorkshops;

/**
 *
 * @author 71500286
 */
public class DAOWorkshop {
    public static boolean save(Connection c, TOWorkshops t) throws Exception{
        StringBuilder sql = new StringBuilder();
        if(t.getId() < 1){
            sql.append("insert into workshops (nome, descricao, data)"
                + "VALUES ("
                    + "\""+t.getNome()+"\", "
                    + "\""+t.getDescricao()+"\", "
                    + "\""+t.getData()+"\")");
        }
        else{
            sql.append("update workshops "
                + "SET nome = \""+t.getNome()+"\","
                        + " descricao =\"" + t.getDescricao()+ "\","
                        + " data =\"" + t.getData()+ "\","
                                + "WHERE id = " + t.getId());
        }
        
        return Data.executeUpdate(c, sql.toString()) > 0;
    }
    
    public static boolean delete(Connection c, int id) throws Exception{
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("delete from workshops WHERE id = " + id);
        
        return Data.executeUpdate(c, sql.toString()) > 0;
    }
    
    public static TOWorkshops getOne (Connection c, int id) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from workshops where id = " + id);
                
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOWorkshops t = new TOWorkshops();
                t.setId(res.getInt("id"));
                t.setNome(res.getString("nome"));
                t.setDescricao(res.getString("descricao"));
                t.setData(res.getString("data"));
                
                return t;
            }
        }
        catch(Exception e){
            return null;
        }
        
        return null;
    }
    
    public static List<TOWorkshops> getAll (Connection c) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from workshops");
        
        List<TOWorkshops> l = new ArrayList<>();
        
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOWorkshops t = new TOWorkshops();
                t.setId(res.getInt("id"));
                t.setNome(res.getString("nome"));
                t.setDescricao(res.getString("descricao"));
                t.setData(res.getString("data"));
                
                l.add(t);
            }
        }
        catch(Exception e){
            return null;
        }
        
        return l;
    }
}
