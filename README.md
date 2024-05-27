# Sincronização Bacen

### Instruções

**1º** - Executar o comando ./gradlew clean bootJar para gerar o jar do projeto

**2º** - Executar o comando java -jar build/libs/sincronizacao-bacen-0.0.1-SNAPSHOT.jar <caminho_arquivo> Exemplo: /home/carlos/DATA.csv

**3º** - Após a execução será criado um arquivo DATA-RESULT.csv no mesmo diretório que foi informado por parâmetro

### Observações

* O projeto foi desenvolvido em Java 17
* Contempla testes unitários
