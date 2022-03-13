/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Books.Bao;
import Books.Bao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lynn
 */
public class SBao {
    
    ManagerConnect mc = new ManagerConnect();

    public List<Bao> getDataBao() {
        try {
            String sql = "Select * from bao";
            Connection con = mc.connectSQL();
            ResultSet rs = null;
            Statement stm = con.createStatement();
            rs = stm.executeQuery(sql);
            List<Bao> listBao = new ArrayList<>();
            while (rs.next()) {
                Bao s = new Bao(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                listBao.add(s);
            }
            return listBao;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addBao(Bao b) {
        String Sql = "insert into bao values(?, ?, ?, ?)";
        Connection con;
        PreparedStatement pstmt = null;
        try {
            con = mc.connectSQL();
            pstmt = con.prepareCall(Sql);
            pstmt.setString(1,b.getMaTaiLieu());
            pstmt.setString(2,b.getTenNhaXB());
            pstmt.setString(3,b.getSoBanPhatHanh());
            pstmt.setString(4,b.getNgayPhatHanh());

            pstmt.execute();
            JOptionPane.showMessageDialog(null, "ADD Done ,Pls Refresh");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delBao(Bao b) {
        String Sql = "delete from bao where maTaiLieu = ?";
        Connection con;
        PreparedStatement pstmt = null;
        try {
            con = mc.connectSQL();
            pstmt = con.prepareCall(Sql);
            pstmt.setString(1,b.getMaTaiLieu());
            pstmt.execute();
            JOptionPane.showMessageDialog(null, "DEL Done ,Pls Refresh");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Bao findBao(Bao fb) {
        List<Bao> listBao = new ArrayList<>();
        listBao = getDataBao();
        for (Bao s : listBao) {
            if (fb.getMaTaiLieu().equals(s.getMaTaiLieu())) {
                return s;
            }
        }
        return null;
    }
}
