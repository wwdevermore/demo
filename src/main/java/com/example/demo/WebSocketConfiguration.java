package com.example.demo;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

/**
 * Created by wwd on 2017/7/20.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {
    //继承的抽象类中主要消息传输接口和消息代理接口
    final static Logger log=Logger.getLogger(WebSocketConfiguration.class);
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/topic");//前缀指向代理
        registry.setApplicationDestinationPrefixes("/app");//前缀指向应用
    }
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/Messages").withSockJS();//启用sockJs
    }
    @Override
    public void configureWebSocketTransport(final WebSocketTransportRegistration registration) {
        registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
            //添加装饰工厂，用于装饰WebSocketHandler，从而处理WebSocket消息
            @Override
            public WebSocketHandler decorate(final WebSocketHandler handler) {
                return new WebSocketHandlerDecorator(handler) {
                    @Override
                    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                        // 客户端与服务器端建立连接后，此处记录谁上线了
                        String address=session.getLocalAddress().toString();
                        log.info("online: " + address);
                        super.afterConnectionEstablished(session);
                    }

                    @Override
                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                        // 客户端与服务器端断开连接后，此处记录谁下线了
                        String address=session.getLocalAddress().toString();
                        log.info("offline: " + address+" "+closeStatus.toString());
                        super.afterConnectionClosed(session, closeStatus);
                    }
                };
            }
        });
        super.configureWebSocketTransport(registration);
    }


}
