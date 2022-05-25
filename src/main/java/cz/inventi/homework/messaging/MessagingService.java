package cz.inventi.homework.messaging;

public interface MessagingService {
    void sendMessage(String message);

    void receiveMessage(String Message);
}
