import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountList {
    private ArrayList<Account> accountList;
    
    public AccountList() 
    {
        try
        {
            String filePath = "Accounts.csv";
            File file = new File(filePath);
            Scanner input = new Scanner(file);
            ArrayList<Account> accountList = new ArrayList<>();
            input.delimiter();
            while (input.hasNext())
            {
                String line=input.nextLine();
                String[] data = line.split(",");
                String userId = data[0];
                String userName = data[1];
                String password = data[2];
                String phoneNumber = data[3];
                String email = data[4];
                String accountType = data[5];
                accountList.add(new Account(userId, userName, password, phoneNumber, email, accountType));
            }
            this.accountList=accountList;
            input.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
        }
        
    }
    public ArrayList<Account> getAccountList() throws FileNotFoundException
    {
        return accountList;
    }
    
    

}
