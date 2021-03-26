package wechatOrder.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * 这个工具类用来将html格式传入的url如(<img src="https://..."/>)转换为正常的url("https://...")
 *
 * @author JJ
 * @date 2020/2/2 - 16:57
 */
public class ImgUrlUtils {


    /**
     * 将html中<img>的图片地址取出,拼接成url,url串
     * @param html
     * @return
     */
    public static String parse(String html) {
        StringBuilder sb= new StringBuilder();
        //判断图片url是否是以html格式传入的
        String[] split = html.split("<img");
        if(split.length>=2){
            Document document = Jsoup.parse(html);
            Elements imgs = document.getElementsByTag("img");
            for (int i=0;i<imgs.size();i++) {
                if(i==0){
                    sb.append(imgs.get(i).attr("src"));
                }else{
                    sb.append(","+imgs.get(i).attr("src"));
                }
            }
            return sb.toString();
        }else{
            return html;
        }
    }

    public static String packaging(String urls){
        String[] split = urls.split(",");
        StringBuilder sb = new StringBuilder();
        for (String url : split) {
            sb.append("<p><img width=\"220px\" height=\"100px\" src=\""+url+"\"/></p>");
        }
        return sb.toString();
    }
}
