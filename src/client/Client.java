package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try{
            Socket socket =new Socket("45.9.41.199",9123); //����������� � ��������
            Scanner scanner = new Scanner(System.in);


            DataOutputStream out = new DataOutputStream(socket.getOutputStream());// ������ ������ (������ ������, � ������ ���������(in ))
            DataInputStream in = new DataInputStream(socket.getInputStream()); // ����(������ ��������)
           String messege;
           Thread thread = new Thread (new Runnable(){
               public void run(){
                   try {
                       while (true)
                           System.out.println(in.readUTF());//���������
                   }catch (IOException e){
                       System.out.println("�������� ���������� � ��������");
                   }
               }

           });
           thread.start();
            while (true) {
                messege = scanner.nextLine();
                out.writeUTF(messege); //�������� ������� ���������

            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
