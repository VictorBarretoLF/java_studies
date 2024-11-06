# Comandos

## Comandos Docker para rodar e entrar no container

Rodando o Kafka com variável de ambiente com Docker

```bash
# Este comando inicia os serviços definidos no arquivo `docker-compose.yml` utilizando as variáveis de ambiente especificadas no arquivo `envirement.env`.
docker-compose -f docker-compose.yml --env-file envirement.env up
```

Para iniciar uma sessão bash dentro do contêiner chamado `my_kafka_container`, você pode usar o seguinte comando `docker run`:

```bash
# Este comando cria e inicia um novo contêiner chamado `my_kafka_container` e abre uma sessão interativa de bash dentro dele.
docker run -it --name my_kafka_container /bin/bash
```

```bash
# Este comando lista os contêineres em execução definidos no arquivo `docker-compose.yml`.
docker compose ps
```

```bash
# Este comando exibe os logs dos contêineres em execução e continua a seguir novos logs em tempo real.
docker compose logs -f
```

```bash
# Este comando abre uma sessão interativa de bash dentro do contêiner `my_kafka_container` em execução.
docker exec -it my_kafka_container bash
```

## Kafka CLI

### Topics

```bash
# Navega até o diretório onde estão os binários do Kafka.
cd /opt/bitnami/kafka/bin
```

```bash
# Cria um novo tópico chamado 'teste' com 3 partições no servidor Kafka em execução no localhost na porta 9092.
./kafka-topics.sh --create --topic=teste --bootstrap-server=localhost:9092 --partitions=3
```

```bash
# Cria um novo tópico chamado 'topic1' com 3 partições e um fator de replicação de 3 no servidor Kafka em execução no localhost na porta 9092.
./kafka-topics.sh --create --topic=topic1 --bootstrap-server=localhost:9092 --partitions=3 --replication-factor=3
```

```bash
# Lista todos os tópicos existentes no cluster Kafka especificado.
./kafka-topics.sh --list --bootstrap-server=localhost:9092
```

```bash
# Descreve os detalhes de todos os tópicos existentes no cluster Kafka especificado.
./kafka-topics.sh --describe --bootstrap-server=localhost:9092
```

```bash
# Exclui o tópico chamado 'topic1' no servidor Kafka em execução no localhost na porta 9092.
./kafka-topics.sh --delete --topic=topic1 --bootstrap-server=localhost:9092
```

```bash
# Create a Kafka topic named 'insync-topic' with 3 partitions and a replication factor of 3.
# The topic is configured to require at least 2 in-sync replicas for a message to be considered committed.
./kafka-topics.sh --create --topic=insync-topic --bootstrap-server=localhost:9092 --partitions=3 --replication-factor=3 --config="min.insync.replicas=2"
```

```bash
# Alter the configuration of an existing Kafka topic named 'topic' to set the minimum number of in-sync replicas to 2.
./kafka-configs.sh --bootstrap-server=localhost:9092 --alter --entity-type=topics --entity-name=topic --add-config="min.insync.replicas=2"
```

### Consumer & Producers

```bash
# Start a Kafka consumer to read messages from the 'teste' topic
./kafka-console-consumer.sh --bootstrap-server=localhost:9092 --topic=teste
```

```bash
# Start a Kafka producer to send messages to the 'teste' topic
./kafka-console-producer.sh --bootstrap-server=localhost:9092 --topic=teste
```

```bash
# Start a Kafka consumer to read all messages from the beginning of the 'teste' topic
./kafka-console-consumer.sh --bootstrap-server=localhost:9092 --topic=teste --from-beginning
```

```bash
# Start a Kafka consumer to read all messages from the beginning of the 'teste' topic with a specific consumer group 'x'
./kafka-console-consumer.sh --bootstrap-server=localhost:9092 --topic=teste --from-beginning --group=x
```

```bash
# Start a Kafka producer to send messages to the 'teste' topic with key-value pairs, using ':' as the key separator
./kafka-console-producer.sh --bootstrap-server=localhost:9092 --topic=teste --property="parse.key=true" --property="key.separator=:"
```

```bash
# Start a Kafka consumer to read messages from the 'product-created-events-topic' topic and print the message keys.
./kafka-console-consumer.sh --bootstrap-server=localhost:9092 --topic=product-created-events-topic --property="print.key=true"
```


```bash
kafka-console-producer --bootstrap-server=localhost:9092 --topic=product-created-events-topic --property="print.key=true" --property="key.separator=:"
```
