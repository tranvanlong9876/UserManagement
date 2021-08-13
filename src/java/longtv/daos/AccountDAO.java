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
import longtv.dtos.AccountDTO;
import longtv.dtos.RegisterDTO;
import longtv.dtos.RoleDTO;
import longtv.dtos.UpdateDTO;
import longtv.util.DatabaseConnection;

/**
 *
 * @author Admin
 */
public class AccountDAO implements Serializable {
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
    public AccountDTO checkLogin(String username, String password) throws Exception {
        AccountDTO account = null;
        try {
            String sql = "SELECT Username, FullName, R.RoleName, Photo\n" +
                         "FROM Account A JOIN Role R\n" +
                         "ON A.Role = R.IDRole\n" +
                         "WHERE Username = ? AND Password = ? AND Status = 1";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            psm.setString(1, username);
            psm.setString(2, password);
            rs = psm.executeQuery();
            if(rs.next()) {
                String photo = rs.getString("Photo");
                String userID = rs.getString("Username");
                String fullname = rs.getString("FullName");
                String rolename = rs.getString("RoleName");
                account = new AccountDTO(userID, fullname, rolename);
                account.setPhoto(photo);
            }
        } finally {
            closeConnection();
        }
        return account;
    }
    
    public boolean checkRightOldPassword(String username, String password) throws Exception {
        boolean check = false;
        try {
            String sql = "SELECT Username\n" +
                         "FROM Account\n" +
                         "WHERE Username = ? AND Password = ? AND Status = 1";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            psm.setString(1, username);
            psm.setString(2, password);
            rs = psm.executeQuery();
            if(rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean checkExistUsername(String username) throws Exception {
        boolean check = false;
        try {
            String sql = "SELECT Username "
                       + "FROM Account "
                       + "WHERE Username = ?";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            psm.setString(1, username);
            rs = psm.executeQuery();
            if(rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean checkExistEmail(String email, String username) throws Exception {
        boolean check = false;
        try {
            String sql = "SELECT Username "
                       + "FROM Account "
                       + "WHERE Email = ? AND Username != ?";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            psm.setString(1, email);
            psm.setString(2, username);
            rs = psm.executeQuery();
            if(rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean checkExistPhone(String phone, String username) throws Exception {
        boolean check = false;
        try {
            String sql = "SELECT Username "
                       + "FROM Account "
                       + "WHERE Phone = ? AND Username != ?";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            psm.setString(1, phone);
            psm.setString(2, username);
            rs = psm.executeQuery();
            if(rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public List<RoleDTO> loadAllRoles() throws Exception {
        List<RoleDTO> result = new ArrayList<>();
        RoleDTO dto;
        try {
            String sql = "SELECT IDRole, RoleName\n" +
                         "FROM Role";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            rs = psm.executeQuery();
            while(rs.next()) {
                int idRole = rs.getInt("IDRole");
                String roleName = rs.getString("RoleName");
                dto = new RoleDTO(idRole, roleName);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean insertNewAccount(RegisterDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "INSERT Account(Username, Password, FullName, Status, Role, Email, Phone, Photo) "
                       + "VALUES(?,?,?,?,?,?,?,?)";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            psm.setString(1, dto.getUsername());
            psm.setString(2, dto.getPassword());
            psm.setString(3, dto.getFullName());
            psm.setInt(4, 1);
            psm.setInt(5, dto.getRole());
            psm.setString(6, dto.getEmail());
            psm.setString(7, dto.getPhone());
            psm.setString(8, dto.getPhotoLink());
            check = psm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean updateAccountWithPassword(UpdateDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "UPDATE Account SET Password = ?, FullName = ?, Role = ?, Email = ?, Phone = ?, Photo = ? "
                       + "WHERE Username = ?";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            
            psm.setString(1, dto.getNewPassword());
            psm.setString(2, dto.getFullName());
            psm.setInt(3, dto.getRole());
            psm.setString(4, dto.getEmail());
            psm.setString(5, dto.getPhone());
            psm.setString(6, dto.getPhotoLink());
            psm.setString(7, dto.getUsername());
            check = psm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean updateAccountWithoutPassword(UpdateDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "UPDATE Account SET FullName = ?, Role = ?, Email = ?, Phone = ?, Photo = ? "
                       + "WHERE Username = ?";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            psm.setString(1, dto.getFullName());
            psm.setInt(2, dto.getRole());
            psm.setString(3, dto.getEmail());
            psm.setString(4, dto.getPhone());
            psm.setString(5, dto.getPhotoLink());
            psm.setString(6, dto.getUsername());
            check = psm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean deleteAccount(String username) throws Exception {
        boolean check = false;
        try {
            String sql = "UPDATE Account SET Status = 0 "
                       + "WHERE Username = ?";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            psm.setString(1, username);
            check = psm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
}
