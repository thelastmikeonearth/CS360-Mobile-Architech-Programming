package com.zybooks.finalprojectmod5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Pair;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app.db";
    private static final int VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final class LoginTable {
        public static final String TABLE_NAME = "login";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

    private static final class WeightsTable {
        public static final String TABLE_NAME = "weights";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_USERNAME = "username";
    }

    private static final class GoalsTable {
        public static final String TABLE_NAME = "goals";
        public static final String COLUMN_NAME_GOAL = "goal";
        public static final String COLUMN_NAME_GOAL_ID = "goal_id";
        public static final String COLUMN_NAME_USERNAME = "username";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LoginTable.TABLE_NAME + " (" +
                LoginTable.COLUMN_NAME_USERNAME + " text primary key, " +
                LoginTable.COLUMN_NAME_PASSWORD + " text)");

        db.execSQL("create table " + WeightsTable.TABLE_NAME + " (" +
                WeightsTable.COLUMN_NAME_WEIGHT + " real, " +
                WeightsTable.COLUMN_NAME_DATE + " long primary key, " +
                WeightsTable.COLUMN_NAME_USERNAME + " text, " +
                "FOREIGN KEY(" + WeightsTable.COLUMN_NAME_USERNAME + ") REFERENCES " + LoginTable.TABLE_NAME + "(" + LoginTable.COLUMN_NAME_USERNAME + "))");

        db.execSQL("create table " + GoalsTable.TABLE_NAME + " (" +
                GoalsTable.COLUMN_NAME_GOAL_ID + " integer primary key autoincrement, " +
                GoalsTable.COLUMN_NAME_GOAL + " real, " +
                GoalsTable.COLUMN_NAME_USERNAME + " text, " +
                "FOREIGN KEY(" + GoalsTable.COLUMN_NAME_USERNAME + ") REFERENCES " + LoginTable.TABLE_NAME + "(" + LoginTable.COLUMN_NAME_USERNAME + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + LoginTable.TABLE_NAME);
        db.execSQL("drop table if exists " + WeightsTable.TABLE_NAME);
        db.execSQL("drop table if exists " + GoalsTable.TABLE_NAME);
        onCreate(db);
    }

    public long createUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        var values = new ContentValues();
        values.put(LoginTable.COLUMN_NAME_USERNAME, username);
        values.put(LoginTable.COLUMN_NAME_PASSWORD, password);
        return db.insert(LoginTable.TABLE_NAME, null, values);
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        var sql = "select password from " + LoginTable.TABLE_NAME + " where username = '" + username + "'";
        var cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0).equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void updateGoal(double goal, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        var values = new ContentValues();
        values.put(GoalsTable.COLUMN_NAME_GOAL, goal);
        values.put(GoalsTable.COLUMN_NAME_USERNAME, username);
        db.insert(GoalsTable.TABLE_NAME, null, values);
    }

    public double getGoal(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        var sql = "select goal from " + GoalsTable.TABLE_NAME + " where username = '" + username + "'";
        var cursor = db.rawQuery(sql, null);
        if (cursor.moveToLast()) {
            return cursor.getDouble(0);
        }
        return -1;
    }

    public void addWeight(double weight, long date, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        var values = new ContentValues();
        values.put(WeightsTable.COLUMN_NAME_WEIGHT, weight);
        values.put(WeightsTable.COLUMN_NAME_DATE, date);
        values.put(WeightsTable.COLUMN_NAME_USERNAME, username);
        db.insert(WeightsTable.TABLE_NAME, null, values);
    }

    public Pair<Long, Double>[] getWeights(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        var sql = "select date, weight from " + WeightsTable.TABLE_NAME + " where username = '" + username + "'";
        var cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            var weights = new Pair[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                weights[i] = new Pair<>(cursor.getLong(0), cursor.getDouble(1));
                cursor.moveToNext();
            }
            return weights;
        }
        return new Pair[0];
    }

    public void deleteWeight(long date, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        var values = new ContentValues();
        values.put(WeightsTable.COLUMN_NAME_DATE, date);
        values.put(WeightsTable.COLUMN_NAME_USERNAME, username);
        db.delete(WeightsTable.TABLE_NAME, WeightsTable.COLUMN_NAME_DATE + " = '" + date + "' and " + WeightsTable.COLUMN_NAME_USERNAME + " = '" + username + "'", null);
    }

}
