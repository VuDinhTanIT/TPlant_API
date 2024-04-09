package com.vku.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vku.models.Feedback;
import com.vku.services.FeedbackService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/feedbacks")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllCategories() {
        List<Feedback> feedbacks = feedbackService.getAllCategories();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/{feedbackId}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long feedbackId) {
        Optional<Feedback> feedback = feedbackService.getFeedbackById(feedbackId);
        return feedback.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
    	feedback.setFeedbackId(null);
        Feedback savedFeedback = feedbackService.saveFeedback(feedback);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }

    @PutMapping("/{feedbackId}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long feedbackId, @RequestBody Feedback feedback) {
        Optional<Feedback> existingFeedback = feedbackService.getFeedbackById(feedbackId);
        if (existingFeedback.isPresent()) {
            feedback.setFeedbackId(feedbackId);
            Feedback updatedFeedback = feedbackService.saveFeedback(feedback);
            return new ResponseEntity<>(updatedFeedback, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long feedbackId) {
        Optional<Feedback> existingFeedback = feedbackService.getFeedbackById(feedbackId);
        if (existingFeedback.isPresent()) {
            feedbackService.deleteFeedback(feedbackId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}