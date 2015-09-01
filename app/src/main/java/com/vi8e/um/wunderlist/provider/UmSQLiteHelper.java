package com.vi8e.um.wunderlist.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.vi8e.um.wunderlist.BuildConfig;
import com.vi8e.um.wunderlist.provider.list.ListColumns;

public class UmSQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = UmSQLiteHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "um.db";
    private static final int DATABASE_VERSION = 1;
    private static UmSQLiteHelper sInstance;
    private final Context mContext;
    private final UmSQLiteHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_LIST = "CREATE TABLE IF NOT EXISTS "
            + ListColumns.TABLE_NAME + " ( "
            + ListColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ListColumns.LIST_TITLE + " TEXT DEFAULT '0', "
            + ListColumns.NUM_LATE_TASK + " TEXT NOT NULL DEFAULT '0', "
            + ListColumns.NUM_CURRENT_TASK + " TEXT DEFAULT '0', "
            + ListColumns.FOLDER_ID + " TEXT DEFAULT '0', "
            + ListColumns.ISPINNED + " TEXT DEFAULT '0', "
            + ListColumns.ISDISTURB + " TEXT DEFAULT '0', "
            + ListColumns.IMG_PATH + " TEXT DEFAULT '0', "
            + ListColumns.TYPE + " TEXT DEFAULT '0' "
            + " );";

    public static final String SQL_CREATE_INDEX_LIST_NUM_LATE_TASK = "CREATE INDEX IDX_LIST_NUM_LATE_TASK "
            + " ON " + ListColumns.TABLE_NAME + " ( " + ListColumns.NUM_LATE_TASK + " );";

    // @formatter:on

    public static UmSQLiteHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static UmSQLiteHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static UmSQLiteHelper newInstancePreHoneycomb(Context context) {
        return new UmSQLiteHelper(context);
    }

    private UmSQLiteHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new UmSQLiteHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static UmSQLiteHelper newInstancePostHoneycomb(Context context) {
        return new UmSQLiteHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private UmSQLiteHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new UmSQLiteHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_LIST);
        db.execSQL(SQL_CREATE_INDEX_LIST_NUM_LATE_TASK);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
