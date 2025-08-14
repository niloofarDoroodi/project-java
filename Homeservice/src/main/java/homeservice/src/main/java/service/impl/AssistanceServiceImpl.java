package homeservice.src.main.java.service.impl;

import homeservice.src.main.java.basics.baseService.impl.BaseServiceImpl;
import homeservice.src.main.java.entity.Assistance;
import homeservice.src.main.java.entity.Manager;
import homeservice.src.main.java.entity.Person;
import homeservice.src.main.java.entity.Technician;
import homeservice.src.main.java.exceptions.DeactivatedTechnicianException;
import homeservice.src.main.java.exceptions.DuplicateAssistanceException;
import homeservice.src.main.java.exceptions.NotFoundException;
import homeservice.src.main.java.repository.impl.AssistanceRepositoryImpl;
import homeservice.src.main.java.repository.impl.PersonRepositoryImpl;
import homeservice.src.main.java.service.AssistanceService;
import homeservice.src.main.java.utility.Constants;
import java.util.List;

public class AssistanceServiceImpl extends BaseServiceImpl<AssistanceRepositoryImpl, Assistance> implements AssistanceService {

    private PersonServiceImple personService;

    public AssistanceServiceImpl(AssistanceRepositoryImpl repository) {
        super(repository);
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl(Person.class);
        personService = new PersonServiceImple(personRepository);
//        personService = ApplicationContext.personService;
    }

    @Override
    public Assistance findAssistance(String assistanceName) {
        return repository.findAssistance(assistanceName).orElse(null);
    }

    public void addAssistance(String username, String assistanceName){
        Person person = personService.findByUsername(username);
        if(person instanceof Manager){
            try {
                if (findAssistance(assistanceName) != null)
                    throw new DuplicateAssistanceException(Constants.ASSISTANCE_ALREADY_EXISTS);
                Assistance assistance = Assistance.builder().title(assistanceName).build();
                saveOrUpdate(assistance);
            } catch (DuplicateAssistanceException e ){
                printer.printError(e.getMessage());
            }
        }
        else
            printer.printError("Only manager can add assistance categories");
    }

    public List<String> seeAssistances(String personUsername){
        Person person = personService.findByUsername(personUsername);
        try {
            if(person == null)
                throw new NotFoundException(Constants.INVALID_USERNAME);
            if(person instanceof Technician && !((Technician) person).isActive())
                throw new DeactivatedTechnicianException(Constants.DEACTIVATED_TECHNICIAN);
            return findAll().stream().map(Object::toString).toList();
        } catch (NotFoundException | DeactivatedTechnicianException e) {
            printer.printError(e.getMessage());
            return List.of();
        }
    }
}
