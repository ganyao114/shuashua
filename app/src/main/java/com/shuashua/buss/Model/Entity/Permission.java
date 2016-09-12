package com.shuashua.buss.Model.Entity;

/**
 * Created by gy939 on 2016/9/12.
 */
public class Permission {

    private boolean isOwner;
    private boolean canCreateShop;
    private boolean canCreateCard;
    private boolean canDistCard;
    private boolean canCreateOrder;

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public boolean isCanCreateShop() {
        return canCreateShop;
    }

    public void setCanCreateShop(boolean canCreateShop) {
        this.canCreateShop = canCreateShop;
    }

    public boolean isCanCreateCard() {
        return canCreateCard;
    }

    public void setCanCreateCard(boolean canCreateCard) {
        this.canCreateCard = canCreateCard;
    }

    public boolean isCanDistCard() {
        return canDistCard;
    }

    public void setCanDistCard(boolean canDistCard) {
        this.canDistCard = canDistCard;
    }

    public boolean isCanCreateOrder() {
        return canCreateOrder;
    }

    public void setCanCreateOrder(boolean canCreateOrder) {
        this.canCreateOrder = canCreateOrder;
    }
}
