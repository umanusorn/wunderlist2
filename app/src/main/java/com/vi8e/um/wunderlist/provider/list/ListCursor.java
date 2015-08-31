package com.vi8e.um.wunderlist.provider.list;

import android.database.Cursor;

import com.vi8e.um.wunderlist.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code list} table.
 */
public class ListCursor extends AbstractCursor {
    public ListCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code list_title} value.
     * Can be {@code null}.
     */
    public String getListTitle() {
        Integer index = getCachedColumnIndexOrThrow(ListColumns.LIST_TITLE);
        return getString(index);
    }

    /**
     * The commercial name of this company.
     * Cannot be {@code null}.
     */
    public String getNumLateTask() {
        Integer index = getCachedColumnIndexOrThrow(ListColumns.NUM_LATE_TASK);
        return getString(index);
    }

    /**
     * Get the {@code num_current_task} value.
     * Can be {@code null}.
     */
    public String getNumCurrentTask() {
        Integer index = getCachedColumnIndexOrThrow(ListColumns.NUM_CURRENT_TASK);
        return getString(index);
    }

    /**
     * Get the {@code folder_id} value.
     * Can be {@code null}.
     */
    public String getFolderId() {
        Integer index = getCachedColumnIndexOrThrow(ListColumns.FOLDER_ID);
        return getString(index);
    }

    /**
     * Get the {@code ispinned} value.
     * Can be {@code null}.
     */
    public String getIspinned() {
        Integer index = getCachedColumnIndexOrThrow(ListColumns.ISPINNED);
        return getString(index);
    }

    /**
     * Get the {@code isdisturb} value.
     * Can be {@code null}.
     */
    public String getIsdisturb() {
        Integer index = getCachedColumnIndexOrThrow(ListColumns.ISDISTURB);
        return getString(index);
    }

    /**
     * Get the {@code img_path} value.
     * Can be {@code null}.
     */
    public String getImgPath() {
        Integer index = getCachedColumnIndexOrThrow(ListColumns.IMG_PATH);
        return getString(index);
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    public String getType() {
        Integer index = getCachedColumnIndexOrThrow(ListColumns.TYPE);
        return getString(index);
    }
}
