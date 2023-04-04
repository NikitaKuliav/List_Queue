package Transport;

public abstract class Transport<T extends Driver> implements Competing {
    private final String brand;
    private final String model;
    private double engineVolume;
    private T driver;
    private int gasTankBar;
    private int oilTankBar;

    public Transport(String brand,
                     String model,
                     double engineVolume,
                     T driver,
                     int gasTankBar,
                     int oilTankBar) {
        if (brand == null || brand.equals("")) {
            brand = "default";
        }
        this.brand = brand;
        if (model == null || model.equals("")) {
            model = "default";
        }
        this.model = model;
        setEngineVolume(engineVolume);
        setDriver(driver);
        setGasTankBar(gasTankBar);
        setOilTankBar(oilTankBar);

    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }


    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        if (engineVolume <= 0) {
            engineVolume = 1.6;
        }
        this.engineVolume = engineVolume;
    }

    public void setGasTankBar(int gasTankBar) {
        if (gasTankBar == 0) {
            gasTankBar = 100;
        }
        this.gasTankBar = gasTankBar;
    }

    public void setOilTankBar(int oilTankBar) {
        if (oilTankBar == 0) {
            oilTankBar = 100;
        }
        this.oilTankBar = oilTankBar;
    }


    public T getDriver() {
        return driver;
    }

    public void setDriver(T driver) {
        this.driver = driver;
    }

    public abstract void printType();

    public abstract void startMove();

    public abstract void finishMove();

    public abstract Type getType();

    public void addGas(int gas){
        setGasTankBar(100);
        System.out.println("Транспорт " + Transport.this + " заправлен до полного бака");

    }

    public void addOil(int oil){
        setOilTankBar(100);
        System.out.println("В транспорт " + Transport.this + " залито масло");
    }

    public void decreaseGasAndOil(int gas, int oil) {
        this.gasTankBar -= gas;
        this.oilTankBar -= oil;
    }

    public int getGasTankBar() {
        return gasTankBar;
    }

    public int getOilTankBar() {
        return oilTankBar;
    }

    public void turnOnEngine(Transport object) throws EmptyGasTankException, NoOilException {

            if (object.getGasTankBar() > 0 && object.getOilTankBar() > 0) {
                System.out.println("Двигатель заведён");
            } else if (object.getGasTankBar() <= 0) {
                throw new EmptyGasTankException("Нет топлива");
            } else {
                throw new NoOilException("Недостаточно масла");
            }

    }

    @Override
    public String toString() {
        return
                "марка='" + brand + '\'' +
                        ", модель='" + model + '\'' +
                        ", объем двигателя=" + engineVolume;

    }
}
