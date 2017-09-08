package com.feechan.imcare.entity;

import java.io.Serializable;

/**
 * Created by Feechan on 9/4/2017.
 */

public class Artikel implements Serializable{
    private int noartikel;
    private String judulartikel;
    private String contentartikel;
    private int kdpenyakit;

    public Artikel(int noartikel, String judulartikel, String contentartikel, int kdpenyakit) {
        this.noartikel = noartikel;
        this.judulartikel = judulartikel;
        this.contentartikel = contentartikel;
        this.kdpenyakit = kdpenyakit;
    }

    public int getNoartikel() {
        return noartikel;
    }

    public void setNoartikel(int noartikel) {
        this.noartikel = noartikel;
    }

    public String getJudulartikel() {
        return judulartikel;
    }

    public void setJudulartikel(String judulartikel) {
        this.judulartikel = judulartikel;
    }

    public String getContentartikel() {
        return contentartikel;
    }

    public void setContentartikel(String contentartikel) {
        this.contentartikel = contentartikel;
    }

    public int getKdpenyakit() {
        return kdpenyakit;
    }

    public void setKdpenyakit(int kdpenyakit) {
        this.kdpenyakit = kdpenyakit;
    }
}
