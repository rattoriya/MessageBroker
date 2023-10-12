package by.ratkevich.messagebroker.messageConsuming;

import by.ratkevich.messagebroker.message.Message;
import by.ratkevich.messagebroker.messageBroker.MessageBroker;
import by.ratkevich.messagebroker.messageproduction.MessageProduction;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class MessageConsuming implements Runnable{

    private int minMessagesToConsume;
    private int threadNumber;
    private MessageBroker messageBroker;

    public MessageConsuming (MessageBroker messageBroker, int threadNumber, int minMessagesToConsume) {
        this.messageBroker = messageBroker;
        this.threadNumber= threadNumber;
        this.minMessagesToConsume= minMessagesToConsume;
    }

    public int getMinMessagesToConsume () {
        return minMessagesToConsume;
    }

    @Override
    public void run () {

        try {
            while (!currentThread().isInterrupted()) {
                SECONDS.sleep(2);
                Message message = messageBroker.consume(threadNumber, this);
            }
        }
        catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }
}
