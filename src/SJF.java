import java.util.ArrayList;
import java.util.Scanner;

public class SJF {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int number;
		int waitingTime[], burstTime[], turnaroundTime[],arrivalTime[],bTime[];
		float averageWTime = 0, averageTATime = 0;
		
		System.out.println("----- Shortest Job First (SJF) -----\n"); 
		
		Scanner num = new Scanner(System.in);
		
		//get the number of process
		System.out.println("Enter no of process"); 
		number = num.nextInt(); 
		
		waitingTime = new int[number];
		burstTime = new int[number];
		bTime = new int[number];
		turnaroundTime = new int[number];
		arrivalTime = new int[number];
		
		
		int aTime=0;
		int TbTime=0;
		
		//get the burst time for each processes
		for(int i = 0; i < number; i++){ 
			System.out.println("Enter the burst time for process " +(i+1)+" :");
			burstTime[i] = num.nextInt();
			bTime[i]=burstTime[i];
			TbTime+= burstTime[i];
			System.out.println("Enter the arrival time for process " +(i+1)+" :");
			arrivalTime[i] = num.nextInt();
			if(aTime<arrivalTime[i])
				aTime=arrivalTime[i];
		}
		
		//Initialize the start time of CPU usage
		long CPUtimeBefore = System.currentTimeMillis(); 
		
		//initialize data in waiting time & turn around time to zero
		for(int i = 0; i < number; i++){
			waitingTime[i]=0;
			turnaroundTime[i]=0;	
		}
		
		int temp; 
		
		/*  ------SJF Algorithm -----*/
		
			
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
						
						temp = bTime[j]; 	
						bTime[j] = bTime[j+1]; 
						bTime[j+1] = temp; 
						
						
					}
				}
			} 
		
		
		int tempi=-1;
		int tempb=0;
		int time =0;
		
		//while all process are not done
		while(TbTime!=0) {
			for(int i = 0; i < number; i++){
				//Check for processes arrival time
				if(time > arrivalTime[i]) {
					//compare burst time of every process,the least burst time is prioritized
					if((tempb==0 || bTime[i]<tempb) && bTime[i]!=0 ) {
						tempi=i;
						tempb=bTime[i];
					}
					//if job completed don't add the waitingTime
					if(bTime[i]!=0) 
						waitingTime[i]++;				
				}	
			}
			
			// process the least burst time job
			if(tempi!= -1 && bTime[tempi]!=0) {
				bTime[tempi]--;
				tempb=bTime[tempi];
				waitingTime[tempi]--;
				TbTime--;
				System.out.println("Time"+time);
				System.out.println("i:"+tempi);
				System.out.println("Burst Time"+bTime[tempi]);
				System.out.println("");
			}
			time++;
		}
	
		
	//allocate the turn around time and waiting time for each process
		for(int i=0;i < number; i++){
			turnaroundTime[i] = burstTime[i]+ waitingTime[i];   
		} 
		
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
		System.out.println("| Process | BurstTime | WaitingTime | TurnAroundTime | Arrival Time");	
		
		for(int i = 0; i < number; i++){
			System.out.println("      "+ i +" \t"+burstTime[i]+"\t     "+waitingTime[i]+"\t\t    "+turnaroundTime[i]+"\t\t    "+arrivalTime[i]);
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
