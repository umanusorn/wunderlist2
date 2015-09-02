package com.vi8e.um.wunderlist.provider.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.BaseModel;

/**
 * Table of list
 */
public interface ListModel extends BaseModel {

    /**
     * Get the {@code list_title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getListTitle();

    /**
     * The commercial name of this company.
     * Cannot be {@code null}.
     */
    @NonNull
    String getNumLateTask();

    /**
     * Get the {@code num_current_task} value.
     * Can be {@code null}.
     */
    @Nullable
    String getNumCurrentTask();

    /**
     * Get the {@code folder_id} value.
     * Can be {@code null}.
     */
    @Nullable
    String getFolderId();

    /**
     * Get the {@code ispinned} value.
     * Can be {@code null}.
     */
    @Nullable
    String getIspinned();

    /**
     * Get the {@code isdisturb} value.
     * Can be {@code null}.
     */
    @Nullable
    String getIsdisturb();

    /**
     * Get the {@code img_path} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImgPath();

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    String getType();
}
