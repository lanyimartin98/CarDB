package dao;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import exceptions.AnotherCarFound;
import exceptions.CarNotFound;
import model.Car;
import org.apache.log4j.Logger;

import javax.security.auth.login.AccountNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class CarDAO implements ICarDAO {
    File jsonFile;
    ObjectMapper mapper;
    TypeReference listReference;
    private Logger logger=Logger.getLogger(CarDAO.class);

    public CarDAO(String jsonFilepath) {
        jsonFile = new File(jsonFilepath);
        if (!jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFile);
                writer.write("[]");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        listReference = new TypeReference<ArrayList<Car>>() {
        };
        logger.info("DAO initialized");
    }
    public Collection<Car> getAllCars(){
        Collection<Car> result = new ArrayList();
        try {
            result = mapper.readValue(jsonFile, listReference);
        } catch (JsonParseException e) {
            logger.error("JSONException occured");
            e.printStackTrace();

        }catch (IOException e) {
            logger.error("IOException occured");
            e.printStackTrace();
        }
        return result;
    }
    public Car getCarByTitle(String title) throws CarNotFound {
        Collection<Car> cars = new ArrayList();
        try {
            cars = mapper.readValue(jsonFile, listReference);
            for(Car c: cars){
                if (c.getTitle().equalsIgnoreCase(title)){
                    logger.info(c.getTitle()+" was returned");
                    return c;

                }
            }
        } catch (IOException e) {
            logger.error("IOException occured");
            e.printStackTrace();
        }
        logger.error(title+" was not found in the database");
        throw new CarNotFound(title);


    }
    public boolean alreadyAdded(String title) throws CarNotFound {
        Collection<Car> cars = new ArrayList();
        try {
            cars = mapper.readValue(jsonFile, listReference);
            for(Car c: cars){
                if (c.getTitle().equalsIgnoreCase(title)){
                    logger.info(title+" already added");
                    return true;
                }
            }
        } catch (IOException e) {
            logger.error("IOException occured");
            e.printStackTrace();

        }
        logger.error(title+" was not found in the database");
        throw new CarNotFound(title);


    }
    public void addCar(Car car) throws AnotherCarFound {
        try {
            if(alreadyAdded(car.getTitle())) {
                logger.error(car.getTitle()+" is already in the list");
                throw new AnotherCarFound(car);
            }
        } catch (CarNotFound carNotFound) {
            Collection<Car> result = getAllCars();
            result.add(car);
            logger.info(car.getTitle()+" was added to the database");
            try {
                mapper.writeValue(jsonFile, result);

            } catch (IOException e) {
                logger.error("IOException occured");
                e.printStackTrace();
            }

        }


        }


}
