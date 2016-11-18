
public class Reversi extends Game {

	int turn;
	public static int[][] board;

	public static int dx[]={0,0,-1,1,-1,1,-1,1};
	public static int dy[]={-1,1,0,0,1,-1,-1,1};
	int scoreA, scoreB;
	
	public Reversi(Agent a, Agent b) {
		super(a, b);
		a.setRole(0);  // O <--- 0
		b.setRole(1);  // 1 <--- X
		
		scoreA=scoreB=0;
		
		name="Reversi";
		board = new int[8][8];
		
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isFinished() {
		// TODO Auto-generated method stub
	
		if(isBoardFull() || onlyOneExists())
			{
				checkForWin();
				
				System.out.println("Player: "+  this.agent[0].name+" scored: "+ scoreA);
				System.out.println("Player: "+  this.agent[1].name+" scored: "+ scoreB);
				
				return true;
			
			}
		
		 return false;
	}
	
	boolean onlyOneExists(){
		int playerA=0;
		int playerB=0;
		
		
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
			{
				if(board[i][j]==1)
					playerA++;
				else if(board[i][j]==0)
					playerB++;
			}
		
		if(playerA==0 || playerB==0)
			return true;
		else 
			return false;
	}

	@Override
	void initialize(boolean fromFile) {
		// TODO Auto-generated method stub
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = -1;
			}
		}
		
		board[3][3]=0;
		board[3][4]=1;	
		board[4][3]=1;
		board[4][4]=0;
		
	}

	@Override
	void showGameState() {
		
		

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("    0   1   2   3   4   5   6   7");
        System.out.println("  ---------------------------------");
        for (int i = 0; i < 8; i++)
        {
                System.out.print(i+" | ");
            for (int j = 0; j < 8; j++)
            {
            	if(board[i][j]==-1)
            		System.out.print(" "+ " | ");
            	else if	(board[i][j]==0)
            		System.out.print( "O | ");
            	else if(board[i][j]==1)
            		System.out.print("X | ");
                else if(board[i][j] == 'S')
                    System.out.print("S | ");
                else
                    System.out.print("T | ");

            }
            System.out.println();
            System.out.println("  ---------------------------------");
        }
        System.out.println("    0   1   2   3   4   5   6   7");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


		// TODO Auto-generated method stub
		
	}

	@Override
	void updateMessage(String msg) {
		System.out.println(msg);
		
		// TODO Auto-generated method stub
		
	}

	
	//user defined functions

	

    public boolean isBoardFull() {

		
    	int item_count=0;
    	
    	
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if(board[i][j]==1 || board[i][j]==0)
                	item_count++;
            }
        }
        
        if(item_count==64)
        	 return true;
        return false;
    }
	

    public static boolean isValidCell(int row, int col)
	{
    	if(row>=0 && row<8 && col>=0 && col<8)
	        return true;

    	if(row<0 || col<0 || row>7 || col >7)
  
    		return false;
    	
    	if(board[row][col]!=-1) return false;
    	
    	
    //	System.out.println(row+ "  "+ col);
		
		return false;
	}

    public int checkForWin() 
    
    {
    	winner = null;
    	int winRole=-1;
    	
    	int player_human = 0;
    	int player_AI = 0;
    	
    	for(int i=0; i<8; i++){
    		
    		for(int j=0; j<8; j++){
    			
    			if(board[i][j]==0)
    				player_AI++;
    			else if(board[i][j]==1)
    				player_human++;
    		}
    		
    	}
    //	System.out.println(player_AI+ " vs "+ player_human );
    	if(player_AI> player_human)
    		winRole=0;
    	else if(player_human < player_AI)
    		winRole=1;
    	
    	if(winRole!=-1)
    	{
    		scoreA=player_AI;
    		scoreB= player_human;
    		winner = agent[winRole];
    	}
		return winRole;

    	
    	
    }
}
