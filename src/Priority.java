import java.util.Scanner;


public class Priority {
	public static void main (String args[]) {
		
		int number;
		float averageWTime = 0, averageTATime = 0,TbTime=0;
		int waitingTime[],burstTime[],turnaroundTime[],priority[],arrivalTime[],bTime[];
		int tempi=-1;
		int tempp=-1;
		int tempb=0;
		int time =0;
		
		System.out.println("------ Priority Scheduling -----\n");
		
		Scanner num = new Scanner(System.in);
		
		//get the number of process
		System.out.println("Enter no of process :"); 
		number = num.nextInt(); 
		
		waitingTime = new int[number];
		burstTime = new int[number];
		bTime = new int[number];
		turnaroundTime = new int[number];
		priority = new int[number];
		arrivalTime = new int[number];
		
		for(int i = 0; i < number; i++){ //input the burst time for each processes
			System.out.println("Enter the burst time for process " +(i+1)+":");
			burstTime[i] = num.nextInt();
			bTime[i]=burstTime[i];
			TbTime+=burstTime[i];
			System.out.println("Enter the arrival time for process " +(i+1)+":");
			arrivalTime[i] = num.nextInt();
			System.out.println("Enter the priority of process(highest priority 1 to infiity) "+ (i+1) +":");
			priority[i] = num.nextInt(); 
		}
		
		long CPUtimeBefore = System.currentTimeMillis(); //Initialize the start time of CPU usage
		
		for(int i = 0; i < number; i++){
			waitingTime[i]=0;
			turnaroundTime[i]=0;	//initialize data in waiting time & turn around time to zero
		}
		
		int temp;

		
		/*----- Priority Scheduling ---*/
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
					
					temp = bTime[j]; 	
					bTime[j] = bTime[j+1]; 
					bTime[j+1] = temp; 
					
					temp = priority[j]; 	
					priority[j] = priority[j+1]; 
					priority[j+1] = temp; 
					
				}
			}
		} 
	//while all process are not done
	while(TbTime!=0) {
		for(int i = 0; i < number; i++){
			//Check for processes arrival time
			if(time > arrivalTime[i]) {
				//compare burst time of every process,the least burst time is prioritized
				if( (tempb==0 || tempp==-1 || priority[i]<tempp)&& bTime[i]!=0 ) {
					tempi=i;
					tempp=priority[i];
					tempb=bTime[i];
				}
				//if job completed don't add the waitingTime
				if(bTime[i]!=0) 
					waitingTime[i]++;				
			}	
		}
		
		// process the highest priority job
		if(tempi!= -1  && bTime[tempi]!=0) {
			bTime[tempi]--;
			waitingTime[tempi]--;
			TbTime--;
			tempb=bTime[tempi];
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
		
		System.out.println("\n-------------- TABLE --------------");

		System.out.println(" Process | BurstTime | WaitingTime | TurnAroundTime | Priority | Arrival Time\n");	
		
		for(int i = 0; i < number; i++){
			System.out.println("     "+ i +"       \t"+burstTime[i]+"\t     "+waitingTime[i]+"\t\t    "+turnaroundTime[i]+"\t\t"+priority[i]+"\t\t "+arrivalTime[i]);
		}
		
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
