package com.feechan.imcare.entity;

/**
 * Created by Feechan on 9/4/2017.
 */

public class Artikel {
    private int noartikel;
    private String contentartikel;
    private String video;
    private int kdpenyakit;

    public Artikel(int noartikel, String contentartikel, String video, int kdpenyakit) {
        this.noartikel = noartikel;
        this.contentartikel = contentartikel;
        this.video = video;
        this.kdpenyakit = kdpenyakit;
    }

    public int getNoartikel() {
        return noartikel;
    }

    public void setNoartikel(int noartikel) {
        this.noartikel = noartikel;
    }

    public String getContentartikel() {
        return contentartikel;
    }

    public void setContentartikel(String contentartikel) {
        this.contentartikel = contentartikel;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getKdpenyakit() {
        return kdpenyakit;
    }

    public void setKdpenyakit(int kdpenyakit) {
        this.kdpenyakit = kdpenyakit;
    }
}
