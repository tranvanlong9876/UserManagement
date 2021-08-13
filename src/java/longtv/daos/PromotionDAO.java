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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import longtv.dtos.PromotionDTO;
import longtv.dtos.PromotionHistoryDTO;
import longtv.util.DatabaseConnection;

/**
 *
 * @author Admin
 */
public class PromotionDAO implements Serializable {
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
    
    public boolean submitPromotionToDatabase(PromotionDTO dto, String userAssign, Timestamp time) throws Exception {
        boolean check = false;
        try {
            String sql = "INSERT INTO Promotion(UserAdded, UserAssign, Value, TimeOfAssign) VALUES(?,?,?,?)";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            psm.setString(1, dto.getUserID());
            psm.setString(2, userAssign);
            psm.setInt(3, dto.getPromotionValue());
            psm.setTimestamp(4, time);
            check = psm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public List<PromotionHistoryDTO> loadAllPromotionHistory() throws Exception {
        List<PromotionHistoryDTO> result = new ArrayList<>();
        PromotionHistoryDTO dto;
        try {
            String sql = "SELECT P.UserAdded, P.UserAssign, CONVERT(nvarchar, P.TimeOfAssign, 120) AS TimeOfAssign, P.Value, A.Photo AS photoOfUserAdded, A.FullName AS nameOfUserAdded, B.Photo AS photoOfUserAssign, B.FullName AS nameOfUserAssign\n" +
                         "FROM Promotion P JOIN Account A\n" +
                         "ON P.UserAdded = A.Username\n" +
                         "JOIN Account B\n" +
                         "ON P.UserAssign = B.Username\n" +
                         "ORDER BY TimeOfAssign DESC";
            conn = DatabaseConnection.makeConnection();
            psm = conn.prepareStatement(sql);
            rs = psm.executeQuery();
            while(rs.next()) {
                String userAdded = rs.getString("UserAdded");
                String photoUserAdded = rs.getString("photoOfUserAdded");
                String nameOfUserAdded = rs.getString("nameOfUserAdded");
                String userAssign = rs.getString("UserAssign");
                String photoUserAssign = rs.getString("photoOfUserAssign");
                String nameOfUserAssign = rs.getString("nameOfUserAssign");
                int promotionValue = rs.getInt("Value");
                String assignmentTime = rs.getString("TimeOfAssign");
                dto = new PromotionHistoryDTO(userAdded, userAssign, photoUserAdded, photoUserAssign, nameOfUserAdded, nameOfUserAssign, assignmentTime, promotionValue);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
}
