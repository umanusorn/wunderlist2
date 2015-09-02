package com.vi8e.um.wunderlist.provider.taskfile;

import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.BaseModel;

/**
 * Data model for the {@code task_file} table.
 */
public interface TaskFileModel extends BaseModel {

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTitle();

    /**
     * Get the {@code taskid} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTaskid();

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    String getType();

    /**
     * Get the {@code url} value.
     * Can be {@code null}.
     */
    @Nullable
    String getUrl();
}
