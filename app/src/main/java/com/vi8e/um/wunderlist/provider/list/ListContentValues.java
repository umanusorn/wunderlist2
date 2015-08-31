package com.vi8e.um.wunderlist.provider.list;

import android.content.ContentResolver;
import android.net.Uri;

import com.vi8e.um.wunderlist.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code list} table.
 */
public class ListContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ListColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, ListSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ListContentValues putListTitle(String value) {
        mContentValues.put(ListColumns.LIST_TITLE, value);
        return this;
    }

    public ListContentValues putListTitleNull() {
        mContentValues.putNull(ListColumns.LIST_TITLE);
        return this;
    }


    /**
     * The commercial name of this company.
     */
    public ListContentValues putNumLateTask(String value) {
        if (value == null) throw new IllegalArgumentException("value for numLateTask must not be null");
        mContentValues.put(ListColumns.NUM_LATE_TASK, value);
        return this;
    }



    public ListContentValues putNumCurrentTask(String value) {
        mContentValues.put(ListColumns.NUM_CURRENT_TASK, value);
        return this;
    }

    public ListContentValues putNumCurrentTaskNull() {
        mContentValues.putNull(ListColumns.NUM_CURRENT_TASK);
        return this;
    }


    public ListContentValues putFolderId(String value) {
        mContentValues.put(ListColumns.FOLDER_ID, value);
        return this;
    }

    public ListContentValues putFolderIdNull() {
        mContentValues.putNull(ListColumns.FOLDER_ID);
        return this;
    }


    public ListContentValues putIspinned(String value) {
        mContentValues.put(ListColumns.ISPINNED, value);
        return this;
    }

    public ListContentValues putIspinnedNull() {
        mContentValues.putNull(ListColumns.ISPINNED);
        return this;
    }


    public ListContentValues putIsdisturb(String value) {
        mContentValues.put(ListColumns.ISDISTURB, value);
        return this;
    }

    public ListContentValues putIsdisturbNull() {
        mContentValues.putNull(ListColumns.ISDISTURB);
        return this;
    }


    public ListContentValues putImgPath(String value) {
        mContentValues.put(ListColumns.IMG_PATH, value);
        return this;
    }

    public ListContentValues putImgPathNull() {
        mContentValues.putNull(ListColumns.IMG_PATH);
        return this;
    }


    public ListContentValues putType(String value) {
        mContentValues.put(ListColumns.TYPE, value);
        return this;
    }

    public ListContentValues putTypeNull() {
        mContentValues.putNull(ListColumns.TYPE);
        return this;
    }

}
