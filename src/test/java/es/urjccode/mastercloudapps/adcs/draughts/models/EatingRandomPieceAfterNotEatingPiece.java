package es.urjccode.mastercloudapps.adcs.draughts.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EatingRandomPieceAfterNotEatingPiece extends GameTest{

    private void assertMove(Coordinate... coordinates){
        assertNull(this.game.move(coordinates));
        assertEquals(this.game, this.expectedGame);
    }

    @Test
    void testRemovingRandomPieceAfterNotEatingWithWhitePieces(){
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            " n      ",
            "  b     ",
            "        ",
            "        ");
        this.setExpectedGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "        ",
            " n      ",
            "        ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(5, 2),
            new Coordinate(4, 3)
        );
    }

    @Test
    void testRemovingRandomPieceAfterNotEatingWithBlackPieces(){
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "    n   ",
            "   b    ",
            "        ",
            "        ",
            "        ");
        this.setExpectedGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "        ",
            "   b    ",
            "        ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(3, 4),
            new Coordinate(4, 5)
        );
    }
}

