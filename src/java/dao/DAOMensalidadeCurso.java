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
import to.TOMensalidadeCurso;
import to.TOUsuario;

/**
 *
 * @author 71500430
 */
public class DAOMensalidadeCurso {
   
    public static boolean save(Connection c, TOMensalidadeCurso mensalidade_curso) throws Exception{
        StringBuilder sql = new StringBuilder();
        
        if(mensalidade_curso.getId() < 1){
            sql.append("insert into mensalidade_curso (id_curso, valor, periodo)"
                + "VALUES (\""+mensalidade_curso.getCurso().getId()+"\", \""+mensalidade_curso.getValor()+"\","
                        + "\"" + mensalidade_curso.getPeriodo() + "\")");
        }
        else{
            sql.append("update mensalidade_curso "
                + "SET id_curso = \""+mensalidade_curso.getCurso().getId()+"\", valor = \""+mensalidade_curso.getValor()+"\","
                        + " periodo =\""+mensalidade_curso.getPeriodo()+"\""
                                + "WHERE id = " + mensalidade_curso.getId());
        }
        
        return Data.executeUpdate(c, sql.toString()) > 0;
    }
    
    public static boolean delete(Connection c, int id) throws Exception{
        
        StringBuilder sql = new StringBuilder();
        
        sql.append("delete from mensalidade_curso WHERE id = " + id);
        
        return Data.executeUpdate(c, sql.toString()) > 0;
    }
    
    public static TOMensalidadeCurso getOne (Connection c, int id) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from mensalidade_curso where id = " + id);
                
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOMensalidadeCurso t = new TOMensalidadeCurso();
                t.setId(res.getInt("id"));
                t.setPeriodo(res.getDouble("periodo"));
                t.setValor(res.getDouble("valor"));
                int cursoId = res.getInt("id_curso");
                TOCursos curso = bo.BOCursos.obter(cursoId);
                t.setCurso(curso);
                
                return t;
            }
        }
        catch(Exception e){
            return null;
        }
        
        return null;
    }
    
    public static List<TOMensalidadeCurso> getAll (Connection c) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from mensalidade_curso");
        
        List<TOMensalidadeCurso> l = new ArrayList<>();
        
        try{
            ResultSet res = Data.executeQuery(c, sql.toString());
            
            while(res.next()){
                TOMensalidadeCurso t = new TOMensalidadeCurso();
                t.setId(res.getInt("id"));
                t.setPeriodo(res.getDouble("periodo"));
                t.setValor(res.getDouble("valor"));
                int cursoId = res.getInt("id_curso");
                TOCursos curso = bo.BOCursos.obter(cursoId);
                t.setCurso(curso);
                
                l.add(t);
            }
        }
        catch(Exception e){
            return null;
        }
        
        return l;
    }
}
