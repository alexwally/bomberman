package ru.atom.gameserver.connectionhandler;

import ru.atom.gameserver.gamesession.model.Player;

public class MessageWrapper {
    private final Player player;
    private final Message message;

    public MessageWrapper(Player player, Message message) {
        this.player = player;
        this.message = message;
    }

    public Player getPlayer() {
        return player;
    }

    public Message getMessage() {
        return message;
    }
}
