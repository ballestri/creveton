package com.apiservices.creveton.service;

import com.apiservices.creveton.dto.QuestionDTO;
import com.apiservices.creveton.model.QuestionEntity;
import com.apiservices.creveton.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionRepository repository;

    public List<QuestionDTO> getAllQuestions(){
        return repository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public List<QuestionDTO> showQuestionbyDomaine(String domaine){
        return repository.findQuestionbyDomaine(domaine).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
    private QuestionDTO convertEntityToDto(QuestionEntity question){
        QuestionDTO questionDTO=new QuestionDTO();
        questionDTO.setNumero(question.getNumero());
        questionDTO.setQ(question.getQ());
        questionDTO.setR1(question.getR1());
        questionDTO.setR2(question.getR2());
        questionDTO.setR3(question.getR3());
        questionDTO.setR4(question.getR4());
        questionDTO.setRj(question.getRj());
        questionDTO.setDomaine(question.getDomaine());
        questionDTO.setLevel(question.getLevel());
        questionDTO.setDifficulte(question.getDifficulte());
        return questionDTO;
    }
}
