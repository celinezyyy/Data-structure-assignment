package assignmenT_Q3;
import java.util.*;

public class Q3Arrow_Basic {

    public static void main(String[] args) {
     
        Scanner read = new Scanner(System.in);
        int front, left, right, back, numWave;  // number of straw man in each direction
        int [] arrow;    //keep the number of arrow will be shoot
 //----------------------------------------------------------------------------------------------------
        System.out.println("Enter number of straw men(for each direction): ");
        System.out.print("Front: ");
        front = read.nextInt();
        if(front>=100)
        {    
            System.out.print("Number of straw men must be less than 100!\n"
                    + "Please enter a valid number again: ");
            front = read.nextInt();
        }
        
        System.out.print("Left: ");
        left = read.nextInt();
        if(left>=100)
        {    
            System.out.print("Number of straw men must be less than 100!\n"
                    + "Please enter a valid number again: ");
            left = read.nextInt();
        }
        
        System.out.print("Right: ");
        right = read.nextInt();
        if(right>=100)
        {    
            System.out.print("Number of straw men must be less than 100!\n"
                    + "Please enter a valid number again: ");
            right = read.nextInt();
        }
        
        System.out.print("Back: ");
        back = read.nextInt();
        if(back>=100)
        {    
            System.out.print("Number of straw men must be less than 100! \n"
                    + "Please enter a valid number again: ");
            back = read.nextInt();
        }
//----------------------------------------------------------------------------------------------------
       
        System.out.print("Enter the number of waves: ");
        numWave = read.nextInt();
        arrow = new int[numWave];
        
        // key in the number of arrow in decreasing order
        System.out.println("Please key in the number of arrow for each wave "
                    + "in decreasing order");
        for(int i=0; i<numWave; i++)
            arrow[i]=read.nextInt(); 
        
        System.out.print("Arrow: [");
        for(int i=0; i<numWave; i++)
        {
            if(i==numWave-1)
                System.out.println(arrow[i] + "]");
            else
                System.out.print(arrow[i] + ", ");
        }
//----------------------------------------------------------------------------------------------------

        int [] numStrawMen = {front, left, right, back};    // keep the number of strawmen in array
        int [] count = {0,0,0,0};       // count how many time the strawmen had been used
        
        Q3MyQueue<Integer> bestArrowCaptured = new Q3MyQueue<>();     //using Queue
        Q3MyQueue<String> bestDirection = new Q3MyQueue<>();         //using Queue
        
        int[] choose = new int[4];      //to store the efficiency arrow on each direction before we choose the best direction
        
        for(int i=0; i<numWave; i++)
        { 
            choose = efficiency(numStrawMen, arrow[i], count);
            
            if(choose[0]>=choose[1] && choose[0]>=choose[2] && choose[0]>=choose[3])
            {
                bestDirection.enqueue("front");
                bestArrowCaptured.enqueue(choose[0]);
            } 

            else if(choose[1]>=choose[2] && choose[1]>=choose[3])
            {
                bestDirection.enqueue("left");
                bestArrowCaptured.enqueue(choose[1]);
            }

            else if(choose[2]>=choose[3])
            {
                bestDirection.enqueue("right");
                bestArrowCaptured.enqueue(choose[2]);
            }

            else 
            {
                bestDirection.enqueue("back");
                bestArrowCaptured.enqueue(choose[3]);
            }
            count = useStrawMenCount(bestDirection.getElement(i),count);
        }
        
        System.out.print("Boat Direction:[");
        while(!bestDirection.isEmpty())
        {
            if(bestDirection.getSize()!=1)
                System.out.print(bestDirection.dequeue() + ", ");
            else
                System.out.print(bestDirection.dequeue()+ "]");
        }
   
        int totalCaptured=0;
        for(int i=0; i<numWave; i++)
            totalCaptured+=bestArrowCaptured.getElement(i);
        
        System.out.print("\nArrow received:[");
        while(!bestArrowCaptured.isEmpty())
        {
            if(bestArrowCaptured.getSize()!=1)
                System.out.print(bestArrowCaptured.dequeue()+ ", ");
            else
                System.out.println(bestArrowCaptured.dequeue() + "]");
        }
        System.out.println("Total = " + totalCaptured);
    }
//----------------------------------------------------------------------------------------------------        
     
    public static int[] useStrawMenCount(String direction,int[]count)
    {
        switch(direction)
        {
            case "front":
                count[0]++;
                break;
            case "left":
                count[1]++;
                break;
            case "right":
                count[2]++;
                break;    
            case "back":
                count[3]++;
                break;    
        }
        return count;
    }
    
    public static int[] efficiency(int[] numStrawMen, int arrow, int[] count)
    {
        int[]captured = new int[4];
        
        for(int i=0; i<4; i++)
        {
            if(count[i]==0)
                captured[i] = arrow * numStrawMen[i]/100;
            else if(count[i]==1)
                captured[i] = (int) (arrow * (numStrawMen[i]*0.8)/100);
            else if(count[i]==2)
                captured[i] = (int) (arrow * (numStrawMen[i]*0.4)/100);
            else
                captured[i] = 0;
        }
        return captured;
        
    }
}
