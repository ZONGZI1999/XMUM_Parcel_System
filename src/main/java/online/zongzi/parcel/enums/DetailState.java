package online.zongzi.parcel.enums;

/**
 * @Author: zongzi
 * @Date: 2020/12/9
 * @Description : 走件流程的详细对应信息
 **/
public enum DetailState {
    Reject(-1, "Your pickup request has been rejected."),
    Cancel(-2,"You have canceled your pickup request."),
    Received(0,"Your parcel has arrived at the parcel room."),
    PickedUp(1,"You have picked up your parcel."),
    Abnormal(2, "Your parcel is in an abnormal state, please go to the parcel room for more details."),
    ApplyingConsignee(3, "Your request for pickup by a consignee is pending."),
    AcceptConsignee(4,"Consignee accepted your pickup request."),
    PickedUpByConsignee(5, "Consignee has picked up your parcel.");

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
