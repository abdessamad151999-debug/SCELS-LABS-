>Lab 01: Domain Objects, Java Classes, and the First
Executable Project
abdessamad assallaou
16/09/2026
package app;

import model.*;
import exception.*;
import service.*;
import util.Chec;

public class App{
	public static Loan[] loans = new Loan[10];
	public static int nextLoanNumber = 1;
	private static Loan attemptBorrow(CampusMember member, EquipmentItem item,int numberOfDays){
		 if(!item.isAvailable()){
			System.out.println("this item is not available");
			return null;
		}
		 if (!member.canBorrow()){
			System.out.println("this member has reached the borrowing limit");
			return null;
		}
	else{
	int index = 0;
    while (index < loans.length && loans[index] != null) {
        index++;
    }
		item.markBorrowed();
		member.recordBorrowing();
		String loanid = String.format("L%03d", nextLoanNumber);
		Loan loan =new Loan(loanid, member, item, numberOfDays);
		loans[index] = loan;
		nextLoanNumber++;
		System.out.println(item.getName()+" borrowed successfully for "+numberOfDays+" days. Total fee: "+item.calculateFee(numberOfDays));
	
	return loan;
	}
}
private static Loan findActiveLoanForItem(Loan[] loans, EquipmentItem item) {
        for (Loan loan : loans) {
            if (loan != null && loan.isActiveLoan() && loan.isForItem(item)) {
                return loan;
            }
        }
        return null;
    }
	private static void attemptReturn(CampusMember member, EquipmentItem item ){
		 Loan loan = findActiveLoanForItem(loans, item);
        if (loan == null) {
            System.out.println("Return rejected: no active loan for " + item.getName());
            return;
		}
		else{
			loan.closeLoan();
			item.markReturned();
			member.recordReturn();
			System.out.println(item.getName()+" returned successfully");
		}
	}
	private static void printAllLoans(Loan[] loans) {
        System.out.println("--- All loans (active + closed) ---");
        for (Loan loan : loans) {
            if (loan != null) {
                loan.summaryLine();
            }
        }
    }
	private static void printActiveLoans(Loan[] loans) {
        System.out.println("--- Active loans ---");
        for (Loan loan : loans) {
            if (loan != null && loan.isActiveLoan()) {
                loan.summaryLine();
            }
        }
    }
	public double calculateActiveLoanFees(Loan[] loans){
		double totalFees = 0.0;
		for (Loan loan : loans) {
			if (loan != null && loan.isActiveLoan()) {
				totalFees += loan.getTotalFee();
			}
		}
		return totalFees;
	}
	public static void printCompactReport(CampusMember member,EquipmentItem item,EquipmentItem item2,EquipmentItem item3){
		System.out.println("Compact status Report");
		System.out.println(member.getFullName());
		System.out.println("Active Loans: "+member.getLoanStatus());
		System.out.println(item.getItemId()+" - "+item.getName()+" - "+item.getAvailabilityText());
		System.out.println(item2.getItemId()+" - "+item2.getName()+" - "+item2.getAvailabilityText());
		System.out.println(item3.getItemId()+" - "+item3.getName()+" - "+item3.getAvailabilityText());
	}

	 
	public static void main(String args[]){
	CampusMember member1 = new CampusMember("M001", " Amin", "amin@univ.ma", "AI",2);
	Laptop laptop = new Laptop("EQ002", "laptop", "computing", 80.0, "Yes", 16);
	Camera camera = new Camera("EQ001", "Canon Camera", "Media", 50.0, "24 MP", "Yes");
	Projector projector = new Projector("EQ003", "Epson Projector", "presentation", 60.0, "3000 Lumens", "Yes");

		//System.out.println("loan1.getMember() == member1 : " + (loan1.getMember() == member1));
        //System.out.println("loan2.getItem() == laptop    : " + (loan2.getItem() == laptop));
        //System.out.println("loan3.getItem() == projector : " + (loan3.getItem() == projector));

		EquipmentItem[] equipament = {camera, laptop, projector};
		for (EquipmentItem item : equipament) {
			System.out.println("Technical description for " + item.getName() + ": " + item.getTechnicalDescription());
			item.displaySummary();
			System.out.println();
		}
		Chargeable[] chargeables = {laptop, camera, projector};
		int[] durations = {1, 3, 5};
		for (Chargeable charge : chargeables) {
   			 System.out.print(((EquipmentItem) charge).getName() + " :");
    		for (int days : durations) {
       		 System.out.print("  " + days + "d = " + String.format("%.2f", charge.calculateFee(days)));
   			 }
    		System.out.println();
		}	
		// EquipmentItem item = new EquipmentItem("E001", "Generic Item", "General", 10.0); // Erreur : EquipmentItem est abstrait et ne peut pas être instancié directement
		Loan loan1 = attemptBorrow(member1, camera, 3);
		Loan loan2 = attemptBorrow(member1, laptop, 5);
		Loan loan3 = attemptBorrow(member1, projector, 2);
		attemptReturn(member1, laptop);
		Loan loan4 = attemptBorrow(member1, projector, 4);
		printAllLoans(loans);
		printActiveLoans(loans);
		}
}
