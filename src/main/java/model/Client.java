package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by wangw on 2017/7/21.
 */
@Data
@NoArgsConstructor
@ToString
public class Client {
    private String UserId;//user_id;
    private String Name;//name;
    private String PhoneNumber;//phoneNumber;
    private String DeviceType;//deviceType;
    private String LicesePlate;//license_plate;
    private String StoreName;//storeName;
}
