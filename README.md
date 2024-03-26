# Serviço de E-mail - Backend

Este é o repositório do backend para o Serviço de E-mail, que oferece funcionalidades para enviar e-mails usando duas implementações: Java Mail Sender e SendGrid.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- RabbitMQ
- Java Mail Sender
- SendGrid API
- Maven
- Log4j2

## Instalação e Configuração

1. Clone este repositório:

```bash
git clone https://github.com/FelipeBert/Email-Service.git
```

## Configuração do Java Mail Sender:

Configure as propriedades do Java Mail Sender no arquivo application.properties ou application.yml dentro do diretório servico-email-java-mailsender/src/main/resources.

### Exemplo de configuração:

```
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=seu-email@example.com
spring.mail.password=sua-senha
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Configuração do SendGrid:

Configure a chave da API do SendGrid no arquivo application.properties ou application.yml dentro do diretório servico-email-sendgrid/src/main/resources.
### Exemplo de configuração:

```
sendgrid.api.key=sua-chave-de-api
```

## Configuração do RabbitMQ:

Verifique se o RabbitMQ está instalado e em execução na sua máquina ou em um servidor remoto.
Configure as credenciais do RabbitMQ no arquivo application.properties ou application.yml dentro de cada componente que utiliza o RabbitMQ.
### Exemplo de configuração:

```
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

## Compile o projeto:

```bash
mvn clean install
```

O servidor estará em execução em `http://localhost:8080`.

- `src/main/java/com/example/emailservice/`: Contém todo o código-fonte do backend.
- `core/`: Entidades e regras de negocio da Aplicação.
- `controllers/`: Controladores para manipulação de rotas e lógica de negócios.
- `dto/`: Data Transfer Objects (DTOs)
- `adapter/`: Contém os adaptadores para integração com serviços externos.
- `repositories/`: Repositórios para acesso aos dados.
- `services/`: Serviços para implementar a lógica de negócios.
- `infraestructure/`: Contém as configurações dos serviços externos SendGrid, Java Mail Sender e RabbitMQ.
- `src/main/resources/`: Recursos estáticos e arquivos de configuração, como arquivos de propriedades e YAML.

## Comunicação por Mensageria com RabbitMQ
O projeto utiliza o RabbitMQ para enviar e-mails por meio de mensageria. Permitindo uma comunicação assíncrona e escalável entre os diferentes componentes do sistema.

## Logging
O projeto utiliza o Log4j2 como framework de logging. Os logs são armazenados no diretório `logs/`.

## Licença
```
Este projeto está licenciado sob a Licença MIT
```