package model;

public class Laptop extends EquipmentItem {
    private String hasDedicatedGPU;
    private int ramGb;

    public Laptop(String itemId, String name, String category, double dailyFee, String hasDedicatedGPU, int ramGb) {
        super(itemId, name, category, dailyFee);
        this.hasDedicatedGPU = hasDedicatedGPU;
        this.ramGb = ramGb;
       // System.out.println("Laptop constructor");// temporary
    }
    public int getRAM() {
        return ramGb;
    }
    @Override
public String getDisplayLabel() {
return "Laptop | " + super.getDisplayLabel();
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
        return "Dedicated GPU: " + hasDedicatedGPU + ", RAM: " + ramGb + "GB";
    }

}
