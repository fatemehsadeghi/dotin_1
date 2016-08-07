package calculateDeposit;

/**
 * Created by fateme on 06/08/2016.
 */
        import java.io.File;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.DocumentBuilder;
        import org.w3c.dom.Document;
        import org.w3c.dom.NodeList;
        import org.w3c.dom.Node;
        import org.w3c.dom.Element;

public class DomParser {
    public static void main(String[] args){

        try {
            File inputFile = new File("input.txt");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("deposit");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :"
                        + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    eElement.getAttribute("rollno");
                    eElement.getElementsByTagName("custumerNumber").item(0).getTextContent();
                    eElement.getElementsByTagName("depositType").item(0).getTextContent();
                    eElement.getElementsByTagName("duration").item(0).getTextContent();
                    eElement.getElementsByTagName("marks").item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
