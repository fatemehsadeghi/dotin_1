package calculateDeposit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.*;
import java.lang.Class;
import java.math.BigDecimal;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import calculateDeposit.Exception.DepositBalanceCantBeNegativeException;
import calculateDeposit.Exception.DepositTypeSubClassNotFoundException;
import calculateDeposit.Exception.DurationInDaysCantBeNegativeAndZeroException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomParser {

    public static void parsXml() throws DurationInDaysCantBeNegativeAndZeroException
            , DepositBalanceCantBeNegativeException
            , DepositTypeSubClassNotFoundException,
            ClassNotFoundException {
        int temp = 0;
        String customerNumber;
        BigDecimal depositBalance;
        int durationInDays;
        BigDecimal payedInterest;
        DepositType depositType;
        int interestRate;
        try {
            final int TIME = 36500;
            File InputFile = new File("input.txt");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(InputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("deposit");
            List<Deposit> depositList = new ArrayList<>();
            do {
                try {
                    if (temp < nList.getLength()) {
                        Node nNode = nList.item(temp);
                        temp++;
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            customerNumber = eElement.getElementsByTagName("customerNumber").item(0).getTextContent();
                            String depositTypeStr = eElement.getElementsByTagName("depositType").item(0).getTextContent();
                            Class depositTypeClass;
                            try {
                                depositTypeClass = Class.forName(depositTypeStr);
                                depositType = (calculateDeposit.DepositType) depositTypeClass.newInstance();
                                interestRate = depositType.getInterestRate();
                            } catch (ClassNotFoundException e) {
                                throw new DepositTypeSubClassNotFoundException("");
                            }
                            String depositBalanceStr = eElement.getElementsByTagName("depositBalance").item(0).getTextContent();
                            depositBalance = new BigDecimal(depositBalanceStr);
                            if (depositBalance.signum() < 0)
                                throw new DepositBalanceCantBeNegativeException("depositBalance can`t be negative");
                            String durationInDaysStr = eElement.getElementsByTagName("durationInDays").item(0).getTextContent();
                            durationInDays = Integer.parseInt(durationInDaysStr);
                            if (durationInDays <= 0)
                                throw new DurationInDaysCantBeNegativeAndZeroException("durationInDays can`t be zero or negative");
                            Deposit deposit = new Deposit(customerNumber, depositBalance, durationInDays, depositType);
                            deposit.calculateInterest(depositBalance, interestRate, durationInDays, TIME);
                            depositList.add(deposit);
                        }
                    }
                } catch (DurationInDaysCantBeNegativeAndZeroException e) {
                    System.out.println(e.getMessage());
                } catch (DepositBalanceCantBeNegativeException e) {
                    System.out.println(e.getMessage());
                } catch (DepositTypeSubClassNotFoundException e) {
                    System.out.println("Class not found!");
                }
            }
            while (temp < nList.getLength());
            Collections.sort(depositList);
            List<String> customerNumPaidInterestList = new ArrayList<>();

            for (int i = 0; i < depositList.size(); i++) {

                Deposit deposit = depositList.get(i);
                System.out.println(deposit.getCustomerNumber() + "#" + deposit.getPayedInterest());
                customerNumPaidInterestList.add("\n" + deposit.getCustomerNumber() + "#" + deposit.getPayedInterest());
                try {

                    FileOutputStream outputFile = new FileOutputStream("Output.txt");
                    ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);
                    outputStream.writeUnshared(customerNumPaidInterestList);
                    outputStream.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, DurationInDaysCantBeNegativeAndZeroException, DepositTypeSubClassNotFoundException, DepositBalanceCantBeNegativeException, ClassNotFoundException {

        parsXml();
    }
}