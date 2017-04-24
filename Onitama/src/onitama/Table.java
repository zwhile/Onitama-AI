/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onitama;
import java.util.*;

/**
 *
 * @author Zack
 */
public class Table {
    
    private Card[] humanHand = new Card[2];   
    private Card[] computerHand = new Card[2];
    private Card sideCard = new Card();
    private ArrayList<Card> allCards = new ArrayList();
    private final Board gameBoard = new Board();
    private final Card monkey = new Card("Monkey");
    private final Card frog = new Card("Frog");
    private final Card tiger = new Card("Tiger");
    private final Card cobra = new Card("Cobra");
    private final Card elephant = new Card("Elephant");
    private final Card horse  = new Card("Horse");
    private final Card crane = new Card("Crane");
    private final Card boar = new Card("Boar");
    private final Card crab = new Card("Crab");
    private final Card ox = new Card("Ox");
    private final Card eel = new Card("Eel");
    private final Card rabbit = new Card("Rabbit");
    private final Card dragon = new Card("Dragon");
    private final Card mantis = new Card("Mantis");
    private final Card goose = new Card("Goose");
    private final Card rooster = new Card("Rooster");
    private int turnNum;
    private char previousTurn;
    
    public Table(boolean print) {
        previousTurn = 'h';
        monkey.addMove(-1,1,'W');
        monkey.addMove(1,1,'X');
        monkey.addMove(-1,-1,'Y');
        monkey.addMove(1,-1,'Z');
        allCards.add(monkey);
        frog.addMove(-2,0,'X');
        frog.addMove(-1,1,'W');
        frog.addMove(1,-1,'Y');
        allCards.add(frog);
        tiger.addMove(0,2,'W');
        tiger.addMove(0,-1,'X');
        allCards.add(tiger);
        cobra.addMove(-1,0,'X');
        cobra.addMove(1,1,'W');
        cobra.addMove(1,-1,'Y');
        allCards.add(cobra);
        elephant.addMove(-1,0,'Y');
        elephant.addMove(-1,1,'W');
        elephant.addMove(1,0,'Z');
        elephant.addMove(1,1,'X');
        allCards.add(elephant);
        horse.addMove(-1, 0, 'X');
        horse.addMove(0, 1, 'W');
        horse.addMove(0, -1, 'Y');
        allCards.add(horse);
        crane.addMove(0,1,'W');
        crane.addMove(-1,-1,'X');
        crane.addMove(1,-1,'Y');
        allCards.add(crane);
        goose.addMove(-1,1,'W');
        goose.addMove(-1,0,'X');
        goose.addMove(1,0,'Y');
        goose.addMove(1,-1,'Z');
        allCards.add(goose);
        mantis.addMove(-1,1,'W');
        mantis.addMove(1,1,'X');
        mantis.addMove(0,-1,'Y');
        allCards.add(mantis);
        dragon.addMove(-2,1,'W');
        dragon.addMove(2,1,'X');
        dragon.addMove(-1,-1,'Y');
        dragon.addMove(1,-1,'Z');
        allCards.add(dragon);
        rabbit.addMove(1,1,'W');
        rabbit.addMove(2,0,'X');
        rabbit.addMove(-1,-1,'Y');
        allCards.add(rabbit);
        eel.addMove(-1,1,'W');
        eel.addMove(1,0,'X');
        eel.addMove(-1,-1,'Y');
        allCards.add(eel);
        ox.addMove(0,1,'W');
        ox.addMove(1,0,'X');
        ox.addMove(0,-1, 'Y');
        allCards.add(ox);
        crab.addMove(0,1,'W');
        crab.addMove(-2,0,'X');
        crab.addMove(2,0, 'Y');
        allCards.add(crab);
        boar.addMove(0,1,'W');
        boar.addMove(-1,0,'X');
        boar.addMove(1,0,'Y');
        allCards.add(boar);
        rooster.addMove(1,1,'W');
        rooster.addMove(-1,0,'X');
        rooster.addMove(1,0,'Y');
        rooster.addMove(-1,-1,'Z');
        allCards.add(rooster);
        /*
        up.addMove(-1, 0, 'X');
        up2.addMove(-2,0, 'X');
        down.addMove(1,0, 'X');
        left.addMove(0, -1, 'X');
        right.addMove(0,1, 'X');
        */
        ArrayList<Card> start = new ArrayList();
        String[] cards = new String[5];
        int random = (int)(Math.random() * 16);
        start.add(allCards.get(random));
        cards[0] = allCards.get(random).getName();
        allCards.remove(random);
        random = (int)(Math.random() * 15);
        start.add(allCards.get(random));
        cards[1] = allCards.get(random).getName();
        allCards.remove(random);
        random = (int)(Math.random() * 14);
        start.add(allCards.get(random));
        cards[2] = allCards.get(random).getName();
        allCards.remove(random);
        random = (int)(Math.random() * 13);
        start.add(allCards.get(random));
        cards[3] = allCards.get(random).getName();
        allCards.remove(random);
        random = (int)(Math.random() * 12);
        start.add(allCards.get(random));
        cards[4] = allCards.get(random).getName();
        allCards.remove(random);
        /*
        start.add(up);
        start.add(up2);
        start.add(down);
        start.add(left);
        start.add(right); 
        */
        int random1 = (int)(Math.random() * 5);
        sideCard = start.get(random1);
        start.remove(random1);
        random1 = (int)(Math.random() * 4);
        humanHand[0]=start.get(random1);
        start.remove(random1);
        random1 = (int)(Math.random() * 3);        
        computerHand[0]=start.get(random1);
        start.remove(random1);  
        random1 = (int)(Math.random() * 2);        
        humanHand[1]=start.get(random1);
        start.remove(random1);             
        computerHand[1]=start.get(0);
        
        if(print == true) {
            System.out.println("BEGINNING OF GAME") ;
            System.out.println();
            System.out.println("Cards in use are:");
            for(int i = 0; i < 5; i++) {
                System.out.println(cards[i]);
            }
            System.out.println();
            System.out.println();
            printTable();
            //System.out.println();
            //System.out.println();            
        }        
    }
    
    public void printAllCards() {
        for(int i = 0; i < allCards.size(); i++) {
            System.out.println(allCards.get(i).getName());
            allCards.get(i).printComputerCard('A');
            System.out.println();
        }
    }
    
    public Table(Table a, boolean print) {
        
        setSideCard(a.getSideCard());
        humanHand = Arrays.copyOf(a.getHumanHand(),2);
        computerHand = Arrays.copyOf(a.getComputerHand(), 2);
        previousTurn = a.getPreviousTurn();
        turnNum = a.getTurnNum();
        gameBoard.copyBoard(a.getBoard());
        
        if(print == true) {
            System.out.println("BEGINNING OF GAME") ;
            printTable();
            //System.out.println();
            //System.out.println();            
        }
    }
    
    public Card getSideCard() {
        return sideCard;
    }
    
    public int getTurnNum() {
        return turnNum;
    }
    
    private void setSideCard(Card newCard) {
        sideCard = new Card(newCard);
    }
    
    public Card getPlayerHand(char player, int i) {
        if (player == 'c')
            return computerHand[i];
        else
            return humanHand[i];
    }
    
    public void setPlayerHand(char player, int i, Card newCard) {
        if (player == 'c')  {
            computerHand[i] = new Card(newCard);
        }
        else
            humanHand[i] = new Card(newCard);
    }

    private void copyComputerHand(Table a) {
        setPlayerHand('c',0,a.getComputerCard(0));
        setPlayerHand('c',1,a.getComputerCard(1));
    }

    private void copyHumanHand(Table a) {
        setPlayerHand('h',0,a.getHumanCard(0));
        setPlayerHand('h',1,a.getHumanCard(1));
    }
    
    public final void printTable() {
       String s1 = getPlayerHand('c',0).cardString(true);
       String s2 = getPlayerHand('c',1).cardString(true);
       ArrayList<String> storage1 = new ArrayList();
       Scanner scanner1 = new Scanner(s1);
       while (scanner1.hasNextLine()) {
           String line = scanner1.nextLine();
           storage1.add(line);
       }
       scanner1.close();
       ArrayList<String> storage2 = new ArrayList();
       Scanner scanner2 = new Scanner(s2);
       while (scanner2.hasNextLine()) {
           String line = scanner2.nextLine();
           storage2.add(line);
       }
       scanner2.close();       
       for(int i = 0; i < storage2.size(); i++) {
           System.out.println(storage1.get(i) + "\t\t" + storage2.get(i));
       }
       System.out.println("\n------------------------------------------------------------------\n");
       String s3 = gameBoard.boardString();
       ArrayList<String> storage3 = new ArrayList();
       Scanner scanner3 = new Scanner(s3);
       while (scanner3.hasNextLine()) {
           String line = scanner3.nextLine();
           storage3.add(line);
       }
       scanner1.close();
       String s4 = getSideCard().cardString(false);
       ArrayList<String> storage4 = new ArrayList();
       Scanner scanner4 = new Scanner(s4);
       while (scanner4.hasNextLine()) {
           String line = scanner4.nextLine();
           storage4.add(line);
       }
       scanner4.close();
       for(int i = 0; i < storage4.size(); i++) {
           if(i < storage4.size()-1)
               System.out.println(storage3.get(i) + "\t\t" + storage4.get(i));
           else
               System.out.println("\t\t\t\t\t" + storage4.get(i));
       }       
       System.out.println("\n------------------------------------------------------------------\n");
       String s5 = getPlayerHand('h',0).cardString(false);
       String s6 = getPlayerHand('h',1).cardString(false);
       ArrayList<String> storage5 = new ArrayList();
       Scanner scanner5 = new Scanner(s5);
       while (scanner5.hasNextLine()) {
           String line = scanner5.nextLine();
           storage5.add(line);
       }
       scanner5.close();
       ArrayList<String> storage6 = new ArrayList();
       Scanner scanner6 = new Scanner(s6);
       while (scanner6.hasNextLine()) {
           String line = scanner6.nextLine();
           storage6.add(line);
       }
       scanner6.close();   
       for(int i = 0; i < storage6.size(); i++) {
           //if(i == storage6.size()-1)
              // System.out.println(storage5.get(i) + "\t\t\t" + storage6.get(i));
           //else
               System.out.println(storage5.get(i) + "\t\t" + storage6.get(i));
       }
       System.out.println(); 
       System.out.println();
    }
    
    public Board getBoard() {
        return gameBoard;
    }
    
    private Card[] getComputerHand() {
        return computerHand;
    }
    
    private Card getComputerCard(int i) {
        return computerHand[i];
    }
    
    private Card getHumanCard(int i) {
        return humanHand[i];
    }
    
    private Card[] getHumanHand() {
        return humanHand;
    }
    
    private char getPreviousTurn() {
        return previousTurn;
    }
    
    public void setPreviousTurn(char a) {
        previousTurn = a;
    }
    
    public void copyBoard(Table a) {
        gameBoard.copyBoard(a.getBoard());
    }
    
    public void copyTable(Table a) {
        copyBoard(a);
        copyComputerHand(a);
        copyComputerHand(a);
        copyHumanHand(a);
        copyHumanHand(a);
        setSideCard(a.getSideCard());
        previousTurn = a.getPreviousTurn();        
    }
    
    public boolean goodTurn(char owner, int pick, char move, int pieceI, int pieceJ) {
        Card turnCard = new Card(getPlayerHand(owner, pick));
        return gameBoard.getBoard()[pieceI][pieceJ].getOwner() == owner /*&& owner != previousTurn*/ && turnCard.hasMove(move) && gameBoard.legalMove(gameBoard.getBoard()[pieceI][pieceJ], turnCard,move);
    }
    
    public void doTurn(char owner, int pick, char move, int pieceI, int pieceJ, boolean print) {
        Card turnCard = new Card(getPlayerHand(owner, pick));
        //System.out.println("TURN: "+turn);
        if(gameBoard.getBoard()[pieceI][pieceJ].getOwner() == owner && owner != previousTurn && turnCard.hasMove(move)) {
            String pieceUsed = gameBoard.getBoard()[pieceI][pieceJ].getPiece();
            gameBoard.useCard(gameBoard.getBoard()[pieceI][pieceJ], turnCard,move);
            Card temp = new Card(turnCard);
            setPlayerHand(owner,pick,getSideCard());
            setSideCard(temp);
            previousTurn = owner;
            //System.out.println(previousTurn);
            turnNum++;
            String pickString;
            if(pick == 0) 
                pickString = "left";
            else
                pickString = "right";
            if(print == true) {
                System.out.println();
                System.out.println("Piece used is: "+pieceUsed+", card used is: "+temp.getName()+", move used is: "+move);
                printTable();
            }
            //return true;
        }
        else {
            if((gameBoard.getBoard()[pieceI][pieceJ].getOwner() == owner) == false)
                System.out.println(1);
            if((owner != previousTurn) == false)
                System.out.println(2);
            if((turnCard.hasMove(move)) == false)
                System.out.println(3);
            //System.out.println("That is not the turn player's piece.");
            //return false;
        }
    }
    
    private boolean kingAlive(Board board, char player) {
    int check = 0;
    for(int i = 0; i < 5; i++) {
        for(int j = 0; j < 5; j++) {
            if(board.getPiece(i,j).getInd() == 'K' && board.getPiece(i,j).getType() == player)
                check++;
        }
    }
    return check == 1;
    }
    
    private int[] findKing(Board board, char player) {
        int[] vals = new int[2];
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(board.isKing(i,j) && board.getPiece(i, j).getOwner() == player) {
                    vals[0] = i;
                    vals[1] = j;
                } 

            }
        }
        return vals;    
    }
    
    public boolean kingAtEnd(Board board, char player) {
        if(player == 'c') {
            int[] loc = new int[2];
            loc[0] = 4;
            loc[1] = 2;
            boolean sameLoc = Arrays.equals(findKing(board,player), loc);
            return sameLoc;
        }
        else {
            int[] loc = new int[2];
            loc[0] = 0;
            loc[1] = 2;
            boolean sameLoc = Arrays.equals(findKing(board,player), loc);
            return sameLoc; 
        }
    }
    
    public boolean gameDone() {
        /*if(kingAlive(gameBoard,'h') == false || kingAlive(gameBoard,'c') == false)
            System.out.println("Cause of loss: death of King.");
        if(kingAtEnd(gameBoard,'h') == true || kingAtEnd(gameBoard,'c') == true)
            System.out.println("Cause of loss: King at opponent's end.");
        */
        return (kingAlive(gameBoard,'h') == false || kingAlive(gameBoard,'c') == false || kingAtEnd(gameBoard,'h') == true || kingAtEnd(gameBoard,'c') == true);
    }
    
    public void getLoser() {
        System.out.println();
        System.out.println();
        if(kingAlive(gameBoard,'h') == false || kingAtEnd(gameBoard,'c') == true)
            System.out.println("Good game!  Better luck next time!");
        else if(kingAlive(gameBoard,'c') == false || kingAtEnd(gameBoard,'h') == true)
            System.out.println("Good game!  I'll defeat you next time!");
    }
}
