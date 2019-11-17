package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBuilder {
    List<String> stringList;
    List<String> characters = Arrays.asList(" ", "b", "n", "B", "N");
    Piece[] pieces = {null, new Piece(Color.WHITE), new Piece(Color.BLACK), new Draught(Color.WHITE), new Draught(Color.BLACK)};

    public GameBuilder(){
        stringList = new ArrayList<>(8);
    }

    public GameBuilder add(String string){
        stringList.add(string);
        return this;
    }

    public Game builder(){
        Board board = new Board();
        for(int x = 0; x < stringList.size(); x++) {
            for (int y = 0; y < stringList.get(x).length(); y++) {
                int position = characters.indexOf(String.valueOf(stringList.get(x).charAt(y)));
                if(position != 0) {
                    board.put(new Coordinate(x, y), pieces[position]);
                }
            }
        }
        return new Game(board);
    }

}