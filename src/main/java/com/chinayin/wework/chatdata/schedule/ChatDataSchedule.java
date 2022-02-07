package com.chinayin.wework.chatdata.schedule;

import com.chinayin.wework.chatdata.service.ChatDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.Objects;

/**
 * @author chianyin <whereismoney@qq.com>
 * 获取微信会话消息
 */
@Slf4j
@Component
public class ChatDataSchedule {

    /**
     * seq 文件保存位置
     */
    private static final String seqFileName = "/tmp/seq.txt";

    @Resource
    private ChatDataService chatDataService;

    @Scheduled(fixedDelay = 1000)
    public void syncChatData() {
        long seqNum = this.getSeqNum();
        log.info("seqNum : {}", seqNum);
        try {
            long latestSeqNum = chatDataService.getChatData(seqNum);
            this.setSeqNum(latestSeqNum);
        } catch (Exception e) {
            log.error("[异常]主流程失败: {}", e.getMessage());
        }
    }

    private int getSeqNum() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(seqFileName));
            String seqNum = in.readLine();
            log.info("[流程]读取 seqNum: {}", seqNum);
            if (Objects.nonNull(seqNum)) {
                return Integer.valueOf(seqNum);
            }
        } catch (Exception e) {
            log.error("[异常]读取 seqNum 失败: {}", e.getMessage());
        }
        return 0;
    }

    private void setSeqNum(long seqNum) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(seqFileName));
            out.write(String.valueOf(seqNum));
            out.close();
            log.info("[流程]记录 seqNum: {}", seqNum);
        } catch (IOException e) {
            log.error("[异常]记录 seqNum 失败: {}", e.getMessage());
        }
    }
}
