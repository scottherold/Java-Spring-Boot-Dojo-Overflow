package com.sherold.dojooverflow.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sherold.dojooverflow.models.Question;
import com.sherold.dojooverflow.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
	// returns Tag objects
	List<Tag> findAll();
	
	// Find Tag by Subject (explicit)
	Optional<Tag> findBySubject(String subject);
	
	// Find Tags by Subject (containing)
	List<Tag> findBySubjectContaining(String subject);
}
