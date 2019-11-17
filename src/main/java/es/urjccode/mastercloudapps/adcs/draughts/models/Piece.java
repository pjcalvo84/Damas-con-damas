package es.urjccode.mastercloudapps.adcs.draughts.models;

public abstract class Piece {

	private Color color;


	Piece(Color color) {
		assert color != null;
		this.color = color;
	}


	public abstract Error checkIsAdvanced(Coordinate origin, Coordinate target);

	public abstract Error checkBadDistance(Coordinate origin, Coordinate target);

	public abstract Error checkEatingEmpty(Coordinate origin, Coordinate target, PieceProvider pieceProvider);

	public abstract boolean createADraught();

	public abstract int getMaxDistance();

	Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
		if (!origin.isDiagonal(target)) {
			return Error.NOT_DIAGONAL;
		}
		if (!pieceProvider.isEmpty(target)) {
			return Error.NOT_EMPTY_TARGET;
		}
		if(checkIsAdvanced(origin, target) != null) {
			return checkIsAdvanced(origin, target);
		}
		if(checkBadDistance(origin,target) != null) {
			return checkBadDistance(origin, target);
		}

		if(checkEatingEmpty(origin, target, pieceProvider) != null) {
			return checkEatingEmpty(origin, target, pieceProvider);
		}

		return null;
	}

	boolean isLimit(Coordinate coordinate){
		return coordinate.getRow()== 0 && this.getColor() == Color.WHITE ||
		coordinate.getRow()== 7 && this.getColor() == Color.BLACK;
	}

	boolean isAdvanced(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE) {
			return difference > 0;
		}
		return difference < 0;
	}

	Color getColor() {
		return this.color;
	}

}