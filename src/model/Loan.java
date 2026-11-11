package model;

public class Loan {
    private String loanId ;
    private int numberOfDays;
    private double totalFee;
    private boolean active;
    private CampusMember member;
    private EquipmentItem item;
public  Loan ( String loanId,CampusMember member,EquipmentItem item, int numberOfDays){
    this.item=item;
    this.member= member;
    this.loanId = loanId;
    this.numberOfDays= numberOfDays;
    this.totalFee = item.calculateFee(numberOfDays);
    this.active = true ;
    }
public CampusMember getMember(){
 return member;
}
public EquipmentItem getItem(){
    return item;
}
public boolean isActiveLoan(){
    return active;
}
public String getLoanId(){
    return loanId;
}
public int getNumberOfDays(){
    return numberOfDays;
}
public double getTotalFee(){
    return totalFee;
}
public boolean closeLoan(){
    return active=false;
}
public boolean isForItem(EquipmentItem targetItem){
 if (targetItem == item){
    return true ;
 }
 return false;
}
public boolean belongsTo(CampusMember targetMember){
    if (targetMember == member){
        return true ;
    }
return false;
}
public void summaryLine(){
    System.out.println("Loan ID :"+loanId);
    System.out.println("member :"+getMember().getFullName());
    System.out.println("item :"+getItem().getName());
    System.out.println("Duration :"+getNumberOfDays());
    System.out.println("fee :"+getTotalFee() );
    if(isActiveLoan()){
        System.out.println("status : Active");
    }
    else{
        System.out.println("status : Closed");
    }

}
}