package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Pawn extends Piece{

    private static final int MAX_DISTANCE = 2;

    Pawn(final Color color){
        super(color);
    }

    @Override
    public Error checkIsAdvanced(final Coordinate origin, final Coordinate target){
        if(!this.isAdvanced(origin, target)){
            return Error.NOT_ADVANCED;
        }
        return null;
    }

    @Override
    public Error checkBadDistance(final Coordinate origin, final Coordinate target){
        final int distance = origin.diagonalDistance(target);
        if(distance > Pawn.MAX_DISTANCE){
            return Error.BAD_DISTANCE;
        }
        return null;
    }

    @Override
    public Error checkEating(final Coordinate origin, final Coordinate target, final PieceProvider pieceProvider){
        final int distance = origin.diagonalDistance(target);
        if(distance == Pawn.MAX_DISTANCE){
            if(pieceProvider.getPiece(origin.betweenDiagonal(target)) == null){
                return Error.EATING_EMPTY;
            }
        }
        return null;
    }

    @Override
    public boolean canMove(final Coordinate origin, final PieceProvider pieceProvider){
        Error error = Error.BAD_FORMAT;
        final int row = origin.getRow();
        final int column = origin.getColumn();
        error = this.isCorrect(origin, new Coordinate(row - 1, column - 1), pieceProvider);
        if(error != null){
            error = this.isCorrect(origin, new Coordinate(row - 1, column + 1), pieceProvider);
        }
        if(error != null){
            error = this.isCorrect(origin, new Coordinate(row + 1, column - 1), pieceProvider);
        }
        if(error != null){
            error = this.isCorrect(origin, new Coordinate(row + 1, column + 1), pieceProvider);
        }
        if(error != null){
            error = this.isCorrect(origin, new Coordinate(row - 2, column - 2), pieceProvider);
        }
        if(error != null){
            error = this.isCorrect(origin, new Coordinate(row - 2, column + 2), pieceProvider);
        }
        if(error != null){
            error = this.isCorrect(origin, new Coordinate(row + 2, column - 2), pieceProvider);
        }
        if(error != null){
            error = this.isCorrect(origin, new Coordinate(row + 2, column + 2), pieceProvider);
        }

        return error == null;
    }

    @Override
    public boolean createADraught(){
        return true;
    }

    @Override
    public int getMaxDistance(){
        return MAX_DISTANCE;
    }
}
