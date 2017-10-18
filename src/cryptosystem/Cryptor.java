package cryptosystem;


import java.math.BigInteger;
import java.util.Random;
import keys.ElGamalKeys;
import keys.PrivateKey;
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
public class Cryptor {
    int bits = 9;
    static BigInteger r;
    static BigInteger c1dInverse;
    PublicKeys publicKeys;
    PrivateKey privateKey;
    
    public Cryptor(PublicKeys publicKeys) {
        r = getRandomR(publicKeys.getP());
        this.publicKeys = publicKeys;
        System.out.println("r: " + r);
    }

    public Cryptor(PublicKeys publicKeys, PrivateKey privateKey) {
        r = getRandomR(publicKeys.getP());
        this.privateKey = privateKey;
        this.publicKeys = publicKeys;
        //System.out.println("r: " + r);
    }
    
    private BigInteger getRandomR(BigInteger p) {
        BigInteger result;
        do {
            result = new BigInteger(bits, new Random());
        } while (result.compareTo(p) > 0 || result.compareTo(BigInteger.ONE) < 0);
        return result;
    }
    
    public String encyptedC1() {
        BigInteger e1 = publicKeys.getE1();
        BigInteger p = publicKeys.getP();
        //BigInteger c1 = 
        return e1.modPow(r, p).toString();
    }
    
    public String encryptedC2(String plain) {
        BigInteger P = new BigInteger(plain).remainder(publicKeys.getP());
        BigInteger e2r = publicKeys.getE2().modPow(r, publicKeys.getP());
        BigInteger c2 = P.multiply(e2r);
        c2 = c2.remainder(publicKeys.getP());
        return c2.toString();
    }
    
    BigInteger findInverse(BigInteger c, BigInteger z) {
        BigInteger r, r1, r2, t, t1, t2, q;
        r1 = z;
        r2 = c;
        t1 = BigInteger.ZERO;
        t2 = BigInteger.ONE;
        
        while (r2.compareTo(BigInteger.ZERO) > 0) {
            q = r1.divide(r2);
            r = r1.subtract(q.multiply(r2));
            r1 = r2;
            r2 = r;
            
            t = t1.subtract(q.multiply(t2));
            t1 = t2;
            t2 = t;
        }
        
        if (r1.equals(BigInteger.ONE)) {
            return t1.modPow(BigInteger.ONE, publicKeys.getP());
        }
        return null;
    }
    
    public String decrypt(String cipher1, String cipher2) {
        BigInteger c1d = new BigInteger(cipher1).modPow(privateKey.getPrivateKey(), publicKeys.getP());
        c1d = findInverse(c1d, publicKeys.getP());
        c1dInverse = findInverse(c1d, publicKeys.getP());
        BigInteger plain = new BigInteger(cipher2).multiply(c1d).modPow(BigInteger.ONE, publicKeys.getP());
        return plain.toString();
    }
    
    public static void main(String args[]) {
        String plainText = "Hacker!";
        ElGamalKeys elGamalKeys = new ElGamalKeys();
        Cryptor cryptor = new Cryptor(elGamalKeys.getPublicKeys(), elGamalKeys.getPrivateKey());
        String c1 = cryptor.encyptedC1();
        System.out.println("C1: " + c1);
        
        
        String c2 = "";
        String cipher = "";
        String plain = "";
        
        for (int i = 0; i < plainText.length(); i++) {
            c2 = cryptor.encryptedC2((int) plainText.charAt(i) + "");
            plain += "" + (char)Integer.parseInt(cryptor.decrypt(c1, c2));
            cipher += c2 + " ";
        }
        
        System.out.println("C1 Inverse: " + Cryptor.c1dInverse);
        System.out.println("Cypher(C2): " + cipher);
        System.out.println("Plain: " + plain);
        
    }
    
}
