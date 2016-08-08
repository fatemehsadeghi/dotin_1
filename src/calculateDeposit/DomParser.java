package calculateDeposit;

/**
 * Created by fateme on 06/08/2016.
 */

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class DomParser {

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*Deposit deposit = new Deposit();
        boolean isDeposit = true;
        String className;
        if (isDeposit) {
            className = "Deposiit";
        } else {
            className = "DepositType";
        }
        Deposit deposit1 = (Deposit) Class.forName(className).newInstance();

        if (className.equals("Deposit")) {
            Deposit deposit2 = new Deposit();
        } else if (className.equals("DepositType")) {
            DepositType depositType = new DepositType();
        }
        */

        BigDecimal payedInterest = null;
        int interestRate = 0;
        int time = 36500;
        try {
            File inputFile = new File("Input.txt");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("deposit");
            System.out.println(nList.getLength());
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println(nList.item(temp));
                System.out.println(" \nCurrent Element :"
                        + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //eElement.getAttribute("rollno");
                    String customerNumber = eElement.getElementsByTagName("customerNumber").item(0).getTextContent();

                    String className = eElement.getElementsByTagName("depositType").item(0).getTextContent();
                    DepositType depositType = (DepositType) Class.forName(className).newInstance();
                    String value = eElement.getElementsByTagName("depositBalance").item(0).getTextContent();
                    BigDecimal depositBalance = new BigDecimal(value);
                    String value2 = eElement.getElementsByTagName("durationInDays").item(0).getTextContent();
                    int durationInDays = Integer.parseInt(value2);
                    Deposit d = new Deposit();
                    d.calculateInterest(payedInterest, depositBalance, interestRate, durationInDays, time);
                    System.out.println(payedInterest);
                    List<Deposit> Contacts = new ArrayList<Deposit>();
                    Deposit objt = new Deposit(customerNumber, payedInterest, depositBalance, durationInDays); // Creating a new object
                    Contacts.add(objt);
                    Contacts.sort();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
