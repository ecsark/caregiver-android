package com.care.caregiver.util;

import android.content.Context;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RedAlice64 on 2014/12/25.
 */
public class Questions extends ServerResponse{
    @SerializedName("questions")
    public List<String> questions;

    @Override
    public void ShowDialog(Context context) {

    }
}
