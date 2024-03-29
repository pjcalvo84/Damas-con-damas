package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.List;

public class Game{

    private Board board;
    private Turn turn;

    public Game(){
        this.turn = new Turn();
        this.board = new Board();
        for(int i = 0; i < this.board.getDimension(); i++){
            for(int j = 0; j < this.board.getDimension(); j++){
                final Coordinate coordinate = new Coordinate(i, j);
                final Piece piece = this.getInitialPiece(coordinate);
                if(piece != null){
                    this.board.put(coordinate, piece);
                }
            }
        }
    }

    public Game(final Board board){
        this.board = board;
        this.turn = new Turn();
    }

    private Piece getInitialPiece(final Coordinate coordinate){
        assert coordinate != null;
        if(coordinate.isBlack()){
            final int row = coordinate.getRow();
            Color color = null;
            if(row <= 2){
                color = Color.BLACK;
            }else if(row >= 5){
                color = Color.WHITE;
            }
            if(color != null){
                return new Pawn(color);
            }
        }
        return null;
    }

    public void move(final Coordinate origin, final Coordinate target){
        assert this.isCorrect(origin, target) == null;
        if(origin.diagonalDistance(target) == this.board.getPiece(origin).getMaxDistance()){
            this.board.remove(origin.betweenDiagonal(target));
        }else{
            this.board.remove(origin, target);
        }
        this.board.move(origin, target);
        if(wouldCreateDraught(target)){
            final Color color = board.getPiece(target).getColor();
            this.board.remove(target);
            this.board.put(target, new Draught(color));
            Draught.addDraught(color);
        }
        this.turn.change();
    }

    private boolean wouldCreateDraught(final Coordinate target){
        return this.board.getPiece(target).isLimit(target)
                && Draught.canCreateNewDraught(board.getPiece(target).getColor())
                && this.board.getPiece(target).createADraught();
    }

    public Error isCorrect(final Coordinate origin, final Coordinate target){
        assert origin != null;
        assert target != null;
        if(board.isEmpty(origin)){
            return Error.EMPTY_ORIGIN;
        }
        if(this.turn.getColor() != this.board.getColor(origin)){
            return Error.OPPOSITE_PIECE;
        }
        return this.board.getPiece(origin).isCorrect(origin, target, board);
    }

    public Color getColor(final Coordinate coordinate){
        assert coordinate != null;
        return this.board.getColor(coordinate);
    }

    public Color getColor(){
        return this.turn.getColor();
    }

    public boolean isBlocked(){
        return this.board.getPieces(this.turn.getColor()).isEmpty() && !isPossibleMove();
    }

    public int getDimension(){
        return this.board.getDimension();
    }

    public Piece getPiece(final Coordinate coordinate){
        assert coordinate != null;
        return this.board.getPiece(coordinate);
    }

    @Override
    public String toString(){
        return this.board + "\n" + this.turn;
    }

    public boolean isPossibleMove(){
        final List<Coordinate> coordinateList = this.board.getCoordinatesForColor(this.turn.getColor());
        for(final Coordinate coordinate : coordinateList){
            if(board.getPiece(coordinate).canMove(coordinate, board)){
                return true;
            }
        }
        return false;
    }

}