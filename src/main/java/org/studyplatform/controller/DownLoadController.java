package org.studyplatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.studyplatform.model.Version;
import org.studyplatform.other.DownloadRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;

@Controller
public class DownLoadController {

    private static final int VERSION=3;
    private static final String filename="Weather_v1.7.3.apk";
    private static final String VERSION_CODE="1.7.3";

    @RequestMapping("download")
    public ResponseEntity<byte[]> download(HttpServletResponse response) throws IOException {
        String fileName=new String("MogeMap.apk".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
        String path="C:\\file\\MogeMap.apk";
        File file=new File(path);
        /*String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            mimeType = "application/octet-stream";
        }*/
        //response.setContentType(mimeType);
        //response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength((int)file.length());
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
    @RequestMapping("checkVersion")
    public @ResponseBody JSONObject  ckeckVersion(){
        Version version=new Version();
        version.setVersion(VERSION);
        version.setVersionCode(VERSION_CODE);
        String result= JSON.toJSONString(version);
        JSONObject obj=JSON.parseObject(result);
        System.out.println(result);
        System.out.println("阿斯蒂芬鸡丝豆腐");
        return obj;
    }
    @RequestMapping(value = "myString/{filename}/{filetype}",method = RequestMethod.GET)
    public String mytest(@PathVariable String filename,@PathVariable String filetype){
        System.out.println("filename:"+filename);
        System.out.println("filetype:"+filetype);
        return "/studyplatform/course_all";
    }
    @RequestMapping(value="download2",method = RequestMethod.GET)
    public static void download(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String fileTrue=filename;
        String fileName=new String(fileTrue.getBytes("UTF-8"),"iso-8859-1");
        String filePath="C:\\file\\"+fileTrue;
        File file=new File(filePath);
        //声明本次下载状态的记录对象
        DownloadRecord downloadRecord = new DownloadRecord(fileName, filePath, request);
        //设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setContentLength((int)file.length());
        System.out.println((int)file.length());
        response.setHeader("Protocol","HTTP/1.1");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Connection","keep-alive");
        response.setHeader("Accept-Encoding","gzip, deflate");
        response.setHeader("Content-Type","text/html;charset=utf-8");
        response.setHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:11.0) Gecko/20100101 Firefox/11.0");
        long pos=0;
        long epos=0;
        if(request.getHeader("Range")!=null){
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            try{
                String range=request.getHeader("Range");
                pos = Long.parseLong(range.substring(range.indexOf("=")+1,range.lastIndexOf("-")));
                System.out.println("pos:"+range.substring(range.indexOf("=")+1,range.lastIndexOf("-")));
                epos=Long.parseLong(range.substring(range.lastIndexOf("-")+1,range.length()));
                System.out.println("pos:"+pos+" epos:"+epos);
                response.setHeader("Content-Range","bytes "+pos+"-"+(epos-1)+"/"+epos);
            }catch (NumberFormatException e){
                e.printStackTrace();
                pos=0;
            }
        }
        //用于记录以完成的下载的数据量，单位是byte
        long downloadedLength = 0l;
        try {
            //打开本地文件流
            InputStream inputStream = new FileInputStream(filePath);
            long skip=skipBytesFromStream(inputStream,pos);
            System.out.println("skip:"+skip);
            //激活下载操作
            OutputStream os = response.getOutputStream();

            //循环写入输出流
            byte[] b = new byte[1024*2];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
                downloadedLength += b.length;
                System.out.println(downloadedLength);
            }

            // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (Exception e){
            downloadRecord.setStatus(DownloadRecord.STATUS_ERROR);
            throw e;
        }
        downloadRecord.setStatus(DownloadRecord.STATUS_SUCCESS);
        downloadRecord.setEndTime(new Timestamp(System.currentTimeMillis()));
        downloadRecord.setLength(downloadedLength);
        //存储记录
    }
    @RequestMapping(value="download3",method = RequestMethod.GET)
    public static void download3(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String fileTrue="MogeMap.apk";
        String fileName=new String(fileTrue.getBytes("UTF-8"),"iso-8859-1");
        String filePath="C:\\file\\"+fileTrue;
        File file=new File(filePath);
        //声明本次下载状态的记录对象
        DownloadRecord downloadRecord = new DownloadRecord(fileName, filePath, request);
        //设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setContentLength((int)file.length());
        System.out.println((int)file.length());
        response.setHeader("Protocol","HTTP/1.1");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Connection","keep-alive");
        response.setHeader("Accept-Encoding","gzip, deflate");
        response.setHeader("Content-Type","text/html;charset=utf-8");
        response.setHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:11.0) Gecko/20100101 Firefox/11.0");
        long pos=0;
        long epos=0;
        if(request.getHeader("Range")!=null){
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            try{
                String range=request.getHeader("Range");
                pos = Long.parseLong(range.substring(range.indexOf("=")+1,range.lastIndexOf("-")));
                System.out.println("pos:"+range.substring(range.indexOf("=")+1,range.lastIndexOf("-")));
                epos=Long.parseLong(range.substring(range.lastIndexOf("-")+1,range.length()));
                System.out.println("pos:"+pos+" epos:"+epos);
                response.setHeader("Content-Range","bytes "+pos+"-"+(epos-1)+"/"+epos);
            }catch (NumberFormatException e){
                e.printStackTrace();
                pos=0;
            }
        }
        //用于记录以完成的下载的数据量，单位是byte
        long downloadedLength = 0l;
        try {
            //打开本地文件流
            InputStream inputStream = new FileInputStream(filePath);
            long skip=skipBytesFromStream(inputStream,pos);
            System.out.println("skip:"+skip);
            //激活下载操作
            OutputStream os = response.getOutputStream();

            //循环写入输出流
            byte[] b = new byte[1024*2];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
                downloadedLength += b.length;
                System.out.println(downloadedLength);
            }

            // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (Exception e){
            downloadRecord.setStatus(DownloadRecord.STATUS_ERROR);
            throw e;
        }
        downloadRecord.setStatus(DownloadRecord.STATUS_SUCCESS);
        downloadRecord.setEndTime(new Timestamp(System.currentTimeMillis()));
        downloadRecord.setLength(downloadedLength);
        //存储记录
    }
    private static long skipBytesFromStream(InputStream inputStream, long n) {
        long remaining = n;
        // SKIP_BUFFER_SIZE is used to determine the size of skipBuffer
        int SKIP_BUFFER_SIZE = 2048;
        // skipBuffer is initialized in skip(long), if needed.
        byte[] skipBuffer = null;
        int nr = 0;
        if (skipBuffer == null) {
            skipBuffer = new byte[SKIP_BUFFER_SIZE];
        }
        byte[] localSkipBuffer = skipBuffer;
        if (n <= 0) {
            return 0;
        }
        while (remaining > 0) {
            try {
                nr = inputStream.read(localSkipBuffer, 0,
                        (int) Math.min(SKIP_BUFFER_SIZE, remaining));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nr < 0) {
                break;
            }
            remaining -= nr;
        }
        return n - remaining;
    }
}
