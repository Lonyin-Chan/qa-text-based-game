package org.lbg.c4.board;

import org.junit.jupiter.api.Test;
import org.lbg.c4.entities.IEntity;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TileTest {

    @Test
    public void get_correct_location() {
        IEntity mockEntity = mock(IEntity.class);
        Tile cut = new Tile(new Point(4, 3), mockEntity);
        Point expectedResult = new Point(4, 3);

        Point actualResult = cut.getLocation();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void set_and_get_correct_entity() {
        IEntity expectedResult = mock(IEntity.class);
        Tile cut = new Tile(new Point(4, 3), null);

        cut.setEntity(expectedResult);
        IEntity actualResult = cut.getEntity();

        assertEquals(expectedResult, actualResult);
    }

}
