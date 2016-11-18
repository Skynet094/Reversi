
public class MinimaxReversiAgent extends Agent {

	

	public static int dx[]={0,0,-1,1,-1,1,-1,1};
	public static int dy[]={-1,1,0,0,1,-1,-1,1};

	public MinimaxReversiAgent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeMove(Game GAME) {
	
		int best_utility=-1000;
		int best_X, best_Y;
		
		best_X=-1;
		best_Y=-1;
		
		int row,col;
		Reversi game = (Reversi) GAME;
		
    	int opponent_id;
		int neighbour_x[]=new int[64];
        int neighbour_y[]=new int[64];
       
        
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++){

            if(game.board[i][j]=='S' || game.board[i][j]=='T')
                game.board[i][j]=-1;

        }
        
        for(int i=0; i<64; i++)
        	{
        		neighbour_x[i]=neighbour_x[i];
        	}
        if(role==1)
    		opponent_id=0;
    	else
    		opponent_id=1;
    	

    int iter=0;

    for(int i=0; i<8; i++)
        for(int j=0; j<8; j++)
            if(game.board[i][j]==role){
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
            
           // System.out.println(next_x+ " ");
            //cout<<next_x<<" "<<next_y<<endl;
            //cout<<board[next_x][next_y]<<endl;
            //break;
            int debug=0;

           // if(current_x==2 && current_y==3)
             //   debug=1;

            
            
            if(game.isValidCell(next_x, next_y) && game.board[next_x][next_y]== opponent_id)
            {

               // cout<<"HELLO."<<endl;

                while(true){
                	

            
                     next_x = next_x + dx[k];
                     next_y= next_y + dy[k];
                     
                     
                    // if(name== "Robo")
               //     	 System.out.println("WHAT IS HAPPENING??");
                    // if(debug)
                      //  cout<<"Next_x, next_y: "<<next_x<<" "<<next_y<<" Content: "<<board[next_x][next_y]<<endl;

                     if(!game.isValidCell(next_x, next_y))
                        break;  // has hit the border, hence , can't place anything here.



                     else if(game.board[next_x][next_y]== opponent_id) // keep moving, if opponent has this cell
                        {
             //               cout<<player_id<<" "<<next_x<<" "<<next_y<<endl;
                            continue;
                        }
                     else if(game.board[next_x][next_y]==-1) // stop if blank is found
                    {

                    	// 	System.out.println("WHY I AM NOT EVEN HERE????");
                            //cout<<"WHAT THE ACTUAL HECK: "<<"Current x,y: "<<current_x<<" "<<current_y<<endl;
                          //  cout<<"Current direction and, nextX, nextY: "<<k<<" "<<next_x<<" "<<next_y<<endl;

	                        	//	Reversi.board[next_x][next_y]='S';
	                        		int temp_board[][]=new int[8][8];
	                        		
	                        		for(int i1=0; i1< 8; i1++)
	                        			for(int j=0; j<8; j++)
	                        				temp_board[i1][j]=game.board[i1][j]; // unchanged game state
	                        		
	                        		update_board(role, next_x, next_y, game);
	                        		
	                        		int utility = utility_value(role, game.board);
	                        		
	                        		if(utility > best_utility){
	                        			
	                        			best_X=next_x; best_Y= next_y;
	                        			best_utility=utility;
	                        		}
	                        		
	                        		for(int i1=0; i1< 8; i1++)
	                        			for(int j=0; j<8; j++)
	                        				game.board[i1][j]=temp_board[i1][j]; // get original game state
	                                               
	                        		break;

                    }

                    else
                        break;

                }


            }


        }
    }
    

	//System.out.println("OH NOES!!!");

    if(best_X!=-1 && best_Y!=-1){
    
    	//System.out.println("OH YES!!!");
    	System.out.println(name + " choose this position: "+ best_X + ", "+best_Y);
    	update_board(role, best_X, best_Y, game);
    	
    	
    	
    }
	
	    if(role==0)
			show_suggestion(1, game);
	else
		show_suggestion(0, game);
		
		// TODO Auto-generated method stub
	    return;
	
	}
	
	
	
	int utility_value(int role, int[][] board){
		
		int value=0;
		for(int i=0; i<8; i++){
			
			for(int j=0; j<8; j++){
				
				if(board[i][j]==role)
					value++;
			}
			
			
		}
		
		return value;
		
		
	}
	
	
	
	

    public static void show_suggestion(int player_id, Reversi game){
		
    	
    //	System.out.println("HELLO WORLD :( " + player_id );
    	
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
            if(game.board[i][j]==player_id){
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
            
           // System.out.println(next_x+ " ");
            //cout<<next_x<<" "<<next_y<<endl;
            //cout<<board[next_x][next_y]<<endl;
            //break;
            int debug=0;

           // if(current_x==2 && current_y==3)
             //   debug=1;

            
            
            if(game.isValidCell(next_x, next_y) && game.board[next_x][next_y]== opponent_id)
            {

               // cout<<"HELLO."<<endl;

                while(true){

                     next_x = next_x + dx[k];
                     next_y= next_y + dy[k];
                     
               //      System.out.println("WHAT IS HAPPENING??");
                    // if(debug)
                      //  cout<<"Next_x, next_y: "<<next_x<<" "<<next_y<<" Content: "<<board[next_x][next_y]<<endl;

                     if(!game.isValidCell(next_x, next_y))
                        break;  // has hit the border, hence , can't place anything here.



                     else if(game.board[next_x][next_y]== opponent_id) // keep moving, if opponent has this cell
                        {
             //               cout<<player_id<<" "<<next_x<<" "<<next_y<<endl;
                            continue;
                        }
                     else if(game.board[next_x][next_y]==-1) // stop if blank is found
                    {


                        if(player_id==1)
                            {
                            //cout<<"WHAT THE ACTUAL HECK: "<<"Current x,y: "<<current_x<<" "<<current_y<<endl;
                          //  cout<<"Current direction and, nextX, nextY: "<<k<<" "<<next_x<<" "<<next_y<<endl;

                                Reversi.board[next_x][next_y]='S';
                            }
                        else
                            Reversi.board[next_x][next_y]= 'T';
                        break;

                    }

                    else
                        break;

                }


            }


        }
    }




    }




	
	

    public static void update_board(int player_id, int x, int y, Reversi game){

    	//System.out.println("Want to place here: "+ x+ " "+y);
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
                if(game.board[i][j]==player_id){
                    neighbour_x[iter]=i;
                    neighbour_y[iter]=j;
                    iter++;
            }


        game.board[x][y]=player_id; //placing disk



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
            //double direction_helper; 
            
            int  _x=neighbour_x[i], _y= neighbour_y[i];

       //     if(player_id==0)
         //   	System.out.println("I am O");
           // else
            //	System.out.println("I am X");
            
            
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

            else{
            	
                
            	double direction_helper= (_y - y)*1.0 / (_x - x)*1.0;
            	

            //	System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: "+ direction_helper);
            	
            	if(Math.ceil(direction_helper) != Math.floor(direction_helper)) // meaning it's a fraction
            		continue;
            	
            	
	            if( ((_y- y)/(_x-x)) == -1 ) { //right diagonal, acute angle, wrt X axis
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
	
            }
      //      System.out.println("Seeking this point: "+ _x+"  "+_y+  " Content "+ game.board[_x][_y]+" direction: "+ direction);
            
            
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

        
            if(game.board[next_x][next_y]== opponent_id) // opponent disk check
            {

               // cout<<"HELLO."<<endl;

                while(true){

                     next_x = next_x + dx[direction];
                     next_y= next_y + dy[direction];


                    //if(debug)
                      //  cout<<"Next x,y: "<<next_x<<" "<<next_y<<" Content: "<<board[next_x][next_y]<<endl;
                 //    System.out.println("Next x,y: "+ next_x+" "+next_y+"  "+game.board[next_x][next_y]);
                     
                     if(next_x==_x && next_y == _y)
                        {
                      //      cout<<"HELLO?"<<endl;
                            success=1;
                            break;  // has hit the special neighbour, hence , I can actually win some disks in this direction.
                        }

                     else if(game.board[next_x][next_y]== opponent_id) // keep moving, if opponent has this cell
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

                     else if(game.board[next_x][next_y]== opponent_id) // keep moving, if opponent has this cell
                        {
                                game.board[next_x][next_y]= player_id;
                        }


                }


            }


        }


        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++){

            if(game.board[i][j]=='S' || game.board[i][j]=='T')
                game.board[i][j]=-1;

        }

    }


}
