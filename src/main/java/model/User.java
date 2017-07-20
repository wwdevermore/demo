package model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Random;

/**
 * Created by wwd on 2017/7/20.
 */
@NoArgsConstructor
@ToString
public class User {
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private Date joinTime;
    public User(String username,String password,String nickname){
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.avatar="/image/avatar/avatar"+new Random().nextInt(10)+".jpg";
        this.joinTime=new Date();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public Date getJoinTime() {
        return this.joinTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$nickname = this.getNickname();
        final Object other$nickname = other.getNickname();
        if (this$nickname == null ? other$nickname != null : !this$nickname.equals(other$nickname)) return false;
        final Object this$avatar = this.getAvatar();
        final Object other$avatar = other.getAvatar();
        if (this$avatar == null ? other$avatar != null : !this$avatar.equals(other$avatar)) return false;
        final Object this$joinTime = this.getJoinTime();
        final Object other$joinTime = other.getJoinTime();
        if (this$joinTime == null ? other$joinTime != null : !this$joinTime.equals(other$joinTime)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $nickname = this.getNickname();
        result = result * PRIME + ($nickname == null ? 43 : $nickname.hashCode());
        final Object $avatar = this.getAvatar();
        result = result * PRIME + ($avatar == null ? 43 : $avatar.hashCode());
        final Object $joinTime = this.getJoinTime();
        result = result * PRIME + ($joinTime == null ? 43 : $joinTime.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof User;
    }
}