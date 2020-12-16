package online.zongzi.parcel.enums;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description : 走件流程的详细对应信息
 **/
public enum DetailState {
    Received(0,"Your parcel has been received by Warehouse!"),
    PickedUp(1,"Your parcel has been picked up by yourself"),
    Abnormal(2, "Your parcel is in abnormal statue"),
    ApplyingConsignee(3, "Your parcel is applying for pick up by consignee!"),
    AcceptConsignee(4,"Consignee accept your apply."),
    PickedUpByConsignee(5, "Your parcel has been picked up by consignee!");

    private int state;
    private String stateInfo;

    DetailState(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static DetailState stateOf(int index) {
        for (DetailState detailState: values()){
            if(detailState.state == index){
                return detailState;
            }
        }
        return null;
    }
}
