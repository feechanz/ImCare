package com.feechan.imcare.entity;

import java.io.Serializable;

/**
 * Created by bcamaster on 05-Sep-17.
 */

public class Video implements Serializable{
    private int novideo;
    private String judulvideo;
    private String contentvideo;
    private int kdpenyakit;

    public Video(int novideo, String judulvideo, String contentvideo, int kdpenyakit) {
        this.novideo = novideo;
        this.judulvideo = judulvideo;
        this.contentvideo = contentvideo;
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

    public String getContentvideo() {
        return contentvideo;
    }

    public void setContentvideo(String contentvideo) {
        this.contentvideo = contentvideo;
    }

    public int getKdpenyakit() {
        return kdpenyakit;
    }

    public void setKdpenyakit(int kdpenyakit) {
        this.kdpenyakit = kdpenyakit;
    }
}
