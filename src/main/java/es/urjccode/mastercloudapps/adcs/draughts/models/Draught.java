package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece {

    private static int numberPieces =0;

    Draught(Color color) {
        super(color);
    }

    public static void addDraught(){
        assert numberPieces <2;
        numberPieces++;
    }

    public static boolean canCreateNewDraught(){
        return numberPieces<2;
    }

}