package cn.gl.share_knowledge.message;

public class EmailRegisterMessage extends Message {
    private boolean state;

    public EmailRegisterMessage(Boolean state, String message) {
        super(message);
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
