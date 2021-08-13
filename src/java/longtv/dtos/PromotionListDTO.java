/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtv.dtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class PromotionListDTO implements Serializable {
    private Map<String, PromotionDTO> promotionList;

    public PromotionListDTO() {
        promotionList = new HashMap<>();
    }

    public Map<String, PromotionDTO> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(Map<String, PromotionDTO> promotionList) {
        this.promotionList = promotionList;
    }

    public void addNewList(PromotionDTO dto) {
        this.promotionList.put(dto.getUserID(), dto);
    }
    
    public void updateList(String userId, int value) {
        this.promotionList.get(userId).setPromotionValue(value);
    }
    
    public void removeList(String userId) {
        this.promotionList.remove(userId);
    }
}
