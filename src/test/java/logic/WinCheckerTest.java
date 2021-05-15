package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WinCheckerTest {
    private WinChecker winChecker;
    private int assemblingEssentials = 0;
    private int maxEssentials = 3;

    @BeforeEach
    public void init(){
        winChecker = new WinChecker();
    }
    @Test
    public void isAssembledTest_Success(){
        winChecker.addEssentials(3);
        assertEquals(true, winChecker.isAssembled());
    }
    @Test
    public void isAssembledTest_Fail(){
        winChecker.addEssentials(1);
        assertEquals(false, winChecker.isAssembled());
    }
    @Test
    public void isAssembledTest_AfterReset_Fail(){
        winChecker.addEssentials(3);
        winChecker.resetAssembledItems();
        assertEquals(false, winChecker.isAssembled());
    }
    @Test
    public void isAssembledTest_AfterReset_Success(){
        winChecker.addEssentials(2);
        winChecker.resetAssembledItems();
        winChecker.addEssentials(3);
        assertEquals(true, winChecker.isAssembled());
    }
}
