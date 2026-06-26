# Simple Bank System

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Design` `Simulation`  
**Time:** O(1)  
**Space:** O(n)

---

## Solution (java)

```java
class Bank {
    long[] balance;
    public Bank(long[] balance) {
        this.balance = balance;
    }
    
    public boolean transfer(int account1, int account2, long money) {
        if(!(withdraw(account1,money))) return false;
        if(!deposit(account2,money)){ deposit(account1,money);return false;}
        return true;
    }
    
    public boolean deposit(int account, long money) {
        if(account > balance.length) return false;
        balance[account-1] += money;
        return true;
    }
    
    public boolean withdraw(int account, long money) {
        if(account > balance.length || balance[account-1] < money) return false;
        balance[account-1] -= money;
        return true;
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */
```

---

---
## Quick Revision
The problem requires implementing a simple bank system with methods to transfer money between accounts, deposit funds into an account, and withdraw funds from an account. This can be achieved by creating a `Bank` class with an array to store account balances.

## Intuition
This approach works because each account balance is stored in a single array index, allowing for efficient updates and retrievals using the account number as an index.

## Algorithm

1. Initialize the `balance` array within the `Bank` constructor.
2. Implement the `deposit` method by checking if the account exists, then adding the deposited amount to the corresponding balance index.
3. Implement the `withdraw` method by checking if the account exists and has sufficient funds, then subtracting the withdrawn amount from the corresponding balance index.
4. Implement the `transfer` method by first attempting to withdraw from the source account, then depositing into the destination account.

## Concept to Remember
* Array indexing allows for efficient storage and retrieval of data.
* Index validation is crucial when accessing array elements.
* Method chaining can be used to simplify complex operations.

## Common Mistakes

* Failing to validate account indices before accessing array elements.
* Neglecting to handle edge cases, such as insufficient funds or non-existent accounts.
* Not considering the potential for concurrent modifications to account balances.

## Complexity Analysis
- Time: O(1) / Each operation has constant time complexity since it directly accesses and updates a single array index.
- Space: O(n) / The `balance` array stores n account balances, resulting in linear space complexity.

## Commented Code

```java
class Bank {
    // Store account balances in an array for efficient storage and retrieval
    long[] balance;

    /**
     * Initialize the bank with an array of initial balances.
     */
    public Bank(long[] balance) {
        this.balance = balance;
    }

    /**
     * Transfer money from one account to another.
     *
     * @param account1 Source account number.
     * @param account2 Destination account number.
     * @param money Amount to transfer.
     */
    public boolean transfer(int account1, int account2, long money) {
        // Attempt to withdraw from source account
        if (!withdraw(account1, money)) return false;
        
        // Deposit into destination account; if unsuccessful, revert withdrawal
        if (!deposit(account2, money)) {
            deposit(account1, money);
            return false;
        }
        
        return true;
    }

    /**
     * Deposit funds into an account.
     *
     * @param account Account number.
     * @param money Amount to deposit.
     */
    public boolean deposit(int account, long money) {
        // Validate account index
        if (account > balance.length) return false;
        
        // Update balance with deposited amount
        balance[account - 1] += money;
        return true;
    }

    /**
     * Withdraw funds from an account.
     *
     * @param account Account number.
     * @param money Amount to withdraw.
     */
    public boolean withdraw(int account, long money) {
        // Validate account index and sufficient balance
        if (account > balance.length || balance[account - 1] < money) return false;
        
        // Update balance with withdrawn amount
        balance[account - 1] -= money;
        return true;
    }
}
```

## Interview Tips

* Pay close attention to array indexing and edge cases.
* Consider the use of method chaining for complex operations.
* Practice explaining your thought process behind the solution.

## Revision Checklist
- [ ] Implement deposit, withdraw, and transfer methods correctly.
- [ ] Validate account indices before accessing balance array.
- [ ] Handle edge cases such as insufficient funds or non-existent accounts.

## Similar Problems

* LeetCode 881: `Boat to Shore`
* LeetCode 1426: `Counting Elements`

## Tags
`Array` `Hash Map` `Bank System`
