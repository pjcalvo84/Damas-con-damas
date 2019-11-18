package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Coordinate{

    private int row;
    private int column;
    private static final int LOWER_LIMIT = 0;
    private static final int UPPER_LIMIT = 7;

    public Coordinate(final int row, final int column){
        this.row = row;
        this.column = column;
    }

    public static Coordinate getInstance(final String format){
        assert format != null;
        try{
            final int value = Integer.parseInt(format);
            final int row = value / 10 - 1;
            final int column = value % 10 - 1;
            if(row < Coordinate.LOWER_LIMIT || Coordinate.UPPER_LIMIT < row || column < Coordinate.LOWER_LIMIT
                    || Coordinate.UPPER_LIMIT < column){
                return null;
            }
            return new Coordinate(row, column);

        }catch(final Exception ex){
            return null;
        }
    }

    public boolean isValid(){
        return Coordinate.LOWER_LIMIT <= row && row <= Coordinate.UPPER_LIMIT && Coordinate.LOWER_LIMIT <= column
                && column <= Coordinate.UPPER_LIMIT;
    }

    boolean isDiagonal(final Coordinate coordinate){
        assert coordinate != null;
        return this.row + this.column == coordinate.row + coordinate.column
                || this.row - this.column == coordinate.row - coordinate.column;
    }

    int diagonalDistance(final Coordinate coordinate){
        assert coordinate != null;
        assert this.isDiagonal(coordinate);
        return Math.abs(this.row - coordinate.row);
    }

    Coordinate betweenDiagonal(final Coordinate coordinate){
        assert coordinate != null;
        assert this.diagonalDistance(coordinate) == 2;
        int rowShift = 1;
        if(coordinate.row - this.row < 0){
            rowShift = -1;
        }
        int columnShift = 1;
        if(coordinate.column - this.column < 0){
            columnShift = -1;
        }
        return new Coordinate(this.row + rowShift, this.column + columnShift);
    }

    boolean isBlack(){
        return (this.row + this.column) % 2 != 0;
    }

    int getRow(){
        return this.row;
    }

    int getColumn(){
        return this.column;
    }

    @Override
    public String toString(){
        return "(" + row + ", " + column + ")";
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(final Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if(column != other.column){
            return false;
        }
        if(row != other.row){
            return false;
        }
        return true;
    }

}