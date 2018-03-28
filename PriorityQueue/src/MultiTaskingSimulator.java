/**
 * 
 *  Due Friday Next Week 11:59 PM. March 30.
 * @author Tai Dao
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MultiTaskingSimulator {
	static int a_HeapSize;
	static int currentPID;
	
	public static void main(String[] args) {
		ArrayList <Process> A = new ArrayList<Process> ();
		for (int i = 0; i < 15 ; i++) {
			//A.add(new Process(i));
			currentPID = i;
			A.add(new Process(currentPID)); // add 20 Processes into ArrayList
		}
		buildMaxHeap(A);
		startMenu(A);
		
		/* Test Bench

		printArrayList(A);
		heapSort(A);

		printArrayList(A);
		
		
		printArrayList(A);

		buildMaxHeap(A);
		//heapSort(A); // Wow we don't even need to do heapSort to do a priority queue!
		
		System.out.println();
		System.out.println();
		printArrayList(A);
		
		System.out.println();
		System.out.println();
		
		////System.out.println(A.get(0).compareTo(A.get(14)));
		
		//System.out.println(heapExtractMax(A));
		System.out.println();
		//heapIncreaseKey(A, 5, 9998);

		*/
	}
	

	/**
	 * This function takes an ArrayList of Processes and makes their priorities adhere to the max heap property.
	 * @param ArrayList<Process> A
	 */
	private static void buildMaxHeap(ArrayList<Process> A) {
		a_HeapSize = A.size() - 1; //(element of last index is ArraySize minus 1)
		for (int i = (A.size() - 1)/2; i >= 0; i--) {
			maxHeapify(A, i);
		}
	}
	
	
	/**
	 * This function is used by buildMaxHeap to recursively swap children nodes with 
	 * parent nodes if the child node is greater than the parent node.
	 * @param ArrayList<Process> A
	 * @param i
	 */
	private static void maxHeapify (ArrayList<Process> A, int i) {
		int largest;
		int l = 2*i + 1;
		int r = 2*i + 2;
		if (l <= a_HeapSize && A.get(l).compareTo(A.get(i)) == 1) {
			//in compareTo Method a result of 1 means A[l] > A[i]
			largest = l;
		}
		else {
			largest = i;
		}
		if (r <= a_HeapSize && A.get(r).compareTo(A.get(largest)) == 1) {
			largest = r;
		}
		if (largest != i) {
			Collections.swap(A, i, largest);
			maxHeapify(A,largest);
		}
	}
	
	/**
	 * This function performs a heapsort of the ArrayList of Processes by priority
	 * 
	 * @param ArrayList<Process> A
	 */
	public static void heapSort(ArrayList<Process> A) {
		buildMaxHeap(A);
		for (int i = (A.size()-1); i >= 1 ; i--) {
			Collections.swap(A, 0, i);
			a_HeapSize = a_HeapSize - 1;
			maxHeapify(A,0);
		}
	}
	
	/**
	 * This function extracts the process of the highest priority
	 * from the heap and maintains the heap property after extracting.
	 * 
	 * @param ArrayList<Process> A
	 */
	public static int heapExtractMax(ArrayList<Process> A) {
		if (a_HeapSize < 0) {
			throw new RuntimeException("Error: Heap Underflow.");
		}
		int max = A.get(0).getPriority();
		A.get(0).setPriority(A.get(a_HeapSize).getPriority());
		a_HeapSize--;
		maxHeapify(A,0);
		return max;
	}
	
	
	/**
	 * This function returns a process with the highest priority from the heap,
	 * but does not remove it from the heap.
	 * 
	 * @param ArrayList<Process> A
	 */
	public static Process heapMaximum(ArrayList<Process> A) {
		return A.get(0);
	}
	
	
	/**
	 * TODO: INSERT INSERT INSERT
	 * 
	 * @param ArrayList<Process> A, Process p (key)
	 */
	public static void maxHeapInsert(ArrayList<Process> A, Process p) {
		// TODO
		a_HeapSize++;
		Process newProcess = new Process(Integer.MIN_VALUE);
		A.add(newProcess);
		//A.get(a_HeapSize).setPriority(Integer.MIN_VALUE);
		heapIncreaseKey(A, a_HeapSize, p);
	}
	
	
	/**
	 * TODO: HEAP INCREASE KEY
	 * 
	 * @param ArrayList<Process> A, int i (index), Process p (key)
	 */
	public static void heapIncreaseKey(ArrayList<Process> A, int i, Process p) {
		// TODO //heapsize is the first call... LAST NODE
		int parentIndex = (i-1)/2;
		
		if (p.compareTo(A.get(i)) == -1) {
			throw new RuntimeException("Error: New key is smaller than current key.");
		}
		A.set(i, p);
		printProcess(A.get(i)); // last process = -2147...
		printProcess(A.get(parentIndex));
		while (i > 0 && A.get(parentIndex).compareTo(A.get(i)) == -1) { // i = 0??? because java?
			Collections.swap(A, 0, parentIndex);
			i = parentIndex; //what is parent(i)? the index of parent? wouldn't that be 0? Trying 0 here.
		}
	}
	

	/**
	 * This function prints the PIDs & Priorities of all elements of the
	 * ArrayList <Process> that was passed to it.
	 * 
	 * @param ArrayList<Process>
	 */
	private static void printArrayList(ArrayList<Process> A) {
		System.out.println("Index\tPID\tPriority");
		for (int i = 0; i < A.size(); i++) {
			System.out.print(i + "\t");
			System.out.print(A.get(i).getPID() + "\t");
			System.out.println(A.get(i).getPriority());
		}
		System.out.println();
	}
	
	/**
	 * This function prints the PID & Priority of an element by taking an
	 * index and ArrayList <Process> that was passed to it.
	 * 
	 * @param int i; // index
	 * @param ArrayList<Process>
	 */
	private static void printProcess(Process p) {
		System.out.println("PID\tPriority");
		System.out.print(p.getPID());
		System.out.println("\t" + p.getPriority());
	}
	
	/**
	 * This function displays a menu with operations that can be done to an 
	 * ArrayList of Processes.
	 * @param ArrayList<Process> A
	 */
	private static void startMenu(ArrayList<Process> A) {
		String choice = null;
		while (choice != "q" || choice != "Q") {
			System.out.println("************ Menu Of Availiable Options ************");
			System.out.println("1. Show current ArrayList of Processes. (Priority Queue)");
			System.out.println("2. View and remove the first priority process.");
			System.out.println("3. Increase priority of a specified process.");
			System.out.println("4. Insert a new process with a random priority index.");
			System.out.println("5. Sort Processes by priority and displays list of Processes\n" +
							   "   (Warning: PriorityQueue will not function properly anymore b/c of heapSort)");
			System.out.println("Q. Quit.\n");
			
			System.out.print("Please type in an option and press enter: ");
		    Scanner scan = new Scanner(System.in);
		    choice = scan.nextLine();
		    System.out.println();
		    switch(choice) {
			    case "1": 
			    	printArrayList(A);
					System.out.println();
			    	break;
			    case "2": 
			    	Boolean repeat = true;
			    	System.out.println("Given the list is not heapsorted. The highest priority process is: ");
			    	Process highestPriorityProcess = heapMaximum(A);
			    	printProcess(highestPriorityProcess);
			    	System.out.println();
			    	
			    	while (repeat) {
				    	System.out.println("Do you want to remove this process? Type Y/N and press Enter: ");
				    	String ynChoice;

				    	ynChoice = scan.nextLine();
				    	switch(ynChoice) {
				    		case "Y":
				    		case "y":
				    			heapExtractMax(A);
						    	System.out.println("Highest priority process was removed from PQ.\n");
						    	printArrayList(A);
						    	repeat = false;
						    	break;
				    		case "N":
				    		case "n":
						    	System.out.println("Process removal canceled...\n");	   
						    	repeat = false;
						    	break;
						    default: 
						    	System.out.println("Invalid input. Please try again.");
					    		repeat = true;	
				    	}		
			    	}
			    	break;
			    case "3": 
			    	//TODO user choose INDEX and increases it's priority
			    	break;
			    case "4": 
			    	//TODO: I am stuck here
			    	Process newProcess = new Process(currentPID++);
					maxHeapInsert(A, newProcess);					
					printArrayList(A);
					System.out.println();
			    	break;
			    case "5":
			    	heapSort(A);
					printArrayList(A);
					System.out.println();
					break;
			    case "Q": 
			    case "q": 
			    	System.out.println("Exiting... Thank you and have a nice day!");
			    	System.exit(1);
			    	break;
			    default:
			    	System.out.println("Invalid Choice. Please try again.\n");
		    }
		}	
	}
}

