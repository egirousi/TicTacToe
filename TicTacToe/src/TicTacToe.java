import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe {
    private JFrame frame;
    private JButton[][] buttons;
    private char[][] board;
    private boolean playerTurn;
    private Random rand;

    public TicTacToe() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));
        
        buttons = new JButton[3][3];
        board = new char[3][3];
        playerTurn = true;
        rand = new Random();
        
        initializeBoard();
        frame.setVisible(true);
    }
    
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].setFocusPainted(false);
                final int row = i;
                final int col = j;
                
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (playerTurn && board[row][col] == '\0') {
                            buttons[row][col].setText("X");
                            board[row][col] = 'X';
                            playerTurn = false;
                            if (!checkWin('X')) {
                                computerMove();
                            }
                        }
                    }
                });
                
                frame.add(buttons[i][j]);
                board[i][j] = '\0';
            }
        }
    }
    
    private void computerMove() {
        if (isBoardFull()) {
            JOptionPane.showMessageDialog(frame, "It's a tie!");
            resetBoard();
            return;
        }
        
        int row, col;
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (board[row][col] != '\0');
        
        buttons[row][col].setText("O");
        board[row][col] = 'O';
        playerTurn = true;
        
        checkWin('O');
    }
    
    private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                announceWinner(player);
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                announceWinner(player);
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            announceWinner(player);
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            announceWinner(player);
            return true;
        }
        
        return false;
    }
    
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void announceWinner(char player) {
        JOptionPane.showMessageDialog(frame, (player == 'X' ? "You win!" : "Computer wins!"));
        resetBoard();
    }
    
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(" ");
                board[i][j] = '\0';
            }
        }
        playerTurn = true;
    }
    
    public static void main(String[] args) {
        new TicTacToe();
    }
}
