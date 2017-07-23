package Service;

import model.Agent;
import model.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangw on 2017/7/20.
 */
@Service
public class AgentService {

    private final ConcurrentHashMap<String, Agent> agents;

    public AgentService() {
        agents = new ConcurrentHashMap<>();
    }

    public boolean addUser(Agent agent) {
        boolean isExist = agents.containsKey(agent.getServiceName());
        if (isExist) {
            return false;
        }
        agents.put(agent.getServiceName(), agent);
        return true;
    }

    public Agent getByUsername(String agentName) {
        return agents.get(agentName);
    }

}
