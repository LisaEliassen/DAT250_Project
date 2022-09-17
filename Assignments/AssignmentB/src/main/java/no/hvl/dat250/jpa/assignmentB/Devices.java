package no.hvl.dat250.jpa.assignmentB;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Devices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String deviceID;

    private String typeOfDevice;

    private String deviceCookie;

    @ManyToMany(mappedBy = "devices")
    private Set<User> users = new HashSet<>();

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getTypeOfDevice() {
        return typeOfDevice;
    }

    public void setTypeOfDevice(String typeOfDevice) {
        this.typeOfDevice = typeOfDevice;
    }

    public String getDeviceCookie() {
        return deviceCookie;
    }

    public void setDeviceCookie(String deviceCookie) {
        this.deviceCookie = deviceCookie;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(User user) {
        this.users.add(user);
    }

}
