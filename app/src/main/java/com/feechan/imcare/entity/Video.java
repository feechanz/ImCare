package com.feechan.imcare.entity;

import java.io.Serializable;

/**
 * Created by bcamaster on 05-Sep-17.
 */

public class Video implements Serializable{
    private int novideo;
    private String judulvideo;
    private String urlvideo;
    private int kdpenyakit;

    public Video(int novideo, String judulvideo, String urlvideo, int kdpenyakit) {
        this.novideo = novideo;
        this.judulvideo = judulvideo;
        this.urlvideo = urlvideo;
        this.kdpenyakit = kdpenyakit;
    }

    public int getNovideo() {
        return novideo;
    }

    public void setNovideo(int novideo) {
        this.novideo = novideo;
    }

    public String getJudulvideo() {
        return judulvideo;
    }

    public void setJudulvideo(String judulvideo) {
        this.judulvideo = judulvideo;
    }

    public String getUrlvideo() {
        return urlvideo;
    }

    public void setUrlvideo(String urlvideo) {
        this.urlvideo = urlvideo;
    }

    public int getKdpenyakit() {
        return kdpenyakit;
    }

    public void setKdpenyakit(int kdpenyakit) {
        this.kdpenyakit = kdpenyakit;
    }
}
