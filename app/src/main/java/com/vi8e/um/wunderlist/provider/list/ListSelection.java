package com.vi8e.um.wunderlist.provider.list;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.vi8e.um.wunderlist.provider.base.AbstractSelection;

/**
 * Selection for the {@code list} table.
 */
public class ListSelection extends AbstractSelection<ListSelection> {
    @Override
    public Uri uri() {
        return ListColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code ListCursor} object, which is positioned before the first entry, or null.
     */
    public ListCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new ListCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public ListCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public ListCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public ListSelection id(long... value) {
        addEquals("list." + ListColumns._ID, toObjectArray(value));
        return this;
    }


    public ListSelection listTitle(String... value) {
        addEquals(ListColumns.LIST_TITLE, value);
        return this;
    }

    public ListSelection listTitleNot(String... value) {
        addNotEquals(ListColumns.LIST_TITLE, value);
        return this;
    }

    public ListSelection listTitleLike(String... value) {
        addLike(ListColumns.LIST_TITLE, value);
        return this;
    }

    public ListSelection numLateTask(String... value) {
        addEquals(ListColumns.NUM_LATE_TASK, value);
        return this;
    }

    public ListSelection numLateTaskNot(String... value) {
        addNotEquals(ListColumns.NUM_LATE_TASK, value);
        return this;
    }

    public ListSelection numLateTaskLike(String... value) {
        addLike(ListColumns.NUM_LATE_TASK, value);
        return this;
    }

    public ListSelection numCurrentTask(String... value) {
        addEquals(ListColumns.NUM_CURRENT_TASK, value);
        return this;
    }

    public ListSelection numCurrentTaskNot(String... value) {
        addNotEquals(ListColumns.NUM_CURRENT_TASK, value);
        return this;
    }

    public ListSelection numCurrentTaskLike(String... value) {
        addLike(ListColumns.NUM_CURRENT_TASK, value);
        return this;
    }

    public ListSelection folderId(String... value) {
        addEquals(ListColumns.FOLDER_ID, value);
        return this;
    }

    public ListSelection folderIdNot(String... value) {
        addNotEquals(ListColumns.FOLDER_ID, value);
        return this;
    }

    public ListSelection folderIdLike(String... value) {
        addLike(ListColumns.FOLDER_ID, value);
        return this;
    }

    public ListSelection ispinned(String... value) {
        addEquals(ListColumns.ISPINNED, value);
        return this;
    }

    public ListSelection ispinnedNot(String... value) {
        addNotEquals(ListColumns.ISPINNED, value);
        return this;
    }

    public ListSelection ispinnedLike(String... value) {
        addLike(ListColumns.ISPINNED, value);
        return this;
    }

    public ListSelection isdisturb(String... value) {
        addEquals(ListColumns.ISDISTURB, value);
        return this;
    }

    public ListSelection isdisturbNot(String... value) {
        addNotEquals(ListColumns.ISDISTURB, value);
        return this;
    }

    public ListSelection isdisturbLike(String... value) {
        addLike(ListColumns.ISDISTURB, value);
        return this;
    }

    public ListSelection imgPath(String... value) {
        addEquals(ListColumns.IMG_PATH, value);
        return this;
    }

    public ListSelection imgPathNot(String... value) {
        addNotEquals(ListColumns.IMG_PATH, value);
        return this;
    }

    public ListSelection imgPathLike(String... value) {
        addLike(ListColumns.IMG_PATH, value);
        return this;
    }

    public ListSelection type(String... value) {
        addEquals(ListColumns.TYPE, value);
        return this;
    }

    public ListSelection typeNot(String... value) {
        addNotEquals(ListColumns.TYPE, value);
        return this;
    }

    public ListSelection typeLike(String... value) {
        addLike(ListColumns.TYPE, value);
        return this;
    }
}
