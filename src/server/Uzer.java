package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Uzer {
    private String name;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Uzer(Socket socket) throws IOException {
        this.socket = socket;
        in = new DataInputStream(this.socket.getInputStream()); //ввод
        out  = new DataOutputStream(this.socket.getOutputStream()); //вывод
    }

    public DataInputStream getIn() {
        return in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
