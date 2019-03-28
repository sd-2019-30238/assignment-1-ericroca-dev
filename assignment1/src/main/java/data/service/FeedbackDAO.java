package data.service;

public interface FeedbackDAO {

    Integer addFeedback(Integer orderID, Integer userID, String details);

    void listFeedbacks();

    void updateFeedback(Integer feedbackID, Integer orderID, Integer userID, String details);

    void deleteFeedback(Integer feedbackID);
}
