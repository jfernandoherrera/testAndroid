package com.jeduca.jfernandoherrera.jeducatest.model.context.remote;

import com.jeduca.jfernandoherrera.jeducatest.model.domain.LoginAttributes;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class LoginRemote {

    private final int OK = 200;

    public Boolean login(String usuario, String contrasena) {

        String SOAPRequestXML = LoginAttributes.loginXml(usuario, contrasena);

        String uri = LoginAttributes.uri;

        HttpPost httppost = new HttpPost(uri);

        StringEntity se = null;

        try {

            se = new StringEntity(SOAPRequestXML, HTTP.UTF_8);

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

        }

        se.setContentType("text/xml");

        httppost.setHeader("Content-Type","text/xml");

        httppost.setEntity(se);

        HttpClient httpclient = new DefaultHttpClient();

        BasicHttpResponse httpResponse = null;

        try {

            httpResponse = (BasicHttpResponse) httpclient.execute(httppost);

        } catch (IOException e) {

            e.printStackTrace();

        }

        HttpEntity entity = httpResponse.getEntity();

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder = null;

        try {

            docBuilder = docBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {

            e.printStackTrace();

        }

        String content = null;

        try {

            content = EntityUtils.toString(entity);

        } catch (IOException e) {

            e.printStackTrace();

        }

        Document doc = null;

        try {

            doc = docBuilder.parse(new InputSource( new StringReader( content )));

        } catch (SAXException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        Element root = doc.getDocumentElement();

        String userExists = LoginAttributes.userExists;

        NodeList nameNodesList = doc.getElementsByTagName(userExists);

        String value = nameNodesList.item(0).getTextContent();

        boolean isOk = (OK == httpResponse.getStatusLine().getStatusCode());

        boolean userExistsValue = (value.equals("true"));

        return isOk && userExistsValue;

    }

}
