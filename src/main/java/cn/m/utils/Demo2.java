package cn.m.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Demo2 {


    private static final String strurl = "https://dytt8.net"; //拉取地址
    private static final String savepath = "E:/DownInternet/"; //获取到的a标签存储文件
    private static final Pattern p = Pattern.compile("<a .*href=.+</a>"); //匹配地址


    public static void main(String[] args) throws IOException {

        Map<String,String> urlMap = new HashMap<String,String>(); //二级页面地址
        List<String> urlList = getInternet(strurl);
        if(urlList.size() > 0){
            for(String string : urlList){
                urlMap.putAll(getParentInternet(string));
            }
        }
        if(urlMap.size() > 0){
            for(String key : urlMap.keySet()){
                System.out.println(key);
            }
        }
    }

    /**
     * 获得首页链接
     * @param strurl
     * @return
     * @throws IOException
     */
    private static List<String> getInternet(String strurl)throws IOException{
        URL url = new URL(strurl);
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));
        PrintWriter pw = new PrintWriter(new File(savepath + System.currentTimeMillis() + ".txt"));

        List<String> urlList = new ArrayList<String>(); //首页地址

        String line = null; //以行读取
        while ((line = br.readLine()) != null) {  //编写正则，匹配超链接地址
            if(line.indexOf("》") != -1 || line.indexOf("《") != -1 ){
                if(line.indexOf("游戏") == -1){
                    //   pw.println(line);  //如果地址包含a标签写入到文件
                    String currUrl = "https://dytt8.net"+line.substring(line.indexOf("<a href='")+9,line.indexOf("'>"));
                    pw.println(currUrl);
                    urlList.add(currUrl);
                }
            }
        }
        pw.close();
        br.close();
        return urlList;
    }


    /**
     * 获得第二页面信息
     * @param strurl
     * @return
     * @throws IOException
     */
    private static Map<String,String> getParentInternet(String strurl)throws IOException{
        URL url = new URL(strurl);
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));

        Map<String,String> urlMap = new HashMap<String,String>();

        String line = null; //以行读取
        while ((line = br.readLine()) != null) {  //编写正则，匹配超链接地址
            if(line.indexOf("ftp:") != -1){
                urlMap.put(line,line);
            }
        }
        br.close();
        return urlMap;
    }













//    public static void main(String[] args) throws IOException {
//
//        URL url = new URL(strurl);
//        URLConnection conn = url.openConnection();
//        InputStream is = conn.getInputStream();
//        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));
//
//        PrintWriter pw = new PrintWriter(new File(savepath + System.currentTimeMillis() + ".txt"));
//
//        String line = null; //以行读取
//        while ((line = br.readLine()) != null) {  //编写正则，匹配超链接地址
//
//            pw.println(line);  //如果地址包含a标签写入到文件
//            Matcher m = p.matcher(line);
//            while (m.find()) {
//                String href = m.group();
//                href = href.substring(href.indexOf("href="));
//                if (href.charAt(5) == '\"') {
//                    href = href.substring(6);
//                } else {
//                    href = href.substring(5);
//                }
//                try {
//                    href = href.substring(0, href.indexOf("\""));
//                } catch (Exception e) {
//                    try {
//                        href = href.substring(0, href.indexOf(" "));
//                    } catch (Exception e1) {
//                        href = href.substring(0, href.indexOf(">"));
//                    }
//                }
//                if (!href.startsWith("http:") || !href.startsWith("https:")) {
//                    System.out.println(href);
//                }
//
//            }
//
//        }
//        pw.close();
//        br.close();
//    }

}
