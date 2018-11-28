/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.DAOUsuario;
import fw.Data;
import fw.DateTime;
import fw.Guid;
import java.sql.Connection;
import java.util.List;
import to.TOUsuario;

/**
 *
 * @author 71500286
 */
public class BOUsuario {
    public static boolean login(String email, String senha) throws Exception{
        try (Connection c = Data.openConnection()){
            
            if(DAOUsuario.login(c, email, senha)){
                TOUsuario t = new TOUsuario();
                t.setEmail(email);
                t.setSenha(senha);
                t.setToken(Guid.getString());
                
                DateTime d = DateTime.now();
                d.addHour(5);
                
                t.setExpiracao(d.getTimestamp());
                
                return DAOUsuario.atualizaToken(c, t);
            }
            
            return false;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static boolean save(TOUsuario usuario) throws Exception{
        try (Connection c = Data.openConnection()){
            return DAOUsuario.save(c, usuario);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static boolean delete(int id) throws Exception{
        try (Connection c = Data.openConnection()){
            return DAOUsuario.delete(c, id);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static TOUsuario getOne(int id) throws Exception{
        try (Connection c = Data.openConnection()){
            return DAOUsuario.getOne(c, id);
        }
    }
    
    public static TOUsuario getOneByEmail(String email) throws Exception{
        try (Connection c = Data.openConnection()){
            return DAOUsuario.getByEmail(c, email);
        }
    }
    
    public static List<TOUsuario> getAll() throws Exception{
        try (Connection c = Data.openConnection()){
            return DAOUsuario.getAll(c);
        }
    }
}
