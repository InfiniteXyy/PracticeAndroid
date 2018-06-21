package cn.infinitex.xyy.androidpractice.practice.httpService;

public class Response {
    private int returnCode;
    private String returnValue;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public String toString() {
        return "Response{" +
                "returnCode=" + returnCode +
                ", returnValue='" + returnValue + '\'' +
                '}';
    }
}
