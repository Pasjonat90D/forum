package com.myproject.forum.controller.page;

import com.myproject.forum.models.Reply;
import com.myproject.forum.models.Topic;
import com.myproject.forum.service.CategoriesService;
import com.myproject.forum.service.RepliesService;
import com.myproject.forum.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class TopicsController {

    @Autowired
    private TopicsService topicsService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private RepliesService repliesService;

    @RequestMapping(value = "page/topic/{id}" , method = RequestMethod.GET)
    public String getTopic(Model model , @PathVariable("id") long id){
        model.addAttribute("reply", repliesService.createReply());
        model.addAttribute("topic" , topicsService.getTopicById(id));
        return "/page/topic";
    }

    @RequestMapping(value = "page/topic/{id}", method = RequestMethod.POST)
    public String addReplies(@PathVariable("id") long id, @ModelAttribute Reply reply,
                             Principal principal) {
        repliesService.saveReply(reply,id,principal.getName());
        return "redirect:/page/topic/{id}";
    }

    @RequestMapping(value = "page/addTopic", method = RequestMethod.GET)
    public String addTopic(Model model){
        model.addAttribute("categories", categoriesService.getAllCategories());
        model.addAttribute("topic", topicsService.createNewTopic());
        return "/page/addTopic";
    }

    @RequestMapping(value = "/page/addTopic", method = RequestMethod.POST)
    public String createTopic(@ModelAttribute Topic topic,
                                @RequestParam(value = "tagListName") String nameCategory,
                                Principal principal) {
        topicsService.saveTopic(topic,nameCategory,principal.getName());
        return "redirect:/";
    }

}
