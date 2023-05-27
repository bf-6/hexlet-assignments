package exercise;

import exercise.connections.Connected;
import exercise.connections.Connection;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {
    private Connection connection;
    private String ip;
    private int port;

    private List<String> buffer = new ArrayList<>();

    public TcpConnection(String ip, int port) {
        this.connection = new Connected(this);
        this.ip = ip;
        this.port = port;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getCurrentState() {
        return this.connection.getCurrentState();
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void write(String data) {
        this.connection.write(data);
    }

    public void connect() {
        this.connection.connect();
    }

    public void disconnect() {
        this.connection.disconnect();
    }

    public List<String> getBuffer() {
        return buffer;
    }

    public void setBuffer(List<String> buffer) {
        this.buffer = buffer;
    }
}
// END
