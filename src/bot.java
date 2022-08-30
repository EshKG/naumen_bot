
//import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardUpLeftHandler;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
//import java.io.PrintWriter;
//import java.io.ObjectInputFilter.Status;
//import java.beans.FeatureDescriptor;
//import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.*;
import org.json.JSONObject;
import org.json.JSONArray;
//import org.json.*;
//import java.util.*;
import org.jsoup.nodes.*;
import org.jsoup.Jsoup;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.select.Elements;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.*;



/*class EdurekaLogger {
    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public void sampleLog()
    {
        LOGGER.log(Level.WARNING, "Welcome to Edureka!");
    }
}*/


class bot {/* ОТПРАВЛЯЕМ СООБЩЕНИЕ БОТУ */



    public static InputStream botAPI(String text) throws IOException {




        String token = "your_token";//your_token-input your bot-token

        String chat_id = "chat_id";//input your telegram chat_id


        String urlbotAPI = String.format(
                "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&parse_mode=HTML&text=%s",
                token,
                chat_id, text);

        URL urlbot = new URL(urlbotAPI);
        HttpURLConnection httpconnbot = (HttpURLConnection) urlbot.openConnection();

        httpconnbot.setRequestMethod("GET");

        InputStream responseStream = httpconnbot.getResponseCode() / 100 == 2
                ? httpconnbot.getInputStream()
                : httpconnbot.getErrorStream();
        return responseStream;

    }

    static String APIrequest(String urlAPI, String credits) throws IOException {

        URL url = new URL(urlAPI);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        byte[] message = (credits).getBytes("UTF-8");
        String basicAuth = DatatypeConverter.printBase64Binary(message);
        httpConn.setRequestProperty("Content-Type", "application/json");
        httpConn.setRequestProperty("Authorization", "Basic " + basicAuth);

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();

        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";

        return response;
    }

    public static String Status(String text) {// Vozvrashaet status
        String status = "";

        if (text.contains("normal")) {
            status = "normal";
        } else if (text.contains("available")) {
            status = "available";
        } else if (text.contains("away")) {
            status = "away";
        } else if (text.contains("dnd")) {
            status = "dnd";
        } else if (text.contains("wrapup")) {
            status = "wrapup";
        } else if (text.contains("speaking")) {
            status = "speaking";
        } else if (text.contains("ringing")) {
            status = "ringing";
        } else if (text.contains("offline")) {
            status = "offline";
        }
        return status;
    }




    // static String ProblemOp(String KovoOtmechaem, String Status,int Hours,int
    // Minutes,int Seconds, String fio,String ObsheeVremya){
    static String ProblemOp1(String Status, int Hours, int Minutes, int Seconds, String fio, String ObsheeVremya) {// Собираем
        // данные
        // по
        // операторам
        // в
        // паузах,без
        // звонков
        // и
        // долго
        // на
        // посту

        String a = "";
        if (Status.equals("wrapup") && (Seconds > 15 || Minutes > 1 || Hours > 0)) {

            // a=department +": "+ fio + " "+ "Длительность"+ ObsheeVremya+" ";
            a ="%0A" + fio + " " + "Длительность: " + ObsheeVremya + "  ";

        } else if ((Status.equals("away") || Status.equals("dnd")) && (Minutes > 10)
                || (Minutes == 10 && Seconds > 5)|| Hours>0) {
            // a=department +": "+ fio + " "+ "Длительность"+ ObsheeVremya+" ";
            a = "%0A" +fio + " " + "Длительность: " + ObsheeVremya + "  ";

        } else if ((Status.equals("normal") || Status.equals("available"))
                && (Minutes > 3 || (Minutes == 3 && Seconds > 0) || Hours>0)) {

            // a=department +": "+ fio + " "+ "Длительность"+ ObsheeVremya+" ";
            a = "%0A" +fio + " " + "Длительность: " + ObsheeVremya + "  ";

        }

        return a;
    }



    static String speaking = "speaking";



    static boolean StatusOtdel(String text, String StatusObshie) {
        boolean a = false;
        if (Status(text).equals(StatusObshie) || Status(text).equals(StatusObshie)) {
            a = true;
        }
        return a;
    }

    static void //HashMap <String,ArrayList<String>>
    addToMap(String Ploshadka,HashMap<String,HashMap<String,Integer>> Hash){

        HashMap<String,Integer> kolichestvo = new HashMap<String,Integer>();
        kolichestvo.put("Свободные",0);
        kolichestvo.put("На паузе",0);
        kolichestvo.put("На посту",0);
        kolichestvo.put("В разговоре",0);

        Hash.put(Ploshadka,kolichestvo);

    }





    static void //HashMap <String,ArrayList<String>>
    takeAll(String ou,HashMap<String,HashMap<String,Integer>> All,//HashMap <String,ArrayList<String>> ProblemOp,
            HashMap<String,HashMap<String,ArrayList<String>>> ProblemAll,//HashMap <HashMap<String,Integer>,ArrayList<String>> obshiyMap,
            String text,
            int statustime,int statustimemin,int statustimesec,String fio,String timestatus

    ){
        // ArrayList<String> otdeli=new ArrayList<String> ();
        //  otdeli.addAll(otdeli1());
        String normal = "normal";
        String available = "available";
        String away = "away";
        String dnd = "dnd";
        String wrapup = "wrapup";
        String speaking = "speaking";

        for (int i = 0; i<otdeli1().size();i++){
            if (ou.equals(otdeli1().get(i))){
                //System.out.println(ou+otdeli1().get(i));
                String Ploshadka = ouname().get(ou);
                //System.out.println(Ploshadka+otdeli1().get(i));
                if (StatusOtdel(Status(text), normal)
                        || StatusOtdel(Status(text), available)) {//System.out.print("Сравнение uuid и statsuuid");

                    All.get(Ploshadka).put("Свободные",All.get(Ploshadka).get("Свободные")+1);


                    // ProblemOp.put(Ploshadka,ProblemOp.get(Ploshadka).put(nanormal,ProblemOp1(Status(text), statustime, statustimemin, statustimesec, fio, timestatus)));
                    if(!ProblemOp1(Status(text), statustime, statustimemin, statustimesec, fio, timestatus).equals("")){
                        ProblemAll.get(Ploshadka).get(nanormal).add(ProblemOp1(Status(text), statustime, statustimemin, statustimesec, fio, timestatus));


                    }

                } else if (StatusOtdel(Status(text), away)
                        || StatusOtdel(Status(text), dnd)) {

                    All.get(Ploshadka).put("На паузе",All.get(Ploshadka).get("На паузе")+1);
                    if(!ProblemOp1(Status(text), statustime, statustimemin, statustimesec, fio, timestatus).equals("")){
                        ProblemAll.get(Ploshadka).get(napause).add(ProblemOp1(Status(text), statustime, statustimemin, statustimesec, fio, timestatus));

                    }

                } else if (StatusOtdel(Status(text), wrapup)) {


                    All.get(Ploshadka).put("На посту",All.get(Ploshadka).get("На посту")+1);
                    if(!ProblemOp1(Status(text), statustime, statustimemin, statustimesec, fio, timestatus).equals("")){

                        ProblemAll.get(Ploshadka).get(napost).add(ProblemOp1(Status(text), statustime, statustimemin, statustimesec, fio, timestatus));

                    }

                }
                else if (StatusOtdel(Status(text), speaking)) {
                    //  number_of_speaking++;
                    HashMap<String,Integer> sv=All.get(Ploshadka);
                    sv.put("В разговоре",sv.get("В разговоре")+1);

                    All.put(Ploshadka,sv);


                }
            }
        }

        //return All;

    }





    public static HashMap<String,String> ouname (){
        HashMap<String,String> oun=new HashMap<String,String>();
        oun.putAll(ouname1(area1id,area1));
        oun.putAll(ouname1(area3id,area3));
        oun.putAll(ouname1(area4id,area4));
        oun.putAll(ouname1(area5id,area5));
        oun.putAll(ouname1(area2id,area2));
        return oun;
    }

    public static HashMap <String,String> ouname1(String ou,String name){
        HashMap <String,String> ouname1= new HashMap<String,String>();
        ouname1.put(ou,name);

        return ouname1;
    }
    //= new HashMap <String,String>();


    public static ArrayList<String> otdeli1(){
        ArrayList<String> otdeli = new ArrayList<String>();
        otdeli.add(area1id);
        otdeli.add(area3id);
        otdeli.add(area4id);
        otdeli.add(area5id);
        otdeli.add(area2id);

        return otdeli;
    }







    public static void main(String[] args) throws IOException {
        long m = System.currentTimeMillis();
  /*  EdurekaLogger obj12 = new EdurekaLogger();
    obj12.sampleLog();
    LogManager slg = LogManager.getLogManager();
    Logger log = slg.getLogger(Logger.GLOBAL_LOGGER_NAME);
    log.log(Level.WARNING, "Hi! Welcome from Edureka"); */





        String fio = "";


        String credits = "wsrest:Password";///super_user_name:password


        /*Новый собиратель статусов  в формате hashmap(словарь) */
        HashMap  <String,HashMap<String,Integer>> AllStates = new HashMap<String,HashMap<String,Integer>>();
        HashMap  <String,HashMap<String,ArrayList<String>>> ProblemAll = new HashMap<String,HashMap<String,ArrayList<String>>>();
        HashMap <String,ArrayList<String>> ProblemOp = new HashMap<String,ArrayList<String>>();




        String normal = "normal";
        String available = "available";
        String away = "away";
        String dnd = "dnd";
        String wrapup = "wrapup";



        // String statestatus = null;

        addToMap(area1, AllStates);
        addToMap(area4, AllStates);
        addToMap(area3, AllStates);
        addToMap(area2, AllStates);
        addToMap(area5, AllStates);




        ProblemAll.put(area1, iterateUsingEntrySetStr(new HashMap<String,ArrayList<String>>()));
        ProblemAll.put(area4, iterateUsingEntrySetStr(new HashMap<String,ArrayList<String>>()));
        ProblemAll.put(area3, iterateUsingEntrySetStr(new HashMap<String,ArrayList<String>>()));
        ProblemAll.put(area2, iterateUsingEntrySetStr(new HashMap<String,ArrayList<String>>()));
        ProblemAll.put(area5, iterateUsingEntrySetStr(new HashMap<String,ArrayList<String>>()));


        // System.out.print(ProblemAll);


        /* BEGIN --HTTP Connection for parsing */
        String strURL = "https://inreparea2.nau.team:8443/login";

        // User id, password

        // String authString = credits;

        // encode the authString using base64
        String encodedString = new String(Base64.encodeBase64(credits.getBytes()));
        /* END --HTTP Connection for parsing */

        /* BEGIN--PARSING DEPARTMENTS */
        // Take FIRST page of departments for parsing
        Document doc = Jsoup.connect(
                        "https://inreparea2.nau.team:8443/published?uuid=fckdshcorebo00000000000nsu8im88jud3414&activeComponent=AgentsInOUListView&objectslist_pn=0")
                .header("Authorization", "Basic " + encodedString)
                .get();
        // TAKE ELEMENTS FOR FIND MAX VALUE OF PAGES
        Element content = doc.getElementById("leftColumn");
        Elements links = content.getElementsByTag("a");
        int max = 0;
        for (Element link : links) {
            String page = link.text();
            int b = Integer.valueOf(page);

            if (b > max) {
                max = b;
            }
        }


        Elements rows_uuid = new Elements();


        for (int j = 0; j < max; j++) {
            String url2 = String.format(
                    "https://naumen_host:8443/published?uuid=dfkjdcorebo00000000000nsu8im77jus2314&activeComponent=AgentsInOUListView&objectslist_pn=%d",
                    j);//HERE YOU MUST INPUT URL FOR ONLINE REPORTS ABarea5 OPERATORS IN NAUMEN . %d - it is the number of page for show,it is must stay same =>%d.
            Document doc_final = Jsoup.connect(url2)
                    .header("Authorization", "Basic " + encodedString)
                    .get();


            rows_uuid = doc_final.select("tr.b-datatable__row");

            /* BEGIN--Get responce from API for departments--BEGIN */
            String urlAPI = "https://naumen_host:8443/api/v2/employees";



            String api = APIrequest(urlAPI, credits);


            JSONObject obj = new JSONObject(api); // <--- (variable api) <==> API response





            JSONArray arr = obj.getJSONArray("items");





            JSONObject stats = new JSONObject();




            for (Element row : rows_uuid) {

                String statsuuid = row.select("a").attr("href");// ** FIND Stats

                String text = row.select("td.b-datatable__body").text();// ** FIND text for time**/

                String timestatus = text.substring(text.lastIndexOf(" ") + 1);

                int statustime = Integer.parseInt(timestatus.substring(0, timestatus.indexOf(":")));
                int statustimemin = Integer
                        .parseInt(timestatus.substring(timestatus.indexOf(":") + 1, timestatus.lastIndexOf(":")));
                int statustimesec = Integer.parseInt(timestatus.substring(timestatus.lastIndexOf(":") + 1));
                statsuuid = statsuuid.substring(16);
                try{


                    for (int i = 0; i < arr.length(); i++) {


                        fio = arr.getJSONObject(i).getString("title");// FIO of operator for tag
                        String ou = arr.getJSONObject(i).getString("ou");
                        JSONArray role = arr.getJSONObject(i).getJSONArray("roles");



                        String uuid = arr.getJSONObject(i).getString("uuid");
                        String roles2 = role.toString();


                        if (uuid.equals(statsuuid) // && textfortime.contains(":") == true
                                && roles2.equals("Operator")){

                            takeAll( ou,AllStates,  ProblemAll,
                                    text,
                                    statustime, statustimemin, statustimesec, fio, timestatus);


                        }
                    }
                }catch(Exception e){System.out.println(e.getMessage());};
            }


        }



        LocalDateTime now = LocalDateTime.now();
        String t=now.format(DateTimeFormatter.ofPattern("HH:mm"));






    }
    /** END OF PART FOR MAKE PARSING TO GET EMPLOYEES STATUSES **/
    public static void iterateUsingEntrySet(HashMap<String, HashMap<String,Integer>> map) {
        for (HashMap.Entry<String, HashMap<String,Integer>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());

        }
    }


    public static String iterateUsingEntrySetKogo(HashMap<String,ArrayList<String>> map) {

        String result ="";
        for (HashMap.Entry<String,ArrayList<String>> entry : map.entrySet()) {

            if (entry.getValue().size()>0){
                result +=l5+entry.getKey()+":"+l66+l7+entry.getValue()+l8;

            }
            else {
                result+="";
            }
        }

        return result;
    }



    public static String napause="На паузе больше 10 минут";
    public static String nanormal="Более 3 минут без звонков";
    public static String napost="На посту более 15 секунд";

    public static HashMap<String,ArrayList<String>> iterateUsingEntrySetStr(HashMap<String, ArrayList<String>> map) {

        map.put(napause,new ArrayList<String>());
        map.put(nanormal,new ArrayList<String>());
        map.put(napost,new ArrayList<String>());
   /*for (HashMap.Entry<String, ArrayList<String>> entry : map.entrySet()) {
        //System.out.println(entry.getKey() + ":" + entry.getValue());
        map.put(entry.getKey(),ProblemOp1(Status, Hours, Minutes, Seconds, fio, ObsheeVremya));*/
        return map;

    }
    //public static String t=now.format(DateTimeFormatter.ofPattern("HH:mm"));
    public static String l ="<del>"+ "------------"  +"</del>"+"<strong>";
    public static String l2= "</strong>"+"<del>"+ "------------"  +"</del>"+"%0A";
    public static String l3 ="************" +"<strong>";
    public static String l4= "</strong>"+"************"  +"%0A";
    public static String l5= "<strong><u>";
    public static String l6= "</u></strong>"+"%0A";
    public static String l66= "</u></strong>";
    public static String l7 = "<strong>";
    public static String l8 = "</strong>"+"%0A";

    public static String telegramMessageObshie(String k,String v){
        return l5+k+l6+"%0A"+l7+v+l8;
    }


    public static String area1 = "area1";//The name of area
    public static String area1id = "corebo00000000000nu2hj3hj33kj";//uuid of the area
    public static String kovo_otmechaemarea1 = "@sv_area1";//Who is will be tagged in chat
    public static String area3 = "area3";//The name of area
    public static  String area3id = "corebo00000000000nvp0qve3hj34hjh";//uuid of the area
    public static  String kovo_otmechaemarea3 = "@sv_area3 @sv_area3_2";//Who is will be tagged in chat
    
    public static String area2 = "area2";//The name of area
    public static  String area2id = "corebo00000000000nuss7qg35443ujns";//uuid of the area
    public static  String kovo_otmechaemarea2 = "@sv_area2 @sv_area2_2";//Who is will be tagged in chat
    public static  String area4 = "area4";//The name of area
    public static String area4id = "corebo00000000000nuss8dasaasa";//uuid of the area
    public static  String kovo_otmechaemarea4 = "@sv_otmechaem";//Who is will be tagged in chat
    public static  String area5 = "area5";//The name of area
    public static String area5id = "corebo00000000000nvnhqhv53asaas";//uuid of the area
    public static  String kovo_otmechaemarea5 = "@sv_5";//Who is will be tagged in chat

    public static ArrayList<String> Goroda(){
        Goroda().add(area1);
        Goroda().add(area4);
        Goroda().add(area3);
        Goroda().add(area2);
        Goroda().add(area5);
        return Goroda();
    }

    public static String telegramMessageOtmechaem(String k,String v, String v2){
        return l5+k+l6+"%0A"+l7+v+l8+"%0A"+l5+l6+l7+v2+l8;  //k+"\n"+v;
    }

    public static void iterateUsingEntrySet2(String t,HashMap<String, HashMap<String,Integer>> map,HashMap<String,HashMap<String,ArrayList<String>>> map2,String  pro1,String pro2) throws IOException {

        HashMap <String,String> kovo_otmechMap = new HashMap <String,String>();
        kovo_otmechMap.put(area1,kovo_otmechaemarea1);
        kovo_otmechMap.put(area4,kovo_otmechaemarea4);
        kovo_otmechMap.put(area3,kovo_otmechaemarea3);
        kovo_otmechMap.put(area2,kovo_otmechaemarea2);
        kovo_otmechMap.put(area5,kovo_otmechaemarea5);


        String result="";
   
        botAPI(l3+t+l4);//border line
        for (HashMap.Entry<String, HashMap<String,Integer>> entry : map.entrySet()) {
            String k = entry.getKey();
            String v= entry.getValue().toString().replaceAll("\\{|\\,|\\}","");




            String kogo="";
            String v2="";
            v2 = iterateUsingEntrySetKogo(map2.get(k));

            if(v2.length()>0){
                v2=l7+v2+l8;
                kogo=kovo_otmechMap.get(k);

            }
     
            k=l5+k+l6;
            v=l7+v.replace("=",":")+l8;

            result=k+v+ v2+kogo;
            botAPI(result.replaceAll("\\[|\\,|\\]|\\{|\\}",""));//MAIN MESSAGE

           //System.out.print(result.replaceAll("\\[|\\,|\\]|\\{|\\}",""));
            }
        }

    }










