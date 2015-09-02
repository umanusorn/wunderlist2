package com.vi8e.um.wunderlist.provider.folder;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code folder} table.
 */
public class FolderContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return FolderColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable FolderSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable FolderSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public FolderContentValues putTitle(@Nullable String value) {
        mContentValues.put(FolderColumns.TITLE, value);
        return this;
    }

    public FolderContentValues putTitleNull() {
        mContentValues.putNull(FolderColumns.TITLE);
        return this;
    }
}
