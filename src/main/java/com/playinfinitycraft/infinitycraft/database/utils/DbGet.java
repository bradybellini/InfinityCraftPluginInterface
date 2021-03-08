package com.playinfinitycraft.infinitycraft.database.utils;

import java.util.List;
import java.util.UUID;

public interface DbGet {

//TODO finish adding all of the get update delete etc methods to be implemented

    int playerIdfromUuid(UUID uuid);
    UUID playerUuidFromId(int id);
    String playerFactionRankFromId(int id);
    String playerFactionRankFromUuid(UUID uuid);
    int factionIdFromName(String name);
    String factionNameFromId(int id);
    UUID factionOwnerFromPlayerId(int id);
    String factionOwnerFromFactionName(String fname);
    String factionTagFromId(int id);
    String factionTagFromName(String fname);
    String factionTagFromOwnerUuid(UUID uuid);
    int factionMemberCountfromId(int id);
    int factionMemberCountfromName(String name);
    List<String> allFactionsRecruiting();
    List<UUID> allFactions();
    List<UUID> allPlayers();

}
