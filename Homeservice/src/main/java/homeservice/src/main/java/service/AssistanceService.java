package homeservice.src.main.java.service;


import homeservice.src.main.java.entity.Assistance;

public interface AssistanceService {
    Assistance findAssistance(String assistanceName);
}
