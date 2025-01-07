```bash
    docker exec -it <container_id> /bin/bash
    
    mongosh -u root -p
    
    kafka-console-consumer --bootstrap-server=localhost:9092 --topic=teste --from-beginning --group=x

    kafka-consumer-groups --bootstrap-server=localhost:9092 --group=x --describe
```

## Seguir os seguintes passos da aula e colocar abaixo: `#A0859 2. Criando primeiro tópico - By @iAzazelOfc` 🔥

```bash
    docker exec -it kafka bash
    
    kafka-topics --create --topic tp-cpf-validated --partitions 3 --replication-factor 1 --bootstrap-server localhost:9092

    kafka-topics --list --bootstrap-server localhost:9092
    
    kafka-topics --describe --topic tp-cpf-validated --bootstrap-server localhost:9092
    
    kafka-console-consumer --bootstrap-server=localhost:9092 --topic=tp-cpf-validated --from-beginning --group=x

    kafka-console-producer --broker-list localhost:9092 --topic tp-cpf-validated
    
    kafka-consumer-groups --bootstrap-server=localhost:9092 --group=x --describe
```