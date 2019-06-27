package net.lzzy.algorithm.algorlib;

import android.widget.Space;

import java.util.Objects;

/**
 * @author lzzy_gxy on 2019/6/22.
 * Description:
 */
public class DirectSearch <T extends Comparable<? super T>> extends BaseSearch<T>{

    DirectSearch(T[] items) {
        super(items);
    }

    @Override
    int search(T key) {
        long start=System.currentTimeMillis();
        int pos=0;
        for (T items:items){
            if (equals(items,key)){
                getDuration(System.currentTimeMillis()-start);
                return pos;
            }
            pos++;
        }
        getDuration(System.currentTimeMillis()-start);
        return -1;
    }
}
