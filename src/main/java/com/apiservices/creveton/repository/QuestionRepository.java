package com.apiservices.creveton.repository;

import com.apiservices.creveton.model.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {
    @Query(value = "SELECT q FROM QuestionEntity q WHERE q.domaine=?1")
    List<QuestionEntity> findQuestionbyDomaine(String domaine);
}
