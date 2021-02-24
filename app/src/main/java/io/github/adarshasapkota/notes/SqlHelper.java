package io.github.adarshasapkota.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.Date;

public class SqlHelper extends SQLiteOpenHelper {
    private final static String TABLE_NAME="notes";
    private final static String COLUMN0="id";
    private final static String COLUMN1="note";
    private final static String COLUMN2="createdDate";
    private final static String COLUMN3="lastModifiedDate";

    public SqlHelper(@Nullable Context context) {
        super(context, context.getPackageName(), null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+COLUMN0+" INTEGER PRIMARY KEY, "+COLUMN1+" TEXT, "+COLUMN2+" TEXT, "+COLUMN3+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean create(Note note){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN1, note.getNote());
        contentValues.put(COLUMN2, note.getCreatedDate());
        contentValues.put(COLUMN3, note.getLastModifiedDate());
        return database.insert(TABLE_NAME, null, contentValues)>0;
    }

    public Cursor read(){
        SQLiteDatabase database=this.getWritableDatabase();
        return database.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

    public boolean update(Note note){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN1, note.getNote());
        contentValues.put(COLUMN3, new Date().toString());
        return database.update(TABLE_NAME, contentValues, " id = ? ", new String[]{note.getId()})>0;
    }

    public boolean delete(Note note){
        SQLiteDatabase database=this.getWritableDatabase();
        return  database.delete(TABLE_NAME, " id = ? ", new String[]{note.getId()})>0;
    }
}
