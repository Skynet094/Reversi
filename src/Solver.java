public class Solver {
	
	/*
	 * The starting point of the game.
	 * Instantiates two agents (human/ minimax/ alpha beta pruning/ or other) and pass them to a game object.
	 * Here a TickTack Toe game is implemented as an example. You need to extend the abstract class Game to create your own game.
	 * */
	
	public static void main(String[] args) 
	{
		

	//	Agent human = new HumanTTTAgent("Neo");
		//Agent human = new MinimaxTTTAgent("007");
		//Agent machine = new MinimaxTTTAgent("Smith");
		//Agent machine2= new MinimaxTTTAgent("Rock");
		
		
		Agent ReversiHuman1= new HumanReversiAgent("Human1");
		Agent ReversiHuman2= new HumanReversiAgent("Human2");
		Agent ReversiAI= new MinimaxReversiAgent("Robo1");
		Agent ReversiAI2= new MinimaxReversiAgent("Robo2");
		
	    //System.out.println(human.name+" vs. "+machine.name);
		
		//Game game = new TickTackToe(machine,machine2);
		
		Game reversi = new Reversi(ReversiAI, ReversiHuman1);
		
		System.out.println(ReversiAI.name+ " has role= "+ReversiAI.role);
		System.out.println(ReversiHuman1.name+ " has role= "+ReversiHuman1.role);
	//	System.out.println(ReversiAI2.name+ " has role= "+ReversiAI2.role);
		
		
		reversi.initialize(false);
		reversi.showGameState();
		//game.play();
	//	reversi.showGameState();
		reversi.play();
		
	}

}
