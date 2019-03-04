package cn.gl.share_knowledge.message;

public class Message {

    private boolean state;

    private String message;

    public Message(String message) {
        this.message = message;
    }

    public Message(boolean state, String message) {
        this.state = state;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
