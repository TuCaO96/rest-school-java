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
import to.TOCursos;
import to.TOEnemCortes;

/**
 *
 * @author 71500286
 */
public class DAOEnemCortes {
    public static boolean save(Connection c, TOEnemCortes t) throws Exception{
        StringBuilder sql = new StringBuilder();
        if(t.getId() < 1){
            sql.append("insert into enem_cortes (curso, nota_maxima, nota_minima, ano)"
                + "VALUES ("
                    + "\""+t.getCurso()+"\", "
                    + "\""+t.getNota_maxima()+"\", "
                    + "\""+t.getNota_minima()+"\", "
                        + "\"" + t.getAno()+ "\")");
        }
        else{
            sql.append("update enem_cortes "
                + "SET curso = \""+t.getCurso()+"\","
                        + " ano =\"" + t.getAno()+ "\","
                        + " nota_maxima =\"" + t.getNota_maxima()+ "\","
                        + " nota_minima =\"" + t.getNota_minima()+ "\","
                                + "WHERE id = " + t.getId());
        }
        
        return Data.executeUpdate(c, sql.toString()) > 0;
    }
    
    public static boolean delete(Connection c, int id) throws Exception{
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("delete from enem_cortes WHERE id = " + id);
        
        return Data.executeUpdate(c, sql.toString()) > 0;
    }
    
    public static TOEnemCortes getOne (Connection c, int id) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from enem_cortes where id = " + id);
                
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOEnemCortes t = new TOEnemCortes();
                t.setId(res.getInt("id"));
                t.setCurso(res.getString("curso"));
                t.setAno(res.getInt("area"));
                t.setNota_maxima(res.getInt("nota_maxima"));
                t.setNota_minima(res.getInt("nota_minima"));
                
                return t;
            }
        }
        catch(Exception e){
            return null;
        }
        
        return null;
    }
    
    public static List<TOEnemCortes> getAll (Connection c) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from enem_cortes");
        
        List<TOEnemCortes> l = new ArrayList<>();
        
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOEnemCortes t = new TOEnemCortes();
                t.setId(res.getInt("id"));
                t.setCurso(res.getString("curso"));
                t.setAno(res.getInt("area"));
                t.setNota_maxima(res.getInt("nota_maxima"));
                t.setNota_minima(res.getInt("nota_minima"));
                
                l.add(t);
            }
        }
        catch(Exception e){
            return null;
        }
        
        return l;
    }
}
