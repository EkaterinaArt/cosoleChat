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
            ServerSocket serverSocket = new ServerSocket(9123); //������ IP � ��������� Port
            System.out.println("������ �������");
            while (true){
                Socket socket = serverSocket.accept(); //���� � ��������� ����������� Uzera
                System.out.println("������ �����������");
                Uzer uzer = new Uzer(socket);

                uzers.add(uzer);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            uzer.getOut().writeUTF("������� ���");
                            String name = uzer.getIn().readUTF(); //������ ������ ���
                            String myName ;

                            for (int i = 0; i < uzers.size(); i++) {


                                if (name.equals(name)) {

                                    uzer.setName(name);
                                    uzer.getOut().writeUTF(uzer.getName() + "����� ����������"); //������ �����
                                } else {
                                    System.out.println("�������� ������ Nick Name");
                               }
                            }
                            String clientMessage;
                            while (true){
                                clientMessage = uzer.getIn().readUTF();//������ ������, ������ ������
                                System.out.println(clientMessage);
                                for (Uzer uzer1 : uzers)

                                    uzer1.getOut().writeUTF(clientMessage); //������ ������� (������), ������  �����
                            }
                        }catch (IOException e){
                            System.out.println("������ ����������");
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
