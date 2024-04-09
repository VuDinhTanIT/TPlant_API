package com.vku.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vku.models.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
	