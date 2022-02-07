### WeworkChatSDK

[![Build Status](https://github.com/chinayin/WeworkChatSDK/workflows/JavaCI/badge.svg)](https://github.com/chinayin/WeworkChatSDK/actions)
[![Author](https://img.shields.io/badge/author-@chinayin-blue.svg)](https://github.com/chinayin)
![license](https://img.shields.io/github/license/chinayin/WeworkChatSDK.svg)

企业微信会话存档服务(WeworkChatSDK)，提供一键接入java sdk，投递数据到阿里云MNS、OSS。

### 功能介绍：

- 实时获取企业微信会话存档聊天数据，处理速度快。
- 多线程处理聊天资源，支持上G聊天附件文件上传`对象存储 OSS`。
- 聊天、资源数据投递`队列服务 MNS`，非java技术栈也可通过队列消费。
- 支持docker部署。
- 生产环境已稳定使用。

### 准备工作：

- 申请并配置阿里云`消息服务 MNS`、`对象存储 OSS`。
- 获取阿里云AccessKey，配置 `application.properties` 中相应配置项。
- 打包，更新配置文件。
- 安装`supervisord`，配置并执行程序。


### 配置文件`application.properties`说明

#### 阿里云 消息服务 MNS
```properties
# 服务可用区endpoint (区分内外网地址)
mns.endpoint=http://
mns.accessKeyId=
mns.accessKeySecret=
# 消息队列名称 (可自定义)
mns.queue=WeworkSyncMessageQueue
```

#### 阿里云 对象存储 OSS
```properties
# 服务可用区endpoint (区分内外网地址)
oss.endpoint=oss-cn-beijing.aliyuncs.com
oss.accessKeyId=
oss.accessKeySecret=
# Bucket名称 (可自定义)
oss.bucket=WeworkResources
```

#### 企业微信会话存档 - 应用配置
```properties
# 企业ID(在我的企业-企业信息里找到)
app.corpId=
# 应用Secret(会话存档应用中找到)
app.corpSecret=
```

#### 企业微信会话存档 - 私钥配置
说明：privateKey.后面的值为版本号(int值)
```properties
encrypt.privateKey.1=
encrypt.privateKey.2=
```

### 部署

- 复制`sdk`目录下`.so`文件至Java Library

官方`.so`下载链接：https://developer.work.weixin.qq.com/document/path/91774

查看目录方法：
```java
public class Demo {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.library.path"));
    }
}
```

- 生成打包文件
```bash
./mvnw clean package
```

- 命令行方式运行
```bash
java -jar WeworkChatSDK-[version].jar
# 生产环境 (目录存在 application-prod.properties )
java -jar WeworkChatSDK-[version].jar --spring.profiles.active=prod
```

- `supervisord`方式运行，参照配置文件： `src\main\resources\supervisord.conf`


### 附录：

#### 使用Alibaba开源的Java诊断工具 `Arthas`

https://arthas.aliyun.com/

下载arthas-boot.jar，然后用java -jar的方式启动：
```bash
curl -O https://arthas.aliyun.com/arthas-boot.jar
java -jar arthas-boot.jar
```

打印帮助信息：
```bash
java -jar arthas-boot.jar -h
```

选择应用java进程：
```bash
$ $ java -jar arthas-boot.jar
* [1]: 12345
```

输入dashboard，按回车/enter，会展示当前进程的信息，按ctrl+c可以中断执行。

#### 企业微信会话存档官方文档

https://developer.work.weixin.qq.com/document/path/91361

