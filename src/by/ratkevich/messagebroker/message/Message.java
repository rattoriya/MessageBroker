package by.ratkevich.messagebroker.message;

public class Message {

    private String message;

    public Message (String message) {
        this.message = message;
    }

    public String getMessage () {
        return message;
    }

    @Override
    public boolean equals (Object object) {
        if (object == null) return false;
        if (this==object) return true;
        if (getClass()!=object.getClass()) return false;

        Message objectMessage = (Message) object;
        return objectMessage.getMessage().equals (message);
    }

    @Override
    public int hashCode () {
        return message.hashCode()*17*22;
    }

    @Override
    public String toString () {
        return message;
    }

}
