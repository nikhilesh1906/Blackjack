package blackjackadmin;

public class BlackJackAdmin 
{

    public static void main(String[] args) 
    {
        //A blackjack admin Has-A BlackJack Game
        BlackJackGame game = new BlackJackGame();
        game.start();
        game.play_game();
        game.end_game();          
    }  
}
