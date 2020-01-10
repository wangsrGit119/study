 - 两个list  里面都是数字200万左右 无序  比较两个list中是否有相同值，并且求出
 - 原则：
   - 对A中的数组进行排序，采取同样的排序方法对B中的数组进行排序
   - 从A，B中各自取出a，b进行比较
   - 如果a>b,那么从B中取出下一个数据b进行比较
   - 如果a<b,那么从A中取出下一个数据a进行比较
   - 如果a=b,那么找到一个，继续
```
 public static List cmp(List<Integer> list01,List<Integer> list02){
        //开始计算
        List list =  new ArrayList();
        long startTime  = System.currentTimeMillis();
        list01.sort(Comparator.naturalOrder());
        list02.sort(Comparator.naturalOrder());
        int j=0,q = 0;
        out:for(;j < list01.size();j++){
            in:for(;q < list02.size();q++){
                if (list01.get(j) > list02.get(q)){
                    continue in;
                }else if(list01.get(j) < list02.get(q)){
                    continue out;
                }else {
                    q++;
                    list.add(list01.get(j));
                    continue out;
                }
            }
        }
        long entTime  = System.currentTimeMillis();
        System.out.println(list.size());
        System.out.println("time: "+(entTime-startTime)+"ms");
        return list;
    }
```