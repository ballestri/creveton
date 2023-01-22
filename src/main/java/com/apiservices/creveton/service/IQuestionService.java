package com.apiservices.creveton.service;

import com.apiservices.creveton.dto.QuestionDTO;
import java.util.List;

public interface IQuestionService {

    List<QuestionDTO> getAllQuestions();
    List<QuestionDTO> showQuestionbyDomaine(String domaine);
}
