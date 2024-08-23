# Rodar o projeto em qualquer máquina e poder trabalhar nele

## Rodar local usando docker compose

```bash
    docker compose up -d
    docker exec -it <nome-do-container> bash
```

### Observações

> Caso esteja tendo problemas, rodar os seguintes comandos para limpeza de chace:

```bash
    docker system prune -a # Remove stopped containers and unused images, networks, and volumes:
    docker builder prune # Remove the build cache:
    docker volume prune # Remove the volumes associated with your services (optional, if you want to clear volumes as well):
    docker-compose build --no-cache # Rebuild the Docker image without using the cache:

    docker system prune -a -f && docker builder prune -f && docker volume prune -f && docker-compose build --no-cache # Todos os comandos em uma linha para facilitar
```

## Rodar usando linha de comando do docker

```bash
    docker run -it --name engect-project -p 8000:8000 --mount type=bind,source="$(pwd)",target=/app python:3.10.13-bookworm bash
```

## Rodar os comandos a seguir dentro do container o rasa

```bash
    pip install -r requirements.txt
```

## Salvar recursos instalados _caso necessário_

```bash
    pip freeze >> requirements.txt
```

## Rodar o projeto internamente acessando o container

Execute os seguintes comandos em sequencia

```bash
    docker compose down
    docker compose up -d
    docker exec -it -u root sistema_engect bash
    python sistema_engect/manage.py runserver 0.0.0.0:8000
```

## Rodar o projeto em ambiente de homologação ou para desenvolvedor

```bash
    docker compose -f docker-compose.dev.yml build --no-cache
    docker compose -f docker-compose.dev.yml up
```

## Rodar o projeto em ambiente de homologação ou para prod

```bash
    docker compose -f docker-compose.prod.yml build --no-cache
    docker compose -f docker-compose.prod.yml up
```

### Derrubar ambiente de homologação ou desenvolvedor

```bash
    docker compose -f docker-compose.prod.yml down
```

## TODO

Criar docker-compose.prod.yml para desenvolvimento em produção
