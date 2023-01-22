package com.apiservices.creveton.config;

import com.apiservices.creveton.model.QuestionEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
public class SpringBatchConfig {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;


    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    public JsonItemReader<QuestionEntity> jsonItemReader() {
        return new JsonItemReaderBuilder<QuestionEntity>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(QuestionEntity.class))
                .resource(new ClassPathResource("data.json"))
                .name("questionJsonItemReader")
                .build();
    }

    @Bean
    public QuestionItemProcessor processor() {
        return new QuestionItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<QuestionEntity> writer() {
        JdbcBatchItemWriter<QuestionEntity> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("insert into questionnaire (r2,q,r3,r4,numero,level,domaine,rj,difficulte,r1) values (:r2,:q,:r3,:r4,:numero,:level,:domaine,:rj,:difficulte,:r1)");
        writer.setDataSource(dataSource);
        return writer;
    }


    @Bean
    public Job writeQuestionDataIntoSqlDb() {
        JobBuilder jobBuilder = jobBuilderFactory.get("QUESTION_JOB");
        jobBuilder.incrementer(new RunIdIncrementer());
        FlowJobBuilder flowJobBuilder = jobBuilder.flow(getFirstStep()).end();
        Job job = flowJobBuilder.build();
        return job;
    }

    @Bean
    public Step getFirstStep() {
        StepBuilder stepBuilder = stepBuilderFactory.get("getFirstStep");
        SimpleStepBuilder<QuestionEntity, QuestionEntity> simpleStepBuilder = stepBuilder.chunk(1);
        return simpleStepBuilder.reader(jsonItemReader()).processor(processor()).writer(writer()).build();
    }
}
