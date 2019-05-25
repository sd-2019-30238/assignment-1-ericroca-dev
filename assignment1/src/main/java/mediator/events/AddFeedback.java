package mediator.events;

import data.implementation.FeedbackDAOImpl;
import data.service.FeedbackDAO;

public class AddFeedback {

    public void addFeedback(Integer orderId, Integer userId, String details) {
        FeedbackDAO feedbackDAO = new FeedbackDAOImpl();
        feedbackDAO.addFeedback(orderId, userId, details);
    }
}
