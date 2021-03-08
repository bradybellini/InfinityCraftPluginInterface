package com.playinfinitycraft.infinitycraft.database.utils;

import com.playinfinitycraft.infinitycraft.database.Postgres;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public interface DbInsert {

    void player(Postgres psql, Player player) throws SQLException;


}
