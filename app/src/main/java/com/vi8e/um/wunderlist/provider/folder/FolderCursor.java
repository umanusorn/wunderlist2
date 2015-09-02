package com.vi8e.um.wunderlist.provider.folder;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code folder} table.
 */
public class FolderCursor extends AbstractCursor implements FolderModel {
    public FolderCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(FolderColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        String res = getStringOrNull(FolderColumns.TITLE);
        return res;
    }
}
