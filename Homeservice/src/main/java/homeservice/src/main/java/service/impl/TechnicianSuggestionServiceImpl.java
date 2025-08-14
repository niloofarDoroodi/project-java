package homeservice.src.main.java.service.impl;

import homeservice.src.main.java.basics.baseService.impl.BaseServiceImpl;
import homeservice.src.main.java.entity.Manager;
import homeservice.src.main.java.entity.Order;
import homeservice.src.main.java.entity.Person;
import homeservice.src.main.java.entity.TechnicianSuggestion;
import homeservice.src.main.java.entity.dto.TechnicianSuggestionDTO;
import homeservice.src.main.java.exceptions.NotFoundException;
import homeservice.src.main.java.repository.impl.TechnicianSuggestionRepositoryImpl;
import homeservice.src.main.java.service.TechnicianSuggestionService;
import homeservice.src.main.java.utility.ApplicationContext;
import homeservice.src.main.java.utility.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TechnicianSuggestionServiceImpl extends
        BaseServiceImpl<TechnicianSuggestionRepositoryImpl, TechnicianSuggestion> implements
        TechnicianSuggestionService {

    private PersonServiceImple personService;

    public TechnicianSuggestionServiceImpl(TechnicianSuggestionRepositoryImpl repository) {
        super(repository);
        personService = ApplicationContext.personService;
    }

    public List<String> showAllSuggestions(String managerUsername){
        Person person = personService.findByUsername(managerUsername);
        if(person instanceof Manager){
            return findAll().stream().map(Object::toString).toList();
        }
        else{
            printer.printError("Only manager can see the list of all technician suggestion");
            return List.of();
        }
    }

    @Override
    public List<TechnicianSuggestionDTO> getSuggestionsOf(Order order) {
        try{
            List<TechnicianSuggestion> suggestions = repository.findTechnitionSugestions(order).orElseThrow(
                    () -> new NotFoundException(Constants.NO_TECHNICIAN_SUGGESTION_FOUND)
            );
            List<TechnicianSuggestionDTO> result = new ArrayList<>();
            for(TechnicianSuggestion t : suggestions){
                TechnicianSuggestionDTO suggestionDTO = TechnicianSuggestionDTO.builder()
                        .suggestionId(t.getId())
                        .suggestionRegistrationDate(LocalDateTime.now())
                        .technicianFirstname(t.getTechnician().getFirstName())
                        .technicianLastname(t.getTechnician().getLastName())
                        .technicianId(t.getTechnician().getId())
                        .technicianScore(t.getTechnician().getScore())
                        .numberOfFinishedTasks(t.getTechnician().getNumberOfFinishedTasks())
                        .suggestedPrice(t.getTechSuggestedPrice())
                        .suggestedDate(t.getTechSuggestedDate())
                        .taskEstimatedDuration(t.getTaskEstimatedDuration()).build();
                result.add(suggestionDTO);
            }
            return result;
        } catch (NotFoundException e) {
            printer.printError(e.getMessage());
            return null;
        }


    }
}
