package com.smartresidesolutions.model;

import java.io.Serializable;

public class UserMenu implements Serializable {

    String menuName;
    int sequence;
    String icon;
    int menuId;


    public UserMenu(String menuName, int sequence, String icon, int menuId) {
        this.menuName = menuName;
        this.sequence = sequence;
        this.icon = icon;
        this.menuId = menuId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getSequence() {
        return sequence;
    }

    public String getIcon() {
        return icon;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
