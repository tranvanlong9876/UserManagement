/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtv.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SearchDTO implements Serializable {
    private List<UserDetailDTO> listUser;
    private List<RoleDTO> listRole;
    private String searchContent;
    private int searchRole;

    public SearchDTO() {
    }

    public SearchDTO(List<UserDetailDTO> listUser, List<RoleDTO> listRole, String searchContent, int searchRole) {
        this.listUser = listUser;
        this.listRole = listRole;
        this.searchContent = searchContent;
        this.searchRole = searchRole;
    }

    public List<RoleDTO> getListRole() {
        return listRole;
    }

    public void setListRole(List<RoleDTO> listRole) {
        this.listRole = listRole;
    }

    public List<UserDetailDTO> getListUser() {
        return listUser;
    }

    public void setListUser(List<UserDetailDTO> listUser) {
        this.listUser = listUser;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public int getSearchRole() {
        return searchRole;
    }

    public void setSearchRole(int searchRole) {
        this.searchRole = searchRole;
    }
    
    
    
}
