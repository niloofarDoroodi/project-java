package homeservice.src.main.java;


import homeservice.src.main.java.connection.Connection;
import homeservice.src.main.java.service.impl.*;
import homeservice.src.main.java.utility.ApplicationContext;
import homeservice.src.main.java.utility.Printer;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class Main {
    public static EntityManager entityManager = Connection.entityManager;
    public static Scanner input = ApplicationContext.input;
    public static Printer printer = ApplicationContext.printer;
    public static AssistanceServiceImpl assistanceService = ApplicationContext.assistanceService;
    public static CustomerServiceImpl customerService = ApplicationContext.customerService;
    public static ManagerServiceImpl managerService = ApplicationContext.managerService;
    public static OrderServiceImpl orderService = ApplicationContext.orderService;
    public static PersonServiceImple personService = ApplicationContext.personService;
    public static SubAssistanceServiceImpl subAssistanceService = ApplicationContext.subAssistanceService;
    public static TechnicianServiceImpl technicianService = ApplicationContext.technicianService;
    public static TechnicianSuggestionServiceImpl technicianSuggestionService = ApplicationContext.technicianSuggestionService;

    public static void main(String[] args) {

////////////////////////////////////////////////////////////GENERAL:
        //1) register
        personService.register();

        //2) login
       personService.login("ali","ali1234");

        //3) change password
        personService.changePassword("ali1234","ali1234","aliali12");

        //4) Everyone can see the list of assistance(Technicians must be active)
        printer.printListWithoutSelect(assistanceService.seeAssistances("ali"));

////////////////////////////////////////////////////////////MANAGER:

        //5) Manager can add assistance category
//        assistanceService.addAssistance("ali","hygiene");

        //6) Manager can add sub-assistance title
//        subAssistanceService.addSubAssistance("ali","hygiene","spraying");

        //7) Manager add a technician to a subAssistance
//        technicianService.addTechnicianToSubAssistance("ali","ahmad1425","spraying","cleaning and hygiene");

        //8) Manager remove a technician from a subAssistance
//        technicianService.removeTechnicianFromSubAssistance("ali","reza","spraying","cleaning and hygiene");

        //9) Manager can see the list of sub-assistance and their technicians and other details
//        printer.printListWithoutSelect(subAssistanceService.showSubAssistances("ali"));

        //10) Manager can change the description of a sub-assistance
//        subAssistanceService.changeDescription("ali","hygiene","spraying","description changed by manager");

        //11) Manager can change the base price of a sub-assistance
//        subAssistanceService.changeBasePrice("ali","Home Appliances","audiovisual equipment",4000000);

        //12) Manager can see the unapproved technicians and they'll become "PEDNING"
//        List<String> technicians = technicianService.seeUnapprovedTechnicians("ali");
//        if(technicians != null){
//            for(String s: technicians)
//                System.out.println(s);
//        }

        //13) Manager can see the list of technicians who are approved but deactivated
//        List<String> technicians = technicianService.seeDeactivatedTechnicians("ali");
//        if(technicians != null){
//            for(String s: technicians)
//                System.out.println(s);
//        }

        //??) Manager can see the list of all technicians and statistics
//        printer.printListWithoutSelect(technicianService.showAllTechnicians("ali"));

        //??) Manager can see the list of all customers and statistics
//        printer.printListWithoutSelect(customerService.showAllCustomers("ali"));

        //??) Manager can see the list of all orders and statistics
//        printer.printListWithoutSelect(orderService.showAllOrders("ali"));

        //??) Manager can see the list of all technician suggestions
//        printer.printListWithoutSelect(technicianSuggestionService.showAllSuggestions("ali"));


////////////////////////////////////////////////////////////CUSTOMER:

        //14) Customer can see the list of sub-assistances
//        printer.printResult("All services",subAssistanceService.showSubAssistances("alireza"));

        //15) Customer makes an order
        orderService.makeOrder("alireza","cleaning and hygiene","spraying");

        //??) Customer can see the list of his orders and their status
//        printer.printListWithoutSelect(customerService.seeOrdersOf("alireza"));

        //18) Customer can see the list of technician suggestions of his order (order --> "CHOOSING_TECHNICIAN")
//        printer.printListWithoutSelect(customerService.seeTechnicianSuggestions("alireza",2));

        //19) Customer can choose a technicianSuggestion (in this project phase order --> "STARTED")
//        customerService.chooseSuggestion("alireza",2,1);

        //20) Customer can pay the technician (order --> "FINISHED")
//        customerService.payThePrice("alireza",2);

        //21) Customer can score the technician and write opinion
//        customerService.scoreTheTechnician("alireza",2);


////////////////////////////////////////////////////////////TECHNICIAN:

        //16) Technician can see list of related orders which are in waiting condition (if profile is active)
//        printer.printListWithoutSelect(technicianService.findRelativeOrders("akbar"));

        //17) Technician can make a suggestion to an order related to him (if profile is active)
//        technicianService.sendTechnicianSuggestion("akbar",2);

    }


}
