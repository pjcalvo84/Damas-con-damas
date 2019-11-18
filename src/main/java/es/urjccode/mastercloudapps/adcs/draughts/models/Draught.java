package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece{

    private static final int MAX_DISTANCE = 7;
    private static int numberWhitePieces = 0;
    private static int numberBlackPieces = 0;

    Draught(final Color color){
        super(color);
    }

    public static void addDraught(final Color color){
        if(color == Color.WHITE){
            numberWhitePieces++;
        }else{
            numberBlackPieces++;
        }
    }

    public static boolean canCreateNewDraught(final Color color){
        if(color == Color.WHITE){
            return numberWhitePieces < 2;
        }else{
            return numberBlackPieces < 2;
        }
    }

    public static void lessDraught(final Color color){
        if(color == Color.WHITE && numberWhitePieces > 0){
            numberWhitePieces--;
        }else if(numberBlackPieces > 0){
            numberBlackPieces--;
        }
    }

    @Override
    public Error checkIsAdvanced(final Coordinate origin, final Coordinate target){
        return null;
    }

    @Override
    public Error checkBadDistance(final Coordinate origin, final Coordinate target){
        return null;
    }

    @Override
    public Error checkEating(final Coordinate origin, final Coordinate target, final PieceProvider pieceProvider){

        if(pieceProvider.findPieces(origin, target).size() > 1){
            return Error.BAD_EATING;
        }
        return null;
    }

    @Override
    public boolean createADraught(){
        return false;
    }

    @Override
    public int getMaxDistance(){
        return MAX_DISTANCE;
    }

    @Override
    public boolean canMove(final Coordinate origin, final PieceProvider pieceProvider){
        Error error = Error.BAD_FORMAT;
        int row = origin.getRow();
        int column = origin.getColumn();
        while(row > 0 && column > 0 && error != null){
            row--;
            column--;
            error = this.isCorrect(origin, new Coordinate(row, column), pieceProvider);
        }
        row = origin.getRow();
        column = origin.getColumn();
        while(row > 0 && column < 7 && error != null){
            row--;
            column++;
            error = this.isCorrect(origin, new Coordinate(row, column), pieceProvider);
        }
        row = origin.getRow();
        column = origin.getColumn();
        while(row < 7 && column > 0 && error != null){
            row++;
            column--;
            error = this.isCorrect(origin, new Coordinate(row, column), pieceProvider);
        }
        row = origin.getRow();
        column = origin.getColumn();
        while(row < 7 && column < 7 && error != null){
            row++;
            column++;
            error = this.isCorrect(origin, new Coordinate(row, column), pieceProvider);
        }
        return error == null;
    }
}