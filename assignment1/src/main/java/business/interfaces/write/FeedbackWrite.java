package business.interfaces.write;

public interface FeedbackWrite {

    String submitFeedback(Integer orderId, Integer userId, String details);
}
