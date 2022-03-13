/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Books.Sach;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lynn
 */
public class SSach {

    ManagerConnect mc = new ManagerConnect();

    public List<Sach> getDataSach() {
        try {
            String sql = "Select * from sach";
            Connection con = mc.connectSQL();
            ResultSet rs = null;
            Statement stm = con.createStatement();
            rs = stm.executeQuery(sql);
            List<Sach> listSach = new ArrayList<>();
            while (rs.next()) {
                Sach s = new Sach(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                listSach.add(s);
            }
            return listSach;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addSach(Sach s) {
        String Sql = "insert into sach values(?, ?, ?, ?,?)";
        Connection con;
        PreparedStatement pstmt = null;
        try {
            con = mc.connectSQL();
            pstmt = con.prepareCall(Sql);
            pstmt.setString(1, s.getMaTaiLieu());
            pstmt.setString(2, s.getTenNhaXB());
            pstmt.setString(3, s.getSoBanPhatHanh());
            pstmt.setString(4, s.getTenTacGia());
            pstmt.setString(5, s.getSoTrang());

            pstmt.execute();
            JOptionPane.showMessageDialog(null, "ADD Done ,Pls Refresh");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delSach(Sach s) {
        String Sql = "delete from sach where maTaiLieu = ?";
        Connection con;
        PreparedStatement pstmt = null;
        try {
            con = mc.connectSQL();
            pstmt = con.prepareCall(Sql);
            pstmt.setString(1, s.getMaTaiLieu());
            pstmt.execute();
            JOptionPane.showMessageDialog(null, "DEL Done ,Pls Refresh");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Sach findSach(Sach fs) {
        List<Sach> listSach = new ArrayList<>();
        listSach = getDataSach();
        for (Sach s : listSach) {
            if (fs.getMaTaiLieu().equals(s.getMaTaiLieu())) {
                return s;
            }
        }
        return null;
    }
}
