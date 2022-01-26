package com.jameshackett.war2022.games;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jameshackett.core.DataSourceFactory;
import com.jameshackett.war2022.games.managers.GameManager;
import com.jameshackett.war2022.games.repositories.LeaderboardRepository;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.json.JSONObject;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


@SpringBootApplication
public class Application implements DisposableBean  {

    @Autowired
    @Qualifier("getWriteDatasource")
    protected DataSource writeDataSource;


    @Autowired
    @Qualifier("getConfig")
    protected JSONObject config;

    @Autowired
    private Environment env;

    private static final String  DEFAULT_CONFIG_LOCATION = "/usr/local/etc/games.json";


    @Bean
    @Autowired
    public GameManager getGameManager(LeaderboardRepository leaderboardRepository) {
        return new GameManager(leaderboardRepository);
    }

    @Bean
    public LeaderboardRepository getLeaderboardRepository(DataSource writeDataSource) {
        return new LeaderboardRepository(writeDataSource);
    }

    @Bean
    public DataSource getWriteDatasource(JSONObject config) {
        if (config == null  || !config.has("mysql")) {
            throw new RuntimeException("Configuration information missing for 'mysql'");
        }
        return DataSourceFactory.getDataSource(config.getJSONObject("mysql").getJSONObject("write"), "write");
    }

    @Bean
    public JSONObject getConfig() {
        // normally this would be stored in an external file that gets copied in during deployment
        return new JSONObject("{\"mysql\":{\"write\":{\"url\":\"jdbc:mysql://localhost:3306/card_games\",\"username\":\"root\",\"password\":\"root\"}}}");
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }


    @Override
    public void destroy() throws Exception {

    }
	
}
