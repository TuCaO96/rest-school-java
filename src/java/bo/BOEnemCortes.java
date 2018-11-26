/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.DAOEnemCortes;
import fw.Data;
import java.sql.Connection;
import java.util.List;
import to.TOEnemCortes;

/**
 *
 * @author 71500430
 */
public class BOEnemCortes {
    
    public static void inserir(List<TOEnemCortes> l) throws Exception {
        try (Connection c = Data.openConnection()) {
            for (TOEnemCortes t : l) {
                DAOEnemCortes.save(c, t);
            }
        }
    }

    public static void inserir(TOEnemCortes t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOEnemCortes.save(c, t);
        }
    }

    public static void alterar(TOEnemCortes t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOEnemCortes.save(c, t);
        }
    }

    public static void excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOEnemCortes.delete(c, id);
        }
    }

    public static TOEnemCortes obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOEnemCortes.getOne(c, id);
        }
    }

    public static List<TOEnemCortes> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOEnemCortes.getAll(c);
        }
    }
    
}
