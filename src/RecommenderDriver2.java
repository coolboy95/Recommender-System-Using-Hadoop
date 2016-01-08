import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;


public class RecommenderDriver2 extends Configured implements Tool{

@Override
	public int run(String[] args) throws Exception{
	
	Configuration conf = getConf();
	
	Job job = new Job(conf, "My Job");
	job.setJarByClass(RecommenderDriver2.class);
	job.setJobName("Recommender System");

	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));

	job.setMapperClass(RecommenderMap2.class);
	job.setReducerClass(RecommenderReducer2.class);

	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(Text.class);

	return job.waitForCompletion(true)?0:1 ; 
}	


public static void main(String[] args) throws Exception {

	 int exitCode=ToolRunner.run(new Configuration(), new RecommenderDriver2(), args);
			  System.exit(exitCode);

  }
}