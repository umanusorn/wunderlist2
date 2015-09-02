package com.vi8e.um.wunderlist.provider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.vi8e.um.wunderlist.BuildConfig;
import com.vi8e.um.wunderlist.provider.base.BaseContentProvider;
import com.vi8e.um.wunderlist.provider.folder.FolderColumns;
import com.vi8e.um.wunderlist.provider.list.ListColumns;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskColumns;
import com.vi8e.um.wunderlist.provider.task.TaskColumns;
import com.vi8e.um.wunderlist.provider.taskcomment.TaskCommentColumns;
import com.vi8e.um.wunderlist.provider.taskfile.TaskFileColumns;
import com.vi8e.um.wunderlist.provider.user.UserColumns;

import java.util.Arrays;

public class MasterProvider extends BaseContentProvider {
    private static final String TAG = MasterProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.vi8e.um.wunderlist.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_FOLDER = 0;
    private static final int URI_TYPE_FOLDER_ID = 1;

    private static final int URI_TYPE_LIST = 2;
    private static final int URI_TYPE_LIST_ID = 3;

    private static final int URI_TYPE_SUBTASK = 4;
    private static final int URI_TYPE_SUBTASK_ID = 5;

    private static final int URI_TYPE_TASK = 6;
    private static final int URI_TYPE_TASK_ID = 7;

    private static final int URI_TYPE_TASK_COMMENT = 8;
    private static final int URI_TYPE_TASK_COMMENT_ID = 9;

    private static final int URI_TYPE_TASK_FILE = 10;
    private static final int URI_TYPE_TASK_FILE_ID = 11;

    private static final int URI_TYPE_USER = 12;
    private static final int URI_TYPE_USER_ID = 13;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, FolderColumns.TABLE_NAME, URI_TYPE_FOLDER);
        URI_MATCHER.addURI(AUTHORITY, FolderColumns.TABLE_NAME + "/#", URI_TYPE_FOLDER_ID);
        URI_MATCHER.addURI(AUTHORITY, ListColumns.TABLE_NAME, URI_TYPE_LIST);
        URI_MATCHER.addURI(AUTHORITY, ListColumns.TABLE_NAME + "/#", URI_TYPE_LIST_ID);
        URI_MATCHER.addURI(AUTHORITY, SubtaskColumns.TABLE_NAME, URI_TYPE_SUBTASK);
        URI_MATCHER.addURI(AUTHORITY, SubtaskColumns.TABLE_NAME + "/#", URI_TYPE_SUBTASK_ID);
        URI_MATCHER.addURI(AUTHORITY, TaskColumns.TABLE_NAME, URI_TYPE_TASK);
        URI_MATCHER.addURI(AUTHORITY, TaskColumns.TABLE_NAME + "/#", URI_TYPE_TASK_ID);
        URI_MATCHER.addURI(AUTHORITY, TaskCommentColumns.TABLE_NAME, URI_TYPE_TASK_COMMENT);
        URI_MATCHER.addURI(AUTHORITY, TaskCommentColumns.TABLE_NAME + "/#", URI_TYPE_TASK_COMMENT_ID);
        URI_MATCHER.addURI(AUTHORITY, TaskFileColumns.TABLE_NAME, URI_TYPE_TASK_FILE);
        URI_MATCHER.addURI(AUTHORITY, TaskFileColumns.TABLE_NAME + "/#", URI_TYPE_TASK_FILE_ID);
        URI_MATCHER.addURI(AUTHORITY, UserColumns.TABLE_NAME, URI_TYPE_USER);
        URI_MATCHER.addURI(AUTHORITY, UserColumns.TABLE_NAME + "/#", URI_TYPE_USER_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return UmSQLiteHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_FOLDER:
                return TYPE_CURSOR_DIR + FolderColumns.TABLE_NAME;
            case URI_TYPE_FOLDER_ID:
                return TYPE_CURSOR_ITEM + FolderColumns.TABLE_NAME;

            case URI_TYPE_LIST:
                return TYPE_CURSOR_DIR + ListColumns.TABLE_NAME;
            case URI_TYPE_LIST_ID:
                return TYPE_CURSOR_ITEM + ListColumns.TABLE_NAME;

            case URI_TYPE_SUBTASK:
                return TYPE_CURSOR_DIR + SubtaskColumns.TABLE_NAME;
            case URI_TYPE_SUBTASK_ID:
                return TYPE_CURSOR_ITEM + SubtaskColumns.TABLE_NAME;

            case URI_TYPE_TASK:
                return TYPE_CURSOR_DIR + TaskColumns.TABLE_NAME;
            case URI_TYPE_TASK_ID:
                return TYPE_CURSOR_ITEM + TaskColumns.TABLE_NAME;

            case URI_TYPE_TASK_COMMENT:
                return TYPE_CURSOR_DIR + TaskCommentColumns.TABLE_NAME;
            case URI_TYPE_TASK_COMMENT_ID:
                return TYPE_CURSOR_ITEM + TaskCommentColumns.TABLE_NAME;

            case URI_TYPE_TASK_FILE:
                return TYPE_CURSOR_DIR + TaskFileColumns.TABLE_NAME;
            case URI_TYPE_TASK_FILE_ID:
                return TYPE_CURSOR_ITEM + TaskFileColumns.TABLE_NAME;

            case URI_TYPE_USER:
                return TYPE_CURSOR_DIR + UserColumns.TABLE_NAME;
            case URI_TYPE_USER_ID:
                return TYPE_CURSOR_ITEM + UserColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_FOLDER:
            case URI_TYPE_FOLDER_ID:
                res.table = FolderColumns.TABLE_NAME;
                res.idColumn = FolderColumns._ID;
                res.tablesWithJoins = FolderColumns.TABLE_NAME;
                res.orderBy = FolderColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_LIST:
            case URI_TYPE_LIST_ID:
                res.table = ListColumns.TABLE_NAME;
                res.idColumn = ListColumns._ID;
                res.tablesWithJoins = ListColumns.TABLE_NAME;
                res.orderBy = ListColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_SUBTASK:
            case URI_TYPE_SUBTASK_ID:
                res.table = SubtaskColumns.TABLE_NAME;
                res.idColumn = SubtaskColumns._ID;
                res.tablesWithJoins = SubtaskColumns.TABLE_NAME;
                res.orderBy = SubtaskColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_TASK:
            case URI_TYPE_TASK_ID:
                res.table = TaskColumns.TABLE_NAME;
                res.idColumn = TaskColumns._ID;
                res.tablesWithJoins = TaskColumns.TABLE_NAME;
                res.orderBy = TaskColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_TASK_COMMENT:
            case URI_TYPE_TASK_COMMENT_ID:
                res.table = TaskCommentColumns.TABLE_NAME;
                res.idColumn = TaskCommentColumns._ID;
                res.tablesWithJoins = TaskCommentColumns.TABLE_NAME;
                res.orderBy = TaskCommentColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_TASK_FILE:
            case URI_TYPE_TASK_FILE_ID:
                res.table = TaskFileColumns.TABLE_NAME;
                res.idColumn = TaskFileColumns._ID;
                res.tablesWithJoins = TaskFileColumns.TABLE_NAME;
                res.orderBy = TaskFileColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_USER:
            case URI_TYPE_USER_ID:
                res.table = UserColumns.TABLE_NAME;
                res.idColumn = UserColumns._ID;
                res.tablesWithJoins = UserColumns.TABLE_NAME;
                res.orderBy = UserColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_FOLDER_ID:
            case URI_TYPE_LIST_ID:
            case URI_TYPE_SUBTASK_ID:
            case URI_TYPE_TASK_ID:
            case URI_TYPE_TASK_COMMENT_ID:
            case URI_TYPE_TASK_FILE_ID:
            case URI_TYPE_USER_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
