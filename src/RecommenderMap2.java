//Learning MapReduce

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RecommenderMap2
extends Mapper<LongWritable, Text, Text, Text> {
	
private Text word = new Text();

@Override
public void map(LongWritable Key, Text value, Context context)
throws IOException, InterruptedException{
	
	
	String line = value.toString();
	StringTokenizer itr = new StringTokenizer(line);
	boolean flag = false;
	String s1 = "";
	String s2 = "";
	  while(itr.hasMoreTokens()){
		  word.set(itr.nextToken());
		  if(flag == false){
			  s1 = word.toString();
			  flag = true;
		  }
		  else{
			  s2 = word.toString();
		  }
	  	}
	  String s3 = "";
	  s3 = s3 + s1.charAt(0);
	  
	  String s4 = "";
	  s4 = s4 + s2 + s1.charAt(1);
	  context.write(new Text(s3), new Text(s4));
	}
}