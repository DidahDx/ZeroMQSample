package PushPull;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

public class Push {
    public static void main(String[] arg) {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(SocketType.PUSH);
        System.out.println("connecting to a pulling client...");
        socket.connect("tcp://localhost:5559");

        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String plainRequest = "Hello ";

            byte[] byteRequest = plainRequest.getBytes();
            byteRequest[byteRequest.length - 1] = 0;
            System.out.println("sending push " + i + plainRequest);
            socket.send(byteRequest, 0);
        }
    }

}
