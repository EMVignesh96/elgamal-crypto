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
public class PrivateKey {
    private final BigInteger d;

    public PrivateKey(BigInteger d) {
        this.d = d;
        
        System.out.println("d: " + d);
    }
    
    public BigInteger getPrivateKey() {
        return d;
    }
}
