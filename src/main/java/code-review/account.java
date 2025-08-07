/**
* Relax Gaming - Java clean code test
*
* For the purposes of this test, the candidate can assume that the code compiles and that references
* to other classes do what you would expect them to.
*
* The objective is for the candidate to list down the things in plain text which can be improved in this class
*
* Good luck!
*
*/

//Class name should be PascalCase compliance (Account not account)
public class account {
    //Field should be private and accessed via getter/setter
    public String accountNumber;

    //Constructor name should match class name (Account not account)
    public account(String accountNumber){
        this.accountNumber = accountNumber;
    }
    
    public String getAccountNumber(){
        return accountNumber;
    }
    
    //Should use generic type List<Transaction> instead of raw ArrayList and  use specific exception.
    public ArrayList getTransactions() throws Exception{
        try{
            // Should use generic type List<?> instead of raw List
            List dbTransactionList = Db.getTransactions(accountNumber.trim());
            ArrayList transactionList = new ArrayList();
            //Could declare i in for loop header
            int i;
            for(i=0; i<dbTransactionList.size(); i++){
                DbRow dbRow = (DbRow) dbTransactionList.get(i);
                Transaction trans = makeTransactionFromDbRow(dbRow);
                trans.setTimestamp(createTimestampAndExpiryDate(trans)[0]);
                trans.setExpiryDate(createTimestampAndExpiryDate(trans)[1]);
                transactionList.add(trans);
            }
            return transactionList;
            
        } catch (SQLException ex){
            //Should log the exception before rethrowing
            throw new Exception("Can't retrieve transactions from the database");
        }
    }
    
    public Transaction makeTransactionFromDbRow(DbRow row)
    {
        double currencyAmountInPounds = Double.parseDouble(row.getValueForField("amt"));
        //Use BigDecimal for precision
        float currencyAmountInEuros = new Float(currencyAmountInPounds * 1.10);
        String description = row.getValueForField("desc");
//        description = fixDescription(description); //Remove commented code
        return new Transaction(description, currencyAmountInEuros);
    }

    //Method name has typo error (Expirydate should be ExpiryDate)
    public String[] createTimestampAndExpirydate(Transaction trans) {
        //Array name should be meaningful and array is initialized size 0 it has to be 2.
    	String[] return1 = new String[]{};
    	LocalDateTime now = LocalDateTime.now();
    	return1[0] = now.toString();
        //Could reuse 'now' variable instead of creating new instance
    	return1[1] = LocalDateTime.now().plusDays(60).toString();
    	
    	return return1;
    	
    }
    
    public String fixDescription(String desc) {
    	String newDesc = "Transaction [" + desc + "]";
    	return newDesc;
    }

    //Add @Override annotation
    //Parameter should be 'Object o' to properly override
    //Should compare strings with equals() not ==
    //Should implement hashCode() when equals() is implemented
    public boolean equals(Account o) {
        return o.getAccountNumber() == getAccountNumber();
    }

    //Implement  toString()
}       

