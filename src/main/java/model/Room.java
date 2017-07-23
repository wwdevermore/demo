package model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by wangw on 2017/7/21.
 */
@Data
public class Room {
    private String RoomId;
    private String Client;
    private String ClientPhone;
    private String OrderNumber;
    private String Service;
    private List ClientMsgQueue;
    private List ServiceMsgQueue;
    private Date CreateTime;
    private Date RecentRequestTime;
}
