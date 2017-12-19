import java.util.Scanner;

public class SJF {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int number;
		int waitingTime[], burstTime[], turnaroundTime[];
		float averageWTime = 0, averageTATime = 0;
		
		System.out.println("----- Shortest Job First (SJF) -----\n"); 
		
		Scanner num = new Scanner(System.in);
		
		//get the number of process
		System.out.println("Enter no of process"); 
		number = num.nextInt(); 
		
		waitingTime = new int[number+1];
		burstTime = new int[number+1];
		turnaroundTime = new int[number+1];
		
		//get the burst time for each processes
		for(int i = 0; i < number; i++){ 
			System.out.println("Enter the burst time for process " +(i+1));
			burstTime[i] = num.nextInt();
		}
		
		//Initialize the start time of CPU usage
		float CPUtimeBefore = System.currentTimeMillis(); 
		
		//initialize data in waiting time & turn around time to zero
		for(int i = 0; i < number; i++){
			waitingTime[i]=0;
			turnaroundTime[i]=0;	
		}
		
		int temp; 
		
		/*  ------SJF Algorithm -----*/
		
		for(int i = 0; i < number; i++){
			for(int j=0;j<number-1;j++){ 	
				//compare processess's burst time,if previous are more than next,swap
				if(burstTime[j] > burstTime[j+1]){ 
					temp = burstTime[j]; 	
					burstTime[j] = burstTime[j+1]; 
					burstTime[j+1] = temp; 
					
					//allocate the waiting time for each process  according to burst time
					temp = waitingTime[j]; 
					waitingTime[j] = waitingTime[j+1]; 
					waitingTime[j+1] = temp; 	
				}
			}
		} 
		
		//allocate the turn around time and waiting time for each process
		for(int i=0;i < number; i++){
			turnaroundTime[i] = burstTime[i]+ waitingTime[i]; 
			waitingTime[i+1] = turnaroundTime[i];  
		} 
		turnaroundTime[number] = waitingTime[number]+ burstTime[number]; 
		
		//calculate average waiting time
		for(int j = 0; j < number; j++){
			averageWTime += waitingTime[j]; 
		}
		
		 //calculate average waiting time 
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
		
		System.out.printf("\nAverage Turn Around Time:  %.2f \n", averageWTime/number);
		
		System.out.printf("Average Turn Around Time:  %.2f \n", averageTATime/number);
		
		//Get the end time of CPU usage during the program
		float CPUtimeAfter = System.currentTimeMillis(); 
				
		//calculate CPU usage
		float CPUtimeDifference = CPUtimeAfter - CPUtimeBefore; 
		
		System.out.println("CPU Time After :" + CPUtimeDifference);
		
		num.close();
	}
}
