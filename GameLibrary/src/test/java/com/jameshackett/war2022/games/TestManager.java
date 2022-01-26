package com.jameshackett.war2022.games;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jameshackett.core.DataSourceFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class TestManager {

    public JSONObject config;
    public DataSource dataSource;


    public TestManager() {
        try {
            initConfig();
            initDB();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void initConfig() throws IOException, JSONException {
        this.config =  new JSONObject("{\"mysql\":{\"write\":{\"url\":\"jdbc:mysql://localhost:3306/card_games\",\"username\":\"root\",\"password\":\"root\"}}}");
    }

    public void initDB() throws JSONException {
        dataSource = DataSourceFactory.getDataSource(config.getJSONObject("mysql").getJSONObject("write"), "write");
    }



}
