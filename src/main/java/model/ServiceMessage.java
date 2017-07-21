package model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by wangw on 2017/7/21.
 */
@Getter
@Data
@NoArgsConstructor
@ToString
public class ServiceMessage {
    private String type;
    private String message;
    private String room_id;
    private String content;
}
