package cn.gl.share_knowledge.dto;

public class BooleanResult {

    private boolean success;

    public BooleanResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
