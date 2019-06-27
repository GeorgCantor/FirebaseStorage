package com.georgcantor.firebasestorage.model.repo.db

import com.georgcantor.firebasestorage.model.entity.Chat
import com.georgcantor.firebasestorage.model.repo.References
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

class MessageRepo(private val references: References) {

    private val messageIdList = mutableListOf<String>()

    fun getMessage(repoCallback: MessageRepoCallback) {
        references.chatRefs()
                .addChildEventListener(object : ChildEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                    }

                    override fun onChildChanged(snapshot: DataSnapshot, p1: String?) {
                        val chat = snapshot.getValue(Chat::class.java) as Chat
                        val position = messageIdList.indexOf(chat.messageId)
                        repoCallback.onMessageUpdate(position, chat)
                    }

                    override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
                        val chat = snapshot.getValue(Chat::class.java) as Chat
                        chat.messageId?.let { messageIdList.add(it) }
                        repoCallback.onMessageComing(chat)
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {
                        val chat = snapshot.getValue(Chat::class.java) as Chat
                        val position = messageIdList.indexOf(chat.messageId)
                        messageIdList.removeAt(position)
                        repoCallback.onMessageDelete(position)
                    }
                })
    }

    fun sendMessage(chat: Chat) {
        val messageId = references.chatRefs().key
        messageId?.let {
            references.chatRefs()
                    .child(it)
                    .setValue(chat.copy(messageId = it))
        }
    }
}