package service;
import exception.*;
import model.*;

public class LendingService {
    private CampusMember[] members = new CampusMember[10];
    private EquipmentItem[] items = new EquipmentItem[10];
    private Loan[] loans = new Loan[10];
    private int memberCount = 0;
    private int itemCount = 0;
    private int loanCount = 0;
    private int nextLoanNumber = 1;

    public boolean addMember(CampusMember member) {
        if (memberCount == members.length) {
            return false;
        }
        members[memberCount] = member;
        memberCount++;
        return true;
    }

    public boolean addItem(EquipmentItem item) {
        if (itemCount == items.length) {
            return false;
        }
        items[itemCount] = item;
        itemCount++;
        return true;
    }

    private CampusMember findMember(String memberId) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getMemberId().equals(memberId)) {
                return members[i];
            }
        }
        return null;
    }

    private EquipmentItem findItem(String itemId) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getItemId().equals(itemId)) {
                return items[i];
            }
        }
        return null;
    }

    private Loan findLoan(String loanId) {
        for (int i = 0; i < loanCount; i++) {
            if (loans[i].getLoanId().equals(loanId)) {
                return loans[i];
            }
        }
        return null;
    }

    public EquipmentItem getItem(String itemId) {
        return findItem(itemId);
    }

    public int getLoanCount() {
        return loanCount;
    }

    private String generateLoanId() {
        return "L" + String.format("%03d", nextLoanNumber);
    }

    public Loan[] getLoans() {
        Loan[] copy = new Loan[loans.length];
        for (int i = 0; i < loans.length; i++) {
            copy[i] = loans[i];
        }
        return copy;
    }
public Loan borrowItem(String memberId,String itemId,int numberOfDays)throws UnknownMemberException,UnknownItemException,ItemUnavailableException,InvalidLoanException {
    CampusMember member = findMember(memberId);
        if (member == null) {
            throw new UnknownMemberException("Unknown member id: " + memberId);
        }

    EquipmentItem item = findItem(itemId);
        if (item == null) {
            throw new UnknownItemException("Unknown item id: " + itemId);
        }

        if (!item.isAvailable()) {
            throw new ItemUnavailableException("Item already borrowed: " + itemId);
        }

        if (numberOfDays < 1 || numberOfDays > 30) {
            throw new InvalidLoanException("Invalid duration: " + numberOfDays
                    + " day(s) (allowed: 1 to 30)");
        }
        if (!member.canBorrow()) {
            throw new InvalidLoanException("Borrowing limit reached for member: " + memberId
                    + " (" + member.getLoanStatus() + ")");
        }

        if (loanCount == loans.length) {
            throw new InvalidLoanException("Loan archive is full (" + loans.length + " loans)");
        }
     Loan loan = new Loan(generateLoanId(), member, item, numberOfDays);
        item.markBorrowed();
        member.recordBorrowing();
        loans[loanCount] = loan;
        loanCount++;
        nextLoanNumber++;
        return loan;

}
public void returnItem(String loanId) throws UnknownLoanException, InvalidLoanException {
        Loan loan = findLoan(loanId);
        if (loan == null) {
            throw new UnknownLoanException("Unknown loan id: " + loanId);
        }
        if (!loan.isActiveLoan()) {
            throw new InvalidLoanException("Loan already closed: " + loanId);
        }

        loan.closeLoan();
        loan.getItem().markReturned();
        loan.getMember().recordReturn();
    }
}


