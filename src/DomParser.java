import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class DomParser {

  public static List<Paper> grabXMLContent() {
    try {
      // Get the DOM Builder Factory
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      // Get the DOM Builder
      DocumentBuilder builder = factory.newDocumentBuilder();
      // Load and Parse the XML document
      // document contains the complete XML as a Tree
      Document document = builder.parse(ClassLoader.getSystemResourceAsStream("dblp-soc-papers.xml"));
      // Iterating through the nodes and extracting the data
      NodeList nodeList = document.getDocumentElement().getChildNodes();
      List<Paper> paperList = new ArrayList<>();
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        if (node instanceof Element) {
          // We have encountered an <inproceedings> or <article> tag
          Paper p = new Paper();
          p.authors = new ArrayList<>();
          p.mdate = node.getAttributes().getNamedItem("mdate").getNodeValue();
          p.key = node.getAttributes().getNamedItem("key").getNodeValue();
          NodeList childNodes = node.getChildNodes();
          for (int j = 0; j < childNodes.getLength(); j++) {
            Node cNode = childNodes.item(j);
            // Identifying the child tag of <inproceedings> or <article> encountered
            if (cNode instanceof Element) {
              // (The line below stores the text between the opening tag and the closing tag.)
              String content = cNode.getLastChild().getTextContent().trim();

              switch (cNode.getNodeName()) {
                case "author":
                  System.out.println(content);
                  p.authors.add(content) ;
                  break;
                case "editor":
                  p.editor = content;
                  break;
                case "title":
                  p.title = content;
                  break;
                case "booktitle":
                  p.booktitle = content;
                  break;
                case "pages":
                  p.pages = content;
                  break;
                case "year":
                  p.year = content;
                  break;
                case "address":
                  p.address = content;
                  break;
                case "journal":
                  p.journal = content;
                  break;
                case "volume":
                  p.volume = content;
                  break;
                case "number":
                  p.number = content;
                  break;
                case "month":
                  p.month = content;
                  break;
                case "url":
                  p.url = content;
                  break;
                case "ee":
                  p.ee = content;
                  break;
                case "cdrom":
                  p.cdrom = content;
                  break;
                case "cite":
                  p.cite = content;
                  break;
                case "publisher":
                  p.publisher = content;
                  break;
                case "note":
                  p.note = content;
                  break;
                case "crossref":
                  p.crossref = content;
                  break;
                case "isbn":
                  p.isbn = content;
                  break;
                case "series":
                  p.series = content;
                  break;
                case "school":
                  p.school = content;
                  break;
                case "chapter":
                  p.chapter = content;
                  break;
                case "publnr":
                  p.publnr = content;
                  break;
              }
            }
          }
          paperList.add(p);
        }
      }
      return paperList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}