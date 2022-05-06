#
#
#--------------------------------------------------------------------------
# WeworkChatSDK
#--------------------------------------------------------------------------
#

FROM --platform=linux/amd64 chinayin/maven:3-jdk-8-bullseye-slim AS builder

RUN set -eux \
  #&& install_packages tree vim \
  && mkdir -p ~/.m2 \
  && echo '<settings><mirrors><mirror><id>aliyun</id><mirrorOf>*</mirrorOf><name>aliyun</name><url>https://maven.aliyun.com/repository/public</url></mirror></mirrors></settings>' >> ~/.m2/settings.xml \
  && mkdir /app

WORKDIR /app
ADD . /app

RUN set -eux \
  && mvn -B package --file pom.xml \
  && ls -l target \
  && SDK_VERSION=$(grep '<version>' pom.xml |head -n1 |tr -cd "[0-9.]") \
  && echo $SDK_VERSION \
  && cp -f target/WeworkChatSDK-${SDK_VERSION}-bundle.tar.gz target/bundle.tar.gz

FROM --platform=linux/amd64 chinayin/openjdk:8-jre-bullseye-slim
ENV TZ=PRC
ENV PARAMS=""

COPY --from=builder /app/target/bundle.tar.gz /app/bundle.tar.gz
WORKDIR /app

RUN set -eux \
  && tar -xzf bundle.tar.gz -C /app --strip-components=1 \
  && mkdir -p /usr/lib64 \
  && mv *.so /usr/lib64 \
  && rm -f bundle.tar.gz

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS WeworkChatSDK-*.jar $PARAMS"]
