package business.services;

import business.interfaces.FeedbackService;
import data.implementation.FeedbackDAOImpl;
import data.service.FeedbackDAO;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Override
    public String submitFeedback(Integer orderId, Integer userId, String details) {
        Pattern pattern = Pattern.compile("^[a-z0-9\\s_-]{1,65000}$");
        Matcher detailsMatcher = pattern.matcher(details);
        if (detailsMatcher.matches()) {
            FeedbackDAO feedbackDAO = new FeedbackDAOImpl();
            if (orderId > 0 && userId > 0) {
                feedbackDAO.addFeedback(orderId, userId, details);
                return "Feedback submitted!";
            }
        }
        return "";
    }
}
