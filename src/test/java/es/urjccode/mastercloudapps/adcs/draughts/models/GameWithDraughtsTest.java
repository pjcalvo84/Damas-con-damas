package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.Before;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GameWithDraughtsTest {

    @Mock
    Turn turn;

    @Mock
    Piece piece;
    
    @Mock
    Board board;

    @InjectMocks
    Game game;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraughts(){
        initCountDraughts();
        Coordinate origin = new Coordinate(1,0);
        Coordinate target = new Coordinate(0,1);
        
        when (turn.getColor()).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(piece);
        when(piece.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(new Pawn(Color.WHITE));
        
        when(board.getPiece(target)).thenReturn(new Pawn(Color.WHITE));
        game.move(origin, target);
        verify(board).remove(target);
        verify(board).put(eq(target), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenPawn(){
        initCountDraughts();
        Coordinate origin = new Coordinate(1,0);
        Coordinate target = new Coordinate(0,1);

        when (turn.getColor()).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(piece);
        when(piece.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(new Pawn(Color.WHITE));

        when(board.getPiece(target)).thenReturn(new Pawn(Color.WHITE));

        Draught.addDraught(game.getPiece(target).getColor());
        Draught.addDraught(game.getPiece(target).getColor());
        game.move(origin, target);
        verify(board, never()).remove(target);
        verify(board, never()).put(eq(target), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenPawnAtLimitAndEatingThenNewDraugts(){
        initCountDraughts();
        Coordinate origin = new Coordinate(2,1);
        Coordinate target = new Coordinate(0,3);
        when (turn.getColor()).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(piece);
        when(piece.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(new Pawn(Color.WHITE));
        when(board.getPiece(target)).thenReturn(new Pawn(Color.WHITE));
        game.move(origin, target);
        verify(board).remove(origin.betweenDiagonal(target));
        verify(board).remove(target);
        verify(board).put(eq(target), any(Draught.class));
    }

    public void initCountDraughts(){
        Draught.lessDraught(Color.WHITE);
        Draught.lessDraught(Color.WHITE);
        Draught.lessDraught(Color.BLACK);
        Draught.lessDraught(Color.BLACK);
    }
}