package mediator.handlers;

public interface Handler {

    <T> T handle(Query query);
}
