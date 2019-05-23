package controller;

import business.services.FeedbackServiceImpl;
import business.interfaces.FeedbackService;
import models.Feedback;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedbackController {

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String submitFeedback(@RequestBody Feedback feedback) {
        FeedbackService feedbackService = new FeedbackServiceImpl();
        return feedbackService.submitFeedback(feedback.getOrderID(), feedback.getUserID(), feedback.getDetails());
    }
}
