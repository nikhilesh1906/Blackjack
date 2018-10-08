/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackadmin;
import java.util.Vector;

//A dealer is a gamer
public class Dealer extends Gamer{
    
    
    
    void initialize_dealer()
    {
        hand.initialize_hand();
        hand.show_initial_hand_dealer();
        hand.set_values();
    }
    void calculate_dealer_sum()
    {
        hand.calculate_sum();
    }
    void show_dealer_sum()
    {
        System.out.println("Dealer sum is : "+hand.sum);
    }
    void show_dealer()
    {
        System.out.print("Dealer's cards are ");
        hand.show_all();
    }
    void hit()
    {
        hand.add_one();
        System.out.print("Dealer's cards are ");
        hand.show_all();
        hand.sum+=(int)hand.cards.lastElement();
    }
    void new_dealer_initialize(){
        clear_gamer_hand();
        hand.initialize_hand();
        hand.show_initial_hand_dealer();
        hand.set_values();
    }
    
    void clear()
    {
        clear_gamer_hand();
    }
}

