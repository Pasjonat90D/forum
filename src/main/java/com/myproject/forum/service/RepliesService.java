package com.myproject.forum.service;

import com.myproject.forum.models.Reply;

public interface RepliesService {
    void saveReply(Reply reply, long idTopic, String userEmail);
    Reply createReply();
}
