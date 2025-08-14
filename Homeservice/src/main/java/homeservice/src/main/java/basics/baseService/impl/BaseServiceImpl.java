package homeservice.src.main.java.basics.baseService.impl;



import homeservice.src.main.java.basics.baseRepository.impl.BaseRepositoryImpl;
import homeservice.src.main.java.basics.baseService.BaseService;
import homeservice.src.main.java.connection.Connection;
import homeservice.src.main.java.entity.base.BaseEntity;
import homeservice.src.main.java.exceptions.NotFoundException;
import homeservice.src.main.java.exceptions.NotSavedException;
import homeservice.src.main.java.utility.ApplicationContext;
import homeservice.src.main.java.utility.Printer;
import homeservice.src.main.java.validation.EntityValidator;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

public class BaseServiceImpl<R extends BaseRepositoryImpl<T>, T extends BaseEntity> implements BaseService<T> {
    protected R repository;
    protected EntityTransaction transaction;
    protected Validator validator;
    protected Printer printer;
    protected Scanner input;

    public BaseServiceImpl(R repository){
        this.repository = repository;
        transaction = Connection.entityManager.getTransaction();
        validator = EntityValidator.validator;
        printer = ApplicationContext.printer;
        input = ApplicationContext.input;
    }

    @Override
    public T saveOrUpdate(T t) {
        try{
            if(!isValid(t))
                return null;
            if(!transaction.isActive()){
                transaction.begin();
                t = repository.saveOrUpdate(t).orElseThrow(()-> new NotSavedException("\nCould not save " + repository.getClassName().getSimpleName()));
                transaction.commit();
            }
            else
                t = repository.saveOrUpdate(t).orElseThrow(()-> new NotSavedException("\nCould not save " + repository.getClassName().getSimpleName()));
            if(t != null)
                printer.printMessage(repository.getClassName().getSimpleName()  + " saved successfully!");
            return t;
        } catch (NotSavedException | RuntimeException e){
            if(transaction.isActive())
                transaction.rollback();
            printer.printError(e.getMessage());
            printer.printError(Arrays.toString(e.getStackTrace()));
            input.nextLine();
            return null;
        }
    }

    @Override
    public void delete(T t) {
        try{
            if(!isValid(t))
                return;
            if(!transaction.isActive()){
                transaction.begin();
                repository.delete(t);
                transaction.commit();
            }
            else
                repository.delete(t);
        } catch (RuntimeException e){
            if(transaction.isActive())
                transaction.rollback();
            if(e instanceof PersistenceException)
                printer.printError("Could not delete " + repository.getClassName().getSimpleName());
            else
                printer.printError("Could not complete deletion. Specified " + repository.getClassName().getSimpleName() + " not found!");
            printer.printError(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public T findById(long id) {
        try{
            return repository.findById(id).orElseThrow(()-> new NotFoundException("\nCould not find " + repository.getClassName().getSimpleName()
                    + " with id = " + id));
        } catch (RuntimeException | NotFoundException e){
            printer.printError(e.getMessage());
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        try{
            return repository.findAll().orElseThrow(()-> new NotFoundException("\nThere is no " + repository.getClassName().getSimpleName()
                    + " registered!"));
        }catch (RuntimeException | NotFoundException e){
            printer.printError(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isValid(T t) {
        Validator validator = EntityValidator.validator;
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if(!violations.isEmpty()){
            for(ConstraintViolation<T> c : violations)
                printer.printError(c.getMessage());
            return false;
        }
        return true;
    }
}
