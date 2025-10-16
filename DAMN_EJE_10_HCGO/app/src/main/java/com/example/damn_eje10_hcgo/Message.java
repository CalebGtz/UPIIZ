package com.example.damn_eje10_hcgo;

public class Message {
    private final String text;
    private final long timestamp;
    private final boolean fromMe;

    public Message(String text, long timestamp, boolean fromMe) {
        this.text = text;
        this.timestamp = timestamp;
        this.fromMe = fromMe;
    }

    public String getText() { return text; }
    public long getTimestamp() { return timestamp; }
    public boolean isFromMe() { return fromMe; }
}