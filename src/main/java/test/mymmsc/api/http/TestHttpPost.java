package test.mymmsc.api.http;

import org.mymmsc.api.io.HttpClient;
import org.mymmsc.api.io.HttpResult;

import java.util.HashMap;
import java.util.Map;

public class TestHttpPost {

    public static void main(String[] args) {
        String str = "EhBUSk1iAAGlQgq8FpaOABf9IgPaydkyU01vemlsbGEvNS4wIChjb21wYXRpYmxlOyBNU0lFIDkuMDsgV2luZG93cyBOVCA2LjE7IFRyaWRlbnQvNS4wKSxnemlwKGdmZSksZ3ppcChnZmUpWjNodHRwOi8vc3BvcnRzLmlmZW5nLmNvbS9hLzIwMTQxMDI0LzQyMjg2OTc5XzAuc2h0bWxiBXpoX0NOaggItQgVAACAP2oICNMBFQAAQD9y1AIIARB4GNgEIg9GHiAnFg0ODxAREhMUGTQyjwEKHCorMzw/XF5xfoABggGQAZEBkgGcAbMBtgHGAcwB4QHiAeMB5AG0BOYB5wHoAekB7AHtAe4B/wGEAosCnQKvArsCvALFAssCzALOAs8C1gKeA6wDsAO5A70D2APaA9wD3QPgA+ED5QPmA+kD6gPxA/gDiASRBJYEmQSaBJsEngSfBKYEpwSpBLYErASyBDoIExcKAwUYBxJKEBDl9tWnCSjA7m06BDDA7m1SGXBhY2stYnJhbmQt5Yek5Yew572ROjpBbGxSL3BhY2stYnJhbmQt5Yek5Yew572ROjrlm77niYfpobUsIOWkmuS4quS9jee9riAyYAJqJptOqE7lTudO6U7rTplP1VHpUYxSsFOVW7NctFzXaq9O+k7vaP5pcICnq+oLee3ECLag6MDIiAEAkAEAmAEAeACgAQGqARtDQUVTRURFYTFJZ25wVXltczBxY1dwNmVQTTDAAQLIAeAD0gECGij4AYfSBqACHrgCiYA/yALjFtEC/r6dIHgrIis=";
        str = "{\"creative_trade_id\":987654321}";
        str = "";
        String sUrl = "http://119.254.111.25:8080/rrc/debtor/getCollectionProgressList.cgi";
        System.out.println(sUrl.length() + ", " + sUrl);
        HttpClient hc = new HttpClient(sUrl, 30);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        hc.addField("a", "123");
        HttpResult hRet = hc.post(null, str);
        System.out.println("http-status=[" + hRet.getStatus() + "], body=["
                + hRet.getBody() + "], message=" + hRet.getError());
    }
}
