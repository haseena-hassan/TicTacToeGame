public class Main {
    public static void main(String[] args) {

        TicTacToeGame game = new TicTacToeGame();

        String winStatus = game.startGame();
        if(winStatus.equals("tie")){
            System.out.println("The Game is Tie!");
        }
        System.out.println("The winner is " + winStatus);
    }
}