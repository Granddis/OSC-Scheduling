import java.util.Scanner;

public class FCFS {
	public static void main(String[] args) {
		
		int number;
		float averageWTime = 0, averageTATime = 0;
		int waitingTime[], burstTime[], turnaroundTime[],arrivalTime[];
		
		
		System.out.println("------ First Come First Serve (FCFS)------- \n"); 
		
		Scanner num = new Scanner(System.in);
		
		//get the number of process
		System.out.println("Enter number of process:"); 
		number = num.nextInt(); 
		
		waitingTime = new int[number];
		burstTime = new int[number];
		turnaroundTime = new int[number];
		arrivalTime = new int[number];
		
		
		//get the burst time and arrival time of each processes
		for(int i = 0; i < number; i++){ 		
			System.out.println("Enter the burst time for process " + (i+1) + ":" );
			burstTime[i] = num.nextInt();
			System.out.println("Enter the arrival time for process " + (i+1) + ":" );
			arrivalTime[i] = num.nextInt();
			
		}
		
		//Initialize the start time of CPU usage
		long CPUtimeBefore = System.currentTimeMillis();
		
		waitingTime[0] = 0;
		
		int temp;
		
		/* First Come First Server(FCFS) Algorithm  */
		
		//sort the array according to arrival time
		for(int i = 0; i < number; i++){
			for(int j=0;j<number-1;j++){ 	
				//compare processess's arrival time,if previous are more than next,swap
				if(arrivalTime[j] > arrivalTime[j+1]){ 
					temp = burstTime[j]; 	
					burstTime[j] = burstTime[j+1]; 
					burstTime[j+1] = temp; 
					
					temp = arrivalTime[j]; 	
					arrivalTime[j] = arrivalTime[j+1]; 
					arrivalTime[j+1] = temp;
					
				}
			}
		} 
		
		//calculate the waiting for each process
		for(int i = 1; i < number; i++){ 
			waitingTime[i] = waitingTime[i-1]+ burstTime[i-1]-arrivalTime[i]+arrivalTime[i-1]; 
			if(waitingTime[i]<0) {
				waitingTime[i]=0;
			}
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
		System.out.println("| Process | BurstTime | WaitingTime | TurnAroundTime | ArrivalTime");	
		
		for(int i = 0; i < number; i++){
			System.out.println("      "+ i +" \t"+burstTime[i]+"\t     "+waitingTime[i]+"\t\t    "+turnaroundTime[i] +"\t\t"+arrivalTime[i]);
		}
		
		System.out.println("\n");
		
		System.out.printf("\nAverage Waiting Time:  %.2f \n", averageWTime/number);
		
		System.out.printf("Average Turn Around Time:  %.2f \n", averageTATime/number);
		
		//Get the end time of CPU usage during the program
		long CPUtimeAfter = System.currentTimeMillis(); 
				
		//calculate CPU usage
		long CPUtimeDifference = CPUtimeAfter - CPUtimeBefore; 
		
		System.out.println("CPU Time After :" + CPUtimeDifference);
		
		num.close();
	}	
}
