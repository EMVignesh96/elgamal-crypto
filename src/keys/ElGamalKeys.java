package keys;


import java.math.BigInteger;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vignesh
 */

public class ElGamalKeys {
    
    PrivateKey privateKey = null;
    PublicKeys publicKeys = null;
    
    
    private final BigInteger e1;
    private final BigInteger e2;
    private final BigInteger p;
    private final BigInteger d;
    public int bits = 9;

    public ElGamalKeys() {
        p = BigInteger.probablePrime(bits, new Random());
        d = getRandomD();
        e1 = getRandomE1(p);
        e2 = e1.modPow(d, p);
        privateKey = new PrivateKey(d);
        publicKeys = new PublicKeys(e1, e2, p);
    }
    
    public PrivateKey getPrivateKey() {
        return privateKey;
    }
    
    public PublicKeys getPublicKeys() {
        return publicKeys;
    }
    
    private BigInteger getRandomD() {
        BigInteger result;
        BigInteger two = BigInteger.valueOf(2);
        do {
            result = new BigInteger(bits, new Random());
        } while (result.compareTo(p.subtract(two)) > 0 || result.compareTo(BigInteger.ONE) < 0);
        return result;
    }
    
    private BigInteger getRandomE1(BigInteger p) {
        BigInteger result;
        do {
            result = new BigInteger(bits, new Random());
            if (p.gcd(result) == BigInteger.ONE) {
                break;
            }
        } while (result.compareTo(p.subtract(BigInteger.ONE)) > 0 || result.compareTo(BigInteger.ONE) < 0);
       
        return result;
    }
}
