package PushPull;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

public class Pull {

    public static void main(String[] args){
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(SocketType.PULL);
        System.out.println("Starting the puller...");
        socket.bind("tcp://localhost:5559");

        while (!Thread.currentThread().isInterrupted()) {
            byte[] rawRequest = socket.recv();
            String cleanRequest = new String(rawRequest, 0, rawRequest.length - 1);
            System.out.println("pulled some data : "+cleanRequest);
        }
    }
}
