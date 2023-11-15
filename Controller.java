// OPSYS HWPROJECT
// Michael Cantrell
// MAC180015

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Controller {

	public static void runProcess(Process proc, int time) {
		System.out.println(proc.processName + ": " + proc.serviceTime + "-" + time);
	}
	
	public static void firstComeFirstServe(ArrayList<Process> procArr) {
		int seconds = 0;
		int q = 1;
		ArrayList<Process> queue = new ArrayList<Process>();
		do {
			System.out.println("Seconds: " + seconds);
			if((seconds % 2 == 0) && (seconds < 10)) {
				int index = seconds / 2;
				procArr.get(index).arrivalTime = index;
				queue.add(procArr.get(index));
				
			}
			
			runProcess(queue.get(0), q);
			queue.get(0).serviceTime -= q;
			if(queue.get(0).serviceTime <= 0) {
				queue.remove(0);
			}
			
			seconds++;
		} while (!queue.isEmpty());
	}
	
	public static void roundRobin(ArrayList<Process> procArr) {
		int seconds = 0;
		int q = 1;
		ArrayList<Process> queue = new ArrayList<Process>();
		do {
			System.out.println("Seconds: " + seconds);
			if((seconds % 2 == 0) && (seconds < 10)) {
				queue.add(procArr.get(seconds / 2));
			}
			
			runProcess(queue.get(0), q);
			queue.get(0).serviceTime -= q;
			if(queue.get(0).serviceTime <= 0) {
				queue.remove(0);
			}
			
			if(queue.size() > 1) {
				queue.add(queue.get(0));
				queue.remove(0);
			}
			
			seconds++;
		} while (!queue.isEmpty());
	}
	
	
	public static void shortestRemainingTime(ArrayList<Process> procArr) {
		int seconds = 0;
		int q = 1;
		ArrayList<Process> queue = new ArrayList<Process>();
		do {
			System.out.println("Seconds: " + seconds);
			if((seconds % 2 == 0) && (seconds < 10)) {
				queue.add(procArr.get(seconds / 2));
			}
			
			Collections.sort(queue, Comparator.comparing(Process::getServiceTime));
					
			runProcess(queue.get(0), q);
			queue.get(0).serviceTime -= q;
			if(queue.get(0).serviceTime <= 0) {
				queue.remove(0);
			}
			
			seconds++;
		} while (!queue.isEmpty());
		
	}
	
	
	public static void highestResponseRatioNext(ArrayList<Process> procArr) {
		int seconds = 0;
		int q = 1;
		ArrayList<Process> queue = new ArrayList<Process>();
		do {
			System.out.println("Seconds: " + seconds);
			if((seconds % 2 == 0) && (seconds < 10)) {
				queue.add(procArr.get(seconds / 2));
			}
			
			Collections.sort(queue, Comparator.comparing(Process::getRatio));
					
			runProcess(queue.get(0), q);
			queue.get(0).serviceTime -= q;
			if(queue.get(0).serviceTime <= 0) {
				queue.remove(queue.get(0));
			}
			
			for(int i = 1; i < queue.size(); i++) {
				queue.get(i).waitTime++;
			}
			
			seconds++;
		} while (!queue.isEmpty());
	}
	
	public static void main(String[] args) {
		
		System.out.println("\n\nFirst Come First Serve: ");
		
		ArrayList<Process> procArr = new ArrayList<Process>();
		procArr.add(new Process("A", 6, 1, 1));
		procArr.add(new Process("B", 12, 2, 2));
		procArr.add(new Process("C", 8, 1, 1));
		procArr.add(new Process("D", 10, 0, 0));
		procArr.add(new Process("E", 4, 2, 2));
		Collections.shuffle(procArr);
		firstComeFirstServe(procArr);
		
		System.out.println("\n\nRound Robin: ");
		
		ArrayList<Process> procArr1 = new ArrayList<Process>();
		procArr1.add(new Process("A", 6, 1, 1));
		procArr1.add(new Process("B", 12, 2, 2));
		procArr1.add(new Process("C", 8, 1, 1));
		procArr1.add(new Process("D", 10, 0, 0));
		procArr1.add(new Process("E", 4, 2, 2));
		Collections.shuffle(procArr1);
		roundRobin(procArr1);
		
		System.out.println("\n\nShortest Remaining Time: ");
		
		ArrayList<Process> procArr2 = new ArrayList<Process>();
		procArr2.add(new Process("A", 6, 1, 1));
		procArr2.add(new Process("B", 12, 2, 2));
		procArr2.add(new Process("C", 8, 1, 1));
		procArr2.add(new Process("D", 10, 0, 0));
		procArr2.add(new Process("E", 4, 2, 2));
		Collections.shuffle(procArr2);
		shortestRemainingTime(procArr2);
		
		System.out.println("\n\nHighest Response Ratio Next: ");
		
		ArrayList<Process> procArr3 = new ArrayList<Process>();
		procArr3.add(new Process("A", 6, 1, 1));
		procArr3.add(new Process("B", 12, 2, 2));
		procArr3.add(new Process("C", 8, 1, 1));
		procArr3.add(new Process("D", 10, 0, 0));
		procArr3.add(new Process("E", 4, 2, 2));
		Collections.shuffle(procArr3);
		highestResponseRatioNext(procArr3);
	
		
	}
}
