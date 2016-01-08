import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RecommenderMap1
extends Mapper<LongWritable, Text, Text, IntWritable> {
	
private final static IntWritable one = new IntWritable(1);
private Text word = new Text();
private final static IntWritable minusone = new IntWritable(-1);

@Override
public void map(LongWritable Key, Text value, Context context)
throws IOException, InterruptedException{
	
	String line = value.toString();
	StringTokenizer itr = new StringTokenizer(line);
	String array[]=new String[50];
	 int size=0;
	  while(itr.hasMoreTokens()){
		  word.set(itr.nextToken());
		     String next=word.toString();
		    array[size++]=next;
		  }
	  
	  for(int i=1; i<size; ++i)
	  {
		  
		  String source = array[0];
		  String out = " ";
		  out = source + array[i];
		  Text out1 = new Text(out);
		  
		  context.write(out1,minusone);
		  
		  String friend = array[i];
		  out = " ";
		  out = friend + array[0];
		  out1 = new Text(out);
		  context.write(out1,minusone);
	  }
	  
	  for(int i=1;i<size;++i){
		  for(int j=i+1;j<size;++j){
			  
			  String friend1 = array[i];
			  String friend2 = array[j];
			  String friend = " ";
			  friend = friend1 + friend2;
			  Text out1 = new Text(friend);
			  
			  context.write(out1, one);
			  
			  String fr = " ";
			  fr = friend2 + friend1;
			  Text out = new Text(fr);
			  context.write(out, one);
		  }
	  }
	}
}