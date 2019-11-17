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
    public Error checkIsAdvanced(Coordinate origin, Coordinate target) {
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