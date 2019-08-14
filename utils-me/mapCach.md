
###自定义缓存 用于临时储存大量数据


```java

/**
 * @author: wjl
 * @description:  自定义缓存 用于并发临时储存
 * @time: 2019/7/31 0031 10:30
 */
public class MapCache {

    private  static volatile Map map=new LinkedHashMap();
    private static volatile ReentrantReadWriteLock  lock=new ReentrantReadWriteLock();
    private  static Logger logger= LoggerFactory.getLogger(MapCache.class);

    /**
     * @Description   临时缓存写入
     * @author wjl
     * @date 2019/7/31 0031
     * @param [key, value]
     * @return void
     */
    public void put(String key,String value){
        lock.writeLock().lock();
        try{
            map.put(key,value);
            logger.info(Thread.currentThread().getName()+"缓存写入");
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * @Description  临时缓存读取
     * @author wjl
     * @date 2019/7/31 0031
     * @param [key]
     * @return java.lang.Object
     */
    public Object get(String key){
        lock.readLock().lock();
        Object obj=null;
        try{
            obj= map.get(key);
            logger.info(Thread.currentThread().getName()+"缓存读取");
        }catch (Exception e){
            e.getStackTrace();
        }finally {
        lock.readLock().unlock();
        }
        return obj;
    }

    /**
     * @Description  缓存清空
     * @author wjl
     * @date 2019/7/31 0031
     * @param []
     * @return void
     */
    public void clear(){
        logger.info("缓存清空");
        map.clear();
    }



    /**
     * @Description  用于返回当前有序map
     * @author wjl
     * @date 2019/8/1 0001
     * @param [map]
     * @return java.util.Map
     */
    public  Map getMap(){
        Map mapResult=null;
        mapResult=map;
        return mapResult;
    }


}

```