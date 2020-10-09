package PubSub;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

public class Sub {

    public static void main(String[] args){
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(SocketType.SUB);
        System.out.println("Starting the subscribing...");
        socket.bind("tcp://localhost:5559");

        while (!Thread.currentThread().isInterrupted()) {
            byte[] rawRequest = socket.recv();
            String cleanRequest = new String(rawRequest, 0, rawRequest.length - 1);
            System.out.println("subscribed some data : "+cleanRequest);
        }
    }
}
