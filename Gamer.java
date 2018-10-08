package blackjackadmin;

public class Gamer {
    //A gamer HAS-A hand
    Hand hand = new Hand();
    
    void initialize_gamer(){
        hand.initialize_hand();
        hand.show_initial_hand_cards();
        hand.set_values();
    }
    
    void clear_gamer_hand(){
        hand.cards.clear();
        hand.sum = 0;
    }
}

