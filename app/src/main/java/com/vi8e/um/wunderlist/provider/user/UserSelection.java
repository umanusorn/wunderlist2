package com.vi8e.um.wunderlist.provider.user;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.vi8e.um.wunderlist.provider.base.AbstractSelection;

/**
 * Selection for the {@code user} table.
 */
public class UserSelection extends AbstractSelection<UserSelection> {
    @Override
    protected Uri baseUri() {
        return UserColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code UserCursor} object, which is positioned before the first entry, or null.
     */
    public UserCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new UserCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public UserCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code UserCursor} object, which is positioned before the first entry, or null.
     */
    public UserCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new UserCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public UserCursor query(Context context) {
        return query(context, null);
    }


    public UserSelection id(long... value) {
        addEquals("user." + UserColumns._ID, toObjectArray(value));
        return this;
    }

    public UserSelection idNot(long... value) {
        addNotEquals("user." + UserColumns._ID, toObjectArray(value));
        return this;
    }

    public UserSelection orderById(boolean desc) {
        orderBy("user." + UserColumns._ID, desc);
        return this;
    }

    public UserSelection orderById() {
        return orderById(false);
    }

    public UserSelection userTitle(String... value) {
        addEquals(UserColumns.USER_TITLE, value);
        return this;
    }

    public UserSelection userTitleNot(String... value) {
        addNotEquals(UserColumns.USER_TITLE, value);
        return this;
    }

    public UserSelection userTitleLike(String... value) {
        addLike(UserColumns.USER_TITLE, value);
        return this;
    }

    public UserSelection userTitleContains(String... value) {
        addContains(UserColumns.USER_TITLE, value);
        return this;
    }

    public UserSelection userTitleStartsWith(String... value) {
        addStartsWith(UserColumns.USER_TITLE, value);
        return this;
    }

    public UserSelection userTitleEndsWith(String... value) {
        addEndsWith(UserColumns.USER_TITLE, value);
        return this;
    }

    public UserSelection orderByUserTitle(boolean desc) {
        orderBy(UserColumns.USER_TITLE, desc);
        return this;
    }

    public UserSelection orderByUserTitle() {
        orderBy(UserColumns.USER_TITLE, false);
        return this;
    }

    public UserSelection imgPath(String... value) {
        addEquals(UserColumns.IMG_PATH, value);
        return this;
    }

    public UserSelection imgPathNot(String... value) {
        addNotEquals(UserColumns.IMG_PATH, value);
        return this;
    }

    public UserSelection imgPathLike(String... value) {
        addLike(UserColumns.IMG_PATH, value);
        return this;
    }

    public UserSelection imgPathContains(String... value) {
        addContains(UserColumns.IMG_PATH, value);
        return this;
    }

    public UserSelection imgPathStartsWith(String... value) {
        addStartsWith(UserColumns.IMG_PATH, value);
        return this;
    }

    public UserSelection imgPathEndsWith(String... value) {
        addEndsWith(UserColumns.IMG_PATH, value);
        return this;
    }

    public UserSelection orderByImgPath(boolean desc) {
        orderBy(UserColumns.IMG_PATH, desc);
        return this;
    }

    public UserSelection orderByImgPath() {
        orderBy(UserColumns.IMG_PATH, false);
        return this;
    }
}
