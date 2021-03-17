package pl.polo.devicerentspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.polo.devicerentspring.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findFirstByNameContainingIgnoreCase(String name);
}
