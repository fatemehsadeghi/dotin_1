package calculateDeposit;

import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.lang.Class;
import java.math.BigDecimal;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DomParser {

    public static void parsXml() throws ParserException {
        int temp = 0;
        String customerNumber = "";
        BigDecimal depositBalance = new BigDecimal(1);
        int durationInDays=1;
        BigDecimal payedInterest = new BigDecimal(1);
        DepositType depositType = new DepositType();
        int interestRate;
        try {

            final int TIME = 36500;
            File InputFile = new File("input.txt");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(InputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("deposit");
            System.out.println(nList.getLength());
            //////////////////Start for/////////////////////////////////
            do {
                try {
                    if (temp < nList.getLength()) {
                        Node nNode = nList.item(temp);
                        System.out.println(nList.item(temp));
                        temp++;
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            customerNumber = eElement.getElementsByTagName("customerNumber").item(0).getTextContent();
                            System.out.println(customerNumber);
                            String depositTypeStr = eElement.getElementsByTagName("depositType").item(0).getTextContent();
                            System.out.println(depositTypeStr);
                            Class depositTypeClass = Class.forName(depositTypeStr);
                            String shortTermClassName = "calculateDeposit.ShortTerm";
                            String qarzClassName = "calculateDeposit.Qarz";
                            String longTermClassName = "calculateDeposit.LongTerm";
                            if (
                                    (!(depositTypeClass.getName().equals(longTermClassName))) &&
                                            (!(depositTypeClass.getName().equals(shortTermClassName))) &&
                                            (!(depositTypeClass.getName().equals(qarzClassName)))
                                    )
                                throw new ParserException(depositTypeClass + "class not found!");
                            depositType = (calculateDeposit.DepositType) depositTypeClass.newInstance();
                            interestRate = depositType.getInterestRate();
                            String depositBalanceStr = eElement.getElementsByTagName("depositBalance").item(0).getTextContent();
                            depositBalance = new BigDecimal(depositBalanceStr);
                            System.out.println(depositBalance);
                            if (depositBalance.signum() < 0)
                                throw new ParserException("depositBalance can`t be negative");
                            String durationInDaysStr = eElement.getElementsByTagName("durationInDays").item(0).getTextContent();
                            durationInDays = Integer.parseInt(durationInDaysStr);
                            System.out.println(durationInDays);
                            if (durationInDays <= 0)
                                throw new ParserException("durationInDays can`t be zero or negative");
                            Deposit deposit = new Deposit(customerNumber, depositBalance, durationInDays, depositType, payedInterest);
                            deposit.calculateInterest(depositBalance, interestRate, durationInDays, TIME);

                            deposit = new Deposit(customerNumber, depositBalance, durationInDays, depositType, payedInterest);
                            List<Deposit> depositList = new ArrayList<>();
                            depositList.add(deposit);
                            for (int i=0 ; i< 3 ; i++){
                                System.out.println(depositList.size()+"gholi");
                                System.out.println(depositList.indexOf(deposit));
                            }
                        }
                    }

                    Deposit deposit = new Deposit(customerNumber, depositBalance, durationInDays, depositType, payedInterest);
                    List<Deposit> depositList = new ArrayList<>();
                    System.out.println(depositList.size()+"//li");
                    for (int i=0 ; i< depositList.size() ; i++){
                        System.out.println(payedInterest+ "SSS");
                    }
                            Collections.sort(depositList);
                            // for( deposit : depositList){
                            System.out.println(deposit.getCustomerNumber() + " #" + deposit.getPayedInterest());



                        //}

                        //}
                        //}


                } catch (ParserException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (temp < nList.getLength());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {


        //try {
        parsXml();

        //todo print all deposit paid interests
        //}
        //catch (ParserException e) {
        //  System.out.println(e.getMessage());
        // e.printStackTrace();

        //}
        // catch (ClassNotFoundException e) {
        //   e.printStackTrace();
        //} catch (Exception e) {
        //  e.printStackTrace();
        //}
    }
}