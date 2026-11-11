package model;

public class Tablet extends EquipmentItem {
private int storageGb;
public Tablet(String itemId, String name, String category, double dailyFee, int storageGb) {
super(itemId, name, category, dailyFee);
this.storageGb = storageGb;
}
public int getStorageGb() {
return storageGb;
}
@Override 
 public String getTechnicalDescription(){
    return "Storage: " + storageGb + "GB";
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
public String getDisplayLabel() {
return "Tablet | " + super.getDisplayLabel();
}
}