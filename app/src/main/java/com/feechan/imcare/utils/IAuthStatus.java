package com.feechan.imcare.utils;

/**
 * Created by Feechan on 8/23/2017.
 */

public interface IAuthStatus {
    void onAuthFailed(String message);
    void onAuthSuccess();
}
