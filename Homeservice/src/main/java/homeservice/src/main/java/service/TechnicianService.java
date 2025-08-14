package homeservice.src.main.java.service;


import homeservice.src.main.java.entity.Technician;
import java.util.List;

public interface TechnicianService {
    List<Technician> saveOrUpdate(List<Technician> technicians);
}
