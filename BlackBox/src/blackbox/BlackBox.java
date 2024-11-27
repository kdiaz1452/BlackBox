/*Compile from PowerShell Terminal

cd "C:\Users\kdiaz\OneDrive\Documents\School\ESU\Fall 2024\CPSC 445\BlackBox\BlackBox\src"
javac blackbox/*.java
java blackbox.BlackBox
*/
package blackbox;

public class BlackBox {

    public static void main(String[] args) {
        // TODO code application logic here

        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null); // centers frame
        
    }

}
