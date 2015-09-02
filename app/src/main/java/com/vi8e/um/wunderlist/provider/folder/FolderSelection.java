package com.vi8e.um.wunderlist.provider.folder;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.vi8e.um.wunderlist.provider.base.AbstractSelection;

/**
 * Selection for the {@code folder} table.
 */
public class FolderSelection extends AbstractSelection<FolderSelection> {
    @Override
    protected Uri baseUri() {
        return FolderColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FolderCursor} object, which is positioned before the first entry, or null.
     */
    public FolderCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FolderCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public FolderCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FolderCursor} object, which is positioned before the first entry, or null.
     */
    public FolderCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FolderCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public FolderCursor query(Context context) {
        return query(context, null);
    }


    public FolderSelection id(long... value) {
        addEquals("folder." + FolderColumns._ID, toObjectArray(value));
        return this;
    }

    public FolderSelection idNot(long... value) {
        addNotEquals("folder." + FolderColumns._ID, toObjectArray(value));
        return this;
    }

    public FolderSelection orderById(boolean desc) {
        orderBy("folder." + FolderColumns._ID, desc);
        return this;
    }

    public FolderSelection orderById() {
        return orderById(false);
    }

    public FolderSelection title(String... value) {
        addEquals(FolderColumns.TITLE, value);
        return this;
    }

    public FolderSelection titleNot(String... value) {
        addNotEquals(FolderColumns.TITLE, value);
        return this;
    }

    public FolderSelection titleLike(String... value) {
        addLike(FolderColumns.TITLE, value);
        return this;
    }

    public FolderSelection titleContains(String... value) {
        addContains(FolderColumns.TITLE, value);
        return this;
    }

    public FolderSelection titleStartsWith(String... value) {
        addStartsWith(FolderColumns.TITLE, value);
        return this;
    }

    public FolderSelection titleEndsWith(String... value) {
        addEndsWith(FolderColumns.TITLE, value);
        return this;
    }

    public FolderSelection orderByTitle(boolean desc) {
        orderBy(FolderColumns.TITLE, desc);
        return this;
    }

    public FolderSelection orderByTitle() {
        orderBy(FolderColumns.TITLE, false);
        return this;
    }
}
