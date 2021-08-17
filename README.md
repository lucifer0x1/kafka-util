# 工程简介
kafa测试代码


# 延伸阅读


## Docker 安装Kafka

### 一、下载镜像

```
docker pull wurstmeister/zookeeper
docker pull wurstmeister/kafka
docker pull sheepkiller/kafka-manager
```


### 二、先启动zookeeper

```
#单机方式
docker run -d --name zookeeper -p 2181:2181 -t wurstmeister/zookeeper
```


### 三、启动kafka

```
#单机方式
docker run -d --name kafka --publish 9082:9092 --link zookeeper:zookeeper --env KAFKA_BROKER_ID=100 --env HOST_IP=192.168.50.201 --env KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 --env KAFKA_ADVERTISED_HOST_NAME=192.168.50.201 --env KAFKA_ADVERTISED_PORT=9082 --restart=always wurstmeister/kafka

```


### 四、创建一个topic（使用代码次步可省略）

```
#进入容器
docker exec -it ${CONTAINER ID} /bin/bash
cd opt/bin
#单机方式：创建一个主题
bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic mykafka
#运行一个生产者
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic mykafka
#运行一个消费者
bin/kafka-console-consumer.sh --zookeeper zookeeper:2181 --topic mykafka --from-beginning
```



### 五、kafka设置分区数量

```
#分区数量的作用：有多少分区就能负载多少个消费者，生产者会自动分配给分区数据，每个消费者只消费自己分区的数据，每个分区有自己独立的offset
#进入kafka容器
vi opt/kafka/config/server.properties
修改run.partitions=2
#退出容器
ctrl+p+q
#重启容器
docker restart kafka

#修改指定topic
./kafka-topics.sh --zookeeper localhost:2181 --alter --partitions 3 --topic topicname
```

### 六、启动kafka-manager

~~~
docker run -d --name kafka-manager --link zookeeper:zookeeper --link kafka:kafka -p 9001:9000 --restart=always --env ZK_HOSTS=zookeeper:2181 sheepkiller/kafka-manager

~~~
