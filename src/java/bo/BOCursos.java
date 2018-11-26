/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.DAOCursos;
import fw.Data;
import java.sql.Connection;
import java.util.List;
import to.TOCursos;

/**
 *
 * @author 71500430
 */
public class BOCursos {
    
    /*public static boolean inserir(List<TOCursos> l) throws Exception {
        try (Connection c = Data.openConnection()) {
            for (TOCursos t : l) {
                DAOCursos.save(c, t);
            }
        }
    }*/

    public static boolean inserir(TOCursos t) throws Exception {
        try (Connection c = Data.openConnection()) {
            if(DAOCursos.save(c, t)){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean alterar(TOCursos t) throws Exception {
        try (Connection c = Data.openConnection()) {
            if(DAOCursos.save(c, t)){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            if(DAOCursos.delete(c, id)){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static TOCursos obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOCursos.getOne(c, id);
        }
    }

    public static List<TOCursos> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOCursos.getAll(c);
        }
    }
    
}
