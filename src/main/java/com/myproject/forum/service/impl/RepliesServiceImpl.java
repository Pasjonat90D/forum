package com.myproject.forum.service.impl;

import com.myproject.forum.models.Reply;
import com.myproject.forum.repository.RepliesRepository;
import com.myproject.forum.repository.TopicsRepository;
import com.myproject.forum.repository.UsersRepository;
import com.myproject.forum.service.RepliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RepliesServiceImpl implements RepliesService {

    @Autowired
    private RepliesRepository repliesRepository;

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public void saveReply(Reply reply, long idTopic, String userEmail) {
        reply.setTopic(topicsRepository.findById(idTopic));
        reply.setUser(usersRepository.findByEmail(userEmail));
        reply.setDate(new Date());
        repliesRepository.save(reply);
    }

    @Override
    public Reply createReply() {
        return new Reply();
    }
}
