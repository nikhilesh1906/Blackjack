package blackjackadmin;
import java.util.Scanner;
import java.util.Vector;

public class BlackJackGame {
    //A blackjack game Has-A Player and Dealer
    Player p = new Player();
    Dealer d = new Dealer();
    Scanner in = new Scanner(System.in);
    //Local variables needed for the game
    int ch,which,round;
    char ch1;
    
    void start()
    {
        round=1;
        System.out.println("Enter total amount: ");
        p.initial=in.nextInt();
       
    }
    //Deal cards to player and dealer
    void initialize_game()
    {
        System.out.println("Enter bet amount: ");
        p.bet = in.nextInt();
        if(p.bet>p.initial){
            System.out.println("Enter valid bet amount < "+p.initial+" : ");
            p.bet = in.nextInt();
        }
        p.money=0;
        d.initialize_dealer();
        p.initialize_player();
        p.calculate_player_sum();
    }
    
    //Allow the player to play the game with the dealer
    void play_game(){
    do 
    {
	System.out.println("ROUND "+round);
        p.clear();
        d.clear();
        initialize_game();
        do{
            if(p.flag==0)
            {
		System.out.println("Enter your choice: \n1.HIT\n2.STAND\n3.DOUBLE DOWN\n4.SPLIT\n5.CHECK BALANCE\n");
		ch=in.nextInt();
		switch(ch) 
		{
                    case 1: 
                        p.hit(0);
                        if(p.dealer_new==1){
                            d.new_dealer_initialize();
                            p.dealer_new = 0;
                        }
                        break;
                    
                    case 2: 
                        p.show_player();
                        d.show_dealer();
                        d.calculate_dealer_sum();
                        stand(0);
			break;
			
                    case 3: 
                        p.bet*=2;
			break;
			
                    case 4: 
                        p.split_player();
                        break;
			
                    case 5: 
                        if(p.initial+p.money>p.initial)
                            System.out.println("Congrats! You've won $"+p.money);
                        
			else if(p.initial+p.money<p.initial)
                            System.out.println("Sorry! You've lost $"+Math.abs(p.money));
                        else
                            System.out.println("No profit, No loss");
                        break;
		}
            }
			
            else
            {
                System.out.println("Enter the hand you want to play");
                which = in.nextInt();
                System.out.println("Enter your choice: \n1.HIT\n2.STAND\n3.DOUBLE DOWN\n4.CHECK BALANCE\n");
		ch=in.nextInt();
		switch(ch) 
		{
                    case 1:
                        p.hit(which);
                        if(p.dealer_new==1){
                            d.new_dealer_initialize();
                            p.dealer_new = 0;
                        }
                        break;
			
                    case 2: 
                        p.show(which);
                        d.show_dealer();
                        d.calculate_dealer_sum();
                        stand(which);
			break;
			
                    case 3: 
                        p.bet*=2;
			break;
			
                    case 4: 
                        if(p.initial+p.money>p.initial)
                            System.out.println("Congrats! You've won $"+p.money);
                        
			else if(p.initial+p.money<p.initial)
                            System.out.println("Sorry! You've lost $"+Math.abs(p.money));
                        else
                            System.out.println("No profit, No loss");
                        break;
                }
            }
        }
        while(ch!=5);
        //p.show_player_sum();
        round++;
        p.initial+=p.money;
	System.out.println("Balance: $"+p.initial);
	if(p.initial<=0)
	{
            ch1='n';
            System.out.println("Sorry! You've run out of money");
	}
        else
        {
            System.out.println("Do you want to play another round? y/n");
            ch1=in.next().charAt(0);
        }
	}while(ch1=='y');
    }
    
    
    void stand(int which)
    {
        d.show_dealer_sum();
        if(d.hand.sum<17)
        {
            d.hit();
            stand(which);
        }
        else
            check_winner(which);
    }
    
    
    void check_winner(int which)
    {
        if(which==0)
        {
            if(p.hand.sum==21 && d.hand.sum!=21)
            {
            		p.money=p.bet*2;
            		System.out.println("You've won!! Your current winnings is "+p.money);
            }
            else if(p.hand.sum>21)
            {
		p.money-=p.bet;
		System.out.println("You've gone bust!! Your current winnings is "+p.money);
            }
            else
            {
		if(d.hand.sum<=21)
		{
                    if(p.hand.sum>d.hand.sum)
                    {
			p.money=p.bet*2;
                        System.out.println("You've won the round!! Your current winnings is "+p.money);
                    }
                    else if(p.hand.sum<d.hand.sum)
                    {
                        p.money-=p.bet;
			System.out.println("You've lost the round!! Your current winnings is "+p.money);
                    }
                    else
			System.out.println("Round drawn!!");
		}
		else 
		{
                    p.money=p.bet*2;
                    System.out.println("You've won the round!! Your current winnings is "+p.money);
		}
            }
        }
        
        
        if(which==1)
        {
            if(p.left.sum==21 && d.hand.sum!=21)
            {
            	p.money=p.bet1*2;
            	System.out.println("You've won!! Your current winnings is "+p.money);
            }
            else if(p.left.sum>21)
            {
            	p.money-=p.bet1;
            	System.out.println("You've gone bust!! Your current winnings is "+p.money);
            }
            else
            {
		if(d.hand.sum<21)
		{
                    if(p.left.sum>d.hand.sum)
                    {
			p.money=p.bet1*2;
                        System.out.println("You've won the round!! Your current winnings is "+p.money);
                    }
                    else if(p.left.sum<d.hand.sum)
                    {
                        p.money-=p.bet1;
			System.out.println("You've lost the round!! Your current winnings is "+p.money);
                    }
                    else
			System.out.println("Round drawn!!");
		}
		else 
		{
                    p.money=p.bet1*2;
                    System.out.println("You've won the round!! Your current winnings is "+p.money);
		}
            }
            p.left.clear_hand();
            p.hand.cards = (Vector)p.right.cards.clone();  
            p.hand.sum = p.right.sum;  
            p.right.clear_hand();
            System.out.println("Hand 1 completed.\nContinuing with other hand..");
            System.out.print("Player's cards are ");
            p.hand.show_all();
            System.out.println("Player 's sum is : "+p.hand.sum);
            p.flag=0;
            d.new_dealer_initialize();
            p.dealer_new = 0;
            
        }
        
        
        if(which==2)
        {
            if(p.right.sum==21 && d.hand.sum!=21)
            {
            	p.money=p.bet2*2;
        		System.out.println("You've won!! Your current winnings is "+p.money);
            }
            else if(p.right.sum>21)
            {
		p.money-=p.bet2;
		System.out.println("You've gone bust!! Your current winnings is "+p.money);
            }
            else
            {
		if(d.hand.sum<21)
		{
                    if(p.right.sum>d.hand.sum)
                    {
			p.money=p.bet2*2;
                        System.out.println("You've won the round!! Your current winnings is "+p.money);
                    }
                    else if(p.right.sum<d.hand.sum)
                    {
                        p.money-=p.bet2;
			System.out.println("You've lost the round!! Your current winnings is "+p.money);
                    }
                    else
			System.out.println("Round drawn!!");
		}
		else 
		{
                    p.money=p.bet2*2;
                    System.out.println("You've won the round!! Your current winnings is "+p.money);
		}
            }
            p.right.clear_hand();
            p.hand.cards = (Vector)p.left.cards.clone();
            p.hand.sum = p.left.sum;
            p.left.clear_hand();
            System.out.println("Hand 2 completed.\nContinuing with other hand..");
            System.out.print("Player's cards are ");
            p.hand.show_all();
            System.out.println("Player 's sum is : "+p.hand.sum);
            p.flag=0;
            d.new_dealer_initialize();
            p.dealer_new = 0;
        }
        
    }
    
    void end_game(){
        System.out.println("Thank you for playing with me today!");
    }

    
    
}
