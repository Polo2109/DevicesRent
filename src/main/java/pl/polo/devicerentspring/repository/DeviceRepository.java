package pl.polo.devicerentspring.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polo.devicerentspring.model.Device;

public interface DeviceRepository extends CrudRepository<Device, Long> {
}
