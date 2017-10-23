
import cryptosystem.Cryptor;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import keys.PublicKeys;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vignesh
 */
public class Alice {
    static ServerSocket serverSocket;
    static Socket socket;
    
    public static void main(String[] arg) throws IOException, ClassNotFoundException {
        serverSocket = new ServerSocket(6059);
        socket = serverSocket.accept();
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        PublicKeys publicKeys = (PublicKeys) inputStream.readObject();
        System.out.println("Enter the plain text: ");
        String plainText = new Scanner(System.in).nextLine();
        
        
        Cryptor cryptor = new Cryptor(publicKeys);
        String c1 = cryptor.encyptedC1();
        
        
        String c2 = "";
        String cipher = "";
        
        for (int i = 0; i < plainText.length(); i++) {
            c2 = cryptor.encryptedC2((int) plainText.charAt(i) + "");
            cipher += c2 + "#";
        }
        
        System.out.println("Cypher(C1): " + c1);
        System.out.println("Cypher(C2): " + cipher);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(c1);
        dataOutputStream.writeUTF(cipher);
        
    }
}
