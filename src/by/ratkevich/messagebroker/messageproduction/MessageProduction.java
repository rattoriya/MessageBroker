package by.ratkevich.messagebroker.messageproduction;

import by.ratkevich.messagebroker.message.Message;
import by.ratkevich.messagebroker.messageBroker.MessageBroker;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class MessageProduction implements Runnable{

    private int minMessagesToProduce;
    private int threadNumber;
    private MessageBroker messageBroker;
    private MessageFactory messageFactory;

    public MessageProduction (MessageBroker messageBroker, MessageFactory messageFactory, int threadNumber, int minMessagesToProduce) {
        this.messageBroker = messageBroker;
        this.messageFactory = messageFactory;
        this.threadNumber = threadNumber;
        this.minMessagesToProduce = minMessagesToProduce;
    }

    public int getMinMessagesToProduce () {
        return minMessagesToProduce;
    }

    @Override
    public void run () {

        try {
            while (!currentThread().isInterrupted()) {

                Message message = messageFactory.createMessage();

                SECONDS.sleep(1);

                messageBroker.produce(message, threadNumber, this);
            }
        }
        catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }


}
