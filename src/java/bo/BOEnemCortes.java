/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import fw.Data;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author 71500430
 */
public class BOEnemCortes {
    
    public static void inserir(List<TOEnemCortes> l) throws Exception {
        try (Connection c = Data.openConnection()) {
            for (TOEnemCortes t : l) {
                DAOEnemCortes.inserir(c, t);
            }
        }
    }

    public static void inserir(TOEnemCortes t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOEnemCortes.inserir(c, t);
        }
    }

    public static void alterar(TOEnemCortes t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOEnemCortes.alterar(c, t);
        }
    }

    public static void excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOEnemCortes.excluir(c, id);
        }
    }

    public static TOEnemCortes obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOEnemCortes.obter(c, id);
        }
    }

    public static List<TOEnemCortes> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOEnemCortes.lista(c);
        }
    }
    
}
