package com.vi8e.um.wunderlist.provider.folder;

import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.BaseModel;

/**
 * Data model for the {@code folder} table.
 */
public interface FolderModel extends BaseModel {

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTitle();
}
