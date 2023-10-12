package by.ratkevich.messagebroker.messageproduction;

import by.ratkevich.messagebroker.message.Message;

public class MessageFactory {
    private int INITIAL_NEXT_MESSAGE_INDEX = 1;
    private int nextMessageIndex;

    public MessageFactory () {
        this.nextMessageIndex = INITIAL_NEXT_MESSAGE_INDEX;
    }

    public synchronized Message createMessage () {

        String message = "Message " + nextMessageIndex;
        nextMessageIndex++;
        return new Message (message);
    }
}
