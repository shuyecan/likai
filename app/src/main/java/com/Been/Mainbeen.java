package com.Been;

import org.litepal.crud.LitePalSupport;

/**
 * Created by Administrator on 2018/10/30.
 */

public class Mainbeen extends LitePalSupport {
    private   int tian;
    private   int jingdu;
    private   boolean issuccess;

    public boolean isIssuccess() {
        return issuccess;
    }

    public void setIssuccess(boolean issuccess) {
        this.issuccess = issuccess;
    }

    public int getTian() {
        return tian;
    }

    public void setTian(int tian) {
        this.tian = tian;
    }

    public int getJingdu() {
        return jingdu;
    }

    public void setJingdu(int jingdu) {
        this.jingdu = jingdu;
    }

    public Mainbeen(int tian, int jingdu, boolean issuccess) {
        this.tian = tian;
        this.jingdu = jingdu;
        this.issuccess = issuccess;
    }
}
