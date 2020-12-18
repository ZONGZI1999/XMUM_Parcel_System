package online.zongzi.parcel.enums;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: 当前包裹状态的状态码对应
 **/
public enum CurrentState {
    /*
     * 当前状态
     * 0: 已入库
     * 1: 已取件
     * 2: 异常件
     */
    InProcess(0, "Arrived"),
    PickedUp(1,"Picked Up"),
    Abnormal(2,"Abnormal");


    private int state;
    private String stateInfo;

    CurrentState(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static CurrentState stateOf(int state){
        for(CurrentState stateInLoop: values()) {
            if (stateInLoop.getState() == state) {
                return stateInLoop;
            }
        }
        return null;
    }
}
