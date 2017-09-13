package com.feechan.imcare.entity;

import java.io.Serializable;

/**
 * Created by Feechan on 9/12/2017.
 */

public class Rs implements Serializable{
    private int idrs;
    private String kdrs;
    private String nmrs;
    private String almt;
    private String kotars;
    private String kdposrs;
    private String kelurahanrs;
    private String kecamatanrs;
    private String telprs;
    private String faxrs;
    private String webrs;
    private String humasrs;
    private double latitude;
    private double longitude;
    private int kdpenyakit;

    public Rs(int idrs, String kdrs, String nmrs, String almt, String kotars, String kdposrs, String kelurahanrs, String kecamatanrs, String telprs, String faxrs, String webrs, String humasrs, double latitude, double longitude, int kdpenyakit) {
        this.idrs = idrs;
        this.kdrs = kdrs;
        this.nmrs = nmrs;
        this.almt = almt;
        this.kotars = kotars;
        this.kdposrs = kdposrs;
        this.kelurahanrs = kelurahanrs;
        this.kecamatanrs = kecamatanrs;
        this.telprs = telprs;
        this.faxrs = faxrs;
        this.webrs = webrs;
        this.humasrs = humasrs;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kdpenyakit = kdpenyakit;
    }

    public int getIdrs() {
        return idrs;
    }

    public void setIdrs(int idrs) {
        this.idrs = idrs;
    }

    public String getKdrs() {
        return kdrs;
    }

    public void setKdrs(String kdrs) {
        this.kdrs = kdrs;
    }

    public String getNmrs() {
        return nmrs;
    }

    public void setNmrs(String nmrs) {
        this.nmrs = nmrs;
    }

    public String getAlmt() {
        return almt;
    }

    public void setAlmt(String almt) {
        this.almt = almt;
    }

    public String getKotars() {
        return kotars;
    }

    public void setKotars(String kotars) {
        this.kotars = kotars;
    }

    public String getKdposrs() {
        return kdposrs;
    }

    public void setKdposrs(String kdposrs) {
        this.kdposrs = kdposrs;
    }

    public String getKelurahanrs() {
        return kelurahanrs;
    }

    public void setKelurahanrs(String kelurahanrs) {
        this.kelurahanrs = kelurahanrs;
    }

    public String getKecamatanrs() {
        return kecamatanrs;
    }

    public void setKecamatanrs(String kecamatanrs) {
        this.kecamatanrs = kecamatanrs;
    }

    public String getTelprs() {
        return telprs;
    }

    public void setTelprs(String telprs) {
        this.telprs = telprs;
    }

    public String getFaxrs() {
        return faxrs;
    }

    public void setFaxrs(String faxrs) {
        this.faxrs = faxrs;
    }

    public String getWebrs() {
        return webrs;
    }

    public void setWebrs(String webrs) {
        this.webrs = webrs;
    }

    public String getHumasrs() {
        return humasrs;
    }

    public void setHumasrs(String humasrs) {
        this.humasrs = humasrs;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getKdpenyakit() {
        return kdpenyakit;
    }

    public void setKdpenyakit(int kdpenyakit) {
        this.kdpenyakit = kdpenyakit;
    }
}
