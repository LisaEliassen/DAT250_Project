package no.hvl.dat250.jpa.assignmentB;

import java.util.ArrayList;
import java.util.List;

public class DeviceDAO {
    private List<Device> devices;

    public DeviceDAO() {
        devices = new ArrayList<>();
    }

    public List<Device> getAllDevices() {
        return this.devices;
    }

    public Device getDeviceByID(Long id) {
        for (Device device : getAllDevices()) {
            if (device.getDeviceID().equals(id)) {
                return device;
            }
        }
        return null;
    }

    public void save(Device device) {
        getAllDevices().add(device);
    }

    public void delete(Device device) {
        getAllDevices().remove(device);
    }

    public void update(Device device, String[] params) {
    }
}
