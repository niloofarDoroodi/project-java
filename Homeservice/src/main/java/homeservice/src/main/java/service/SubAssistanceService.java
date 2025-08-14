package homeservice.src.main.java.service;


import homeservice.src.main.java.entity.Assistance;
import homeservice.src.main.java.entity.SubAssistance;

public interface SubAssistanceService {
    SubAssistance findSubAssistance(String title, Assistance assistance);
}
