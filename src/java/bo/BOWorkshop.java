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
public class BOWorkshop {
    
    public static void inserir(List<TOWorkshop> l) throws Exception {
        try (Connection c = Data.openConnection()) {
            for (TOWorkshop t : l) {
                DAOWorkshop.inserir(c, t);
            }
        }
    }

    public static void inserir(TOWorkshop t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOWorkshop.inserir(c, t);
        }
    }

    public static void alterar(TOWorkshop t) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOWorkshop.alterar(c, t);
        }
    }

    public static void excluir(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            DAOProfessores.excluir(c, id);
        }
    }

    public static TOWorkshops obter(int id) throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOWorkshop.obter(c, id);
        }
    }

    public static List<TOWorkshop> lista() throws Exception {
        try (Connection c = Data.openConnection()) {
            return DAOWorkshop.lista(c);
        }
    }
    
}
