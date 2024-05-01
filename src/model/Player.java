package model;

public class Player {
    public String name;
    public PlayingPiece playingPiece;

    public Player(String playerName, PlayingPiece playerPiece) {
        name = playerName;
        playingPiece = playerPiece;
    }
}
