/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtv.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import longtv.dtos.UserDetailDTO;
import longtv.util.DatabaseConnection;

/**
 *
 * @author Admin
 */
public class SearchDAO implements Serializable {
    Connection conn;
    PreparedStatement psm;
    ResultSet rs;
    
    private void closeConnection() throws Exception {
        if(rs != null) {
            rs.close();
        }
        if(psm != null) {
            psm.close();
        }
        if(conn != null) {
            conn.close();
        }
    }
    
    public List<UserDetailDTO> loadAllUser(String searchContent, int searchRole) throws Exception {
        List<UserDetailDTO> result = new ArrayList<>();
        UserDetailDTO dto;
        try {
            String sql = "SELECT Username, FullName, R.RoleName, Email, Phone, Photo, P.Value\n" +
                         "FROM Account A JOIN Role R\n" +
                         "ON A.Role = R.IDRole\n" +
                         "FULL OUTER JOIN Promotion P\n" +
                         "ON A.Username = P.UserAdded\n" +
                         "WHERE (FullName LIKE ? OR Username LIKE ?) AND A.Role LIKE ? AND Status = 1";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            psm.setString(1, "%" + searchContent + "%");
            psm.setString(2, "%" + searchContent + "%");
            psm.setString(3, "%" + (searchRole > 0 ? searchRole : "") + "%");
            rs = psm.executeQuery();
            while(rs.next()) {
                String username = rs.getString("Username");
                String fullname = rs.getString("FullName");
                String rolename = rs.getString("RoleName");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                String photoLink = rs.getString("Photo");
                int promotionValue = rs.getInt("Value");
                dto = new UserDetailDTO(username, fullname, rolename, email, phone, photoLink);
                dto.setPromotionValue(promotionValue);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public UserDetailDTO loadUserInfo(String userID) throws Exception {
        UserDetailDTO dto = null;
        try {
            String sql = "SELECT Username, FullName, R.RoleName, Email, Phone, Photo, P.Value\n" +
                         "FROM Account A JOIN Role R\n" +
                         "ON A.Role = R.IDRole\n" +
                         "FULL OUTER JOIN Promotion P\n" +
                         "ON A.Username = P.UserAdded\n" +
                         "WHERE Username = ? AND Status = 1";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            psm.setString(1, userID);
            rs = psm.executeQuery();
            if(rs.next()) {
                String username = rs.getString("Username");
                String fullname = rs.getString("FullName");
                String rolename = rs.getString("RoleName");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                String photoLink = rs.getString("Photo");
                int promotionValue = rs.getInt("Value");
                dto = new UserDetailDTO(username, fullname, rolename, email, phone, photoLink);
                dto.setPromotionValue(promotionValue);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public UserDetailDTO loadUserForUpdate(String userID) throws Exception {
        UserDetailDTO dto = null;
        try {
            String sql = "SELECT Username, FullName, Role, Email, Phone, Photo\n" +
                         "FROM Account\n" +
                         "WHERE Username = ? AND Status = 1";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            psm.setString(1, userID);
            rs = psm.executeQuery();
            if(rs.next()) {
                String username = rs.getString("Username");
                String fullname = rs.getString("FullName");
                String role = rs.getString("Role");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                String photoLink = rs.getString("Photo");
                dto = new UserDetailDTO(username, fullname, role, email, phone, photoLink);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
}
