package com.apiservices.creveton.config;

import com.apiservices.creveton.model.QuestionEntity;
import org.springframework.batch.item.ItemProcessor;

public class QuestionItemProcessor  implements ItemProcessor<QuestionEntity, QuestionEntity> {
    @Override
    public QuestionEntity process(QuestionEntity question) throws Exception {
        return question;
    }
}
