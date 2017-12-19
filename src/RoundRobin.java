import java.util.Scanner;

public class RoundRobin {

	public static void main(String[] args) {

		int number, sum, timeQuantum;
		int waitingTime[], burstTimeInput[], turnaroundTime[], burstTimeOutput[];
		float averageWTime = 0, averageTATime = 0;	

		System.out.println("-------Round Robin-------- \n"); 
		
		Scanner num = new Scanner(System.in);
		
		//input number of process
		System.out.println("Enter no of process"); 
		number = num.nextInt(); 
		
		waitingTime = new int[number];
		burstTimeInput = new int[number];
		turnaroundTime = new int[number];
		burstTimeOutput = new int[number];
		
		
		//input burst time for each process
		for(int i = 0; i < number; i++){
			System.out.println("Enter the burst time for process " +(i+1));
			burstTimeInput[i] = num.nextInt(); 
		}
		
		//input quantum time
		System.out.println("Enter time quantum?"); 
		
		timeQuantum = num.nextInt();
		
		//Initialize the start time of CPU usage
		float CPUtimeBefore = System.currentTimeMillis(); 
		
		
		//copy burstTimeInput into burstTimeOutput for print
		for(int i = 0; i < number; i++){
			burstTimeOutput[i] = burstTimeInput[i]; 
		}
		
		
		//initialize elements in waiting time to zero
		for(int i = 0; i < number; i++){
			waitingTime[i] = 0; 
		}
		
		/* --- Round Robin Algorithm ---- */
		
		do {
			for(int i = 0; i < number; i++){
				
				//compare burstTime with quantum
				if(burstTimeInput[i] > timeQuantum){ 
					//partition the current burst time according the Quantum time
					burstTimeInput[i] -= timeQuantum; 					
					for(int j = 0; j < number; j++){
						if((j != i) && (burstTimeInput[j] != 0 )){
							//allocate the waiting time for the correct process
							waitingTime[j] += timeQuantum; 
						}
					}
				}else{
					for(int j = 0; j < number; j++){
						if((j != i) && (burstTimeInput[j] != 0)){
							waitingTime[j] += burstTimeInput[i]; 
						}
					
					}
					burstTimeInput[i]=0;
				}
			}
			
			sum = 0;
			
			//sum of burstInput after minus with timeQuantum
			for(int k = 0; k < number; k++){
				sum = sum + burstTimeInput[k]; 
			}
			
		}while(sum != 0);
		
		 //allocate turn around time for each process
		for(int i = 0; i < number; i++){
			turnaroundTime[i] = waitingTime[i] + burstTimeOutput[i];
		}
		
		//calculate average waiting
		for(int j = 0; j < number; j++){
			averageWTime += waitingTime[j];  
		}
		
		for(int j = 0; j < number; j++){
			averageTATime += turnaroundTime[j]; //calculate average turn around time
		}
		
		System.out.println("\n------------------TABLE---------------- \n");

		System.out.print("\n");
		System.out.println("| Process | BurstTime | WaitingTime | TurnAroundTime |");	
		
		for(int i = 0; i < number; i++){
			System.out.println("      "+ i +" \t"+burstTimeOutput[i]+"\t     "+waitingTime[i]+"\t\t    "+turnaroundTime[i]);
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
