package logic.characters;

import logic.Way;
import logic.icecells.IceCell;
import logic.icecells.StableIceCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BearTest {
    private IceCell mockOwnCell;
    private IceCell mockNeighbourCell;
    private Bear bear;

    @BeforeEach
    public void init(){
        mockOwnCell = mock(StableIceCell.class);
        mockNeighbourCell = mock(StableIceCell.class);
        bear = new Bear(mockOwnCell);
    }

    @Test
    public void Bear_Successful_Move() {
        when(mockOwnCell.getNeighbour(Way.values()[0])).thenReturn(mockNeighbourCell);
        when(mockOwnCell.getNeighbour(Way.values()[1])).thenReturn(mockNeighbourCell);
        when(mockOwnCell.getNeighbour(Way.values()[2])).thenReturn(mockNeighbourCell);
        when(mockOwnCell.getNeighbour(Way.values()[3])).thenReturn(mockNeighbourCell);
        when(mockNeighbourCell.acceptBear(bear)).thenReturn(true);

        bear.move();

        verify(mockOwnCell, times(1)).removeBear();
    }
}
