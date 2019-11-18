package es.urjccode.mastercloudapps.adcs.draughts.models;

public abstract class Piece{

    private Color color;

    Piece(final Color color){
        assert color != null;
        this.color = color;
    }

    public abstract Error checkIsAdvanced(Coordinate origin, Coordinate target);

    public abstract Error checkBadDistance(Coordinate origin, Coordinate target);

    public abstract Error checkEating(Coordinate origin, Coordinate target, PieceProvider pieceProvider);

    public abstract boolean createADraught();

    public abstract int getMaxDistance();

    public abstract boolean canMove(Coordinate origin, PieceProvider pieceProvider);

    Error isCorrect(final Coordinate origin, final Coordinate target, final PieceProvider pieceProvider){
        if(checkValidCoordinate(origin, target)){
            return Error.OUT_COORDINATE;
        }
        if(!origin.isDiagonal(target)){
            return Error.NOT_DIAGONAL;
        }
        if(!pieceProvider.isEmpty(target)){
            return Error.NOT_EMPTY_TARGET;
        }
        if(checkIsAdvanced(origin, target) != null){
            return checkIsAdvanced(origin, target);
        }
        if(checkBadDistance(origin, target) != null){
            return checkBadDistance(origin, target);
        }
        if(checkEating(origin, target, pieceProvider) != null){
            return checkEating(origin, target, pieceProvider);
        }
        return null;
    }

    private boolean checkValidCoordinate(final Coordinate origin, final Coordinate target){
        return !origin.isValid() || !target.isValid();
    }

    boolean isLimit(final Coordinate coordinate){
        return coordinate.getRow() == 0 && this.getColor() == Color.WHITE
                || coordinate.getRow() == 7 && this.getColor() == Color.BLACK;
    }

    boolean isAdvanced(final Coordinate origin, final Coordinate target){
        assert origin != null;
        assert target != null;
        final int difference = origin.getRow() - target.getRow();
        if(color == Color.WHITE){
            return difference > 0;
        }
        return difference < 0;
    }

    Color getColor(){
        return this.color;
    }

}