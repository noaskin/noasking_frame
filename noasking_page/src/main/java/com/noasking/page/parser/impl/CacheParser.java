package com.noasking.page.parser.impl;

import com.noasking.page.Page;
import com.noasking.page.SqlUtil;
import com.noasking.page.cache.Cache;
import com.noasking.page.cache.CacheFactory;
import com.noasking.page.parser.Parser;
import com.noasking.page.parser.SqlServer;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;

import java.util.List;
import java.util.Map;

/**
 * 缓存实现
 *
 * @author MaJing
 */
public class CacheParser implements Parser {

    private Parser delegate;

    private Cache<String, String> CACHE_COUNTSQL;
    private Cache<String, String> CACHE_PAGESQL;
    private boolean cachePageSql = false;

    public CacheParser(Parser delegate, String sqlCacheClass) {
        this.delegate = delegate;
        CACHE_COUNTSQL = CacheFactory.createSqlCache(sqlCacheClass);
        //一般情况下的pagesql都是简单的字符串拼接，不需要缓存，只有sqlserver方式由于处理SQL结构耗时，因此针对这种情况会缓存
        if(delegate instanceof SqlServerParser){
            CACHE_PAGESQL = CacheFactory.createSqlCache(sqlCacheClass);
            cachePageSql = true;
        }
    }

    @Override
    public boolean isSupportedMappedStatementCache() {
        return delegate.isSupportedMappedStatementCache();
    }

    @Override
    public String getCountSql(String sql) {
        //一般涉及复杂处理，缓存结果
        String countSql = CACHE_COUNTSQL.get(sql);
        if(countSql != null){
            return countSql;
        } else {
            countSql = delegate.getCountSql(sql);
            CACHE_COUNTSQL.put(sql, countSql);
        }
        return countSql;
    }

    @Override
    public String getPageSql(String sql) {
        if(cachePageSql){
            //SqlServerParser实现会缓存
            Page<?> page = SqlUtil.getLocalPage();
            String pageSql = CACHE_PAGESQL.get(sql);
            if(pageSql == null){
                //调用不替换参数的这个方法
                pageSql = ((SqlServerParser)delegate).getPageSqlWithOutPage(sql);
                CACHE_PAGESQL.put(sql, pageSql);
            }
            pageSql = pageSql.replace(SqlServer.START_ROW, String.valueOf(page.getStartRow()));
            pageSql = pageSql.replace(SqlServer.PAGE_SIZE, String.valueOf(page.getPageSize()));
            return pageSql;
        } else {
            //一般情况下，简单字符串拼接，不需要缓存
            return delegate.getPageSql(sql);
        }
    }

    @Override
    public List<ParameterMapping> getPageParameterMapping(Configuration configuration, BoundSql boundSql) {
        return delegate.getPageParameterMapping(configuration, boundSql);
    }

    @Override
    public Map<String, Object> setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page<?> page) {
        return delegate.setPageParameter(ms, parameterObject, boundSql, page);
    }
}
