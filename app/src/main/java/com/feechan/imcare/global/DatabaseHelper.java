package com.feechan.imcare.global;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.feechan.imcare.entity.Penyakit;

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


    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PEOPLE_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE_PENYAKIT + "("
                +KEY_KDPENYAKIT + " INTEGER,"
                +KEY_NMPENYAKIT + " TEXT,"
                +KEY_DESPENYAKIT + " TEXT,"
                +KEY_FKETURUNAN + " TEXT,"
                +KEY_MENULAR + " TEXT,"
                +KEY_KRONIS + " TEXT,"
                +KEY_IMAGE_URL + " TEXT" + ")";
        db.execSQL(CREATE_PEOPLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PENYAKIT);

        // Create tables again
        onCreate(db);
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

        db.insert(TABLE_PENYAKIT,null,values);
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
                Penyakit p = new Penyakit(kdpenyakit,nmpenyakit,despenyakit,fketurunan,menular,kronis,image_url);
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
                p = new Penyakit(kdpenyakit,nmpenyakit,despenyakit,fketurunan,menular,kronis,image_url);

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
        db.update(TABLE_PENYAKIT,cv,KEY_KDPENYAKIT + " = ?",new String[]{String.valueOf(newPenyakit.getKdpenyakit())});
    }

    public void removePenyakit(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PENYAKIT,KEY_KDPENYAKIT + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
