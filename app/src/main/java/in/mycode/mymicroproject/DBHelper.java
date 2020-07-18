package in.mcode.mymicroproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Restaurant";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "users";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";
    public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" + COL_EMAIL + " TEXT PRIMARY KEY,"+ COL_PASSWORD + " TEXT)";
    public static final String TABLE_UPGRADE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
        Toast.makeText(context, "Table Initialized", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_UPGRADE);
        onCreate(db);
        Toast.makeText(context, "Table Upgraded", Toast.LENGTH_SHORT).show();
    }
    public String addUser(String email, String password) {

        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_EMAIL, email);
            contentValues.put(COL_PASSWORD, password);
            db.insert(TABLE_NAME, null, contentValues);
            db.close();
            return "Successfully Signed-up";
        } catch (Exception exe) {
            return "Error on Sign-up.Message : " + exe.getMessage();
        }

    }


    public String search(String email, String password) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            String CHECK_LOGIN = "select * from " + TABLE_NAME + " where " + COL_EMAIL + "='" + email + "' and " + COL_PASSWORD + "='" + password + "'";
            Cursor cursor = db.rawQuery(CHECK_LOGIN, null);
            cursor.moveToFirst();
            do {
                String Email = cursor.getString(cursor.getColumnIndex(COL_EMAIL));
                String passwd = cursor.getString(cursor.getColumnIndex(COL_PASSWORD));
                if (Email.equalsIgnoreCase(email) && passwd.equals(password)) {
                    return "Successfully logged in";

                } else {
                    return "Login failed";
                }
            }
            while (cursor.moveToNext());

        } catch (Exception ex) {
            return "Error!" + ex.getMessage();
        }
    }

}
