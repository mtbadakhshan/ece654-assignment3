//import myqual.verified.PossiblyUnverified;

import myqual.verified.PossiblyUnverified;
import myqual.verified.Verified;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class FullNode {

    public static void main(String[] args) throws Exception {
        @Verified Transaction verified_tx = validate(new Transaction());
        Transaction unverified_tx = new Transaction();
        forward(verified_tx);
        forward(unverified_tx);
    }

    public static @Verified Transaction validate(@PossiblyUnverified Transaction tx) throws Exception {
        Signature ecdsaVerify = Signature.getInstance(tx.ALGO);
        KeyFactory kf = KeyFactory.getInstance("EC");

        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(tx.pubkey));

        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(tx.plaintext.getBytes("UTF-8"));
        boolean result = ecdsaVerify.verify(Base64.getDecoder().decode(tx.sig));

        if(result){
            Transaction new_tx = new @Verified Transaction(tx.pubkey, tx.plaintext, tx.sig);
            return  new_tx;
        } else {
            throw new Exception("Invalid Transaction");
        }

    }

    public static void forward(@Verified Transaction tx){

    }
}
