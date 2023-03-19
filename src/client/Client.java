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
            Socket socket =new Socket("45.9.41.199",9123); //соединяемся с сервером
            Scanner scanner = new Scanner(System.in);


            DataOutputStream out = new DataOutputStream(socket.getOutputStream());// объект вывода (клиент отдает, а сервер принемает(in ))
            DataInputStream in = new DataInputStream(socket.getInputStream()); // ввод(клиент получает)
           String messege;
           Thread thread = new Thread (new Runnable(){
               public void run(){
                   try {
                       while (true)
                           System.out.println(in.readUTF());//прочитать
                   }catch (IOException e){
                       System.out.println("Потеряно соединение с сервиром");
                   }
               }

           });
           thread.start();
            while (true) {
                messege = scanner.nextLine();
                out.writeUTF(messege); //передали серверу сообщение

            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
