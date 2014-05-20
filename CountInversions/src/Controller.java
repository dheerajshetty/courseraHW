import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;


public class Controller  {
	
	public static void main(String[] args) throws Exception {
		File file = new File("IntegerArray.txt");
		int input[] = new int[100000];
		int sortedArray[] = new int[100000];
		
		Scanner scanner = new Scanner(file);
		int index = 0;
		long count;
		
		for (int i = 0; i < input.length; i++) {
			sortedArray[i] = input[i];
		}
		
		while ( scanner.hasNextLine() ) {
			input[index] = Integer.parseInt(scanner.nextLine());
			index++;
		}
		count = inversions(0, input.length-1, input,sortedArray);
		System.out.println(count);
		
		
	}
	
	static long inversions(int start, int end, int[] input, int[] sortedInput)
	{
		
		long count = 0;
		long countLeft = 0;
		long countRight = 0;
		long countSplit = 0;
		

	   if((end - start) == 0) {
			count = 0;
			sortedInput[start] = input[start];
			return count;
		}
					
		if((end - start) == 1) {
			if(input[start]> input[end]) {
				count = 1;
				sortedInput[start] = input[end];
				sortedInput[end] = input[start];
				return count;
			}
			else
			{
				count = 0;
				sortedInput[start] = input[start];
				sortedInput[end] = input[end];
				return count;
			}
		}
			
		if(end%2 == 0)
		{
			countLeft += inversions(start, (start+end)/2, input, sortedInput);
			countRight += inversions((start+end)/2+1, end, input, sortedInput);
		}
		else
		{
			countLeft += inversions(start, (start+end)/2, input, sortedInput);
			
			countRight += inversions((start+end)/2+1, end, input, sortedInput);
		}
		
		countSplit += mergeAndCount(start, end, sortedInput);
		
		
		count += countLeft + countRight + countSplit;
		
		return (count);
	}
	
	static int mergeAndCount(int start, int end, int[] sortedInput)
	{		
		int i = start;
		int j;
		j = (start+end)/2 + 1;
		
		int count = 0;
		int helper[] = new int[sortedInput.length];
		
		for(int x = 0; x< sortedInput.length; x++) {
			helper[x] = sortedInput[x];
		}
		
		for(int k = start; k <= end; k++)
		{
			if(i<= (start+end)/2 && j <= end)
			{
				if(helper[i] < helper[j])
				{
					sortedInput[k] = helper[i];
					i++;
				}
				else
				{
					count += (start+end)/2 + 1 - i;
					sortedInput[k] = helper[j];
					j++;
				}
			}
			else if (i<= (start+end)/2)
			{
				sortedInput[k] = helper[i];
				i++;
			}
			else if (j <= end)
			{
			  sortedInput[k] = helper[j];
			  j++;
			}
			
		}
		
		return count;
	}

}
