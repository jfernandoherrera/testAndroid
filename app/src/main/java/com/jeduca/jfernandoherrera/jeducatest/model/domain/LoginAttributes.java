package com.jeduca.jfernandoherrera.jeducatest.model.domain;


public class LoginAttributes {

    public static final String uri = "http://190.144.240.54/WebServiceApp/ServiceWebApp.asmx";
    public static final String userExists = "UsuarioExiste";


    public static String loginXml(String usuario, String contrasena) {

        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <autenticarUsuario xmlns=\"http://jeduca.com/CondorWebService/\">\n" +
                "      <usuario>" + usuario + "</usuario>\n" +
                "      <contrasena>"+contrasena+"</contrasena>\n" +
                "    </autenticarUsuario>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
    }
}
