package com.example.brenda.reportcard;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.brenda.reportcard.R.id.English;
import static com.example.brenda.reportcard.R.id.Science;



public class DbAdapter {


    myDbHelper myhelper;
    public DbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name, String surname,String Age,String grade,String Mathematics,String science,String english)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.SURNAME, surname);
        contentValues.put(myDbHelper.AGE, Age);//added
        contentValues.put(myDbHelper.GRADE, grade);//added
        contentValues.put(myDbHelper.MATHEMATICS, Mathematics);//added
        contentValues.put(myDbHelper.SCIENCE, science);//added
        contentValues.put(myDbHelper.ENGLISH, english);//added
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public ArrayList <String> getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.SURNAME, myDbHelper.AGE, myDbHelper.GRADE, myDbHelper.MATHEMATICS, myDbHelper.SCIENCE, myDbHelper.ENGLISH};//added
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);



        ArrayList<String> userList= new ArrayList<String>() ;
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String  Surname =cursor.getString(cursor.getColumnIndex(myDbHelper.SURNAME));
            String  Age =cursor.getString(cursor.getColumnIndex(myDbHelper.AGE));
            String  Grade =cursor.getString(cursor.getColumnIndex(myDbHelper.GRADE));
            String  Mathematics =cursor.getString(cursor.getColumnIndex(myDbHelper.MATHEMATICS));
            String  Science =cursor.getString(cursor.getColumnIndex(myDbHelper.SCIENCE));
            String  eng =cursor.getString(cursor.getColumnIndex(myDbHelper.ENGLISH));

            int average = (Integer.parseInt(Mathematics)+ Integer.parseInt(Science)+ Integer.parseInt(eng))/3;
            int total = (Integer.parseInt(Mathematics)+ Integer.parseInt(Science)+ Integer.parseInt(eng));

            String display=cid+ "   Student  : " + name +"  Leaner name    : " + Surname +" learner Surname : " + Age +" Age      : " + Grade +"  Grade  : " + Mathematics +" Science        : " + Science +"     - English         : " + eng + "     - Total         : " + total +"     - Avarage         : " +average  +"\n";
            userList.add(display);


        }

        return  userList;
    }


    public  int delete(String uname, String unumber)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();

        String[] whereArgs ={uname, unumber};

        int count =db.delete(myDbHelper.TABLE_NAME , myDbHelper.NAME+" = ? AND " + myDbHelper.SURNAME+" =? ",whereArgs);
        return  count;
    }


    public int updateName(String OldName , String NewName,String OldNumber,String NewNumber,String OldMath,String  NewMath,String OldBuz, String NewBuz,String OldAccout,String NewAccount,String OldScie,String NewSci,String OldEngli,String NewEngli)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,NewName);
        contentValues.put(myDbHelper.SURNAME,NewNumber); //added
        contentValues.put(myDbHelper.AGE,NewMath); //added
        contentValues.put(myDbHelper.GRADE,NewBuz); //added
        contentValues.put(myDbHelper.MATHEMATICS,NewAccount); //added
        contentValues.put(myDbHelper.SCIENCE,NewSci); //added
        contentValues.put(myDbHelper.ENGLISH,NewEngli); //added


        String[] whereArgs= {OldName,OldNumber,OldMath,OldBuz,OldAccout,OldScie,OldEngli};//added
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" =? AND "+ myDbHelper.SURNAME+" =? AND "+ myDbHelper.AGE+" =? AND "+ myDbHelper.GRADE+" =? AND "+ myDbHelper.MATHEMATICS+" =? AND "+ myDbHelper.SCIENCE+" =? AND "+ myDbHelper.ENGLISH+" =? ",whereArgs );

        return count;
    }

static class myDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dd";    // Database Name
    private static final String TABLE_NAME = "mydd";   // Table Name
    private static final int DATABASE_Version = 10;    // Database Version
    private static final String UID = "_id";     // Column I (Primary Key)
    private static final String NAME = "Name";    //Column II
    private static final String SURNAME = "Surname";    // Column III
    private static final String AGE = "Age";    // Column III
    private static final String GRADE = "Grade";    // Column III
    private static final String MATHEMATICS = "Mathematics";    // Column III
    private static final String SCIENCE = "Science";    // Column III
    private static final String ENGLISH = "English";    // Column III


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," + SURNAME + " VARCHAR(225)," + AGE + " VARCHAR(225)," + GRADE + " VARCHAR(225)," + MATHEMATICS + " VARCHAR(225)," + SCIENCE + " VARCHAR(225)," + ENGLISH + " VARCHAR(225));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;

    public myDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Message.message(context, "" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Message.message(context, "OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e) {
            Message.message(context, "" + e);
        }
    }
}
    public boolean checkUser(String name) {

        String[] columns = {

                myhelper.UID
        };
        SQLiteDatabase db = myhelper.getReadableDatabase();


        String selection = myDbHelper.NAME + " = ?";


        String[] selectionArgs = {name};


        Cursor cursor = db.query(myhelper.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String name, String password) {


        String[] columns = {

                myDbHelper.UID
        };
        SQLiteDatabase db = myhelper.getReadableDatabase();

        String selection = myDbHelper.NAME + " = ?" + " AND " + myDbHelper.SURNAME + " = ?";

        String[] selectionArgs = {name, password};


        Cursor cursor = db.query(myDbHelper.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


}

