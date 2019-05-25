package business.services.write;

import business.interfaces.write.FeedbackWrite;
import data.implementation.FeedbackDAOImpl;
import data.service.FeedbackDAO;
import mediator.ConcreteMediator;
import mediator.Mediator;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FeedbackWriteService implements FeedbackWrite {

    private Mediator mediator;

    public FeedbackWriteService() {
        mediator = new ConcreteMediator();
    }

    @Override
    public String submitFeedback(Integer orderId, Integer userId, String details) {
        return mediator.submitFeedback(orderId, userId, details);
    }
}
