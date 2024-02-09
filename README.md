# API de Gerenciamento de Pedidos e Itens

Bem-vindo à documentação da API de gerenciamento de pedidos e itens. Esta API permite realizar operações CRUD (Criar/Ler/Atualizar/Excluir) para itens (do tipo PRODUTO ou SERVICO) 
E manipular pedidos podendo adicionar, remover e visualizar os itens do pedido. 

Versões:
- Java 17
- Spring boot 3.2.2
- Postgres 16 (mas não deve dar problema com outras versões)
  - Abrir o arquivo src/main/resources/application.yml
  - Adicionar seu usuario e senha do postgres
  - Adicionar uma database existente ou criar uma chamada loja

Executar o projeto:
- Após ter o ambiente configurado, na pasta raiz executar o comando:
  - ```mvn spring-boot:run```

## Endpoints
### Pedidos
#### Listar Pedidos [GET /pedidos]
- Retorna uma lista paginada de todos os pedidos.


##### Parâmetros de consulta opcionais:
- page: Número da página desejada (default: 0)
- size: Tamanho da página (default: 5)


####  Criar Pedido [POST /pedidos]
- Cria um novo pedido com os seguintes campos:
  - desconto: valor entre 0 e 100 que indica o percentual de desconto a ser aplicado. Caso omitido vai ser 0
  - situacao: (Obrigatório) texto entre uma das opções: ABERTO, FECHADO


####  Detalhes do Pedido [GET /pedidos/{id}]
- Retorna os detalhes de um pedido específico, incluindo seus itens.


####  Aplicar Desconto [PATCH /pedidos/aplicarDesconto]
- Aplica um desconto percentual ao pedido. Recebe um JSON com os campos:
  -  id: (Obrigatório) representa o ID do pedido para aplicar o desconto
  -  percentualDeDesconto: (Obrigatório) valor entre 0 e 100


####  Adicionar Item ao Pedido [PATCH /pedidos/adicionarItem]
- Adiciona um novo item ao pedido. Recebe um JSON com os campos:
    -  id: (Obrigatório) representa o ID do pedido
    -  idItem: (Obrigatório) é o id do item obtido da rota [GET /itens]


####  Remover Item do Pedido [PATCH /pedidos/removerItem]
- Remove um item do pedido. Recebe um JSON com os campos:
    -  id: (Obrigatório) representa o ID do pedido
    -  idItem: (Obrigatório) é o id do item obtido da rota [GET /itens]


####  Abrir Pedido [PATCH /pedidos/abrirPedido]
- Altera o status de um pedido para Aberto. Recebe um JSON com os campos:
    -  id: (Obrigatório) ID do pedido


####  Fechar Pedido [PATCH /pedidos/fecharPedido]
- Altera o status de um pedido para Fechado. Recebe um JSON com os campos:  
    -  id: (Obrigatório) ID do pedido


### Itens
####  Listar Itens [GET /itens]
- Retorna uma lista paginada de todos os itens.
##### Parâmetros de consulta opcionais:
- page: Número da página desejada (default: 0)
- size: Tamanho da página (default: 5)


####  Criar Item [POST /itens]
- Cria um novo item com os campos:
  - nome:(obrigatório) texto de no máximo 100 caracteres
  - preco:(obrigatório) número acima de 0
  - ativo:(obrigatório) booleano
  - tipo:(obrigatório) pode ser um valor entre: PRODUTO, SERVICO


####  Detalhes do Item [GET /itens/{id}]
- Retorna os detalhes de um item específico.

##Observações Importantes
- Todos os IDs das entidades são gerados automaticamente e são do tipo UUID.
- É possível aplicar filtros na listagem de pedidos e itens.
- As entidades utilizam Bean Validation para garantir a integridade dos dados.
- Vários ControllerAdvice foram implementados para customizar as exceptions.
- No cadastro de produtos/serviços, é possível diferenciar um produto de um serviço através do campo tipo que pode ser 'PRODUTO' ou 'SERVICO'.
- É possível aplicar desconto no carrinho ou adicionar/remover itens somente se o pedido estiver na situação ABERTO
- O valorTotal do pedido será a soma dos valores dos itens do pedido com o percentual de desconto já aplicado nos itens que são do tipo 'PRODUTO'.
- Não é possível excluir um produto/serviço se ele estiver associado a algum pedido.
- Não é possível adicionar um produto desativado em um pedido.
