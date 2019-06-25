package com.exercise.domXmlTest;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @Author guojianmin
 * @@Description : 生成 XML 文件
 */
public class DomXml {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        createXml();
        System.out.println("运行时间："+ (System.currentTimeMillis() - start));
    }

    /**
     * 生成xml方法
     */
    public static void createXml(){
        try {
            // 创建解析器工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document document = db.newDocument();
            // 不显示standalone="no"
            document.setXmlStandalone(true);
            Element bookstore = document.createElement("bookstore");
            // 向bookstore根节点中添加子节点book
            Element book = document.createElement("book");

            Element name = document.createElement("name");
            // 不显示内容 name.setNodeValue("不好使");
            name.setTextContent("雷神");
            book.appendChild(name);
            // 为book节点添加属性
            book.setAttribute("id", "1");
            // 将book节点添加到bookstore根节点中
            bookstore.appendChild(book);
            // 将bookstore节点（已包含book）添加到dom树中
            document.appendChild(bookstore);

            // 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();
            // 创建 Transformer对象
            Transformer tf = tff.newTransformer();

            // 输出内容是否使用换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            // 创建xml文件并写入内容
            tf.transform(new DOMSource(document), new StreamResult(new File("D:\\book1.xml")));
            System.out.println("生成book1.xml成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成book1.xml失败");
        }
    }
}