package com.feechan.imcare.global;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.feechan.imcare.entity.Artikel;
import com.feechan.imcare.entity.Penyakit;
import com.feechan.imcare.entity.Rs;
import com.feechan.imcare.entity.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feechan on 8/23/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "imcaredb";

    private static final String TABLE_PENYAKIT = "penyakit";

    private static final String KEY_KDPENYAKIT      = "kdpenyakit";
    private static final String KEY_NMPENYAKIT      = "nmpenyakit";
    private static final String KEY_DESPENYAKIT     = "despenyakit";
    private static final String KEY_FKETURUNAN      = "fketurunan";
    private static final String KEY_MENULAR         = "menular";
    private static final String KEY_KRONIS          = "kronis";
    private static final String KEY_IMAGE_URL       = "image_url";
    private static final String KEY_VIDEO_URL       = "video_url";

    private static final String TABLE_VIDEO = "video";
    private static final String KEY_NOVIDEO         = "novideo";
    private static final String KEY_JUDULVIDEO      = "judulvideo";
    private static final String KEY_URLVIDEO        = "urlvideo";

    private static final String TABLE_ARTIKEL = "artikel";
    private static final String KEY_NOARTIKEL       = "noartikel";
    private static final String KEY_JUDULARTIKEL    = "judulartikel";
    private static final String KEY_CONTENTARTIKEL  = "contentartikel";

    private static final String TABLE_RS = "rs";
    private static final String KEY_IDRS            = "idrs";
    private static final String KEY_KDRS            = "kdrs";
    private static final String KEY_NMRS            = "nmrs";
    private static final String KEY_ALMT            = "almt";
    private static final String KEY_KOTARS          = "kotars";
    private static final String KEY_KDPOSRS         = "kdposrs";
    private static final String KEY_KELURAHANRS     = "kelurahanrs";
    private static final String KEY_KECAMATANRS     = "kecamatanrs";
    private static final String KEY_TELPRS          = "telprs";
    private static final String KEY_FAXRS           = "faxrs";
    private static final String KEY_WEBRS           = "webrs";
    private static final String KEY_HUMASRS         = "humasrs";
    private static final String KEY_LATITUDE        = "latitude";
    private static final String KEY_LONGITUDE       = "longitude";


    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PENYAKIT_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE_PENYAKIT + "("
                +KEY_KDPENYAKIT + " INTEGER,"
                +KEY_NMPENYAKIT + " TEXT,"
                +KEY_DESPENYAKIT + " TEXT,"
                +KEY_FKETURUNAN + " TEXT,"
                +KEY_MENULAR + " TEXT,"
                +KEY_KRONIS + " TEXT,"
                +KEY_IMAGE_URL + " TEXT,"
                +KEY_VIDEO_URL + " TEXT" + ")";
        db.execSQL(CREATE_PENYAKIT_TABLE);

        String CREATE_VIDEO_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_VIDEO  + "("
                +KEY_NOVIDEO + " INTEGER,"
                +KEY_JUDULVIDEO + " TEXT,"
                +KEY_URLVIDEO + " TEXT,"
                +KEY_KDPENYAKIT + " INTEGER"+ ")";
        db.execSQL(CREATE_VIDEO_TABLE);

        String CREATE_ARTIKEL_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_ARTIKEL + "("
                +KEY_NOARTIKEL + " INTEGER,"
                +KEY_JUDULARTIKEL + " TEXT,"
                +KEY_CONTENTARTIKEL + " TEXT,"
                + KEY_KDPENYAKIT + " INTEGER"+ ")";
        db.execSQL(CREATE_ARTIKEL_TABLE);

        String CREATE_RS_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_RS+ "("
                +KEY_IDRS + " INTEGER,"
                +KEY_KDRS + " TEXT,"
                +KEY_NMRS + " TEXT,"
                +KEY_ALMT + " TEXT,"
                +KEY_KOTARS + " TEXT,"
                +KEY_KDPOSRS + " TEXT,"
                +KEY_KELURAHANRS + " TEXT,"
                +KEY_KECAMATANRS + " TEXT,"
                +KEY_TELPRS + " TEXT,"
                +KEY_FAXRS + " TEXT,"
                +KEY_WEBRS + " TEXT,"
                +KEY_HUMASRS + " TEXT,"
                +KEY_LATITUDE + " DOUBLE,"
                +KEY_LONGITUDE + " DOUBLE,"
                +KEY_KDPENYAKIT + " INTEGER" +")";
        db.execSQL(CREATE_RS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PENYAKIT);

        // Create tables again
        onCreate(db);
    }

    public List<Rs> getAllRs(){
        List<Rs> rss = new ArrayList<Rs>();
        String query = "SELECT * FROM "+TABLE_RS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int idrs = cursor.getInt(0);
                String kdrs = cursor.getString(1);
                String nmrs = cursor.getString(2);
                String almt = cursor.getString(3);
                String kotars = cursor.getString(4);
                String kdposrs = cursor.getString(5);
                String kelurahanrs = cursor.getString(6);
                String kecamatanrs = cursor.getString(7);
                String telprs = cursor.getString(8);
                String faxrs = cursor.getString(9);
                String webrs = cursor.getString(10);
                String humasrs = cursor.getString(11);
                double latitude = cursor.getDouble(12);
                double longitude = cursor.getDouble(13);
                int kdpenyakit = cursor.getInt(14);
                Rs rs = new Rs(idrs,kdrs,nmrs,almt,kotars,kdposrs,kelurahanrs,kecamatanrs,telprs,faxrs,webrs,humasrs,latitude,longitude,kdpenyakit);
                rss.add(rs);
            } while (cursor.moveToNext());
        }
        return rss;
    }

    public Rs getRs(int idrs){

        String query = "SELECT * FROM "+TABLE_RS + " WHERE idrs = " + idrs;
        Rs rs = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                //int novideo = cursor.getInt(0);
                String kdrs = cursor.getString(1);
                String nmrs = cursor.getString(2);
                String almt = cursor.getString(3);
                String kotars = cursor.getString(4);
                String kdposrs = cursor.getString(5);
                String kelurahanrs = cursor.getString(6);
                String kecamatanrs = cursor.getString(7);
                String telprs = cursor.getString(8);
                String faxrs = cursor.getString(9);
                String webrs = cursor.getString(10);
                String humasrs = cursor.getString(11);
                double latitude = cursor.getDouble(12);
                double longitude = cursor.getDouble(13);
                int kdpenyakit = cursor.getInt(14);
                rs = new Rs(idrs,kdrs,nmrs,almt,kotars,kdposrs,kelurahanrs,kecamatanrs,telprs,faxrs,webrs,humasrs,latitude,longitude,kdpenyakit);


            } while (cursor.moveToNext());
        }
        return rs;
    }

    public void addRs(Rs rs)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IDRS,rs.getIdrs());
        values.put(KEY_KDRS, rs.getKdrs());//1
        values.put(KEY_NMRS, rs.getNmrs());//2
        values.put(KEY_ALMT, rs.getAlmt());//3
        values.put(KEY_KOTARS, rs.getKotars());//4
        values.put(KEY_KDPOSRS, rs.getKdposrs());//5
        values.put(KEY_KELURAHANRS, rs.getKelurahanrs());//6
        values.put(KEY_KECAMATANRS, rs.getKecamatanrs());//7
        values.put(KEY_TELPRS, rs.getTelprs());//8
        values.put(KEY_FAXRS, rs.getFaxrs());//9
        values.put(KEY_WEBRS, rs.getWebrs());//10
        values.put(KEY_HUMASRS, rs.getHumasrs());//11
        values.put(KEY_LATITUDE, rs.getLatitude());//12
        values.put(KEY_LONGITUDE, rs.getLongitude());//13
        values.put(KEY_KDPENYAKIT, rs.getKdpenyakit());//14

        db.insert(TABLE_RS,null,values);
        db.close();
    }

    public void updateRs(Rs newRs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_KDRS, newRs.getKdrs());//1
        cv.put(KEY_NMRS, newRs.getNmrs());//2
        cv.put(KEY_ALMT, newRs.getAlmt());//3
        cv.put(KEY_KOTARS, newRs.getKotars());//4
        cv.put(KEY_KDPOSRS, newRs.getKdposrs());//5
        cv.put(KEY_KELURAHANRS, newRs.getKelurahanrs());//6
        cv.put(KEY_KECAMATANRS, newRs.getKecamatanrs());//7
        cv.put(KEY_TELPRS, newRs.getTelprs());//8
        cv.put(KEY_FAXRS, newRs.getFaxrs());//9
        cv.put(KEY_WEBRS, newRs.getWebrs());//10
        cv.put(KEY_HUMASRS, newRs.getHumasrs());//11
        cv.put(KEY_LATITUDE, newRs.getLatitude());//12
        cv.put(KEY_LONGITUDE, newRs.getLongitude());//13
        cv.put(KEY_KDPENYAKIT, newRs.getKdpenyakit());//14
        db.update(TABLE_RS,cv,KEY_IDRS + " = ?",new String[]{String.valueOf(newRs.getIdrs())});
    }

    public void removeRs(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RS,KEY_IDRS + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Artikel> getAllArtikel(int kdpenyakit){
        List<Artikel> artikels = new ArrayList<Artikel>();
        String query = "SELECT * FROM "+TABLE_ARTIKEL+ " WHERE kdpenyakit = "+kdpenyakit;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int noartikel = cursor.getInt(0);
                String judulartikel = cursor.getString(1);
                String contentartikel = cursor.getString(2);
                Artikel a = new Artikel(noartikel,judulartikel,contentartikel,kdpenyakit);
                artikels.add(a);
            } while (cursor.moveToNext());
        }
        return artikels;
    }

    public void addArtikel(Artikel artikel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOARTIKEL, artikel.getNoartikel());
        values.put(KEY_JUDULARTIKEL, artikel.getJudulartikel());
        values.put(KEY_CONTENTARTIKEL, artikel.getContentartikel());
        values.put(KEY_KDPENYAKIT, artikel.getKdpenyakit());

        db.insert(TABLE_ARTIKEL,null,values);
        db.close();
    }

    public void removeArtikel(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ARTIKEL,KEY_NOARTIKEL + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Artikel getArtikel(int noartikel){
        String query = "SELECT * FROM "+TABLE_ARTIKEL + " WHERE noartikel = " + noartikel;
        Artikel a = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                //int novideo = cursor.getInt(0);
                String judulartikel = cursor.getString(1);
                String contentartikel = cursor.getString(2);
                int kdpenyakit = cursor.getInt(3);
                a = new Artikel(noartikel,judulartikel,contentartikel,kdpenyakit);

            } while (cursor.moveToNext());
        }
        return a;
    }

    public void updateArtikel(Artikel newArtikel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_JUDULARTIKEL, newArtikel.getJudulartikel());
        cv.put(KEY_CONTENTARTIKEL, newArtikel.getContentartikel());
        cv.put(KEY_KDPENYAKIT, newArtikel.getKdpenyakit());
        db.update(TABLE_ARTIKEL,cv,KEY_NOARTIKEL + " = ?",new String[]{String.valueOf(newArtikel.getNoartikel())});
    }

    public List<Video> getAllVideo(int kdpenyakit){
        List<Video> videos = new ArrayList<Video>();
        String query = "SELECT * FROM "+TABLE_VIDEO+ " WHERE kdpenyakit = "+kdpenyakit;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int novideo = cursor.getInt(0);
                String judulvideo = cursor.getString(1);
                String urlvideo = cursor.getString(2);
                Video v = new Video(novideo,judulvideo,urlvideo,kdpenyakit);
                videos.add(v);
            } while (cursor.moveToNext());
        }
        return videos;
    }

    public void addVideo(Video video)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOVIDEO, video.getNovideo());
        values.put(KEY_JUDULVIDEO, video.getJudulvideo());
        values.put(KEY_URLVIDEO, video.getUrlvideo());
        values.put(KEY_KDPENYAKIT, video.getKdpenyakit());

        db.insert(TABLE_VIDEO,null,values);
        db.close();
    }


    public void addPenyakit(Penyakit penyakit)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_KDPENYAKIT, penyakit.getKdpenyakit());
        values.put(KEY_NMPENYAKIT, penyakit.getNmpenyakit());
        values.put(KEY_DESPENYAKIT, penyakit.getDespenyakit());
        values.put(KEY_FKETURUNAN, penyakit.getFketurunan());
        values.put(KEY_MENULAR, penyakit.getMenular());
        values.put(KEY_KRONIS, penyakit.getKronis());
        values.put(KEY_IMAGE_URL, penyakit.getImage_url());
        values.put(KEY_VIDEO_URL, penyakit.getVideo_url());

        db.insert(TABLE_PENYAKIT,null,values);
        db.close();
    }

    public Video getVideo(int novideo){

        String query = "SELECT * FROM "+TABLE_VIDEO + " WHERE novideo = " + novideo;
        Video v = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                //int novideo = cursor.getInt(0);
                String judulvideo = cursor.getString(1);
                String urlvideo = cursor.getString(2);
                int kdpenyakit = cursor.getInt(3);
                v = new Video(novideo,judulvideo,urlvideo,kdpenyakit);

            } while (cursor.moveToNext());
        }
        return v;
    }

    public void updateVideo(Video newVideo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_JUDULVIDEO, newVideo.getJudulvideo());
        cv.put(KEY_URLVIDEO, newVideo.getUrlvideo());
        cv.put(KEY_KDPENYAKIT, newVideo.getKdpenyakit());
        db.update(TABLE_VIDEO,cv,KEY_NOVIDEO + " = ?",new String[]{String.valueOf(newVideo.getNovideo())});
    }

    public void removeVideo(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VIDEO,KEY_NOVIDEO + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Penyakit> getPenyakit()
    {
        List<Penyakit> penyakits = new ArrayList<Penyakit>();
        String query = "SELECT * FROM "+TABLE_PENYAKIT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int kdpenyakit = cursor.getInt(0);
                String nmpenyakit = cursor.getString(1);
                String despenyakit = cursor.getString(2);
                String fketurunan = cursor.getString(3);
                String menular = cursor.getString(4);
                String kronis = cursor.getString(5);
                String image_url = cursor.getString(6);
                String video_url = cursor.getString(7);
                Penyakit p = new Penyakit(kdpenyakit,nmpenyakit,despenyakit,fketurunan,menular,kronis,image_url,video_url);
                penyakits.add(p);
            } while (cursor.moveToNext());
        }
        return penyakits;
    }

    public Penyakit getPenyakit(int kdpenyakit){

        String query = "SELECT * FROM "+TABLE_PENYAKIT + " WHERE kdpenyakit = " + kdpenyakit;
        Penyakit p = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String nmpenyakit = cursor.getString(1);
                String despenyakit = cursor.getString(2);
                String fketurunan = cursor.getString(3);
                String menular = cursor.getString(4);
                String kronis = cursor.getString(5);
                String image_url = cursor.getString(6);
                String video_url = cursor.getString(7);
                p = new Penyakit(kdpenyakit,nmpenyakit,despenyakit,fketurunan,menular,kronis,image_url,video_url);

            } while (cursor.moveToNext());
        }
        return  p;
    }

    public void updatePenyakit(Penyakit newPenyakit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NMPENYAKIT, newPenyakit.getNmpenyakit());
        cv.put(KEY_DESPENYAKIT, newPenyakit.getDespenyakit());
        cv.put(KEY_FKETURUNAN, newPenyakit.getFketurunan());
        cv.put(KEY_MENULAR, newPenyakit.getMenular());
        cv.put(KEY_KRONIS, newPenyakit.getKronis());
        cv.put(KEY_IMAGE_URL, newPenyakit.getImage_url());
        cv.put(KEY_VIDEO_URL, newPenyakit.getVideo_url());
        db.update(TABLE_PENYAKIT,cv,KEY_KDPENYAKIT + " = ?",new String[]{String.valueOf(newPenyakit.getKdpenyakit())});
    }

    public void removePenyakit(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PENYAKIT,KEY_KDPENYAKIT + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
