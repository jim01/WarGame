package com.jameshackett.core;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.json.JSONObject;

public class DataSourceFactory {

    public static DataSource getDataSource(JSONObject config, String name) throws RuntimeException{
        PoolProperties p = new PoolProperties();
        p.setName(name);
        p.setUrl(config.getString("url"));
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername(config.getString("username"));
        p.setPassword(config.getString("password"));

        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);

        return datasource;
    }
}
