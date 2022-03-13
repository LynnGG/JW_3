/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Account.signin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trant
 */
public class SignIn {

    ManagerConnect kn = new ManagerConnect();

    public List<signin> layDuLieu() throws SQLException {
        try {
            Connection con = kn.connectSQL();
            String sql = "select SDT,matKhau from THONGTINTK ";
            ResultSet rs = null;
            Statement stm = con.createStatement();
            rs = stm.executeQuery(sql);
            List<signin> layDL = new ArrayList<>();

            while (rs.next()) {
                signin dn = new signin(rs.getString(1), rs.getString(2));
                layDL.add(dn);
            }

            return layDL;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public signin kiemTraTK(signin dn) throws SQLException {
        List<signin> layDL = new ArrayList<>();
        layDL = layDuLieu();
        for (signin s : layDL) {
            if (dn.getSoDienThoai().equals(s.getSoDienThoai()) && dn.getMatKhau().equals(s.getMatKhau())) {
                return s;
            }
        }
        return null;
    }
}
