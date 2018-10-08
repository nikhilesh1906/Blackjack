package blackjackadmin;

import java.util.Vector;
import java.util.Random;

public class Hand {
    Vector cards=new Vector();
    Random rand = new Random();
    int sum;
    
    void initialize_hand()
    {
        cards.add(rand.nextInt(13)+1);
	cards.add(rand.nextInt(13)+1);
        
        if((int)cards.get(0)==11)
            cards.set(0, "jack");
	else if((int)cards.get(0)==12)
            cards.set(0, "queen");
	else if((int)cards.get(0)==13)
            cards.set(0, "king");
	else if((int)cards.get(0)==1)
            cards.set(0, "ace");
		
	if((int)cards.get(1)==11)
            cards.set(1, "jack");
	else if((int)cards.get(1)==12)
            cards.set(1, "queen");
	else if((int)cards.get(1)==13)
            cards.set(1, "king");
	else if((int)cards.get(1)==1)
            cards.set(1, "ace");
        
        //Remove comments for testing Split
        //cards.set(1,5);
        //cards.set(0,5);
    }
    
    void show_initial_hand_dealer()
    {
        System.out.println("Dealers face up card: "+cards.get(0));
    }
    
    void show_initial_hand_cards()
    {
        System.out.println("Players cards: "+cards.get(0)+" "+" "+cards.get(1));
    }
    
    void set_values()
    {
        if(cards.get(0)=="jack" || cards.get(0)=="queen" || cards.get(0)=="king")
            cards.set(0,10);

	if(cards.get(1)=="jack" || cards.get(1)=="queen" || cards.get(1)=="king")
            cards.set(1,10);
		
        if(cards.get(0)=="ace")
            cards.set(0,11);
		
	if(cards.get(1)=="ace")
            cards.set(1,11);
        
        if((int)cards.get(0)==11 && (int)cards.get(1)==11)
            cards.set(1,1);
    }
    
    void calculate_sum()
    {
        for(int i=0;i<cards.size();i++)
        {
            sum+=(int)cards.get(i);
	}
    }
    
    void add_one()
    {
        cards.add(rand.nextInt(13)+1);
        if((int)cards.lastElement()==11 || (int)cards.lastElement()==12 || (int)cards.lastElement()==13)
            cards.set(cards.size()-1, 10);
	else if((int)cards.lastElement()==1)
	{
            if(sum+11<=21)
		cards.set(cards.size()-1, 11);
            else
		cards.set(cards.size()-1, 1);
	}
    }
    
    void show_all()
    {
        System.out.println(cards);
    }
    
    boolean allow_split()
    {
        if((int)cards.get(0)!=(int)cards.get(1))
            return false;
	else
            return true;
    }
    
    void clear_hand()
    {
        cards.clear();
        sum = 0;
    }
}

