package by.softarex.collectdata.dto;

import java.io.Serializable;

public class PasswordDTO implements Serializable {

    private String password;
    private String newPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public PasswordDTO() {
    }

    public PasswordDTO(String password, String newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }
}
