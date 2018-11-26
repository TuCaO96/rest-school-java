/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.DAOWorkshop;
import fw.Data;
import java.sql.Connection;
import java.util.List;
import to.TOWorkshops;

/**
 *
 * @author 71500430
 */
public class BOWorkshop {
    
    public static void inserir(List<TOWorkshops> l) throws Exception {
        try (Connection c = Data.openConnection()) {
            for (TOWorkshops t : l) {
                DAOWorkshop.save(c, t);
            }
        }
    }

    public static void inserir(TOWorkshops t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOWorkshop.save(c, t);
        }
    }

    public static void alterar(TOWorkshops t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOWorkshop.save(c, t);
        }
    }

    public static void excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOWorkshop.delete(c, id);
        }
    }

    public static TOWorkshops obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOWorkshop.getOne(c, id);
        }
    }

    public static List<TOWorkshops> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOWorkshop.getAll(c);
        }
    }
    
}
