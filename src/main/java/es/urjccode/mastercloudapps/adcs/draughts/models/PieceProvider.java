package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.List;

interface PieceProvider {

    boolean isEmpty(Coordinate coordinate);
    Piece getPiece(Coordinate coordinate);
    public List<Coordinate> findPieces(Coordinate origin, Coordinate target);
}