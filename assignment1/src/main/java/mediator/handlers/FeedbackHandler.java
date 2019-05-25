package mediator.handlers;

import mediator.events.AddFeedback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeedbackHandler implements Handler {

    private Integer orderId;
    private Integer userId;
    private String details;

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public <T> T handle(Request request) {
        if (request == Request.ADDFEEDBACK) {
            Pattern pattern = Pattern.compile("^[a-z0-9\\s_-]{1,65000}$");
            Matcher detailsMatcher = pattern.matcher(details);
            if (detailsMatcher.matches()) {
                if (orderId > 0 && userId > 0) {
                    new AddFeedback().addFeedback(orderId, userId, details);
                    return (T) "Feedback submitted!";
                }
            }
            return (T) "";
        }
        return null;
    }
}
