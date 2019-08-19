package cn.wangsr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author: wjl
 * @description:
 * @time: 2019/8/15 0015 10:42
 */
public class WaterResourceDriver {
    public static void main(String[] args) throws Exception {
        // 1 获取配置信息，或者job对象实例
        Configuration configuration = new Configuration();

        Job job = Job.getInstance(configuration);

        // 6 指定本程序的jar包所在的本地路径
        job.setJarByClass(WaterResourceDriver.class);

        // 2 指定本业务job要使用的mapper/Reducer业务类
        job.setMapperClass(WaterSourceMapper.class);
        job.setReducerClass(WaterSourceReducer.class);

        // 3 指定mapper输出数据的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(WaterSourceResultBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(WaterSourceResultBean.class);

        // 5 指定job的输入原始文件所在目录
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
