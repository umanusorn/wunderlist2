package com.vi8e.um.wunderlist.provider.user;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code user} table.
 */
public class UserCursor extends AbstractCursor implements UserModel {
    public UserCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(UserColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code user_title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getUserTitle() {
        String res = getStringOrNull(UserColumns.USER_TITLE);
        return res;
    }

    /**
     * Get the {@code img_path} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImgPath() {
        String res = getStringOrNull(UserColumns.IMG_PATH);
        return res;
    }
}
