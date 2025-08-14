package homeservice.src.main.java.repository;


import homeservice.src.main.java.entity.Technician;
import homeservice.src.main.java.exceptions.NotSavedException;
import java.util.List;
import java.util.Optional;

public interface TechnicianRepository {
    Optional<List<Technician>> saveOrUpdate(List<Technician> technicians) throws NotSavedException;
    Optional<List<Technician>> findUnapproved();
    Optional<List<Technician>> findDeactivated();
}
