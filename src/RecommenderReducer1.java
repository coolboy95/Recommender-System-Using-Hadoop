//Learning MapReduce

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class RecommenderReducer1 
extends Reducer<Text, IntWritable, Text, IntWritable> {
	
@Override
public void reduce(Text Key, Iterable<IntWritable>values, Context context)
throws IOException, InterruptedException {
	
	int sum=0;
	boolean flag=false;
	int get_value=0;
	
	for(IntWritable value:values)
	{
		get_value=value.get(); 
	    if(get_value==-1)
	    {
	    	flag=true;
	    	break;
	    }
	    sum+=get_value;
	}
	if(flag==false)
	{
		context.write(Key,new IntWritable(sum));
	}
  }
	
}