package by.ratkevich.messagebroker.messageBroker;

import by.ratkevich.messagebroker.message.Message;
import by.ratkevich.messagebroker.messageConsuming.MessageConsuming;
import by.ratkevich.messagebroker.messageproduction.MessageProduction;

import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Thread.currentThread;

public class MessageBroker {

    private Queue<Message> messages;

    public MessageBroker () {
        messages = new ArrayDeque<>();
    }

    public synchronized void produce (Message message, int threadNumber, MessageProduction messageProduction) {

        try {

            while (messages.size() >= messageProduction.getMinMessagesToProduce()) {

                super.wait();
            }

            messages.add(message);
            System.out.println("Produced "+ message+" by producing thread "+ threadNumber);

            super.notifyAll();
        }
        catch (InterruptedException e) {
            currentThread().interrupt();
        }

    }

    public synchronized Message consume (int threadNumber, MessageConsuming messageConsuming) {

        try {

            while (messages.isEmpty()||
                    messages.size() <= messageConsuming.getMinMessagesToConsume()) {
                super.wait();
            }

            Message message = messages.poll();
            System.out.println("Consumed "+ message+" by consuming thread "+ threadNumber);

            super.notifyAll();

            return message;
        }
        catch (InterruptedException e) {
            currentThread().interrupt();
            return null;
        }

    }

}
