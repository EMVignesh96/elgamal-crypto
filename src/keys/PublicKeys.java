package keys;


import java.math.BigInteger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vignesh
 */
public class PublicKeys {
    private final BigInteger e1;
    private final BigInteger e2;
    private final BigInteger p;

    public PublicKeys(BigInteger e1, BigInteger e2, BigInteger p) {
        this.e1 = e1;
        this.e2 = e2;
        this.p = p;
        
        System.out.println("e1: " + e1 + " e2: " + e2 + " p: " + p);
    }
    
    public BigInteger getE1() {
        return e1;
    }
    
    public BigInteger getE2() {
        return e2;
    }
    
    public BigInteger getP() {
        return p;
    }
}
