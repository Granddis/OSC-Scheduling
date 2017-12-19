import java.util.Scanner;

public class RoundRobin {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		int n, sum, timeQuantum;
		int waitingTime[], burstTimeInput[], turnaroundTime[], burstTimeOutput[];
		float averageWTime = 0, averageTATime = 0;	

		System.out.println("         Round Robin           \n"); 
		
		Scanner num = new Scanner(System.in);
		System.out.println("Enter no of process"); 
		n = num.nextInt(); //input number of process
		
		waitingTime = new int[n];
		burstTimeInput = new int[n];
		turnaroundTime = new int[n];
		burstTimeOutput = new int[n];
		
		for(int i = 0; i < n; i++){
			System.out.println("Enter the burst time for process " +(i+1));
			burstTimeInput[i] = num.nextInt(); //input burst time for each process
		}
		
		System.out.println("Enter time quantum?"); //input quantum time
		
		timeQuantum = num.nextInt();
		
		long cputimeBefore = System.currentTimeMillis(); //Initialize the start time of CPU usage
		
		for(int i = 0; i < n; i++){
			burstTimeOutput[i] = burstTimeInput[i]; //copy burstTimeInput into burstTimeOutput for print
		}
		
		for(int i = 0; i < n; i++){
			waitingTime[i] = 0; //initialize elements in waiting time to zero
		}
		
		/*  ======= Round Robin Algorithm ====== */
		
		do {
			for(int i = 0; i < n; i++){
				if(burstTimeInput[i] > timeQuantum){ //compare burstTime with quantum
					burstTimeInput[i] -= timeQuantum; //minus current burst time with timeQuantum
					
					for(int j = 0; j < n; j++){
						if((j != i) && (burstTimeInput[j] != 0 )){
							waitingTime[j] += timeQuantum; //allocate the waiting time for the correct process
						}
					}
				}else{
					for(int j = 0; j < n; j++){
						if((j != i) && (burstTimeInput[j] != 0)){
							waitingTime[j] += burstTimeInput[i]; 
						}
					
					}
					burstTimeInput[i]=0;
				}
			}
			
			sum = 0;
			
			for(int k = 0; k < n; k++){
				sum = sum + burstTimeInput[k]; //sum of burstInput after minus with timeQuantum
			}
			
		}while(sum != 0);
		
		/*  ======= End ====== */
		
		for(int i = 0; i < n; i++){
			turnaroundTime[i] = waitingTime[i] + burstTimeOutput[i]; //allocate turn around time for each process
		}
			
		for(int j = 0; j < n; j++){
			averageWTime += waitingTime[j]; //calculate average waiting 
		}
		
		for(int j = 0; j < n; j++){
			averageTATime += turnaroundTime[j]; //calculate average turn around time
		}
		
		System.out.println("\n                   TABLE                          \n");

		System.out.print("\n");
		System.out.println("| Process | BurstTime | WaitingTime | TurnAroundTime |");	
		
		for(int i = 0; i < n; i++){
			System.out.println("      "+ i +" \t"+burstTimeOutput[i]+"\t     "+waitingTime[i]+"\t\t    "+turnaroundTime[i]);
		}
		
		System.out.println("\n");
		
		System.out.println("Average Waiting Time "+ String.format("%.2f", (averageWTime = averageWTime/n)));
		
		System.out.println("Average Turn Around Time "+ String.format("%.2f", (averageTATime = averageTATime/n)));
		
		long cputimeAfter = System.currentTimeMillis(); //Get the end time of CPU usage during the program
		
		long cputimeDifference = cputimeAfter - cputimeBefore; //Calculate CPU usage
		
		System.out.println("CPU Time After " + cputimeDifference);
		
	}
}
