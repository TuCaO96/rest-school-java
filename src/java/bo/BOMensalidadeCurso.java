/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.DAOMensalidadeCurso;
import fw.Data;
import java.sql.Connection;
import java.util.List;
import to.TOMensalidadeCurso;

/**
 *
 * @author 71500430
 */
public class BOMensalidadeCurso {
    
    public static void inserir(TOMensalidadeCurso t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOMensalidadeCurso.save(c, t);
        }
    }

    public static void alterar(TOMensalidadeCurso t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOMensalidadeCurso.save(c, t);
        }
    }

    public static boolean excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOMensalidadeCurso.delete(c, id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static TOMensalidadeCurso obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOMensalidadeCurso.getOne(c, id);
        }
    }

    public static List<TOMensalidadeCurso> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOMensalidadeCurso.getAll(c);
        }
    }
    
}
