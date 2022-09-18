package no.hvl.dat250.jpa.assignmentB;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceID;

    private String typeOfDevice;

    private String deviceCookie;

    @ManyToMany(mappedBy = "device")
    private Set<FeedAppUser> feedAppUsers = new HashSet<>();

    public Long getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(Long deviceID) {
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

    public Set<FeedAppUser> getUsers() {
        return feedAppUsers;
    }

    public void setUsers(FeedAppUser feedAppUser) {
        this.feedAppUsers.add(feedAppUser);
    }

}
