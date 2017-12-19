import java.util.Scanner;


public class Priority {
	public static void main (String args[]) {
		
		int n;
		float averageWTime = 0, averageTATime = 0;
		int waitingTime[],burstTime[],turnaroundTime[],priority[];
		
		System.out.println("------ Priority Scheduling -----\n");
		
		Scanner num = new Scanner(System.in);
		
		//get the number of process
		System.out.println("Enter no of process :"); 
		n = num.nextInt(); 
		
		waitingTime = new int[n+1];
		burstTime = new int[n+1];
		turnaroundTime = new int[n+1];
		priority = new int[n+1];
		
		for(int i = 0; i < n; i++){ //input the burst time for each processes
			System.out.println("Enter the burst time for process " +(i+1)+":");
			burstTime[i] = num.nextInt();
			System.out.println("Enter the priority of process "+ (i+1) +":");
			priority[i] = num.nextInt(); 
		}
		
		float CPUtimeBefore = System.currentTimeMillis(); //Initialize the start time of CPU usage
		
		for(int i = 0; i < n; i++){
			waitingTime[i]=0;
			turnaroundTime[i]=0;	//initialize data in waiting time & turn around time to zero
		}
		
		int temp;

		
		/*----- Priority Scheduling ---*/
		
		for(int i = 0; i < n; i++){
			for(int j=0;j<n-1;j++){ 	
				if(priority[j]> priority[j+1]) {
					temp = burstTime[j]; 	//process with less burst time, start first
					burstTime[j] = burstTime[j+1]; 
					burstTime[j+1] = temp; 
					
					temp = waitingTime[j]; 
					waitingTime[j] = waitingTime[j+1]; //allocate the waiting time for each process  
					waitingTime[j+1] = temp; 	//according to burst time
					
					temp = priority[j]; 
					priority[j] = priority[j+1]; //allocate the waiting time for each process  
					priority[j+1] = temp; 	//according to burst time
					
				}
			}
		}
		
		
		//allocate the turn around time and the waiting time for each process
		for(int i=0;i < n; i++){
			turnaroundTime[i] = burstTime[i]+ waitingTime[i]; 
			waitingTime[i+1] = turnaroundTime[i];  
		} 
		turnaroundTime[n] = waitingTime[n]+ burstTime[n]; 
		
		
		//calculate average waiting time
		for(int j = 0; j < n; j++){
			averageWTime += waitingTime[j]; 
		}
		
		
		//calculate average waiting time
		for(int j = 0; j < n; j++){
			averageTATime += turnaroundTime[j];  
		}
		
		System.out.println("\n-------------- TABLE --------------");

		System.out.println(" Process | BurstTime | WaitingTime | TurnAroundTime | Priority");	
		
		for(int i = 0; i < n; i++){
			System.out.println("     "+ i +"       \t"+burstTime[i]+"\t     "+waitingTime[i]+"\t\t    "+turnaroundTime[i]+"\t\t"+priority[i]);
		}
		
		System.out.printf("\n\n1Average Turn Around Time:  %.2f \n ", averageWTime/n);
		
		System.out.printf("Average Turn Around Time:  %.2f \n", averageTATime/n);
				
		//Get the end time of CPU usage during the program
		float CPUtimeAfter = System.currentTimeMillis(); 
		
		//calculate CPU usage
		float CPUtimeDifference = CPUtimeAfter - CPUtimeBefore; 
		
		System.out.println("CPU Time After :" + CPUtimeDifference);
	}
}
