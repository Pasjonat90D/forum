package com.myproject.forum.service;

import com.myproject.forum.models.Topic;

import java.util.List;

public interface TopicsService {

    Topic getTopicById(long id);
    void saveTopic(Topic topic, String nameCategory, String user);
    Topic createNewTopic();
    List<Topic> getAllTopics();
}
