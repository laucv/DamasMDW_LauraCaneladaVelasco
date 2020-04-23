package es.urjccode.mastercloudapps.adcs.draughts.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EatingRandomPieceAfterNotEatingPiece extends GameTest{

    private void assertMove(Coordinate... coordinates){
        assertNull(this.game.move(coordinates));
        assertEquals(this.game, this.expectedGame);
    }

    private void assertMoveRandomRemoving(int expectedWhite, int expectedBlack, Coordinate... coordinates){
        assertNull(this.game.move(coordinates));
        assertEquals(expectedBlack, this.game.getNumberOfPieces(Color.BLACK));
        assertEquals(expectedWhite, this.game.getNumberOfPieces(Color.WHITE));
    }

    @Test
    public void testRemovingPieceAfterNotEatingWithWhitePieces(){
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
    public void testRemovingPieceAfterNotEatingWithBlackPieces(){
        this.setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "    n   ",
            "   b    ",
            "        ",
            "        ",
            "        ");
        this.setExpectedGame(Color.WHITE,
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

    @Test
    public void testJumpAndNotRemovingAnyPieceWithWhitePieces(){
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "   n    ",
            "  b     ",
            "        ",
            "        ");
        this.setExpectedGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "    b   ",
            "        ",
            "        ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(5, 2),
            new Coordinate(3, 4)
        );
    }

    @Test
    public void testJumpAndNotRemovingAnyPieceWithBlackPieces(){
        this.setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "    n   ",
            "   b    ",
            "        ",
            "        ",
            "        ");
        this.setExpectedGame(Color.WHITE,
            "        ",
            "        ",
            "        ",
            "        ",
            "        ",
            "  n     ",
            "        ",
            "        ");
        this.assertMove(
            new Coordinate(3, 4),
            new Coordinate(5, 2)
        );
    }

    @Test
    public void testRemoveAnyRandomPieceAfterNotEatingWithWhitePieces(){
        this.setGame(Color.WHITE,
            "        ",
            "        ",
            " n n    ",
            "        ",
            "   n    ",
            "  b b   ",
            "     b  ",
            "        ");
        this.assertMoveRandomRemoving(2, 3,
            new Coordinate(5, 2),
            new Coordinate(4, 1)
        );
    }

    @Test
    public void testRemoveAnyRandomPieceAfterNotEatingWithBlackPieces(){
        this.setGame(Color.BLACK,
            "        ",
            "        ",
            "        ",
            "  n n   ",
            "   b    ",
            "        ",
            "        ",
            "        ");
        this.assertMoveRandomRemoving(1, 1,
            new Coordinate(3, 4),
            new Coordinate(5, 5)
        );
    }
}

