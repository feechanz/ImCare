package com.feechan.imcare.entity;

import java.io.Serializable;

/**
 * Created by Feechan on 8/21/2017.
 */

public class Penyakit implements Serializable {
    private int kdpenyakit;
    private String nmpenyakit;
    private String despenyakit;
    private String fketurunan;
    private String menular;
    private String kronis;
    private String image_url;
    private String video_url;

    public Penyakit() {
    }

    public Penyakit(int kdpenyakit, String nmpenyakit, String despenyakit, String fketurunan, String menular, String kronis, String image_url, String video_url) {
        this.kdpenyakit = kdpenyakit;
        this.nmpenyakit = nmpenyakit;
        this.despenyakit = despenyakit;
        this.fketurunan = fketurunan;
        this.menular = menular;
        this.kronis = kronis;
        this.image_url = image_url;
        this.video_url = video_url;
    }

    public int getKdpenyakit() {
        return kdpenyakit;
    }

    public void setKdpenyakit(int kdpenyakit) {
        this.kdpenyakit = kdpenyakit;
    }

    public String getNmpenyakit() {
        return nmpenyakit;
    }

    public void setNmpenyakit(String nmpenyakit) {
        this.nmpenyakit = nmpenyakit;
    }

    public String getDespenyakit() {
        return despenyakit;
    }

    public void setDespenyakit(String despenyakit) {
        this.despenyakit = despenyakit;
    }

    public String getFketurunan() {
        return fketurunan;
    }

    public void setFketurunan(String fketurunan) {
        this.fketurunan = fketurunan;
    }

    public String getMenular() {
        return menular;
    }

    public void setMenular(String menular) {
        this.menular = menular;
    }

    public String getKronis() {
        return kronis;
    }

    public void setKronis(String kronis) {
        this.kronis = kronis;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
