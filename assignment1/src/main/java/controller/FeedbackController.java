package controller;

import business.services.write.FeedbackWriteService;
import business.interfaces.write.FeedbackWrite;
import models.Feedback;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedbackController {

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String submitFeedback(@RequestBody Feedback feedback) {
        FeedbackWrite feedbackWriteService = new FeedbackWriteService();
        return feedbackWriteService.submitFeedback(feedback.getOrderID(), feedback.getUserID(), feedback.getDetails());
    }
}
