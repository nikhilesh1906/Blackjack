package blackjackadmin;

import java.util.Vector;

//A player is a gamer
public class Player extends Gamer{
    
    int flag,dealer_new;
    int bet,money,bet1,bet2,initial;
    Hand left = new Hand();
    Hand right = new Hand();
    
    void initialize_player()
    {
        flag=0;
        dealer_new=0;
        initialize_gamer();
    }
    
    void calculate_player_sum()
    {
        hand.calculate_sum();
        if(hand.sum==21)
        {
        	money=bet*2;
			System.out.println("Blackjack!! Your current winnings is "+money+"\n");
        }
    }
    
    void show_player_sum()
    {
        System.out.println("Player sum is : "+hand.sum);
    }
    void hit(int which)
    {
	if(which==0)
	{
            hand.add_one();
            System.out.print("Player's card values are ");
            hand.show_all();
            hand.sum+=(int)hand.cards.lastElement();
            if(hand.sum>21) //CHECK
            {
                money-=bet;
                System.out.println("You've gone bust!!");
            }
	}
	else if(which==1)
	{
            left.add_one();
            System.out.print("Player 1's card values are ");
            left.show_all();
            left.sum+=(int)left.cards.get(left.cards.size()-1);
            System.out.println("Player 1's sum is : "+left.sum);
            if(left.sum>21) //CHECK
            {
                money-=bet1;
                System.out.println("You've gone bust!!");
                left.clear_hand();
                hand.cards = (Vector)right.cards.clone();  
                hand.sum = right.sum;
                right.clear_hand();
                System.out.println("Hand 1 completed.\nContinuing with other hand..");
                show_player();
                System.out.println("Player 's sum is : "+hand.sum);
                flag=0;
                dealer_new=1;
            }
	}
	else if(which==2)
	{
            right.add_one();
            System.out.print("Player 2's card values are ");
            right.show_all();
            right.sum+=(int)right.cards.get(right.cards.size()-1);
            System.out.println("Player 2's sum is : "+right.sum);
            if(right.sum>21) //CHECK
            {
                money-=bet2;
                System.out.println("You've gone bust!!");
                right.clear_hand();
                hand.cards = (Vector)left.cards.clone();
                hand.sum = left.sum;
                left.clear_hand();
                System.out.println("Hand 2 completed.\nContinuing with other hand..");
                show_player();
                System.out.println("Player 's sum is : "+hand.sum);
                flag=0;
                dealer_new=1;
            }
	}
		
    }
    void show_player()
    {
        System.out.print("Player's card values are ");
        hand.show_all();
    }
    
    void split_player()
    {
        boolean allow = hand.allow_split();
        if(allow == true)
        {
            flag=1;
            bet1=bet2=bet;
            left.cards.add((int)hand.cards.get(0));
            right.cards.add((int)hand.cards.get(1));
            
            left.calculate_sum();
            left.add_one();
            System.out.print("Player 1 cards: ");
            left.show_all();
            left.sum+=(int)left.cards.lastElement();
            System.out.println("Player 1 sum: "+left.sum);
            right.calculate_sum();
            right.add_one();
            System.out.print("Player 2 cards: ");
            right.show_all();
            right.sum+=(int)right.cards.lastElement();
            System.out.println("Player 2 sum: "+right.sum);
        }
        
    }
    
    void show(int which){
        if(which==1)
        {
            System.out.print("Player card values hand 1: ");
            left.show_all();
        }
        else
        {
            System.out.print("Player card values hand 2: ");
            right.show_all();
        }
            
    }
    
    void clear()
    {
        clear_gamer_hand();
    }
    
}

