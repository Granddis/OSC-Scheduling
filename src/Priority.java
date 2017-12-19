import java.util.Scanner;


public class Priority {
	public static void main (String args[]) {
		
		int n;
		float averageWTime = 0, averageTATime = 0;
		int waitingTime[],burstTime[],turnaroundTime[],priority[];
		
		System.out.println("==== Priority Scheduling =====\n");

		Scanner num = new Scanner(System.in);
		System.out.println("Enter no of process"); 
		n = num.nextInt(); //get the number of process
		
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
		
		long cputimeBefore = System.currentTimeMillis(); //Initialize the start time of CPU usage
		
		for(int i = 0; i < n; i++){
			waitingTime[i]=0;
			turnaroundTime[i]=0;	//initialize data in waiting time & turn around time to zero
		}
		
		int temp;

		
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
		
		for(int i=0;i < n; i++){
			turnaroundTime[i] = burstTime[i]+ waitingTime[i]; //allocate the turn around time for each process
			waitingTime[i+1] = turnaroundTime[i];  //allocate the waiting time 
		} 
		turnaroundTime[n] = waitingTime[n]+ burstTime[n]; 
		
		for(int j = 0; j < n; j++){
			averageWTime += waitingTime[j]; //calculate average waiting time
		}
		
		for(int j = 0; j < n; j++){
			averageTATime += turnaroundTime[j]; //calculate average waiting time 
		}
		
		System.out.println("\n====================== TABLE =========================");

		System.out.print(" ____________________________________________________\n");
		System.out.println("| Process | BurstTime | WaitingTime | TurnAroundTime | Priority");	
		
		for(int i = 0; i < n; i++){
			System.out.println("      "+ i +" \t"+burstTime[i]+"\t     "+waitingTime[i]+"\t\t    "+turnaroundTime[i]+"\t\t"+priority[i]);
		}
		
		System.out.println("\n======================================================");
		
		System.out.println("Average Waiting Time "+ String.format("%.2f", (averageWTime = averageWTime/n)));
		
		System.out.println("Average Turn Around Time "+ String.format("%.2f", (averageTATime = averageTATime/n)));
				
		long cputimeAfter = System.currentTimeMillis(); //Get the end time of CPU usage during the program
		
		long cputimeDifference = cputimeAfter - cputimeBefore; //calculate CPU usage
		
		System.out.println("CPU Time After " + cputimeDifference);
	}
}
