/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onitama;

//import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
//import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author Zack
 */
public class BoardTree { 
Table top = new Table(false);    
double score;
ArrayList<Table> finalBoards = new ArrayList();
char turn;
int numChildren;
int sNC;
DefaultMutableTreeNode scoreRoot;
DefaultMutableTreeNode tableRoot;
ArrayList<Character> moveLetters;
ArrayList<int[]> pieceArray;
int superIndex;
ArrayList<Integer> goodCards;
ArrayList<Integer> pathIndices;


public BoardTree(Table initTable) {
    top.copyTable(initTable);
    score = (double)0;
    scoreRoot = new DefaultMutableTreeNode(score);
    tableRoot = new DefaultMutableTreeNode(top);
    turn = 'c';
    numChildren = tableRoot.getChildCount();
    sNC = scoreRoot.getChildCount();
    moveLetters = new ArrayList();
    pieceArray = new ArrayList();
    goodCards = new ArrayList();
    pathIndices = new ArrayList();
    superIndex = 0;
    numChildren = 0;
    sNC = 0;
    generateTables((Table)tableRoot.getUserObject(),turn,tableRoot, true);
    generateScores((Table)tableRoot.getUserObject(),turn,scoreRoot, false);
    System.out.println();
    //DefaultMutableTreeNode ttemp = (DefaultMutableTreeNode)tableRoot;
    //Object[] ttempO = ttemp.getUserObjectPath();
    //paths.add(ttempO);
    
    numChildren = tableRoot.getChildCount();
    sNC = scoreRoot.getChildCount();
    /*
    System.out.println(tableRoot.getLeafCount());
    System.out.println(scoreRoot.getLeafCount());
    System.out.println(numChildren);
    System.out.println(sNC);
    System.out.println("Diff 1: "+(tableRoot.getLeafCount()-numChildren));
    System.out.println("Diff 1: "+(scoreRoot.getLeafCount()-sNC));
    System.out.println();
    */
    switchTurn();
    
    numChildren = 0;
    sNC = 0;
    for(int i = 0; i < tableRoot.getChildCount(); i++) {
        //DefaultMutableTreeNode temp = (DefaultMutableTreeNode)tableRoot.getChildAt(i);
        //Table tempTable = (Table)temp.getUserObject();
        //Object[] tempO = temp.getUserObjectPath();
        //paths.add(tempO);
        //if(gameOver(tempTable.getBoard()) == false) {
            makeBoardChildren(tableRoot,turn,i);
            makeScoreChildren((DefaultMutableTreeNode)tableRoot, (DefaultMutableTreeNode)scoreRoot, turn,i);
        //}
        //else {
            
        //}
        numChildren += tableRoot.getChildAt(i).getChildCount();
        sNC += scoreRoot.getChildAt(i).getChildCount(); 
    }
    
    /*
    System.out.println(tableRoot.getLeafCount());
    System.out.println(scoreRoot.getLeafCount());
    System.out.println(numChildren);
    System.out.println(sNC);
    System.out.println("Diff 2: "+(tableRoot.getLeafCount()-numChildren));
    System.out.println("Diff 2: "+(scoreRoot.getLeafCount()-sNC));
    System.out.println();     
    */
    switchTurn();
    
    
    numChildren = 0;
    sNC = 0;
    for(int i = 0; i < tableRoot.getChildCount(); i++) {
        for(int j = 0; j < tableRoot.getChildAt(i).getChildCount(); j++) {
            //DefaultMutableTreeNode temp = (DefaultMutableTreeNode)tableRoot.getChildAt(i).getChildAt(j);
            //Table tempTable = (Table)temp.getUserObject();
            //Object[] tempO = temp.getUserObjectPath();
            //paths.add(tempO);
            //if(gameNotOver(tempTable.getBoard())) {
                makeBoardChildren((DefaultMutableTreeNode)tableRoot.getChildAt(i),turn,j);
                makeScoreChildren((DefaultMutableTreeNode)tableRoot.getChildAt(i), (DefaultMutableTreeNode)scoreRoot.getChildAt(i), turn,j);
            //}
            numChildren += tableRoot.getChildAt(i).getChildAt(j).getChildCount();
            sNC += scoreRoot.getChildAt(i).getChildAt(j).getChildCount(); 
        }
    }
    /*
    System.out.println(tableRoot.getLeafCount());
    System.out.println(scoreRoot.getLeafCount());
    System.out.println(numChildren);
    System.out.println(sNC);
    System.out.println("Diff 3: "+(tableRoot.getLeafCount()-numChildren));
    System.out.println("Diff 3: "+(scoreRoot.getLeafCount()-sNC));
    System.out.println();   
    */
    switchTurn();
    
    

    numChildren = 0;
    sNC = 0;
    for(int i = 0; i < tableRoot.getChildCount(); i++) {
        for(int j = 0; j < tableRoot.getChildAt(i).getChildCount(); j++) {
            for(int k = 0; k < tableRoot.getChildAt(i).getChildAt(j).getChildCount(); k++) {
                //DefaultMutableTreeNode temp = (DefaultMutableTreeNode)tableRoot.getChildAt(i).getChildAt(j).getChildAt(k);
                //Table tempTable = (Table)temp.getUserObject();
                //Object[] tempO = temp.getUserObjectPath();
                //paths.add(tempO);
                //if(gameNotOver(tempTable.getBoard())) {
                    makeBoardChildren((DefaultMutableTreeNode)tableRoot.getChildAt(i).getChildAt(j),turn,k);
                    makeScoreChildren((DefaultMutableTreeNode)tableRoot.getChildAt(i).getChildAt(j), (DefaultMutableTreeNode)scoreRoot.getChildAt(i).getChildAt(j), turn,k);
                //}
                    //makeScore((double)scoreTable(tempTable),tableRoot.getChildAt(i).getChildAt(j));
                numChildren += tableRoot.getChildAt(i).getChildAt(j).getChildAt(k).getChildCount();
                sNC += scoreRoot.getChildAt(i).getChildAt(j).getChildAt(k).getChildCount(); 
                
            }
        }
    }
    switchTurn();
    
    
    updateScores();
    /*
    System.out.println(tableRoot.getLeafCount());
    System.out.println(scoreRoot.getLeafCount());
    System.out.println(numChildren);
    System.out.println(sNC);
    System.out.println("Diff 4: "+(tableRoot.getLeafCount()-numChildren));
    System.out.println("Diff 4: "+(scoreRoot.getLeafCount()-sNC));
    System.out.println();   
    System.out.println();
    */
    
    
    
    /*
    for(int m = 0; m < scoreRoot.getChildCount(); m++) {
                    DefaultMutableTreeNode temp22 = (DefaultMutableTreeNode)scoreRoot.getChildAt(m);
                    double foo = (double)temp22.getUserObject();                         
                            System.out.println(foo);                            
    }
    DefaultMutableTreeNode temp22 = (DefaultMutableTreeNode)scoreRoot;
    System.out.println("Maximum chosen: "+(double)temp22.getUserObject());
    
    System.out.println();
    System.out.println();
    
    for(int m = 0; m < scoreRoot.getChildAt(1).getChildCount(); m++) {
                    DefaultMutableTreeNode temp2 = (DefaultMutableTreeNode)scoreRoot.getChildAt(1).getChildAt(m);
                    double foo = (double)temp2.getUserObject();                         
                            System.out.println(foo);                            
    }
    DefaultMutableTreeNode temp2 = (DefaultMutableTreeNode)scoreRoot.getChildAt(1);
    System.out.println("Minimum chosen: "+(double)temp2.getUserObject());
    
    System.out.println();
    System.out.println();
    
    for(int m = 0; m < scoreRoot.getChildAt(1).getChildAt(1).getChildCount(); m++) {
                    DefaultMutableTreeNode temp = (DefaultMutableTreeNode)scoreRoot.getChildAt(1).getChildAt(1).getChildAt(m);
                    double foo = (double)temp.getUserObject();                         
                            System.out.println(foo);                            
    }
    DefaultMutableTreeNode temp = (DefaultMutableTreeNode)scoreRoot.getChildAt(1).getChildAt(1);
    System.out.println("Maximum chosen: "+(double)temp.getUserObject());
    
    System.out.println();
    System.out.println();
    
    for(int m = 0; m < scoreRoot.getChildAt(1).getChildAt(1).getChildAt(1).getChildCount(); m++) {
                    DefaultMutableTreeNode temp1 = (DefaultMutableTreeNode)scoreRoot.getChildAt(1).getChildAt(1).getChildAt(1).getChildAt(m);
                    double foo = (double)temp1.getUserObject();                         
                            System.out.println(foo);                            
    }
    DefaultMutableTreeNode temp1 = (DefaultMutableTreeNode)scoreRoot.getChildAt(1).getChildAt(1).getChildAt(1);
    System.out.println("Minimum chosen: "+(double)temp1.getUserObject());
    
    System.out.println();
    System.out.println();
    */
    
    
    
    doChoice();
}

public int getNumChildren() {
    return numChildren;
}

public int getDepth() {
    return tableRoot.getDepth();
}

private void switchTurn() {
    if(turn=='h')
        turn = 'c';
    else
        turn = 'h';
}

private void updateScores() { 
    
    for(int i = 0; i < scoreRoot.getChildCount(); i++) {
        for(int j = 0; j < scoreRoot.getChildAt(i).getChildCount(); j++) {
            for(int k = 0; k < scoreRoot.getChildAt(i).getChildAt(j).getChildCount(); k++) {
                DefaultMutableTreeNode rooty = (DefaultMutableTreeNode)scoreRoot.getChildAt(i).getChildAt(j).getChildAt(k);
                DescriptiveStatistics stats = new DescriptiveStatistics();
                for(int m = 0; m < rooty.getChildCount(); m++) {
                    DefaultMutableTreeNode tempNode = (DefaultMutableTreeNode)scoreRoot.getChildAt(i).getChildAt(j).getChildAt(k).getChildAt(m);
                    double tempScore = (double)tempNode.getUserObject();
                    stats.addValue(tempScore);
                }
                if(rooty.isLeaf() == false) {
                    rooty.setUserObject((double)stats.getMin());
                }
            }
        }
    }    
    
    for(int i = 0; i < scoreRoot.getChildCount(); i++) {
        for(int j = 0; j < scoreRoot.getChildAt(i).getChildCount(); j++) {
            DefaultMutableTreeNode rooty = (DefaultMutableTreeNode)scoreRoot.getChildAt(i).getChildAt(j);
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for(int k = 0; k < rooty.getChildCount(); k++) {
                DefaultMutableTreeNode tempNode = (DefaultMutableTreeNode)scoreRoot.getChildAt(i).getChildAt(j).getChildAt(k);
                double tempScore = (double)tempNode.getUserObject();
                stats.addValue(tempScore);
            }
            if(rooty.isLeaf() == false)
                rooty.setUserObject((double)stats.getMax());
        }
    }     

    for(int i = 0; i < scoreRoot.getChildCount(); i++) {
        DefaultMutableTreeNode rooty = (DefaultMutableTreeNode)scoreRoot.getChildAt(i);
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for(int j = 0; j < rooty.getChildCount(); j++) {
            DefaultMutableTreeNode tempNode = (DefaultMutableTreeNode)scoreRoot.getChildAt(i).getChildAt(j);
            double tempScore = (double)tempNode.getUserObject();
            stats.addValue(tempScore);
        }
        if(rooty.isLeaf() == false)    
            rooty.setUserObject((double)stats.getMin());
    }
    
    DefaultMutableTreeNode rooty = (DefaultMutableTreeNode)scoreRoot;
    DescriptiveStatistics stats = new DescriptiveStatistics();
    for(int i = 0; i < scoreRoot.getChildCount(); i++) {
        DefaultMutableTreeNode tempRooty = (DefaultMutableTreeNode)scoreRoot.getChildAt(i);
        double tempScore = (double)tempRooty.getUserObject();
        stats.addValue(tempScore);
    }
    if(rooty.isLeaf() == false)
        rooty.setUserObject((double)stats.getMax());    
}

public int getCardChoice() {
    return goodCards.get(superIndex);
}

public char getMoveChoice() {
    return moveLetters.get(superIndex);
}

public int[] getPieceLoc() {
    return pieceArray.get(superIndex);
}

private void doChoice() {
    DefaultMutableTreeNode tempScoreRoot = (DefaultMutableTreeNode)scoreRoot;
    DefaultMutableTreeNode tempTableRoot = (DefaultMutableTreeNode)tableRoot;
    Table returnTable; //= new Table((Table)tempTableRoot.getUserObject(),false);
    double bestScore = (double)tempScoreRoot.getUserObject();
    ArrayList<Integer> indices = new ArrayList();
    for(int i = 0; i < tempTableRoot.getChildCount(); i++) {
        DefaultMutableTreeNode rooty = (DefaultMutableTreeNode)scoreRoot.getChildAt(i);
        double tempScore = (double)rooty.getUserObject();
        System.out.print(tempScore+"; "+goodCards.get(i)+"; "+moveLetters.get(i)+"; "+top.getBoard().getPiece(pieceArray.get(i)[0], pieceArray.get(i)[1]).getPiece());
        if(bestScore == tempScore) {
            indices.add(i);
            System.out.println("  *("+i+")*  ");
        }
        else
            System.out.println();
        
    }
    System.out.println();
    int random = (int)(Math.random() * indices.size());
    superIndex = indices.get(random);
    System.out.println("Child count: "+tempTableRoot.getChildCount());
    System.out.println("superIndex: "+superIndex);
    System.out.println("Number of indices: "+indices.size());
    System.out.println("Board score: "+bestScore);
    System.out.println();
    DefaultMutableTreeNode tempBest = (DefaultMutableTreeNode)tableRoot.getChildAt(superIndex);
    returnTable = (Table)tempBest.getUserObject();
    // FIND SUPERINDEX
    System.out.println("The computer's move is: ");
    System.out.println();
    for(int i = 0; i < 12; i++) {
        System.out.print(" ");
    }
    System.out.println(top.getBoard().getPiece(pieceArray.get(superIndex)[0], pieceArray.get(superIndex)[1]).getPiece());
    System.out.println();
    //int printX = pieces.get(index)[1]-2;
    //int printY = pieces.get(index)[0]+2;
    //System.out.println("The piece being moved is at location: ("+printX+", "+printY+").");
    //System.out.println("This is piece: "+top.getBoard().getPiece(pieces.get(index)[0], pieces.get(index)[1]).getPiece());)
    returnTable.getSideCard().printComputerCard(moveLetters.get(superIndex));
    System.out.println();
    System.out.println();
    System.out.println("The table should now look like this: ");
    returnTable.printTable();
}

private double scoreTable(Table a) {
    Table temp = new Table(false);
    temp.copyBoard(a);
    ArrayList<int[]> humanLocs = findPieces(temp.getBoard(),'h');
    ArrayList<int[]> computerLocs = findPieces(temp.getBoard(),'c');
    int[] humanKingLoc = findKing(temp.getBoard(),'h');
    int[] computerKingLoc = findKing(temp.getBoard(),'c');
    double hKWD = findKingWinDist(humanKingLoc, 'h');
    double cKWD = findKingWinDist(computerKingLoc, 'c');
    
    if(kingAlive(temp.getBoard(),'h') == false || kingAtEnd(temp.getBoard(), 'c') == true)
        return (double)Integer.MAX_VALUE;
    else if((kingAlive(temp.getBoard(),'c') == false || kingAtEnd(temp.getBoard(), 'h') == true))
        return (double)Integer.MIN_VALUE;
    else 
        return  //(double)(0.5*(double)1/(double)cKWD)
                -(double)(0.5*(double)1/(double)hKWD)
                + (double)(0.1*Math.pow(getNumPieces(temp.getBoard(), 'c'),2)) 
                - (double)(0.1*Math.pow(getNumPieces(temp.getBoard(), 'h'),2))
                + (double)((1.546*(1/findMeanKingDist(computerLocs, humanKingLoc))))
                - (double)((1.546*(1/findMeanKingDist(humanLocs, computerKingLoc))))
                +(double)((1.546*(1/findMeanOppPieceDist(humanLocs, computerLocs)))-0.353);
                //((findMeanKingMinDist(computerLocs, humanKingLoc)*(cKWD-hKWD)) + findMeanOppPieceDist(humanLocs,computerLocs)*getPieceDiff(a.getBoard()));
}

private char otherPlayer() {
    if(turn == 'h') 
        return 'c';
    else
        return 'h';
}

private double findKingWinDist(int[] kingLoc, char team) {
    if(team == 'c') {
        //DescriptiveStatistics stats = new DescriptiveStatistics();
        //stats.addValue(Math.abs(kingLoc[0]-4));
        //stats.addValue(Math.abs(kingLoc[1]-2));
        //double distance = Math.hypot(kingLoc[0]-4, kingLoc[1]-2);
        return (double)Math.hypot(kingLoc[0]-4, kingLoc[1]-2);//.1*stats.getMin();
    }
    else {
        //DescriptiveStatistics stats = new DescriptiveStatistics();
        //stats.addValue(Math.abs(kingLoc[0]-0));
        //stats.addValue(Math.abs(kingLoc[1]-2));
        //double distance = Math.hypot(kingLoc[0]-4, kingLoc[1]-2);
        return (double)Math.hypot(kingLoc[0]-0, kingLoc[1]-2);//.1*stats.getMin();
    }
        
}

private double getPieceDiff(Board board) {
    return (double)board.numPieces('c')-(double)board.numPieces('h');
}

private double getNumPieces(Board board, char team) {
    return (double)board.numPieces(team);
}

private double getTeamPiecesLost(Board board, char team) {
    return (double)5-(double)board.numPieces(team);
}

private double findMeanOppPieceDist(ArrayList<int[]> humanLocs, ArrayList<int[]> computerLocs) {
    DescriptiveStatistics stats = new DescriptiveStatistics();
    for(int i = 0; i < humanLocs.size(); i++) {
        for(int j = 0; j < computerLocs.size(); j++) {
            //DescriptiveStatistics tempStats = new DescriptiveStatistics();
            //tempStats.addValue(Math.abs(humanLocs.get(i)[0]-computerLocs.get(j)[0]));
            //tempStats.addValue(Math.abs(humanLocs.get(i)[1]-computerLocs.get(j)[1]));
            stats.addValue(Math.hypot(humanLocs.get(i)[0]-computerLocs.get(j)[0], humanLocs.get(i)[1]-computerLocs.get(j)[1]));
        }
    }
    return (double)(10/stats.getMean());
}

private double findMeanTeamPieceDist(ArrayList<int[]> teamLocs) {
    DescriptiveStatistics stats = new DescriptiveStatistics();
    for(int i = 0; i < teamLocs.size(); i++) {
        for(int j = 0; j < teamLocs.size(); j++) {    
            if(i != j) {
                stats.addValue(Math.hypot(teamLocs.get(i)[0]-teamLocs.get(j)[0], teamLocs.get(i)[1]-teamLocs.get(j)[1]));
            }
        }
    }
    return stats.getMean();
}

private double findMeanKingDist(ArrayList<int[]> playerLocs, int[] oppKingLoc) {
    DescriptiveStatistics stats = new DescriptiveStatistics();
    for(int i = 0; i < playerLocs.size(); i++) {
            //DescriptiveStatistics tempStats = new DescriptiveStatistics();
            //tempStats.addValue(Math.abs(playerLocs.get(i)[0]-oppKingLoc[0]));
            //tempStats.addValue(Math.abs(playerLocs.get(i)[1]-oppKingLoc[1]));
            stats.addValue(Math.hypot(playerLocs.get(i)[0]-oppKingLoc[0], playerLocs.get(i)[1]-oppKingLoc[1]));
    }
    return (double)(stats.getMean());
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

private ArrayList<int[]> findPieces(Board board, char owner) {
    ArrayList<int[]> locs = new ArrayList();
    for(int i = 0; i < 5; i++) {
        for(int j = 0; j < 5; j++) {
            if(board.getPiece(i,j).getInd() != ' ') {
                if(board.getPiece(i,j).getType() == owner) {
                    int[] temp = new int[2];
                    temp[0] = i;
                    temp[1] = j;
                    locs.add(temp);
                }
            }
        }
    }
    return locs;
}

private void makeBoardChildren(DefaultMutableTreeNode boardParent, char turn, int i) {
    DefaultMutableTreeNode temp2 = (DefaultMutableTreeNode)boardParent.getChildAt(i);
    //DefaultMutableTreeNode temp3 = (DefaultMutableTreeNode)boardParent.getChildAt(i);
    generateTables((Table)temp2.getUserObject(),turn,(DefaultMutableTreeNode)boardParent.getChildAt(i), false);
    
}

private void makeScoreChildren(DefaultMutableTreeNode boardParent, DefaultMutableTreeNode scoreParent, char turn, int i) {
    //DefaultMutableTreeNode temp2 = (DefaultMutableTreeNode)boardParent.getChildAt(i);
    //DefaultMutableTreeNode temp3 = (DefaultMutableTreeNode)boardParent.getChildAt(i);
    //DefaultMutableTreeNode temp3 = (DefaultMutableTreeNode)boardParent.getChildAt(i);
    //for(int j = 0; j < boardParent.getChildAt(i).getChildCount(); j++) {
        DefaultMutableTreeNode tempParent = (DefaultMutableTreeNode)boardParent.getRoot();
        DefaultMutableTreeNode temp3 = (DefaultMutableTreeNode)boardParent.getChildAt(i);//.getChildAt(j);
        if(tempParent.getDepth() == 4)
        generateScores((Table)temp3.getUserObject(),turn,(DefaultMutableTreeNode)scoreParent.getChildAt(i), true);
        else
            generateScores((Table)temp3.getUserObject(),turn,(DefaultMutableTreeNode)scoreParent.getChildAt(i), false);        
    //}
    /*DefaultMutableTreeNode tempParent = (DefaultMutableTreeNode)scoreParent.getRoot();
    if(tempParent.getDepth() == 4)
        generateScores((Table)temp2.getUserObject(),turn,(DefaultMutableTreeNode)scoreParent.getChildAt(i), true);
    else
        generateScores((Table)temp2.getUserObject(),turn,(DefaultMutableTreeNode)scoreParent.getChildAt(i), false);*/
    
}

private void generateTables(Table curTab, char currentTurn, DefaultMutableTreeNode parent, boolean first) {
    char[] possMoves = new char[4]; possMoves[0] = 'W'; possMoves[1] = 'X'; possMoves[2] = 'Y'; possMoves[3] = 'Z';
    for(int i = 0; i < 5; i++) {
        for(int j = 0; j < 5; j++) {
            for(int k = 0; k < 4; k++) {
                for(int m = 0; m < 2; m++) {
                    if(curTab.goodTurn(currentTurn, m, possMoves[k], i, j) && curTab.gameDone() == false) {
                        Table temp = new Table(curTab, false);
                        if(first == true) {
                            moveLetters.add(possMoves[k]);
                            goodCards.add(m);
                            int[] a = new int[2];
                            a[0] = i;
                            a[1] = j;
                            pieceArray.add(a);
                        }
                        //System.out.print("worked ");
                        temp.doTurn(currentTurn, m, possMoves[k], i, j, false);
                        makeTable(temp, parent);
                        //makeScore((double)0, parent);
                    }
                    //if(curTab.goodTurn(currentTurn, m, possMoves[k], i, j) && curTab.gameDone() == true) {
                    //   curTab.printTable();
                    //}
                }
            }
        }
    }
    
    /*if(curTab.gameDone() == true) {
        Object[] path = parent.getUserObjectPath();
        for(int i = 0; i < path.length; i++) {
            System.out.println("Turn: "+(i));
            Table a = new Table((Table)path[i],false);
            a.getBoard().printBoard();
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println("---");
        System.out.println();
        /*
        DefaultMutableTreeNode b = (DefaultMutableTreeNode)parent.getParent();
        Table a = new Table((Table)b.getUserObject(), false);
        System.out.println();
        System.out.println();
        a.printTable();
        System.out.println();
        curTab.printTable();
        System.out.println("----------------------------------------------------------");
        System.out.println("----------------------------------------------------------");
        System.out.println();
        System.out.println();
        
        
    }*/
    //System.out.println();
    //System.out.println("Size of this tree: "+parent.getChildCount());
}

private void generateScores(Table curTab, char currentTurn, DefaultMutableTreeNode parent, boolean leaf) {
    char[] possMoves = new char[4]; possMoves[0] = 'W'; possMoves[1] = 'X'; possMoves[2] = 'Y'; possMoves[3] = 'Z';
    for(int i = 0; i < 5; i++) {
        for(int j = 0; j < 5; j++) {
            for(int k = 0; k < 4; k++) {
                for(int m = 0; m < 2; m++) {
                    if(curTab.goodTurn(currentTurn, m, possMoves[k], i, j) == true && curTab.gameDone() == false) {
                        Table temp = new Table(curTab, false);
                        temp.doTurn(currentTurn, m, possMoves[k], i, j, false);
                        //System.out.print("worked ");
                        //temp.doTurn(currentTurn, m, possMoves[k], i, j, false);
                        //makeTable(temp, parent);
                        //if(leaf == true || curTab.gameDone() == true)
                            makeScore(scoreTable(temp), parent);
                        //else
                          //  makeScore((double)0,parent);
                    }
                        
                }
            }
        }
    }
    //System.out.println();
    //System.out.println("Size of this tree: "+parent.getChildCount());
}

public boolean isLeaf(DefaultMutableTreeNode test) {
    return test.isLeaf();
}

private void makeTable(Table entry,DefaultMutableTreeNode parent) {
    DefaultMutableTreeNode tableNode = new DefaultMutableTreeNode((Table)entry);
    parent.add(tableNode);

}

public void makeScore(double score, DefaultMutableTreeNode parent) {
    DefaultMutableTreeNode scoreNode = new DefaultMutableTreeNode((double)score);
    parent.add(scoreNode);
}

}