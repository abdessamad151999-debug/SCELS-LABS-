package model;

public class Projector extends EquipmentItem{
    private String lumens;
    private String hdmiSupported;

    public Projector(String itemId, String name, String category, double dailyFee, String lumens, String hdmiSupported) {
        super(itemId, name, category, dailyFee);
        this.lumens = lumens;
        this.hdmiSupported = hdmiSupported;
       // System.out.println("Projector constructor"); // temporary
    }
public String getLumens() {
        return lumens;
    }
    @Override
public String getDisplayLabel() {
return "Projector | " + super.getDisplayLabel();
}

    @Override
    public double calculateFee(int numberOfDays) {
        if (numberOfDays > 0) {
            return getDailyFee() * numberOfDays;
        } else {
            return 0.0;
        }
    }

    @Override
    public String getTechnicalDescription() {
        return "Lumens: " + lumens + ", HDMI Supported: " + hdmiSupported;
    }

}
