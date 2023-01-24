package com.atguigu.web;

import com.atguigu.myssm.io.BeanFactory;
import com.atguigu.myssm.io.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    private BeanFactory beanFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        beanFactory = new ClassPathXmlApplicationContext();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //我请求的是：http://localhost:8081/hello.do?m=selectOrders
        //1.这里获取的是/hello.do
        String servletPath = req.getServletPath();
        //2.获得到/hello.do 我希望把他变成 hello
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0,lastDotIndex);
        //3.我希望hello->HelloController能进行对应
        //5.已经将我们在xml配置好的id和class对应上后以及解析完成后，则可以获得这个类
        Object controllerBeanObj = beanFactory.getBean(servletPath);
        //6.而后我们需要继续获取m这个变量进行
        String m = req.getParameter("m");
        try {
            //对获取到的m的method
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for(Method method : methods){
                if(m.equals(method.getName())){
                    //统一获取参数
                    //首先获取方法中的所有的参数
                    Parameter[] parameters = method.getParameters();
                    //并且创建同等长度的数组用于存放值
                    Object[] parametersValue = new Object[parameters.length];
                    for(int i = 0;i < parameters.length; i++){
                        String parameterName = parameters[i].getName();
                        Object parameterValue = null;
                        if("req".equals(parameterName)){
                            parameterValue = req;
                        } else if ("resp".equals(parameterName)) {
                            parameterValue = resp;
                        } else if ("session".equals(parameterName)) {
                            parameterValue = req.getSession();
                        }else{
                            String typeName = parameters[i].getType().getTypeName();
                            String parameterValueStr = req.getParameter(parameterName);
                            if("java.lang.Integer".equals(typeName)){
                                parameterValue = Integer.parseInt(parameterValueStr);
                            }else{
                                parameterValue = parameterValueStr;
                            }
                        }
                        parametersValue[i] = parameterValue;
                    }
                    //设定访问权限
                    method.setAccessible(true);
                    //调用，并且将视图的处理利用字符串进行返回
                    Object returnObj = method.invoke(controllerBeanObj,parametersValue);
                    //统一进行视图的处理
                    if(returnObj != null){
                        String methodReturnStr = (String) returnObj;
                        //如果是重定向开头，则进行重定向
                        if(methodReturnStr.startsWith("redirect:")){
                            String redirectStr = methodReturnStr.substring("redirect:".length());
                            resp.sendRedirect(redirectStr);
                        } else if (methodReturnStr.startsWith("ajax:")) {//若ajax开头则进行返回ajax字符串
                            String ajaxStr = methodReturnStr.substring("ajax:".length());
                            resp.getWriter().write(ajaxStr);
                        }else{
                            //若没有开头，则进行内部的转发
                            req.getRequestDispatcher(methodReturnStr).forward(req,resp);
                        }
                    }
                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
