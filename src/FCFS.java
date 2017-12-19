import java.util.Scanner;

public class FCFS {
	public static void main(String[] args) {
		
		int number;
		float averageWTime = 0, averageTATime = 0;
		int waitingTime[], burstTime[], turnaroundTime[];
		
		
		System.out.println("------ First Come First Serve (FCFS)------- \n"); 
		
		Scanner num = new Scanner(System.in);
		
		//get the number of process
		System.out.println("Enter number of process:"); 
		number = num.nextInt(); 
		
		waitingTime = new int[number];
		burstTime = new int[number];
		turnaroundTime = new int[number];
		
		
		//Initialize the start time of CPU usage
		for(int i = 0; i < number; i++){ 		
			System.out.println("Enter the burst time for process " + (i+1) + ":" );
			burstTime[i] = num.nextInt();
		}
		
		float CPUtimeBefore = System.currentTimeMillis();
		
		waitingTime[0] = 0;
		
		/* First Come First Server(FCFS) Algorithm  */
		
		
		//calculate the waiting for each process
		for(int i = 1; i < number; i++){ 
			waitingTime[i] = waitingTime[i-1]+ burstTime[i-1]; 
		} 
		
		//calculate turn around time for each process and average waiting time
		for(int i = 0; i < number; i++){ 
			turnaroundTime[i] = waitingTime[i] + burstTime[i]; 
			averageWTime += waitingTime[i]; 
		} 
		
		
		//calculate average turn around time for all processes
		for(int j = 0; j < number; j++){
			averageTATime += turnaroundTime[j]; 
		}
		
		System.out.println("\n------------------TABLE---------------- \n");

		System.out.print("\n");
		System.out.println("| Process | BurstTime | WaitingTime | TurnAroundTime |");	
		
		for(int i = 0; i < number; i++){
			System.out.println("      "+ i +" \t"+burstTime[i]+"\t     "+waitingTime[i]+"\t\t    "+turnaroundTime[i]);
		}
		
		System.out.println("\n");
		
		System.out.printf("\n\n1Average Turn Around Time:  %.2f \n ", averageWTime/number);
		
		System.out.printf("Average Turn Around Time:  %.2f \n", averageTATime/number);
		
		//Get the end time of CPU usage during the program
		float CPUtimeAfter = System.currentTimeMillis(); 
				
		//calculate CPU usage
		float CPUtimeDifference = CPUtimeAfter - CPUtimeBefore; 
		
		System.out.println("CPU Time After :" + CPUtimeDifference);
		
		num.close();
	}	
}
