/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onitama;

/**
 *
 * @author Zack
 */
public class Board {
    
    private final Piece[][] board = new Piece[5][5];
    
    public Board() {
        for(int i = 0; i < 5; i++) {
            if(i != 2) {
                board[0][i] = new Piece(0,i,'c', Integer.toString(i).charAt(0));
                board[4][i] = new Piece(4,i,'h', Integer.toString(i).charAt(0));
            }
            else {
                board[0][i] = new Piece(0,i,'c', 'K');
                board[4][i] = new Piece(4,i,'h', 'K');
            }
        }
        for(int i = 1; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                board[i][j] = new Piece(i,j,' ', ' ');
            }
        } 
        //board[1][2] = new Piece(1,2, 'h', 'K');
        //board[4][2] = new Piece(4,0,' ', ' ');
    }
    
    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            System.out.print("| ");
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j].getPiece());
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
    
    public String boardString() {
        String all = "";
        for (int i = 0; i < 5; i++) {
            all += "| ";
            for (int j = 0; j < 5; j++) {
                all += board[i][j].getPiece();
                all += " | ";
            }
            all += "\n";
        }
        return all;        
    }
    
    public Piece[][] getBoard() {
        return board;
    }
    
    public void copyBoard(Board a) {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                board[i][j] = new Piece (a.getPiece(i,j));
            }
        }
    }
    
    boolean noSelfDestruct(Piece piece, Card attempt, char choice, char team) {
        if(team == 'h')
            return board[attempt.findMove(choice)[0]+piece.getCoords(0)][attempt.findMove(choice)[1]+piece.getCoords(1)].getOwner()=='h';
        else 
            return board[piece.getCoords(0)-attempt.findMove(choice)[0]][piece.getCoords(1)-attempt.findMove(choice)[1]].getOwner() == 'c';
    }
    
    boolean inBounds(Piece piece, Card attempt, char choice) {
        if(piece.getOwner() == 'h')
            return 0 <= attempt.findMove(choice)[0] + piece.getCoords(0) && attempt.findMove(choice)[0] + piece.getCoords(0) < 5 
                    && 0 <= attempt.findMove(choice)[1] + piece.getCoords(1) && attempt.findMove(choice)[1] + piece.getCoords(1) < 5;
        else
            return 0 <= piece.getCoords(0) - attempt.findMove(choice)[0] && piece.getCoords(0) - attempt.findMove(choice)[0] < 5 && 
                    0 <= piece.getCoords(1) - attempt.findMove(choice)[1] && piece.getCoords(1) - attempt.findMove(choice)[1] < 5;
                    
    }
    
    public boolean legalMove(Piece piece, Card attempt, char moveChoice) {
        if(piece.getType() != ' ' && (moveChoice == 'W' || moveChoice == 'X' || moveChoice == 'Y' || moveChoice == 'Z')) {
            if(piece.getOwner() == 'h') {
                //if((0 <= attempt.findMove(choice)[0]+piece.getCoords(0) == true && attempt.findMove(choice)[0]+piece.getCoords(0) < 5 == true && 0 <= attempt.findMove(choice)[1]+piece.getCoords(1) && attempt.findMove(choice)[1]+piece.getCoords(1) < 5)  == true && board[attempt.findMove(choice)[0]+piece.getCoords(0)][attempt.findMove(choice)[1]+piece.getCoords(1)].getOwner()=='h') {
                if(inBounds(piece, attempt, moveChoice) && noSelfDestruct(piece,attempt, moveChoice, piece.getOwner()))
                    return false; //System.out.println("Human player cannot perform move.");
                else {
                    //if((0 <= attempt.findMove(spot)[0]+piece.getCoords(0) && attempt.findMove(spot)[0]+piece.getCoords(0) < 5 && 0 <= attempt.findMove(spot)[1]+piece.getCoords(1) && attempt.findMove(spot)[1]+piece.getCoords(1) < 5)  == false)
                    //    System.out.println("Human player cannot perform move.");
                    return inBounds(piece, attempt, moveChoice);
                }
            }
            else {
                if(inBounds(piece, attempt, moveChoice) && noSelfDestruct(piece,attempt, moveChoice, piece.getOwner()))
                    return false; //System.out.println("Human player cannot perform move.");
                else {
                    //if((0 <= attempt.findMove(spot)[0]+piece.getCoords(0) && attempt.findMove(spot)[0]+piece.getCoords(0) < 5 && 0 <= attempt.findMove(spot)[1]+piece.getCoords(1) && attempt.findMove(spot)[1]+piece.getCoords(1) < 5)  == false)
                    //    System.out.println("Human player cannot perform move.");
                    return inBounds(piece, attempt, moveChoice);
                }
                //if((0 <= piece.getCoords(0)-attempt.findMove(choice)[0] && piece.getCoords(0)-attempt.findMove(choice)[0] < 5 && 0 <= piece.getCoords(1)-attempt.findMove(choice)[1] && piece.getCoords(1)-attempt.findMove(choice)[1] < 5) == true && board[piece.getCoords(0)-attempt.findMove(choice)[0]][piece.getCoords(1)-attempt.findMove(choice)[1]].getOwner() == 'c') {
                    //System.out.println("Computer player cannot perform move.");
               //     return false;
                //}
                //else {
                    //if((0 <= piece.getCoords(0)-attempt.findMove(spot)[0] && piece.getCoords(0)-attempt.findMove(spot)[0] < 5 && 0 <= piece.getCoords(1)-attempt.findMove(spot)[1] && piece.getCoords(1)-attempt.findMove(spot)[1] < 5) == false)
                     //      System.out.println("Computer player cannot perform move.");
                //    return (0 <= piece.getCoords(0)-attempt.findMove(choice)[0] && piece.getCoords(0)-attempt.findMove(choice)[0] < 5 && 0 <= piece.getCoords(1)-attempt.findMove(choice)[1] && piece.getCoords(1)-attempt.findMove(choice)[1] < 5);
            
                //}
            }
        }
        else {
            //System.out.println("That is not a legal piece to be moved.");
            return false;
        }
    }
    
    public Piece getPiece(int i, int j) {
        return board[i][j];
    }
    
    public boolean isPawn(int i, int j) {
        return Character.isLowerCase(board[i][j].getType()) == true;
    }
    
    public boolean isKing(int i, int j) {
        return board[i][j].getInd() == 'K';
    }
    
    public boolean isSpace(int i, int j) {
        return board[i][j].getType() == ' ';       
    }
    
    public boolean isPiece(int i, int j) {
        return board[i][j].getType() == 'c' || board[i][j].getType() == 'C' || board[i][j].getType() == 'h' || board[i][j].getType() == 'H';
    }
    
    public int[] findPiece(String input) {
        int[] loc = new int[2];
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++)
                if(board[i][j].getPiece().equals(input)) {
                    loc[0] = i;
                    loc[1] = j;
                }
        }
        return loc;
    }
    
    public void useCard(Piece piece, Card input, char spot) {
        
        if(legalMove(piece,input,spot) == true) {
            if(piece.getOwner() == 'h') {
                int newX = piece.getCoords(1)+input.findMove(spot)[1];
                int newY = piece.getCoords(0)+input.findMove(spot)[0];
                board[newY][newX].become(piece.getPiece());
                board[piece.getCoords(0)][piece.getCoords(1)].become("  ");
            }
            else {
                int newX = piece.getCoords(1)-input.findMove(spot)[1];
                int newY = piece.getCoords(0)-input.findMove(spot)[0];
                board[newY][newX].become(piece.getPiece());
                board[piece.getCoords(0)][piece.getCoords(1)].become("  ");               
            }
        }
    }
    
    public double numPieces(char team) {
        double total = 0;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(board[i][j].getOwner() == team && isKing(i, j) == false)
                    total++;
            }
        }
        return total;
    }
}
