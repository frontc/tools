package cn.lefer.tools.Token;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/6/29
 * @Description : jwt token status
 */
public class LeferJwtStatus {
    boolean valid;
    String message;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LeferJwtStatus{" +
                "valid=" + valid +
                ", message='" + message + '\'' +
                '}';
    }
}
