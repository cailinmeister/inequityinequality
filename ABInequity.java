//agent based model of actors playing conditional produce and partition game a la 'Inequity and Inequality in the Emergence of Norms' paper
//string memory with best response to the string, a la axtell et al emergence of classes model
//This version starts with no memories
//actors play with out group only
//actors have a two stage interaction where they produce a good by dividing labor.  If it is produced, in the second stage they bargain over the good.

//import java.io.*;
//import java.util.Arrays;

public class ABInequity
{
    public static void main(String[] args)
    {//intialize variables
        
      /*  try{
                FileWriter outfile = new FileWriter(args[0]);
                PrintWriter out = new PrintWriter(outfile);   */ 
        
//agent num is total number of agents, agentnumA is number from A group which must = 1/2 agentnum, memory length is the length of each string of memories, astrat and bstrat are placehold variables
        int agentnum = 50;
         int agentnumA= 25;
        int memorylength = 15;
        int astrat = 0;
        int bstrat = 0;
        int astrat2 = 0;
        int bstrat2 = 0;
        
        //xx is number of trials and xxx is length of trial  
        int xx = 10000;
        int xxx = 1200;
        
        //number of production strategies
        int stratnum = 3;
        
        //production game
        double[][] payoff={{4,4,4},{5,5,0},{6,0,0}};
       // double[][] payoff2={{3,3,3},{5,5,0},{7,0,0}};
        
        //number of partition strategies
        int stratnum2 = 3;
        
        //partition game
        double[][] payoff2={{4,4,4},{5,5,0},{6,0,0}};
        //double[][] payoff2={{3,3,3},{5,5,0},{7,0,0}};
        
        //matrix to hold outcomes
        int[] outcomes;
        outcomes = new int[5];
        //CAILIN THS^^NUMBER WILL NEED TO CHANGE ONCE WE KNOW STABLE STATES OF THE MODEL
        
        
    //begin number of simulations xx is the number of simulations
        for (int ii = 0; ii < xx; ii++)
        {
        
         //intialize agents, entries track memories for each agent [for each agent][for each memory][0 is produce game, 1 is partition with a high producer, 2 is partition with a med producer, 3 is partition with a low producer] 
        int [][][] agents;
        agents = new int[agentnum][memorylength][4];
        
       //enter 20 (arbitrarily chosen) for each memory as a placeholder before they get them
        for (int i = 0; i < agentnum; i++)
        {for (int j = 0; j < memorylength; j++)
            {for (int k = 0; k<4; k++)
            {agents[i][j][k] = 20;}}}
        
        //begin runs, xxx is number of steps
        for (int iii = 0; iii < xxx; iii++)
        {      
        
  //randomly pair two agents
        //generate first random agent (a) and different second random agent (b) from groups A and B
            int a = (int)(Math.random()*(agentnumA));
            int b = (int)(Math.random()*(agentnumA));
            b = b + agentnumA;
            
                 
 //Determine actors' strategies
           //calculate expected payoff for each strategy for first actor a
            double BR [];
           BR = new double[stratnum];
           
           for (int j = 0; j < memorylength; j++)
           {if(agents[a][j][0]==20)
               {}
           else
           {for(int k = 0; k < stratnum; k++)
               {BR[k] = BR[k] + payoff[k][agents[a][j][0]];}}}
            
           
         //choose best strategy
           //find highest payoffs
            double aa = 0;
           double bb = 0;
           for(int k = 0; k < stratnum; k++)
           {aa = BR[k];
           if (aa > bb)
           {bb = aa;}
           else
           {}}

                      
        //now count how many strategies are optimal and record them
           int cc = 0;
           int HB [];
           HB = new int[stratnum];
           for (int k = 0; k < stratnum; k++)
           {if(BR[k]==bb)
               {HB[cc] = k;
               cc=cc+1;}}
           
           //randomly pick one of these
           int dd = (int)(Math.random()*(cc));
           astrat = HB[dd];

           
        //player B 
           //calculate expected payoffs for each strategy to memory
           for(int k = 0; k < stratnum; k++)
               {BR[k] = 0;}
           
           for (int j = 0; j < memorylength; j++)
           {if(agents[b][j][0]==20)
               {}
           else
           {for(int k = 0; k < stratnum; k++)
               {BR[k] = BR[k] + payoff[k][agents[b][j][0]];}}}
            
          //find highest payoffs
          aa = 0;
           bb = 0;
           for(int k = 0; k < stratnum; k++)
           {aa = BR[k];
           if (aa > bb)
           {bb = aa;}
           else
           {}}
           
           //now count how many strategies are optimal and record them
           for (int k = 0; k < stratnum; k++)
           {HB[k] = 0;}
           cc = 0;
           
           for (int k = 0; k < stratnum; k++)
           {if(BR[k]==bb)
               {HB[cc] = k;
               cc=cc+1;}}
           
           //randomly pick one of these
          dd = (int)(Math.random()*(cc));
           bstrat = HB[dd];

           
            //update memories of both actors
               //player A
               for (int j = 0; j < memorylength-1; j++)
               {agents[a][j][0] = agents[a][j+1][0];}
               agents[a][memorylength-1][0] = bstrat;
               
               //player B
               for (int j = 0; j < memorylength-1; j++)
               {agents[b][j][0] = agents[b][j+1][0];}
               agents[b][memorylength-1][0] = astrat;
               
               
     //Now agents figure out demands for partitioning conditional on their opponent's demand strategy if they 'make a pie'
               if(astrat+bstrat <= 2)
               {
                //calculate expected payoff for each strategy for first actor a, given that they are now partitioning and partner's play
           for(int k = 0; k < stratnum2; k++)
               {BR[k] = 0;}
           
           for (int j = 0; j < memorylength; j++)
           {if(agents[a][j][bstrat+1]==20)
               {}
           else
           {for(int k = 0; k < stratnum2; k++)
               {BR[k] = BR[k] + payoff2[k][agents[a][j][bstrat+1]];}}}
            
           
         //choose best strategy
           //find highest payoffs
            aa = 0;
           bb = 0;
           for(int k = 0; k < stratnum2; k++)
           {aa = BR[k];
           if (aa > bb)
           {bb = aa;}
           else
           {}}

                      
        //now count how many strategies are optimal and record them
            cc = 0;
           for (int k = 0; k < stratnum2; k++)
           {HB[k] = 0;}
           HB = new int[stratnum2];
           for (int k = 0; k < stratnum2; k++)
           {if(BR[k]==bb)
               {HB[cc] = k;
               cc=cc+1;}}
           
           //randomly pick one of these
           dd = (int)(Math.random()*(cc));
           astrat2 = HB[dd];

           
        //player B 
           //calculate expected payoffs for each strategy to memory
           for(int k = 0; k < stratnum2; k++)
               {BR[k] = 0;}
           
           for (int j = 0; j < memorylength; j++)
           {if(agents[b][j][astrat+1]==20)
               {}
           else
           {for(int k = 0; k < stratnum2; k++)
               {BR[k] = BR[k] + payoff2[k][agents[b][j][astrat+1]];}}}
            
          //find highest payoffs
          aa = 0;
           bb = 0;
           for(int k = 0; k < stratnum2; k++)
           {aa = BR[k];
           if (aa > bb)
           {bb = aa;}
           else
           {}}
           
           //now count how many strategies are optimal and record them
           for (int k = 0; k < stratnum2; k++)
           {HB[k] = 0;}
           cc = 0;
           
           for (int k = 0; k < stratnum2; k++)
           {if(BR[k]==bb)
               {HB[cc] = k;
               cc=cc+1;}}
           
           //randomly pick one of these
          dd = (int)(Math.random()*(cc));
           bstrat2 = HB[dd];

           
            //update memories of both actors
               //player A
               for (int j = 0; j < memorylength-1; j++)
               {agents[a][j][bstrat+1] = agents[a][j+1][bstrat+1];}
               agents[a][memorylength-1][bstrat+1] = bstrat2;
               
               //player B
               for (int j = 0; j < memorylength-1; j++)
               {agents[b][j][astrat+1] = agents[b][j+1][astrat+1];}
               agents[b][memorylength-1][astrat+1] = astrat2;
               }
               
               else
               {}
                 
            //close steps 
        }
        
         //record simulation outcomes in matrix, outcomes 0 is fair-fair, 1 is fair-unfair, 2 is unfair-fair, 3 is infair-unfair equity, 4 is unfair-unfair strong inequity
              
               //fair production
               if(agents[0][0][0]==1)
               {if(agents[0][0][2]==1)
                   {outcomes[0] = outcomes[0]+1;}
               else 
               {outcomes[1] = outcomes[1]+1;}}

               //a agents demanding high for production
               else if(agents[0][0][0]==0)
               {if(agents[0][0][1]==1)
                   {outcomes[2] = outcomes[2]+1;}
               else if(agents[0][0][1]==0)
                   {outcomes[4] = outcomes[4]+1;}
               else
               {outcomes[3] = outcomes[3]+1;}}
               
               //b agents demanding high for production
                else if(agents[0][0][0]==2)
                {if(agents[0][0][3]==1)
                   {outcomes[2] = outcomes[2]+1;}
                else if(agents[0][0][3]==0)
                   {outcomes[3] = outcomes[3]+1;}
               else
               {outcomes[4] = outcomes[4]+1;}}
        
    /*   System.out.println("production norm");
        for(int k = 0; k < agentnum; k++)
        {for (int j = 0; j < memorylength; j++)
               {System.out.print(agents[k][j][0]);}
        System.out.println();}
                  System.out.println();
                  System.out.println();
        
           System.out.println("division norm1");
        for(int k = 0; k < agentnum; k++)
        {for (int j = 0; j < memorylength; j++)
               {System.out.print(agents[k][j][1]);}
        System.out.println();}
                  System.out.println();
                  System.out.println();
                  
                    System.out.println("division norm2");
        for(int k = 0; k < agentnum; k++)
        {for (int j = 0; j < memorylength; j++)
               {System.out.print(agents[k][j][2]);}
        System.out.println();}
                  System.out.println();
                  System.out.println();
        
                  System.out.println("division norm3");
        for(int k = 0; k < agentnum; k++)
        {for (int j = 0; j < memorylength; j++)
               {System.out.print(agents[k][j][3]);}
        System.out.println();}
                  System.out.println();
                  System.out.println();*/
                
      
        
         //close simulations   
        }
          System.out.println();
            
         for(int k = 0; k < 5; k++)
         {System.out.println(outcomes[k]);}
        
       /*     out.close();
        }
    
            catch (IOException ee)
            {ee.printStackTrace();}*/  
        
        //out brackets
    }
}

        
        