package Sercurity;

import Service.AgentService;
import model.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wwd on 2017/7/23.
 */
//public class AllAgentDetailService implements UserDetailsService{
//    private final static String ROLE_TAG = "ROLE_USER";
//
//    @Autowired
//    private AgentService agentService;
//
//    @Override
//    public UserDetails loadUserByUsername(String agentName)throws UsernameNotFoundException{
//        Agent agent = agentService.getByUsername(agentName);
//        if (agent == null) {
//            throw new UsernameNotFoundException("用户(客服)不存在");
//        }
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(ROLE_TAG));
//        // 用户认证（用户名，密码，权限）
//
//    }
//}
