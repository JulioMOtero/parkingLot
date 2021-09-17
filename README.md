# parkingLot

# Setup

O Projeto utiliza Lombok, então é necessário ter o plugin na IDE para navegar.

# Instruções - Teste back-end Java

A construção desse microserviço tem como objetivo avaliar os conhecimentos, código e a organização.

## Tecnologias a serem utilizadas:

- Java 8 ou 11
- Spring Boot
- JPA
- GIT
- Maven

# Definições do projeto

Criar um microserviço para controle de estacionamento, com os seguintes critérios:

- Deve realizar checkin, checkout e pagamento
- Não deve liberar saída sem pagamento
- Deve fornecer um histórico por período

##### Endpoints as serem constrúidos:

## Checkin

POST /parking

Request JSON:
{ brand: 'Volkswagen', model: 'Jetta', plate: 'PRV-1234' }

response:
{ idCheckin: 1 }

Obs: Validar a máscara da placa e retornar um código de reserva.

## Checkout

PUT /parking/:id/checkout

Obs: Não deve liberar saída sem pagamento

## Pagamento

PUT /parking/:id/pay

## Histórico

GET /parking/history

Request params:

- startDate
- endDate

Response JSON:
[
{ id: 1, time: '40 minutos', paid: true, checkout: true }
]

## Testes

Construir ao menos 2 testes unitários

###### Um PLUS a mais, porém não obrigatório

- Construir um arquivo Swagger Yaml com a estrutura da API a ser desenvolvida. Pesquisar "Java Generate swagger
  automatically"

## PROXIMO PASSO

###### Tratamento de Erro

- A API deve retornar codigos de errro de acordo com cada situaçao e significado do erro
    - Ex: checkin sem placa erro 400(bad request)

- Criaçao de exception de negocio
    - Ex: pagamento já efetuado( trocar runtime exception por pagamentoNaoRealizadoException)

- Validações do Request -Ex: validar o tamanho da placa -validar se passou as datas ( se nao erro 400)

Estudar Regex videoOptional<CheckIn> checkout = this.checkinRepository.findById(id); if (checkout.isEmpty()) { throw new
NotFoundException("id: " + id + " Não encontrado"); }
