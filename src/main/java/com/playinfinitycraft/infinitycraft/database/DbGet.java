package com.playinfinitycraft.infinitycraft.database;

import java.util.List;
import java.util.UUID;

public interface DbGet {

//TODO finish adding all of the get update delete etc methods to be implemented

    public int playerIdfromUuid(UUID uuid);
    public UUID playerUuidFromId(int id);
    public String playerFactionRankFromId(int id);
    public String playerFactionRankFromUuid(UUID uuid);
    public int factionIdFromName(String name);
    public String factionNameFromId(int id);
    public UUID factionOwnerFromPlayerId(int id);
    public String factionOwnerFromFactionName(String fname);
    public String factionTagFromId(int id);
    public String factionTagFromName(String fname);
    public String factionTagFromOwnerUuid(UUID uuid);
    public int factionMemberCountfromId(int id);
    public int factionMemberCountfromName(String name);
    public List<UUID> getAllPlayers();

}
