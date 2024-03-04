package com.vku.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vku.models.Feedback;
import com.vku.repositories.FeedbackRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> getAllCategories() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> getFeedbackById(int feedbackId) {
        return feedbackRepository.findById(feedbackId);
    }

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(int feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
}