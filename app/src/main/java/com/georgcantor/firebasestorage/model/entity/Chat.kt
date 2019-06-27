package com.georgcantor.firebasestorage.model.entity

data class Chat(
        var user: String?,
        var messageId: String?,
        var message: String?,
        var time: String?,
        var isSameUser: Boolean?
)