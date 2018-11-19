/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author 71500286
 */
public class Data {
    public static Connection openConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/trabalhosd?"
                + "user=root&password=");
    }

    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection : " + e.getMessage());
            }
        }
    }

    public static ResultSet executeQuery(Connection conn, String query) throws SQLException {
        Statement sta = conn.createStatement();
        ResultSet rs = null;
        try {
            rs = sta.executeQuery(query);
        } catch (Exception err) {
            //Log.logar(err.getMessage(), Log.TYPE_INFORMATION);
        }
        return rs;
    }

    public static int executeUpdate(Connection conn, String query, Object... p) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        // Recebe os parâmetros da Query
        for (int i = 1; i <= p.length; i++) {
            pstmt.setObject(i, retiraInject(p[i - 1]));
        }

        return pstmt.executeUpdate();
    }

    public static int executeUpdate(Connection conn, String query, Object p) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setObject(1, retiraInject(p));

        return pstmt.executeUpdate();
    }

    public static int executeUpdate(Connection conn, String query, List<Object> p) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        // Recebe os parâmetros da Query
        int i = 1;
        for (Object o : p) {
            pstmt.setObject(i++, retiraInject(o));
        }

        return pstmt.executeUpdate();
    }

    public static int executeUpdate(Connection conn, String query) throws SQLException {
        Statement stm = conn.createStatement();
        return stm.executeUpdate(query);
    }

    public static ResultSet executeQuery(Connection conn, String query, Object... p) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        // Recebe os parâmetros da Query
        for (int i = 1; i <= p.length; i++) {
            pstmt.setObject(i, retiraInject(p[i - 1]));
        }

        return pstmt.executeQuery();
    }

    public static ResultSet executeQuery(Connection conn, String query, Object p) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setObject(1, retiraInject(p));

        return pstmt.executeQuery();
    }

    public static ResultSet executeQuery(Connection conn, String query, List<Object> p) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(query);
        // Recebe os parâmetros da Query
        int i = 1;
        for (Object o : p) {
            pstmt.setObject(i++, retiraInject(o));
        }

        return pstmt.executeQuery();
    }

    public static Object retiraInject(Object o) {
        if (o != null && o.getClass().getCanonicalName().contains("String")) {
            String s = (String) o;
            o = s.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        }
        return o;
    }

    public static void closeResultSet(ResultSet rs) throws Exception {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new Exception("Error closing ResultSet : " + e.getMessage());
            }
        }
    }
}
