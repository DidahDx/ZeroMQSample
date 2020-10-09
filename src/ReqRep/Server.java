import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Server {

    public static void main(String[] args) {

        try (ZContext context = new ZContext()) {
            //  Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            socket.bind("tcp://128.199.174.204:5555");

            while (!Thread.currentThread().isInterrupted()) {
                byte[] reply = socket.recv(0);
                System.out.println(
                        "Received " + ": [" + new String(reply, ZMQ.CHARSET) + "]"
                );
                String response = "world";
                socket.send(response.getBytes(ZMQ.CHARSET), 0);
                Thread.sleep(10000); //  Do some 'work'
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
