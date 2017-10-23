
import cryptosystem.Cryptor;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import keys.ElGamalKeys;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vignesh
 */
public class Bob {
    static ServerSocket serverSocket;
    static Socket socket;
    static ObjectOutputStream outputStream;
    static ElGamalKeys elGamalKeys;
    
    public static void main(String args[]) throws IOException {
        socket = new Socket("localhost", 6059);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        elGamalKeys = new ElGamalKeys();
        
        Cryptor cryptor = new Cryptor(elGamalKeys.getPublicKeys(), elGamalKeys.getPrivateKey());
        
        outputStream.writeObject(elGamalKeys.getPublicKeys());
        outputStream.flush();
        
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String c1 = (String) dataInputStream.readUTF();
        String cipher = (String) dataInputStream.readUTF();
        
        String c2[] = cipher.split("[#]+");
        String plainText = "";
        
        for (String c2String : c2) {
            plainText += "" + (char)Integer.parseInt(cryptor.decrypt(c1, c2String));
        }
        
        
        System.out.println("C1: " + c1);
        System.out.println("Cypher(C2): " + cipher);
        System.out.println("Plain: " + plainText);
        
    }    
}
