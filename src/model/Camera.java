package model;

public class Camera extends EquipmentItem{
    private String megapixels;
    private String lensKitIncluded;

    public Camera(String itemId, String name, String category, double dailyFee, String megapixels, String lensKitIncluded) {
        super(itemId, name, category, dailyFee);
        this.megapixels = megapixels;
        this.lensKitIncluded = lensKitIncluded;
       // System.out.println("Camera constructor"); // temporary
    }
public String getMegapixels() {
        return megapixels;
    }
    @Override
public String getDisplayLabel() {
return "Camera | " + super.getDisplayLabel();
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
        return "Megapixels: " + megapixels + ", Lens Kit Included: " + lensKitIncluded;
    }

}
