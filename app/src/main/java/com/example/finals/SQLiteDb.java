package com.example.finals;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.finals.Frag2Rv.ModalClassNotes;

public class SQLiteDb {
   private final String db_name = "UserNotes";
   private final int db_version = 1;
   private final String table_name = "Notes";

   private final String col_id = "id";

   private final String col_title = "title";
   private final String col_description = "description";

   ModalClassNotes note;

   Context context;
   NotesOpenHelper openHelper;

   private class NotesOpenHelper extends SQLiteOpenHelper{

       public NotesOpenHelper(Context context){
           super(context, db_name, null, db_version);
       }

       @Override
       public void onCreate(SQLiteDatabase db) {
           String query= "CREATE TABLE " + table_name + " (" +
                   col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   col_title + " TEXT, " +
                   col_description + " TEXT" + ");";
           db.execSQL(query);

       }

       @Override
       public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           db.execSQL("DROP TABLE IF EXISTS " + table_name + ";");
           onCreate(db);
       }
   }

    public SQLiteDb(Context context){

       this.context = context;
    }



    public void open(){
        openHelper = new NotesOpenHelper(context);
    }

    public void close(){
       openHelper.close();
    }

    public void insert(ModalClassNotes notenew){
       ContentValues cv = new ContentValues();
       cv.put(col_title, notenew.getTitle());
       cv.put(col_description, notenew.getDescription());

       SQLiteDatabase db = openHelper.getWritableDatabase();
       db.insert(table_name, null, cv);
       db.close();

    }

    public int update(ModalClassNotes notenew) {
        ContentValues cv = new ContentValues();
        cv.put(col_title, notenew.getTitle());
        cv.put(col_description, notenew.getDescription());


        SQLiteDatabase db = openHelper.getWritableDatabase();
        int count =0;
        count= db.update(table_name, cv, col_id + " = ?", new String[]{String.valueOf(notenew.getId())});
        db.close();
        return count;
        //incdicates if updated

    }

    public void delete(ModalClassNotes notenew){
       //first get the corresb=ponding id from the
        ContentValues cv = new ContentValues();
        cv.put(col_title, notenew.getTitle());
        cv.put(col_description, notenew.getDescription());


        SQLiteDatabase db = openHelper.getWritableDatabase();
        int count =0;
        count= db.delete(table_name, col_id + " = ?", new String[]{String.valueOf(notenew.getId())});
        db.close();
   
    }



}
