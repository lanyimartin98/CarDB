package model;

import exceptions.InvalidData;
import exceptions.MilageCanNotBeLowered;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Objects;

public class Car {

    public Car(){

    }

    private String make;
    private String model;
    private String title;
    private LocalDate startOfUse;
    private double horsePower;
    private inUse inUse;
    private boolean underCustody;
    private usageType usageType;
    private int milage;
    private Logger logger=Logger.getLogger(Car.class);

    public String getMake() {
        return make;
    }

    public void setMake(String make) {

        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws InvalidData {
        if(!title.matches("[A-Z][A-Z][A-Z]-[0-9][0-9][0-9]")) {
            logger.error("Invalid title format was used");
            throw new InvalidData(title);
        }

        this.title=title;
    }

    public LocalDate getStartOfUse() {
        return startOfUse;
    }

    public void setStartOfUse(LocalDate startOfUse) throws InvalidData {

        if(startOfUse.isAfter(LocalDate.now())){
            logger.error("Invalid date tried to be set");
            throw new InvalidData(startOfUse.toString());
        }
        this.startOfUse = startOfUse;
    }

    public double getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(double horsePower){

        if(horsePower>1000){
            this.usageType=usageType.racecar;
            logger.info(this.title+" was specified as racecar");
            this.horsePower=horsePower;
        }
        else {

            this.horsePower = horsePower;
        }

    }

    public model.inUse getInUse() {
        return inUse;
    }

    public void setInUse(model.inUse inUse) {
        this.inUse = inUse;
    }

    public boolean isUnderCustody() {
        return underCustody;
    }

    public void setUnderCustody(boolean underCastody) {
        this.underCustody = underCastody;
    }

    public model.usageType getUsageType() {
        return usageType;
    }

    public void setUsageType(model.usageType usageType) {

        this.usageType = usageType;
    }

    public int getMilage() {
        return milage;
    }

    public void setMilage(int milage) throws MilageCanNotBeLowered {
        if(milage<this.milage){
            logger.error("Milage was tried to be lowered on "+this.title);
            throw new MilageCanNotBeLowered();

        }
        this.milage = milage;
    }

    public Car(String make, String model, String title, LocalDate startOfUse, double horsePower, model.inUse inUse, boolean underCustody, model.usageType usageType,int milage) {
        this.make = make;
        this.model = model;
        this.title = title;
        this.startOfUse = startOfUse;
        this.horsePower = horsePower;
        this.inUse = inUse;
        this.underCustody = underCustody;

        if(horsePower>1000){
            this.usageType= usageType.racecar;
            this.horsePower = horsePower;

        }
        else {
            this.usageType = usageType;
        }
        this.milage=milage;
        logger.info("New car was inserted with a title "+title);
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", title='" + title + '\'' +
                ", startOfUse=" + startOfUse +
                ", horsePower=" + horsePower +
                ", inUse=" + inUse +
                ", underCastody=" + underCustody +
                ", usageType=" + usageType +
                ", milage=" + milage+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.horsePower, horsePower) == 0 &&
                underCustody == car.underCustody &&
                milage == car.milage &&
                make.equals(car.make) &&
                model.equals(car.model) &&
                title.equals(car.title) &&
                startOfUse.equals(car.startOfUse) &&
                inUse == car.inUse &&
                usageType == car.usageType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, title, startOfUse, horsePower, inUse, underCustody, usageType, milage);
    }
}
