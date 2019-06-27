package net.lzzy.algorithm.algorlib;

import java.lang.ref.SoftReference;
import java.util.Set;

/**
 * @author lzzy_gxy on 2019/6/27.
 * Description:
 */
public class BinarySearch<T extends  Comparable<? super T>> extends  BaseSearch<T>{
   BinarySearch(T[] items){
       super(items);
   }

    @Override
    public int search(T key) {
       long start=System.currentTimeMillis();
       int left=0;
       int right=items.length-1;
       while (left<=right){
           int mid=(left+right)/2;
           int comp=compare(key,items[mid]);
           if (comp==0){
               setDuration(System.currentTimeMillis()-start);
               return mid;
           }else if(comp>0){
               left=mid+1;
           }else {
               right=mid-1;
           }
       }
       setDuration(System.currentTimeMillis()-start);
       return-1;
    }
}
