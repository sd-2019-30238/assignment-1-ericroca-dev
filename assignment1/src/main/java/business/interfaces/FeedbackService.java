package business.interfaces;

public interface FeedbackService {

    String submitFeedback(Integer orderId, Integer userId, String details);
}
