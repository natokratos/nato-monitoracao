# Requisitos para desenvolvimento

# Pré-requisito
- Docker versão mais atual
- Maven versão mais atual
- Git client versão mais atual
- SOAP UI versão mais atual
- IDE para Desenvolvimento: STS, Eclipse ou IntelliJ
- Criar um diretório na estação chamado workpsace que conterá os códigos-fonte
- Editar o arquivo /etc/hosts e adicionar os seguintes IP's
	
	127.0.0.1       tivit-core
	127.0.0.1       tivit-api-gateway
	127.0.0.1       tivit-api-accounts
	127.0.0.1       tivit-api-transfers
	
# Baixar o código-fonte

- Via linha de comando, entrar no diretório que conterá os códigos-fonte e executar os comandos abaixo, caso esteja vazio:

	git init
	git clone http://git.sirius.tivit.com.br/tivit-monitoracao/tivit-monitoracao.git

- Abrir a IDE de desenvolvimento, importar como projeto Maven existente

# Compilar a aplicação

- Via linha de comando, mudar para o diretório tivit-core dentro do diretório de códigos-fonte
  
	cd $HOME/workspace/tivit-core
  
- Via linha de comando, executar o comando abaixo para gerar as classes, executar os testes e gerar o relatório de cobertura:

	mvn clean install test  

- A partir daqui basta usar a IDE de desenvolvimento para construir o código, compilar e testar a aplicação

# Relatório de Cobertura

- O relatório estará disponível dentro deste mesmo diretório em target/site/jacoco/index.html, deve ser acessado por um browser de sua escolha

# Rodando a aplicação local
  
- Para rodar basta executar a aplicação pelo IDE de desenvolvimento

# Rodando a aplicação no Docker

- A aplicação já deve estar compilada para que funcione no Docker, veja na seção "Compilar a Aplicação"

- Via linha de comando, mudar para o diretório src/main/docker

- Executar o comando a seguir. Caso apareça um erro dizendo que já existe, apenas ignore e continue no item abaixo:

	docker network create tivit-micro-net

- Executar o comando a seguir:

	docker build -t tivit-core . 
	
- Veja se aparece alguma imagem com o nome tivit-core executando este comando:

	docker images
	
- Caso apareça execute o comando a seguir para criar o container pela primeira vez, não é necessário executar novamente:

	docker run -d -p 9090:9090 --name tivit-core --network tivit-micro-net tivit-core
	
- Parar o container:

	docker stop tivit-core
	
- Iniciar o container:

	docker start tivit-core
	
# Atualizar a Aplicação no Docker

- Para atualizar a aplicação no docker, primeiro pare o container:

	docker stop tivit-core

- Compile a aplicação novamente conforme descrito na seção "Compilar a Aplicação"

- Via linha de comando mude para o diretório src/main/docker e execute o comando a seguir:

	docker cp tivit-core*.jar tivit-core:/root/tivit-core.jar
	
- Reinicie o container:
	
	docker start tivit-core
	
# Web Services SOAP

- Para acessar os WebServices SOAP, digite o seguinte endereço pelo browser:

	http://localhost:9090/services/transfers
	http://localhost:9090/services/transfers?wsdl
	  
	http://localhost:9090/services/accounts
	http://localhost:9090/services/accounts?wsdl

- Para testar os serviços use o SOAP UI. Abra a ferramenta, crie um novo projeto e aponte para um dos WSDL localizados dentro da aplicação em:

	src/main/resources/AccountsImplService.wsdl
	src/main/resources/TransfersImplService.wsdl

- Após a criação, adicione o outro WSDL ao projeto. A ferramenta já irá criar objetos para testar todos os métodos disponíveis nos WSDL

- Selecione um dos métodos, preencha as informações necessárias e execute
- Você pode salvar os casos de teste criados numa suíte maior para executar sempre que precisar

# Banco em memória

- Para acessar o H2 (banco em memória) use o seguinte endereço:

	http://localhost:9090/h2
    
- Coloque no campo JDBC_URL o valor:

	jdbc:h2:mem:tivitdb
  
- Coloque no campo Username o valor:

	sa
  
- Deixe o campo Password vazio
  
- Clique em Connect
  