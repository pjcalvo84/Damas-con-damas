package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Pawn extends Piece {

    private static final int MAX_DISTANCE = 2;

    Pawn(Color color) {
        super(color);
    }

    @Override
    public Error checkIsAdvanced(Coordinate origin, Coordinate target) {
        if (!this.isAdvanced(origin, target)) {
            return Error.NOT_ADVANCED;
        }
        return null;
    }

    @Override
    public Error checkBadDistance(Coordinate origin, Coordinate target) {
        int distance = origin.diagonalDistance(target);
        if (distance > Pawn.MAX_DISTANCE) {
            return Error.BAD_DISTANCE;
        }
        return null;
    }

    @Override
    public Error checkEatingEmpty(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        int distance = origin.diagonalDistance(target);
        if (distance == Pawn.MAX_DISTANCE) {
            if (pieceProvider.getPiece(origin.betweenDiagonal(target)) == null) {
                return Error.EATING_EMPTY;
            }
        }
        return null;
    }
}
