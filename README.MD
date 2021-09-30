## Sobre o app

### Goodha - Educação, rotinas e atividades para pais e filhos

Seu filho passa horas no celular ou no videogame e você não sabe mais o que fazer?

Que tal tomar o controle da rotina dos seus filhoEntities e poder aproveitar alguns momentos juntos entre pais e filhoEntities?

Com a ajuda de Goodha, pais e filhoEntities podem criar rotinas e atividades divertidas e manter hábitos saudáveis juntos.

Tudo isso de forma fácil, intuitiva e divertida


### Com o Goodha você pode:
- Criar atividades personalizadas para você e seu filhoEntity.
- Utilizar tarefas já criadas por outros usuários.
- Ser notificado quando for o horário de uma atividade.
- Criar usuario
- Criar notificação de atividade

#### Para fazer o download do aplicativo [clique aqui](https://play.google.com/store/apps/details?id=com.raphaelinacio.goodha)


## Sobre esse projeto

Esse projeto é a aplicação backend que suporta o aplicativo [goodha](https://play.google.com/store/apps/details?id=com.raphaelinacio.goodha)

### Quais os pilares para essa aplicação

Os pilares para a construção dessa aplicação são TDD, Clean Architeture, SOLID e Design Patterns

### Tecnologias utilizadas

- Java 11
- Spring boot
- Cloud Datastore - Banco de Dados
- AppEngine - Plataforma de execução
- Cloud Trace - Tracing
- Cloud Pub/Sub - Mensageria
- Cloud Storage para armazenamento de imagens
- Cloud Vision API para verificar o conteudo das imagens enviadas pelos usuarios

## Referencias de estudo
 - Alura Curso de Clean Architecture
 - Branas IO - Clean Code e Clean Architecture-
 - Coursera - Building Scalable Java Microservices with spring boot and spring cloud

## Comandos e informações uteis

* URL ``https://macro-scion-300810.rj.r.appspot.com``
* Deploy via GCLOUD ``gcloud app deploy  src/main/appengine/app.yaml``
* Deploy app no appengine via mvn ``mvn clean package appengine:deploy``
* App ``https://play.google.com/store/apps/details?id=com.raphaelinacio.goodha``

