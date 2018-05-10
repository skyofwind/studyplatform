package org.studyplatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.studyplatform.model.*;
import org.studyplatform.service.MogeMapService;

import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/mogemap")
public class MogeMapController {

    private final static String RECORD_PATH = "C:\\MogeMap\\RunRecords\\";

    @Autowired
    private MogeMapService mogeMapService;

    @RequestMapping("/displayRecord/{rid}")
    public ModelAndView goDisplay(@PathVariable int rid) throws IOException {
        ModelAndView modelAndView = new ModelAndView();

        Mogemap_run_record record = mogeMapService.getRecordById(rid);
        File file = new File(record.getJson());
        String json = getRecordJson(file);
        record.setJson(json);
        String result= JSON.toJSONString(record);
        System.out.println("result="+result);
        JSONObject obj=JSON.parseObject(result);

        modelAndView.addObject("json", obj);
        modelAndView.setViewName("/mapRecord");

        return modelAndView;
    }

    @RequestMapping("/checkPhone/{phone}")
    public @ResponseBody
    JSONObject ckeckPhone(@PathVariable String phone){
        Mogemap_user user = mogeMapService.getUserByPhone(phone);
        PhoneType phoneType = new PhoneType();
        if(user == null){
            phoneType.setUse("0");
        }else {
            phoneType.setUse("1");
        }
        String result= JSON.toJSONString(phoneType);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/checkPhone")
    public @ResponseBody
    JSONObject test(){
        PhoneType phoneType = new PhoneType();
        phoneType.setUse("0");
        String result= JSON.toJSONString(phoneType);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/addUser")
    public @ResponseBody Mogemap_user addUser(@RequestBody Mogemap_user user) throws UnsupportedEncodingException {
        String type = "0";
        //String json= URLDecoder.decode(param, "utf8");
        System.out.println(user.getName());

        Mogemap_user mogemap_user = mogeMapService.getUserByPhone(user.getPhone());
        if(mogemap_user == null){
            int result = mogeMapService.addUser(user);
            if(result == 1){
                type = "1";
            }else {
                type = "2";
            }
        }
        System.out.println(type);
        return user;
    }
    @RequestMapping("/updateUser")
    public @ResponseBody
    JSONObject updateUser(@RequestBody Mogemap_user user){
        int result = mogeMapService.updateUserById(user);
        if(result == 1){
            System.out.println("更改成功");
        }else {
            System.out.println("失败");
        }
        String s = JSON.toJSONString(user);
        JSONObject object = JSON.parseObject(s);
        return object;
    }
    @RequestMapping("/addRunRecord")
    public @ResponseBody Mogemap_run_record addRecord(@RequestBody Mogemap_run_record record){
        //Mogemap_run_record myRecord = new Mogemap_run_record();
        String json = JSON.toJSONString(record);
        JSONObject object = JSON.parseObject(json);
        String array = object.getString("json");
        String phone = record.getPhone();
        DateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(record.getDate());
        String pathName = "RunRecord_"+phone+"_"+dateStr+".txt";
        String filePath = RECORD_PATH+phone+"\\"+pathName;

        record.setJson(filePath);
        int result = mogeMapService.addRecord(record);
        System.out.println("result="+result);

        boolean a = false;
        if(result == 1){
            File dir = new File(RECORD_PATH+phone);
            judeDirExists(dir);
            File file = new File(filePath);
            judeFileExists(file);

            try {
                a = writeTextFile(file, array);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        Timestamp timestamp = new Timestamp(record.getDate().getTime());
        map.put("phone", record.getPhone());
        map.put("mdate", timestamp);

        Mogemap_run_record myRecord = mogeMapService.getRecordByPhoneAndDate(map);
        //System.out.println("result="+array);
        System.out.println("phone="+phone);
        System.out.println("pathName="+filePath);
        System.out.println("a="+a);

        return myRecord;
    }
    @RequestMapping("/getRecord/{id}")
    public @ResponseBody
    JSONObject getRecord(@PathVariable int id) throws IOException {
        Mogemap_run_record record = mogeMapService.getRecordById(id);
        File file = new File(record.getJson());
        String json = getRecordJson(file);
        record.setJson(json);
        String result= JSON.toJSONString(record);
        System.out.println("result="+result);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/getRecords/{phone}")
    public @ResponseBody
    JSONObject getRecords(@PathVariable String phone){
        RunRecords records = new RunRecords();
        records.setRecords(mogeMapService.getRecords(phone));
        String result= JSON.toJSONString(records);
        System.out.println("result="+result);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/getRecordsByDay/{phone}")
    public @ResponseBody
    JSONObject getRecordsByDay(@PathVariable String phone){
        RunRecords records = new RunRecords();
        records.setRecords(mogeMapService.getRecordsByDay(phone));
        String result= JSON.toJSONString(records);
        System.out.println("result="+result);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/getRecordsByWeek/{phone}")
    public @ResponseBody
    JSONObject getRecordsByWeek(@PathVariable String phone){
        RunRecords records = new RunRecords();
        records.setRecords(mogeMapService.getRecordsByWeek(phone));
        String result= JSON.toJSONString(records);
        System.out.println("result="+result);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/getRecordsByMonth/{phone}")
    public @ResponseBody
    JSONObject getRecordsByMonth(@PathVariable String phone){
        RunRecords records = new RunRecords();
        records.setRecords(mogeMapService.getRecordsByMonth(phone));
        String result= JSON.toJSONString(records);
        System.out.println("result="+result);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/day/{mPhone}/PK/{fPhone}")
    public @ResponseBody
    JSONObject getDayPk(@PathVariable String mPhone, @PathVariable String fPhone){
        List<Mogemap_run_record> mRecords = mogeMapService.getRecordsByDay(mPhone);
        List<Mogemap_run_record> fRecords = mogeMapService.getRecordsByDay(fPhone);
        Mogemap_user mUser = mogeMapService.getUserByPhone(mPhone);
        Mogemap_user fUser = mogeMapService.getUserByPhone(fPhone);
        UserRunPK pk = new UserRunPK();
        MogeUserItem mItem = new MogeUserItem();
        MogeUserItem fItem = new MogeUserItem();
        double mCount = 0, fCount = 0;
        for(Mogemap_run_record r: mRecords){
            if(judeToday(r.getDate())){
                mCount +=r.getDistance();
            }
        }
        for(Mogemap_run_record r: fRecords){
            if(judeToday(r.getDate())){
                fCount +=r.getDistance();
            }
        }
        mItem.setDistance(mCount);
        mItem.setHead(mUser.getHeadurl());
        mItem.setId(mUser.getId());
        mItem.setName(mUser.getName());

        fItem.setDistance(fCount);
        fItem.setHead(fUser.getHeadurl());
        fItem.setId(fUser.getId());
        fItem.setName(fUser.getName());

        if(mCount > fCount){
            pk.setWin(mItem);
            pk.setLose(fItem);
        }else {
            pk.setWin(fItem);
            pk.setLose(mItem);
        }
        String result= JSON.toJSONString(pk);
        System.out.println("result="+result);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/week/{mPhone}/PK/{fPhone}")
    public @ResponseBody
    JSONObject getWeekPk(@PathVariable String mPhone, @PathVariable String fPhone){
        List<Mogemap_run_record> mRecords = mogeMapService.getRecordsByWeek(mPhone);
        List<Mogemap_run_record> fRecords = mogeMapService.getRecordsByWeek(fPhone);
        Mogemap_user mUser = mogeMapService.getUserByPhone(mPhone);
        Mogemap_user fUser = mogeMapService.getUserByPhone(fPhone);
        UserRunPK pk = new UserRunPK();
        MogeUserItem mItem = new MogeUserItem();
        MogeUserItem fItem = new MogeUserItem();
        double mCount = 0, fCount = 0;
        for(Mogemap_run_record r: mRecords){
            mCount +=r.getDistance();
        }
        for(Mogemap_run_record r: fRecords){
            fCount +=r.getDistance();
        }
        mItem.setDistance(mCount);
        mItem.setHead(mUser.getHeadurl());
        mItem.setId(mUser.getId());
        mItem.setName(mUser.getName());

        fItem.setDistance(fCount);
        fItem.setHead(fUser.getHeadurl());
        fItem.setId(fUser.getId());
        fItem.setName(fUser.getName());

        if(mCount > fCount){
            pk.setWin(mItem);
            pk.setLose(fItem);
        }else {
            pk.setWin(fItem);
            pk.setLose(mItem);
        }
        String result= JSON.toJSONString(pk);
        System.out.println("result="+result);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/month/{mPhone}/PK/{fPhone}")
    public @ResponseBody
    JSONObject getMonthPk(@PathVariable String mPhone, @PathVariable String fPhone){
        List<Mogemap_run_record> mRecords = mogeMapService.getRecordsByMonth(mPhone);
        List<Mogemap_run_record> fRecords = mogeMapService.getRecordsByMonth(fPhone);
        Mogemap_user mUser = mogeMapService.getUserByPhone(mPhone);
        Mogemap_user fUser = mogeMapService.getUserByPhone(fPhone);
        UserRunPK pk = new UserRunPK();
        MogeUserItem mItem = new MogeUserItem();
        MogeUserItem fItem = new MogeUserItem();
        double mCount = 0, fCount = 0;
        for(Mogemap_run_record r: mRecords){
            mCount +=r.getDistance();
        }
        for(Mogemap_run_record r: fRecords){
            fCount +=r.getDistance();
        }
        mItem.setDistance(mCount);
        mItem.setHead(mUser.getHeadurl());
        mItem.setId(mUser.getId());
        mItem.setName(mUser.getName());

        fItem.setDistance(fCount);
        fItem.setHead(fUser.getHeadurl());
        fItem.setId(fUser.getId());
        fItem.setName(fUser.getName());

        if(mCount > fCount){
            pk.setWin(mItem);
            pk.setLose(fItem);
        }else {
            pk.setWin(fItem);
            pk.setLose(mItem);
        }
        String result= JSON.toJSONString(pk);
        System.out.println("result="+result);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/leaderboards/{mPhone}/{mType}")
    public @ResponseBody
    JSONObject getLeaderboards(@PathVariable String mPhone, @PathVariable int mType){
        List<Mogemap_user_friends> friends = new ArrayList<Mogemap_user_friends>();
        friends = mogeMapService.getFriendsByMid(mPhone);
        List<MogeUserItem> items = new ArrayList<MogeUserItem>();
        List<Mogemap_run_record> records;
        Mogemap_user user;
        if (mType == 0){//day
            records = mogeMapService.getRecordsByDay(mPhone);
            user = mogeMapService.getUserByPhone(mPhone);
            items.add(getMogeUserItem(user, records, 0));
            for(Mogemap_user_friends f: friends){
                records.clear();

                records = mogeMapService.getRecordsByDay(f.getFid());
                user = mogeMapService.getUserByPhone(f.getFid());
                items.add(getMogeUserItem(user, records, 0));
            }
        }else if (mType == 1){//week
            records = mogeMapService.getRecordsByWeek(mPhone);
            user = mogeMapService.getUserByPhone(mPhone);
            items.add(getMogeUserItem(user, records, 1));
            for(Mogemap_user_friends f: friends){
                records.clear();

                records = mogeMapService.getRecordsByWeek(f.getFid());
                user = mogeMapService.getUserByPhone(f.getFid());
                items.add(getMogeUserItem(user, records, 1));
            }

        }else if (mType == 2){//month
            records = mogeMapService.getRecordsByMonth(mPhone);
            user = mogeMapService.getUserByPhone(mPhone);
            items.add(getMogeUserItem(user, records, 2));
            for(Mogemap_user_friends f: friends){
                records.clear();

                records = mogeMapService.getRecordsByMonth(f.getFid());
                user = mogeMapService.getUserByPhone(f.getFid());
                items.add(getMogeUserItem(user, records, 2));
            }
        }
        Collections.sort(items, Collections.reverseOrder());
        MogeLeaderboards leaderboards = new MogeLeaderboards();
        leaderboards.setItems(items);
        String result= JSON.toJSONString(leaderboards);
        System.out.println("result="+result);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/getSevenRecord/{phone}")
    public @ResponseBody
    JSONObject getSevenRecord(@PathVariable String phone){
        List<Mogemap_run_record> list = mogeMapService.getSevenRecordByPhone(phone);
        RunRecords runRecords = new RunRecords();
        runRecords.setRecords(list);
        String result = JSON.toJSONString(runRecords);
        System.out.println(result);
        JSONObject obj = JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/getUserPhone/{phone}")
    public @ResponseBody
    JSONObject getUserByPhone(@PathVariable String phone){
        Mogemap_user user = mogeMapService.getUserByPhone(phone);
        String result= JSON.toJSONString(user);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/getUserQQ/{qq}")
    public @ResponseBody
    JSONObject getUserByqq(@PathVariable String qq){
        System.out.println("qq="+qq);
        Mogemap_user user = mogeMapService.getUserByQq(qq);
        if(user == null){
            System.out.println("是空的");
        }
        String result= JSON.toJSONString(user);
        System.out.println("result="+result);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/getUserWeibo/{weibo}")
    public @ResponseBody
    JSONObject getUserByWeibo(@PathVariable String weibo){
        Mogemap_user user = mogeMapService.getUserByWeibo(weibo);
        if(user == null){
            System.out.println("是空的");
        }
        String result= JSON.toJSONString(user);
        System.out.println("result="+result);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/user/{mid}/add/{fid}")
    public @ResponseBody
    JSONObject addFriends(@PathVariable String mid, @PathVariable String fid){
        String type = "";
        Mogemap_user user = mogeMapService.getUserByPhone(fid);
        if(user == null){
            type = "0";//用户不存在
        }else {
            Mogemap_user_friends friends = mogeMapService.getFriendsByMidAndFid(mid, fid);
            if(friends == null){
                friends = new Mogemap_user_friends();
                friends.setMid(mid);
                friends.setFid(fid);
                int result = mogeMapService.addFriend(friends);
                friends.setMid(fid);
                friends.setFid(mid);
                result = mogeMapService.addFriend(friends);
                if (result == 1){
                    type = "1";//添加好友成功
                }else {
                    type = "2";//添加好友失败
                }

            }else {
                type = "3";//好友已存在
            }
        }
        String json = "{\"type\":\""+type+"\"}";
        JSONObject obj=JSON.parseObject(json);
        return obj;
    }
    @RequestMapping("/deleteFriend/{mid}/{fid}")
    public @ResponseBody
    JSONObject deleteFriend(@PathVariable String mid, @PathVariable String fid){
        int a = mogeMapService.deleteFriend(mid, fid);
        PhoneType phoneType = new PhoneType();
        if(a == 1){
            mogeMapService.deleteFriend(fid, mid);
            phoneType.setUse("1");
        }else {
            phoneType.setUse("0");
        }
        String result= JSON.toJSONString(phoneType);
        JSONObject obj=JSON.parseObject(result);
        return obj;
    }
    @RequestMapping("/getFriends/{phone}")
    public @ResponseBody
    JSONObject getFriends(@PathVariable String phone){
        List<Mogemap_user_friends> friends = mogeMapService.getFriendsByMid(phone);
        List<Mogemap_user> users = new LinkedList<Mogemap_user>();
        if(friends == null){
            System.out.println("集合是和i空的");
        }else {
            System.out.println("size="+friends.size());
            if(friends.size() > 0){
                for(Mogemap_user_friends f: friends){
                    Mogemap_user user = mogeMapService.getUserByPhone(f.getFid());
                    users.add(user);
                }
            }
        }
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(users));
        String result= JSON.toJSONString(array);
        String fResult = "{\"friends\":"+result+"}";
        System.out.println(fResult);
        JSONObject obj=JSON.parseObject(fResult);
        return obj;
    }

    // 判断文件是否存在
    public static void judeFileExists(File file) {

        if (file.exists()) {
            System.out.println("file exists");
        } else {
            System.out.println("file not exists, create it ...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    private MogeUserItem getMogeUserItem(Mogemap_user user, List<Mogemap_run_record> records, int type){
        MogeUserItem item = new MogeUserItem();
        if(type == 0){
            item.setDistance(getDistanceByDay(records));
        }else{
            item.setDistance(getDistance(records));
        }
        item.setHead(user.getHeadurl());
        item.setId(user.getId());
        item.setName(user.getName());
        return item;
    }
    private double getDistance(List<Mogemap_run_record> records){
        double distance = 0;
        for(Mogemap_run_record record: records){
            distance +=record.getDistance();
        }
        return distance;
    }
    private double getDistanceByDay(List<Mogemap_run_record> records){
        double distance = 0;
        for(Mogemap_run_record record: records){
            if(judeToday(record.getDate())){
                distance +=record.getDistance();
            }
        }
        return distance;

    }
    private boolean judeToday(Date date){
        boolean result = false;
        Date nDate = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(calendar.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)){
            result = true;
        }
        return result;
    }
    // 判断文件夹是否存在
    public static void judeDirExists(File file) {

        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }

    }
    public static boolean writeTextFile(File file, String str) throws IOException {
        boolean result = false;
        String filein = str + "\r\n";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;

        try {
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            result = true;
        } catch (IOException e1) {
            // TODO 自动生成 catch 块
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return result;
    }
    public static String getRecordJson(File file) throws IOException {
        String result = "";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                //buf = buf.append(System.getProperty("line.separator"));
            }
            result = buf.toString();
        } catch (IOException e1) {
            // TODO 自动生成 catch 块
            throw e1;
        } finally {
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return result;
    }
}
