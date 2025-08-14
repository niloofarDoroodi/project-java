package homeservice.src.main.java.service.impl;


import homeservice.src.main.java.basics.baseService.impl.BaseServiceImpl;
import homeservice.src.main.java.entity.*;
import homeservice.src.main.java.entity.enums.TechnicianStatus;
import homeservice.src.main.java.exceptions.*;
import homeservice.src.main.java.repository.impl.TechnicianRepositoryImpl;
import homeservice.src.main.java.service.TechnicianService;
import homeservice.src.main.java.utility.ApplicationContext;
import homeservice.src.main.java.utility.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TechnicianServiceImpl extends BaseServiceImpl<TechnicianRepositoryImpl, Technician> implements TechnicianService {

    private final PersonServiceImple personService;
    private final SubAssistanceServiceImpl subAssistanceService;
    private final AssistanceServiceImpl assistanceService;
    private final OrderServiceImpl orderService;

    public TechnicianServiceImpl(TechnicianRepositoryImpl repository) {
        super(repository);
        personService = ApplicationContext.personService;
        subAssistanceService = ApplicationContext.subAssistanceService;
        assistanceService = ApplicationContext.assistanceService;
        orderService = ApplicationContext.orderService;
    }

    public Technician specifyTechnician(Path path){
        printer.getInput("first name");
        String firstname = input.nextLine();
        printer.getInput("last name");
        String lastname = input.nextLine();
        printer.getInput("email");
        String email = input.nextLine();
        printer.getInput("user name");
        String username = input.nextLine();
        printer.getInput("password");
        String password = input.nextLine();
        LocalDateTime registrationDate = LocalDateTime.now();
        try {
            byte[] image = Files.readAllBytes(path);
            return Technician.builder().firstName(firstname).lastName(lastname).email(email).username(username)
                    .password(password).registrationDate(registrationDate).score(0).credit(0).isActive(false)
                    .technicianStatus(TechnicianStatus.NEW).subAssistances(List.of())
                    .image(image).build();
        } catch (IOException e) {
            printer.printError(e.getMessage());
            return null;
        }
    }

    public boolean validateImage(Path path){
        try {
            String pathString = path.toString();
            if(!pathString.endsWith(".jpg"))
                throw new InvalidImageException(Constants.INVALID_IMAGE_FORMAT);
            byte[] image = Files.readAllBytes(path);
            if(image.length > 307200)
                throw new InvalidImageException(Constants.INVALID_IMAGE_SIZE);
            return true;
        } catch (IOException | InvalidImageException e) {
            printer.printError(e.getMessage());
            return false;
        }
    }

    public void saveImageToDirectory(Path path,byte[] image){
        try {
            Files.write(path,image);
        } catch (IOException e) {
            printer.printError(Constants.IMAGE_NOT_SAVED_TO_DIRECTORY);
        }
    }

    public void addTechnicianToSubAssistance(String managerName, String technicianName,
                                             String subassistanceTitle,String assistanceTitle){
        Person manager = personService.findByUsername(managerName);
        if(manager instanceof Manager){
            try{
                Person technician = personService.findByUsername(technicianName);
                Assistance assistance = assistanceService.findAssistance(assistanceTitle);

                if(assistance == null)
                    throw new NotFoundException(Constants.ASSISTANCE_NOT_FOUND);

                SubAssistance subAssistance = subAssistanceService.findSubAssistance(subassistanceTitle,assistance);

                if(!(technician instanceof Technician) || subAssistance == null)
                    throw new NotFoundException(Constants.TECHNICIAN_OR_SUBASSISTANCE_NOT_FOUND);

                if(!((Technician) technician).isActive() && ((Technician) technician).getTechnicianStatus()==TechnicianStatus.APPROVED)
                    throw new DeactivatedTechnicianException(Constants.DEACTIVATED_TECHNICIAN);

                List<Technician> technicians = subAssistance.getTechnicians();
                if(technicians.contains(technician))
                    throw new DuplicateTechnicianException(Constants.DUPLICATE_TECHNICIAN_SUBASSISTANCE);

                technicians.add((Technician) technician);
                ((Technician) technician).setTechnicianStatus(TechnicianStatus.APPROVED);
                ((Technician) technician).setActive(true);
                subAssistanceService.saveOrUpdate(subAssistance);

            } catch (NotFoundException | DeactivatedTechnicianException | DuplicateTechnicianException e) {
                printer.printError(e.getMessage());
            }
        }
        else
            printer.printError(("Only manager can add technicians to a sub-assistance"));
    }

    public void removeTechnicianFromSubAssistance(String managerName, String technicianName,
                                             String subassistanceTitle,String assistanceTitle){
        Person manager = personService.findByUsername(managerName);
        if(manager instanceof Manager){
            try{
                Person technician = personService.findByUsername(technicianName);
                Assistance assistance = assistanceService.findAssistance(assistanceTitle);

                if(assistance == null)
                    throw new NotFoundException(Constants.ASSISTANCE_NOT_FOUND);

                SubAssistance subAssistance = subAssistanceService.findSubAssistance(subassistanceTitle,assistance);

                if(!(technician instanceof Technician) || subAssistance == null)
                    throw new NotFoundException(Constants.TECHNICIAN_OR_SUBASSISTANCE_NOT_FOUND);

                List<Technician> technicians = subAssistance.getTechnicians();
                if(!technicians.contains(technician))
                    throw new NotFoundException(Constants.TECHNICIAN_NOT_IN_LIST);

                technicians.remove((Technician) technician);
                subAssistanceService.saveOrUpdate(subAssistance);

            } catch (NotFoundException e) {
                printer.printError(e.getMessage());
            }
        }
        else
            printer.printError(("Only manager can remove technicians from a sub-assistance"));
    }

    @Override
    public List<Technician> saveOrUpdate(List<Technician> technicians) {
        try{
            for(Technician t: technicians) {
                if (!isValid(t))
                    return null;
            }
            if(!transaction.isActive()){
                transaction.begin();
               technicians = repository.saveOrUpdate(technicians).orElse(null);
                transaction.commit();
            }
            else
                technicians = repository.saveOrUpdate(technicians).orElse(null);
            if(technicians != null)
                printer.printMessage("Technician list saved successfully!");
            return technicians;
        } catch (NotSavedException | RuntimeException e){
            if(transaction.isActive())
                transaction.rollback();
            printer.printError(e.getMessage());
            printer.printError(Arrays.toString(e.getStackTrace()));
            input.nextLine();
            return null;
        }
    }

    public List<String> showAllTechnicians(String managerUsername){
        Person person = personService.findByUsername(managerUsername);
        if(person instanceof Manager){
            return findAll().stream().map(Object::toString).toList();
        }
        else{
            printer.printError("Only manager can see the list of all technicians");
            return List.of();
        }
    }

    public List<String> seeUnapprovedTechnicians(String managerUsername){

        Person manager = personService.findByUsername(managerUsername);
        if(manager instanceof Manager){
            try{
                List<Technician> technicians = repository.findUnapproved().orElse(null);
                if(technicians == null || technicians.isEmpty())
                    throw new NotFoundException(Constants.NO_UNAPPROVED_TECHNICIANS);
                List<String> result = technicians.stream().map(Object::toString).toList();
                boolean isListChanged = false;
                for(Technician t : technicians){
                    if(t.getTechnicianStatus()==TechnicianStatus.NEW){
                        t.setTechnicianStatus(TechnicianStatus.PENDING);
                        isListChanged = true;
                    }
                }
                if(isListChanged)
                    saveOrUpdate(technicians);
                return result;
            } catch (NotFoundException e){
                printer.printError(e.getMessage());
                return null;
            }

        }
        else {
            printer.printError(("Only manager can see unapproved technicians"));
            return null;
        }
    }

    public List<String> seeDeactivatedTechnicians(String managerUsername){

        Person manager = personService.findByUsername(managerUsername);
        if(manager instanceof Manager){
            try{
                List<Technician> technicians = repository.findDeactivated().orElse(null);
                if(technicians == null || technicians.isEmpty())
                    throw new NotFoundException(Constants.NO_DEACTIVATED_TECHNICIANS);
                return technicians.stream().map(Object::toString).toList();
            } catch (NotFoundException e){
                printer.printError(e.getMessage());
                return null;
            }

        }
        else {
            printer.printError(("Only manager can see deactivated technicians"));
            return null;
        }
    }

    public List<String> findRelativeOrders(String technicianUsername){
        Person person = personService.findByUsername(technicianUsername);
        if(person instanceof Technician){
            try{
                if(!((Technician) person).isActive())
                    throw new DeactivatedTechnicianException(Constants.DEACTIVATED_TECHNICIAN);

               return orderService.findRelatedOrders((Technician) person).stream().map(Object::toString).toList();
            } catch (DeactivatedTechnicianException e) {
                printer.printError(e.getMessage());
                return List.of();
            }
        }
        else {
            printer.printError(("Only technicians can see their relative orders"));
            return List.of();
        }
    }

    public void sendTechnicianSuggestion (String technicianUsername, long orderId){
        Person person = personService.findByUsername(technicianUsername);
        if(person instanceof Technician){
            try{
                if(!((Technician) person).isActive())
                    throw new DeactivatedTechnicianException(Constants.DEACTIVATED_TECHNICIAN);

                Order order = orderService.findById(orderId);
                if(order == null)
                    throw new NotFoundException(Constants.NO_SUCH_ORDER);

                orderService.sendTechnicianSuggestion((Technician) person,order);

            } catch (DeactivatedTechnicianException | NotFoundException e) {
                printer.printError(e.getMessage());
            }
        }
        else {
            printer.printError(("Only technicians can send suggestions to an order"));
        }
    }
}
