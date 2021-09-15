### Bons atividades para crianças

Pais podem escolher atividades para os seus filhoEntities diários para os seus filhoEntities, como também podem criar novos atividades
ou escolher atividades de outras pessoas.

### 
Seu filhoEntity passa horas no celular ou no videogame e você não sabe mais o que fazer?

Que tal tomar o controle da rotina dos seus filhoEntities e poder aproveitar alguns momentos juntos entre pais e filhoEntities?

Com a ajuda de Goodha, pais e filhoEntities podem criar rotinas e atividades divertidas e manter hábitos saudáveis juntos.

Tudo isso de forma fácil, intuitiva e divertida

* Com o Goodha você pode:
- Criar atividades personalizadas para você e seu filhoEntity.
- Utilizar tarefas já criadas por outros usuários.
- Ser notificado quando for o horário de uma atividade.
- Criar usuario
- Criar notificação de atividade

### Quais os pilares para essa aplicação

Os pilares para a conctrução dessa aplicação são TDD, Clean Architeture e DDD, SOLID e Design Patterns

### Camada application

- Cloud Datastore - Banco de Dados
- AppEngine - Plataforma de execução
- Cloud Trace - Tracing
- Cloud Pub/Sub - Mensageria
- Cloud Storage para armazenamento de imagens

## Referencias de estudo
 - Alura Curso de Clean Architecture
 - Branas IO - Clean Code e Clean Architecture-
 - Coursera - Building Scalable Java Microservices with spring boot and spring cloud

## Como rodar

* URL ``https://macro-scion-300810.rj.r.appspot.com``
* Deploy via GCLOUD ``gcloud app deploy  src/main/appengine/app.yaml``
* Deploy app no appengine via mvn ``mvn clean package appengine:deploy``
