package com.jameshackett.war2022.games.controllers;

import com.jameshackett.war2022.core.PayloadResponse;
import com.jameshackett.war2022.core.protocol.games.GameRequest;
import com.jameshackett.war2022.games.managers.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameManager gameManager;

    @RequestMapping(value = {"/war2022/play", "/war2022/play/"}, method = RequestMethod.POST, consumes = "application/json")
    public PayloadResponse playWar2022(@RequestBody GameRequest gameRequest) {
        return new PayloadResponse(gameManager.playWar2022(gameRequest));
    }

    @RequestMapping(value = {"/leaderboard/{game}", "/leaderboard/{game}"}, method = RequestMethod.GET)
    public PayloadResponse getGame(@PathVariable String game) {
        return new PayloadResponse(gameManager.getLeaderboard(game));
    }


}
