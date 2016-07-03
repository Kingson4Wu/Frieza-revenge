package demo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * XML解析类
 * @author Administrator
 *
 */
public class XmlUtil implements Serializable {

	private static final long serialVersionUID = 4272705762921019405L;

	private static Logger log4j = Logger.getLogger(XmlUtil.class.getName());

	/**
	 * 加载xml文件
	 *
	 * @param fileName String 配置文件名.
	 * @param mapErrorMessage Map
	 * @return Document XML文档
	 */
	public static Document loadXML(String fileName,
			Map<String, Object> mapErrorMessage) {
		//判断文件名是否为null
		if (fileName == null) {
			fileName = "";
		}
		// 判断文件名长度是否为0
		if (fileName.length() == 0) {
		} else {
		}
		if (FileUtils.checkFile(fileName)) { //校验文件
			try {
				// 构建xml解析器
				SAXReader sr = new SAXReader();
				//读xml文件
				return sr.read(fileName);
			} catch (DocumentException ex) {
				mapErrorMessage.put("errorMessage", ex.getMessage());
				//显示文件错误信息
			}
		}
		return null;
	}

	public static Document loadXML(String fileName) {
		//判断文件名是否为null
		if (fileName == null) {
			return null;
		}
		// 判断文件名长度是否为0
		if (fileName.length() == 0) {
			return null;
		} 
		if (FileUtils.checkFile(fileName)) { //校验文件
			try {
				File file=new File(fileName);
				System.out.println(file.exists());
				// 构建xml解析器
				SAXReader sr = new SAXReader();
				//读xml文件
				return sr.read(file);
			} catch (DocumentException ex) {
				//显示文件错误信息
				log4j.error("load config fail!" + ex.getMessage(),ex);
			}
		}
		return null;
	}

	/**
	 * 保存xml文件
	 *
	 * @param document Document 文档.
	 * @param fileName String 配置文件名.
	 */
	public static void saveXML(Document document, String fileName) {
		// 判断目标文件名称是否为null
		if (fileName == null) {
			fileName = "";
		}
		// 判断目标文件名称长度是否为0
		if (fileName.length() == 0) {
//			log4j.info("File name length is 0");
		} else {
			//保存目标文件
			try {
				XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(
						fileName));
				xmlWriter.write(document);
				xmlWriter.close();
			} catch (Exception ex) {
				//设置映射文件错误信息
//				log4j.info(ex.getMessage());
			}
		}
	}

	/**
	 * 保存xml文件
	 *
	 * @param document Document 文档.
	 * @param fileName String 配置文件名.
	 * @param mapErrorMessage Map
	 */
	public static void saveXML(Document document, String fileName,
			Map<String, Object> mapErrorMessage) {
		// 判断目标文件名称是否为null
		if (fileName == null) {
			fileName = "";
		}
		// 判断目标文件名称长度是否为0
		if (fileName.length() == 0) {
			mapErrorMessage.put("errorMessage", "file name length is 0");
		} else {
			//保存目标文件
			try {
				XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(
						fileName));
				xmlWriter.write(document);
				xmlWriter.close();
			} catch (Exception ex) {
				//设置映射文件错误信息
				mapErrorMessage.put("errorMessage", ex.getMessage());
			}
		}
	}

	/**
	 * 取节点的名称
	 * @param element Element 节点
	 * @return String 节点值
	 */
	public static String getElementName(Element element) {
		String elementName = ""; // 定义节点名称变量
		//判断节点是否为null
		if (element == null) {
		} else {
			//取得节点名称
			elementName = element.getName();
			//判断节点名称是否为null
			if (elementName == null) {
				elementName = "";
			}
		}
		return elementName;
	}

	/**
	 * 取根节点
	 * @param document Document 文档
	 * @return Element 根节点
	 */
	public static Element getRootElement(Document document) {
		Element rootElement = null; //定义子节点变量

		// 判断节点是否为null
		if (document == null) {
		} else {
			// 取得子节点
			rootElement = document.getRootElement();
		}
		return rootElement;
	}

	/**
	 * 设置根节点
	 * @param document Document 文档
	 * @param rootElementName String 节点名称
	 * @return Element 根节点
	 */
	public static Element setRootElement(Document document,
			String rootElementName) {
		Element rootElement = null; //定义子节点变量

		// 判断节点是否为null
		if (document == null) {
		} else {
			// 取得子节点
			rootElement = document.addElement(rootElementName);
		}
		return rootElement;
	}

	/**
	 * 取节点的子节点
	 * @param element Element 节点
	 * @param childElementName String 子节点名称
	 * @return Element 子节点
	 */
	public static Element getElementChildElement(Element element,
			String childElementName) {
		Element childElement = null; //定义子节点变量

		// 判断子节点名称是否为null
		if (childElementName == null) {
			childElementName = "";
		}
		// 判断节点是否为null
		if (element == null) {
		} else {
			// 判断子节点名称长度是否为0
			if (childElementName.length() == 0) {
			} else {
				// 取得子节点
				childElement = element.element(childElementName);
			}
		}
		return childElement;
	}

	/**
	 * 设置节点的子节点
	 * @param element Element 节点
	 * @param childElementName String 子节点名称
	 * @return Element 子节点
	 */
	public static Element setElementChildElement(Element element,
			String childElementName) {
		Element childElement = null; //定义子节点变量

		// 判断子节点名称是否为null
		if (childElementName == null) {
			childElementName = "";
		}
		// 判断节点是否为null
		if (element == null) {
		} else {
			// 判断子节点名称长度是否为0
			if (childElementName.length() == 0) {
			} else {
				// 取得子节点
				childElement = element.addElement(childElementName);
				;
			}
		}
		return childElement;
	}

	/**
	 * 取节点的值
	 * @param element Element 节点
	 * @return String 节点值
	 */
	public static String getElementValue(Element element) {
		String elementValue = ""; // 定义节点值变量
		//判断节点是否为null
		if (element == null) {
		} else {
			//取得节点值
			elementValue = element.getTextTrim();
			//判断节点值是否为null
			if (elementValue == null) {
				elementValue = "";
			}
		}
		return elementValue;
	}

	/**
	 * 设置节点的值
	 * @param element Element 节点
	 * @param elementValue String 节点值
	 */
	public static void setElementValue(Element element, String elementValue) {
		// 判断节点是否为null
		if (element == null) {
		} else {
			// 判断节点值是否为null
			if (elementValue == null) {
				elementValue = "";
			}
			// 判断节点值长度是否为0
			if (elementValue.length() == 0) {
			} else {
				//设置节点值
				//element.addText("");
				element.setText(elementValue);

			}
		}
	}

	/**
	 * 取节点的属性
	 * @param element Element 节点
	 * @param attributeName String 属性名称
	 * @return String 属性值
	 */
	public static String getElementAttribute(Element element,
			String attributeName) {
		String attributeValue = ""; //定义节点属性值变量
		// 判断节点是否为null
		if (element == null) {
		} else {
			//判断节点属性名称是否为null
			if (attributeName == null) {
				attributeName = "";
			}
			//判断节点属性名称长度是否为0
			if (attributeName.length() == 0) {
			} else {
				// 取得节点属性值
				attributeValue = element.attributeValue(attributeName);
				//判断节点属性值是否为null
				if (attributeValue == null) {
					attributeValue = "";
				}
			}
		}
		return attributeValue;
	}

	/**
	 * 设置节点的属性
	 * @param element Element 节点
	 * @param attributeName String 属性名称
	 * @param attributeValue String 属性值
	 */
	public static void setElementAttribute(Element element,
			String attributeName, String attributeValue) {
		// 判断节点是否为null
		if (element == null) {
		} else {
			//判断节点属性值是否为null
			if (attributeValue == null) {
				attributeValue = "";
			}
			//判断节点属性名称是否为null
			if (attributeName == null) {
				attributeName = "";
			}
			//判断节点属性名称长度是否为0
			if (attributeName.length() == 0) {
			} else {
				//设置节点属性值
				element.addAttribute(attributeName, attributeValue);
			}
		}
	}

	/**
	 * xpath节点
	 * @param element Element 节点
	 * @param elementName String xpath的节点名称
	 * @return List 节点的List集
	 *
	 */
	@SuppressWarnings("unchecked")
	public static List selectElementNodes(Element element, String elementName) {
		List elementList = null; //定义源节点的List集变量
		// 判断xpath的节点名称是否为null
		if (elementName == null) {
			elementName = "";
		}
		// 判断节点是否为null
		if (element == null) {
		} else {
			// 不为null 在节点中取List集
			elementList = element.selectNodes(elementName);
		}
		return elementList;
	}

	/**
	 * xpath文档
	 * @param document Document 文档名称
	 * @param elementName String xpath的节点名称
	 * @return List 节点的List集
	 *
	 */
	@SuppressWarnings("unchecked")
	public static List selectElementNodes(Document document, String elementName) {
		List elementList = null; //定义源节点的List集变量
		// 判断xpath的节点名称是否为null
		if (elementName == null) {
			elementName = "";
		}
		// 判断节点是否为null
		if (document == null) {
		} else {
			// 不为null 在节点中取List集
			//System.out.println(elementName);
			try {
				elementList = document.selectNodes(elementName);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		//System.out.println(elementList);
		return elementList;
	}

	/**
	 * 设置节点名称
	 *
	 * @param element Element 节点
	 * @param elementName String 名称
	 */
	public static void setElementName(Element element, String elementName) {
		//判断节点名称是否为null
		if (elementName == null) {
			elementName = "";
		}
		//判断节点是否为null
		if (element == null) {
		} else {
			//取得节点名称
			element.setName(elementName);
		}
	}

	/**
	 * 添加子节点
	 *
	 * @param element Element 节点
	 * @param elementName String 名称
	 * @return Element
	 */
	public static Element addChildElement(Element element, String elementName) {
		Element childElement = null;
		//判断节点名称是否为null
		if (elementName == null) {
			elementName = "";
		}
		//判断节点是否为null
		if (element == null) {
		} else {
			//取得节点名称
			childElement = element.addElement(elementName);
		}
		return childElement;
	}

	/**
	 * 添加根节点
	 *
	 * @param document Element 节点
	 * @param elementName String 名称
	 * @return Element
	 */
	public static Element addRootElement(Document document, String elementName) {
		Element rootElement = null;
		//判断节点名称是否为null
		if (elementName == null) {
			elementName = "";
		}
		//判断节点是否为null
		if (document == null) {
		} else {
			//取得节点名称
			rootElement = document.addElement(elementName);
		}
		return rootElement;
	}

//	/**
//	 * 查找某个值是否存在
//	 * @return
//	 */
//	public static boolean selectNode(String str) {
//		SAXBuilder builder = new SAXBuilder();
//		Document doc = builder.build("kk.xml");
//		Element root = doc.getRootElement();
//
//		XPath xpath = XPath.newInstance("//电话[../姓名='张三'][@类型='家庭']");
//		List list = xpath.selectNodes(root);
//		Iterator iter = list.iterator();
//		while (iter.hasNext()) {
//			Element item = (Element) iter.next();
//			System.err.println(item.getText());
//		}
//
//	}

}
