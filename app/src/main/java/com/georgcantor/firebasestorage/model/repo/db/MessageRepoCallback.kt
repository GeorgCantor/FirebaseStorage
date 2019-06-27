package com.georgcantor.firebasestorage.model.repo.db

import com.georgcantor.firebasestorage.model.entity.Chat

interface MessageRepoCallback {

    fun onMessageComing(chat: Chat)

    fun onMessageDelete(position: Int)

    fun onMessageUpdate(position: Int, chat: Chat)
}