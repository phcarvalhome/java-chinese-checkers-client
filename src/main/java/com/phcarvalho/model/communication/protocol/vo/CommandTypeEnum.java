package com.phcarvalho.model.communication.protocol.vo;

public enum CommandTypeEnum {

    ADD_GAME(CommandTemplateEnum.MAIN, "addGame"),
    ADD_PLAYER(CommandTemplateEnum.MAIN, "addPlayer"),
    FLAG_AS_READY(CommandTemplateEnum.MAIN, "flagAsReady"),
    NOTIFY_VICTORY(CommandTemplateEnum.BOARD, "notifyVictory"),
    NOTIFY_WITHDRAWAL(CommandTemplateEnum.BOARD, "notifyWithdrawal"),
    MOVE_PIECE(CommandTemplateEnum.BOARD, "movePiece"),
    SEND_MESSAGE(CommandTemplateEnum.CHAT, "sendMessage"),
    CONNECT(CommandTemplateEnum.CONNECTION, "connect"),
    DISCONNECT(CommandTemplateEnum.CONNECTION, "disconnect");

    private CommandTemplateEnum template;
    private String value;

    CommandTypeEnum(CommandTemplateEnum template, String value) {
        this.template = template;
        this.value = value;
    }

    public CommandTemplateEnum getTemplate() {
        return template;
    }

    public String getValue() {
        return value;
    }
}
