/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onitama;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Zack
 */
public class Card {

    /**
     * @param args ethe command line arguments
     */
    private char[][] card = new char[5][5];
    private int numMoves;
    private final String name;

    public Card(Card a) {
        card = Arrays.copyOf(a.getArray(), a.getArray().length);
        numMoves = a.getNumMoves();
        name = a.getName();
    }

    public Card() {
        for (int i = 0; i < 5; i++) {
            Arrays.fill(card[i], ' ');
        }
        card[2][2] = '@';
        numMoves = 0;
        name = "";
    }
    
    public Card(String inputName) { 
        for (int i = 0; i < 5; i++) {
            Arrays.fill(card[i], ' ');
        }
        //card[2][2] = '@';
        card[2][2] = '#';
        numMoves = 0;
        name = inputName;
    }

    public char[][] getArray() {
        return card;
    }

    public void printComputerCard(char move) {
        for (int i = 4; i >= 0; i--) {
            if(card[i][4] == move)
                System.out.print("|[");
            else
                System.out.print("|  ");
            for (int j = 4; j >= 0; j--) {
                if(card[i][j] == move)
                    System.out.print("*");
                else
                    System.out.print(card[i][j]);
                if(j != 0 && card[i][j-1] == move)
                    System.out.print(" |[");
                else if(card[i][j] == move)
                    System.out.print("] |  ");
                else
                    System.out.print(" |  ");
            }
            System.out.println();
        }
        String all = "";
        for(int i = 0; i < 13-name.length()/2; i++) {
            all += " ";
        }
        all += name;
        System.out.println(all);
    }
    
    public void printHumanCard() {
        for (int i = 0; i < 5; i++) {
            System.out.print("| ");
            for (int j = 0; j < 5; j++) {
                System.out.print(card[i][j]);
                System.out.print(" | ");
            }
            System.out.println();
        }
        String all = "";
        for(int i = 0; i < 13-name.length()/2; i++) {
            all += " ";
        }
        all += name;
        System.out.println(all);
    }
    
    public String cardString(boolean invert) {
        String all = "";
        if(invert == false) {
            for (int i = 0; i < 5; i++) {
                all += "|  ";
                for (int j = 0; j < 5; j++) {
                    all += card[i][j];
                    all += " |  ";
                }
                //all += "  |";
                all += "\n";
            }
        }
        else {
            for (int i = 4; i >= 0; i--) {
                all += "|  ";
                for (int j = 4; j >= 0; j--) {
                    all += card[i][j];
                    all += " |  ";
                }
                //all += "  |";
                all += "\n";
            }            
        }
        for(int i = 0; i < 13-name.length()/2; i++) {
            all += " ";
        }
        all += name;
        for(int i = 0; i < 13-name.length()/2; i++) {
            all += " ";
        }
        return all;
    }

    public boolean hasMove(char target) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (card[i][j] == target) {
                    count += 1;
                }
            }
        }
        return count == 1;
    }

    public boolean isMove(int i, int j) {
        return (card[i][j] == 'W' || card[i][j] == 'X' || card[i][j] == 'Y' || card[i][j] == 'Z');
    }

    public int[] findMove(char choice) {
        int[] loc = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (card[i][j] == choice) {
                    loc[0] = i - 2;
                    loc[1] = j - 2;
                    break;
                }
            }
        }
        return loc;
    }

    public void addMove(int j, int i, char choice) {
        card[2 - i][2 + j] = choice;
        numMoves += 1;
    }

    public int getNumMoves() {
        return numMoves;
    }

    public String getName() {
        return name;
    }
    
    public void printName() {
        System.out.println(name);
    }
    
    public static void turnIO(Table table) {
        boolean legal = false;
        boolean done;
        String piece = "";
        System.out.println();
        System.out.println();
        int cardChoice = 0;
        char cardMove = 'A';
        int[] pieceLoc = new int[2];
        pieceLoc[0] = 0;
        pieceLoc[1] = 0;
        while(legal == false) {
            done = false;
            while (done == false) {
                System.out.println("Which PIECE you would like to move?  Type in 0, 1, k, 3, or 4 and then hit enter.");
                Scanner S1 = new Scanner(System.in);
                String in = S1.next();
                if(in.equals("k"))
                    piece = "h"+in.toUpperCase();
                else
                    piece = "h"+in;
                //System.out.println(piece+"-yaeh");
                //System.out.println();
                if (piece.length() == 2 && (piece.charAt(1) == '0' || piece.charAt(1) == '1' || piece.charAt(1) == '3' || piece.charAt(1) == '4' || piece.charAt(1) == 'K')) {
                    done = true;
                } else {
                    System.out.println();
                    System.out.println("You seem to have put in something incorrect, please try again!");
                    System.out.println();
                }
            }
            System.out.println();
            pieceLoc = table.getBoard().findPiece(piece);
            done = false;
            //int cardChoice = 0;
            String cardInput;
            String[] names = new String[2];
            names[0] = table.getPlayerHand('h', 0).getName().toUpperCase();
            names[1] = table.getPlayerHand('h', 1).getName().toUpperCase();
            while (done == false) {
                System.out.println("Which CARD would you like to use?  Type in its name and then hit enter.");
                Scanner S2 = new Scanner(System.in);
                cardInput = S2.next();
                cardInput = cardInput.toUpperCase();
                if(cardInput.equals(names[0])) {
                    cardChoice = 0;
                    done = true;
                }
                else if(cardInput.equals(names[1])) {
                    cardChoice = 1;
                    done = true;
                }
                else {
                    System.out.println();
                    System.out.println("You seem to have put in something incorrect, please try again!");
                    System.out.println();                    
                }
                /*switch (cardInput) {
                    case "LEFT":
                        cardChoice = 0;
                        done = true;
                        break;
                    case "RIGHT":
                        cardChoice = 1;
                        done = true;
                        break;
                    default:
                        System.out.println();
                        System.out.println("You seem to have put in something incorrect, please try again!");
                        System.out.println();
                        break;
                }*/
            }
            //System.out.println(cardInput);
            System.out.println();
            done = false;
            while(done == false) {
                System.out.println("On this card, which MOVE would you like to perform?  Type in W, X, Y, or Z and then hit enter.");
                Scanner S3 = new Scanner(System.in);
                cardMove = S3.next().charAt(0);
                cardMove = Character.toUpperCase(cardMove);
                if(cardMove == 'W' || cardMove == 'X' || cardMove == 'Y' || cardMove == 'Z')
                    done = true;
                else {
                    System.out.println();
                    System.out.println("You seem to have put in something incorrect, please try again!");
                    System.out.println();
                }
            }
            if(table.goodTurn('h', cardChoice, cardMove, pieceLoc[0], pieceLoc[1]) == true) {
                legal = true;
            }
            else {
                System.out.println();
                System.out.println("You seem to have put in an illegal move, please try again!");
                System.out.println("\n");
            }   
        }
        //System.out.println(cardMove);
        table.doTurn('h', cardChoice, cardMove, pieceLoc[0], pieceLoc[1], true);
        //table.doTurn('h', 0, 'W', 4, 2, true);
        //table.printTable();

    }

    private static char decideFirst() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
        //System.out.println("RANDOM NUMBER: "+randomNum);
        if (randomNum % 2 == 0) {
            System.out.println("Human player goes first.");
            System.out.println();
            System.out.println();
            return 'h';
        } else {
            System.out.println("Computer player goes first.");
            System.out.println();
            System.out.println();
            return 'c';
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        FileOutputStream file = new FileOutputStream("results/"+timeStamp+".txt");
        DualStream tee = new DualStream(file, System.out);
        System.setOut(tee);
        //System.out.println("This goes to out.txt");
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        char start = decideFirst();
        
        Table testTable = new Table(true);
        
        int turn = 0;
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        if (start == 'h') {
            testTable.setPreviousTurn('c');
            turnIO(testTable);
            turn += 1;
            System.out.println("------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------");
        }
        testTable.setPreviousTurn('h');
        System.out.println();
        BoardTree test = new BoardTree(testTable);
        
        testTable.doTurn('c', test.getCardChoice(), test.getMoveChoice(), test.getPieceLoc()[0], test.getPieceLoc()[1], false);
        turn += 1;
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------");
        //System.out.println();
        //System.out.println();
        boolean gameOn = true;
        while (gameOn) {
            testTable.setPreviousTurn('c');
            //System.out.println("Updated table:");
            //testTable.printTable();h
            turnIO(testTable);
            turn += 1;
            System.out.println("------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------");
            System.out.println();
            System.out.println();
            //testTable.printTable();
            if (testTable.gameDone() == true) {
                gameOn = false;
                testTable.getLoser();
                System.out.println();
                System.out.println();
                System.out.println("Number of turns total: "+turn);
                System.out.println("Number of COMPUTER pieces remaining: "+(int)testTable.getBoard().numPieces('c'));
                System.out.println("Number of HUMAN pieces remaining: "+(int)testTable.getBoard().numPieces('h'));
            }
            else {
                testTable.setPreviousTurn('h');
                BoardTree next = new BoardTree(testTable);
                testTable.doTurn('c', next.getCardChoice(), next.getMoveChoice(), next.getPieceLoc()[0], next.getPieceLoc()[1], false);
                turn += 1;
                if (testTable.gameDone() == true) {
                    gameOn = false;
                    testTable.getLoser();
                    System.out.println();
                    System.out.println();
                    System.out.println("Number of turns total: "+turn);
                    System.out.println("Number of COMPUTER pawns remaining: "+(int)testTable.getBoard().numPieces('c'));
                    System.out.println("Number of HUMAN pawns remaining: "+(int)testTable.getBoard().numPieces('h'));
                }            
            }
            System.out.println("------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------");
            //System.out.println();
            //System.out.println();
            //testTable.printTable();
        }
    }
    
}
