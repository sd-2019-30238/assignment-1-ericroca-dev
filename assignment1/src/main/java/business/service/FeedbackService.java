package business.service;

public interface FeedbackService {

    String submitFeedback(Integer orderId, Integer userId, String details);
}
