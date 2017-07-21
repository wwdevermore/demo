package model;

import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangw on 2017/7/21.
 */
public class OnlineService {
    private int serviceId;
    private String serviceName;
    private WebSocketSession wsSession;
    private boolean isIdle;
    private List<String> roomList = new ArrayList<>();
    private Date recentActiveTime = new Date();
}
