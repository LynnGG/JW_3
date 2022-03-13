/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Books.TapChi;
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
public class STapChi {

    ManagerConnect mc = new ManagerConnect();

    public List<TapChi> getDataTapChi() {
        try {
            String sql = "Select * from tapchi";
            Connection con = mc.connectSQL();
            ResultSet rs = null;
            Statement stm = con.createStatement();
            rs = stm.executeQuery(sql);
            List<TapChi> listTapChi = new ArrayList<>();
            while (rs.next()) {
                TapChi s = new TapChi(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                listTapChi.add(s);
            }
            return listTapChi;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addTapChi(TapChi b) {
        String Sql = "insert into tapchi values(?, ?, ?, ?,?)";
        Connection con;
        PreparedStatement pstmt = null;
        try {
            con = mc.connectSQL();
            pstmt = con.prepareCall(Sql);
            pstmt.setString(1, b.getMaTaiLieu());
            pstmt.setString(2, b.getTenNhaXB());
            pstmt.setString(3, b.getSoBanPhatHanh());
            pstmt.setString(4, b.getSoPhatHanh());
            pstmt.setString(5, b.getThangPhatHanh());

            pstmt.execute();
            JOptionPane.showMessageDialog(null, "ADD Done ,Pls Refresh");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delTapChi(TapChi b) {
        String Sql = "delete from tapchi where maTaiLieu = ?";
        Connection con;
        PreparedStatement pstmt = null;
        try {
            con = mc.connectSQL();
            pstmt = con.prepareCall(Sql);
            pstmt.setString(1, b.getMaTaiLieu());
            pstmt.execute();
            JOptionPane.showMessageDialog(null, "DEL Done ,Pls Refresh");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public TapChi findTapChi(TapChi tc) {
        List<TapChi> listTapChi = new ArrayList<>();
        listTapChi = getDataTapChi();
        for (TapChi s : listTapChi) {
            if (tc.getMaTaiLieu().equals(s.getMaTaiLieu())) {
                return s;
            }
        }
        return null;
    }
}