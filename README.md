# Welcome to the Game of War!

This app will run a game of war simulation between a player and the house. It will return the cards used and the winner. The player's wins will be saved to a leaderboard where you can fetch a list of the top winners.

# Setup

Run the `./importDB.sh` to setup the database

You can then start the app by .`/gradlew bootRun.` It will create a webserver on `http://localhost :8080`


# REST Requests

**Play a Game of War**
This will return you the player cards and winner of the game.

    curl -d '{"playerName":"Jim"}' -H 'Content-Type: application/json' http://localhost:8080/game/war2022/play

**Get Top Scores**
This will get you the top 100 scores for the game of war

    curl http://localhost:8080/game/leaderboard/war2022


