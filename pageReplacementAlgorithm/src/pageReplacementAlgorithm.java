import java.util.*;

/*
Name: Alfaro,Amanda
Project: PA-2 (Page Replacement Algorithm)
File: pageReplacementAlgorithm
Instructor: Feng Chen
Class: cs4103-sp16
LogonID: cs410304
*/

public class pageReplacementAlgorithm {
	public static int totalPageReferences, numPageMisses, numPageHits, pageFrames, clockHand;
	public static ArrayList<Integer> victimPages = new ArrayList<Integer>();
	public static int[] pageReferenceString = { 0, 1, 2, 3, 2, 4, 5, 3, 4, 1, 6, 3, 7, 8, 7, 8, 4, 9, 7, 8, 1, 2, 9, 5,4, 5, 0, 2 };
	public static ArrayList<Page> allocatedPageFrames = new ArrayList<Page>();
	public static ArrayList<Page> newPageReference = new ArrayList<Page>();

	public static void clockAlgorithm() {
		for (int i = 0; i < pageReferenceString.length; i++) {
			newPageReference.add(new Page(pageReferenceString[i]));
		}
		for (int i = 0; i < newPageReference.size(); i++) {
			boolean emptySpace = false;
			for (int j = 0; j < pageFrames; j++) {
				if (allocatedPageFrames.size() <= j || allocatedPageFrames.get(j) == null) {
					emptySpace = true;
					allocatedPageFrames.add(newPageReference.get(i));
					newPageReference.get(i).statusBit = 1;
					newPageReference.get(i).referenceBit = 1;
					totalPageReferences++;
					numPageMisses++;
					break;
				}
			}
			if (!emptySpace) {
				if (Check(newPageReference.get(i))){
					newPageReference.get(i).referenceBit = 1;
					numPageHits++;
					totalPageReferences++;
					
				} else { // EVICTION
					while (allocatedPageFrames.get(clockHand).referenceBit != 0) {
						allocatedPageFrames.get(clockHand).referenceBit = 0;
						if (clockHand+1 < pageFrames) {
							clockHand++;
						} else{
							clockHand = 0;
						}
					}
					victimPages.add(allocatedPageFrames.get(clockHand).pageNumber);
					allocatedPageFrames.set(clockHand, newPageReference.get(i));
					allocatedPageFrames.get(clockHand).statusBit = 1;
					if (clockHand+1 < pageFrames) {
						clockHand++;
					} else {
						clockHand = 0;
					}
					totalPageReferences++;
					numPageMisses++;
				}
			}
		}
	}
	public static boolean Check(Page page){
		for(int i=0; i < allocatedPageFrames.size(); i++){
			if(allocatedPageFrames.get(i).pageNumber == page.pageNumber){
				return true; 
			}
		}
		return false;
	}
	public static class Page {
		public int referenceBit = 0;
		public int statusBit = 0; // 0 is Free, 1 is allocated
		public int pageNumber;
		public Page(int numb){
			pageNumber = numb;
		}
	}
	
	public static void main(String args[]) {
		Scanner cin = new Scanner(System.in);
		System.out.print("Enter number of page frames: ");
		pageFrames = cin.nextInt();
		cin.close();
		clockAlgorithm();
		
		System.out.println("Number of page misses " + numPageMisses);
		System.out.println("Sequence of victim pages " + victimPages);
		System.out.println("Total number of page references " + totalPageReferences);
		System.out.println("Number of page hits " + numPageHits);

	}
}
	
