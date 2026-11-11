package model;
public abstract class EquipmentItem implements Chargeable{
    private String itemId;
    private String name;
    private String category;
    private double dailyFee;
    private boolean available;
protected EquipmentItem(String itemId , String name, String category, double  dailyFee){
    this.itemId = itemId;
    this.name = name ; 
    this.category = category;
    this.dailyFee = dailyFee;
    this.available = true;
   // System.out.println("EquipmentItem constructor");// temporary
}

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }
    protected double getDailyFee() {
        return dailyFee;
    }
    public String getCategory() {
        return category;
    }
public boolean  isAvailable(){
return available;
}
public void   markBorrowed(){
if(available){
    available = false;
}
else{
    System.out.println("this item is already borrowed");
}
}
public void markReturned(){
    if (available){
        System.out.println("this item already available");
    }
    else{
        available=true;
    }
}
public void displaySummary(){
        System.out.println("itemId : " +itemId);
        System.out.println("name :"+name);
        System.out.println("category :"+category);
        System.out.println("dailyFee :"+dailyFee);
        System.out.println("availability :"+getAvailabilityText());

}
public String getAvailabilityText(){
    if(available){
        return "Available";
    }
    else{
        return "Borrowed";
    }
}
public String getDisplayLabel(boolean includeAvailability) {
// TODO 1: when false, return getDisplayLabel()
// TODO 2: when true, append " | available=" + isAvailable()
if (includeAvailability) {
    return getDisplayLabel() + " | available=" + isAvailable();
}
return getDisplayLabel();
}
public String getDisplayLabel() {
return getItemId() + " - " + getName();

}
 public abstract String getTechnicalDescription();
}
