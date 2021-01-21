
import java.util.StringJoiner;

/**
 *
 * @author jordo
 */
public class SortingEfficienciesUI extends javax.swing.JFrame {

    public static long loopCounterS, compareCounterS, shiftCounterS;
    public static long loopCounterB, compareCounterB, shiftCounterB;
    public static long loopCounterI, compareCounterI, shiftCounterI;
    public static long loopCounterQ, compareCounterQ, shiftCounterQ;
    
    public SortingEfficienciesUI() {
        initComponents();
    }
    
    public void initRandom(int[] randomNumbers, int totalNumbers, StringJoiner random){
        for (int i=0;i<totalNumbers;i++){   //20000 potential numbers chosen at random
                                            //Between -10000 and 10000
            randomNumbers[i] = (int)(Math.random() * ((20000) + 1)) + -10000;
            random.add(randomNumbers[i]+"");
        }
    }
    
    public void copyArray(int[] original, int[] copy){
        for (int i=0;i<original.length;i++){
            copy[i] = original [i]; //Copies each value by each index increment
        }
    }
    
    static public int[] selection(int[] rand){
        for(int i = 0; i < rand.length - 1; i++){
            loopCounterS++;
            for(int j = i + 1; j < rand.length; j++){
                loopCounterS++;
                compareCounterS++;
                if(rand[i] > rand[j]){  //Compares the two values beside eachother
                                        //Swaps if first one is greater, if not checks
                                        //First with the next value
                    shiftCounterS++;
                    int temp = rand[i];
                    rand[i] = rand[j];
                    rand[j] = temp;
                }
            }
        }
        return rand;
    }
    
    static public int[] bubble(int[] rand){
        int temp;
        for(int i = 0; i < rand.length; i++) {
            loopCounterB++;
            for(int j = 1; j < (rand.length-i); j++) {
                loopCounterB++;
                compareCounterB++;
                if(rand[j-1] > rand[j]){    //Checks all values that are next to eachother
                                            //And swaps if j-1 (or earlier one) is
                                            //Greater than the next value
                    shiftCounterB++;
                    temp = rand[j-1];
                    rand[j-1] = rand[j];
                    rand[j] = temp;
                }
            }
        }
        return rand;
    }
    
    static public int[] insertion(int[] rand){
        for (int i = 1; i < rand.length; i++) {
            loopCounterI++;
            int temp = rand[i];
            int j = i - 1;
            compareCounterI++;
            while (j >= 0 && rand[j] > temp) {  //Compares the second term to the first
                        //If the earlier value is more than the later, then swap
                        //Repeated after all terms are in relative order
                loopCounterI++;
                shiftCounterI++;
                rand[j + 1] = rand[j];
                j = j - 1;
            }
            rand[j + 1] = temp;
        }
        return rand;
    }
    
    static public int[] selectionDes(int[] rand){
        for(int i = 0; i < rand.length - 1; i++){
            loopCounterS++;
            for(int j = i + 1; j < rand.length; j++){
                loopCounterS++;
                compareCounterS++;
                if(rand[i] < rand[j]){  //Compares the two values beside eachother
                                        //Swaps if second one is greater, if not checks
                                        //First with the next value
                    shiftCounterS++;
                    int temp = rand[i];
                    rand[i] = rand[j];
                    rand[j] = temp;
                }
            }
        }
        return rand;
    }
    
    static public int[] bubbleDes(int[] rand){
        int temp;
        for(int i = 0; i < rand.length; i++) {
            loopCounterB++;
            for(int j=1; j < (rand.length-i); j++) {
                loopCounterB++;
                compareCounterB++;
                if(rand[j-1] < rand[j]) {   //Checks all values that are next to eachother
                                            //And swaps if j or later one is
                                            //Greater than the value before it
                    shiftCounterB++;
                    temp = rand[j-1];
                    rand[j-1] = rand[j];
                    rand[j] = temp;
                }
            }
        }
        return rand;
    }
    
    static public int[] insertionDes(int[] rand){
        for (int i = 1; i < rand.length; i++) {
            loopCounterI++;
            int temp = rand[i];
            int j = i - 1;
            compareCounterI++;
            while (j >= 0 && rand[j] < temp) {  //Compares the second term to the first
                        //If the earlier value is less than the later, then swap
                        //Repeated after all terms are in relative order
                loopCounterI++;
                shiftCounterI++;
                rand[j + 1] = rand[j];
                j = j - 1;
            }
            rand[j + 1] = temp;
        }
        return rand;
    }
    
    public int[] quick(int[] n, int low, int high)
    {
        int temp;//used to swap values
        loopCounterQ++;
        compareCounterQ++;
        if (low < high){ //if the lowest index is lower than the highest index
            int pivot = n[high];// the pivot value that will be put in the exact right position
            int leftwall = low - 1;//the index of the smaller element
            for (int i = low; i <= high - 1; i++){
                loopCounterQ++;
                compareCounterQ++;
                if (n[i] <= pivot){ // if the current element is equal to or smaller than the pivot
                    leftwall++;//increases how many values have been determined smaller tha the pivot
                    //swap the current element with the element beside the elements that have been determined smaller than the pivot
                    shiftCounterQ++;
                    temp = n[leftwall];
                    n[leftwall] = n[i];
                    n[i] = temp;
                }
            }
            //puts the pivot inbetween the values that are larger, and smaller, putting it in the final position
            shiftCounterQ++;
            temp = n[leftwall + 1];
            n[leftwall + 1] = n[high];
            n[high] = temp;
            //recalls the method for the values that were determined smaller than the pivot
            quick(n, low, leftwall);
            //recalls the method for the values that were determined larger than the pivot
            quick(n, leftwall + 2, high);
        }
        return n;
    }

    public int[] quickDes(int[] n, int low, int high)
    {
        int temp;//used to swap values
        loopCounterQ++;
        compareCounterQ++;
        if (low < high){ //if the lowest index is lower than the highest index
            int pivot = n[high];// the pivot value that will be put in the exact right position
            int leftwall = low - 1;//the index of the larger element
            for (int i = low; i <= high - 1; i++){
                loopCounterQ++;
                compareCounterQ++;
                if (n[i] >= pivot){ // if the current element is equal to or greater than the pivot
                    leftwall++;//increases how many values have been determined greater than the pivot
                    //swap the current element with the element beside the elements that have been determined greater than the pivot
                    shiftCounterQ++;
                    temp = n[leftwall];
                    n[leftwall] = n[i];
                    n[i] = temp;
                }
            }
            //puts the pivot inbetween the values that are larger, and smaller, putting it in it's final position
            shiftCounterQ++;
            temp = n[leftwall + 1];
            n[leftwall + 1] = n[high];
            n[high] = temp;
            //recalls the method for the values that were determined larger than the pivot
            quickDes(n, low, leftwall);
            //recalls the method for the values that were determined smaller than the pivot
            quickDes(n, leftwall + 2, high);
        }
        return n;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupAmount = new javax.swing.ButtonGroup();
        btnGroupOrder = new javax.swing.ButtonGroup();
        pnlBackground = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblAmount = new javax.swing.JLabel();
        rBtn10 = new javax.swing.JRadioButton();
        rBtn100 = new javax.swing.JRadioButton();
        rBtn1000 = new javax.swing.JRadioButton();
        rBtn5000 = new javax.swing.JRadioButton();
        lblSortOrder = new javax.swing.JLabel();
        rBtnAscending = new javax.swing.JRadioButton();
        rBtnDescending = new javax.swing.JRadioButton();
        btnSortNumbers = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        outOriginalNumbers = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        outSortedNumbers = new javax.swing.JTextArea();
        lblOriginalNumbers = new javax.swing.JLabel();
        lblSortedNumbers = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        outSortResults = new javax.swing.JTextArea();
        lblSortResults = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBackground.setBackground(new java.awt.Color(204, 204, 204));

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 0));
        lblTitle.setText("Sorting Efficiencies");

        lblAmount.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAmount.setForeground(new java.awt.Color(0, 0, 0));
        lblAmount.setText("<html>Enter the amount of numbers<br>In the list:</html>");

        rBtn10.setBackground(new java.awt.Color(204, 204, 204));
        btnGroupAmount.add(rBtn10);
        rBtn10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rBtn10.setForeground(new java.awt.Color(0, 0, 0));
        rBtn10.setText("10");

        rBtn100.setBackground(new java.awt.Color(204, 204, 204));
        btnGroupAmount.add(rBtn100);
        rBtn100.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rBtn100.setForeground(new java.awt.Color(0, 0, 0));
        rBtn100.setText("100");

        rBtn1000.setBackground(new java.awt.Color(204, 204, 204));
        btnGroupAmount.add(rBtn1000);
        rBtn1000.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rBtn1000.setForeground(new java.awt.Color(0, 0, 0));
        rBtn1000.setText("1000");

        rBtn5000.setBackground(new java.awt.Color(204, 204, 204));
        btnGroupAmount.add(rBtn5000);
        rBtn5000.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rBtn5000.setForeground(new java.awt.Color(0, 0, 0));
        rBtn5000.setText("5000");

        lblSortOrder.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSortOrder.setForeground(new java.awt.Color(0, 0, 0));
        lblSortOrder.setText("Sort Order:");

        rBtnAscending.setBackground(new java.awt.Color(204, 204, 204));
        btnGroupOrder.add(rBtnAscending);
        rBtnAscending.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rBtnAscending.setForeground(new java.awt.Color(0, 0, 0));
        rBtnAscending.setText("Ascending");

        rBtnDescending.setBackground(new java.awt.Color(204, 204, 204));
        btnGroupOrder.add(rBtnDescending);
        rBtnDescending.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rBtnDescending.setForeground(new java.awt.Color(0, 0, 0));
        rBtnDescending.setText("Descending");

        btnSortNumbers.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnSortNumbers.setText("Sort Numbers");
        btnSortNumbers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortNumbersActionPerformed(evt);
            }
        });

        outOriginalNumbers.setEditable(false);
        outOriginalNumbers.setColumns(20);
        outOriginalNumbers.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        outOriginalNumbers.setRows(5);
        jScrollPane1.setViewportView(outOriginalNumbers);

        outSortedNumbers.setEditable(false);
        outSortedNumbers.setColumns(20);
        outSortedNumbers.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        outSortedNumbers.setRows(5);
        jScrollPane3.setViewportView(outSortedNumbers);

        lblOriginalNumbers.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblOriginalNumbers.setForeground(new java.awt.Color(0, 0, 0));
        lblOriginalNumbers.setText("Original Numbers:");

        lblSortedNumbers.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSortedNumbers.setForeground(new java.awt.Color(0, 0, 0));
        lblSortedNumbers.setText("Sorted Numbers:");

        outSortResults.setEditable(false);
        outSortResults.setColumns(20);
        outSortResults.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        outSortResults.setRows(5);
        jScrollPane2.setViewportView(outSortResults);

        lblSortResults.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSortResults.setForeground(new java.awt.Color(0, 0, 0));
        lblSortResults.setText("Sort Results:");

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rBtn5000)
                                    .addComponent(rBtn10))
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(btnSortNumbers))
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGap(80, 80, 80)
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rBtnAscending)
                                            .addComponent(lblSortOrder)
                                            .addComponent(rBtnDescending)))))
                            .addComponent(rBtn1000)
                            .addComponent(rBtn100)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblOriginalNumbers))
                                .addGap(83, 83, 83)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSortedNumbers)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(lblTitle)))
                .addGap(107, 107, 107)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSortResults)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblSortResults)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(29, 29, 29)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rBtn10)
                                    .addComponent(rBtnAscending))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rBtn100)
                                    .addComponent(rBtnDescending))
                                .addGap(18, 18, 18)
                                .addComponent(rBtn1000)
                                .addGap(18, 18, 18)
                                .addComponent(rBtn5000)
                                .addGap(67, 67, 67))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSortOrder))
                                .addGap(184, 184, 184)
                                .addComponent(btnSortNumbers)
                                .addGap(40, 40, 40)))
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOriginalNumbers)
                            .addComponent(lblSortedNumbers))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSortNumbersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortNumbersActionPerformed
        int totalNumbers = 0;
        if (rBtn10.isSelected() == true) totalNumbers = 10;
        if (rBtn100.isSelected() == true) totalNumbers = 100;
        if (rBtn1000.isSelected() == true) totalNumbers = 1000;
        if (rBtn5000.isSelected() == true) totalNumbers = 5000;
        
        //Arrays that all have the same random numbers to be sorted
        int[] randS = new int[totalNumbers];
        int[] randB = new int[totalNumbers];
        int[] randI = new int[totalNumbers];
        int[] randQ = new int[totalNumbers];
        int[] sortedNumbers = new int[totalNumbers];
        
        StringJoiner random = new StringJoiner("\n");
        StringJoiner sorted = new StringJoiner("\n");
        StringJoiner results = new StringJoiner("\n");
        
        //Adding the same random numbers to each array
        initRandom(randS, totalNumbers, random);
        copyArray(randS, randB);
        copyArray(randS, randI);
        copyArray(randS, randQ);
        
        long start, finish, elapsedS=0, elapsedB=0, elapsedI=0, elapsedQ=0;
        
        //Calls methods for each algorithm and times how long it takes to return
        if(rBtnAscending.isSelected() == true){
            start = System.currentTimeMillis();
            sortedNumbers = selection(randS);
            finish = System.currentTimeMillis();
            elapsedS = finish - start;
        
            start = System.currentTimeMillis();
            bubble(randB);
            finish = System.currentTimeMillis();
            elapsedB = finish - start;
        
            start = System.currentTimeMillis();
            insertion(randI);
            finish = System.currentTimeMillis();
            elapsedI = finish - start;
            
            start = System.currentTimeMillis();
            quick(randQ, 0, randQ.length-1);
            finish = System.currentTimeMillis();
            elapsedQ = finish - start;
        
            for (int i=0;i<sortedNumbers.length;i++){
                sorted.add(sortedNumbers[i]+"");
            }
        }   //Descending methods
        else if(rBtnDescending.isSelected() == true){
            start = System.currentTimeMillis();
            sortedNumbers = selectionDes(randS);
            finish = System.currentTimeMillis();
            elapsedS = finish - start;
        
            start = System.currentTimeMillis();
            bubbleDes(randB);
            finish = System.currentTimeMillis();
            elapsedB = finish - start;
        
            start = System.currentTimeMillis();
            insertionDes(randI);
            finish = System.currentTimeMillis();
            elapsedI = finish - start;
            
            start = System.currentTimeMillis();
            quickDes(randQ, 0, randQ.length-1);
            finish = System.currentTimeMillis();
            elapsedQ = finish - start;
        
            for (int i=0;i<sortedNumbers.length;i++){
                sorted.add(sortedNumbers[i]+"");
            }
        }
        //Results screen text
        results.add("Selection Sort\nNumber of times the loop was executed: "+
                loopCounterS+"\nNumber of times a comparison was made: "+
                compareCounterS+"\nNumber of times a value was shifted: "+
                shiftCounterS+"\nTime in Milliseconds to sort: "+elapsedS+
                "\nBubble Sort\nNumber of times the loop was executed: "+
                loopCounterB+"\nNumber of times a comparison was made: "+
                compareCounterB+"\nNumber of times a value was shifted: "+
                shiftCounterB+"\nTime in Milliseconds to sort: "+elapsedB+
                "\nInsertion Sort\nNumber of times the loop was executed: "+
                loopCounterI+"\nNumber of times a comparison was made: "+
                compareCounterI+"\nNumber of times a value was shifted: "+
                shiftCounterI+"\nTime in Milliseconds to sort: "+elapsedI+
                "\nQuick Sort\nNumber of times the loop was executed: "+
                loopCounterQ+"\nNumber of times a comparison was made: "+
                compareCounterQ+"\nNumber of times a value was shifted: "+
                shiftCounterQ+"\nTime in Milliseconds to sort: "+elapsedQ);
        
        outSortResults.setText(results.toString());
        outSortedNumbers.setText(sorted.toString());
        outOriginalNumbers.setText(random.toString());
        //Resets the counters back to 0 after being added to the text area
        loopCounterS=0; compareCounterS=0; shiftCounterS=0;
        loopCounterB=0; compareCounterB=0; shiftCounterB=0;
        loopCounterI=0; compareCounterI=0; shiftCounterI=0;
        loopCounterQ=0; compareCounterQ=0; shiftCounterQ=0;
    }//GEN-LAST:event_btnSortNumbersActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SortingEfficienciesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SortingEfficienciesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SortingEfficienciesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SortingEfficienciesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SortingEfficienciesUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupAmount;
    private javax.swing.ButtonGroup btnGroupOrder;
    private javax.swing.JButton btnSortNumbers;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblOriginalNumbers;
    private javax.swing.JLabel lblSortOrder;
    private javax.swing.JLabel lblSortResults;
    private javax.swing.JLabel lblSortedNumbers;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextArea outOriginalNumbers;
    private javax.swing.JTextArea outSortResults;
    private javax.swing.JTextArea outSortedNumbers;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JRadioButton rBtn10;
    private javax.swing.JRadioButton rBtn100;
    private javax.swing.JRadioButton rBtn1000;
    private javax.swing.JRadioButton rBtn5000;
    private javax.swing.JRadioButton rBtnAscending;
    private javax.swing.JRadioButton rBtnDescending;
    // End of variables declaration//GEN-END:variables
}
