package mdmatrakas.compGrafica.util;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XmlService {
    public static Object unmarshal(Class<?> clazz, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller un = context.createUnmarshaller();

            // Read from File
            Object d = un.unmarshal(file);
            return d;
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object unmarshal(File file) {
        try {
            // create context with ":" separated list of packages that contain your JAXB ObjectFactory classes
            JAXBContext context = JAXBContext
                    .newInstance("com.mdmatrakas.foztrans_bd.data_model:com.mdmatrakas.foztrans_bd.data_model.xml_util");
            Unmarshaller un = context.createUnmarshaller();

            // Read from File
            Object d = un.unmarshal(file);
            return d;
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object unmarshal(File file, String packages) {
        try {
            // create context with ":" separated list of packages that contain your JAXB ObjectFactory classes
            JAXBContext context = JAXBContext.newInstance(packages);
            Unmarshaller un = context.createUnmarshaller();

            // Read from File
            Object d = un.unmarshal(file);
            return d;
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void marshal(Object obj, File file) {
        if (obj == null)
            return;

        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller m = context.createMarshaller();
            // for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            // m.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //
            // standalone=\"no\"?>");

            // Write to File
            m.marshal(obj, file);
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Object unmarshal(Class<?> clazz, String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller un = context.createUnmarshaller();
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xml));

            // Read from String
            Object d = un.unmarshal(reader);
            return d;
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
        catch (FactoryConfigurationError e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object unmarshal(String xml) {
        try {
            // create context with ":" separated list of packages that contain your JAXB ObjectFactory classes
            JAXBContext context = JAXBContext
                    .newInstance("com.mdmatrakas.foztrans_bd.data_model:com.mdmatrakas.foztrans_bd.data_model.xml_util");
            Unmarshaller un = context.createUnmarshaller();
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xml));

            // Read from String
            Object d = un.unmarshal(reader);
            return d;
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
        catch (FactoryConfigurationError e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object unmarshal(String xml, String packages) {
        try {
            // create context with ":" separated list of packages that contain your JAXB ObjectFactory classes
            JAXBContext context = JAXBContext.newInstance(packages);
            Unmarshaller un = context.createUnmarshaller();
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new StringReader(xml));

            // Read from String
            Object d = un.unmarshal(reader);
            return d;
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
        catch (FactoryConfigurationError e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String marshal(Object obj, boolean xmlHeader) {

        if (obj == null)
            return "";

        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller m = context.createMarshaller();
            // for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // configure no xml header
            // <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            if (xmlHeader)// show
                m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
            else {// hide
                m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                // m.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //
                // standalone=\"no\"?>");
                // m.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
            }

            StringWriter stringWriter = new StringWriter();

            // Write to String
            m.marshal(obj, stringWriter);
            return stringWriter.toString();
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
