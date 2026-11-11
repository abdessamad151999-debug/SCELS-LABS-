package app;

import model.*;
import exception.*;
import service.*;
import util.Chec;

public class App{
private static final LendingService service= new LendingService();
private static void tryBorrow(String memberId, String itemId, int days) {
        System.out.println("> borrowItem(" + memberId + ", " + itemId + ", " + days + ")");
        try {
            Loan loan = service.borrowItem(memberId, itemId, days);
            System.out.println("Loan created:");
            loan.summaryLine();
        } catch (UnknownMemberException e) {
            System.out.println("REJECTED (member)      : " + e.getMessage());
        } catch (UnknownItemException e) {
            System.out.println("REJECTED (item)        : " + e.getMessage());
        } catch (ItemUnavailableException e) {
            System.out.println("REJECTED (unavailable) : " + e.getMessage());
        } catch (InvalidLoanException e) {
            System.out.println("REJECTED (rule)        : " + e.getMessage());
        }
		finally {
			System.out.println("Borrowing scenario finished .");
		}
        printState(itemId);
		
    }
	 private static void tryReturn(String loanId) {
        System.out.println("> returnItem(" + loanId + ")");
        try {
            service.returnItem(loanId);
            System.out.println("Loan " + loanId + " returned successfully");
        } catch (UnknownLoanException e) {
            System.out.println("REJECTED (loan) : " + e.getMessage());
        } catch (InvalidLoanException e) {
            System.out.println("REJECTED (rule) : " + e.getMessage());
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
	private static void printState(String itemId) {
        EquipmentItem item = service.getItem(itemId);
        if (item != null) {
            System.out.println("   item " + itemId + " availability: " + item.getAvailabilityText());
        }
        System.out.println("   loans stored: " + service.getLoanCount());
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
    private static void printEquipment(EquipmentItem item) {
        System.out.println(item.getDisplayLabel());
        System.out.println(item.getDisplayLabel(true));
}

	 
	public static void main(String args[]){
	CampusMember member1 = new CampusMember("M001", " Amin", "amin@univ.ma", "AI",2);
	CampusMember member2 = new CampusMember("M002", "abdessamad", "abdessamad@univ.ma", "AI",4);
	Laptop laptop = new Laptop("EQ002", "laptop", "computing", 80.0, "Yes", 16);
	Camera camera = new Camera("EQ001", "Canon Camera", "Media", 50.0, "24 MP", "Yes");
	Projector projector = new Projector("EQ003", "Epson Projector", "presentation", 60.0, "3000 Lumens", "Yes");
    Tablet tablet = new Tablet("EQ004", "iPad", "computing", 40.0, 128);

		service.addMember(member1);
        service.addMember(member2);
        service.addItem(laptop);
        service.addItem(camera);
        service.addItem(projector);
        service.addItem(tablet);
		//System.out.println("loan1.getMember() == member1 : " + (loan1.getMember() == member1));
        //System.out.println("loan2.getItem() == laptop    : " + (loan2.getItem() == laptop));
        //System.out.println("loan3.getItem() == projector : " + (loan3.getItem() == projector));

	camera.getDisplayLabel();
    camera.getDisplayLabel(true);
    laptop.getDisplayLabel();
    laptop.getDisplayLabel(true);
    tablet.getDisplayLabel();
    tablet.getDisplayLabel(true);

    EquipmentItem[] inventory = {
        laptop,
        camera,
        projector,
        tablet
        };
    for (EquipmentItem item : inventory) {
    printEquipment(item);
    }
    for (EquipmentItem item : inventory) {
    if (item instanceof Laptop) {
    Laptop current = (Laptop) item;
    System.out.println("Laptop RAM: " + current.getRAM());
    } else if (item instanceof Camera) {
    Camera current = (Camera) item;
    System.out.println("Camera Resolution: " + current.getMegapixels());
    } else if (item instanceof Projector) {
    Projector current = (Projector) item;
    System.out.println("Projector Brightness: " + current.getLumens());
    } else if (item instanceof Tablet) {
    Tablet current = (Tablet) item;
    System.out.println("Tablet Storage: " + current.getStorageGb() + "GB");
    }
    }
//EquipmentItem unsaf = new Projector("EQ999", "Dummy Projector", "presentation", 0.0, "1000 Lumens", "No");
//Laptop forcedLaptop = (Laptop) unsaf; // Throws ClassCastException at runtime!


    // 1. print inventory before borrowing
for (EquipmentItem item : inventory) {
System.out.println(item.getDisplayLabel(true));
}
try {
// TODO: borrow an available Camera through LendingService
Loan loan = service.borrowItem(member1.getMemberId(), camera.getItemId(), 3);
// TODO: print the borrowed item’s polymorphic display label
camera.getDisplayLabel();
// TODO: attempt one rejected borrow using an existing Lab 04 failure
	tryBorrow("M001", "EQ999", 2);// unkown item
// TODO: return the valid loan through LendingService
service.returnItem(loan.getLoanId());
}  catch (UnknownMemberException
| UnknownItemException
| ItemUnavailableException
| InvalidLoanException
| UnknownLoanException e) {
System.out.println(e.getMessage());
}
// 2. print inventory after the scenarios
for (EquipmentItem item : inventory) {
System.out.println(item.getDisplayLabel(true));
}



	/*EquipmentItem[] equipament = {camera, laptop, projector};
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
		*/
	}
}
