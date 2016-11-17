import java.util.Scanner;

public class ReversiHumanVsHuman {
	static Scanner scanner= new Scanner(System.in);
	
	public static int board[][]= new int[8][8];

	public static int dx[]={0,0,-1,1,-1,1,-1,1};
	public static int dy[]={-1,1,0,0,1,-1,-1,1};

	    //horizontal left check  dx + 0 , dy - 1
	    //horizontal right check  dx + 0 , dy + 1
	    //vertical up check   dx -1 , dy + 0
	    //vertical down check dx + 1 , dy + 0
	    //right(acute angle) diagonal up check   dx - 1 , dy + 1
	    //right(acute angle) diagonal down check dx + 1 , dy   - 1
	    //left(large angle) diagonal up check dx - 1 , dy - 1
	    //left(large angle) diagonal down check dx +1 , dy + 1

	public static void main(String[] args) {
		
	//	for(int i=0; i<8; i++)
		//	System.out.println(dx[i]+ " "+dy[i]);
	    //cout<< !(0)<<endl;
	    //cout<< !(1) <<endl;


	    for (int i = 0; i < 8; i++) {
	                for (int j = 0; j < 8; j++) {
	                    board[i][j] = -1;
	                }
	            }

	    board[3][3]=0;
	    board[3][4]=1;
	    board[4][3]=1;
	    board[4][4]=0;

	    show_suggestion(1);
	    showGameState();
	    System.out.println("Reversi: Let's play!");


	    int turn=1;


	    while(true){
	            if(turn==1)
	                System.out.print("Player 1's move: x,y: ");
	            else
	                System.out.print("Player 2's move x,y: ");

	            int x,y;
	            //cin>>x>>y;
	            x= scanner.nextInt();
	            y=scanner.nextInt();
	            System.out.println(x+ " "+y);
	            update_board(turn, x, y);
	        //    showGameState();
	            turn= (1^turn);
	            show_suggestion(turn);
	            showGameState();



	    }

	  //  show_suggestion(0);
//	    showGameState();


	  //  showGameState();

	    //update_board(0, 3, 5);

	    //update_board()
	  //  showGameState();



	}

	


	public static boolean valid_move(int x, int y){
	    if(x>0 && x<8 && y>0 && y<8)
	        return true;
	    else
	        return false;
	}

		public static void showGameState() {


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


	    public static void update_board(int player_id, int x, int y){

	    	
	    	int opponent_id;
	    	
	    	if(player_id==1)
	    		opponent_id=0;
	    	else
	    		opponent_id=1;
	    	
	        int neighbour_x[]=new int[64];
	        int neighbour_y[]=new int[64];
	       
	        for(int i=0; i<64; i++)
	        	{
	        		neighbour_x[i]=neighbour_x[i];
	        	}
	        
	        int iter=0;

	        for(int i=0; i<8; i++)
	            for(int j=0; j<8; j++)
	                if(board[i][j]==player_id){
	                    neighbour_x[iter]=i;
	                    neighbour_y[iter]=j;
	                    iter++;
	            }


	        board[x][y]=player_id; //placing disk



	        for(int i=0; i<iter; i++){

	            // determine relationship between neighbour_x[i], neighbour_y[i] and (x,y)
	            int direction=-1;
	            //1 2 3 4 5 6 7 8


	    //0 horizontal left check  dx + 0 , dy - 1
	    //1 horizontal right check  dx + 0 , dy + 1
	    //2 vertical up check   dx -1 , dy + 0
	    //3 vertical down check dx + 1 , dy + 0
	    //4 right(acute angle) diagonal up check   dx - 1 , dy + 1
	    //5 right(acute angle) diagonal down check dx + 1 , dy   - 1
	    //6 left(large angle) diagonal up check dx - 1 , dy - 1
	    //7 left(large angle) diagonal down check dx +1 , dy + 1

	            int  _x=neighbour_x[i], _y= neighbour_y[i];

	            if(_x == x){  // horizontally ache
	                    // horizontally left or right?
	                if(y > _y)
	                     direction=0;
	                else
	                    direction=1;
	            }


	            else if(_y == y){  //vertically ache
	                    // vertically up or down?
	                if(x > _x) // is x down?
	                     direction=2;
	                else
	                    direction=3;
	            }


	            else if( ((_y- y)/(_x-x)) == -1 ) { //right diagonal, acute angle, wrt X axis
	                //diagonally up or down ? amar current placement er respect e neighbour node ta ki uporer dike naki nicher dike
	                 if(y < _y && x > _x)
	                    direction=4;
	                else
	                    direction= 5;
	            }

	            else if(((_y- y)/(_x-x)) == 1 ){ //left diagonal, boro angle, with respect to X axis
	                ///diagonally up or down
	                if(y > _y && x > _x) // 6 means up,
	                    direction=6;
	                else
	                    direction= 7;  // 7 means down
	            }
	            
	            else
	            	continue;

	            int debug=0;

	            //if(x==4 && y==5 && _x==2 && _y==3)
	              //  debug=1;

	        //    if(debug)
	          //      cout<<"direction: "<<direction<<" neighbour:x,y "<< _x<<" "<<_y <<endl;

	        ///now, I know the relationship between x,y and neighbour_x[i], neighbour_y[i]

	        int next_x = x +dx[direction];
	        int next_y= y +dy[direction];

	  //      if(debug)
	    //        cout<<"Next x,y: "<<next_x<<" "<<next_y<<" Content: "<<board[next_x][next_y]<<endl;
	        int success=0;

	        
	            if(board[next_x][next_y]== opponent_id) // opponent disk check
	            {

	               // cout<<"HELLO."<<endl;

	                while(true){

	                     next_x = next_x + dx[direction];
	                     next_y= next_y + dy[direction];


	                    //if(debug)
	                      //  cout<<"Next x,y: "<<next_x<<" "<<next_y<<" Content: "<<board[next_x][next_y]<<endl;

	                     if(next_x==_x && next_y == _y)
	                        {
	                      //      cout<<"HELLO?"<<endl;
	                            success=1;
	                            break;  // has hit the special neighbour, hence , I can actually win some disks in this direction.
	                        }

	                     else if(board[next_x][next_y]== opponent_id) // keep moving, if opponent has this cell
	                        continue;
	                    else{
	                        break;
	                    }

	                }
	            }

	            if(success==1){

	                     next_x = x ;  //
	                     next_y= y;


	                while(true){

	                     next_x = next_x + dx[direction];
	                     next_y= next_y + dy[direction];

	                     if(next_x==_x && next_y == _y)
	                            break;  // has hit another x, hence , can't place anything here.

	                     else if(board[next_x][next_y]== opponent_id) // keep moving, if opponent has this cell
	                        {
	                                board[next_x][next_y]= player_id;
	                        }


	                }


	            }


	        }


	        for(int i=0; i<8; i++)
	            for(int j=0; j<8; j++){

	            if(board[i][j]=='S' || board[i][j]=='T')
	                board[i][j]=-1;

	        }

	    }


	    public static void show_suggestion(int player_id){
			
	    	
	    	
	    		    	
	    	int opponent_id;
			int neighbour_x[]=new int[64];
	        int neighbour_y[]=new int[64];
	       
	        for(int i=0; i<64; i++)
	        	{
	        		neighbour_x[i]=neighbour_x[i];
	        	}
	        if(player_id==1)
	    		opponent_id=0;
	    	else
	    		opponent_id=1;
	    	

	    int iter=0;

	    for(int i=0; i<8; i++)
	        for(int j=0; j<8; j++)
	            if(board[i][j]==player_id){
	                neighbour_x[iter]=i;
	                neighbour_y[iter]=j;
	                iter++;


	        }

	    for(int k=0 ; k < 8 ; k++){
	        for(int i=0; i< iter; i++ ){

	            int current_x= neighbour_x[i];
	            int current_y= neighbour_y[i];

	            //do I want to pursue this branch?
	            int next_x = current_x +dx[k];
	            int next_y= current_y +dy[k];
	            //cout<<next_x<<" "<<next_y<<endl;
	            //cout<<board[next_x][next_y]<<endl;
	            //break;
	            int debug=0;

	           // if(current_x==2 && current_y==3)
	             //   debug=1;

	            if(valid_move(next_x, next_y) && board[next_x][next_y]== opponent_id)
	            {

	               // cout<<"HELLO."<<endl;

	                while(true){

	                     next_x = next_x + dx[k];
	                     next_y= next_y + dy[k];

	                    // if(debug)
	                      //  cout<<"Next_x, next_y: "<<next_x<<" "<<next_y<<" Content: "<<board[next_x][next_y]<<endl;

	                     if(!valid_move(next_x, next_y))
	                        break;  // has hit the border, hence , can't place anything here.



	                     else if(board[next_x][next_y]== opponent_id) // keep moving, if opponent has this cell
	                        {
	             //               cout<<player_id<<" "<<next_x<<" "<<next_y<<endl;
	                            continue;
	                        }
	                     else if(board[next_x][next_y]==-1) // stop if blank is found
	                    {


	                        if(player_id==1)
	                            {
	                            //cout<<"WHAT THE ACTUAL HECK: "<<"Current x,y: "<<current_x<<" "<<current_y<<endl;
	                          //  cout<<"Current direction and, nextX, nextY: "<<k<<" "<<next_x<<" "<<next_y<<endl;

	                                board[next_x][next_y]='S';
	                            }
	                        else
	                            board[next_x][next_y]= 'T';
	                        break;

	                    }

	                    else
	                        break;

	                }


	            }


	        }
	    }




	    }




}
