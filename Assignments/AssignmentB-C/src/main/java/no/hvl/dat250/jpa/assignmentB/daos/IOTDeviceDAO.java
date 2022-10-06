package no.hvl.dat250.jpa.assignmentB.daos;

import no.hvl.dat250.jpa.assignmentB.feedapp.IOTDevice;

import java.util.ArrayList;
import java.util.List;

public class IOTDeviceDAO {
    private List<IOTDevice> IOTDevices;

    public IOTDeviceDAO() {
        IOTDevices = new ArrayList<>();
    }

    public List<IOTDevice> getAllDevices() {
        return this.IOTDevices;
    }

    public IOTDevice getDeviceByID(Long id) {
        for (IOTDevice IOTDevice : getAllDevices()) {
            if (IOTDevice.getDeviceID().equals(id)) {
                return IOTDevice;
            }
        }
        return null;
    }

    public void save(IOTDevice IOTDevice) {
        getAllDevices().add(IOTDevice);
    }

    public void delete(IOTDevice IOTDevice) {
        getAllDevices().remove(IOTDevice);
    }

    public void update(IOTDevice IOTDevice, String[] params) {
    }
}
