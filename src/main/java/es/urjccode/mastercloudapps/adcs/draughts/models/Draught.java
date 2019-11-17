package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece {

    private static int numberWhitePieces = 0;
    private static int numberBlackPieces = 0;

    Draught(Color color) {
        super(color);
    }

    public static void addDraught(Color color){
        if(color == Color.WHITE)
            numberWhitePieces++;
        else
            numberBlackPieces++;
    }

    public static boolean canCreateNewDraught(Color color){
        if(color == Color.WHITE)
            return numberWhitePieces < 2;
        else
            return numberBlackPieces < 2;
    }

    public static void lessDraught(Color color){
        if(color == Color.WHITE && numberWhitePieces > 0)
            numberWhitePieces--;
        else if(numberBlackPieces > 0)
            numberBlackPieces--;
    }

    @Override
    Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        if (!origin.isDiagonal(target)) {
            return Error.NOT_DIAGONAL;
        }
        if (!pieceProvider.isEmpty(target)) {
            return Error.NOT_EMPTY_TARGET;
        }
        if (!this.isAdvanced(origin, target)) {
            return Error.NOT_ADVANCED;
        }
        /*if (distance == Piece.MAX_DISTANCE) {
            if (pieceProvider.getPiece(origin.betweenDiagonal(target)) == null) {
                return Error.EATING_EMPTY;
            }
        }*/
        return null;
    }

    @Override
    public Error checkBadDistance(Coordinate origin, Coordinate target) {
        return null;
    }

    @Override
    public Error checkEatingEmpty(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        return null;
    }

}