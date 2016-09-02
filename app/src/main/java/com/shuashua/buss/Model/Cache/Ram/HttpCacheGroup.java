package com.shuashua.buss.Model.Cache.Ram;

import net.gy.SwiftFrameWork.Service.cache.config.PoolType;
import net.gy.SwiftFrameWork.Service.cache.control.CachePoolGroup;

/**
 * Created by gy on 2016/9/3.
 */
public class HttpCacheGroup extends CachePoolGroup{
    public HttpCacheGroup() {
        init(PoolType.Sync);
    }
}
