import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class RecommenderReducer2 
extends Reducer<Text, Text , Text, Text> {
	
@Override
public void reduce(Text Key, Iterable<Text>values, Context context)
throws IOException, InterruptedException {
	
	String arr[] = new String[50];
	String arr1[] = new String[50];
	String arr2[] = new String[50];
	int arr3[] = new int[50];
	int size = 0;int max;
	for(Text value:values)
	{
		
		String h1 = value.toString();
		arr[size++] = h1;
	}
	
	for(int i=0;i<size;++i)
	{
		arr1[i] = arr2[i] = "";
		arr1[i] = arr[i].substring(0, arr[i].length()-1);
		arr2[i] = arr[i].substring(arr[i].length()-1, arr[i].length());
	}
	
	for(int i=0;i<size;++i){
		arr3[i] = Integer.parseInt(arr1[i]);
	}
	// applying bubble sort 
	for(int i=0;i<size-1;++i){
		max = arr3[i];
		for(int j=i+1;j<size;++j){
			if(arr3[j]>max){
				int temp = arr3[i];
				arr3[i] = arr3[j];
				arr3[j] = temp;
				
				String temp1 = arr2[i];
				arr2[i] = arr2[j];
				arr2[j] = temp1;
			}
		}
	}
	String out = " ";
	for(int i=0;i<Math.min(10, size);++i){
		
		out = out + arr2[i] + "\t";
	}
	String out1 = "-> Your recommended friend list is(from higher to lower priority) is: " + out ;
	context.write(Key, new Text(out1));
		}
	
}