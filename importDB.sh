#!/bin/sh

SQL_USER="root"
SQL_PWD="root"
SQL_DB="card_games"
SQL_IMPORT="./GameLibrary/src/main/sql/create_games.sql"


SQL_CMD="mysql --user=$SQL_USER --password=$SQL_PWD $SQL_DB  < $SQL_IMPORT"
eval $SQL_CMD
