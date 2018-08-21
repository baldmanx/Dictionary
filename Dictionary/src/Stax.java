import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Stax{

    public String StaxEx(String text, int count){

        boolean countCheck = false;
        final String fileName = "words.xml";
        String meaning = "Meaning" + count;
        String result = "";

        if (count > 1)
        {
            countCheck = true;
        }

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader xmlr = factory.newInstance().createXMLStreamReader(new FileInputStream(fileName));

            while (xmlr.hasNext()) {
                if (xmlr.next() == XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equalsIgnoreCase(text)) {

                    xmlr.next();

                    if (countCheck)
                    {
                        for (int i = 0; i < count - 1; i++)
                        {
                            xmlr.next();
                            xmlr.next();
                            xmlr.next(); // я не понял, как перейти от одного тэга к другому, но путём и3учения деталей
                            xmlr.next(); // элементов библиотеки для обработки XML и выводом их на экран выяснил, что
                        }                // этого точно можно добиться, если 4 ра3а вы3вать метод next.
                    }

                  if (xmlr.next() == XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equalsIgnoreCase(meaning)) {
                      if (xmlr.next() == XMLStreamConstants.CHARACTERS) {
                          result = xmlr.getText();
                      }
                  }
              }
            }

        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}