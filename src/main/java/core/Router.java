package core;

import model.Agent;
import model.Room;
import org.codehaus.groovy.runtime.metaclass.ConcurrentReaderHashMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangw on 2017/7/21.
 */
public class Router {
    private static Map<Integer, Agent> onlineAgentsMap =new ConcurrentReaderHashMap();
    private static Map<String, Room> roomMap=new ConcurrentReaderHashMap();
    private static List<Integer> idleAgentsList =new ArrayList<>();
    private static LinkedList<String> queueRoomsIdList =new LinkedList<>();
}
