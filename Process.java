// OPSYS HWPROJECT
// Michael Cantrell
// MAC180015

public class Process {
	
	public String processName;
	public int serviceTime = 1;
	public int diskIOTime;
	public int diskIOActivity;
	public int waitTime = 0;
	
	public int startTime = 0;
	public int arrivalTime = 0;
	public int finishTime = 0;
	public int responseTime = (arrivalTime - startTime);
	public int turnAroundTime = (finishTime - arrivalTime);
	public int TAvsServiceTime = (turnAroundTime / serviceTime);
	
	public int getServiceTime() {
		return serviceTime;
	}
	
	public double getRatio() {
		return ((waitTime + serviceTime) / serviceTime);
	}
	
	public Process(String name, int ser, int disk, int act) {
		processName = name;
		serviceTime  = ser;
		diskIOTime = disk;
		diskIOActivity = act;
	}
}
