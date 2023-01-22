package com.apiservices.creveton.controller;

import com.apiservices.creveton.dto.QuestionDTO;
import com.apiservices.creveton.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/rest", name = "app_creveton_")
public class QuestionController {

    @Autowired
    IQuestionService questionService;

    @GetMapping("/questions")
    public List<QuestionDTO> loadQuestions()
    {
        return questionService.getAllQuestions();
    }


    @GetMapping("/showQuestions")
    public List<QuestionDTO> findQuestionbyDomaine(Model model, @RequestParam String domaine)
    {
        List<QuestionDTO> questions= questionService.showQuestionbyDomaine(domaine);
        model.addAttribute("questions",questions);
        return questions;
    }

}
