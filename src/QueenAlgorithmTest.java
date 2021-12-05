import org.junit.Test;
import static org.junit.Assert.*;

public class QueenAlgorithmTest {
    @Test
    public void test2x2() {
        ChessController cs = new ChessController();
        cs.init(2,2, false);
        boolean success = cs.start();
        assertFalse(success);
    }

    @Test
    public void test3x3() {
        ChessController cs = new ChessController();
        cs.init(3,3, false);
        boolean success = cs.start();
        assertFalse(success);
    }

    @Test
    public void test4x4() {
        ChessController cs = new ChessController();
        cs.init(4,4, false);
        boolean success = cs.start();
        assertTrue(success);
    }

    @Test
    public void test5x5() {
        ChessController cs = new ChessController();
        cs.init(5,5, false);
        boolean success = cs.start();
        assertTrue(success);
    }

    @Test
    public void test6x6() {
        ChessController cs = new ChessController();
        cs.init(6,6, false);
        boolean success = cs.start();
        assertTrue(success);
    }

    @Test
    public void test7x7() {
        ChessController cs = new ChessController();
        cs.init(7,7, false);
        boolean success = cs.start();
        assertTrue(success);
    }

    @Test
    public void test8x8() {
        ChessController cs = new ChessController();
        cs.init(8,8, false);
        boolean success = cs.start();
        assertTrue(success);
    }

    @Test
    public void test9x9() {
        ChessController cs = new ChessController();
        cs.init(9,9, false);
        boolean success = cs.start();
        assertTrue(success);
    }

    @Test
    public void test10x10() {
        ChessController cs = new ChessController();
        cs.init(10,10, false);
        boolean success = cs.start();
        assertTrue(success);
    }
}
