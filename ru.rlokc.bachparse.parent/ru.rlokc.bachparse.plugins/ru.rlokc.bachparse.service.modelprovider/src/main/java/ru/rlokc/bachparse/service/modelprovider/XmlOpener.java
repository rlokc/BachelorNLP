package ru.rlokc.bachparse.service.modelprovider;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class XmlOpener {
	
	protected XMLStreamReader streamReader;

	public XmlOpener(String filePath) {
		this(new File(filePath));
	}
	
	public XmlOpener(File file) {
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		try {
			streamReader = xmlif.createXMLStreamReader(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
