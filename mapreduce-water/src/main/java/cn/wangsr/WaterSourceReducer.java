package cn.wangsr;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Date;

/**
 * @author: wjl
 * @description:
 * @time: 2019/8/15 0015 15:30
 */
public class WaterSourceReducer extends Reducer< Text, WaterSourceResultBean,  Text, WaterSourceResultBean> {


    @Override
    protected void reduce(Text key, Iterable<WaterSourceResultBean> values, Context context) throws IOException, InterruptedException {

          Long sum=0L;//监测站条数计数器
          Double sum_mapExcessiveWater=0.0;
          Double sum_mapWaterTemperature=0.0;
          Double sum_mapPh=0.0;
          Double sum_mapOxygen=0.0;
          StringBuffer stringBuffer=new StringBuffer();



        for(WaterSourceResultBean resultBean : values){
            sum +=resultBean.getCurrentDetectionNum();  //条数自加加
            sum_mapExcessiveWater +=Double.parseDouble(resultBean.getMapExcessiveWater().replaceAll("[^0-9.]+", ""));
            sum_mapWaterTemperature +=Double.parseDouble(resultBean.getMapWaterTemperature().replaceAll("[^0-9.]+", ""));
            sum_mapPh +=Double.parseDouble(resultBean.getMapPh().replaceAll("[^0-9.]+", ""));
            sum_mapOxygen +=Double.parseDouble(resultBean.getMapOxygen().replaceAll("[^0-9.]+", ""));

        }
         stringBuffer.append(new Date());

         String detectionTime="检测日期=>"+stringBuffer;
         String excessiveWater="超标水量=>"+sum_mapExcessiveWater;
         String waterTemperature="水温=>"+sum_mapWaterTemperature;
         String ph="PH=>"+sum_mapPh;
         String oxygen="溶解氧=>"+sum_mapOxygen;
        WaterSourceResultBean waterSourceReducer=new WaterSourceResultBean(sum,excessiveWater,waterTemperature,ph,oxygen,detectionTime);

        context.write(key, waterSourceReducer);
    }
}
