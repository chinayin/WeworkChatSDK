package com.chinayin.wework.chatdata.service.impl;

import com.chinayin.wework.chatdata.service.FileOutputService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chianyin <whereismoney@qq.com>
 * 保存消息到文件
 */
@Slf4j
@Service("FileOutputService")
public class FileOutputServiceImpl implements FileOutputService {

    private static String path = "./logs";

    @Override
    public void saveMessage(String msg) {
        String file = path + "/msg-" + this.getDateStr();
        this.record(file, msg);
    }

    @Override
    public void saveOriginMessage(String msg) {
        String file = path + "/origin-msg-" + this.getDateStr();
        this.record(file, msg);
    }

    private boolean record(String file, String msg) {
        try {
            // 判断文件夹是否存在
//            if (!fs.getParentFile().exists()) {
//                fs.getParentFile().mkdirs();
//            }
            FileOutputStream outputStream = new FileOutputStream(new File(file), true);
            outputStream.write(msg.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.close();
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    private String getDateStr() {
        return (new SimpleDateFormat("yyyy-MM-dd")).format((new Date())) + ".log";
    }
}
