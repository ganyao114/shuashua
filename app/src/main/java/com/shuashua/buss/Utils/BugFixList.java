package com.shuashua.buss.Utils;

import java.util.ArrayList;

/**
 * Created by pc on 16/8/13.
 */
public class BugFixList<T> extends ArrayList<T>{
    @Override
    public T get(int index) {
        return super.get(size()-index-1);
    }
}
