package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        ArrayList<Socket> sockets = new ArrayList<>();
        ArrayList<Uzer> uzers = new ArrayList<>();

        // 0.0.0.0 - 255.255.255.255 NAT
        try {
            ServerSocket serverSocket = new ServerSocket(9123); //Связка IP и свободный Port
            System.out.println("Сервер запущен");
            while (true){
                Socket socket = serverSocket.accept(); //Ждем и сохраняем подключения Uzera
                System.out.println("Клиент подключился");
                Uzer uzer = new Uzer(socket);

                uzers.add(uzer);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            uzer.getOut().writeUTF("Введите Имя");
                            String name = uzer.getIn().readUTF(); //сервер читает имя
                            String myName ;

                            for (int i = 0; i < uzers.size(); i++) {


                                if (name.equals(name)) {

                                    uzer.setName(name);
                                    uzer.getOut().writeUTF(uzer.getName() + "Добро пожаловать"); //Сервер пишет
                                } else {
                                    System.out.println("Выберите другой Nick Name");
                               }
                            }
                            String clientMessage;
                            while (true){
                                clientMessage = uzer.getIn().readUTF();//клиент вводит, сервер читает
                                System.out.println(clientMessage);
                                for (Uzer uzer1 : uzers)

                                    uzer1.getOut().writeUTF(clientMessage); //клиент выводит (отдает), сервер  пишет
                            }
                        }catch (IOException e){
                            System.out.println("Клиент отключился");
                           uzers.remove(uzer);
                        }
                    }
                });
                thread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
