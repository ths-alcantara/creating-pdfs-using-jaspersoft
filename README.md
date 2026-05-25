
# Criando documentos com JasperSoft e Java
Há um tempo atrás precisei fazer um trabalho parecido com esse e não achava tanta informação sobre como fazer, hoje em dia achei muito mais informação, mas segue aqui minha contribuição também 😉​.

##  Sobre o projeto 👩🏻‍💻
Criei, da forma mais enxuta possível (até o momento), uma pequena API que recebe um arquivo CSV e retorna um arquivo .zip contendo PDFs de certificados fictícios.

Cada certificado é gerado com base em uma linha do CSV, ou seja: um arquivo com 100 linhas resulta em 100 PDFs, cada um contendo suas respectivas informações, como nome do aluno, cpf, curso, escola, data e nome do professor, todas essas informações são variáveisali mentadas pelo CSV.

Esse projeto pode servir como base para diferentes tipos de geração de documentos, como, notas fiscais, boletins, relatórios,documentos de estoque, certificados e por ai vai.

Atualmente a aplicação utiliza um CSV como fonte de dados, mas a mesma estrutura pode ser adaptada facilmente para consumir informações diretamente de um banco de dados.


## Sobre o JasperSoft
Na minha opinião, é um ótimo software para montar o design visual (que definitivamente é o meu pior inimigo 😅), principalmente por possuir uma interface gráfica onde você consegue visualizar exatamente o que está criando antes mesmo de compilar o relatório.

Além disso, alterações no layout costumam ser bem práticas de fazer.
Mas sim… ele tem uma forte estética de software dos anos 2000.

Utilizei a Community Version que pode ser baixada aqui :
https://www.jaspersoft.com/products/jaspersoft-community

## Bibliotecas principais

- jasperreports
- jasperreports-pdf
- opencsv


## Screenshots
Ao rodar essa aplicação você deve ser capaz de gerar pdfs com esse design aqui (eu deixei um csv de exemplo salvo no repositorio):
<img width="1024" height="723" alt="Captura de tela 2026-05-25 203421" src="https://github.com/user-attachments/assets/804c29a1-3349-4635-b9a7-7f086a9cafde" />
(não, graphic design is not my passion)


## Autores

- [@ths-alcantara](https://github.com/ths-alcantara)

