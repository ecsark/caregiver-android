package com.care.caregiver.conversation;

import com.care.caregiver.util.ServerResponse;

import java.lang.reflect.Type;

/**
 * Created by RedAlice64 on 2014/12/24.
 */
public interface ConversationListener {
    public void onResponseArrive(ServerResponse response);
}
