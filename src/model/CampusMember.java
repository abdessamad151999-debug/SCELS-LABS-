package model;
public class CampusMember {
    private  String memberId;
    private  String fullName;
    private String email;
    private String department;
    private int activeLoans ;
    private int borrowingLimit;

    public CampusMember(String memberId,String fullName,String email,String department, int borrowingLimit){
        this.memberId=memberId;
        this.fullName = fullName;
        this.email = email;
        this.department= department;
        this.borrowingLimit=borrowingLimit;
        activeLoans=0;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public int getActiveLoans() {
        return activeLoans;
    }

    public String getDepartment() {
        return department;
    }

    public int getBorrowingLimit() {
        return borrowingLimit;
    }
    public boolean  canBorrow(){
        return activeLoans<borrowingLimit;
    }
    
    public void recordBorrowing(){
         if (canBorrow()){
           activeLoans++;
        }
    }
    public void recordReturn(){
        if(activeLoans>0){
            activeLoans=activeLoans-1;
        }
    }
    public void displayProfile (){
        System.out.println("memberid :"+memberId);
        System.out.println("fullname :"+fullName);
        System.out.println("email :"+email);
        System.out.println("departement :"+department);
        System.out.println("ActivLoans :"+activeLoans);
        System.out.println("BorrowingLimits :"+borrowingLimit);
    }
    public String getLoanStatus(){
        return activeLoans + "/" + borrowingLimit;
    }


}
