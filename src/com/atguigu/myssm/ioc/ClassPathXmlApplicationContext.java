package com.atguigu.myssm.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类是使用XML进行存储并解析的一个功能型类
 */
public class ClassPathXmlApplicationContext implements BeanFactory{
    private Map<String,Object> beanMap = new HashMap<>();
    //通过对xml进行解析得到了类全名
    //具体解析步骤如下:
    public ClassPathXmlApplicationContext(){
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");

            //创建DocumentBuilderFactory对象，为创建DocumentBuilder类做准备
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //使用DocumentBuilderFactory进行创建DocumentBuilder
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //使用DocumentBuilder对利用反射获取到的inputStream进行获取以及解析。
            //从而创建了org.w3c.dom.Document的类对xml文件进行解析
            Document document = documentBuilder.parse(inputStream);
            //然后根据我们所写的<beans><bean id="" class="com.atguigu.xxx"></beans>
            //我们需要获取的是TagName为bean的所以获取到这个List数组
            NodeList beanNodeList = document.getElementsByTagName("bean");

            //遍历整个数组
            for(int i = 0; i < beanNodeList.getLength(); i++){
                //获取但个元素的节点
                Node beanNode = beanNodeList.item(i);
                //判断这个元素的节点类型（NodeType）是否是元素节点
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    //如果是则进入这里将beanNode强转成org.w3c.dom.Element类型
                    Element beanElement = (Element)beanNode;
                    //获取id的值
                    String beanId = beanElement.getAttribute("id");
                    //获取class的值
                    String className = beanElement.getAttribute("class");

                    //因为我们需要一个容器好对上面传下来的地址进行对应类名
                    //所以当我们配置好xml进行读取时需要一个Map容器用来String与Object的对应
                    //然而JDK15的新特性不建议使用Class.forName(className).newInstance()
                    //这种类型的做法，所以使用先获取构造函数，然后用构造函数进行newInstance()的做法。

                    //这里老师所写的是Class beanClass = Class.forName(className);
                    //Object beanObj = beanClass.newInstance();
                    Constructor constructor = Class.forName(className).getConstructor();
                    Object beanObj = constructor.newInstance();

                    //这里开始建立id与newInstance()对象进行联系。
                    beanMap.put(beanId,beanObj);
                    //到此处为止bean与bean之间的依赖关系还没有设置
                }
            }
            //因为需要对bean的依赖关系需要重新在这个类里面做完。
            //组装bean之间的依赖关系

            //需要重新进行for循环
            for(int i = 0; i < beanNodeList.getLength(); i++){

                Node beanNode = beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    //这里还是需要获取该节点的id值
                    String beanId = beanElement.getAttribute("id");
                    //而组装关系是因为节点是这样类型的：
                    //<bean id="ordersService" class="com.atguigu.model.service.OrdersServiceImpl">
                    //        <property name="ordersDao" ref="ordersDao"/>
                    //    </bean>
                    //我们对其<property>这个标签感兴趣，所以需要获取所有的子节点
                    NodeList childNodeList = beanElement.getChildNodes();
                    for(int j = 0; j < childNodeList.getLength();j++){
                        Node childNode = childNodeList.item(j);
                        //判断是否是<property>这个标签
                        if(childNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(childNode.getNodeName())){
                            Element propertyElement = (Element) childNode;
                            //获取其name属性
                            String propertyName = propertyElement.getAttribute("name");
                            //获取其ref属性
                            String propertyRef = propertyElement.getAttribute("ref");
                            //我们所想的是在其ordersService的类下的某个属性，其属性名为ordersDao的属性赋上ref(即另一个id)为ordersDao的类
                            //所以我们进行了这样的获取Object，分别获取ref下代表的类以及上面所获得的beanId的类
                            Object refObj = beanMap.get(propertyRef);
                            Object beanObj = beanMap.get(beanId);
                            //利用反射技术，将属性名为ordersDao的值进行赋值
                            Class beanClazz = beanObj.getClass();
                            Field propertyField = beanClazz.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);
                        }
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SAXException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
