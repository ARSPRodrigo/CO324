package bank;

import java.util.HashMap;
import java.util.Map;

public class Account {

	private static int count = 0;
	/** Hash table of all accounts that have been created */
	private static Map<Integer,Account> accounts = new HashMap<Integer,Account>();
	
	/** Find account by ID */
	public static Account find(int id) {
		return accounts.get(id);	
	}

	final int id;
	private int balance;
	
	/** Create new account with unique ID and add it to global hash table */
	public Account() {
        /**
         * Q1 c
         * If this is not synchronized there can be conflicts
         * in account id's when creating from multiple threads.
         * That means it will cause a data race
         */
		synchronized(accounts) {
			id = count++;
			accounts.put(id, this);
		}
	}
	
	/** Negative amount indicates withdrawal */
	public void deposit(int amount) {
        /**
         * Q1 a
         * This is a data race in this class
         * deposit can be accessed by many threads
         * and this is not synchronized
         * balance = 10
         * T1------------+-----T2
         * +deposit(1)---+
         * +-------------+deposit(2)
         * +-------------+balance = 12
         * +balance = 11-+
         */
        /**
         * Q1 d
         * This method needs synchronisation
         * but transfer is synchronized so this is not need
         */
        /**
         * balance += amount;
         */
        /**
         * Q2 a
         */
        //synchronized (this) {
            balance += amount;
        //}
	}
	
	/** Transfer amount from this account to another. */
	public void transfer (Account to, int amount) {
        /**
         * Q1 b
         * When called from multiple threads without explicit synchronization,
         * this admits both data race and race conditions
         */
        /**
         * Q1 d
         * This method needs synchronisation
         */
        /**
         * deposit(-amount);
         * to.deposit(amount);
         */
        /**
         * Q2 a
         */
        synchronized (this) {
            deposit(-amount);
        }
        synchronized (to) {
            to.deposit(amount);
        }
	}
	
	public int balance() {
		return balance;
	}

	@Override
	public String toString() {
		return "ID:"+id+", balance:"+balance;
	}
}
