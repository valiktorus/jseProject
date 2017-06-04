package by.gsu.epamlab.enums;

public enum  Driver {
    MySQL("com.mysql.jdbc.Driver");

    private String driverName;

    Driver(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }
}
