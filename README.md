# Premiação Pior Filme
Esta aplicação tem a finalidade de retornar a lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

## Preparação da aplicação antes da execução
Adicione o arquivo csv com nome **movielist.csv** na pasta **\src\main\resources\dadosCsv**. Este arquivo csv deve conter 5 colunas na seguinte ordem:
1. Ano do Filme
2. Título do Filme
3. Nomes dos estudios(Separados por virgula)
4. Nomes dos Produtores(Separados por virgula)
5. Ganhador(Os filmes ganhadores deverão ter o valor "yes" nesta coluna do csv)

Para separar as colunas utilize o caracter ";".

Caso o nome do arquivo esteja errado aparecerá no log da aplicação a mensagem *Arquivo csv não encontrado*.

## Executando a aplicação
Vá até o pacote br.com.analisador.filmes.boot onde terá a classe AnalisadorFilmesApplication. Clique com botão direito > Run As > Java Application.

Nos logs você verá a mensagem *Dados carregados com sucesso* mostrando que a aplicação já carregou as informações no banco de dados H2 e esta no ar pronto para uso.

## Executando testes
Abra a pasta /src/test/java e vá até o pacote br.com.analisador.filmes.negocio.services. Clique com botão direito > Run As > JUnit Test.
