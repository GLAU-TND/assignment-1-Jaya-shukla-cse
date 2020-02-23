package definition;


import adt.Mycont;
import java.util.InputMismatchException;
import java.util.regex.Pattern;
import java.util.Scanner;

public class MyContact implements Mycont {
    Scanner sc=new Scanner(System.in);
    MyLinkedList<Person> MyContactBook=new MyLinkedList<>();
    MyLinkedList<String> contactNumbers=new MyLinkedList<>();

    private String getFirstName(){
        System.out.println("Please Enter The Name Of The Person");
        System.out.println("FirstName:");
        String firstName=sc.next();
        return firstName;
    }
    private String getLastName() {
        System.out.println("LastName:");
        String lastName = sc.next();
        return lastName;
    }
    private MyLinkedList<String> GetContactNumbers(){
        MyLinkedList<String> contactNumbers=new MyLinkedList<>();
        System.out.println("Contact Number");
        String contactNum=sc.next();
        while(true){
            if(Pattern.matches("[0-9]+",contactNum)){
                contactNumbers.add(contactNum);
                break;
            }
            else{
                System.out.println("Invalid PhoneNumber");
                break;
            }
        }
        while(true){
            System.out.println("Do you want to add new contact number?(y/n):");
            String s=sc.next();
            char at=s.charAt(0);
            if(s.length()>1){
                System.out.println("Please enter a valid input,i.e.,y(lowercase) for Yes or n(lowercase) for no");
                continue;
            }
            if(at=='y'){
                System.out.println("contactNumber: ");
                contactNum = sc.next();
                if (Pattern.matches("[0-9]+", contactNum)) {
                    contactNumbers.add(contactNum);
                }
                else {
                    System.out.println("Invalid PhoneNumber");
                }
            }
            else if (at == 'n') {
                break;
            }
            else {
                System.out.println("Please Enter a Vaild Input i.e., y(lowercase) for Yes or n(lowercase) for No");
            }
        }
        return contactNumbers;
    }

    private String getEmail() {
        String Email = null;
        while (true) {
            System.out.println("Do you want to add an Email ? (y/n) :");
            String s = sc.next();
            char at = s.charAt(0);
            if (s.length() > 1) {
                System.out.println("Please Enter a valid input i.e.,y(lowerCase) for Yes or n(lowerCase) for No");
                continue;
            }
            if (at == 'y') {
                System.out.println("Email Address: ");
                Email = sc.next();
            }
            else if (at == 'n') {
                break;
            }
            else {
                System.out.println("Please Enter Valid Input i.e., y(lowecase) for Yes or n(lowercase) for No");
            }
        }
        return Email;
    }

    private int compareFirstName(String fName) {
        int index = 0;
        if (MyContactBook.size == 0) {
        }
        else {
            for (int i = 0; i < MyContactBook.size; i++) {
                Person temp = MyContactBook.getData(i);
                String name = temp.getFirstName();
                name = name.toLowerCase();
                fName = fName.toLowerCase();
                if (name.compareTo(fName) < 0) {
                    index++;
                }
                else if (name.compareTo(fName) == 0) {
                    return index;
                }
                else {
                    break;
                }
            }
        }
        return index;
    }

    @Override
    public void addContact() {
        System.out.println("You have chosen to add a new contact:");
        String firstname = getFirstName();
        String lastname = getLastName();
        contactNumbers = GetContactNumbers();
        String Email = getEmail();
        Person newContact;
        newContact = new Person(firstname, lastname, Email, contactNumbers);
        int index = compareFirstName(firstname);
        MyContactBook.add(newContact, index);
        System.out.println("Contact Added SuccessFully");
        System.out.println();
        System.out.println();
    }

    @Override
    public void viewContact() {
        System.out.println("---Here are all your contacts---\n" +
                "-------- * -------- * -------- * --------");

        for (int i = 0; i < MyContactBook.size; i++) {
            Person response = MyContactBook.getData(i);
            System.out.println(response);
        }
        System.out.println("Total Contacts: " + MyContactBook.size);
    }

    private void printNames() {
        System.out.println("Here are your all contacts:");
        for (int i = 0; i < MyContactBook.size; i++) {
            Person temp = MyContactBook.getData(i);
            System.out.println((i + 1) + "." + temp.getFirstName() + " " + temp.getLastName());
        }
    }

    @Override
    public void deleteContact() {
        Scanner sc = new Scanner(System.in);
        printNames();
        System.out.print("Press the number against the contact to delete it: ");
        try {
            int index = sc.nextInt();
            if (index > MyContactBook.size || index == 0) {
                System.out.println("Invaild Input");
            }
            else {
                Person p = MyContactBook.getData(index - 1);
                String name = p.getFirstName() + p.getLastName();
                MyContactBook.remove(index - 1);
                System.out.println(name + "'s Contact has been removed Successfully");
            }
        }
        catch (InputMismatchException E) {
            System.out.println("Integer input expected ");
        }
    }

    @Override
    public void searchContact() {
        int size = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("You could search for a contact from their first names:");
        String name = sc.next();
        name = name.trim();
        MyLinkedList<Integer> lists = matchFirst(name);
        boolean a = false;
        size = lists.size;
        if (size > 1) {
            a = true;
        }
        System.out.println(a ? size + " Matches found!" : size + " Match found!");
        for (int i = 0; i < size; i++) {
            int index = lists.getData(i);
            System.out.println(MyContactBook.getData(index));
        }
    }

    private MyLinkedList<Integer> matchFirst(String Firstname) {
        MyLinkedList<Integer> indexes = new MyLinkedList<>();
        if (MyContactBook.size == 0) {
        }
        else {
            for (int i = 0; i < MyContactBook.size; i++) {
                Person temp = MyContactBook.getData(i);
                String name = temp.getFirstName();
                name = name.toLowerCase();
                Firstname = Firstname.toLowerCase();
                if (name.compareTo(Firstname) == 0) {
                    indexes.add(i);
                }

            }
        }
        return indexes;
    }
}


