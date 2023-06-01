package exercise.connections;

import exercise.TcpConnection;

import java.util.List;

// BEGIN
public class Connected implements Connection {
    private TcpConnection connection;

    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        System.out.println("Error");
    }

    @Override
    public void disconnect() {
        this.connection.setConnection(new Disconnected(connection));
    }

    @Override
    public void write(String data) {
        List<String> tempBuffer = this.connection.getBuffer();
        tempBuffer.add(data);
        this.connection.setBuffer(tempBuffer);
    }


    @Override
    public void write() {

    }

    @Override
    public String getCurrentState() {
        return "connected";
    }
}
// END
