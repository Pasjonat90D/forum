package com.myproject.forum.service.impl;

import com.myproject.forum.models.Topic;
import com.myproject.forum.repository.CategoriesRepository;
import com.myproject.forum.repository.TopicsRepository;
import com.myproject.forum.repository.UsersRepository;
import com.myproject.forum.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TopicsServiceImpl implements TopicsService {

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Topic getTopicById(long id) {
        return topicsRepository.findById(id);
    }

    @Override
    public void saveTopic(Topic topic, String nameCategory, String userEmail) {
        topic.setCategory(categoriesRepository.findByName(nameCategory));
        topic.setUser(usersRepository.findByEmail(userEmail));
        topic.setDate(new Date());
        topicsRepository.save(topic);
    }

    @Override
    public Topic createNewTopic() {
        return new Topic();
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicsRepository.findAll();
    }
}
