package online.zongzi.parcel.enums;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description: 代取状态的状态码对应的中文
 **/
public enum ConsigneeState {
    /*
     * 是否代取
     * 0: 未代取
     * 1: 请求中
     * 2: 同意代取
     * 3: 已代取
     */
    NoApply(0, "未代取"),
    Processing(1, "请求中"),
    AgreeConsignee(2,"同意代取"),
    PickedUp(3,"已代取");

    private int state;
    private String stateInfo;

    ConsigneeState(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ConsigneeState stateOf(int state) {
        for (ConsigneeState consigneeState: values()) {
            if (consigneeState.getState() == state) {
                return consigneeState;
            }
        }
        return null;
    }
}
