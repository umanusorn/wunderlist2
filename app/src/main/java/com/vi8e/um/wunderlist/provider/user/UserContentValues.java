package com.vi8e.um.wunderlist.provider.user;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code user} table.
 */
public class UserContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return UserColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable UserSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable UserSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public UserContentValues putUserTitle(@Nullable String value) {
        mContentValues.put(UserColumns.USER_TITLE, value);
        return this;
    }

    public UserContentValues putUserTitleNull() {
        mContentValues.putNull(UserColumns.USER_TITLE);
        return this;
    }

    public UserContentValues putImgPath(@Nullable String value) {
        mContentValues.put(UserColumns.IMG_PATH, value);
        return this;
    }

    public UserContentValues putImgPathNull() {
        mContentValues.putNull(UserColumns.IMG_PATH);
        return this;
    }
}
