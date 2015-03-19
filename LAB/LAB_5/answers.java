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
         */
        /**
         * balance += amount;
         */
        /**
         * Q2 a
         */
        /**
         * this synchronisation is not needed
         */
        //synchronized (this) {
            balance += amount;
        //}
    }

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

    /**
     * Q2 b
     * Starvation cannot be happen
     * Because the locking is done once for a instance
     * of modifying the balance 
     */

    /**
     * Q2 c
     * A deadlock happens if the modification the threads 
     * are trying to achive is circular and when those modifications
     * done on exclusive resources.
     * synchronized (this) {
     *      deposit(-amount);
     *      synchronized (to) {
     *          to.deposit(amount);
     *      } 
     *  }
     * In this synchronisation methods there are no 
     * any synchronization like that.
     */