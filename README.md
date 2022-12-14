# API - Cais

<h2> Informações</h2>

Projeto de API para gerenciamento de um cais, as tecnologias usadas nesse
projeto foram:
<ul>
  <li>Spring Boot</li>
  <li>Spring Security</li>
  <li>JWT</li>
  <li>Model Mapper</li>
  <li>Lombok</li>
  <li>MYSQL</li>
  <li>Flyway</li>
  <li>Postman</li>
</ul>

<h3> Na classe WebConfig, foi habilitado o Cors e addFormatters para formatar Enums que serão utilizados para filtros</h3>

![Captura de Tela (188)](https://user-images.githubusercontent.com/81782608/210427885-c61f89dc-d622-4242-979a-f3d683383d4f.png)

<h3> Foram criadas duas classes para converter os Enums Origin e Status</h3>

![Captura de Tela (189)](https://user-images.githubusercontent.com/81782608/210429234-4793bb90-1533-4c00-b785-7914aee7efd0.png)

![Captura de Tela (190)](https://user-images.githubusercontent.com/81782608/210429240-a85767b0-273b-4ccf-86f9-4841fedcf267.png)

<h3>Na pasta services se encontram os dtos de requisição e de resposta, a pasta assembler que contém classes com metodos relacionadas a serialização de objetos utilizando o ModelMapper e por fim as classes de serviços das entidades</h3>

![Captura de Tela (191)](https://user-images.githubusercontent.com/81782608/210429831-d470df44-aee8-475b-8618-dd4b00f769a6.png)

<h3>Na pasta exceptions, estão as subpastas <strong>response</strong> que contém as classe de exceção, e a <strong>handler</strong> que contém a calsse de captura de erros e seus tratamentos</h3>

![Captura de Tela (192)](https://user-images.githubusercontent.com/81782608/210433898-4ea1a5c8-723b-4aaf-a252-9cb5df2f011e.png)

<h2>Controllers</h2>

<h3>AuthController</h3>
<p>Controller criado para fazer login de usuários, gerando token a partir do seu service <strong>TokenService</strong> e autenticação com o <strong>AuthenticationManager</strong>

![Captura de Tela (194)](https://user-images.githubusercontent.com/81782608/210434625-74853d0e-4ff1-41b6-8eff-10965f32b41a.png)

<h3>CompanyController</h3>

![Captura de Tela (195)](https://user-images.githubusercontent.com/81782608/210434741-53ea85e2-f809-46cb-8a80-cb37cac8dc59.png)

<h3>PierController</h3>

![Captura de Tela (196)](https://user-images.githubusercontent.com/81782608/210434814-5b3d3988-82a2-4568-a0ce-e459c76f588e.png)

<h3>ShipController</h3>

![Captura de Tela (197)](https://user-images.githubusercontent.com/81782608/210434922-19ebe48f-be8a-455b-ae3a-66e2ee10ce96.png)

<h3>StayController</h3>

![Captura de Tela (198)](https://user-images.githubusercontent.com/81782608/210435017-e0e29ea0-d1a3-4641-b8ab-1c43ba975395.png)

<h3>UserController</h3>

![Captura de Tela (200)](https://user-images.githubusercontent.com/81782608/210435081-3eb1264d-e5c0-47ee-8bb2-87952e3c6625.png)

<h2>Testes</h2>

![Captura de Tela (245)](https://user-images.githubusercontent.com/81782608/210653151-a9b1818d-adf5-42dd-beea-be1d00cb014b.png)

<h2>Swagger</h2>

![Captura de Tela (241)](https://user-images.githubusercontent.com/81782608/210637068-7384241b-7a96-4326-ba81-1ffd545015e2.png)

<h2>Postman - Requisições</h2>

<h3>User</h3>

<h4>Criar User</h4>

![Captura de Tela (201)](https://user-images.githubusercontent.com/81782608/210437953-50d2babb-14cf-4308-bcab-55b3ac292afb.png)

<h4>Login</h4>
<p> O Login será devolvido o Token, esse token dará acesso aos demais endpoints da aplicação, token tem validade de 24 horas

![Captura de Tela (203)](https://user-images.githubusercontent.com/81782608/210438126-7f98c858-9230-461b-a7d8-23d460f78c80.png)

<h4>Listar</h4>

![Captura de Tela (204)](https://user-images.githubusercontent.com/81782608/210439924-83c4cf7c-81fd-4a22-b5a3-762eae40053a.png)

<h4>Buscar</h4>
<p> Possui verificações retornando status de erros referente a cada quebra das verificações, mais informações no swagger

![Captura de Tela (205)](https://user-images.githubusercontent.com/81782608/210440061-582bdfab-3db6-4891-befa-0661bfbe7340.png)

<h4>Atualizar</h4>

![Captura de Tela (242)](https://user-images.githubusercontent.com/81782608/210639558-26195283-5f40-4095-bd45-5f266a8fed83.png)

<h4>Delete</h4>
<p> Você pode excluir o seu usuário e automaticamente você será deslogado

![Captura de Tela (243)](https://user-images.githubusercontent.com/81782608/210640287-058567ea-1e3f-4f2b-8db5-47a3f5a27a53.png)

<h3>Company</h3>

<h4>Listar </h4>
<p> Possui Filtros, podendo assim filtrar por Origin, Name, fazer parginação e outras funcionalidades que o Pageable trás 

![Captura de Tela (207)](https://user-images.githubusercontent.com/81782608/210441540-2cd726f4-5f50-43e0-8597-38c352a08ae4.png)

<h4>Listar todos os Ship de uma determinada Company</h4>

![Captura de Tela (209)](https://user-images.githubusercontent.com/81782608/210442080-f8426588-adae-4258-9a7b-59cc7e42dfac.png)

<h4>Buscar por Name</h4>

![Captura de Tela (210)](https://user-images.githubusercontent.com/81782608/210442713-5d060379-d9b4-4bec-98aa-df2b3b41a897.png)

<h4>Buscar por Id</h4>

![Captura de Tela (211)](https://user-images.githubusercontent.com/81782608/210442769-c3a2ec8f-562c-4259-8d94-5232bcd19900.png)

<h4>Atualizar</h4>

![Captura de Tela (212)](https://user-images.githubusercontent.com/81782608/210442924-e74f9b08-56e5-49c1-b145-c7e8021a4653.png)

<h4>Adicionar</h4>

![Captura de Tela (213)](https://user-images.githubusercontent.com/81782608/210443121-2b1241b2-2a2e-4005-9c1e-d8b450919232.png)

<h4>Delete</h4>

![Captura de Tela (214)](https://user-images.githubusercontent.com/81782608/210443196-96f5cd05-041d-4580-a73a-5960f5c7fb8c.png)

<h4>Vincular</h4>

![Captura de Tela (215)](https://user-images.githubusercontent.com/81782608/210443473-b4a75652-ee3e-4b4e-bb8f-ff57e4160612.png)

<h4>Desvincular</h4>

![Captura de Tela (216)](https://user-images.githubusercontent.com/81782608/210443533-1e4a4c86-5af8-4e24-9e98-3219c20e4020.png)

<h3>Pier</h3>

<h4>Listar </h4>
<p> Possui Filtros, parginação e outras funcionalidades que o Pageable trás 

![Captura de Tela (217)](https://user-images.githubusercontent.com/81782608/210444504-69e6f1dd-0f24-468c-9aef-cadd02418cd3.png)

<h4>Buscar por Id</h4>

![Captura de Tela (218)](https://user-images.githubusercontent.com/81782608/210444691-13be410b-e7ef-4135-952a-e463dda2d984.png)

<h4>Buscar por Name</h4>

![Captura de Tela (219)](https://user-images.githubusercontent.com/81782608/210445206-39d8c088-9868-4262-b9f4-6f258122ca15.png)

<h4>Criar </h4>

![Captura de Tela (220)](https://user-images.githubusercontent.com/81782608/210445251-6d3b5093-6965-44c1-8622-dd416977b70d.png)

<h4>Delete </h4>
<p> Camada de verificação, não é possível excluir um Pier que um Ship está usando, mais detalhes de verificação no swagger

![Captura de Tela (221)](https://user-images.githubusercontent.com/81782608/210445316-8fcb571f-0260-4231-9b44-535a8bd9a9e0.png)

<h4>Atualizar</h4>

![Captura de Tela (222)](https://user-images.githubusercontent.com/81782608/210445633-09732741-e59d-44cd-8fe3-abd004357ae7.png)

<h4>Vincular</h4>

![Captura de Tela (223)](https://user-images.githubusercontent.com/81782608/210445830-1f5ed342-ee36-4d5a-beb2-2b14f1ec5140.png)

<h4>Desvincular</h4>

![Captura de Tela (224)](https://user-images.githubusercontent.com/81782608/210445911-992c6337-d846-4b37-a343-95f215f9f0af.png)

<h3>Ship</h3>

<h4>Listar</h4>
<p> Possui Filtros, parginação e outras funcionalidades que o Pageable trás 

![Captura de Tela (225)](https://user-images.githubusercontent.com/81782608/210591954-992e8275-cc79-4e94-b0de-b5bff10ec2b7.png)

<h4>Buscar</h4>

![Captura de Tela (226)](https://user-images.githubusercontent.com/81782608/210592121-6345e5c2-a1d8-4f00-9aa7-cfdb767f3b93.png)

<h4>Criar</h4>
<p> O novo Ship vem com Company e Pier Null, na regra de negócio o Ship será vinculado com o a Company que ele pertece e com  o Pier que ele irá atracar 

![Captura de Tela (227)](https://user-images.githubusercontent.com/81782608/210592254-ddbfed87-24eb-428c-99d4-04036df6ffa8.png)

<h4>Atualizar</h4>
<p> Com verificações

![Captura de Tela (229)](https://user-images.githubusercontent.com/81782608/210592815-354683c6-da65-4d08-924b-1f4a9441d179.png)

<h4>Excluir</h4>

![Captura de Tela (230)](https://user-images.githubusercontent.com/81782608/210593056-2d9395f7-1371-4bf2-9054-63430df8e1ec.png)

<h3>Stay</h3>

<h4>Listar</h4>

![Captura de Tela (231)](https://user-images.githubusercontent.com/81782608/210594370-b189fafe-2bf9-4953-b8c0-09ed5a57beed.png)

<h4>Buscar</h4>

![Captura de Tela (232)](https://user-images.githubusercontent.com/81782608/210594452-16fbcccb-bcc9-43a8-9ff6-ac40f14c38ca.png)

<h4>Atualizar</h4>
<p> Altera apenas o finalPrice

![Captura de Tela (236)](https://user-images.githubusercontent.com/81782608/210598971-1b3cc3ca-ed9e-4257-814c-797995c0e94c.png)

<h4>Entrada</h4>
<p> Automaticamente será criado uma nova stay, o finalPrice e a saída ficam como null, só pode criar uma stay com o mesmo ship se ele não tiver uma stay ou sua stay estiver com status close

![Captura de Tela (237)](https://user-images.githubusercontent.com/81782608/210599097-f704eb70-84b0-40ed-aaa4-994be418ea75.png)

<h4>Saída</h4>
<p> Automaticamente será calculado o finalPrice e a mudança de status para close

![Captura de Tela (233)](https://user-images.githubusercontent.com/81782608/210595616-5fd9ba54-1ada-4c0c-aeab-58e9d993d942.png)

<h4>Listar Stays de um determinado Ship</h4>

![Captura de Tela (240)](https://user-images.githubusercontent.com/81782608/210600042-b19ac40b-d92a-4941-81fc-84d8517ad7fe.png)
