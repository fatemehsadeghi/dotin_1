package calculateDeposit;

import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.lang.Class;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import com.sun.org.apache.bcel.internal.generic.GOTO;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import static java.sql.Types.TIME;
public class DomParser {
    public static void parsXml() throws Exception {
        BigDecimal payedInterest = new BigDecimal(1);
        final int TIME = 36500;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        //Document doc = dBuilder.parse(inputFile);
        Document doc = dBuilder.parse("C:\\Users\\fateme\\IdeaProjects\\dotin_1\\Input.txt");
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("deposit");
        System.out.println(nList.getLength());
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println(nList.item(temp));
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String customerNumber = eElement.getElementsByTagName("customerNumber").item(0).getTextContent();
                System.out.println(customerNumber);
                String depositTypeStr = eElement.getElementsByTagName("depositType").item(0).getTextContent();
                System.out.println(depositTypeStr);
                //calculateDeposit.DepositType depositType = (calculateDeposit.DepositType) Class.forName(depositTypeStr).newInstance();
                Class depositType1 = Class.forName(depositTypeStr);
                //if(c !=  ) throw new ClassNotFoundException();
                calculateDeposit.DepositType depositType = (calculateDeposit.DepositType) depositType1.newInstance();
                int interestRate = depositType.getInterestRate();
                System.out.println(interestRate);
                //depositType.setInterestRate();
                String depositBalanceStr = eElement.getElementsByTagName("depositBalance").item(0).getTextContent();
                BigDecimal depositBalance = new BigDecimal(depositBalanceStr);
                if (depositBalance.signum() < 0) throw new Exception("depositBalance can`t be negative");
                System.out.println(depositBalance);
                String durationInDaysStr = eElement.getElementsByTagName("durationInDays").item(0).getTextContent();
                int durationInDays = Integer.parseInt(durationInDaysStr);
                if (durationInDays <= 0) throw new Exception("durationInDays can`t be zero or negative");
                System.out.println(durationInDays);
                Deposit deposit = new Deposit(customerNumber, depositBalance, durationInDays, depositType, payedInterest);
                deposit.calculateInterest(depositBalance, interestRate, durationInDays, TIME);
                List<Deposit> depositList = new ArrayList<Deposit>();
                //deposit = new Deposit(customerNumber, depositBalance, durationInDays, depositType , payedInterest);
                depositList.add(deposit);
                System.out.println(depositList);
            }
           // DepositType deposit;
           // List<Deposit> depositList = new ArrayList<Deposit>();
            //depositList.sort(payedInterest, new Comparator<Deposit>() {
              //          @Override
                //        public int compare(Deposit deposit1, Deposit deposit2) {
                  //          return deposit2.getPayedInterest().compareTo(deposit1.getPayedInterest());
                    //    }
                //}




        }}






    public static void main(String[] args)  {


        try {
            parsXml();

            //depositList.add((Deposit) depositList);
            //Contacts.sort();
            //todo print all deposit paid interests
        } catch (Exception e) {
           System.out.println(e.getMessage());
              e.printStackTrace();
        }
        //finally {
          //  System.out.println("finaly");
            //parsXml();
           // while (int temp < nList.getLength() )
               // continue;

        //}
    }
}