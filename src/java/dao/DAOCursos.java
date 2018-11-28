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

/**
 *
 * @author 71500286
 */
public class DAOCursos {
    public static boolean save(Connection c, TOCursos t) throws Exception{
        StringBuilder sql = new StringBuilder();
        if(t.getId() < 1){
            sql.append("insert into cursos (nome, area, base_salario, pos_rank)"
                + "VALUES ("
                    + "\""+t.getNome()+"\", "
                    + "\""+t.getArea()+"\", "
                    + "\""+t.getBase_salarial()+"\", "
                        + "\"" + t.getPos_rank()+ "\")");
        }
        else{
            sql.append("update cursos "
                + "SET name = \""+t.getNome()+"\","
                        + " area =\"" + t.getArea()+ "\","
                        + " base_salarial =\"" + t.getBase_salarial()+ "\","
                        + " pos_rank =\"" + t.getPos_rank()+ "\","
                                + "WHERE id = " + t.getId());
        }
        
        return Data.executeUpdate(c, sql.toString()) > 0;
    }
    
    public static boolean delete(Connection c, int id) throws Exception{
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("delete from cursos WHERE id = " + id);
        
        return Data.executeUpdate(c, sql.toString()) > 0;
    }
    
    public static TOCursos getOne (Connection c, int id) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from cursos where id = " + id);
                
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOCursos t = new TOCursos();
                t.setId(res.getInt("id"));
                t.setNome(res.getString("name"));
                t.setArea(res.getString("area"));
                t.setBase_salarial(res.getInt("base_salarial"));
                t.setPos_rank(res.getInt("pos_rank"));
                
                return t;
            }
        }
        catch(Exception e){
            return null;
        }
        
        return null;
    }
    
    public static List<TOCursos> getAll (Connection c) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from cursos");
        
        List<TOCursos> l = new ArrayList<>();
        
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOCursos t = new TOCursos();
                t.setId(res.getInt("id"));
                t.setNome(res.getString("name"));
                t.setArea(res.getString("area"));
                t.setBase_salarial(res.getInt("base_salarial"));
                t.setPos_rank(res.getInt("pos_rank"));
                
                l.add(t);
            }
        }
        catch(Exception e){
            return null;
        }
        
        return l;
    }
}
