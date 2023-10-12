package by.ratkevich.messagebroker;

import by.ratkevich.messagebroker.messageBroker.MessageBroker;
import by.ratkevich.messagebroker.messageConsuming.MessageConsuming;
import by.ratkevich.messagebroker.messageproduction.MessageFactory;
import by.ratkevich.messagebroker.messageproduction.MessageProduction;

public class Main {
    public static void main(String[] args) {


        MessageBroker messageBroker = new MessageBroker ();
        MessageFactory messageFactory = new MessageFactory();

        Thread firstProducingThread = new Thread (new MessageProduction(messageBroker, messageFactory, 1, 15));
        Thread secondProducingThread = new Thread (new MessageProduction(messageBroker, messageFactory, 2, 10));
        Thread thirdProducingThread = new Thread (new MessageProduction(messageBroker, messageFactory, 3, 5));

        Thread firstConsumingThread = new Thread (new MessageConsuming(messageBroker, 1, 5));
        Thread secondConsumingThread = new Thread (new MessageConsuming(messageBroker, 2, 10));
        Thread thirdConsumingThread = new Thread (new MessageConsuming(messageBroker, 3, 15));

        start (firstProducingThread, secondProducingThread, thirdProducingThread,
                firstConsumingThread,secondConsumingThread, thirdConsumingThread);
    }

    public static void start (Thread ...threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }
}