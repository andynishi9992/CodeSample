import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import java.awt.color.*;
// @URL in tictactoeURL.txt file 
public class andrewmnA07 extends JApplet implements ActionListener  {

	/**
	 * @author Andrew Nishimura
	 * this method creates the window and runs the program
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		JFrame windows = new JFrame("tic tac toe");
		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		andrewmnA07 game = new andrewmnA07();
		windows.setContentPane(game.x);
		windows.pack();
		windows.setSize(400, 400);
		windows.setVisible(true);
	}
public void init(){
	/*JFrame windows = new JFrame("tic tac toe");
	windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	andrewmnA07 game = new andrewmnA07();
	windows.setContentPane(game.x);
	windows.pack();
	windows.setSize(400, 400);
	windows.setVisible(true);*/
	andrewmnA07 x =new andrewmnA07();
	this.setContentPane(x.x);
}
	private JButton[] squares;// the boxes hold X,O
	private JPanel x;// panel holds the game
	private JButton playagain;//buton to restart
	private JLabel notice;//label on top
	private JLabel winorlose;// label to tell if you win
	private int cpumoves;// counts computer moves 
	private boolean winner = false;//you
	private boolean winner2 = false;//computer
	private int totalmoves = 0;// counts total moves

	/*
	 * * this constructor creates the window for the tictac toe game
	 */
	public andrewmnA07() {
		this.x = new JPanel();// total panel
		x.setLayout(new BorderLayout());
		notice = new JLabel("  TIC TAC TOE you:X cpu:O");
		x.add(notice, BorderLayout.NORTH);
		// the buttons
		this.squares = new JButton[9];

		for (int y = 0; y < squares.length; y++) {
			squares[y] = new JButton("");
		}
		// sets size of buttons
		for (int b = 0; b < squares.length; b++) {
			squares[b].setSize(300, 300);
		}
		// set color of buttons
		for(int ii=0 ;ii<squares.length;ii++)
		{
			squares[ii].setBackground(Color.WHITE);
		}
		JPanel buton = new JPanel();// panel of buttons
		buton.setLayout(new GridLayout(3, 3));// sets layout of buttons

		for (int i = 0; i < squares.length; i++) {
			buton.add(squares[i]);// adds buttons to the grid
		}

		for (int ii = 0; ii < squares.length; ii++) {
			this.squares[ii].addActionListener(this);
		}
		x.add(buton);// adds the panel of buttons to overall panel
		JPanel botom = new JPanel();
		botom.setLayout(new GridLayout(1, 2));
		this.playagain = new JButton("play again");// adds a playagain button at
													// the end
		this.playagain.addActionListener(this);
		this.winorlose = new JLabel("");
		botom.add(winorlose);
		botom.add(playagain);
		x.add(botom, BorderLayout.SOUTH);

	}
/**
 * this method generates a random number to choose which button computer 
 * presses
 *
 */
	public void computermove() {
		Random cpu = new Random();

		int cpumove = cpu.nextInt(8);

		while (squares[cpumove].getText().equals("") == false) {
			cpumove = cpu.nextInt(8);

			if (this.cpumoves > 3) {
				break;
			}
		}
		if (this.cpumoves <= 4) {
			squares[cpumove].setText("O");
			squares[cpumove].setEnabled(false);
		}
		this.cpumoves++;
		this.totalmoves++;
	}
/**
 * this method tells java what to do when user presses a button 
 */
	@Override
	public void actionPerformed(ActionEvent x) {
		if (x.getSource() == this.playagain) {//resets the game
			for (int o = 0; o < squares.length; o++) {
				squares[o].setText("");
				squares[o].setEnabled(true);
				squares[o].setBackground(Color.WHITE);
			}
			this.cpumoves = 0;
			this.winner = false;
			this.winner2 = false;
			this.totalmoves = 0;
			this.winorlose.setText("");
		} else
			for (int oo = 0; oo < squares.length; oo++) {
				if (x.getSource() == squares[oo]) {
					squares[oo].setText("X");
					squares[oo].setEnabled(false);
					this.totalmoves++;
					if (winner() == false && totalmoves<9) {//make sure computer doesnt move after last square been picked
						computermove();
					}
					if (winner() == true) {//checks if you win
						this.winorlose.setText("you Win");
					} else if (computerwin() == true) {// checks if computer won
						this.winorlose.setText("YOU LOSE");
					}
					if (this.totalmoves >= 9 && winner() == false//checks to for a tie
							&& computerwin() == false) {
						for(int i3 = 0; i3<squares.length;i3++){
							squares[i3].setBackground(Color.lightGray);
						}
						this.winorlose.setText("Its a tie");
					}
				}
			}
		// TODO Auto-generated method stub

	}
/**
 * this method checks to see if user won
 * true you win
 * false lose or tie
 * @return true or false
 */
	public boolean winner() {

		if (squares[0].getText() == "X" && squares[1].getText() == "X"
				&& squares[2].getText() == "X") {
			this.winner = true;
             squares[0].setBackground(Color.GREEN);//changes to green when you win
             squares[1].setBackground(Color.GREEN);
             squares[2].setBackground(Color.GREEN);
		} else if (squares[0].getText() == "X" && squares[3].getText() == "X"
				&& squares[6].getText() == "X") {
			this.winner = true;
			squares[0].setBackground(Color.GREEN);
            squares[3].setBackground(Color.GREEN);
            squares[6].setBackground(Color.GREEN);
		} else if (squares[0].getText() == "X" && squares[4].getText() == "X"
				&& squares[8].getText() == "X") {
			this.winner = true;
			squares[0].setBackground(Color.GREEN);
            squares[4].setBackground(Color.GREEN);
            squares[8].setBackground(Color.GREEN);
		} else if (squares[1].getText() == "X" && squares[4].getText() == "X"
				&& squares[7].getText() == "X") {
			this.winner = true;
			squares[1].setBackground(Color.GREEN);
            squares[4].setBackground(Color.GREEN);
            squares[7].setBackground(Color.GREEN);
		} else if (squares[2].getText() == "X" && squares[5].getText() == "X"
				&& squares[8].getText() == "X") {
			this.winner = true;
			squares[2].setBackground(Color.GREEN);
            squares[5].setBackground(Color.GREEN);
            squares[8].setBackground(Color.GREEN);
		} else if (squares[2].getText() == "X" && squares[4].getText() == "X"
				&& squares[6].getText() == "X") {
			this.winner = true;
			squares[2].setBackground(Color.GREEN);
            squares[4].setBackground(Color.GREEN);
            squares[6].setBackground(Color.GREEN);
		} else if (squares[6].getText() == "X" && squares[7].getText() == "X"
				&& squares[8].getText() == "X") {
			this.winner = true;
			squares[6].setBackground(Color.GREEN);
            squares[7].setBackground(Color.GREEN);
            squares[8].setBackground(Color.GREEN);
		} else if (squares[3].getText() == "X" && squares[4].getText() == "X"
				&& squares[5].getText() == "X") {
			this.winner = true;
			squares[3].setBackground(Color.GREEN);
            squares[4].setBackground(Color.GREEN);
            squares[5].setBackground(Color.GREEN);
		}
		if (this.winner == true) {
			for (int x = 0; x < squares.length; x++) {
				squares[x].setEnabled(false);
			}
		}
		return this.winner;
	}
/**
 * this method checks to see if the computer won the game
 * true computer win
 * false lose or tie
 * @return true or false
 */
	public boolean computerwin() {
		if (squares[0].getText() == "O" && squares[1].getText() == "O"
				&& squares[2].getText() == "O") {
			this.winner2 = true;
             squares[0].setBackground(Color.RED);//changes the square to red when cpu wins
             squares[1].setBackground(Color.RED);
             squares[2].setBackground(Color.RED);
		} else if (squares[0].getText() == "O" && squares[3].getText() == "O"
				&& squares[6].getText() == "O") {
			this.winner2 = true;
			squares[0].setBackground(Color.RED);
            squares[3].setBackground(Color.RED);
            squares[6].setBackground(Color.RED);
		} else if (squares[0].getText() == "O" && squares[4].getText() == "O"
				&& squares[8].getText() == "O") {
			this.winner2 = true;
			squares[0].setBackground(Color.RED);
            squares[4].setBackground(Color.RED);
            squares[8].setBackground(Color.RED);
		} else if (squares[1].getText() == "O" && squares[4].getText() == "O"
				&& squares[7].getText() == "O") {
			this.winner2 = true;
			squares[1].setBackground(Color.RED);
            squares[4].setBackground(Color.RED);
            squares[7].setBackground(Color.RED);
		} else if (squares[2].getText() == "O" && squares[5].getText() == "O"
				&& squares[8].getText() == "O") {
			this.winner2 = true;
			squares[2].setBackground(Color.RED);
            squares[5].setBackground(Color.RED);
            squares[8].setBackground(Color.RED);
		} else if (squares[2].getText() == "O" && squares[4].getText() == "O"
				&& squares[6].getText() == "O") {
			this.winner2 = true;
			squares[2].setBackground(Color.RED);
            squares[4].setBackground(Color.RED);
            squares[6].setBackground(Color.RED);
		} else if (squares[6].getText() == "O" && squares[7].getText() == "O"
				&& squares[8].getText() == "O") {
			this.winner2 = true;
			squares[6].setBackground(Color.RED);
            squares[7].setBackground(Color.RED);
            squares[8].setBackground(Color.RED);
		} else if (squares[3].getText() == "O" && squares[4].getText() == "O"
				&& squares[5].getText() == "O") {
			this.winner2 = true;
			squares[3].setBackground(Color.RED);
            squares[4].setBackground(Color.RED);
            squares[5].setBackground(Color.RED);
		}
		if (this.winner2 == true) {
			for (int x = 0; x < squares.length; x++) {
				squares[x].setEnabled(false);
			}
		}
		return this.winner2;

	}

}