package com.noasking.page.parser.impl;

import com.noasking.page.Page;
import com.noasking.page.SqlUtil;
import com.noasking.page.parser.SqlServer;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author MaJing
 */
public class SqlServerParser extends AbstractParser {
    private static final SqlServer pageSql = new SqlServer();

    //with(nolock)
    protected static final String WITHNOLOCK = ", PAGEWITHNOLOCK";

    @Override
    public String getCountSql(String sql) {
        sql = sql.replaceAll("((?i)with\\s*\\(nolock\\))", WITHNOLOCK);
        sql = super.getCountSql(sql);
        sql = sql.replaceAll(WITHNOLOCK, " with(nolock)");
        return sql;
    }

    @Override
    public boolean isSupportedMappedStatementCache() {
        //由于sqlserver每次分页参数都是直接写入到sql语句中，因此不能缓存MS
        return false;
    }

    @Override
    public List<ParameterMapping> getPageParameterMapping(Configuration configuration, BoundSql boundSql) {
        return boundSql.getParameterMappings();
    }

    @Override
    public String getPageSql(String sql) {
        Page<?> page = SqlUtil.getLocalPage();
        sql = sql.replaceAll("((?i)with\\s*\\(nolock\\))", WITHNOLOCK);
        sql = pageSql.convertToPageSql(sql, page.getStartRow(), page.getPageSize());
        sql = sql.replaceAll(WITHNOLOCK, " with(nolock)");
        return sql;
    }

    /**
     * 该方法针对 CacheParser 提供，返回的 sql 不是最终执行的 sql，需要替换分页参数
     *
     * @param sql
     * @return
     */
    public String getPageSqlWithOutPage(String sql) {
        sql = sql.replaceAll("((?i)with\\s*\\(nolock\\))", WITHNOLOCK);
        sql = pageSql.convertToPageSql(sql);
        sql = sql.replaceAll(WITHNOLOCK, " with(nolock)");
        return sql;
    }

    @Override
    public Map<String, Object> setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page<?> page) {
        return super.setPageParameter(ms, parameterObject, boundSql, page);
    }
}