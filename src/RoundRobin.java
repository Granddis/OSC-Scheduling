import java.util.Scanner;

public class RoundRobin {

	public static void main(String[] args) {

		int number, sum, timeQuantum,TbTime;
		int waitingTime[], burstTime[], turnaroundTime[], bTime[],arrivalTime[];
		float averageWTime = 0, averageTATime = 0;	

		System.out.println("-------Round Robin-------- \n"); 
		
		Scanner num = new Scanner(System.in);
		
		//input number of process
		System.out.println("Enter no of process"); 
		number = num.nextInt(); 
		
		arrivalTime = new int[number];
		waitingTime = new int[number];
		burstTime = new int[number];
		bTime = new int[number];
		turnaroundTime = new int[number];
		
		TbTime=0;
		
		//input burst time for each process
		for(int i = 0; i < number; i++){
			System.out.println("Enter the burst time for process " +(i+1) + ":");
			burstTime[i] = num.nextInt(); 
			
			System.out.println("Enter the arrival time for process " +(i+1)+":");
			arrivalTime[i] = num.nextInt(); 
		}
		
		//input quantum time
		System.out.println("Enter time quantum?"); 
		
		timeQuantum = num.nextInt();
		
		//Initialize the start time of CPU usage
		long CPUtimeBefore = System.currentTimeMillis(); 
		
		
		//copy burstTimeInput into burstTimeOutput for print
		for(int i = 0; i < number; i++){
			TbTime+=burstTime[i];
			 bTime[i]=burstTime[i]; 
		}
		
		
		//initialize elements in waiting time to zero
		for(int i = 0; i < number; i++){
			waitingTime[i] = 0; 
		}
		
		int temp=0;
		
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
					
				}
			}
		} 
		
	/*---- Round Robin Algorithm -----*/	
		
	int tempi=0;	
	int qCounter=timeQuantum;	
	int time=0;
	int ni=0;
	//while all process are not done
	while(TbTime!=0) {
		for(int i = 0; i < number; i++){
			//Check for processes arrival time
			if(time > arrivalTime[i]) {
				//process the first arrived jobs with n quantum times set
				if(i==ni && qCounter!=0 && bTime[i]!=0) {
					bTime[ni]--;
					TbTime--;
					qCounter--;
				}
				//if job completed or the current processing jobs , don't add the waitingTime 
				if(bTime[i]!=0 && i!=ni) 
					waitingTime[i]++;				
			}	
		}
		//if Task complete before the quantum number or quantum number consumed move to the next available node
		if(qCounter==0 || bTime[ni]==0) {
			tempi=ni;
			ni++;
			qCounter=timeQuantum;
			if(ni==number)
				ni=0;
			//if the next node process has yet to arrived or done keep looping
			while(bTime[ni]==0 && TbTime!=0 || arrivalTime[ni]>time+1){
				ni++;
				if(ni==number) {
					ni=0;
				}
				if(ni==tempi)
					break;
			}
		}

		time++;

	}
	
		
		 //allocate turn around time for each process
		for(int i = 0; i < number; i++){
			turnaroundTime[i] = waitingTime[i] + burstTime[i];
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
		System.out.println("| Process | BurstTime | WaitingTime | TurnAroundTime | Arrival Time");	
		
		for(int i = 0; i < number; i++){
			System.out.println("      "+ i +" \t"+burstTime[i]+"\t     "+waitingTime[i]+"\t\t    "+turnaroundTime[i]+"\t\t "+arrivalTime[i]);
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
