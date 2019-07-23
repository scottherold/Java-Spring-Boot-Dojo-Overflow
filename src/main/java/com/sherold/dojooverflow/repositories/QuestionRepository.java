package com.sherold.dojooverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sherold.dojooverflow.models.Question;
import com.sherold.dojooverflow.models.Tag;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
	// returns Question objects
	List<Question> findAll();
}
