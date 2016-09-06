
/*
 * Name: Alfaro, Amanda
 * Project: PA-1 (Producer-Consumer)
 * File: filename
 * Instructor: Feng Chen
 * Class: cs4103-sp16
 * LogonID: cs4103xx
 * 
 */
import java.util.*;
public class ProducerConsumer extends Thread {

	
	public static void main(String args[]){
		ArrayList ele = new ArrayList();
		int size = 5;
		Thread producerThread = new Thread(new Producer(ele,size), "Producer");
		Thread consumerThread = new Thread(new Consumer(ele,size), "Consumer");
		producerThread.start();
		consumerThread.start();
		(new ProducerConsumer()).start();
		Scanner cin = new Scanner(System.in);
		for(int i=0; i<4;i++){
			System.out.print("Enter ");
			int n = cin.nextInt();
		}
	}
}

