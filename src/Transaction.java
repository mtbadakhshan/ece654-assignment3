import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class Transaction {

    private static final String SPEC = "secp256k1";
    public static final String ALGO = "SHA256withECDSA";


    public String pubkey;
    public String plaintext;
    public String sig;

    public Transaction(String _pubkey, String _plaintext, String _sig){

        this.pubkey = _pubkey;
        this.plaintext = _plaintext;
        this.sig = _sig;

    }

    public Transaction() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException, SignatureException {

        ECGenParameterSpec ecSpec = new ECGenParameterSpec(SPEC);
        KeyPairGenerator g = KeyPairGenerator.getInstance("EC");
        g.initialize(ecSpec, new SecureRandom());
        KeyPair keypair = g.generateKeyPair();
        PublicKey publicKey = keypair.getPublic();
        PrivateKey privateKey = keypair.getPrivate();

        plaintext = "Hello";

        //...... sign
        Signature ecdsaSign = Signature.getInstance(ALGO);
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(plaintext.getBytes("UTF-8"));
        byte[] signature = ecdsaSign.sign();
        pubkey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        sig = Base64.getEncoder().encodeToString(signature);
//        System.out.println(sig);
//        System.out.println(pubkey);


    }


}
