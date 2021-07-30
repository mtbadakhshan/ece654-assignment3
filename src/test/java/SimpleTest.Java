import org.junit.Test;
import static org.junit.Assert.*;

//import org.FullNode;
//import org.Transaction;
public class SimpleTest {

    @Test
    public void test1() throws Exception {
        System.out.println("Test 1 works");

        try {
            Transaction tx1 = new Transaction();
            Transaction tx2 = FullNode.validate(new Transaction());
            assertEquals(tx1.pubkey, tx2.pubkey);

        }catch (Exception e) {
            throw e;
        }


    }
}