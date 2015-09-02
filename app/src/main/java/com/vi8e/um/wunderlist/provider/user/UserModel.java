package com.vi8e.um.wunderlist.provider.user;

import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.BaseModel;

/**
 * Data model for the {@code user} table.
 */
public interface UserModel extends BaseModel {

    /**
     * Get the {@code user_title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getUserTitle();

    /**
     * Get the {@code img_path} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImgPath();
}
